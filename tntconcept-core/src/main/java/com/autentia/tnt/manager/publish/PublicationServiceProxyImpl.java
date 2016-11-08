package com.autentia.tnt.manager.publish;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.Magazine;
import com.autentia.tnt.businessobject.Publication;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.PublicationSearch;
import com.autentia.tnt.util.RestUtil;
import com.autentia.tnt.util.SpringUtils;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

public class PublicationServiceProxyImpl implements PublicationServiceProxy {
	
	private PublishBeanTransformer transform = new PublishBeanTransformer();
	
	private static final Log log = LogFactory.getLog(PublicationServiceProxyImpl.class);
	
	private MagazineServiceProxy magazineServiceProxy = new MagazineServiceProxyImpl();
	
	public PublicationServiceProxyImpl(){
		
	}
	
	public String getBaseURI(){
		return "http://tnt-publications.cfapps.io/api/publish";
	}
	
	public Publication afterTransformInitialization(Publication outputPublication, com.emc.ps.appmod.tnt.domain.publications.Publication inputPublication){
		Integer magazineId = inputPublication.getMagazineId();
		log.info("Magazine id from input publication from MS : "+magazineId);
		Magazine magazine = (Magazine)magazineServiceProxy.getById(magazineId);
		log.info("Object created from magazine id : "+magazine.toString());
		outputPublication.setMagazine(magazine);
		return outputPublication;
	}
	
	public com.emc.ps.appmod.tnt.domain.publications.Publication afterTransformInitialization(com.emc.ps.appmod.tnt.domain.publications.Publication outputPublication, Publication inputPublication){
		Integer magazineId = inputPublication.getMagazine().getId();
		log.info("Magazine id from business objects magazine object : "+magazineId);
		outputPublication.setMagazineId(magazineId);
		return outputPublication;
	}
	
	public List<Publication> afterTransformListInitialization(List<Publication> outputList, List<com.emc.ps.appmod.tnt.domain.publications.Publication> inputList) {
		for (Publication outputPublication : outputList) {
			for (com.emc.ps.appmod.tnt.domain.publications.Publication inputPublication : inputList) {
				if(inputPublication.getId() == outputPublication.getId()) {
					outputPublication = afterTransformInitialization(outputPublication, inputPublication);
				}
			}
		}
		return outputList;
	}
	
	public List<Publication> getAllEntities(PublicationSearch search, SortCriteria sort) {
		RestUtil<com.emc.ps.appmod.tnt.domain.publications.Publication> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.publications.Publication>(
				getBaseURI(), com.emc.ps.appmod.tnt.domain.publications.Publication.class);
		ClientResponse response =  rest.getList("/publication/list");
		List<com.emc.ps.appmod.tnt.domain.publications.Publication> pubList = response.getEntity(
				new GenericType<List<com.emc.ps.appmod.tnt.domain.publications.Publication>>() {});
		List<Publication> outputList = transform.transformPublication(pubList);
		return this.afterTransformListInitialization(outputList, pubList);
	}

	public Publication getById(int id) {
		RestUtil<com.emc.ps.appmod.tnt.domain.publications.Publication> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.publications.Publication>
													(getBaseURI(), com.emc.ps.appmod.tnt.domain.publications.Publication.class);
		
		com.emc.ps.appmod.tnt.domain.publications.Publication pub =  rest.get("/publication/"+id);
		
		Publication outputPublication = transform.transformPublish(pub);
		return this.afterTransformInitialization(outputPublication, pub);
		
	}

	public void insertEntity(Publication publication) {
		publication.setOwnerId(SpringUtils.getPrincipal().getId());
		RestUtil<com.emc.ps.appmod.tnt.domain.publications.Publication> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.publications.Publication>
														(getBaseURI(), com.emc.ps.appmod.tnt.domain.publications.Publication.class);

		com.emc.ps.appmod.tnt.domain.publications.Publication pub =  transform.transformPublish(publication);
		pub = this.afterTransformInitialization(pub, publication);
		rest.post("/publication", pub);
	}

	public void updateEntity(Publication publication) {
		publication.setOwnerId(SpringUtils.getPrincipal().getId());
		RestUtil<com.emc.ps.appmod.tnt.domain.publications.Publication> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.publications.Publication>
													(getBaseURI(), com.emc.ps.appmod.tnt.domain.publications.Publication.class);
		com.emc.ps.appmod.tnt.domain.publications.Publication pub =  transform.transformPublish(publication);
		pub = this.afterTransformInitialization(pub, publication);
		rest.put("/publication", pub);
	}

	public void deleteEntity(Publication publication) {
		RestUtil<com.emc.ps.appmod.tnt.domain.publications.Publication> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.publications.Publication>
													(getBaseURI(), com.emc.ps.appmod.tnt.domain.publications.Publication.class);
		
		rest.delete("/publication/"+publication.getId());
	}

}
