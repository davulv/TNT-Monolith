package com.autentia.tnt.manager.publish;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.Magazine;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.MagazineSearch;
import com.autentia.tnt.util.RestUtil;
import com.autentia.tnt.util.SpringUtils;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

public class MagazineServiceProxyImpl implements MagazineServiceProxy{
	
	 private static final Log log = LogFactory.getLog(MagazineServiceProxyImpl.class);
	
	private PublishBeanTransformer transform = new PublishBeanTransformer();

	public String getBaseURI(){
		return "http://tnt-edge.cfapps.io/zuul/api/publish";
	}

	public List<Magazine> getAllEntities(MagazineSearch search, SortCriteria sort) {
		log.info("In List------");
		RestUtil<com.emc.ps.appmod.tnt.domain.publications.Magazine> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.publications.Magazine>(
																			getBaseURI(), com.emc.ps.appmod.tnt.domain.publications.Magazine.class);
		log.info("In List------before rest");
		ClientResponse response =  rest.getList("/magazine/list");
		List<com.emc.ps.appmod.tnt.domain.publications.Magazine> magList = response.getEntity(
				new GenericType<List<com.emc.ps.appmod.tnt.domain.publications.Magazine>>() {});
		log.info("In List------after rest"+ magList.size());
		return transform.transformMagazine(magList);
	}

	public Magazine getById(int id) {
		RestUtil<com.emc.ps.appmod.tnt.domain.publications.Magazine> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.publications.Magazine>
													(getBaseURI(), com.emc.ps.appmod.tnt.domain.publications.Magazine.class);
		
		com.emc.ps.appmod.tnt.domain.publications.Magazine mag =  rest.get("/magazine/"+id);
		return transform.transformMagazine(mag);
	}

	public void insertEntity(Magazine magazine) {
		log.info("--------------In create Mag----"+magazine.getName());
		magazine.setOwnerId(SpringUtils.getPrincipal().getId());
		RestUtil<com.emc.ps.appmod.tnt.domain.publications.Magazine> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.publications.Magazine>
														(getBaseURI(), com.emc.ps.appmod.tnt.domain.publications.Magazine.class);

		com.emc.ps.appmod.tnt.domain.publications.Magazine mag =  transform.transformMagazine(magazine);
		log.info("--------------In create Mag after transformation----"+mag.getName() + "owner Id --"+ mag.getOwnerId());
		rest.post("/magazine", mag);
	}

	public void updateEntity(Magazine magazine) {
		magazine.setOwnerId(SpringUtils.getPrincipal().getId());
		RestUtil<com.emc.ps.appmod.tnt.domain.publications.Magazine> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.publications.Magazine>
													(getBaseURI(), com.emc.ps.appmod.tnt.domain.publications.Magazine.class);
		com.emc.ps.appmod.tnt.domain.publications.Magazine mag =  transform.transformMagazine(magazine);
		rest.put("/magazine", mag);
	}

	public void deleteEntity(Magazine magazine) {
		RestUtil<com.emc.ps.appmod.tnt.domain.publications.Magazine> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.publications.Magazine>
													(getBaseURI(), com.emc.ps.appmod.tnt.domain.publications.Magazine.class);
		
		rest.delete("/magazine/"+magazine.getId());
	}

}
