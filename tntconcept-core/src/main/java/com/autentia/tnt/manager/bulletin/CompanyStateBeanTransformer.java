package com.autentia.tnt.manager.bulletin;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.CompanyState;

public class CompanyStateBeanTransformer {

	private static final Log log = LogFactory.getLog(CompanyStateBeanTransformer.class);
	
	public CompanyState transformCompanyState(com.emc.ps.appmod.tnt.domain.bulletin.CompanyState inputBean) {
		
		log.info("Inside individual transformCompanyStated returning business bulletinBoard");
		CompanyState outputBean = new CompanyState();
		try{
			BeanUtilsBean.getInstance().copyProperties(outputBean, inputBean);
			log.info("After tarnsforming to business CompanyState : "+outputBean.getDescription());
		}catch (IllegalAccessException ex) {
			// TODO: handle exception
			throw new RuntimeException("Error cloning ITransferObject", ex);
		} catch (InvocationTargetException ex) {
			// TODO: handle exception
			throw new RuntimeException("Error cloning ITransferObject", ex);
		}
		return outputBean;
	}
	
	public com.emc.ps.appmod.tnt.domain.bulletin.CompanyState transformCompanyState(CompanyState inputCompanyState) {
	
		log.info("Inside individual transformCompanyState returning our CompanyState");
		com.emc.ps.appmod.tnt.domain.bulletin.CompanyState outputCompanyState = new com.emc.ps.appmod.tnt.domain.bulletin.CompanyState();
		try{
			BeanUtilsBean.getInstance().copyProperties(outputCompanyState, inputCompanyState);
		}catch (IllegalAccessException ex) {
			// TODO: handle exception
			throw new RuntimeException("Error cloning ITransferObject", ex);
		} catch (InvocationTargetException ex) {
			// TODO: handle exception
			throw new RuntimeException("Error cloning ITransferObject", ex);
		}
		return outputCompanyState;
	}
	
	public List<CompanyState> transformCompanyStateList(List<com.emc.ps.appmod.tnt.domain.bulletin.CompanyState> inputCompanyStateList) {
		log.info("........in transformCompanyStateList");
		List<CompanyState> outputBulletinBoardList = new ArrayList<CompanyState>();
		for (com.emc.ps.appmod.tnt.domain.bulletin.CompanyState inputCompanyState : inputCompanyStateList) {
			log.info(".....in transformCompanyStateList ...."+inputCompanyState.getDescription());
			outputBulletinBoardList.add(transformCompanyState(inputCompanyState));
		}
		return outputBulletinBoardList;
	}

	
}
