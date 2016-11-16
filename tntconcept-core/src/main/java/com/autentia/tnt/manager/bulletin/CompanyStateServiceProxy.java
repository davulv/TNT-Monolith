package com.autentia.tnt.manager.bulletin;

import java.util.List;

import com.autentia.tnt.businessobject.CompanyState;
import com.autentia.tnt.dao.SearchCriteria;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.manager.publish.IProxy;

public interface CompanyStateServiceProxy extends IProxy{

	public List<CompanyState> getAllCompanyStates(SearchCriteria search, SortCriteria sort);
	
	public void insertCompanyState(CompanyState companyState);
	
	public void updateCompanyState(CompanyState companyState);
	
	public void deleteCompanyState(CompanyState companyState);
	

}
