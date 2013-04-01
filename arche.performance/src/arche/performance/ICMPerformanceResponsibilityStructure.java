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

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

//import arche.example.hibernate.vo.ArchEResponsibilityInteractionRelationVO;
import arche.performance.hibernate.ArchECoreArchitecture;
import arche.performance.hibernate.ArchECoreDataProvider;
import arche.performance.hibernate.ArchECoreResponsibilityStructure;
import arche.performance.hibernate.dao.ArchEParameterDAO;
import arche.performance.hibernate.vo.ArchERelationVO;
import arche.performance.hibernate.vo.ArchEResponsibilityDependencyRelationVO;
import arche.performance.hibernate.vo.ArchEResponsibilityReactionRelationVO;
import arche.performance.hibernate.vo.ArchEResponsibilityVO;
import arche.performance.hibernate.vo.ArchEVersionVO;


import edu.cmu.sei.arche.ArchEException;
import edu.cmu.sei.arche.external.data.ArchERelation;

public class ICMPerformanceResponsibilityStructure extends ArchECoreResponsibilityStructure {

	private static Hashtable<String,String> mappingsToTables = new Hashtable<String,String>();

	static { // These mappings should come from the rf-xml configuration file
		mappingsToTables.put(ICMPerformanceReasoningFramework.PARAMETER_EXECUTION_TIME, 
				"icmperformancerf_p_executiontime");
	}
	
	public static void setParameterToTableMapping(String parameterName, String tableName) {
		mappingsToTables.put(parameterName, tableName);
		return;
	}

	protected List<ArchEResponsibilityReactionRelationVO> reaVOs; // For reaction relations
	
	public ICMPerformanceResponsibilityStructure(ArchECoreArchitecture architecture) {
		super(architecture);
		this.reaVOs = null;
	}

	public void restore() throws ArchEException{
		super.restore();
		
		Session openSession = ArchECoreDataProvider.getSessionFactory().getCurrentSession();
		try{
    	
	    	// Restore the reaction relations between responsibilities
	    	Query qreas = openSession.createQuery("from ArchEResponsibilityReactionRelationVO as rel where rel.version = ?");
	    	qreas.setInteger(0, version.getId());
	    	reaVOs = qreas.list();	
			//System.out.println("RESTORE REACTIONS: "+reaVOs.size()+" version= "+version.getId());

	    	for (Iterator<ArchEResponsibilityReactionRelationVO> itRea = reaVOs.iterator(); itRea.hasNext();)
	    		this.restoreParametersForRelation(itRea.next(), openSession);

	    	
		}catch (HibernateException ex){
			throw new ArchEException(ex.getMessage(),ex.getCause());
		}
	}	

	public void save() throws ArchEException{
		super.save();
		
		Session openSession = ArchECoreDataProvider.getSessionFactory().getCurrentSession();
		try{
			
			ArchERelationVO rel = null;
			
			// Save or update the reaction relations
			for(Iterator<ArchEResponsibilityReactionRelationVO> it=reaVOs.iterator(); it.hasNext() ; ) {
				rel = it.next();
				openSession.saveOrUpdate(rel);			
				this.saveParametersForRelation(rel, openSession);				
			}

//			System.out.println("SAVE REACTIONS: "+reaVOs.size()+" version= "+version.getId());

		}catch (HibernateException ex){
			throw new ArchEException(ex.getMessage(),ex.getCause());
		}			
	}
	
	public void saveAs(ArchEVersionVO newVersion) throws ArchEException {
		super.saveAs(newVersion);
		
		Session openSession = ArchECoreDataProvider.getSessionFactory().getCurrentSession();
		try{

			ArchERelationVO rel = null;

			// Save the reaction relations under a new version
			for(Iterator<ArchEResponsibilityReactionRelationVO> it=reaVOs.iterator(); it.hasNext() ; ) {
				rel = it.next();
				rel.setVersion(newVersion);
				openSession.save(rel);			
				this.saveParametersForRelationAs(newVersion, rel, openSession);
			}
			//System.out.println("SAVE AS REACTIONS: "+reaVOs.size()+" version= "+newVersion.getId());
			
		}catch (HibernateException ex){
			throw new ArchEException(ex.getMessage(),ex.getCause());
		}			
	}

	public void delete() throws ArchEException{
		super.delete();
		
		Session openSession = ArchECoreDataProvider.getSessionFactory().getCurrentSession();
		try{

			ArchERelationVO rel = null;
			
			// Delete the reaction relations
			for (Iterator<ArchEResponsibilityReactionRelationVO> itRea = reaVOs.iterator(); itRea.hasNext();) {
				rel = itRea.next();
				openSession.delete(rel);
				this.deleteParametersForRelation(rel, openSession);
			}

		}catch (HibernateException ex){
			throw new ArchEException(ex.getMessage(),ex.getCause());
		}			
	}	
	
	public boolean addRelation(ArchERelation rel) {
		if(super.addRelation(rel))
			return true;
		
		if (rel instanceof ArchEResponsibilityReactionRelationVO) {
			if (!reaVOs.contains(rel))
				return (reaVOs.add((ArchEResponsibilityReactionRelationVO)rel));
			else
				return (false);
		}
		return (false);
	}
	
	public boolean deleteRelation(ArchERelation rel) {
		boolean addedToDeleteList = super.deleteRelation(rel);
		
		// The specific case of responsibility reactions
		if (rel instanceof ArchEResponsibilityReactionRelationVO)
			return (reaVOs.remove(rel));
		return (false);
	}
	
	public List<ArchERelation> getRelations(String relationTypeVO) {
		List<ArchERelation> results = super.getRelations(relationTypeVO);
		if(results != null)
			return results;
		
		// TODO: Review the type conversions below, maybe they can be programmed in a different way
		ArchERelation[] result = null;
		if (relationTypeVO.equals(ArchEResponsibilityReactionRelationVO.class.getName())) {
			result = new ArchERelation[reaVOs.size()];
			result = reaVOs.toArray(result);
			return (Arrays.asList(result));
		}
		else
			return (null);
		
	}
	
	// This is a hook method that is expected to  be overridden by an  ArchECoreResponsibilityStructure
	// subclass in order to load specific properties for a responsibility
	protected void restoreParametersForResponsibility(ArchEResponsibilityVO responsibility,
			Session openSession) {		
			
		String executionTime = ICMPerformanceReasoningFramework.PARAMETER_EXECUTION_TIME;
		String tableName1 = (String)(mappingsToTables.get(executionTime));
		Double parameterValue1 = ArchEParameterDAO.findDoubleParameter(tableName1, 
				responsibility, version, openSession);
		if (parameterValue1 != null) 
			responsibility.defineParameter(executionTime, parameterValue1);

		// Repeat the process for any parameter that needs to be loaded
		
		return;
	}
	
	// This is a hook method that is expected to  be overridden by an  ArchECoreResponsibilityStructure
	// subclass in order to save specific properties for a responsibility
	protected void saveParametersForResponsibility(ArchEResponsibilityVO responsibility, Session openSession) {
		
		String executionTime = ICMPerformanceReasoningFramework.PARAMETER_EXECUTION_TIME;
		String tableName1 = (String)(mappingsToTables.get(executionTime));
		if (responsibility.hasParameter(executionTime)) {			
			try {
				Double value1 = responsibility.getDoubleParameter(executionTime);
				ArchEParameterDAO.saveOrUpdateDoubleParameter(tableName1, 
						value1, responsibility, version, openSession);
			} catch (ArchEException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Repeat the process for any parameter that needs to be saved

		return;
	}
	
	// This is a hook method that is expected to  be overridden by an  ArchECoreResponsibilityStructure
	// subclass in order to load specific properties for a relation
	protected void restoreParametersForRelation(ArchERelationVO relation, Session openSession) {

		// No properties for the relations defined by the ICMPerformanceReasoningFramework
		
		// Repeat the process for any parameter that needs to be loaded

		return;
	}

	// This is a hook method that is expected to  be overridden by an  ArchECoreResponsibilityStructure
	// subclass in order to save specific properties for a relation
	protected void saveParametersForRelation(ArchERelationVO relation, Session openSession) {
		
		// No properties for the relations defined by the ICMPerformanceReasoningFramework

		// Repeat the process for any parameter that needs to be saved
		
		return;
	}

	// This is a hook method that is expected to  be overridden by an  ArchECoreResponsibilityStructure
	// subclass in order to delete specific properties for a responsibility
	protected void deleteParametersForResponsibility(ArchEResponsibilityVO responsibility, Session openSession) {

		String executionTime = ICMPerformanceReasoningFramework.PARAMETER_EXECUTION_TIME;
		String tableName1 = (String)(mappingsToTables.get(executionTime));
		if (responsibility.hasParameter(executionTime)) 			
			ArchEParameterDAO.deleteParameter(executionTime, tableName1, responsibility, version, openSession);

		// Repeat the process for any parameter that needs to be deleted

		return;
	}

	// This is a hook method that is expected to  be overridden by an  ArchECoreResponsibilityStructure
	// subclass in order to delete specific properties for a relationship
	protected void deleteParametersForRelation(ArchERelationVO relation, Session openSession) {

		// No properties for the relations defined by the ICMPerformanceReasoningFramework

		// Repeat the process for any parameter that needs to be saved
		
		return;
	}
	
}
