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

package edu.cmu.sei.pacc.perf.ui.editors;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.ide.IDEActionFactory;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;

/**
 * Manages the installation/deinstallation of global actions for multi-page editors. Responsible for
 * the redirection of global actions to the active editor. Multi-page contributor replaces the
 * contributors for the individual editors in the multi-page editor.
 * 
 * @author Paulo Merson
 */
public class ModelEditorContributor extends MultiPageEditorActionBarContributor {

    private IEditorPart activeEditorPart;

//    private Action      sampleAction;

    /**
     * Creates a multi-page contributor.
     */
    public ModelEditorContributor() {
        super();
//        createActions();
    }

    /**
     * Returns the action registed with the given text editor.
     * 
     * @return IAction or null if editor is null.
     */
    protected IAction getAction(ITextEditor editor, String actionID) {
        return (editor == null ? null : editor.getAction(actionID));
    }

    /*
     * (non-JavaDoc) Method declared in AbstractMultiPageEditorActionBarContributor.
     */
    public void setActivePage(IEditorPart part) {
        if (activeEditorPart == part) {
            return;
        }

        activeEditorPart = part;

        IActionBars actionBars = getActionBars();
        if (actionBars != null) {

            ITextEditor editor = (part instanceof ITextEditor) ? (ITextEditor) part : null;

            actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(),
                                              getAction(editor, ITextEditorActionConstants.DELETE));
            actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(),
                                              getAction(editor, ITextEditorActionConstants.UNDO));
            actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(),
                                              getAction(editor, ITextEditorActionConstants.REDO));
            actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(),
                                              getAction(editor, ITextEditorActionConstants.CUT));
            actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(),
                                              getAction(editor, ITextEditorActionConstants.COPY));
            actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(),
                                              getAction(editor, ITextEditorActionConstants.PASTE));
            actionBars.setGlobalActionHandler(ActionFactory.SELECT_ALL.getId(),
                                              getAction(editor,
                                                        ITextEditorActionConstants.SELECT_ALL));
            actionBars.setGlobalActionHandler(ActionFactory.FIND.getId(),
                                              getAction(editor, ITextEditorActionConstants.FIND));
            actionBars.setGlobalActionHandler(IDEActionFactory.BOOKMARK.getId(),
                                              getAction(editor, IDEActionFactory.BOOKMARK.getId()));
            actionBars.updateActionBars();
        }
    }

    //    private void createActions() {
    //        sampleAction = new Action() {
    //
    //            public void run() {
    //                MessageDialog.openInformation(null, "ZZZLIXO Plug-in", "Sample Action Executed");
    //            }
    //        };
    //        sampleAction.setText("Sample Action");
    //        sampleAction.setToolTipText("Sample Action tool tip");
    //        sampleAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
    //                .getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
    //    }
    //
    //    public void contributeToMenu(IMenuManager manager) {
    //        IMenuManager menu = new MenuManager("Editor &Menu");
    //        manager.prependToGroup(IWorkbenchActionConstants.MB_ADDITIONS, menu);
    //        menu.add(sampleAction);
    //    }
    //
    //    public void contributeToToolBar(IToolBarManager manager) {
    //        manager.add(new Separator());
    //        manager.add(sampleAction);
    //    }

    public void contributeToStatusLine(IStatusLineManager mgr) {
        mgr
                .setMessage("Changes to the model are overwritten when analysis or interpretation is called");
    }
}
