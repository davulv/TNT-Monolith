package com.autentia.tnt.manager.publish;

import java.util.List;

import com.autentia.tnt.businessobject.Publication;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.PublicationSearch;

public interface PublicationServiceProxy {
	 /**
	   * List publications. 
	   * @param search search filter to apply
	   * @param sort sorting criteria
	   * @return the list of all publications sorted by requested criterion
	   */
	  public List<Publication> getAllEntities(PublicationSearch search, SortCriteria sort);
	  
	  /**
	   * Get publication by primary key.
	   * @return publication selected by id.
	   */
	  public com.autentia.tnt.businessobject.Publication getEntityById(int id);
		
	  /**
	   * Insert publication. 
	   */
	  public void insertEntity(Publication publication);
		 
	  /**
	   * Update publication. 
	   */
	  public void updateEntity(Publication publication);

	  /**
	   * Delete publication. 
	   */
	  public void deleteEntity(Publication publication);
}
