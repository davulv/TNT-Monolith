package com.autentia.tnt.manager.bulletin;

import java.util.List;

import com.autentia.tnt.businessobject.BulletinBoardCategory;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.BulletinBoardCategorySearch;
import com.autentia.tnt.manager.publish.IProxy;

public interface CategoryServiceProxy extends IProxy{

	public List<BulletinBoardCategory> getAllCategories(BulletinBoardCategorySearch search, SortCriteria sort);
	
	//public BulletinBoardCategory getCategoryById(Integer id); since present in Iproxy
	
	public void insertCategory(BulletinBoardCategory category);
	
	public void updateCategory(BulletinBoardCategory category);
	
	public void deleteCategory(BulletinBoardCategory category);
	
}
