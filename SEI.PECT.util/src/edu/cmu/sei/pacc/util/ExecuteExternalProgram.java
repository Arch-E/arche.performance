/*
 * ArchE
 * Copyright (c) 2012 Carnegie Mellon University.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following acknowledgments and disclaimers.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. All advertising materials for third-party software mentioning features or
 * use of this software must display the following disclaimer:
 *
 * “Neither Carnegie Mellon University nor its Software Engineering Institute
 * have reviewed or endorsed this software”
 *
 * 4. The names “Carnegie Mellon University,” and/or “Software Engineering
 * Institute" shall not be used to endorse or promote products derived from
 * this software without prior written permission. For written permission,
 * please contact permission@sei.cmu.edu.
 *
 * 5. Redistributions of any form whatsoever must retain the following
 * acknowledgment:
 *
 * Copyright 2012 Carnegie Mellon University.
 *
 * This material is based upon work funded and supported by the United States
 * Department of Defense under Contract No. FA8721-05-C-0003 with Carnegie
 * Mellon University for the operation of the Software Engineering Institute, a
 * federally funded research and development center.
 *
 * NO WARRANTY
 *
 * THIS CARNEGIE MELLON UNIVERSITY AND SOFTWARE ENGINEERING INSTITUTE MATERIAL
 * IS FURNISHED ON AN “AS-IS” BASIS. CARNEGIE MELLON UNIVERSITY MAKES NO
 * WARRANTIES OF ANY KIND, EITHER EXPRESSED OR IMPLIED, AS TO ANY MATTER
 * INCLUDING, BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR PURPOSE OR
 * MERCHANTABILITY, EXCLUSIVITY, OR RESULTS OBTAINED FROM USE OF THE MATERIAL.
 * CARNEGIE MELLON UNIVERSITY DOES NOT MAKE ANY WARRANTY OF ANY KIND WITH
 * RESPECT TO FREEDOM FROM PATENT, TRADEMARK, OR COPYRIGHT INFRINGEMENT.
 */

package edu.cmu.sei.pacc.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Used to exec an external program and retrieve any output on stdio or stdout. 
 * The current interface is very simple. The constructor sets up the command 
 * line and creates buffers to capture output. Afterward, there are two ways
 * to execute a program: synchronously or asynchronously.<p>
 * 
 * (1) Synchronous execution is achieved by calling exec(), which will execute
 * the external thread and block the calling thread until execution is complete.
 * After exec() returns, stdout and stderr can be retrieved by calling getStdOut()
 * and getStdErr().<p>
 * 
 * (2) Asynchronous execution is achieved by calling asynchExec(), which will 
 * return once the external program has been launched. asynchExec() takes an
 * optional parameter that is an object to notify on completion. If no object is
 * provided, the caller will have to poll (calling isDone()) to check for completion.
 * Asynchronous execution can be cancelled by calling terminate().<p>
 * 
 * The following code (taken from AnalyzeAction.verifyClaim() in ComFoRT) shows 
 * one way to use asynchronous execution to enable cancellation from the 
 * eclipse UI:<p>
 * 
 * <code><pre>
 * program.asynchExec(complete);
 *	while (program.isDone() == false) {
 *	   if (monitor.isCanceled()) {
 *	      if (program.terminate() == true) {
 *	         _dest.println("Verification cancelled.");
 *	         return null;
 *	      } else if (program.isDone() == true){
 *	         break;
 *	      }
 *	   }
 *	   synchronized (complete) {
 *	      try {
 *	         // check for cancellation every 8 seconds
 *	         complete.wait(8000);
 *	      } catch (InterruptedException e) {
 *	         continue;
 *	      }
 *	   }
 *	}
 *	result = program.getExitValue();
 * </pre></code><p>
 *
 * Either way, this class can only be exec'd once.
 * 
 * For more information about calling external programs from java, see:
 * 
 * @see http://www.javaworld.com/javaworld/jw-12-2000/jw-1229-traps.html
 * @author jivers
 */
public class ExecuteExternalProgram {
   /** used to store exec results to stdout (default option) */
	private StringWriter 		_stdout = null;
	/** used to store exec results to stderr */
	private StringWriter			_stderr = null;
	/** program string to be executed */
	private String 				_cmd = null;			// either _cmd OR _cmdArray has the command to execute
	private String[]				_cmdArray = null;
	private Process				_process = null;	// only used for asynchronous execution
	private Object					_monitor = null;	// object to notify on completion (asynch only)
	private boolean				_done = false;
	private boolean				_executingAsynchronously = false;
	private int						_exitValue = 0;
	private boolean				_writeToFile = false;
	private Writer					_outFile = null;	// optional file to stream output to
	private boolean				_executed = false;
	private File					_workingDirectory = null;	// optional

 
	/**
	 * @param cmd
	 *           command line to be executed in an OS shell.
	 * @param workingDirectory
	 * 			 directory in which to execute the command line (may be null)
	 * This form caches all output on stdout, which can be retrieved
	 * by calling getStdOut();
	 */
	public ExecuteExternalProgram(String cmd) {
		_cmd = cmd;
		_workingDirectory = null;
		_stdout = new StringWriter();
		_stderr = new StringWriter();
	} // end of ctr

	
	/**
	 * @param cmd
	 *           command line to be executed in an OS shell.
	 * @param workingDirectory
	 * 			 directory in which to execute the command line (may be null)
	 * This form caches all output on stdout, which can be retrieved
	 * by calling getStdOut();
	 */
	public ExecuteExternalProgram(String cmd, File workingDirectory) {
		_cmd = cmd;
		_workingDirectory = workingDirectory;
		_stdout = new StringWriter();
		_stderr = new StringWriter();
	} // end of ctr
	
	
	/**
	 * @param cmd
	 * 				command line to be executed in an OS shell.
	 * @param fileName
	 * 				name of the file to which stdout should be piped.
	 * stdout cannot be retrived via getStdOut() when using this form.
	 * @throws IOException if output file cannot be opened
	 */
	public ExecuteExternalProgram(String cmd, String fileName) throws IOException {
		_cmd = cmd;
		_workingDirectory = null;
		_outFile = new BufferedWriter(new FileWriter(fileName));
		_writeToFile = true;
		_stderr = new StringWriter();
	} // end of ctr
	

	/**
	 * @param cmd
	 * 				command line to be executed in an OS shell.
	 * @param fileName
	 * 				name of the file to which stdout should be piped.
	 * @param workingDirectory
	 * 			 directory in which to execute the command line (may be null)
	 * stdout cannot be retrived via getStdOut() when using this form.
	 * @throws IOException if output file cannot be opened
	 */
	public ExecuteExternalProgram(String cmd, String fileName, 
			File workingDirectory) throws IOException {
		_cmd = cmd;
		_workingDirectory = workingDirectory;
		_outFile = new BufferedWriter(new FileWriter(fileName));
		_writeToFile = true;
		_stderr = new StringWriter();
	} // end of ctr

	
	/**
	 * @param cmd
	 *           array containing command line to be executed in an OS shell.
	 * @param workingDirectory
	 * 			 directory in which to execute the command line (may be null)
	 * This form caches all output on stdout, which can be retrieved
	 * by calling getStdOut();
	 */
	public ExecuteExternalProgram(String[] cmd) {
		_cmdArray = cmd;
		_workingDirectory = null;
		_stdout = new StringWriter();
		_stderr = new StringWriter();
	} // end of ctr

	
	/**
	 * @param cmd
	 *           array containing command line to be executed in an OS shell.
	 * @param workingDirectory
	 * 			 directory in which to execute the command line (may be null)
	 * This form caches all output on stdout, which can be retrieved
	 * by calling getStdOut();
	 */
	public ExecuteExternalProgram(String[] cmd, File workingDirectory) {
		_cmdArray = cmd;
		_workingDirectory = workingDirectory;
		_stdout = new StringWriter();
		_stderr = new StringWriter();
	} // end of ctr
	
	
	/**
	 * @param cmd
	 * 				array containing command line to be executed in an OS shell.
	 * @param fileName
	 * 				name of the file to which stdout should be piped.
	 * stdout cannot be retrived via getStdOut() when using this form.
	 * @throws IOException if output file cannot be opened
	 */
	public ExecuteExternalProgram(String[] cmd, String fileName) throws IOException {
		_cmdArray = cmd;
		_workingDirectory = null;
		_outFile = new BufferedWriter(new FileWriter(fileName));
		_writeToFile = true;
		_stderr = new StringWriter();
	} // end of ctr
	

	/**
	 * @param cmd
	 * 				array containing command line to be executed in an OS shell.
	 * @param fileName
	 * 				name of the file to which stdout should be piped.
	 * @param workingDirectory
	 * 			 directory in which to execute the command line (may be null)
	 * stdout cannot be retrived via getStdOut() when using this form.
	 * @throws IOException if output file cannot be opened
	 */
	public ExecuteExternalProgram(String[] cmd, String fileName, 
			File workingDirectory) throws IOException {
		_cmdArray = cmd;
		_workingDirectory = workingDirectory;
		_outFile = new BufferedWriter(new FileWriter(fileName));
		_writeToFile = true;
		_stderr = new StringWriter();
	} // end of ctr

	
	/**
	 * Exec the command string, save all output, and return the exit value.
	 * Executes on the callers thread. Each ExecuteExternalProgram instance
	 * can only be executed once.
	 */
	public int exec () throws IOException {
		return exec(null);
	}
	
	
	/**
	 * Exec the command string, save all output, and return the exit value.
	 * Executes on the callers thread. Each ExecuteExternalProgram instance
	 * can only be executed once.
	 * 
	 * @param input string which is piped into the standard input of the
	 * 	process. If null, it is ignored.
	 */
	public int exec (String input) throws IOException {
		StreamReader stdout = null, stderr = null;
		Process process = null;

		assert(_executed == false);
		assert(_cmd != null || _cmdArray != null);
		_executed = true;
		try {
			_done = false;
			_exitValue = 0;
			_executingAsynchronously = false;
			if (_cmd != null) {
				process = Runtime.getRuntime().exec(_cmd, null, _workingDirectory);
			} else {
				process = Runtime.getRuntime().exec(_cmdArray, null, _workingDirectory);
			}
			
			if (input != null) {
				OutputStream stdin = process.getOutputStream();
				stdin.write(input.getBytes());
				stdin.flush();
			}
			
			if (_writeToFile == false) {
				stdout = new StreamReader(process.getInputStream(), _stdout);
			} else {
				stdout = new StreamReader(process.getInputStream(), _outFile);
			}
			stderr = new StreamReader(process.getErrorStream(), _stderr);
			_exitValue = process.waitFor();

			// wait for stream readers to complete their reads
			stdout.join();
			if (_writeToFile == true) {
				_outFile.flush();
				_outFile.close();
			}
			stderr.join();
			_done = true;

			process.destroy();
			return _exitValue;
		} catch (InterruptedException e) {
			// interrupts are fine; used during cancellation
			if (_writeToFile == true) {
				_outFile.flush();
				_outFile.close();
			}
		}

		return -1;
	} // end of exec

	
	/**
	 * Start asynchronous execution of program and return. If a monitor object
	 * is supplied, notify on this object when execution completes. Each 
	 * ExecuteExternalProgram instance can only be executed once.
	 */
	// TODO: refactor; should be able to pass in a monitor and
	// an interval. allow this function to handle all code for
	// cancellation.
	public void asynchExec (Object monitor) throws IOException {
		StreamReader stdout = null, stderr = null;
		CompletionMonitor complete = null;
		
		assert(_executed == false);
		assert(_cmd != null || _cmdArray != null);
		_executed = true;

		_monitor = monitor;
		_done = false;
		_exitValue = 0;
		_executingAsynchronously = true;
		if (_cmd != null) {
			_process = Runtime.getRuntime().exec(_cmd, null, _workingDirectory);
		} else {
			_process = Runtime.getRuntime().exec(_cmdArray, null, _workingDirectory);
		}
		if (_writeToFile == false) {
			stdout = new StreamReader(_process.getInputStream(), _stdout);
		} else {
			stdout = new StreamReader(_process.getInputStream(), _outFile);
		}
		stderr = new StreamReader(_process.getErrorStream(), _stderr);
		
		// spawn a thread to monitor for execution completion
		complete = new CompletionMonitor(stdout, stderr);
		complete.start();
	} // end of aysnchExec
	

	/**
	 * Terminates execution is external thread is currently executing asynchronously.
	 * @return true iff termination was executed; false if termination was unnecessary
	 * (e.g., because execution was already complete or program was not executing
	 * asynchronously). Terminate will only return false for an asynchronous execution
	 * if the signal for completion has already be generated.
	 */
	public boolean terminate () {
		// Correct execution relies on ensuring that _done == true only
		// after a signal (if any) has been notified on _monitor. Otherwise,
		// a caller that gets a return value of false here may be in a race
		// condition between the return and the point at which it can call
		// _monitor.wait().
		if ((_executingAsynchronously == true) && (_done == false)) {
			_process.destroy();
			_exitValue = -1;
			_done = true;
			return true;
		}
		return false;
	} // end of terminate
	
	
	/**
	 * @return true iff the external program has completed execution.
	 */
	public boolean isDone () {
		return _done;
	} // end of isDone
	
	
	/** 
	 * @return exit value from the program, if it has completed. otherwise
	 * returns 0.
	 */
	public int getExitValue () {
		return _exitValue;
	} // end of getExitValue
	

	/**
	 * @return Standard output from the execution of the command line. Should
	 *         only be called after execution is complete (isDone() == true).
	 *         If output is being piped to a file, this method will return null.
	 */
	public String getStdOut () {
		if (_writeToFile == false) {
			return _stdout.toString();
		} else {
			return null;
		}
	} // end of getStdOut


	/**
	 * @return Standard error from the execution of the command line. Should only
	 *         be called after execution is complete (isDone() == true).
	 */
	public String getStdErr () {
		return _stderr.toString();
	} // end of getStdErr


	/** 
	 * Inner class for monitoring for completion of an asynchronously executing 
	 * external program.
	 */
	private class CompletionMonitor extends Thread {
		private StreamReader 		stdout;
		private StreamReader			stderr;
		
		public CompletionMonitor (StreamReader out, StreamReader err) {	
			stdout= out;
			stderr = err;
		} // end of ctr
		
		public void run () {
			try {
				_exitValue = _process.waitFor();
				_process.destroy();

				// wait for stream readers to complete their reads
				
				// TODO: possible race condition here; what if not terminated because _process 
				// never really got started and so no broken pipes to stop stream threads???
				
				stdout.join();
				if (_writeToFile == true) {
					try {
						_outFile.flush();
						_outFile.close();
					} catch (IOException e1) {
						// not much can be done here
					}
				}
				stderr.join();
			} catch (InterruptedException e) {
				// interrupts are fine; used during cancellation
				if (_writeToFile == true) {
					try {
						_outFile.flush();
						_outFile.close();
					} catch (IOException e1) {
						// not much can be done here
					}
				}
			}
			
			_done = true;

			if (_monitor != null) {
				synchronized (_monitor) {
					_monitor.notify();
				}
			}
		} // end of run

	} // end of CompletionMonitor inner class
	
	
   /**
    * Inner class for reading streams; needed because Java's exec will block if
    * the process is writing to streams and fills the buffer. So, we need to
    * use this class to keep pulling information off the streams so the call to
    * waitFor() will not block. Read everything off the supplied input stream
    * and stuff it in the provided StringBuffer.
    * 
    * @see http://www.oreillynet.com/pub/h/1092 (among others)
    */
	private class StreamReader extends Thread {
		private InputStream src = null;
		private Writer dest = null;


		public StreamReader(InputStream in, Writer out) {
			src = in;
			dest = out;
			start();
		} // end of ctr


		public void run () {
			int nextChar;
			try {
				while ((nextChar = src.read()) != -1) {
					dest.write(nextChar);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // end of run
	} // end of StreamReader class

} // end of ExecuteExternalProgram inner class
