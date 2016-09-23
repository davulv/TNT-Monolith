package com.autentia.tnt.manager.admin;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.Inventary;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.InventarySearch;
import com.autentia.tnt.util.RestUtil;
import com.autentia.tnt.util.SpringUtils;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

public class InventoryServiceProxyImpl implements InventoryServiceProxy {
	
	private InventoryBeanTransformer transform = new InventoryBeanTransformer();
	
	 /** Logger */
	  private static final Log log = LogFactory.getLog(InventoryServiceProxyImpl.class);
	
	public InventoryServiceProxyImpl(){
	}
	
	public String getBaseURI(){
		return "http://tnt-utilities.cfapps.io/api/utilities";
	}

	public List<Inventary> getAllEntities(InventarySearch search, SortCriteria sort) {

		RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Inventory> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Inventory>
		(getBaseURI(), com.emc.ps.appmod.tnt.domain.utilities.Inventory.class);
		
		ClientResponse response =  rest.getList("/inventory/list");
		List<com.emc.ps.appmod.tnt.domain.utilities.Inventory> inventoryList = response.getEntity(
				new GenericType<List<com.emc.ps.appmod.tnt.domain.utilities.Inventory>>() {});
		
		
		
		return transform.transformInventory(inventoryList);
		

	}

	public Inventary getById(int id) {
		RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Inventory> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Inventory>
													(getBaseURI(), com.emc.ps.appmod.tnt.domain.utilities.Inventory.class);
		
		com.emc.ps.appmod.tnt.domain.utilities.Inventory inventory =  rest.get("/inventory/"+id);
		
		return transform.transformInventory(inventory);
		
	}

	public void insertEntity(Inventary inventary) {
		log.info("insertEntity entry");
		
		inventary.setOwnerId(SpringUtils.getPrincipal().getId());
		RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Inventory> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Inventory>
														(getBaseURI(), com.emc.ps.appmod.tnt.domain.utilities.Inventory.class);

		com.emc.ps.appmod.tnt.domain.utilities.Inventory inventory =  transform.transformInventory(inventary);
		log.info("insertEntity transformed");
		try {
		rest.post("/inventory", inventory);
		}catch(Exception e) {
			log.info("insertEntity exception", e);
		}
	}

	public void updateEntity(Inventary inventary) {
		inventary.setOwnerId(SpringUtils.getPrincipal().getId());
		RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Inventory> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Inventory>
													(getBaseURI(), com.emc.ps.appmod.tnt.domain.utilities.Inventory.class);
		com.emc.ps.appmod.tnt.domain.utilities.Inventory inventory =  transform.transformInventory(inventary);
		rest.put("/inventory", inventory);
	}

	public void deleteEntity(Inventary inventary) {
		RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Inventory> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Inventory>
													(getBaseURI(), com.emc.ps.appmod.tnt.domain.utilities.Inventory.class);
		
		rest.delete("/inventory/"+inventary.getId());
	}

}
