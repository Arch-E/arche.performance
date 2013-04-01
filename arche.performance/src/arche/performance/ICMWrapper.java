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

package arche.performance;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import arche.performance.hibernate.ArchECoreResponsibilityStructure;
import arche.performance.hibernate.ArchECoreView;
import arche.performance.hibernate.vo.ArchEResponsibilityReactionRelationVO;
import arche.performance.hibernate.vo.ArchEResponsibilityVO;
import arche.performance.hibernate.vo.ArchEScenarioVO;
import arche.performance.hibernate.vo.ArchETranslationRelationVO;
import arche.performance.hibernate.vo.ArchEVersionVO;
import edu.cmu.sei.arche.ArchEException;
import edu.cmu.sei.arche.external.data.ArchEArchitecture;
import edu.cmu.sei.arche.external.data.ArchERelation;
import edu.cmu.sei.arche.external.data.ArchEResponsibility;
import edu.cmu.sei.arche.external.data.ArchEResponsibilityStructure;
import edu.cmu.sei.arche.external.data.ArchEScenario;
import edu.cmu.sei.arche.external.data.ArchEView;
import edu.cmu.sei.pacc.perf.ReasoningFramework;
import edu.cmu.sei.pacc.perf.icm.AssemblyInstance;
import edu.cmu.sei.pacc.perf.icm.Constant;
import edu.cmu.sei.pacc.perf.icm.Distribution;
import edu.cmu.sei.pacc.perf.icm.ElementInstance;
import edu.cmu.sei.pacc.perf.icm.IcmComponent;
import edu.cmu.sei.pacc.perf.icm.IcmFactory;
import edu.cmu.sei.pacc.perf.icm.IcmPackage;
import edu.cmu.sei.pacc.perf.icm.IcmService;
import edu.cmu.sei.pacc.perf.icm.Scenario;
import edu.cmu.sei.pacc.perf.icm.ServiceSourcePinIcm;
import edu.cmu.sei.pacc.perf.icm.SinkPinInstance;
import edu.cmu.sei.pacc.perf.icm.SinkPinMode;
import edu.cmu.sei.pacc.perf.icm.SourcePinInstance;
import edu.cmu.sei.pacc.perf.icm.SourcePinMode;
import edu.cmu.sei.pacc.perf.icm.Uniform;

public class ICMWrapper extends ArchECoreView implements ArchEView { 
	
	private static final SourcePinMode DEFAULT_SOURCEPIN_MODE 	= SourcePinMode.SYNCH_LITERAL;
	private static final SinkPinMode DEFAULT_SINKPIN_MODE 		= SinkPinMode.MUTEX_LITERAL;	

	private static final int 	DEFAULT_PRIORITY 				= 1;
	private static final double DEFAULT_CONNECTION_OVERHEAD 	= 0.0;
	private static final double DEFAULT_EVENT_INTERARRIVAL_TIME = 10;
		
		private AssemblyInstance icmModel = null;
		
		// This method is no longer used, because it is replaced by the method 
		// below which already loads the AssemblyInstance by delegating in 
		// the facade ReasoningFramework
		private static AssemblyInstance loadICM(String filename) {
			
			// Paste here the invocation to ICM EMF model
		       ResourceSet resourceSet = new ResourceSetImpl();

		       // Register the appropriate resource factory to handle all file extensions.
		       resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put
		           (Resource.Factory.Registry.DEFAULT_EXTENSION,
		            new XMIResourceFactoryImpl());

		       // Register the package to ensure it is available during loading.
		       resourceSet.getPackageRegistry().put(IcmPackage.eNS_URI,IcmPackage.eINSTANCE);

		       URI fileURI = URI.createFileURI(new File(filename).getAbsolutePath());
		       Resource resource = resourceSet.getResource(fileURI, true);
		       AssemblyInstance assembly = (AssemblyInstance) resource.getContents().get(0);

		       return (assembly);
		}	

		protected static AssemblyInstance loadnewICM(String filename) {
			// There may be other ways of loading the file
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
	        IResource resource = root.findMember(new Path(filename));
	        IContainer container =(IContainer)resource;
	        IFile ifile = container.getFile(new Path(filename));
	        // This is the method provided by the ReasoningFramework API
			AssemblyInstance assembly = ReasoningFramework.loadConstructiveAssembly(ifile);			
			return (assembly);
		}		
		
	    private static String removeSpaces(String withSpaces) {
	        java.util.StringTokenizer t = new java.util.StringTokenizer(withSpaces, " ");
	        StringBuffer result = new StringBuffer("");
	        while (t.hasMoreTokens()) {
	            result.append(t.nextToken());
	            if (t.hasMoreTokens())
		            result.append("_");
	        }
	        return result.toString();
	    }
	    
	    private static Distribution getUniformDistribution(IcmFactory icmFactory,double min, double max) {
	    	Uniform uniform = icmFactory.createUniform();
	    	uniform.setMin(min);
	    	uniform.setMax(max);
	    	return (uniform);
	    }

	    private static Distribution getConstantDistribution(IcmFactory icmFactory,double c) {
	    	Constant constant = icmFactory.createConstant();
	    	constant.add(c);
	    	return (constant);
	    }
	    
	    private static int _PRIORITY = 0;
	    private static int getTaskPriority() {
	    	_PRIORITY++;
	    	return (_PRIORITY);
	    }

	    private static void resetTaskPriority() {
	    	_PRIORITY = 0;
	    }

		private static AssemblyInstance createICM(String name, List<ArchEScenario> scenarios, 
				List<ArchEResponsibility> responsibilities, List<ArchERelation> reactions,
				List<ArchERelation> translations) {
			
			//System.out.println("@@@@@CREATION OF ICM WITH REACTIONS: "+reactions.size());
			
			IcmFactory factory = IcmFactory.eINSTANCE;
			AssemblyInstance assembly = factory.createAssemblyInstance();
			assembly.setName(name);
			assembly.setConnectionOverhead(DEFAULT_CONNECTION_OVERHEAD);
			resetTaskPriority();
			
			String nameWithoutSpaces = null;
					
			// Rule 1: Create an ICM component for each responsibility
			IcmComponent comp = null;
			ArchEResponsibilityVO resp = null;
			Hashtable<String,IcmComponent> allComponents = new Hashtable<String,IcmComponent>();
			for (Iterator<ArchEResponsibility> itResponsibilities = responsibilities.iterator(); itResponsibilities.hasNext();) {
				resp = (ArchEResponsibilityVO)(itResponsibilities.next());
				comp = factory.createIcmComponent();
				nameWithoutSpaces =removeSpaces(resp.getName());
				comp.setName(nameWithoutSpaces);
				allComponents.put(nameWithoutSpaces,comp);
				assembly.getElements().add(comp);
			}
			
			// Rule 2: For each reaction relationship create corresponding source and sink pins
			// in the components associated to the responsibilities involved in the relationship		
			IcmComponent component = null;
			SinkPinInstance pinSinkInstance = null;
			SourcePinInstance pinSourceInstance = null;
			ArchEResponsibilityReactionRelationVO reaction = null;
			List<String> connectedComponents = new ArrayList<String>();
			double avgExecutionTime = 0;
			Distribution uniform = null;
			// For connecting the pins within a component
			for(Iterator<ArchERelation> it1 = reactions.iterator(); it1.hasNext();) {
				reaction = (ArchEResponsibilityReactionRelationVO)(it1.next());
				
				// This creates a sink pin for the child responsibility, 
				// assuming that parent --> child maps to source_parent --> sink_child
				pinSinkInstance = factory.createSinkPinInstance();
				pinSinkInstance.setMode(DEFAULT_SINKPIN_MODE);
				pinSinkInstance.setPriority(getTaskPriority());
				nameWithoutSpaces = removeSpaces(reaction.getChild().getName());
				pinSinkInstance.setName("psink_"+ nameWithoutSpaces);
				try {
					avgExecutionTime = reaction.getChild().getDoubleParameter(ICMPerformanceReasoningFramework.PARAMETER_EXECUTION_TIME);
					uniform = getUniformDistribution(factory,1.0,avgExecutionTime);
					pinSinkInstance.setExecTimeDistribution(uniform);
				} catch (ArchEException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				component =  allComponents.get(nameWithoutSpaces);			
				pinSinkInstance.setElementInstance(component);
				// This saves the names of those components that have at least one sink pin
				// connected, for later.
				if (!connectedComponents.contains(nameWithoutSpaces))
					connectedComponents.add(nameWithoutSpaces);

				// This creates a source pin for the child responsibility
				pinSourceInstance = factory.createSourcePinInstance();
				pinSourceInstance.setMode(DEFAULT_SOURCEPIN_MODE);
				
				nameWithoutSpaces = removeSpaces(reaction.getParent().getName());
				pinSourceInstance.setName("psource_"+nameWithoutSpaces);
				component =  allComponents.get(nameWithoutSpaces);			
				pinSourceInstance.setElementInstance(component);
				
				// This connects the source for the sink to react to
				pinSourceInstance.getSinks().add(pinSinkInstance);
				pinSinkInstance.getLinkSources().add(pinSourceInstance);				
			}
		
			// Rule 3: Create services for each of the scenarios, and then link them
			// to responsibilities (components) that have no incoming reactions (within the same scenario)
			ServiceSourcePinIcm serviceSourcePin = null;
			IcmService service = null;
			IcmComponent initialComponent = null;
			SinkPinInstance serviceSinkPin = null;
			ArchEScenarioVO scItem = null;
			List<IcmComponent> list = null;
			Scenario scenarioMode = null;
			int index = 1;
			Double interarrival = null;
			Distribution constant = null;
			
			for(Iterator<ArchEScenario> itScenarios = scenarios.iterator(); itScenarios.hasNext();) {
				scItem = (ArchEScenarioVO)(itScenarios.next());

				// Rule 4: Create Icm scenarios (modes) for the assembly
				scenarioMode = factory.createScenario();
				scenarioMode.setNumber(index);
				index++;					
				nameWithoutSpaces = scItem.getId();
				scenarioMode.setName(nameWithoutSpaces); // Check if the number depends on the services (Gabriel)!!
				assembly.getScenarios().add(scenarioMode);

//				list = getInitialComponentsFor(scItem,translations,markedComponents,allComponents);	
				list = getInitialComponentsFor1(scItem, translations, reactions,allComponents);
				if (list.size() > 0) { // The scenario has at least one responsibility
					interarrival = scItem.getStimulusValue();
					if (interarrival == null)
						interarrival = DEFAULT_EVENT_INTERARRIVAL_TIME;
					constant = getConstantDistribution(factory,interarrival);

					// Create a service for the scenario
					service = factory.createIcmService();							
					service.setPriority(getTaskPriority());
					nameWithoutSpaces = removeSpaces(scItem.getId());
					service.setName("Service_" + nameWithoutSpaces);
					assembly.getElements().add(service);
					
					// Create a source pin for the service
					serviceSourcePin = factory.createServiceSourcePinIcm();
					// The deadline comes from the response of the scenario
					serviceSourcePin.setDeadline(scItem.getMeasureValue());
					serviceSourcePin.setMode(DEFAULT_SOURCEPIN_MODE);
					avgExecutionTime = ICMPerformanceReasoningFramework.DEFAULT_EXECUTION_TIME;
					serviceSourcePin.setEventDistribution(constant);	
					uniform = getUniformDistribution(factory,1.0, avgExecutionTime);
					serviceSourcePin.setExecTimeDistribution(uniform);
					serviceSourcePin.setName("psource_service");
					serviceSourcePin.setElementInstance(service);	

					// Create a sink pin for every component that is "initial" for the scenario
					for (Iterator<IcmComponent> itComponents = list.iterator(); itComponents.hasNext();) {
						initialComponent = itComponents.next();

						// This saves the names of those components that have at least one sink pin
						// connected, for later.
						if (!connectedComponents.contains(initialComponent.getName()));
							connectedComponents.add(initialComponent.getName());

						serviceSinkPin = factory.createSinkPinInstance();
						serviceSinkPin.setMode(DEFAULT_SINKPIN_MODE);
						serviceSinkPin.setPriority(getTaskPriority());
						//avgExecutionTime = ICMPerformanceReasoningFramework.DEFAULT_EXECUTION_TIME;
						avgExecutionTime = retrieveExecutionTimeFor(initialComponent, responsibilities);
						uniform = getUniformDistribution(factory,1.0, avgExecutionTime);
						serviceSinkPin.setExecTimeDistribution(uniform);
						serviceSinkPin.setName("psink_" + service.getName());
						serviceSinkPin.setElementInstance(initialComponent);
						
						// This connects the source for the sink to react to
						serviceSourcePin.getSinks().add(serviceSinkPin);
						serviceSinkPin.getLinkSources().add(serviceSourcePin);
					}

					serviceSourcePin.getScenarios().add(scenarioMode);
				} // End if list.size() > 0				
			}			
			
			// Rule 5: Create a default scenario for all the remaining "unconnected" components
//			if (allComponents.size() > connectedComponents.size()) {
//				scenarioMode = factory.createScenario();
//				scenarioMode.setNumber(index);
//				index++;					
//				nameWithoutSpaces = "background";
//				scenarioMode.setName(nameWithoutSpaces); // Check if the number depends on the services (Gabriel)!!
//				assembly.getScenarios().add(scenarioMode);
//
//				interarrival = DEFAULT_EVENT_INTERARRIVAL_TIME;
//				constant = getConstantDistribution(factory,interarrival);
//
//				// Create a service for the default scenario
//				service = factory.createIcmService();							
//				service.setPriority(getTaskPriority());
//				service.setName("Service_background");
//				assembly.getElements().add(service);
//				
//				// Create a source pin for the service
//				serviceSourcePin = factory.createServiceSourcePinIcm();
//				// The deadline comes from the response of the scenario
//				serviceSourcePin.setDeadline(100.0); // Some default long value
//				serviceSourcePin.setMode(DEFAULT_SOURCEPIN_MODE);
//				avgExecutionTime = ICMPerformanceReasoningFramework.DEFAULT_EXECUTION_TIME;
//				serviceSourcePin.setEventDistribution(constant);	
//				uniform = getUniformDistribution(factory,1.0, avgExecutionTime);
//				serviceSourcePin.setExecTimeDistribution(uniform);
//				serviceSourcePin.setName("psource_service");
//				serviceSourcePin.setElementInstance(service);
//				
//				for(Enumeration<IcmComponent> itComponents = allComponents.elements(); itComponents.hasMoreElements();) {
//					initialComponent = itComponents.nextElement();
//					// This means the component is isolated
//					if (!connectedComponents.contains(initialComponent.getName())) {
//						serviceSinkPin = factory.createSinkPinInstance();
//						serviceSinkPin.setMode(DEFAULT_SINKPIN_MODE);
//						serviceSinkPin.setPriority(getTaskPriority());
//						//avgExecutionTime = ICMPerformanceReasoningFramework.DEFAULT_EXECUTION_TIME;
//						avgExecutionTime = retrieveExecutionTimeFor(initialComponent, responsibilities);
//						uniform = getUniformDistribution(factory,1.0, avgExecutionTime);
//						serviceSinkPin.setExecTimeDistribution(uniform);
//						serviceSinkPin.setName("psink_" + service.getName());
//						serviceSinkPin.setElementInstance(initialComponent);
//						
//						// This connects the source for the sink to react to
//						serviceSourcePin.getSinks().add(serviceSinkPin);
//						serviceSinkPin.getLinkSources().add(serviceSourcePin);					
//					}
//				}
//				
//			}

			for(Enumeration<IcmComponent> itComponents = allComponents.elements(); itComponents.hasMoreElements();) {
				initialComponent = itComponents.nextElement();
				initialComponent.setName("IcmC_" + initialComponent.getName());
			}

			// This prints the assembly just created, for testing purposes
//			System.out.println("ASSEMBLY ==> "+assembly.getName());
//			ElementInstance componenti = null;
//			PinInstance pini = null;
//			for (Iterator it3 = assembly.getElements().iterator(); it3.hasNext();) {
//				componenti = (ElementInstance)(it3.next());
//				System.out.println("    Component: "+ componenti.getName());
//				for (Iterator it4 = componenti.getPins().iterator(); it4.hasNext();) {
//					pini = (PinInstance)(it4.next());
//					System.out.println("        Pin: "+ pini.getName());					
//				}
//			}
			
			return (assembly);
		}
		
		private static double retrieveExecutionTimeFor(
				IcmComponent initialComponent, List<ArchEResponsibility> resps) {
			ArchEResponsibilityVO respItem = null;
			for (Iterator<ArchEResponsibility> itResponsibilities = resps.iterator(); itResponsibilities.hasNext();) {
				respItem = (ArchEResponsibilityVO)(itResponsibilities.next());
				if (removeSpaces(respItem.getName()).equals(initialComponent.getName()))
					try { // This is the executionTime associated to the original responsibility
						return(respItem.getDoubleParameter(ICMPerformanceReasoningFramework.PARAMETER_EXECUTION_TIME));
					} catch (ArchEException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return (0);
					}
			} // Otherwise, return the default value
			return (ICMPerformanceReasoningFramework.DEFAULT_EXECUTION_TIME);

		}

		private static List<IcmComponent> getInitialComponentsFor(ArchEScenarioVO scenario, 
				List<ArchERelation> translations, List<String> listOfComponents,
				Hashtable<String, IcmComponent> allComponents) {
			List<IcmComponent> result = new ArrayList<IcmComponent>();
			ArchETranslationRelationVO trItem = null;
			String componentKey = null;
			for (Iterator<ArchERelation> itRelations = translations.iterator(); itRelations.hasNext();) {
				trItem = (ArchETranslationRelationVO)(itRelations.next());
				ArchEScenarioVO parent = trItem.getParent();
				if ((parent != null) 
						&& parent.equals(scenario)) {
					componentKey = removeSpaces(trItem.getChild().getName());
					if (!listOfComponents.contains(componentKey))
						result.add(allComponents.get(componentKey));
				}
			}
			return (result);
		}
		
		private static List<ArchEResponsibilityVO> getInitialResponsibilities(ArchEScenarioVO scenario,
				List<ArchERelation> translations, List<ArchERelation> reactions ) {
			
			List<ArchEResponsibilityVO> result = new ArrayList<ArchEResponsibilityVO>();
			
			List<ArchEResponsibilityVO> mappedResponsibilities = new ArrayList<ArchEResponsibilityVO>();

			ArchETranslationRelationVO trItem = null;
			for (Iterator<ArchERelation> itRelations = translations.iterator(); itRelations.hasNext();) {
				trItem = (ArchETranslationRelationVO)(itRelations.next());
				ArchEScenarioVO parent = trItem.getParent();
				if ((parent != null) && parent.equals(scenario)) 
					mappedResponsibilities.add(trItem.getChild());
			}
			
			ArchEResponsibilityVO respItem = null;
			ArchEResponsibilityReactionRelationVO reactionItem = null;
			boolean isInitial = true;
			for (Iterator<ArchEResponsibilityVO> itResps = mappedResponsibilities.iterator(); itResps.hasNext();) {
				respItem = itResps.next();
				isInitial = true;
				for (Iterator<ArchERelation> itReactions = reactions.iterator(); itReactions.hasNext();) {
					reactionItem = (ArchEResponsibilityReactionRelationVO)(itReactions.next());
					if (reactionItem.getChild().equals(respItem) 
							&& mappedResponsibilities.contains(reactionItem.getParent()))
						isInitial = false;
				}
				if (isInitial)
					result.add(respItem);
			}
			
			return (result);			
		}

		private static List<IcmComponent> getInitialComponentsFor1(ArchEScenarioVO scenario, 
				List<ArchERelation> translations, List<ArchERelation> reactions,
				Hashtable<String, IcmComponent> allComponents) {

			List<IcmComponent> result = new ArrayList<IcmComponent>();
			String componentKey = null;

			List<ArchEResponsibilityVO> initialResponsibilities = getInitialResponsibilities(scenario, translations, reactions);
			ArchEResponsibilityVO respItem = null;
			for (Iterator<ArchEResponsibilityVO> itResps = initialResponsibilities.iterator(); itResps.hasNext();) {
				respItem = itResps.next();
				componentKey = removeSpaces(respItem.getName());
				result.add(allComponents.get(componentKey));
			}

			return (result);
		}

		public ICMWrapper(ArchEArchitecture architecture) {
			super(architecture);
			icmModel = null;
		}

		public void setInputInformation(String name, List<ArchEScenario> scenarios,
				ArchEResponsibilityStructure responsibilityStructure ) {

			List<ArchEResponsibility> responsibilities = responsibilityStructure.getResponsibilities();		
			ArchECoreResponsibilityStructure coreStructure = ((ArchECoreResponsibilityStructure)responsibilityStructure);		
			String reactionTypeVO = ArchEResponsibilityReactionRelationVO.class.getName();
			List <ArchERelation> reactions = coreStructure.getRelations(reactionTypeVO);
			String translationTypeVO = ArchETranslationRelationVO.class.getName();
			List <ArchERelation> translations = coreStructure.getRelations(translationTypeVO);
			
			icmModel = createICM(name,scenarios, responsibilities, reactions, translations);
			// We assume always the first scenario in the ICM file is the one to analyze
			icmModel.setSourceFile(this.saveICMAssembly(icmModel));
		}
		
		private String saveICMAssembly(AssemblyInstance assembly) {

	    	// configure this RF with the configuration file 
			try {     	   
				URL url = FileLocator.find(Platform.getBundle("SEI.ArchE.Performance"), new Path("/temp"), null);
				String tempPathName = FileLocator.resolve(url).getPath();

				ResourceSet resourceSet = new ResourceSetImpl();

				IWorkspaceRoot workspaceRoot =  ResourcesPlugin.getWorkspace().getRoot();	
				IPath path = new Path(tempPathName+assembly.getName()+".icm");
				
				IFile xmlFile = workspaceRoot.getFile(path);
				
				// Get the URI of the model file			
				URI fileURI = URI.createFileURI(path.toString());

				// Create a resource for this file.
				Resource resource = resourceSet.createResource(fileURI);

				// Add the assembly to the resource contents
				resource.getContents().add(assembly);
				
				// Save the contents of the resource to the file system.
				String xmlAssemblyFile = null;
				try {
				 resource.save(Collections.EMPTY_MAP);
				} catch (IOException e) {}
				
				xmlAssemblyFile = fileURI.toFileString();
				
				return (path.toPortableString());				
				
			} catch (MalformedURLException e1){
				e1.printStackTrace();
			} catch (IOException e1){
				e1.printStackTrace();
			}                             
			
			return null; // for error
		}
	
		public boolean clearICMAssembly() {
			File icmFile = new Path(icmModel.getSourceFile()).toFile();
			boolean ok = icmFile.delete();
			return (ok);
		}

		public AssemblyInstance getICMModel() {
			return (icmModel);
		}	

		public String getServicePinNameFor(ArchEScenario scenario) {
			
			ElementInstance elem = null;
			String nameWithoutSpaces = null;
			
			for(Iterator<ElementInstance> itComponents = icmModel.getElements().iterator(); itComponents.hasNext();) {
				elem = itComponents.next();
				
				if (elem instanceof IcmService) {
					nameWithoutSpaces = "Service_" + removeSpaces(((ArchEScenarioVO)scenario).getId());
					nameWithoutSpaces = nameWithoutSpaces+".psource_service";
					return (nameWithoutSpaces.toLowerCase());
				}
			}
			return (null);
		}

		// This method is necessary to know the evaluation procedure to apply on the ICM model
		public HashMap<Integer,Boolean> getScenarioMapFor(ArchEScenario scenario) {
			HashMap<Integer,Boolean> map = new HashMap<Integer,Boolean>();
			
			if (scenario == null) { // It means to activate all the scenario modes
				for(Iterator<Scenario> itScenarioModes = icmModel.getScenarios().iterator(); itScenarioModes.hasNext();) 
					map.put(itScenarioModes.next().getNumber(), new Boolean(true)); 
				return (map);
			}
			
			// Otherwise, a specific scenario gets activated
			Scenario scenarioMode = null;
			String archeScenarioName = ((ArchEScenarioVO)scenario).getId();
			for(Iterator<Scenario> itScenarioModes = icmModel.getScenarios().iterator(); itScenarioModes.hasNext();) {
				scenarioMode = itScenarioModes.next();	
				if (scenarioMode.getName().equals(archeScenarioName))// The scenario to be analyzed
					map.put(scenarioMode.getNumber(), new Boolean(true)); 
				else
					map.put(scenarioMode.getNumber(), new Boolean(false));
				
			}
			return (map);
		}
	
		
		//---- The remaining methods are unimplemented, since this view doesn't need to be saved/restored
		// from the database but rather it is always (re-)generated from the responsibility structure
		
		@Override
		public void delete() throws ArchEException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void restore() throws ArchEException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void save() throws ArchEException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void saveAs(ArchEVersionVO newVersion) throws ArchEException {
			// TODO Auto-generated method stub
			
		}		

}
