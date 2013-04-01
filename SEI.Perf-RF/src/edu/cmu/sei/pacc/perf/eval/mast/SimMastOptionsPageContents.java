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

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.RowLayout;

public class SimMastOptionsPageContents extends Composite {

	private Group modeGroup = null;
	public Button verificationButton = null;
	public Button exhaustiveButton = null;
	private Composite composite = null;
	public CCombo execTimeCombo = null;
	private Label label = null;
	private Label label1 = null;
	public Text simLength = null;
	public Button useOffsetsCheckBox = null;
	private Composite composite1 = null;
	public Button useMastResultsViewer = null;
	public SimMastOptionsPageContents(Composite parent, int style) {
		super(parent, style);
		initialize();
	}

	private void initialize() {
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.numColumns = 1;
		gridLayout2.verticalSpacing = 7;
		gridLayout2.marginWidth = 7;
		gridLayout2.marginHeight = 7;
		gridLayout2.horizontalSpacing = 7;
		createModeGroup();
		this.setLayout(gridLayout2);
		createComposite();
		this.setSize(new Point(216, 188));
	}

	/**
	 * This method initializes modeGroup	
	 *
	 */
	private void createModeGroup() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		modeGroup = new Group(this, SWT.NONE);
		modeGroup.setText("Simulation Mode");
		modeGroup.setLayout(gridLayout);
		verificationButton = new Button(modeGroup, SWT.RADIO);
		verificationButton.setText("Verification");
		verificationButton.setToolTipText("Run the simulation and stop at the first missed deadline");
		exhaustiveButton = new Button(modeGroup, SWT.RADIO);
		exhaustiveButton.setText("Exhaustive");
		exhaustiveButton.setToolTipText("Run the simulation and keep running even if deadlines are missed (SLOW)");
	}

	/**
	 * This method initializes composite	
	 *
	 */
	private void createComposite() {
		GridLayout gridLayout1 = new GridLayout();
		gridLayout1.numColumns = 1;
		gridLayout1.verticalSpacing = 7;
		gridLayout1.marginWidth = 7;
		gridLayout1.marginHeight = 7;
		gridLayout1.horizontalSpacing = 7;
		composite = new Composite(this, SWT.NONE);
		composite.setLayout(gridLayout1);
		createComposite1();
		useOffsetsCheckBox = new Button(composite, SWT.CHECK);
		useOffsetsCheckBox.setText("Use Offsets");
		useMastResultsViewer = new Button(composite, SWT.CHECK);
		useMastResultsViewer.setText("Show Results Using MAST");
		execTimeCombo.add("Split Uniform Min-Avg-Max");
		execTimeCombo.add("Constant Minimum");
		execTimeCombo.add("Constant Average");
		execTimeCombo.add("Constant Maximum");
	}

	/**
	 * This method initializes composite1	
	 *
	 */
	private void createComposite1() {
		GridLayout gridLayout3 = new GridLayout();
		gridLayout3.numColumns = 2;
		gridLayout3.verticalSpacing = 7;
		gridLayout3.marginWidth = 3;
		gridLayout3.marginHeight = 7;
		gridLayout3.horizontalSpacing = 7;
		composite1 = new Composite(composite, SWT.NONE);
		composite1.setLayout(gridLayout3);
		label = new Label(composite1, SWT.NONE);
		label.setText("Execution Times");
		execTimeCombo = new CCombo(composite1, SWT.BORDER);
		execTimeCombo.setEditable(false);
		label1 = new Label(composite1, SWT.NONE);
		label1.setText("Simulation Length");
		simLength = new Text(composite1, SWT.BORDER);
		
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
