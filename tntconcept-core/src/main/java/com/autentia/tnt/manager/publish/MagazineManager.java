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

package com.autentia.tnt.manager.publish;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.Magazine;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.MagazineDAO;
import com.autentia.tnt.dao.search.MagazineSearch;
import com.autentia.tnt.util.SpringUtils;



public class MagazineManager {

/* Magazine - generated by stajanov (do not edit/delete) */



  /** Logger */
  private static final Log log = LogFactory.getLog(MagazineManager.class);

  /** Magazine DAO **/
  private MagazineDAO magazineDAO;
  
  private MagazineServiceProxy proxy = new MagazineServiceProxyImpl();

  /**
   * Get default MagazineManager as defined in Spring's configuration file.
   * @return the default singleton MagazineManager
   */
  public static MagazineManager getDefault()
  {
    return (MagazineManager)SpringUtils.getSpringBean("managerMagazine");
  }

  /** 
   * Empty constructor needed by CGLIB (Spring AOP)
   */
  protected MagazineManager()
  {
  }
	
  /** 
   * Default constructor 
   * @deprecated do not construct managers alone: use Spring's declared beans
   */
  public MagazineManager( MagazineDAO magazineDAO )
  {
    this.magazineDAO = magazineDAO;
  }

  /**
   * List magazines. 
   * @param search search filter to apply
   * @param sort sorting criteria
   * @return the list of all magazines sorted by requested criterion
   */
  public List<Magazine> getAllEntities(MagazineSearch search, SortCriteria sort){
    //return magazineDAO.search( search, sort );
	  List<Magazine> magList =  proxy.getAllEntities(search, sort);
	  log.info("In MagazineManager --"+ magList.size());
	  Magazine m = magList.get(0);
	  log.info("In MagazineManager id before returning --"+ m.getId() + "name "+ m.getName() +"ownerid--"+ m.getOwnerId());
	  return magList;
  }
  
  /**
   * Get magazine by primary key.
   * @return magazine selected by id.
   */
  public Magazine getEntityById(int id){
    //return magazineDAO.getById(id);
	  return (Magazine)proxy.getById(id);
  }
	
  /**
   * Insert magazine. 
   */
  public void insertEntity(Magazine magazine) {
    //magazineDAO.insert(magazine);
	  log.info("In Insert magazine "+ magazine.getOwnerId());	  
	  proxy.insertEntity(magazine);
  }
	 
  /**
   * Update magazine. 
   */
  public void updateEntity(Magazine magazine) {
    //magazineDAO.update(magazine);
	  proxy.updateEntity(magazine);
  }

  /**
   * Delete magazine. 
   */
  public void deleteEntity(Magazine magazine) {
    //magazineDAO.delete(magazine);
	  proxy.deleteEntity(magazine);
  }

/* Magazine - generated by stajanov (do not edit/delete) */

	
}
