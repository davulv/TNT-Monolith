package com.autentia.tnt.manager.bulletin;

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
	
	private CategoryBeanTransformer transform = new CategoryBeanTransformer();

	public CategoryServiceProxyImpl() {
		// TODO Auto-generated constructor stub
	}

	public String getBaseURI(){
		return "http://localhost:8085";
	}
	
	public Object getById(int id) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.Category> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.Category>
												(getBaseURI(), com.emc.ps.appmod.tnt.domain.bulletin.Category.class);
		com.emc.ps.appmod.tnt.domain.bulletin.Category category = rest.get("/category/"+id);
		return transform.transformCategory(category);
	}

	public List<BulletinBoardCategory> getAllCategories(BulletinBoardCategorySearch search, SortCriteria sort) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.Category> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.Category>
												(getBaseURI(), com.emc.ps.appmod.tnt.domain.bulletin.Category.class);
		ClientResponse response = rest.getList("/category/list");
		List<com.emc.ps.appmod.tnt.domain.bulletin.Category> categoryList = response.getEntity(
				new GenericType<List<com.emc.ps.appmod.tnt.domain.bulletin.Category>>() {});
		log.info("---just before transforming--"+categoryList.size());
		List<BulletinBoardCategory> outputCategoryList = transform.transformCategoryList(categoryList);
		log.info("---after transforming---"+outputCategoryList.size());
		return outputCategoryList;
	}

	public void insertCategory(BulletinBoardCategory category) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.Category> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.Category>
												(getBaseURI(), com.emc.ps.appmod.tnt.domain.bulletin.Category.class);
		com.emc.ps.appmod.tnt.domain.bulletin.Category c = transform.transformCategory(category);
		rest.post("/category", c);
	}

	public void updateCategory(BulletinBoardCategory category) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.Category> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.Category>
												(getBaseURI(), com.emc.ps.appmod.tnt.domain.bulletin.Category.class);
		com.emc.ps.appmod.tnt.domain.bulletin.Category c = transform.transformCategory(category);
		rest.put("/category", c);

	}

	public void deleteCategory(BulletinBoardCategory category) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.Category> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.Category>
												(getBaseURI(), com.emc.ps.appmod.tnt.domain.bulletin.Category.class);
		rest.delete("/category/"+category.getId());

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
