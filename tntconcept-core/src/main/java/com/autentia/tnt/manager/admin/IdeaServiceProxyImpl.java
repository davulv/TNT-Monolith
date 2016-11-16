package com.autentia.tnt.manager.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.Idea;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.UserDAO;
import com.autentia.tnt.dao.search.IdeaSearch;
import com.autentia.tnt.util.RestUtil;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

public class IdeaServiceProxyImpl implements IdeaServiceproxy {

private static final Log log = LogFactory.getLog(IdeaServiceProxyImpl.class);
	
	private IdeaBeanTransformer transformer = new IdeaBeanTransformer();
	
	private UserDAO userDao = new UserDAO();
	
	public IdeaServiceProxyImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public String getBaseURI(){
		return "http://tnt-edge.cfapps.io/zuul/api/bulletin";
	}
	
	public Idea afterTransform(Idea outputIdea, com.emc.ps.appmod.tnt.domain.bulletin.Idea inputIdea) {
		Integer userId = inputIdea.getUserId();
		log.info("user id from inputIdea from MS : "+userId);
		User user = userDao.getById(userId);
		log.info("User object created from userId : "+user.getAccount());
		outputIdea.setUser(user);
		return outputIdea;
	}
	
	public com.emc.ps.appmod.tnt.domain.bulletin.Idea afterTransform(com.emc.ps.appmod.tnt.domain.bulletin.Idea outputIdea, Idea inputIdea) {
		Integer userId = inputIdea.getUser().getId();
		log.info("User Id from Input business Idea : "+userId);
		outputIdea.setUserId(userId);
		return outputIdea;
	}

	public Object getById(int id) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.Idea> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.Idea>
										(getBaseURI(), com.emc.ps.appmod.tnt.domain.bulletin.Idea.class);
		com.emc.ps.appmod.tnt.domain.bulletin.Idea idea = rest.get("/suggestion/"+id);
		Idea outputIdea = transformer.transformIdea(idea);
		return afterTransform(outputIdea, idea);		
	}

	public List<Idea> getAllIdeas(IdeaSearch search, SortCriteria sort) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.Idea> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.Idea>
										(getBaseURI(), com.emc.ps.appmod.tnt.domain.bulletin.Idea.class);
		ClientResponse response =  rest.getList("/suggestion/list");
		List<com.emc.ps.appmod.tnt.domain.bulletin.Idea> ideaList = response.getEntity(
				new GenericType<List<com.emc.ps.appmod.tnt.domain.bulletin.Idea>>() {});
		log.info("---just before transforming--"+ideaList.size());
		List<Idea> outputIdeaList = new ArrayList<Idea>();
		for (com.emc.ps.appmod.tnt.domain.bulletin.Idea idea : ideaList) {
			Idea outputIdea = transformer.transformIdea(idea);
			outputIdea = this.afterTransform(outputIdea, idea);
			outputIdeaList.add(outputIdea);
		}
		return outputIdeaList;
	}

	public void insertIdea(Idea idea) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.Idea> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.Idea>
										(getBaseURI(), com.emc.ps.appmod.tnt.domain.bulletin.Idea.class);
		com.emc.ps.appmod.tnt.domain.bulletin.Idea outputIdea = transformer.transformIdea(idea);
		outputIdea = this.afterTransform(outputIdea, idea);
		rest.post("/suggestion", outputIdea);
	}

	public void updateIdea(Idea idea) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.Idea> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.Idea>
										(getBaseURI(), com.emc.ps.appmod.tnt.domain.bulletin.Idea.class);
		com.emc.ps.appmod.tnt.domain.bulletin.Idea outputIdea = transformer.transformIdea(idea);
		outputIdea = this.afterTransform(outputIdea, idea);
		rest.put("/suggestion", outputIdea);
	}

	public void deleteIdea(Idea idea) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.Idea> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.Idea>
										(getBaseURI(), com.emc.ps.appmod.tnt.domain.bulletin.Idea.class);
		rest.delete("/suggestion/"+idea.getId());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
