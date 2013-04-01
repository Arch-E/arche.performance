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

package edu.cmu.sei.pacc.perf.eval;

import java.util.Iterator;
import java.util.List;

import edu.cmu.sei.pacc.perf.model.PeriodicTask;
import edu.cmu.sei.pacc.perf.model.Subtask;
import edu.cmu.sei.pacc.perf.model.Task;

/**
 * Helper methos to compute parameters needed for Lambda-ABA evaluations
 * @author gmoreno
 */
public class LambdaABAUtils {

	/**
	 * helper method for to find hyper period
	 * 
	 * @param tasks
	 * @return hyperperiod
	 */
	static public double computeHyperperiod(List tasks) {
		int lcm = 1;
		for (Iterator it = tasks.iterator(); it.hasNext();) {
			Task task = (Task) it.next();
			if (task instanceof PeriodicTask) {
				PeriodicTask periodicTask = (PeriodicTask) task;
				double period = periodicTask.getPeriod();
				if (period > 0) { // ignore tasks with no period specified
		
					// take into account downsampling in the hyperperiod
					for (Iterator it2 = periodicTask.getSubtasks()
							.iterator(); it2.hasNext();) {
						Subtask st = (Subtask) it2.next();
						if (st.getDownsamplingFactor() > 0) {
							period *= st.getDownsamplingFactor();
						}
					}
		
					// TODO LCM of doubles?
					lcm = LCM(lcm, (int) period);
				}
			}
		}
		return lcm;
	}

	/**
	 * helper method to compute the maximum offset
	 * 
	 * @param tasks
	 * @return maximum offset 
	 * TODO avoid truncation (related to computeHyperperiod LCM)
	 */
	static public int maxOffset(List tasks) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
	
		for (Iterator it = tasks.iterator(); it.hasNext();) {
			Task task = (Task) it.next();
			if (task instanceof PeriodicTask) {
				PeriodicTask periodicTask = (PeriodicTask) task;
				if (periodicTask.getPeriod() < min) {
					min = (int) periodicTask.getPeriod();
				}
				if (periodicTask.getPeriod() > max) {
					max = (int) periodicTask.getPeriod();
				}
			}
		}
	
		return max - min;
	}

	/**
	 * helper method to compute the minimum period
	 * 
	 * @param tasks
	 * @return minimum period 
	 */
	static public int minPeriod(List tasks) {
		int min = Integer.MAX_VALUE;
	
		for (Iterator it = tasks.iterator(); it.hasNext();) {
			Task task = (Task) it.next();
			if (task instanceof PeriodicTask) {
				PeriodicTask periodicTask = (PeriodicTask) task;
				if (periodicTask.getPeriod() < min) {
					min = (int) periodicTask.getPeriod();
				}
			}
		}
	
		return min;
	}

	/**
	 * @return gcd(|m|, |n|)
	 */
	static private int GCD(int m, int n) {
		if (m < 0) {
			m = -m;
		}
		if (n < 0) {
			n = -n;
		}
		if (0 == n) {
			return m;
		} else {
			return GCD(n, m % n);
		}
	}

	/**
	 * @return lcm(|m|, |n|)
	 */
	static private int LCM(int m, int n) {
		if (m < 0) {
			m = -m;
		}
		if (n < 0) {
			n = -n;
		}
		return m * (n / GCD(m, n)); // parentheses important to avoid overflow
	}

}
