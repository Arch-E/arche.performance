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

package edu.cmu.sei.pacc.perf;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Vector;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import edu.cmu.sei.pacc.perf.eval.EvaluationProcedure;
import edu.cmu.sei.pacc.perf.eval.extend.ExtendEvaluation;
import edu.cmu.sei.pacc.perf.eval.extend.ExtendMetaBridge;
import edu.cmu.sei.pacc.perf.eval.mast.MastEvaluation;
import edu.cmu.sei.pacc.perf.eval.mast.SimMastEvaluation;
import edu.cmu.sei.pacc.perf.eval.matlab.MatlabEvaluation;
import edu.cmu.sei.pacc.perf.eval.qsim.QsimEvaluation;

/**
 * The main plugin class to be used in the desktop.
 */
public class PerformanceRFPlugin extends AbstractUIPlugin {

    //The shared instance.
    private static PerformanceRFPlugin plugin;

    //Resource bundle.
    private ResourceBundle             resourceBundle;

    private MessageConsole             console;

    private List<DesignInputExtension> availableDesignInputs;
    
    /** list of evaluation procedures registered with the plugin */
    private List<EvaluationProcedure> evaluationProcedures;
    
    /** whether Extend is installed or not, or null if we haven't checked yet */
    private Boolean hasExtend = null;
    
    /** Maintain a single instance of the file infos throughout the session */
    private static Vector evaluationFileInfos = new Vector(); 
    
    /**
     * The constructor.
     */
    public PerformanceRFPlugin() {
        super();
        plugin = this;
        try {
            resourceBundle = ResourceBundle
                    .getBundle("edu.cmu.sei.pacc.perf.PerformanceRFPluginResources");
        } catch (MissingResourceException x) {
            resourceBundle = null;
        }
        // Default resource factories are not registered in the standalone EMF environment.
        // Therefore, for each file extension or scheme your application wants to load or save,
        // you need to register the corresponding resource factory. To load and save XMI
        // documents, the line below is necessary.
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
                .put("library", new XMIResourceFactoryImpl());
        
        try {
            loadDesignInputs();
        } catch (Exception e) {
            throw new RuntimeException("Could not load plug-ins that contain design inputs to the performance RF.", e);
        }
        
        /* Load the list of available evaluation procedures.
         * For now, this procedures are part of this same plugin, but they could be moved to
         * separate plugins.
         */
        evaluationProcedures = new ArrayList<EvaluationProcedure>();
        evaluationProcedures.add(new ExtendEvaluation());
        evaluationProcedures.add(new MatlabEvaluation());
        evaluationProcedures.add(new MastEvaluation());
        evaluationProcedures.add(new SimMastEvaluation());
        evaluationProcedures.add(new QsimEvaluation());
    }

    /**
     * Look up in the Eclipse platform for plug-ins that extend the "designInput" extension-point.
     * Store their data in the availableDesignInputs list.   
     * @return
     */
    private void loadDesignInputs() {
        availableDesignInputs = new ArrayList<DesignInputExtension>(1);
        
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IExtensionPoint point = registry.getExtensionPoint("SEI.Perf_RF.designInput");
        if (point == null) {
            // extensionPoint should not be null, but we test to avoid a NPE.
            return;
        }
        IExtension[] extensions = point.getExtensions();
        for (int i = 0; i < extensions.length; i++) {
            
            IConfigurationElement[] elements = extensions[i].getConfigurationElements();
            DesignInputExtension designExtension = new DesignInputExtension();
            
            for (int j = 0; j < elements.length; j++) {
                
                if (elements[j].getName().equals("designLanguage")) {
                    
                    designExtension.setName(elements[j].getAttribute("name"));
                    ITransformToIcm transformToIcm;
                    try {
                        transformToIcm = (ITransformToIcm) elements[j].createExecutableExtension("class");
                    } catch (Exception e) {
                        throw new RuntimeException("Could not load plug-in for the "  + designExtension.getName() +
                                                   " design input language.");
                    }
                    designExtension.setTransformToIcmObject(transformToIcm);
                    designExtension.setFileExtension(elements[j].getAttribute("fileExtension"));                    
                    availableDesignInputs.add(designExtension);
                }
            }
        }
    }


    /**
     * This method is called upon plug-in activation
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
    }

    /**
     * This method is called when the plug-in is stopped
     */
    public void stop(BundleContext context) throws Exception {
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     */
    public static PerformanceRFPlugin getDefault() {
        return plugin;
    }

    /**
     * Returns the string from the plugin's resource bundle, or 'key' if not found.
     */
    public static String getResourceString(String key) {
        ResourceBundle bundle = PerformanceRFPlugin.getDefault().getResourceBundle();
        try {
            return (bundle != null) ? bundle.getString(key) : key;
        } catch (MissingResourceException e) {
            return key;
        }
    }

    /**
     * Returns the plugin's resource bundle,
     */
    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public MessageConsole getConsole() {
        if (console == null) {
            console = initConsole("Performance RF");
        }
        return console;
    }

    /**
     * This is from http://eclipsefaq.org, Chapter 18, FAQ 307. How to write to the console from a
     * plugin.
     * 
     * @param name
     * @return
     */
    private MessageConsole initConsole(String name) {
        ConsolePlugin plugin = ConsolePlugin.getDefault();
        IConsoleManager conMan = plugin.getConsoleManager();
        IConsole[] existing = conMan.getConsoles();
        for (int i = 0; i < existing.length; i++) {
            if (name.equals(existing[i].getName())) {
                return (MessageConsole) existing[i];
            }
        }
        // no console found, so create a new one
        MessageConsole myConsole = new MessageConsole(name, null);
        conMan.addConsoles(new IConsole[] { myConsole});
        return myConsole;
    }

    
    /**
     * @return Returns the availableDesignInputs.
     */
    public List<DesignInputExtension> getAvailableDesignInputs() {
        return availableDesignInputs;
    }
    
    /**
     * Returns the name of the plugin
     * 
     * @return plugin name
     */
    public String getName() {
        return getBundle().getSymbolicName();
    }
    
    public boolean isExtendInstalled() {
        if (hasExtend == null) {
            hasExtend = new Boolean(false);
            try {
                URL extendDllUrl = Platform.getBundle(getName()).getEntry("/tools/ExtendBridge.dll");
                if (extendDllUrl != null) {
                    extendDllUrl = FileLocator.resolve(extendDllUrl);
                    String extendDllPath = (new File(extendDllUrl.getFile())).getAbsolutePath();
                    System.load(extendDllPath);
                    boolean extendRegistered = ExtendMetaBridge.isExtendRegistered();
                    hasExtend = new Boolean(extendRegistered);
                }
            } catch (Exception e) {} // assume Extend not installed
        }
        
        return hasExtend.booleanValue();
    }

    
    /**
     * @return Returns the evaluationProcedures.
     */
    public List<EvaluationProcedure> getEvaluationProcedures() {
        return evaluationProcedures;
    }
    
    /**
     * Create output directory for intermediate files generated by the plugin.
     * This directory is named performance and is in the same directory as the
     * design file being analyzed.
     *  
     * @param designFilePath path of the design file.
     * @return path of the output directory
     */
    public static IPath createOutputFolder(String designFilePath) {
        IPath designPath = new Path(designFilePath);
        IPath outputPath = designPath.removeLastSegments(1).append("performance");
        outputPath.toFile().mkdirs();
    	return outputPath;
    }

	public static Vector getEvaluationFileInfos() {
		return evaluationFileInfos;
	}

	public static void setEvaluationFileInfos(Vector evaluationFileInfos) {
		PerformanceRFPlugin.evaluationFileInfos = evaluationFileInfos;
	}
}