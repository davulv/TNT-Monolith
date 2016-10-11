package com.autentia.tnt.manager.admin;

import java.util.List;

import com.autentia.tnt.businessobject.Idea;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.IdeaSearch;
import com.autentia.tnt.manager.publish.IProxy;

public interface IdeaServiceproxy extends IProxy{

	public List<Idea> getAllIdeas(IdeaSearch search, SortCriteria sort);
	
	public void insertIdea(Idea idea);
	
	public void updateIdea(Idea idea);
	
	public void deleteIdea(Idea idea);
	
}
