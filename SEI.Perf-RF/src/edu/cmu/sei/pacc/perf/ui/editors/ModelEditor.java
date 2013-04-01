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

import java.io.ByteArrayInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.views.properties.PropertySheet;

import edu.cmu.sei.pacc.perf.PerformanceRFPlugin;
import edu.cmu.sei.pacc.perf.model.PerformanceModel;
import edu.cmu.sei.pacc.perf.model.Subtask;
import edu.cmu.sei.pacc.perf.model.Task;
import edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl;
import edu.cmu.sei.pacc.perf.ui.Messages;
import edu.cmu.sei.pacc.perf.ui.preferences.PerformanceRFPreferenceInitializer;

/**
 * Multi-page editor for persisted performance model files (xmi). These are the pages:
 * <ul>
 * <li>page 0 contains a simple text editor.
 * <li>page 1 shows the model in graphical format (read-only)
 * </ul>
 * 
 * @author Alexander Mordkovich
 * @author Paulo Merson
 * @author Tanmay Sinha
 */
public class ModelEditor extends MultiPageEditorPart implements IResourceChangeListener {

    /** The text editor used in page 1. */
    private TextEditor        editor;

    /** figure (task or subtask) that currently has focus */
    private Figure            focusedFigure         = null;

    /** Task figure */
    private TaskFigure        tf;

    /** Subtask figure */
    private SubtaskFigure     sf;

    /** object that is used by draw2d to draw SWT visual components. */
    private LightweightSystem lws;

    /** Get WIDTH from the preference pages */
    private static int        WIDTH                 = PerformanceRFPlugin
                                                            .getDefault()
                                                            .getPreferenceStore()
                                                            .getInt(
                                                                    PerformanceRFPreferenceInitializer.WIDTH_PREFERENCE);

    private final int         SUBTASK_LEFT_MARGIN   = 150;

    private final int         TASK_LEFT_MARGIN      = 10;

    private final int         TASK_VERTICAL_DIST    = 150;

    private final int         SUBTASK_VERTICAL_DIST = 100;

    /** The canvas for Draw2D ojects */
    FigureCanvas              canvas;

    /** Fullpath of file being edited */
    private String            fileName;

    /** reference to the standard Eclipse Properties View */
    private PropertySheet     propertiesView;

    /**
     * Constructor for the performance model multi-page editor.
     */
    public ModelEditor() {
        super();
        ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
    }

    /**
     * For loading the model (xmi) in the file (one time) or in the text editor to memory as
     * objects.
     */
    private ResourceSet resourceSet;

    /**
     * Creates the pages of the multi-page editor.
     */
    protected void createPages() {
        createPageGraphic();
        createPageText();
        pageChange(0);
    }

    /**
     * Create page 1 of the performance model multi-page editor, which contains a text editor.
     */
    void createPageText() {
        try {
            editor = new TextEditor();
            int index = addPage(editor, getEditorInput());
            setPageText(index, editor.getTitle());
            this.setTitleToolTip(fileName);
            this.setPartName(editor.getTitle());
        } catch (PartInitException e) {
            ErrorDialog.openError(getSite().getShell(), "Error creating nested text editor", null,
                                  e.getStatus());
        }
    }

    /**
     * Creates page 0 of the performance model multi-page editor, which shows (read-only) the model
     * graphically.
     */
    void createPageGraphic() {
        canvas = new FigureCanvas(getContainer(), SWT.H_SCROLL | SWT.V_SCROLL);
        canvas.setViewport(new Viewport(true));
        canvas.setScrollBarVisibility(FigureCanvas.AUTOMATIC);

        lws = new LightweightSystem(canvas);
        int index = addPage(canvas);
        setPageText(index, Messages.getString("titlePageGraph"));
    }

    /**
     * The <code>MultiPageEditorPart</code> implementation of this <code>IWorkbenchPart</code>
     * method disposes all nested editors. Subclasses may extend.
     */
    public void dispose() {
        ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
        super.dispose();
    }

    /**
     * Saves the multi-page editor's document.
     */
    public void doSave(IProgressMonitor monitor) {
        MessageDialog.openWarning(getSite().getShell(), "Performance Model Save",
                                  "Your changes will be overwritten if the model is regenerated \n"
                                          + "by calling performance analysis or interpretation.");
        getEditor(1).doSave(monitor);
    }

    /**
     * Saves the multi-page editor's document as another file. Also updates the text for page 1's
     * tab, and updates this multi-page editor's input to correspond to the nested editor's.
     */
    public void doSaveAs() {
        IEditorPart editor = getEditor(1);
        editor.doSaveAs();
        setPageText(1, editor.getTitle());
        setInput(editor.getEditorInput());
    }

    /*
     * (non-Javadoc) Method declared on IEditorPart
     */
    public void gotoMarker(IMarker marker) {
        setActivePage(1);
        IDE.gotoMarker(getEditor(1), marker);
    }

    /**
     * Checks that the input is an instance of <code>IFileEditorInput</code>. Then loads the
     * model from the xmi file to make sure it is a performance model. There's no need to load the
     * file contents to the text editor because that's done in createPageText().
     */
    public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException {
        if (editorInput instanceof IFileEditorInput) {
            super.init(site, editorInput);
            fileName = ((IFileEditorInput) getEditorInput()).getFile().getFullPath().toString();
            if (loadModel()) {
                // happy path
                return;
            }
        }
        // input is not a persisted performance model file
        throw new PartInitException("The input is not a valid performance model file");
    }

    /**
     * Method declared on IEditorPart.
     */
    public boolean isSaveAsAllowed() {
        return true;
    }

    /**
     * When the graphic page is activated, loads the reference to the properties view, which is used
     * when a task or subtask is selected, and then calls the method that will render the contents
     * of that page.
     */
    protected void pageChange(int newPageIndex) {
        super.pageChange(newPageIndex);
        if (newPageIndex == 0) {
            IWorkbenchPage activePage = getSite().getWorkbenchWindow().getActivePage();
            if (activePage != null) {
                propertiesView = (PropertySheet) activePage
                        .findView("org.eclipse.ui.views.PropertySheet");
            }

            // Listener for preference changes
            PerformanceRFPlugin.getDefault().getPreferenceStore()
                    .addPropertyChangeListener(new IPropertyChangeListener() {

                        public void propertyChange(PropertyChangeEvent event) {
                            if (event.getProperty().equals(PerformanceRFPreferenceInitializer.WIDTH_PREFERENCE)) {
                                Integer w = Integer.valueOf(event.getNewValue().toString());
                                WIDTH = w.intValue();
                                pageChange(0);
                            }
                            if (event.getProperty()
                                    .equals(PerformanceRFPreferenceInitializer.TASK_COLOR_PREFERENCE)) {
                                Color newTaskColor = new Color(null, PreferenceConverter
                                        .getColor(PerformanceRFPlugin.getDefault()
                                                .getPreferenceStore(),
                                                PerformanceRFPreferenceInitializer.TASK_COLOR_PREFERENCE));
                                tf.setColor(newTaskColor);
                                pageChange(0);
                            }
                            if (event.getProperty()
                                    .equals(PerformanceRFPreferenceInitializer.SUBTASK_COLOR_PREFERENCE)) {
                                Color newSubtaskColor = new Color(null, PreferenceConverter
                                        .getColor(PerformanceRFPlugin.getDefault()
                                                .getPreferenceStore(),
                                                PerformanceRFPreferenceInitializer.SUBTASK_COLOR_PREFERENCE));
                                sf.setColor(newSubtaskColor);
                                pageChange(0);
                            }
                        }
                    });
            
            displayModelGraphically();
        }
    }

    /**
     * Closes all project files on project close.
     */
    public void resourceChanged(final IResourceChangeEvent event) {
        if (event.getType() == IResourceChangeEvent.PRE_CLOSE) {
            Display.getDefault().asyncExec(new Runnable() {

                public void run() {
                    IWorkbenchPage[] pages = getSite().getWorkbenchWindow().getPages();
                    for (int i = 0; i < pages.length; i++) {
                        if (((FileEditorInput) editor.getEditorInput()).getFile().getProject()
                                .equals(event.getResource())) {
                            IEditorPart editorPart = pages[i].findEditor(editor.getEditorInput());
                            pages[i].closeEditor(editorPart, true);
                        }
                    }
                }
            });
        }
    }

    /**
     * Locates the file being edited and load its contents using the EMF persistence API.
     * 
     * @return true if could load the model and it's a valid performance model; false otherwise
     */
    private boolean loadModel() {
        try {
            resourceSet = new ResourceSetImpl();
            resourceSet.getPackageRegistry().put("http://edu.cmu.sei.pacc.perf.model",
                                                 ModelPackageImpl.init());

            URI fileURI = URI.createFileURI(fileName);
            Resource emfResource = resourceSet.createResource(fileURI);
            emfResource.load(null);
            List modelContents = emfResource.getContents();
            Object obj = modelContents.get(0);
            if (obj == null || !(obj instanceof PerformanceModel)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Iterates over the tasks and subtasks and creates the tasks and subtasks figures. Also creates
     * the connections between subtasks, wrapping around when it reaches a pre-set right margin.
     * 
     * @param contents
     */
    private void displayModelGraphically() {

        // Load contents (list of tasks and subtasks) from current xmi text in the text editor
        String editorText = editor.getDocumentProvider().getDocument(editor.getEditorInput()).get();
        ByteArrayInputStream textStream = new ByteArrayInputStream(editorText.getBytes());

        List modelContents = null;
        try {
            Resource emfResource = resourceSet.createResource(URI.createURI("dummy.model"));
            emfResource.load(textStream, null);

            modelContents = emfResource.getContents();

        } catch (Exception e) {
            MessageDialog.openError(getSite().getShell(), "Performance Model Error",
                                    "The text is not a valid performance model:\n\n"
                                            + e.getMessage());
            setActivePage(1);
        }

        // Create all draw2d figures

        Figure figure = new Figure();
        ScrollPane scp = new ScrollPane();

        XYLayout contentsLayout = new XYLayout();
        contentsLayout.setObserveVisibility(true);

        figure.setLayoutManager(contentsLayout);
        // Font classFont = new Font(null, "Arial", 12, SWT.BOLD);

        int verticalOffset = 10;
        int horizontalOffset = 10;

        // Iterate through model and display the tasks
        for (Iterator it = modelContents.iterator(); it.hasNext();) {
            PerformanceModel model = (PerformanceModel) it.next();

            // Get all the tasks for the model
            EList tasks = model.getTasks();
            for (Iterator taskIterator = tasks.iterator(); taskIterator.hasNext();) {
                Task t = (Task) taskIterator.next();

                // Create figure for this task
                tf = new TaskFigure(t);

                // add listener to set focus on click
                tf.addMouseListener(new MouseClickListener(tf, this));

                // Add this task figure to the main figure
                figure.add(tf);

                EList subtasks = t.getSubtasks();
                Vector conns = new Vector(subtasks.size());
                horizontalOffset += SUBTASK_LEFT_MARGIN;

                // Create the rectangle for the task
                contentsLayout.setConstraint(tf, new Rectangle(TASK_LEFT_MARGIN, verticalOffset,
                        -1, -1));

                // Iterate over subtasks
                for (Iterator subs = subtasks.iterator(); subs.hasNext();) {

                    Subtask subtask = (Subtask) subs.next();

                    // Create a new subtask figure
                    sf = new SubtaskFigure(subtask);

                    sf.addMouseListener(new MouseClickListener(sf, this));

                    int sfWidth = sf.getPreferredSize().width;
                    if (horizontalOffset + sfWidth >= WIDTH) {
                        horizontalOffset = SUBTASK_LEFT_MARGIN + TASK_LEFT_MARGIN;
                        verticalOffset += SUBTASK_VERTICAL_DIST;
                        tf.setHeight(tf.getHeight() + 100);
                    }
                    Rectangle subtaskArea = new Rectangle(horizontalOffset, verticalOffset + 10,
                            -1, -1);
                    sf.setBounds(subtaskArea);
                    contentsLayout.setConstraint(sf, subtaskArea);

                    // Create a new connection whose base is at this subtask
                    PolylineConnection newConn = new PolylineConnection();
                    ChopboxAnchor sourceAnchor = new ChopboxAnchor(sf);
                    newConn.setSourceAnchor(sourceAnchor);
                    newConn.setTargetDecoration(new PolygonDecoration());
                    newConn.setConnectionRouter(new BendpointConnectionRouter());
                    conns.add(newConn);

                    // Complete a connection from previous subtask
                    int nofConns = conns.size();
                    if (nofConns > 1) {
                        PolylineConnection previousConn = (PolylineConnection) conns
                                .elementAt(nofConns - 2);
                        ChopboxAnchor targetAnchor = new ChopboxAnchor(sf);
                        previousConn.setTargetAnchor(targetAnchor);
                        SubtaskFigure previousSf = (SubtaskFigure) previousConn.getSourceAnchor()
                                .getOwner();
                        Point start = previousSf.getLocation();
                        Point end = sf.getLocation();
                        if (end.y > start.y && end.x < start.x) {
                            // Has to wrap around. We did not use the ManhattanConnectionRouter
                            // because
                            // it makes the line go over the other subtasks. It's a final class that
                            // does not allow further configuration of behavior and simply doesn't
                            // work
                            // well. The bugzilla entries for ManhattanConnectionRouter say it's
                            // meant
                            // for examples only.
                            // Therefore, we're using the BendpointConnectionRouter and right below
                            // we calculate the bendpoints to provide the manhattan routing.

//                            List bps = new ArrayList(2);
                            int sfHeight = sf.getPreferredSize().height;
                            
                            //[Bug 487 Fix] 
                            //This is the solution to the scrolling problem
                            //We calculate two bendpoints just like before 
                            //but instead of using bendpoint connectors, now we're using
                            //three separate connectors and two invisible subtask figures (placed at bendpoints)
                            //that are used to make three separate connections and allow proper scrolling
                          
                            //The Connections
                            PolylineConnection conn1 = new PolylineConnection();
                            PolylineConnection conn2 = new PolylineConnection();
                            PolylineConnection conn3 = new PolylineConnection();

                            //The Bendpoints
                            int midXBp1 = start.x + previousSf.getPreferredSize().width / 2;
                            int midYBp1 = end.y - SUBTASK_VERTICAL_DIST / 2 + sfHeight / 2;
                            int midXBp2 = end.x + sfWidth / 2;
                            
                            //The Invisible Subtask Figure
                            SubtaskFigure subtaskBp1 = new SubtaskFigure();
                            figure.add(subtaskBp1);
                            subtaskBp1.setVisible(false);
                            contentsLayout.setConstraint(subtaskBp1, new Rectangle(midXBp1,
                                    midYBp1, 2, 2));                             

                            SubtaskFigure subtaskBp2 = new SubtaskFigure();
                            figure.add(subtaskBp2);
                            subtaskBp2.setVisible(false);
                            contentsLayout.setConstraint(subtaskBp2, new Rectangle(midXBp2,
                                    midYBp1, 2, 2)); 
                            
                            //Setting the source and taget anchor
                            ChopboxAnchor sa1 = new ChopboxAnchor(previousSf);
                            ChopboxAnchor ta1 = new ChopboxAnchor(subtaskBp1);
                            
                            ChopboxAnchor sa2 = new ChopboxAnchor(subtaskBp1);
                            ChopboxAnchor ta2 = new ChopboxAnchor(subtaskBp2);

                            ChopboxAnchor sa3 = new ChopboxAnchor(subtaskBp2);
                            ChopboxAnchor ta3 = new ChopboxAnchor(sf);
                            
                            conn1.setSourceAnchor(sa1);
                            conn1.setTargetAnchor(ta1);

                            conn2.setSourceAnchor(sa2);
                            conn2.setTargetAnchor(ta2);
                            
                            conn3.setSourceAnchor(sa3);
                            conn3.setTargetAnchor(ta3);
                            
                            conn3.setTargetDecoration(new PolygonDecoration());
                            
                            //Adding the connection to the figure
                            figure.add(conn1);
                            figure.add(conn2);
                            figure.add(conn3);
                            
//                            //The bendpoint mechanism
//                            Bendpoint bp1 = new AbsoluteBendpoint(midXBp1, midYBp1);
//                            bps.add(bp1);
//                            
//                            Bendpoint bp2 = new AbsoluteBendpoint(midXBp2, midYBp1);
//                            bps.add(bp2);
                            
//                            previousConn.getConnectionRouter().setConstraint(previousConn, bps);
                            
                            //The previous connection is turned invisible because we do not 
                            //need it when we require bendpoints
                            previousConn.setVisible(false);
                        }
                        figure.add(previousConn);
                    }

                    figure.add(sf);

                    horizontalOffset += sfWidth + 20; // gap between subtasks is 20

                    if (horizontalOffset > tf.getWidth()) {
                        tf.setWidth(horizontalOffset);
                    }

                } // Iterate over subtasks

                ((Rectangle) contentsLayout.getConstraint(tf)).height = tf.getHeight();
                ((Rectangle) contentsLayout.getConstraint(tf)).width = tf.getWidth();

                verticalOffset += TASK_VERTICAL_DIST;
                horizontalOffset = TASK_LEFT_MARGIN;
            }
        } // Iterate over Tasks

        // Now add legend to the diagram

        int yPos = verticalOffset + 40;

        Label keyLabel = new Label("Key: ");
        keyLabel.setFont(new Font(null, "", 9, SWT.BOLD | SWT.ITALIC));
        figure.add(keyLabel);
        contentsLayout.setConstraint(keyLabel, new Rectangle(TASK_LEFT_MARGIN, yPos, -1, -1));

        TaskFigure taskLegend = new TaskFigure();
        figure.add(taskLegend);
        contentsLayout
                .setConstraint(taskLegend, new Rectangle(TASK_LEFT_MARGIN + 40, yPos, 90, 35));
        SubtaskFigure subtaskLegend = new SubtaskFigure();
        figure.add(subtaskLegend);
        contentsLayout.setConstraint(subtaskLegend, new Rectangle(TASK_LEFT_MARGIN + 150, yPos, 80,
                35));

        Label connLabel = new Label("Subtask precedence");
        connLabel.setFont(new Font(null, "", 8, SWT.NORMAL));
        figure.add(connLabel);
        contentsLayout
                .setConstraint(connLabel, new Rectangle(TASK_LEFT_MARGIN + 250, yPos, -1, -1));

        
        //[Bug 487] 
        //See description earlier
        
        //The invisible subtask figures
        SubtaskFigure subtaskStart= new SubtaskFigure();
        subtaskStart.setVisible(false);
        figure.add(subtaskStart);
        contentsLayout.setConstraint(subtaskStart, new Rectangle(TASK_LEFT_MARGIN + 250, yPos + 20, 1,
                1)); 
        
        SubtaskFigure subtaskEnd= new SubtaskFigure();
        subtaskEnd.setVisible(false);
        figure.add(subtaskEnd);
        contentsLayout.setConstraint(subtaskEnd, new Rectangle(TASK_LEFT_MARGIN + 360, yPos + 20, 1,
                1)); 
        
        //The Connection
        PolylineConnection connLegend = new PolylineConnection();
        
        ChopboxAnchor sourceAnchor = new ChopboxAnchor(subtaskStart);
        ChopboxAnchor targetAnchor = new ChopboxAnchor(subtaskEnd);

        connLegend.setTargetDecoration(new PolygonDecoration());
        
        //Setting the source and taget anchor
        connLegend.setSourceAnchor(sourceAnchor);
        connLegend.setTargetAnchor(targetAnchor);
        
        figure.add(connLegend);

        scp.setContents(figure);
        scp.setScrollBarVisibility(ScrollPane.AUTOMATIC);
        lws.setContents(scp);
        // canvas.setContents(figure);
        // lws.setContents(canvas.getViewport());
        // shell.open();
    }

    // ------------------------------------------------------------------------------------------
    // NESTED CLASSES
    // ------------------------------------------------------------------------------------------

    public class MouseClickListener implements MouseListener {

        private Figure      f;

        private ModelEditor enclosingEditor;

        /**
         * @param figure
         */
        public MouseClickListener(Figure figure, ModelEditor modelEditor) {
            f = figure;
            enclosingEditor = modelEditor;
        }

        /**
         * @see org.eclipse.draw2d.MouseListener#mousePressed(org.eclipse.draw2d.MouseEvent)
         */
        public void mousePressed(MouseEvent arg0) {
        }

        /**
         * User just clicked and released on this figure f. Need to set the color of f but also
         * reset the color of the previously focused figure.
         * 
         * @see org.eclipse.draw2d.MouseListener#mouseReleased(org.eclipse.draw2d.MouseEvent)
         */
        public void mouseReleased(MouseEvent arg0) {
            Color selectedColor = new Color(null, 100, 150, 150);
            
            //For changing border during selection 
            //DOES NOT WORK properly becuase of rounded rectangles
            // LineBorder selectedBorder = new LineBorder(ColorConstants.black, 3);
            // LineBorder originalBorder = new LineBorder(ColorConstants.black, 1);

            if (enclosingEditor.focusedFigure != null) {
                if (focusedFigure instanceof TaskFigure) {
                    focusedFigure.setBackgroundColor(((TaskFigure) focusedFigure).getColor());
                } else if (focusedFigure instanceof SubtaskFigure) {
                    focusedFigure.setBackgroundColor(((SubtaskFigure) focusedFigure).getColor());
                }
            }
            f.setBackgroundColor(selectedColor);
            enclosingEditor.focusedFigure = f;

            // notify the properties view that a task or subtask was selected so that the
            // properties of the selected item can be shown
            if (f instanceof TaskFigure) {
                enclosingEditor.propertiesView.selectionChanged(enclosingEditor, (TaskFigure) f);
            } else if (f instanceof SubtaskFigure) {
                enclosingEditor.propertiesView.selectionChanged(enclosingEditor, (SubtaskFigure) f);
            }
        }

        /**
         * @see org.eclipse.draw2d.MouseListener#mouseDoubleClicked(org.eclipse.draw2d.MouseEvent)
         */
        public void mouseDoubleClicked(MouseEvent arg0) {
        }

    }

}
