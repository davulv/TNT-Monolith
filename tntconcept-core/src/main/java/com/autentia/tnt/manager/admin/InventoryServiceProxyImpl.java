package com.autentia.tnt.manager.admin;

import java.util.List;

import com.autentia.tnt.businessobject.Inventary;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.InventarySearch;
import com.autentia.tnt.util.RestUtil;

public class InventoryServiceProxyImpl implements InventoryServiceProxy {
	
	private InventoryBeanTransformer transform = new InventoryBeanTransformer();
	
	public InventoryServiceProxyImpl(){
	}
	
	public String getBaseURI(){
		return "http://tnt-utilities.cfapps.io/api/utilities";
	}

	public List<Inventary> getAllEntities(InventarySearch search, SortCriteria sort) {

		RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Inventory> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Inventory>
		(getBaseURI(), com.emc.ps.appmod.tnt.domain.utilities.Inventory.class);
		
		List<com.emc.ps.appmod.tnt.domain.utilities.Inventory> inventoryList =  rest.getList("/inventory/list");
		
		return transform.transformInventory(inventoryList);
		

	}

	public Inventary getEntityById(int id) {
		RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Inventory> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Inventory>
													(getBaseURI(), com.emc.ps.appmod.tnt.domain.utilities.Inventory.class);
		
		com.emc.ps.appmod.tnt.domain.utilities.Inventory inventory =  rest.get("/inventory/"+id);
		
		return transform.transformInventory(inventory);
		
	}

	public void insertEntity(Inventary inventary) {
		RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Inventory> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Inventory>
														(getBaseURI(), com.emc.ps.appmod.tnt.domain.utilities.Inventory.class);

		com.emc.ps.appmod.tnt.domain.utilities.Inventory inventory =  transform.transformInventory(inventary);
		rest.post("/inventory", inventory);
	}

	public void updateEntity(Inventary inventary) {
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
