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

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Shows a dialog with a red label saying that the system is not schedulable
 * @author gmoreno
 *
 */
public class MastResultsNE extends Dialog {
	public class MastResultsContents extends Composite {

		private Text text = null;
		public MastResultsContents(Composite parent, int style) {
			super(parent, style);
			initialize();
		}

		private void initialize() {
			GridLayout gridLayout = new GridLayout();
			gridLayout.numColumns = 1;
			gridLayout.marginHeight = 20;
			gridLayout.marginWidth = 40;
			text = new Text(this, SWT.BORDER | SWT.READ_ONLY);
			text.setEditable(false);
			text.setBackground(new Color(Display.getCurrent(), 203, 62, 62));
			text.setText("The system is NOT schedulable");
			text.setFont(new Font(Display.getDefault(), "Arial", 11, SWT.NORMAL));
			text.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
			this.setLayout(gridLayout);
			this.setSize(new Point(332, 59));
		}

	}  //  @jve:decl-index=0:visual-constraint="10,10"

	public MastResultsNE(Shell shell) {
		super(shell);
	}
	
	@Override 
	protected Control createDialogArea(Composite parent) {
		return new MastResultsContents(parent, SWT.NULL);
	}	

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("GMAST Results");
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
	}
}
