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

package edu.cmu.sei.pacc.perf.eval.mast;

import edu.cmu.sei.pacc.perf.model.*;

/**
 * Helper for creating MAST models
 * 
 * @author gmoreno
 */
public class MastUtils {
	
	
	/**
	 * Creates execution time parameters for MAST from a distribution
	 * 
	 * @param distribution
	 * @return String with the MAST equivalent of the distribution
	 */
	public static String formatExecTimeDistribution(Distribution distribution,
			MastTemplateArguments templateArgs) {
		Double worst = null;
		Double best = null;
		
		Double average = distribution.getComputedMean();
		
		if (distribution instanceof Uniform) {
			Uniform uniform = (Uniform) distribution;
			best = new Double(uniform.getMin());
			worst = new Double(uniform.getMax());
		} else if (distribution instanceof Constant) {
			Constant constant = (Constant) distribution;
			best = worst = new Double(constant.getValue());
		} else if (distribution instanceof Unknown) {
			Unknown unknown = (Unknown) distribution;
			best = new Double(unknown.getMin());
			worst = new Double(unknown.getMax());
		}

		if (templateArgs.getIgnoreExecTimeDistribution()) {
			switch (templateArgs.getIgnoreDistributionUsing()) {
			case USE_MIN:
				if (best != null) {
					average = best;
				}
				break;
			case USE_MAX:
				if (worst != null) {
					average = worst;
				}
				break;
			}
			best = worst = average;
		}
		
		StringBuffer rval = new StringBuffer();
		if (best != null) {
			rval.append("Best_Case_Execution_Time => ");
			rval.append(best);
			rval.append(",\n");
		}
		if (worst != null) {
			rval.append("Worst_Case_Execution_Time => ");
			rval.append(worst);
			rval.append(",\n");
		}
		rval.append("Avg_Case_Execution_Time => ");
		rval.append(average);

		return rval.toString();
	}
}
