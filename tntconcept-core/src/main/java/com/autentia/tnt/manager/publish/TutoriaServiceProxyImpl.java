package com.autentia.tnt.manager.publish;

import java.util.List;

import com.autentia.tnt.businessobject.Tutorial;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.TutorialSearch;
import com.autentia.tnt.util.RestUtil;

public class TutoriaServiceProxyImpl implements TutoriaServiceProxy{
	
	private PublishBeanTransformer transform = new PublishBeanTransformer();

	public String getBaseURI(){
		return "http://tnt-publications.cfapps.io";
	}

	public List<Tutorial> getAllEntities(TutorialSearch search, SortCriteria sort) {
		RestUtil<com.emc.ps.appmod.tnt.domain.publications.Tutorial> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.publications.Tutorial>(
				getBaseURI(), com.emc.ps.appmod.tnt.domain.publications.Tutorial.class);
		List<com.emc.ps.appmod.tnt.domain.publications.Tutorial>  tutList = rest.getList("/tutorial/list");
		return transform.transformTutorial(tutList);
	}

	public Tutorial getEntityById(int id) {
		RestUtil<com.emc.ps.appmod.tnt.domain.publications.Tutorial> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.publications.Tutorial>
													(getBaseURI(), com.emc.ps.appmod.tnt.domain.publications.Tutorial.class);
		 
		com.emc.ps.appmod.tnt.domain.publications.Tutorial tut = rest.get("/tutorial/"+id);
		return transform.transformTutorial(tut);
	}

	public void insertEntity(Tutorial tutorial) {
		RestUtil<com.emc.ps.appmod.tnt.domain.publications.Tutorial> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.publications.Tutorial>
														(getBaseURI(), com.emc.ps.appmod.tnt.domain.publications.Tutorial.class);

		com.emc.ps.appmod.tnt.domain.publications.Tutorial tut =  transform.transformTutorial(tutorial);
		rest.post("/tutorial", tut);
	}

	public void updateEntity(Tutorial tutorial) {
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
