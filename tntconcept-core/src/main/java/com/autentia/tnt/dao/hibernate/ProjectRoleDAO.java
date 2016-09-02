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

package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.*;
import com.autentia.tnt.dao.*;
import com.autentia.tnt.dao.search.ProjectSearch;
import com.autentia.tnt.util.SpringUtils;

import java.util.*;
import org.apache.commons.logging.*;

/**
 * DAO for ProjectRole objects.
 * @author stajanov code generator
 */
public class ProjectRoleDAO extends HibernateManagerBase<ProjectRole> 
{
/* projectRole - generated by stajanov (do not edit/delete) */
  /** Logger */
  private static final Log log = LogFactory.getLog(ProjectRoleDAO.class);

  /**
   * Get default ProjectRoleDAO as defined in Spring's configuration file.
   * @return the default singleton ProjectRoleDAO
   */
  public static ProjectRoleDAO getDefault()
  {
    return (ProjectRoleDAO)SpringUtils.getSpringBean("daoProjectRole");
  }

 /** 
   * Constructor
   * @deprecated do not construct DAOs alone: use Spring's declared beans
   */
  public ProjectRoleDAO(){
    super(false);
  }

  /** 
   * Retrieve a ProjectRole object from database given its id
   * @param id primary key of ProjectRole object
   * @return the ProjectRole object identified by the id
   * @throws DataAccException on error
   */
  public ProjectRole getById( int id ) throws DataAccException {
    return super.getByPk(ProjectRole.class,id);
  }

  /** 
   * Get all ProjectRole objects from database sorted by the given criteria
   * @param crit the sorting criteria
   * @return a list with all existing ProjectRole objects
   * @throws DataAccException on error
   */
  public List<ProjectRole> search( SortCriteria crit ) throws DataAccException {
    return super.list(ProjectRole.class,crit);
  }

  /** 
   * Get specified ProjectRole objects from database sorted by the given criteria
   * @param search search criteria
   * @param sort the sorting criteria
   * @return a list with ProjectRole objects matching the search criteria
   * @throws DataAccException on error
   */
  public List<ProjectRole> search(SearchCriteria search, SortCriteria sort) throws DataAccException {
    return super.search(ProjectRole.class,search,sort);
  }

  /** 
   * Insert a new ProjectRole object in database
   * @param dao the ProjectRole object to insert
   * @throws DataAccException on error
   */
  public void insert(ProjectRole dao) throws DataAccException {
    super.insert(dao);
  }

  /** 
   * Update an existing ProjectRole object in database
   * @param dao the ProjectRole object to update
   * @throws DataAccException on error
   */
  public void update(ProjectRole dao) throws DataAccException {
    super.update(dao,dao.getId());
  }

  /** 
   * Delete an existing ProjectRole object in database
   * @param dao the ProjectRole object to update
   * @throws DataAccException on error
   */
  public void delete(ProjectRole dao) throws DataAccException {
    super.delete(dao,dao.getId());
  }
/* projectRole - generated by stajanov (do not edit/delete) */
}
