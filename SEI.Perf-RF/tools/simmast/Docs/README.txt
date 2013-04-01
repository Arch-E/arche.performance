-----------------------------------------------------------------------
--                              Sim_Mast                             --
--                        Simulator of MAST Model                    --
--                                                                   --
--                       Copyright (C) 2000-2002                     --
--                 Universidad de Cantabria, SPAIN                   --
--                                                                   --
--                                                                   --
--                    URL: http://mast.unican.es/                    --
--                                                                   --
-- Authors: Michael Gonzalez       mgh@unican.es                     --
--          Jose Javier Gutierrez  gutierjj@unican.es                --
--          Jose Carlos Palencia   palencij@unican.es                --
--          Jose Maria Drake       drakej@unican.es                  --
--                                                                   --
-- This program is free software; you can redistribute it and/or     --
-- modify it under the terms of the GNU General Public               --
-- License as published by the Free Software Foundation; either      --
-- version 2 of the License, or (at your option) any later version.  --
--                                                                   --
-- This program is distributed in the hope that it will be useful,   --
-- but WITHOUT ANY WARRANTY; without even the implied warranty of    --
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU --
-- General Public License for more details.                          --
--                                                                   --
-- You should have received a copy of the GNU General Public         --
-- License along with this program; if not, write to the             --
-- Free Software Foundation, Inc., 59 Temple Place - Suite 330,      --
-- Boston, MA 02111-1307, USA.                                       --
--                                                                   --
-----------------------------------------------------------------------

                            Version 1.1

TABLE OF CONTENTS
-----------------

   
   1. Binary Installation
   2. Source Installation   
   3. Usage of the Simulation Tool
   4. Support, Problems, and Questions


1. BINARY INSTALLATION
----------------------

    Windows: 
      - requires winzip (www.winzip.com) and acrobat reader 
        (www.adobe.com/acrobat/readstep.html) for the documentation.
      - unzip the file sim_mast-bin-win-1-0.zip into a directory of your
        choice.
      - This causes the following files and directories to appear:
            sim_mast_simulator.exe     : executable simulation tool
            sim_mast_launcher.exe      : graphic tool for invoking the simulator                  
            mast_results_viewer.exe    : graphical driver for viewing results
            xsdvalid.bat               : validator for xml files
            *.jar                      : validator files
            Mast_Model.xsd             : XML-Schema for Mast files
            Mast_Results.xsd           : XML-Schema for Results files
            Mast_Trace.xsd             : XML-Schema for Trace files
            docs                       : directory containing the Sim_MAST documents
            README.txt                 : this file
            sim_mast.ini               : ini file
            sim_mast-status.txt        : description of the current status           
      - put that directory in the PATH, so that you can access the tools; 
      - Open the sim_mast.ini file and write the path of the navigation tool
        you want to use to see the help file and in the second line write the 
        path for that file that is the directory where you have installed the 
        files followed by \docs\.
        
         
            ** Note: During testing, we detected incompatibilities
               between the gmast graphical interface and one version
               of the Panda antivirus tool. The executable analysis
               tool had no problem. We did not find any problems with
               other antivirus tools.

    Linux (tested on RedHat 7.1):
      - requires acrobat reader (www.adobe.com/acrobat/readstep.html) for 
        the documentation, and gtk (available in the RedHat distribution) 
        for the GUI.
      - unzip and extract the files in sim_mast-bin-linux-1-0.tar.gz into
        the directory of your choice:
            tar zxvf sim_mast-bin-linux-1-0.tar.gz
      - This causes the following files to appear:
            sim_mast_simulator    : executable simulation tool
            sim_mast_launcher     : graphic tool for invoking the simulator
            mast_Results_viewer   : graphical driver for viewing results
            xsdvalid              : validator for xml files
            *.jar                 : validator files
            Mast_Model.xsd        : XML-Schema for Mast files
            Mast_Results.xsd      : XML-Schema for Results files
            Mast_Trace.xsd        : XML-Schema for Trace files
            sim_mast.ini          : ini file
            docs                  : directory containing the Sim_MAST documents
            examples              : directory with examples
            README.txt            : this file
            mast-status.txt       : description of the current status
            validator             : script for using the validator
      - put that directory in the PATH, so that you can access the tools

2. SOURCE INSTALLATION
----------------------

      - Requires the gnat compiler (ftp.cs.nyu.edu/pub/gnat) and
        acrobat reader (www.adobe.com/acrobat/readstep.html) for the 
        documents. We have used version 3.14p of gnat.

      - Unzip and extract the source files from sim_mast-src-1-0.tar.gz 
        or from sim_mast-src-1-0.zip into the directory of your choice. 
        This creates the following directories and files:

            src                 : directory containing the source files
            sim_mast_launcher   : directory containing the graphical driver files
            mast_results_viewer : directory with the graphical results viewer files
            mast_src            : directory containing the MAST files used
            docs                : directory containing the MAST documents
            Mast_Model.xsd      : XML-Schema for Mast files
            Mast_Results.xsd    : XML-Schema for Results files
            Mast_Trace.xsd      : XML-Schema for Trace files
            README.txt          : this file
            sim_mast.ini        : ini file
            sim_mast-status.txt : description of the current status           
	
      - For Compiling the tools you need Xmlada (available from 
	http://libre.act-europe.fr/xmlada/). We have used version 0.7.1.

        The tools can be compiled invoking the following command in the 
        src directory:

             gnatmake -g -gnato sim_mast_simulator -IC:\sim_mast\mast_src 
              -Ic:\XMLada\sax -Ic:\XMLada\dom -Ic:\XMLada\unicode -Ic:\XMLada\input_sources
        
	assuming that you have extract the file in the c:\sim_mast directory
        and XMLada is installed in directory c:\XMLada. 
        Then, set the appropriate PATH, or move the executable program
        to a directory in the search PATH.

      - For compiling the graphical driver you need GtkAda (available
        from http://libre.act-europe.fr/GtkAda/). We have used version
        1.2.12 (on Linux) and 1.3.12 (on Windows).

        To compile in Linux, invoke the following command in 
        the gsim_mast/src directory:

             gnatmake sim_mast_launcher `gtkada-config`

        Then set the appropriate PATH, or create links to the executable
        files somewhere in your search PATH.

        To compile in Windows, use the command:

             gnatmake sim_mast_launcher -Ic:\Gtkada-1.3.12\lib

        assuming that GtkAda is installed in directory c:\Gtkada-1.3.12. 
        Then, set the appropriate PATH, or move the executable program
        to a directory in the search PATH.
        
        In this directory (gsim_mast/src) you have to modify the sim_mast.ini
        file with the apropiate path of the help page.

      - For compiling the graphical results viewer you also need
        GtkAda (available from http://libre.act-europe.fr/GtkAda/). We
        have used version 1.2.12 (on Linux) and 1.3.12 (on Windows).

        To compile in Linux, invoke the following command in 
        the gmast/src directory:

             gnatmake -I/usr/local/mast/src gmastresults `gtkada-config`
              -I/usr/local/XMLada/sax -I/usr/local/XMLada/dom 
              -I/usr/local/XMLada/unicode -I/usr/local/XMLada/input_sources

        assuming that the mast sources are in \usr\local\mast\src.
        Then set the appropriate PATH, or create links to the
        executable files somewhere in your search PATH.

        To compile in Windows, use the command:

             gnatmake gmastresults -Ic:\Gtkada-1.3.12\lib -Ic:\sim_mast\mast_src
             -Ic:\XMLada\sax -Ic:\XMLada\dom -Ic:\XMLada\unicode -Ic:\XMLada\input_sources

        assuming that the mast sources are in directory c:\mast\src
        and that GtkAda is installed in directory c:\Gtkada-1.3.12.
        Then, set the appropriate PATH, or move the executable program
        to a directory in the search PATH.

      - For using XML format in the input files, you need xsdvalid 
        (available from www.xmlmind.com/xsdvalid). We have used version
        23p1 (on Windows) and 25p1 (on Linux). We can install this tool
        unzipping and extracting the files xsdvalid23p-1.zip or xsdvalid25p-1.tar.gz.

      - If you wish to add or change anything in the file format, you
        need the aflex and ayacc tools (available from the MAST Web
        page) to re-generate the mast file parser or the mast results
        parser. You can install the tools by unzipping and extracting the
        files from:
            aflex-ayacc-gnat.tar.gz
        this will create the directories
            ayacc
            aflex
        these are modified versions of ayacc and aflex with the
        following changes:
            - adapted for gnat
            - can accept input files with international characters (8
              bit characters)
        you need to compile the tools:
            - in the ayacc/src directory: gnatmake ayacc
            - in the aflex/src directory: gnatmake aflex
        and put the corresponding directories in the PATH, or create links
        to the executable files somewhere in your search PATH, or move the
        executable files to a directory that is in the PATH.

        The mast file parser description is in file
        "mast_parser.y". The lexical analyzer description for the mast
        file is in file "mast_lex.l". To regenerate the parser and the
        tools use the "generate" script.

        The parser for the results file has its description in file
        "mast_results_parser.y", and its lexical analyzer description
        in "mast_results_lex.l". To regenerate the results parser
        use the "generate_results" script.


3. USAGE OF THE SIMULATION TOOL
------------------------------

   graphical driver
   ----------------

      sim_mast_launcher

         The graphical driver displays the information about the
         files and parameters needed by the tool. After setting all the
         desired fields, pressing the "Simulate" button will invoke 
         the sim_mast_simulator tool with the appropiate arguments.
         This arguments are stored in a file named "sim_mast_command" 
         that let invoke the tool without using the graphical launcher,
         directly from the command line:

            sim_mast_simulator 
         
   
         The "Help" button in the graphical driver provides additional
         help. 
         

   command format:   
   ---------------
      The format for the sim_mast_command file is:
      
      max_time_of_simulation profile_type logger_length [num_of_executions] input_file [output_file]

      where max_time_of_simulation is a float value,
            profile_type: EXHAUSTIVE, VERIFICATION, SCHEDULABILITY,
            logger_length: 512,2k,8k,32k,
            num_of_executions: 256,512,1024,2048,4096,8192,
            input_file: complete path with extension
            output_file: complete path without extension.

   tool description:

   -----------------

   sim_mast_simulator
   ------------------
      If the file has XML format, the xsdvalid.bat validates the file.
      If the file is not valid the aplication stops and the errors 
      can be found in the file "validator_report" or "err". After that, 
      the tool parses the input file.  
      If the file has the "Mast" format the tool parses the input file.
      If it finds errors it reports them and stops. The list of errors 
      can be found in the file "mast_parser.lis". 
      In both cases,if there are no errors, the real-time system 
      description is transformed to the simulation model and the 
      simulation is performed. When it ends:
      - The execution traces are stored in a file with xml format.
      - If a results file has been defined the results are stored in it
        and a file with a copy of the model is also created.
      - If no results file has been defined the results are put on
        the standard output.


 
   graphical results viewer
   ------------------------

      It is invoked directly from the graphical driver, pressing the
      "View Results" button.

      It can also be invoked directly from the command line:

         mast_results_viewer model_file results_file

      where model_file is the file containing the mast system
      description, and results_file is the file containing the results
      of the analysis tools on that model_file.


4. SUPPORT, PROBLEMS, AND QUESTIONS
-----------------------------------

   If you have any questions, comments, or need help in using SIM_MAST, please 
   send a message to:

         lopezpa@unican.es

   To download the most recent version of SIM_MAST please look in:

         http://mast.unican.es/sim_mast

   Thanks for your interest in MAST,


                   The MAST team.

