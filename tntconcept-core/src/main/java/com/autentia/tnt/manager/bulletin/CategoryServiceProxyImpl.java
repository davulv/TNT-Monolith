package com.autentia.tnt.manager.bulletin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.BulletinBoardCategory;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.BulletinBoardCategorySearch;
import com.autentia.tnt.util.RestUtil;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

public class CategoryServiceProxyImpl implements CategoryServiceProxy {
	
	private static final Log log = LogFactory.getLog(CategoryServiceProxyImpl.class);
	
	private CategoryBeanTransformer transformer = new CategoryBeanTransformer();
	
	//private BulletinBoardServiceProxy bulletinBoardServiceProxy = new BulletinBoardServiceproxyImpl();

	public CategoryServiceProxyImpl() {
		// TODO Auto-generated constructor stub
	}

	public String getBaseURI(){
		return "http://tnt-edge.cfapps.io/zuul/api/bulletin";
	}
	
	public BulletinBoardCategory afterTransform(BulletinBoardCategory outputCategory, com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory inputCategory) {
		/*Integer categoryId = inputCategory.getId();
		log.info("category id from input BulletinBoardCategory from MS : "+categoryId);
		Integer numOfMsgs = bulletinBoardServiceProxy.getNumOfMsgs(categoryId);
		log.info("Number of msgs from BB of MS : "+numOfMsgs);*/
		log.info(".... in afterTransform of CSPI : "+outputCategory.getName());
		outputCategory.setNumMessages(1);
		return outputCategory;
	}
	
	public com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory afterTransform(com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory outputCategory, BulletinBoardCategory inputCategory) {
		//Nothing to be done here as numOfMsgs is not in BBC of MS
		return outputCategory;
	}
	
	public Object getById(int id) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory>
												(getBaseURI(), com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory.class);
		com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory category = rest.get("/category/"+id);
		BulletinBoardCategory outputCategory = transformer.transformCategory(category);
		return this.afterTransform(outputCategory, category);
	}

	public List<BulletinBoardCategory> getAllCategories(BulletinBoardCategorySearch search, SortCriteria sort) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory>
												(getBaseURI(), com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory.class);
		ClientResponse response = rest.getList("/category/list");
		List<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory> categoryList = response.getEntity(
				new GenericType<List<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory>>() {});
		log.info("---just before transforming--"+categoryList.size());
		List<BulletinBoardCategory> outputCategoryList = new ArrayList<BulletinBoardCategory>();
		for (com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory category : categoryList) {
			BulletinBoardCategory outputCategory = transformer.transformCategory(category);
			outputCategory = this.afterTransform(outputCategory, category);
			outputCategoryList.add(outputCategory);
		}
		return outputCategoryList;
	}

	public void insertCategory(BulletinBoardCategory category) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory>
												(getBaseURI(), com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory.class);
		com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory outputCategory = transformer.transformCategory(category);
		outputCategory = this.afterTransform(outputCategory, category);
		rest.post("/category", outputCategory);
	}

	public void updateCategory(BulletinBoardCategory category) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory>
												(getBaseURI(), com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory.class);
		com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory outputCategory = transformer.transformCategory(category);
		outputCategory = this.afterTransform(outputCategory, category);
		rest.put("/category", outputCategory);

	}

	public void deleteCategory(BulletinBoardCategory category) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory>
												(getBaseURI(), com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoardCategory.class);
		rest.delete("/category/"+category.getId());

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
