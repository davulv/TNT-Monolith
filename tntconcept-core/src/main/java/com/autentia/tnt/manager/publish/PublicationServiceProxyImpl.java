package com.autentia.tnt.manager.publish;

import java.util.List;

import com.autentia.tnt.businessobject.Publication;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.PublicationSearch;
import com.autentia.tnt.util.RestUtil;

public class PublicationServiceProxyImpl implements PublicationServiceProxy {
	
	private PublishBeanTransformer transform = new PublishBeanTransformer();
	
	public PublicationServiceProxyImpl(){
	}
	
	public String getBaseURI(){
		return "";
	}

	public List<Publication> getAllEntities(PublicationSearch search, SortCriteria sort) {
		return null;
	}

	public Publication getEntityById(int id) {
		RestUtil<com.emc.ps.appmod.tnt.domain.publications.Publication> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.publications.Publication>
													(getBaseURI(), com.emc.ps.appmod.tnt.domain.publications.Publication.class);
		
		com.emc.ps.appmod.tnt.domain.publications.Publication pub =  rest.get("/publication/"+id);
		
		return transform.transformPublish(pub);
		
	}

	public void insertEntity(Publication publication) {
		RestUtil<com.emc.ps.appmod.tnt.domain.publications.Publication> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.publications.Publication>
														(getBaseURI(), com.emc.ps.appmod.tnt.domain.publications.Publication.class);

		com.emc.ps.appmod.tnt.domain.publications.Publication pub =  transform.transformPublish(publication);
		rest.post("/publication", pub);
	}

	public void updateEntity(Publication publication) {
		RestUtil<com.emc.ps.appmod.tnt.domain.publications.Publication> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.publications.Publication>
													(getBaseURI(), com.emc.ps.appmod.tnt.domain.publications.Publication.class);
		com.emc.ps.appmod.tnt.domain.publications.Publication pub =  transform.transformPublish(publication);
		rest.put("/publication", pub);
	}

	public void deleteEntity(Publication publication) {
		RestUtil<com.emc.ps.appmod.tnt.domain.publications.Publication> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.publications.Publication>
													(getBaseURI(), com.emc.ps.appmod.tnt.domain.publications.Publication.class);
		
		rest.delete("/publication/"+publication.getId());
	}

}
