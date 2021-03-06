/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.autentia.tnt.manager.admin;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.WorkingAgreement;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.WorkingAgreementDAO;
import com.autentia.tnt.dao.search.WorkingAgreementSearch;
import com.autentia.tnt.util.SpringUtils;

public class WorkingAgreementManager {

/* generated by stajanov (do not edit/delete) */



  /** Logger */
  private static final Log log = LogFactory.getLog(WorkingAgreementManager.class);

  /** WorkingAgreement DAO **/
  private WorkingAgreementDAO workingAgreementDAO;

  /**
   * Get default WorkingAgreementManager as defined in Spring's configuration file.
   * @return the default singleton WorkingAgreementManager
   */
  public static WorkingAgreementManager getDefault()
  {
    return (WorkingAgreementManager)SpringUtils.getSpringBean("managerWorkingAgreement");
  }

  /** 
   * Empty constructor needed by CGLIB (Spring AOP)
   */
  protected WorkingAgreementManager()
  {
  }
	
  /** 
   * Default constructor 
   * @deprecated do not construct managers alone: use Spring's declared beans
   */
  public WorkingAgreementManager( WorkingAgreementDAO workingAgreementDAO )
  {
    this.workingAgreementDAO = workingAgreementDAO;
  }

  /**
   * List workingAgreements. 
   * @param search search filter to apply
   * @param sort sorting criteria
   * @return the list of all workingAgreements sorted by requested criterion
   */
  public List<WorkingAgreement> getAllEntities(WorkingAgreementSearch search, SortCriteria sort){
    return workingAgreementDAO.search( search, sort );
  }
  
  /**
   * Get workingAgreement by primary key.
   * @return workingAgreement selected by id.
   */
  public WorkingAgreement getEntityById(int id){
    return workingAgreementDAO.getById(id);	    
  }
	
  /**
   * Insert workingAgreement. 
   */
  public void insertEntity(WorkingAgreement workingAgreement) {
    workingAgreementDAO.insert(workingAgreement);
  }
	 
  /**
   * Update workingAgreement. 
   */
  public void updateEntity(WorkingAgreement workingAgreement) {
    workingAgreementDAO.update(workingAgreement);
  }

  /**
   * Delete workingAgreement. 
   */
  public void deleteEntity(WorkingAgreement workingAgreement) {
    workingAgreementDAO.delete(workingAgreement);
  }

/* generated by stajanov (do not edit/delete) */
  
  
}
