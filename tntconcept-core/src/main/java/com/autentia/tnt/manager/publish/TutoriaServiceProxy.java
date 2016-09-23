package com.autentia.tnt.manager.publish;

import java.util.List;

import com.autentia.tnt.businessobject.Tutorial;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.TutorialSearch;

public interface TutoriaServiceProxy  extends IProxy{
	/**
	   * List tutorials. 
	   * @param search search filter to apply
	   * @param sort sorting criteria
	   * @return the list of all tutorials sorted by requested criterion
	   */
	  public List<Tutorial> getAllEntities(TutorialSearch search, SortCriteria sort);
	  
	 
		
	  /**
	   * Insert tutorial. 
	   */
	  public void insertEntity(Tutorial tutorial);
		 
	  /**
	   * Update tutorial. 
	   */
	  public void updateEntity(Tutorial tutorial);

	  /**
	   * Delete tutorial. 
	   */
	  public void deleteEntity(Tutorial tutorial);
}
