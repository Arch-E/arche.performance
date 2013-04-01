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

package edu.cmu.sei.pacc.perf.util;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Helper class with utility methods to add and clear maker.
 * 
 * @note There is redundancy in among this class and edu.cmu.sei.pacc.ui.MarkerUtilities.
 *      This methods are able to handle project-wide markers (maybe too much of a scope) but this
 *      allows clearing marker in multiple files (such as included files). It also uses the Error
 *      class instead of the Problem class, because it holds information about the file where the
 *      problem is. 
 *
 * @author Gabriel Moreno
 */
public class MarkerHelper {

    /**
     * Adds markers for all the Errors in the list.
     * 
     * @param errors list of Error
     * @param owner indicates the owner of the problem or error
     */
    public static void addMarkers(final List errors, final String owner) {
        
        IWorkspaceRunnable op = new IWorkspaceRunnable() {

            public void run(IProgressMonitor progressMonitor) {
                for (Iterator it = errors.iterator(); it.hasNext(); ) {
                    Error error = (Error) it.next();
                    try {
                        IMarker marker = error.getFile().createMarker(IMarker.PROBLEM);
                        String[] attribNames = new String[4];
                        Object[] attribValues = new Object[4];
                        attribNames[0] = "owner";
                        attribValues[0] = owner;
                        attribNames[1] = IMarker.LINE_NUMBER;
                        attribValues[1] = new Integer(error.getLine());
                        attribNames[2] = IMarker.SEVERITY;
                        attribValues[2] = new Integer(error.getSeverity());
                        attribNames[3] = IMarker.MESSAGE;
                        attribValues[3] = error.getMessage();
                        marker.setAttributes(attribNames, attribValues);
                    } catch (Exception e) {
                    	e.printStackTrace();
                    }
                }
            }
        };
        try {
            ResourcesPlugin.getWorkspace().run(op, null);
        } catch (Exception e) {}
    }

    /**
     * Clears all the markers belonging to the given owner in a project.
     * 
     * @param project 
     * @param owner
     * @throws CoreException
     */
    public static void clearMarkers(IProject project, String owner) throws CoreException {
        IMarker[] markers = 
            project.findMarkers(null, true, IResource.DEPTH_INFINITE);
        int[] deleteIndexes = new int[markers.length];
        int deleteCount = 0;
        for (int i = 0; i < markers.length; i++) {
            String ownerValue = markers[i].getAttribute("owner", "");
            if (ownerValue.equals(owner)) {
                deleteIndexes[deleteCount++] = i;
            }
        }
        if (deleteCount > 0) {
            IMarker[] toBeDeleted = new IMarker[deleteCount];
            for (int i = 0; i < deleteCount; i++) {
                toBeDeleted[i] = markers[deleteIndexes[i]];
            }
            project.getWorkspace().deleteMarkers(toBeDeleted);
        }
    }
    
}
