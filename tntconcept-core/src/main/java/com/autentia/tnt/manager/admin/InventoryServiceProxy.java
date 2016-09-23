package com.autentia.tnt.manager.admin;

import java.util.List;

import com.autentia.tnt.businessobject.Inventary;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.InventarySearch;
import com.autentia.tnt.manager.publish.IProxy;

public interface InventoryServiceProxy extends IProxy{
	 /**
	   * List publications. 
	   * @param search search filter to apply
	   * @param sort sorting criteria
	   * @return the list of all publications sorted by requested criterion
	   */
	  public List<Inventary> getAllEntities(InventarySearch search, SortCriteria sort);
	  
			
	  /**
	   * Insert publication. 
	   */
	  public void insertEntity(Inventary Inventary);
		 
	  /**
	   * Update publication. 
	   */
	  public void updateEntity(Inventary Inventary);

	  /**
	   * Delete publication. 
	   */
	  public void deleteEntity(Inventary Inventary);
}
