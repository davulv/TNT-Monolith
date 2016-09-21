package com.autentia.tnt.manager.publish;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.Magazine;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.MagazineSearch;
import com.autentia.tnt.util.RestUtil;

public class MagazineServiceProxyImpl implements MagazineServiceProxy{
	
	 private static final Log log = LogFactory.getLog(MagazineServiceProxyImpl.class);
	
	private PublishBeanTransformer transform = new PublishBeanTransformer();

	public String getBaseURI(){
		return "http://tnt-publications.cfapps.io";
	}

	public List<Magazine> getAllEntities(MagazineSearch search, SortCriteria sort) {
		RestUtil<com.emc.ps.appmod.tnt.domain.publications.Magazine> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.publications.Magazine>(
																			getBaseURI(), com.emc.ps.appmod.tnt.domain.publications.Magazine.class);
		List<com.emc.ps.appmod.tnt.domain.publications.Magazine>  magList = rest.getList("/magazine/list");
		return transform.transformMagazine(magList);
	}

	public Magazine getEntityById(int id) {
		RestUtil<com.emc.ps.appmod.tnt.domain.publications.Magazine> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.publications.Magazine>
													(getBaseURI(), com.emc.ps.appmod.tnt.domain.publications.Magazine.class);
		
		com.emc.ps.appmod.tnt.domain.publications.Magazine mag =  rest.get("/magazine/"+id);
		return transform.transformMagazine(mag);
	}

	public void insertEntity(Magazine magazine) {
		log.info("--------------In create Mag----"+magazine.getName());
		RestUtil<com.emc.ps.appmod.tnt.domain.publications.Magazine> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.publications.Magazine>
														(getBaseURI(), com.emc.ps.appmod.tnt.domain.publications.Magazine.class);

		com.emc.ps.appmod.tnt.domain.publications.Magazine mag =  transform.transformMagazine(magazine);
		log.info("--------------In create Mag after transformation----"+mag.getName());
		rest.post("/magazine", mag);
	}

	public void updateEntity(Magazine magazine) {
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
