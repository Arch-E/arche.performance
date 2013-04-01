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

package edu.cmu.sei.pacc.perf.actions;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BuildAction;

import edu.cmu.sei.pacc.perf.DesignInputExtension;
import edu.cmu.sei.pacc.perf.ITransformToIcm;
import edu.cmu.sei.pacc.perf.PerformanceRFPlugin;
import edu.cmu.sei.pacc.perf.ui.Messages;
import edu.cmu.sei.pacc.perf.ui.wizards.BatchPerfAnalysisWizard;
import edu.cmu.sei.pacc.perf.ui.wizards.PerfAnalysisWizard;

/**
 * Action to do performance analysis on a batch of designs
 * @author gmoreno
 */
public class AnalysisAction implements IObjectActionDelegate {

    private IWorkbenchPart       targetPart;

    private IStructuredSelection selection;

    /**
     * Calls performance interpretation and analysis. It launches a wizard that will eventually call
     * the evaluation procedure (external tool).
     * 
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action) {

        if (selection == null || selection.isEmpty()) {
            return;
        }
        
        boolean saveNoConfirm = BuildAction.isSaveAllSet();
        boolean savedOk = PerformanceRFPlugin.getDefault().getWorkbench()
                .saveAllEditors(!saveNoConfirm);
        if (!savedOk) {
            return;
        }

        /* check that all the files have the same extension */
        String fileExtension = null;
        for (Iterator it = selection.iterator(); it.hasNext();) {
        	IFile file = (IFile) it.next();
        	
        	if (fileExtension == null) {
        		fileExtension = file.getFileExtension();
        	} else if (!file.getFileExtension().equals(fileExtension)) {
        		MessageDialog.openError(null, "Performance Analysis",
        				"All the selected files must be of the same kind in order to perform batch analysis");
        		return;
        	}
        }
        
        /* default to "no translation needed" if file is already ICM */
        ITransformToIcm designToIcmTranslator = null;
        
        if (!fileExtension.equals("icm")) {
        
	        // first check that there's a designToIcm translator for the selected kind of file
	        List<DesignInputExtension> availableDesignInputs = PerformanceRFPlugin.getDefault()
	                .getAvailableDesignInputs();
	        for (Iterator<DesignInputExtension> it = availableDesignInputs.iterator(); it.hasNext();) {
	            DesignInputExtension designInput = it.next();
	            if (designInput.getFileExtension().equals(fileExtension)) {
	                designToIcmTranslator = designInput.getTransformToIcmObject();
	                break;
	            }
	        }
	        if (designToIcmTranslator == null) {
	            String msg = Messages.getString("noDesignToIcmObject");//$NON-NLS-1$
	            MessageDialog.openError(null, "Performance Analysis", msg);
	            return;
	        }
        }

        IWorkbenchWindow wbw = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

        // nothing should be written to the console until after the wizard is created.
        // this is because creating the wizard will create the translation to ICM, which
        // in the case of CCL, will potentially clear the console if the appropriate
        // user preference is set.
        Wizard wizard = (selection.size() > 1) ?
        	new BatchPerfAnalysisWizard(selection, designToIcmTranslator) :
        		new PerfAnalysisWizard((IFile) selection.getFirstElement(),
        				designToIcmTranslator);
        WizardDialog dialog = new WizardDialog(wbw.getShell(), wizard);
        dialog.open();
    }

    /**
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction,
     *      org.eclipse.ui.IWorkbenchPart)
     */
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        this.targetPart = targetPart;

    }

    /**
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     *      org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            this.selection = (IStructuredSelection) selection;
        }

    }

    
    /**
     * @return Returns the targetPart.
     */
    public IWorkbenchPart getTargetPart() {
        return targetPart;
    }

}
