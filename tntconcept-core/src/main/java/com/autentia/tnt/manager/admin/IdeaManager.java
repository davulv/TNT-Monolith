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

import com.autentia.tnt.businessobject.Idea;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.IdeaDAO;
import com.autentia.tnt.dao.hibernate.UserDAO;
import com.autentia.tnt.dao.search.IdeaSearch;
import com.autentia.tnt.util.SpringUtils;






public class IdeaManager {

/* Idea - generated by stajanov (do not edit/delete) */



  /** Logger */
  private static final Log log = LogFactory.getLog(IdeaManager.class);

  /** Idea DAO **/
  private IdeaDAO ideaDAO;
  
  private IdeaServiceproxy ideaServiceproxy = new IdeaServiceProxyImpl();

  /**
   * Get default IdeaManager as defined in Spring's configuration file.
   * @return the default singleton IdeaManager
   */
  public static IdeaManager getDefault()
  {
    return (IdeaManager)SpringUtils.getSpringBean("managerIdea");
  }

  /** 
   * Empty constructor needed by CGLIB (Spring AOP)
   */
  protected IdeaManager()
  {
  }
	
  /** 
   * Default constructor 
   * @deprecated do not construct managers alone: use Spring's declared beans
   */
  public IdeaManager( IdeaDAO ideaDAO )
  {
    this.ideaDAO = ideaDAO;
  }

  /**
   * List ideas. 
   * @param search search filter to apply
   * @param sort sorting criteria
   * @return the list of all ideas sorted by requested criterion
   */
  public List<Idea> getAllEntities(IdeaSearch search, SortCriteria sort){
    //return ideaDAO.search( search, sort );
	  return ideaServiceproxy.getAllIdeas(search, sort);
  }
  
  /**
   * Get idea by primary key.
   * @return idea selected by id.
   */
  public Idea getEntityById(int id){
    //return ideaDAO.getById(id);
	  return (Idea) ideaServiceproxy.getById(id);
  }
	
  /**
   * Insert idea. 
   */
  public void insertEntity(Idea idea) {
    //ideaDAO.insert(idea);
	  ideaServiceproxy.insertIdea(idea);
  }
	 
  /**
   * Update idea. 
   */
  public void updateEntity(Idea idea) {
    //ideaDAO.update(idea);
	  ideaServiceproxy.updateIdea(idea);
  }

  /**
   * Delete idea. 
   */
  public void deleteEntity(Idea idea) {
    //ideaDAO.delete(idea);
	  ideaServiceproxy.deleteIdea(idea);
  }

/* Idea - generated by stajanov (do not edit/delete) */
}
