package com.autentia.tnt.manager.bulletin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.CompanyState;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.SearchCriteria;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.UserDAO;
import com.autentia.tnt.util.RestUtil;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

public class CompanyStateServiceProxyImpl implements CompanyStateServiceProxy {
	
	private static final Log log = LogFactory.getLog(CompanyStateServiceProxyImpl.class);
	
	private CompanyStateBeanTransformer transformer = new CompanyStateBeanTransformer();
	
	private CategoryServiceProxy categoryServiceProxy = new CategoryServiceProxyImpl();
	
	private UserDAO userDao = new UserDAO();

	public CompanyStateServiceProxyImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public String getBaseURI(){
		return "http://tnt-bulletin.cfapps.io/api/bulletin";
	}
	
	public CompanyState afterTransform(CompanyState companystate, com.emc.ps.appmod.tnt.domain.bulletin.CompanyState inputCompanyState) {
		/*Integer categoryId = inputbulletinBoard.getBulletinBoardCategoryId();
		log.info("category id from input BulletinBoard from MS : "+categoryId);
		BulletinBoardCategory category = (BulletinBoardCategory) categoryServiceProxy.getById(categoryId);
		log.info("Category object created from id : "+category.getName());
		outputBulletinBoard.setCategory(category);*/
		Integer userId = inputCompanyState.getUserId();
		log.info("user id from inputBulletinBoard from MS : "+userId);
		User user = userDao.getById(userId);
		log.info("User object created from userId : "+user.getAccount());
		companystate.setUser(user);
		return companystate;
	}
	
	public com.emc.ps.appmod.tnt.domain.bulletin.CompanyState afterTransform(com.emc.ps.appmod.tnt.domain.bulletin.CompanyState outputCompanyState, CompanyState inputCompanyState) {
		/*Integer categoryId = inputCompanyState.getCategory().getId();
		log.info("Category Id from input business BB : "+categoryId);
		outputBulletinBoard.setBulletinBoardCategoryId(categoryId);*/
		Integer userId = inputCompanyState.getUser().getId();
		log.info("User Id from Input business BB : "+userId);
		outputCompanyState.setUserId(userId);
		return outputCompanyState;
	}

	public Object getById(int id) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.CompanyState> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.CompanyState>
										(getBaseURI(), com.emc.ps.appmod.tnt.domain.bulletin.CompanyState.class);
		com.emc.ps.appmod.tnt.domain.bulletin.CompanyState companystate = rest.get("/companystate/"+id);
		CompanyState outputCompanyState = transformer.transformCompanyState(companystate);
		return afterTransform(outputCompanyState, companystate);
	}

	public List<CompanyState> getAllCompanyStates(SearchCriteria search, SortCriteria sort) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.CompanyState> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.CompanyState>
										(getBaseURI(), com.emc.ps.appmod.tnt.domain.bulletin.CompanyState.class);
		ClientResponse response =  rest.getList("/companystate/list");
		List<com.emc.ps.appmod.tnt.domain.bulletin.CompanyState> companyStateList = response.getEntity(
				new GenericType<List<com.emc.ps.appmod.tnt.domain.bulletin.CompanyState>>() {});
		log.info("---just before transforming--"+companyStateList.size());
		List<CompanyState> outputBulletinBoardList = new ArrayList<CompanyState>();
		for (com.emc.ps.appmod.tnt.domain.bulletin.CompanyState companyState : companyStateList) {
			CompanyState outputCompanyState = transformer.transformCompanyState(companyState);
			outputCompanyState = this.afterTransform(outputCompanyState, companyState);
			outputBulletinBoardList.add(outputCompanyState);
		}
		return outputBulletinBoardList;
	}

	public void insertCompanyState(CompanyState companyState) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.CompanyState> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.CompanyState>
										(getBaseURI(), com.emc.ps.appmod.tnt.domain.bulletin.CompanyState.class);
		com.emc.ps.appmod.tnt.domain.bulletin.CompanyState outputCompanyState = transformer.transformCompanyState(companyState);
		log.info("inside insertCompanyState...after transformCompanyState");
		outputCompanyState = this.afterTransform(outputCompanyState, companyState);
		log.info("inside insertCompanyState...after afterTransform");
		rest.post("/companystate", outputCompanyState);
	}

	public void updateCompanyState(CompanyState companyState) {
		// TODO Auto-generated method stub
		log.info("inside updateCompanyState...");
		RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.CompanyState> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.CompanyState>
		(getBaseURI(), com.emc.ps.appmod.tnt.domain.bulletin.CompanyState.class);
		com.emc.ps.appmod.tnt.domain.bulletin.CompanyState outputCompanyState = transformer.transformCompanyState(companyState);
		log.info("inside updateCompanyState...after transformCompanyState");
		outputCompanyState = this.afterTransform(outputCompanyState, companyState);
		log.info("inside updateCompanyState...after afterTransform"+ outputCompanyState.getDescription());
		try{
			rest.put("/companystate", outputCompanyState);
		}catch(Exception e){log.info("inside updateCompanyState exception...", e);}

	}

	public void deleteCompanyState(CompanyState companyState) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.CompanyState> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.CompanyState>
										(getBaseURI(), com.emc.ps.appmod.tnt.domain.bulletin.CompanyState.class);
		rest.delete("/companystate/"+companyState.getId());
	}


}
