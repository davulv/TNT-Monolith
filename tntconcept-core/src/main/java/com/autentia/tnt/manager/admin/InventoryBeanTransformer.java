package com.autentia.tnt.manager.admin;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InventoryBeanTransformer {
	
	 /** Logger */
	  private static final Log log = LogFactory.getLog(InventoryBeanTransformer.class);
	
	public com.autentia.tnt.businessobject.Inventary transformInventory(
			com.emc.ps.appmod.tnt.domain.utilities.Inventory input){		
		com.autentia.tnt.businessobject.Inventary inventary = new com.autentia.tnt.businessobject.Inventary();
		
		//BeanUtils.copyProperties(pub, input);
		try {
			BeanUtilsBean.getInstance().copyProperties(inventary, input);
			inventary.setId(input.getId());
		} 
		catch (IllegalAccessException ex) {
			throw new RuntimeException("Error cloning ITransferObject",ex);
		} 
		catch (InvocationTargetException ex) {
			throw new RuntimeException("Error cloning ITransferObject",ex);
		}
		
		return inventary;
		
		
		
				

	}
	
	public com.emc.ps.appmod.tnt.domain.utilities.Inventory transformInventory(
			com.autentia.tnt.businessobject.Inventary input){		
		com.emc.ps.appmod.tnt.domain.utilities.Inventory inventory = new com.emc.ps.appmod.tnt.domain.utilities.Inventory();
		
		try {
			log.debug("transformInventory businessobject to domain object");
			BeanUtilsBean.getInstance().copyProperties(inventory, input);
			inventory.setInventoryType(com.emc.ps.appmod.tnt.domain.utilities.InventoryType.DESKTOP_PC);
			log.debug("transformInventory businessobject to domain object exit");
		} 
		catch (IllegalAccessException ex) {
			throw new RuntimeException("Error cloning ITransferObject",ex);
		} 
		catch (InvocationTargetException ex) {
			throw new RuntimeException("Error cloning ITransferObject",ex);
		}
		
		return inventory;
	}
	
	public List<com.autentia.tnt.businessobject.Inventary> transformInventory(
			List<com.emc.ps.appmod.tnt.domain.utilities.Inventory> input){		
		List<com.autentia.tnt.businessobject.Inventary> inventaryList = new ArrayList<com.autentia.tnt.businessobject.Inventary>();
		for(com.emc.ps.appmod.tnt.domain.utilities.Inventory inventory: input) {
			inventaryList.add(transformInventory(inventory));
		}

		
		return inventaryList;
	}
	

	
	
	
}
