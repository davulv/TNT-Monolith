package com.autentia.tnt.manager.admin;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.InventaryType;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.hibernate.UserDAO;

public class InventoryBeanTransformer {
	
	 /** Logger */
	  private static final Log log = LogFactory.getLog(InventoryBeanTransformer.class);
	  /** User DAO **/
	  private UserDAO userDao = new UserDAO();
	
	public com.autentia.tnt.businessobject.Inventary transformInventory(
			com.emc.ps.appmod.tnt.domain.utilities.Inventory input){		
		com.autentia.tnt.businessobject.Inventary inventary = new com.autentia.tnt.businessobject.Inventary();
		
		//BeanUtils.copyProperties(pub, input);
		try {
			inventary.setSerialNumber(input.getSerialNumber());
			inventary.setCost(input.getCost());
			inventary.setBuyDate(input.getBuyDate());
			inventary.setDescription(input.getDescription());
			inventary.setDepartmentId(input.getDepartmentId());
			inventary.setId(input.getId());
			inventary.setLocation(input.getLocation());
			inventary.setModel(input.getModel());
			inventary.setOwnerId(input.getOwnerId());
			inventary.setProvider(input.getProvider());
			inventary.setRam(input.getRam());
			inventary.setRenting(input.getToRent());
			inventary.setSpeed(input.getSpeed());
			inventary.setStorage(input.getStorage());
			inventary.setTrademark(input.getTrademark());
			inventary.setCountry(input.getCountry());
			
			
			//BeanUtilsBean.getInstance().copyProperties(inventary, input);
			log.info("get user object with id:"+ input.getAssignedTo());
			User user = userDao.getById(input.getAssignedTo());
			log.info("assigning user object to invetary");
			inventary.setAssignedTo(user);
			
			inventary.setType(InventaryType.valueOf(input.getInventoryType()));
			inventary.setId(input.getId());
		} 
		catch (Exception ex) {
			throw new RuntimeException("Error cloning ITransferObject",ex);
		} 
		/*catch (InvocationTargetException ex) {
			throw new RuntimeException("Error cloning ITransferObject",ex);
		}*/
		
		return inventary;
		
		
		
				

	}
	
	public com.emc.ps.appmod.tnt.domain.utilities.Inventory transformInventory(
			com.autentia.tnt.businessobject.Inventary input){		
		com.emc.ps.appmod.tnt.domain.utilities.Inventory inventory = new com.emc.ps.appmod.tnt.domain.utilities.Inventory();
		
		try {
			log.info("transformInventory businessobject to domain object");
			BeanUtilsBean.getInstance().copyProperties(inventory, input);
			inventory.setInventoryType(input.getType().toString());
			inventory.setAssignedTo(input.getAssignedTo().getId());
			log.info("transformInventory businessobject to domain object exit with id"+ inventory.getId());
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
	
	public List<com.autentia.tnt.businessobject.InventaryType> transformInventoryType(
			List<String> input){		
		List<com.autentia.tnt.businessobject.InventaryType> inventaryTypeList = new ArrayList<com.autentia.tnt.businessobject.InventaryType>();
		for(String inventoryType: input) {
			inventaryTypeList.add(InventaryType.valueOf(inventoryType));

		}

		
		return inventaryTypeList;
	}

	
	
	
}
