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

package edu.cmu.sei.pacc.perf.eval.viewers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;

import edu.cmu.sei.pacc.perf.PerformanceRFPlugin;
import edu.cmu.sei.pacc.perf.eval.model.ExtendEvaluationInfo;
import edu.cmu.sei.pacc.perf.eval.model.ExtendTaskInfo;
import edu.cmu.sei.pacc.perf.eval.model.FileInfo;
import edu.cmu.sei.pacc.perf.eval.model.MatlabEvaluationInfo;

/**
 * The viewer for displaying evaluation results
 * 
 * @author tsinha
 *
 */
public class EvaluationResultViewer extends ViewPart {
	private static TreeViewer viewer;

	private DrillDownAdapter drillDownAdapter;

	private Action action1;

	private Vector fileInfo = null;

	class TreeObject implements IAdaptable {
		private String name;

		private TreeParent parent;

		public TreeObject(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setParent(TreeParent parent) {
			this.parent = parent;
		}

		public TreeParent getParent() {
			return parent;
		}

		public String toString() {
			return getName();
		}

		public Object getAdapter(Class key) {
			return null;
		}
	}

	class TreeParent extends TreeObject {
		private ArrayList children;

		public TreeParent(String name) {
			super(name);
			children = new ArrayList();
		}

		public void addChild(TreeObject child) {
			children.add(child);
			child.setParent(this);
		}

		public void removeChild(TreeObject child) {
			children.remove(child);
			child.setParent(null);
		}

		public TreeObject[] getChildren() {
			return (TreeObject[]) children.toArray(new TreeObject[children
					.size()]);
		}

		public boolean hasChildren() {
			return children.size() > 0;
		}
	}

	class ViewContentProvider implements IStructuredContentProvider,
			ITreeContentProvider {
		private TreeParent invisibleRoot;

		public void inputChanged(Viewer v, Object oldInput, Object newInput) {

			if (oldInput != newInput) {
				viewer.refresh();
			}
		}

		public void dispose() {
		}

		public Object[] getElements(Object parent) {
			if (parent.equals(getViewSite())) {
				createHierarchy();
				return getChildren(invisibleRoot);
			}
			return getChildren(parent);
		}

		public Object getParent(Object child) {
			if (child instanceof TreeObject) {
				return ((TreeObject) child).getParent();
			}
			return null;
		}

		public Object[] getChildren(Object parent) {
			if (parent instanceof TreeParent) {
				return ((TreeParent) parent).getChildren();
			}
			return new Object[0];
		}

		public boolean hasChildren(Object parent) {
			if (parent instanceof TreeParent)
				return ((TreeParent) parent).hasChildren();
			return false;
		}

		private void createHierarchy() {

			TreeParent root = null;
			TreeParent p1 = null;
			TreeParent p2 = null;

			invisibleRoot = new TreeParent("");

			fileInfo = PerformanceRFPlugin.getEvaluationFileInfos();

			for (int i = 0; i < fileInfo.size(); i++) {

				FileInfo standIn = (FileInfo) fileInfo.get(i);

				root = new TreeParent(standIn.getFileName() + ".ccl");

				Vector evals = standIn.getEvaluations();

				for (int j = 0; j < evals.size(); j++) {

					if (evals.get(j) instanceof MatlabEvaluationInfo) {
						MatlabEvaluationInfo matlabInfo = (MatlabEvaluationInfo) evals
								.get(j);
						TreeObject matChild1 = new TreeObject(
								"Average Queuing Time E[Q] = "
										+ matlabInfo.getAvgQ());
						TreeObject matChild2 = new TreeObject(
								"Average Service Time E[Ss] = "
										+ matlabInfo.getAvgSer());
						TreeObject matChild3 = new TreeObject(
								"Average Latency E[W] = "
										+ matlabInfo.getAvgLat());

						TreeParent matPar = new TreeParent(
								"Sporadic server task");
						p1 = new TreeParent("Matlab");

						matPar.addChild(matChild1);
						matPar.addChild(matChild2);
						matPar.addChild(matChild3);

						p1.addChild(matPar);

						root.addChild(p1);

					}

					if (evals.get(j) instanceof ExtendEvaluationInfo) {

						p2 = new TreeParent("Extend");

						ExtendEvaluationInfo extEvalInfo = (ExtendEvaluationInfo) evals
								.get(j);

						Vector tasksInfo = extEvalInfo.getNumberOftasks();

						for (int k = 0; k < tasksInfo.size(); k++) {

							ExtendTaskInfo standInTask = (ExtendTaskInfo) tasksInfo
									.get(k);

							TreeParent taskObj = new TreeParent("Task : "
									+ (k + 1));

							TreeObject obsBest = new TreeObject("Best = "
									+ standInTask.getBest());
							TreeObject obsAvg = new TreeObject("Average = "
									+ standInTask.getAvg());
							TreeObject obsWorst = new TreeObject("Worst = "
									+ standInTask.getWorst());

							taskObj.addChild(obsBest);
							taskObj.addChild(obsAvg);
							taskObj.addChild(obsWorst);

							p2.addChild(taskObj);
						}

						root.addChild(p2);

					}

				}
				
				invisibleRoot.addChild(root);
			}
		}
	}

	class ViewLabelProvider extends LabelProvider {

		public String getText(Object obj) {
			return obj.toString();
		}

		public Image getImage(Object obj) {
			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
			if (obj instanceof TreeParent) {
				// return getFileImage().createImage();
				imageKey = ISharedImages.IMG_OBJ_FOLDER;
			}
			return PlatformUI.getWorkbench().getSharedImages().getImage(
					imageKey);

		}
	}

	// class NameSorter extends ViewerSorter {
	// }

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		// viewer.setSorter(new NameSorter());
		viewer.setInput(getViewSite());
		makeActions();
		hookContextMenu();
		// hookDoubleClickAction();
		contributeToActionBars();
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				EvaluationResultViewer.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(action1);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(action1);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(action1);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
	}

	private void makeActions() {
		action1 = new Action() {
			public void run() {
				viewer.refresh();
			}
		};
		action1.setText("Refresh");
		action1.setToolTipText("Refresh viewer");
		action1.setImageDescriptor(getRefreshImage());
	}

	// private void hookDoubleClickAction() {
	// viewer.addDoubleClickListener(new IDoubleClickListener() {
	// public void doubleClick(DoubleClickEvent event) {
	// doubleClickAction.run();
	// }
	// });
	// }

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	private ImageDescriptor getRefreshImage() {
		URL imgUrl = PerformanceRFPlugin.getDefault().getBundle().getEntry(
				"icons/refresh.gif");
		return ImageDescriptor.createFromURL(imgUrl);
	}

	public static void refreshViewer() {
		viewer.refresh();
	}
	
//	private ImageDescriptor getFileImage() {
//		URL imgUrl = PerformanceRFPlugin.getDefault().getBundle().getEntry(
//				"icons/CCLFile.gif");
//		return ImageDescriptor.createFromURL(imgUrl);
//	}
}