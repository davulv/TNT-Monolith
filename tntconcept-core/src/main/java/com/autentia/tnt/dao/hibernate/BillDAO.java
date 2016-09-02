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
import com.autentia.tnt.util.SpringUtils;

import java.util.*;

import org.apache.commons.logging.*;

/**
 * DAO for Bill objects.
 * @author stajanov code generator
 */
public class BillDAO extends HibernateManagerBase<Bill> 
{
/* bill - generated by stajanov (do not edit/delete) */
  /** Logger */
  private static final Log log = LogFactory.getLog(BillDAO.class);

  /**
   * Get default BillDAO as defined in Spring's configuration file.
   * @return the default singleton BillDAO
   */
  public static BillDAO getDefault()
  {
    return (BillDAO)SpringUtils.getSpringBean("daoBill");
  }

 /** 
   * Constructor
   * @deprecated do not construct DAOs alone: use Spring's declared beans
   */
  public BillDAO(){
    super(false);
  }

  /** 
   * Retrieve a Bill object from database given its id
   * @param id primary key of Bill object
   * @return the Bill object identified by the id
   * @throws DataAccException on error
   */
  public Bill getById( int id ) throws DataAccException {
    return super.getByPk(Bill.class,id);
  }

  /** 
   * Get all Bill objects from database sorted by the given criteria
   * @param crit the sorting criteria
   * @return a list with all existing Bill objects
   * @throws DataAccException on error
   */
  public List<Bill> search( SortCriteria crit ) throws DataAccException {
    return super.list(Bill.class,crit);
  }

  /** 
   * Get specified Bill objects from database sorted by the given criteria
   * @param search search criteria
   * @param sort the sorting criteria
   * @return a list with Bill objects matching the search criteria
   * @throws DataAccException on error
   */
  public List<Bill> search(SearchCriteria search, SortCriteria sort) throws DataAccException {
    return super.search(Bill.class,search,sort);
  }

  /** 
   * Insert a new Bill object in database
   * @param dao the Bill object to insert
   * @throws DataAccException on error
   */
  public void insert(Bill dao) throws DataAccException {
    super.insert(dao);
  }

  /** 
   * Update an existing Bill object in database
   * @param dao the Bill object to update
   * @throws DataAccException on error
   */
  public void update(Bill dao) throws DataAccException {
    super.update(dao,dao.getId());
  }

  /** 
   * Delete an existing Bill object in database
   * @param dao the Bill object to update
   * @throws DataAccException on error
   */
  public void delete(Bill dao) throws DataAccException {
    super.delete(dao,dao.getId());
  }
/* bill - generated by stajanov (do not edit/delete) */
  
  
  
  
}
