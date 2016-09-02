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

package com.autentia.tnt.manager.contacts;

import com.autentia.tnt.businessobject.Collaborator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.CollaboratorDAO;
import com.autentia.tnt.dao.search.CollaboratorSearch;
import com.autentia.tnt.util.SpringUtils;




public class CollaboratorManager {
    /* Collaborator - generated by stajanov (do not edit/delete) */



  /** Logger */
  private static final Log log = LogFactory.getLog(CollaboratorManager.class);

  /** Collaborator DAO **/
  private CollaboratorDAO collaboratorDAO;

  /**
   * Get default CollaboratorManager as defined in Spring's configuration file.
   * @return the default singleton CollaboratorManager
   */
  public static CollaboratorManager getDefault()
  {
    return (CollaboratorManager)SpringUtils.getSpringBean("managerCollaborator");
  }

  /** 
   * Empty constructor needed by CGLIB (Spring AOP)
   */
  protected CollaboratorManager()
  {
  }
	
  /** 
   * Default constructor 
   * @deprecated do not construct managers alone: use Spring's declared beans
   */
  public CollaboratorManager( CollaboratorDAO collaboratorDAO )
  {
    this.collaboratorDAO = collaboratorDAO;
  }

  /**
   * List collaborators. 
   * @param search search filter to apply
   * @param sort sorting criteria
   * @return the list of all collaborators sorted by requested criterion
   */
  public List<Collaborator> getAllEntities(CollaboratorSearch search, SortCriteria sort){
    return collaboratorDAO.search( search, sort );
  }
  
  /**
   * Get collaborator by primary key.
   * @return collaborator selected by id.
   */
  public Collaborator getEntityById(int id){
    return collaboratorDAO.getById(id);	    
  }
	
  /**
   * Insert collaborator. 
   */
  public void insertEntity(Collaborator collaborator) {
    collaboratorDAO.insert(collaborator);
  }
	 
  /**
   * Update collaborator. 
   */
  public void updateEntity(Collaborator collaborator) {
    collaboratorDAO.update(collaborator);
  }

  /**
   * Delete collaborator. 
   */
  public void deleteEntity(Collaborator collaborator) {
    collaboratorDAO.delete(collaborator);
  }

/* Collaborator - generated by stajanov (do not edit/delete) */

}