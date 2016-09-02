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














package com.autentia.tnt.dao.search;

import java.util.*;
import java.math.*;

import org.hibernate.type.*;

import com.autentia.tnt.businessobject.*;
import com.autentia.tnt.dao.*;

/**
 * Class to search for Tutorial objects
 * @author stajanov code generator
 */
public class TutorialSearch extends SearchCriteria 
{
/* generated by stajanov (do not edit/delete) */















  @Override
  public String getHQL() {
    StringBuilder ret = new StringBuilder();
    int iArgNum = 0;
            
    
  
        
    if( isNameSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( getName()==null ){
	ret.append( "name is NULL" );
      } else {
	ret.append( "name like :arg"+(iArgNum++) );
      }
    }

              
    
  
        
    if( isDescriptionSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( getDescription()==null ){
	ret.append( "description is NULL" );
      } else {
	ret.append( "description like :arg"+(iArgNum++) );
      }
    }

              
    
      if( isStartMaxDeliveryDateSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( startMaxDeliveryDate == null ){
        ret.append( "maxDeliveryDate=:arg"+(iArgNum++) );
      } else {
        ret.append( "maxDeliveryDate>=:arg"+(iArgNum++) );
      }
    }
    if( isEndMaxDeliveryDateSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( endMaxDeliveryDate == null ){
        ret.append( "maxDeliveryDate=:arg"+(iArgNum++) );
      } else {
        ret.append( "maxDeliveryDate<=:arg"+(iArgNum++) );
      }
    }

              
    
      if( isStartEndDateSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( startEndDate == null ){
        ret.append( "endDate=:arg"+(iArgNum++) );
      } else {
        ret.append( "endDate>=:arg"+(iArgNum++) );
      }
    }
    if( isEndEndDateSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( endEndDate == null ){
        ret.append( "endDate=:arg"+(iArgNum++) );
      } else {
        ret.append( "endDate<=:arg"+(iArgNum++) );
      }
    }

              
    
  
        
    if( isDepartmentIdSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( getDepartmentId()==null ){
	ret.append( "departmentId is NULL" );
      } else {
	ret.append( "departmentId = :arg"+(iArgNum++) );
      }
    }

              
    
      if( isStartInsertDateSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( startInsertDate == null ){
        ret.append( "insertDate=:arg"+(iArgNum++) );
      } else {
        ret.append( "insertDate>=:arg"+(iArgNum++) );
      }
    }
    if( isEndInsertDateSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( endInsertDate == null ){
        ret.append( "insertDate=:arg"+(iArgNum++) );
      } else {
        ret.append( "insertDate<=:arg"+(iArgNum++) );
      }
    }

              
    
      if( isStartUpdateDateSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( startUpdateDate == null ){
        ret.append( "updateDate=:arg"+(iArgNum++) );
      } else {
        ret.append( "updateDate>=:arg"+(iArgNum++) );
      }
    }
    if( isEndUpdateDateSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( endUpdateDate == null ){
        ret.append( "updateDate=:arg"+(iArgNum++) );
      } else {
        ret.append( "updateDate<=:arg"+(iArgNum++) );
      }
    }

                  
    
  
        
    if( isUserSet() ){
      ret.append( (ret.length()==0) ? "WHERE " : " AND " );
      if( getUser()==null ){
	ret.append( "user is NULL" );
      } else {
	ret.append( "user = :arg"+(iArgNum++) );
      }
    }

                  customGetHQL(ret,iArgNum);
    return ret.toString();
  }

  @Override
  public Object[] getArguments(){
    ArrayList<Object> ret = new ArrayList<Object>();
            
  
      if( isNameSet() && getName()!=null ){
        ret.add( name );
    }

              
  
      if( isDescriptionSet() && getDescription()!=null ){
        ret.add( description );
    }

              
  
      if( isStartMaxDeliveryDateSet() ){
        ret.add( startMaxDeliveryDate );
    }
    if( isEndMaxDeliveryDateSet() ){
        ret.add( endMaxDeliveryDate );
    }

              
  
      if( isStartEndDateSet() ){
        ret.add( startEndDate );
    }
    if( isEndEndDateSet() ){
        ret.add( endEndDate );
    }

              
  
      if( isDepartmentIdSet() && getDepartmentId()!=null ){
        ret.add( departmentId );
    }

              
  
      if( isStartInsertDateSet() ){
        ret.add( startInsertDate );
    }
    if( isEndInsertDateSet() ){
        ret.add( endInsertDate );
    }

              
  
      if( isStartUpdateDateSet() ){
        ret.add( startUpdateDate );
    }
    if( isEndUpdateDateSet() ){
        ret.add( endUpdateDate );
    }

                  
  
      if( isUserSet() && getUser()!=null ){
        ret.add( user );
    }

                  customGetArguments(ret);
    return ret.toArray();
  }

  @Override
  public void reset(){
            
  
      unsetName();
  
              
  
      unsetDescription();
  
              
  
      unsetStartMaxDeliveryDate();
    unsetEndMaxDeliveryDate();

              
  
      unsetStartEndDate();
    unsetEndEndDate();

              
  
      unsetDepartmentId();
  
              
  
      unsetStartInsertDate();
    unsetEndInsertDate();

              
  
      unsetStartUpdateDate();
    unsetEndUpdateDate();

                  
  
      unsetUser();
  
                  customReset();
  }
    
  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder();
    ret.append("TutorialSearch{");
            
  
  
          if( isNameSet() ){
          ret.append( "(name" );
          ret.append( "="+name );
          ret.append( ")" );
      }

    
              
  
  
          if( isDescriptionSet() ){
          ret.append( "(description" );
          ret.append( "="+description );
          ret.append( ")" );
      }

    
              
  
  
    if( isStartMaxDeliveryDateSet() ){
        ret.append( "(startMaxDeliveryDate" );
        ret.append( "="+startMaxDeliveryDate );
        ret.append( ")" );
    }
    if( isEndMaxDeliveryDateSet() ){
        ret.append( "(endMaxDeliveryDate" );
        ret.append( "="+endMaxDeliveryDate );
        ret.append( ")" );
    }

              
  
  
    if( isStartEndDateSet() ){
        ret.append( "(startEndDate" );
        ret.append( "="+startEndDate );
        ret.append( ")" );
    }
    if( isEndEndDateSet() ){
        ret.append( "(endEndDate" );
        ret.append( "="+endEndDate );
        ret.append( ")" );
    }

              
  
  
          if( isDepartmentIdSet() ){
          ret.append( "(departmentId" );
          ret.append( "="+departmentId );
          ret.append( ")" );
      }

    
              
  
  
    if( isStartInsertDateSet() ){
        ret.append( "(startInsertDate" );
        ret.append( "="+startInsertDate );
        ret.append( ")" );
    }
    if( isEndInsertDateSet() ){
        ret.append( "(endInsertDate" );
        ret.append( "="+endInsertDate );
        ret.append( ")" );
    }

              
  
  
    if( isStartUpdateDateSet() ){
        ret.append( "(startUpdateDate" );
        ret.append( "="+startUpdateDate );
        ret.append( ")" );
    }
    if( isEndUpdateDateSet() ){
        ret.append( "(endUpdateDate" );
        ret.append( "="+endUpdateDate );
        ret.append( ")" );
    }

                  
  
  
          if( isUserSet() ){
          ret.append( "(user" );
          ret.append( "="+user );
          ret.append( ")" );
      }

    
                  customToString(ret);
    ret.append("}");
    return ret.toString();
  }

  // Getters and setters
        
  
  
    
    
    public boolean isNameSet(){
        return nameSet;
    }
    public String getName(){
        return name;
    }
    public void setName( String name ){
        this.name = name;
        this.nameSet = true;
    }
    public void unsetName(){
        this.nameSet = false;
    }
          
  
  
    
    
    public boolean isDescriptionSet(){
        return descriptionSet;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription( String description ){
        this.description = description;
        this.descriptionSet = true;
    }
    public void unsetDescription(){
        this.descriptionSet = false;
    }
          
  
  
    public boolean isStartMaxDeliveryDateSet(){
        return startMaxDeliveryDateSet;
    }
    public Date getStartMaxDeliveryDate(){
        return startMaxDeliveryDate;
    }
    public void setStartMaxDeliveryDate( Date startMaxDeliveryDate ){
        this.startMaxDeliveryDate = startMaxDeliveryDate;
        this.startMaxDeliveryDateSet = true;
    }
    public void unsetStartMaxDeliveryDate(){
        this.startMaxDeliveryDateSet = false;
    }
    public boolean isEndMaxDeliveryDateSet(){
        return endMaxDeliveryDateSet;
    }
    public Date getEndMaxDeliveryDate(){
        return endMaxDeliveryDate;
    }
    public void setEndMaxDeliveryDate( Date endMaxDeliveryDate ){
        this.endMaxDeliveryDate = endMaxDeliveryDate;
        this.endMaxDeliveryDateSet = true;
    }
    public void unsetEndMaxDeliveryDate(){
        this.endMaxDeliveryDateSet = false;
    }

          
  
  
    public boolean isStartEndDateSet(){
        return startEndDateSet;
    }
    public Date getStartEndDate(){
        return startEndDate;
    }
    public void setStartEndDate( Date startEndDate ){
        this.startEndDate = startEndDate;
        this.startEndDateSet = true;
    }
    public void unsetStartEndDate(){
        this.startEndDateSet = false;
    }
    public boolean isEndEndDateSet(){
        return endEndDateSet;
    }
    public Date getEndEndDate(){
        return endEndDate;
    }
    public void setEndEndDate( Date endEndDate ){
        this.endEndDate = endEndDate;
        this.endEndDateSet = true;
    }
    public void unsetEndEndDate(){
        this.endEndDateSet = false;
    }

          
  
  
    
    
    public boolean isDepartmentIdSet(){
        return departmentIdSet;
    }
    public Integer getDepartmentId(){
        return departmentId;
    }
    public void setDepartmentId( Integer departmentId ){
        this.departmentId = departmentId;
        this.departmentIdSet = true;
    }
    public void unsetDepartmentId(){
        this.departmentIdSet = false;
    }
          
  
  
    public boolean isStartInsertDateSet(){
        return startInsertDateSet;
    }
    public Date getStartInsertDate(){
        return startInsertDate;
    }
    public void setStartInsertDate( Date startInsertDate ){
        this.startInsertDate = startInsertDate;
        this.startInsertDateSet = true;
    }
    public void unsetStartInsertDate(){
        this.startInsertDateSet = false;
    }
    public boolean isEndInsertDateSet(){
        return endInsertDateSet;
    }
    public Date getEndInsertDate(){
        return endInsertDate;
    }
    public void setEndInsertDate( Date endInsertDate ){
        this.endInsertDate = endInsertDate;
        this.endInsertDateSet = true;
    }
    public void unsetEndInsertDate(){
        this.endInsertDateSet = false;
    }

          
  
  
    public boolean isStartUpdateDateSet(){
        return startUpdateDateSet;
    }
    public Date getStartUpdateDate(){
        return startUpdateDate;
    }
    public void setStartUpdateDate( Date startUpdateDate ){
        this.startUpdateDate = startUpdateDate;
        this.startUpdateDateSet = true;
    }
    public void unsetStartUpdateDate(){
        this.startUpdateDateSet = false;
    }
    public boolean isEndUpdateDateSet(){
        return endUpdateDateSet;
    }
    public Date getEndUpdateDate(){
        return endUpdateDate;
    }
    public void setEndUpdateDate( Date endUpdateDate ){
        this.endUpdateDate = endUpdateDate;
        this.endUpdateDateSet = true;
    }
    public void unsetEndUpdateDate(){
        this.endUpdateDateSet = false;
    }

            
  
  
    
    
    public boolean isUserSet(){
        return userSet;
    }
    public User getUser(){
        return user;
    }
    public void setUser( User user ){
        this.user = user;
        this.userSet = true;
    }
    public void unsetUser(){
        this.userSet = false;
    }
        
  // Fields
        
  
      private boolean nameSet;
        private String name;

          
  
      private boolean descriptionSet;
        private String description;

          
  
      private boolean startMaxDeliveryDateSet;
        private Date startMaxDeliveryDate;
    private boolean endMaxDeliveryDateSet;
        private Date endMaxDeliveryDate;

          
  
      private boolean startEndDateSet;
        private Date startEndDate;
    private boolean endEndDateSet;
        private Date endEndDate;

          
  
      private boolean departmentIdSet;
        private Integer departmentId;

          
  
      private boolean startInsertDateSet;
        private Date startInsertDate;
    private boolean endInsertDateSet;
        private Date endInsertDate;

          
  
      private boolean startUpdateDateSet;
        private Date startUpdateDate;
    private boolean endUpdateDateSet;
        private Date endUpdateDate;

            
  
      private boolean userSet;
        private User user;
  
        
  // Returns if there are a search condition active
  public boolean isSearchActive() {
    return customIsSearchActive()||nameSet||descriptionSet||startMaxDeliveryDateSet||endMaxDeliveryDateSet||startEndDateSet||endEndDateSet||departmentIdSet||startInsertDateSet||endInsertDateSet||startUpdateDateSet||endUpdateDateSet||userSet;
  }


/* generated by stajanov (do not edit/delete) */

	private void customGetHQL(StringBuilder ret, int iArgNum)
	{
	}

	private boolean customIsSearchActive()
	{
		return false;
	}

	private void customToString(StringBuilder ret)
	{
	}

	private void customReset()
	{
	}

	private void customGetArguments(ArrayList ret)
	{
	}
}
