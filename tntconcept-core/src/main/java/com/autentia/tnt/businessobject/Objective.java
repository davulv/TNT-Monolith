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

package com.autentia.tnt.businessobject;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.autentia.tnt.dao.ITransferObject;
import com.autentia.tnt.dao.hibernate.UserDAO;

/**
 * Transfer object to store Objectives
 * @author stajanov code generator
 */
public class Objective implements Serializable, ITransferObject 
{
  /** Serial version field */
  private static final long serialVersionUID = -1L;
  
  public boolean isPending()
  {
    return state.equals(ObjectiveState.PENDING);
  }
  
/* objective - generated by stajanov (do not edit/delete) */









  // Fields
  
  
    
  private Integer id;

      
  
    
  private ObjectiveState state;

      
      
  private String name;

      
      
  private String log;

      
      
  private Date startDate;

      
      
  private Date endDate;

      
      
  private Integer departmentId;

      
      
  private Date insertDate;

      
      
  private Date updateDate;

        
  
    
  private User user;

      
  
    
  private Project project;

        	 	

  // Setters and getters
  
  
  
  public Integer getId() {
    return id;
  }
  private void setId( Integer id ) {
    this.id = id;
  }
      
  
  
  public ObjectiveState getState() {
    return state;
  }
  public void setState( ObjectiveState state ) {
    this.state = state;
  }
      
  
  
  public String getName() {
    return name;
  }
  public void setName( String name ) {
    this.name = name;
  }
      
  
  
  public String getLog() {
    return log;
  }
  public void setLog( String log ) {
    this.log = log;
  }
      
  
  
  public Date getStartDate() {
    return startDate;
  }
  public void setStartDate( Date startDate ) {
    this.startDate = startDate;
  }
      
  
  
  public Date getEndDate() {
    return endDate;
  }
  public void setEndDate( Date endDate ) {
    this.endDate = endDate;
  }
      
  
  
  public Integer getDepartmentId() {
    return departmentId;
  }
  public void setDepartmentId( Integer departmentId ) {
    this.departmentId = departmentId;
  }
      
  
  
  public Date getInsertDate() {
    return insertDate;
  }
  public void setInsertDate( Date insertDate ) {
    this.insertDate = insertDate;
  }
      
  
  
  public Date getUpdateDate() {
    return updateDate;
  }
  public void setUpdateDate( Date updateDate ) {
    this.updateDate = updateDate;
  }
        
  
  
  public User getUser() {
    return user;
  }
  public void setUser( User user ) {
    this.user = user;
  }
      
  
  
  public Project getProject() {
    return project;
  }
  public void setProject( Project project ) {
    this.project = project;
  }
        
        public Integer getOwnerId()
    {
      return user.getId();
    }
    
      
        public void setOwnerId(Integer ownerId) {
      user = UserDAO.getDefault().getById(ownerId);
    }
    
    
  public boolean equals( Object that )
  {
  	try {
	  if (that == null) 
  		return false;
  	else 
    	return this.getId().equals( ((Objective)that).getId() );
  } catch (Exception e) {
		return false;
	}
  }
  
  public int hashCode() {
  	  if(this.getId()==null)
  	  	return super.hashCode();
  	  else	
	 	return this.getId().intValue();
	}
/* objective - generated by stajanov (do not edit/delete) */
	public List<Integer> getOwnersId() {
		// TODO Auto-generated method stub
		return null;
	}
}
