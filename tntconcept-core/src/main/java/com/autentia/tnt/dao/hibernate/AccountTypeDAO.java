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

import com.autentia.tnt.businessobject.Account;
import com.autentia.tnt.businessobject.AccountType;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.IDataAccessObject;
import com.autentia.tnt.dao.SearchCriteria;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.util.SpringUtils;

public class AccountTypeDAO extends HibernateManagerBase<AccountType>  {

/* accountType - generated by stajanov (do not edit/delete) */
  /** Logger */
  private static final Log log = LogFactory.getLog(AccountTypeDAO.class);

  /**
   * Get default AccountTypeDAO as defined in Spring's configuration file.
   * @return the default singleton AccountTypeDAO
   */
  public static AccountTypeDAO getDefault()
  {
    return (AccountTypeDAO)SpringUtils.getSpringBean("daoAccountType");
  }

 /** 
   * Constructor
   * @deprecated do not construct DAOs alone: use Spring's declared beans
   */
  public AccountTypeDAO(){
    super(false);
  }

  /** 
   * Retrieve a AccountType object from database given its id
   * @param id primary key of AccountType object
   * @return the AccountType object identified by the id
   * @throws DataAccException on error
   */
  public AccountType getById( int id ) throws DataAccException {
    return super.getByPk(AccountType.class,id);
  }

  /** 
   * Get all AccountType objects from database sorted by the given criteria
   * @param crit the sorting criteria
   * @return a list with all existing AccountType objects
   * @throws DataAccException on error
   */
  public List<AccountType> search( SortCriteria crit ) throws DataAccException {
    return super.list(AccountType.class,crit);
  }

  /** 
   * Get specified AccountType objects from database sorted by the given criteria
   * @param search search criteria
   * @param sort the sorting criteria
   * @return a list with AccountType objects matching the search criteria
   * @throws DataAccException on error
   */
  public List<AccountType> search(SearchCriteria search, SortCriteria sort) throws DataAccException {
    return super.search(AccountType.class,search,sort);
  }

  /** 
   * Insert a new AccountType object in database
   * @param dao the AccountType object to insert
   * @throws DataAccException on error
   */
  public void insert(AccountType dao) throws DataAccException {
    super.insert(dao);
  }

  /** 
   * Update an existing AccountType object in database
   * @param dao the AccountType object to update
   * @throws DataAccException on error
   */
  public void update(AccountType dao) throws DataAccException {
    super.update(dao,dao.getId());
  }

  /** 
   * Delete an existing AccountType object in database
   * @param dao the AccountType object to update
   * @throws DataAccException on error
   */
  public void delete(AccountType dao) throws DataAccException {
    super.delete(dao,dao.getId());
  }
/* accountType - generated by stajanov (do not edit/delete) */
}
