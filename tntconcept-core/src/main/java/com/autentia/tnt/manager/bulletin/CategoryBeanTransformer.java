package com.autentia.tnt.manager.bulletin;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.BulletinBoardCategory;

public class CategoryBeanTransformer {
	
	private static final Log log = LogFactory.getLog(CategoryBeanTransformer.class);
	
	public BulletinBoardCategory transformCategory(com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory inputCategory) {
		
		log.info("Inside individual categoryTransform returning business category");
		BulletinBoardCategory outputCategory = new BulletinBoardCategory();
		try{
			BeanUtilsBean.getInstance().copyProperties(outputCategory, inputCategory);
			log.info("After tarnsforming to business category : "+outputCategory.getName());
		}catch (IllegalAccessException ex) {
			// TODO: handle exception
			throw new RuntimeException("Error cloning ITransferObject", ex);
		} catch (InvocationTargetException ex) {
			// TODO: handle exception
			throw new RuntimeException("Error cloning ITransferObject", ex);
		}
		return outputCategory;
	}
	
	public com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory transformCategory(BulletinBoardCategory inputCategory) {
	
		log.info("Inside individual categoryTransform returning our category");
		com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory outputCategory = new com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory();
		try{
			BeanUtilsBean.getInstance().copyProperties(outputCategory, inputCategory);
		}catch (IllegalAccessException ex) {
			// TODO: handle exception
			throw new RuntimeException("Error cloning ITransferObject", ex);
		} catch (InvocationTargetException ex) {
			// TODO: handle exception
			throw new RuntimeException("Error cloning ITransferObject", ex);
		}
		return outputCategory;
	}
	
	public List<BulletinBoardCategory> transformCategoryList(List<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory> inputCategoryList) {
		log.info("........in categorylist transformer");
		List<BulletinBoardCategory> outputCategoryList = new ArrayList<BulletinBoardCategory>();
		for (com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory inputCategory : inputCategoryList) {
			log.info(".....in categoryList transformer...."+inputCategory.getName());
			outputCategoryList.add(transformCategory(inputCategory));
		}
		return outputCategoryList;
	}

}
