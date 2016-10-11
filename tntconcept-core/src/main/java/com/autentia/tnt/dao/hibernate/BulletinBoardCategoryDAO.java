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
import com.autentia.tnt.dao.search.BulletinBoardCategorySearch;
import com.autentia.tnt.dao.search.BulletinBoardSearch;
import com.autentia.tnt.manager.bulletin.CategoryServiceProxy;
import com.autentia.tnt.manager.bulletin.CategoryServiceProxyImpl;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.SpringUtils;

import java.util.*;
import org.apache.commons.logging.*;

/**
 * DAO for BulletinBoardCategory objects.
 * @author stajanov code generator
 */
public class BulletinBoardCategoryDAO extends HibernateManagerBase<BulletinBoardCategory> 
{
	
	private CategoryServiceProxy categoryServiceProxy = new CategoryServiceProxyImpl();
	
  /**
   * Get configured public category
   * @return public category object
   */
  public BulletinBoardCategory getPublicCategory() {
    //return super.getByPk( BulletinBoardCategory.class, ConfigurationUtil.getDefault().getIdPublicCategory() );
    return (BulletinBoardCategory) categoryServiceProxy.getById(ConfigurationUtil.getDefault().getIdPublicCategory());
  }
  
/* bulletinBoardCategory - generated by stajanov (do not edit/delete) */



  /** Logger */
  private static final Log log = LogFactory.getLog(BulletinBoardCategoryDAO.class);

  /**
   * Get default BulletinBoardCategoryDAO as defined in Spring's configuration file.
   * @return the default singleton BulletinBoardCategoryDAO
   */
  public static BulletinBoardCategoryDAO getDefault()
  {
    return (BulletinBoardCategoryDAO)SpringUtils.getSpringBean("daoBulletinBoardCategory");
  }

 /** 
   * Constructor
   * @deprecated do not construct DAOs alone: use Spring's declared beans
   */
  public BulletinBoardCategoryDAO(){
    super(false);
  }

  /** 
   * Retrieve a BulletinBoardCategory object from database given its id
   * @param id primary key of BulletinBoardCategory object
   * @return the BulletinBoardCategory object identified by the id
   * @throws DataAccException on error
   */
  public BulletinBoardCategory getById( int id ) throws DataAccException {
    //return super.getByPk(BulletinBoardCategory.class,id);
	  return (BulletinBoardCategory) categoryServiceProxy.getById(id);
  }

  /** 
   * Get all BulletinBoardCategory objects from database sorted by the given criteria
   * @param crit the sorting criteria
   * @return a list with all existing BulletinBoardCategory objects
   * @throws DataAccException on error
   */
  public List<BulletinBoardCategory> search( SortCriteria crit ) throws DataAccException {
    //return super.list(BulletinBoardCategory.class,crit);
	  BulletinBoardCategorySearch search = new BulletinBoardCategorySearch();
	  return categoryServiceProxy.getAllCategories(search, crit);
  }

  /** 
   * Get specified BulletinBoardCategory objects from database sorted by the given criteria
   * @param search search criteria
   * @param sort the sorting criteria
   * @return a list with BulletinBoardCategory objects matching the search criteria
   * @throws DataAccException on error
   */
  public List<BulletinBoardCategory> search(SearchCriteria search, SortCriteria sort) throws DataAccException {
    //return super.search(BulletinBoardCategory.class,search,sort);
	  return categoryServiceProxy.getAllCategories((BulletinBoardCategorySearch) search, sort);
  }

  /** 
   * Insert a new BulletinBoardCategory object in database
   * @param dao the BulletinBoardCategory object to insert
   * @throws DataAccException on error
   */
  public void insert(BulletinBoardCategory dao) throws DataAccException {
    //super.insert(dao);
	  categoryServiceProxy.insertCategory(dao);
  }

  /** 
   * Update an existing BulletinBoardCategory object in database
   * @param dao the BulletinBoardCategory object to update
   * @throws DataAccException on error
   */
  public void update(BulletinBoardCategory dao) throws DataAccException {
    //super.update(dao,dao.getId());
	  categoryServiceProxy.updateCategory(dao);
  }

  /** 
   * Delete an existing BulletinBoardCategory object in database
   * @param dao the BulletinBoardCategory object to update
   * @throws DataAccException on error
   */
  public void delete(BulletinBoardCategory dao) throws DataAccException {
    //super.delete(dao,dao.getId());
    categoryServiceProxy.deleteCategory(dao);
  }

/* bulletinBoardCategory - generated by stajanov (do not edit/delete) */
}
