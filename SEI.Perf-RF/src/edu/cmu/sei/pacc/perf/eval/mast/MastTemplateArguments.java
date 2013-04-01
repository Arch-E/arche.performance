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

import edu.cmu.sei.pacc.perf.model.Distribution;
import edu.cmu.sei.pacc.perf.model.PerformanceModel;

public class MastTemplateArguments {

	private PerformanceModel model;
	private boolean useOffsets = true;
	private boolean ignoreExecTimeDistribution = false;
	
	public enum DistributionParameter { USE_MIN, USE_AVG, USE_MAX };
	private DistributionParameter ignoreDistributionUsing = DistributionParameter.USE_AVG;
	
	/**
	 * Creates execution time parameters for MAST from a distribution
	 * 
	 * This method takes into account whether the distribution of
	 * exectimes should be ignored.
	 * 
	 * @param distribution
	 * @return String with the MAST equivalent of the distribution
	 */
	public String formatExecTimeDistribution(
			Distribution distribution) {
		return MastUtils.formatExecTimeDistribution(distribution,
				this); 
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(PerformanceModel model) {
		this.model = model;
	}

	/**
	 * @return the model
	 */
	public PerformanceModel getModel() {
		return model;
	}

	/**
	 * @param useOffsets the useOffsets to set
	 */
	public void setUseOffsets(boolean useOffsets) {
		this.useOffsets = useOffsets;
	}

	/**
	 * @return the useOffsets
	 */
	public boolean getUseOffsets() {
		return useOffsets;
	}

	/**
	 * @param ignoreExecTimeDistribution the ignoreExecTimeDistribution to set
	 */
	public void setIgnoreExecTimeDistribution(boolean ignoreExecTimeDistribution) {
		this.ignoreExecTimeDistribution = ignoreExecTimeDistribution;
	}

	/**
	 * @return the ignoreExecTimeDistribution
	 */
	public boolean getIgnoreExecTimeDistribution() {
		return ignoreExecTimeDistribution;
	}

	public void setIgnoreDistributionUsing(
			DistributionParameter ignoreDistributionUsing) {
		this.ignoreDistributionUsing = ignoreDistributionUsing;
	}

	public DistributionParameter getIgnoreDistributionUsing() {
		return ignoreDistributionUsing;
	}
	
}
