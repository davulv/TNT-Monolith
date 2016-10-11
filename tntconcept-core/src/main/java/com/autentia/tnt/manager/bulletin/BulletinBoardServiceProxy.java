package com.autentia.tnt.manager.bulletin;

import java.util.List;

import com.autentia.tnt.businessobject.BulletinBoard;
import com.autentia.tnt.dao.SearchCriteria;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.manager.publish.IProxy;

public interface BulletinBoardServiceProxy extends IProxy{

	public List<BulletinBoard> getAllBulletinBoards(SearchCriteria search, SortCriteria sort);
	
	public void insertBulletinBoard(BulletinBoard bulletinBoard);
	
	public void updateBulletinBoard(BulletinBoard bulletinBoard);
	
	public void deleteBulletinBoard(BulletinBoard bulletinBoard);
	
	public Integer getNumOfMsgs(Integer categoryId);
	
}
