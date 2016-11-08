package com.autentia.tnt.manager.publish;

import java.util.List;

import com.autentia.tnt.businessobject.Tutorial;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.TutorialSearch;
import com.autentia.tnt.util.RestUtil;
import com.autentia.tnt.util.SpringUtils;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

public class TutoriaServiceProxyImpl implements TutoriaServiceProxy{
	
	private PublishBeanTransformer transform = new PublishBeanTransformer();

	public String getBaseURI(){
		return "http://tnt-publications.cfapps.io/api/publish";
	}

	public List<Tutorial> getAllEntities(TutorialSearch search, SortCriteria sort) {
		RestUtil<com.emc.ps.appmod.tnt.domain.publications.Tutorial> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.publications.Tutorial>(
				getBaseURI(), com.emc.ps.appmod.tnt.domain.publications.Tutorial.class);
		ClientResponse response =  rest.getList("/tutorial/list");
		List<com.emc.ps.appmod.tnt.domain.publications.Tutorial> tutList = response.getEntity(
				new GenericType<List<com.emc.ps.appmod.tnt.domain.publications.Tutorial>>() {});
		return transform.transformTutorial(tutList);
	}

	public Tutorial getById(int id) {
		RestUtil<com.emc.ps.appmod.tnt.domain.publications.Tutorial> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.publications.Tutorial>
													(getBaseURI(), com.emc.ps.appmod.tnt.domain.publications.Tutorial.class);
		 
		com.emc.ps.appmod.tnt.domain.publications.Tutorial tut = rest.get("/tutorial/"+id);
		return transform.transformTutorial(tut);
	}

	public void insertEntity(Tutorial tutorial) {
		tutorial.setOwnerId(SpringUtils.getPrincipal().getId());
		RestUtil<com.emc.ps.appmod.tnt.domain.publications.Tutorial> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.publications.Tutorial>
														(getBaseURI(), com.emc.ps.appmod.tnt.domain.publications.Tutorial.class);

		com.emc.ps.appmod.tnt.domain.publications.Tutorial tut =  transform.transformTutorial(tutorial);
		rest.post("/tutorial", tut);
	}

	public void updateEntity(Tutorial tutorial) {
		tutorial.setOwnerId(SpringUtils.getPrincipal().getId());
		RestUtil<com.emc.ps.appmod.tnt.domain.publications.Tutorial> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.publications.Tutorial>
													(getBaseURI(), com.emc.ps.appmod.tnt.domain.publications.Tutorial.class);
		com.emc.ps.appmod.tnt.domain.publications.Tutorial tut =  transform.transformTutorial(tutorial);
		rest.put("/tutorial", tut);
	}

	public void deleteEntity(Tutorial tutorial) {
		RestUtil<com.emc.ps.appmod.tnt.domain.publications.Tutorial> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.publications.Tutorial>
													(getBaseURI(), com.emc.ps.appmod.tnt.domain.publications.Tutorial.class);
		
		rest.delete("/tutorial/"+tutorial.getId());
	}

}
