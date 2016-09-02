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

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.AccountEntry;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.IDataAccessObject;
import com.autentia.tnt.dao.SearchCriteria;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.util.SpringUtils;


public class AccountEntryDAO extends HibernateManagerBase<AccountEntry> {
/* accountEntry - generated by stajanov (do not edit/delete) */



  /** Logger */
  private static final Log log = LogFactory.getLog(AccountEntryDAO.class);

  /**
   * Get default AccountEntryDAO as defined in Spring's configuration file.
   * @return the default singleton AccountEntryDAO
   */
  public static AccountEntryDAO getDefault()
  {
    return (AccountEntryDAO)SpringUtils.getSpringBean("daoAccountEntry");
  }

 /** 
   * Constructor
   * @deprecated do not construct DAOs alone: use Spring's declared beans
   */
  public AccountEntryDAO(){
    super(false);
  }

  /** 
   * Retrieve a AccountEntry object from database given its id
   * @param id primary key of AccountEntry object
   * @return the AccountEntry object identified by the id
   * @throws DataAccException on error
   */
  public AccountEntry getById( int id ) throws DataAccException {
    return super.getByPk(AccountEntry.class,id);
  }

  /** 
   * Get all AccountEntry objects from database sorted by the given criteria
   * @param crit the sorting criteria
   * @return a list with all existing AccountEntry objects
   * @throws DataAccException on error
   */
  public List<AccountEntry> search( SortCriteria crit ) throws DataAccException {
    return super.list(AccountEntry.class,crit);
  }

  /** 
   * Get specified AccountEntry objects from database sorted by the given criteria
   * @param search search criteria
   * @param sort the sorting criteria
   * @return a list with AccountEntry objects matching the search criteria
   * @throws DataAccException on error
   */
  public List<AccountEntry> search(SearchCriteria search, SortCriteria sort) throws DataAccException {
    return super.search(AccountEntry.class,search,sort);
  }

  /** 
   * Insert a new AccountEntry object in database
   * @param dao the AccountEntry object to insert
   * @throws DataAccException on error
   */
  public void insert(AccountEntry dao) throws DataAccException {
    super.insert(dao);
  }

  /** 
   * Update an existing AccountEntry object in database
   * @param dao the AccountEntry object to update
   * @throws DataAccException on error
   */
  public void update(AccountEntry dao) throws DataAccException {
    super.update(dao,dao.getId());
  }

  /** 
   * Delete an existing AccountEntry object in database
   * @param dao the AccountEntry object to update
   * @throws DataAccException on error
   */
  public void delete(AccountEntry dao) throws DataAccException {
    super.delete(dao,dao.getId());
  }

/* accountEntry - generated by stajanov (do not edit/delete) */
}
