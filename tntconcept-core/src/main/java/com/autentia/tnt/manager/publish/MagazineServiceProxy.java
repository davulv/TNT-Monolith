package com.autentia.tnt.manager.publish;

import java.util.List;

import com.autentia.tnt.businessobject.Magazine;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.MagazineSearch;

public interface MagazineServiceProxy extends IProxy{
	/**
	   * List magazines. 
	   * @param search search filter to apply
	   * @param sort sorting criteria
	   * @return the list of all magazines sorted by requested criterion
	   */
	  public List<Magazine> getAllEntities(MagazineSearch search, SortCriteria sort);
	  
		
	  /**
	   * Insert magazine. 
	   */
	  public void insertEntity(Magazine magazine);
		 
	  /**
	   * Update magazine. 
	   */
	  public void updateEntity(Magazine magazine);

	  /**
	   * Delete magazine. 
	   */
	  public void deleteEntity(Magazine magazine);
	  
	  

	
}
