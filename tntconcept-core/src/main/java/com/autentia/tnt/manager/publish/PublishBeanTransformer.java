package com.autentia.tnt.manager.publish;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.autentia.tnt.businessobject.Magazine;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.hibernate.UserDAO;

public class PublishBeanTransformer {
	
	private static final Log log = LogFactory.getLog(PublishBeanTransformer.class);
	
	private UserDAO userDao = new  UserDAO();
	
	public List<com.autentia.tnt.businessobject.Publication> transformPublication(
			List<com.emc.ps.appmod.tnt.domain.publications.Publication> input){		
		
		
		log.info("-----------in list transformer");
		List<com.autentia.tnt.businessobject.Publication> pubList = new ArrayList<com.autentia.tnt.businessobject.Publication>();
		for (com.emc.ps.appmod.tnt.domain.publications.Publication pub: input){
			log.info("-----------in list transformer ---"+pub.getName());
			pubList.add(transformPublish(pub));
		}
		return pubList;
	}
		
	
	
	public com.autentia.tnt.businessobject.Publication transformPublish(
			com.emc.ps.appmod.tnt.domain.publications.Publication input){		
		com.autentia.tnt.businessobject.Publication pub = new com.autentia.tnt.businessobject.Publication();
		
		//BeanUtils.copyProperties(pub, input);
		try
		{
			BeanUtilsBean.getInstance().copyProperties(pub, input);
			pub.setId(input.getId());
			/*Integer magazineId = input.getMagazineId();
			log.info("Magazine id from input publication in transformer : "+magazineId);
			Magazine magazine = (Magazine)magazineServiceProxy.getById(magazineId);
			log.info("Object created from magazine id : "+magazine.toString());
			pub.setMagazine(magazine);*/
			
		} 
		catch (IllegalAccessException ex)
		{
			throw new RuntimeException("Error cloning ITransferObject",ex);
		} 
		catch (InvocationTargetException ex)
		{
			throw new RuntimeException("Error cloning ITransferObject",ex);
		}
		
		return pub;
	}
	
	public com.emc.ps.appmod.tnt.domain.publications.Publication transformPublish(
			com.autentia.tnt.businessobject.Publication input){		
		com.emc.ps.appmod.tnt.domain.publications.Publication pub = new com.emc.ps.appmod.tnt.domain.publications.Publication();
		
		//BeanUtils.copyProperties(pub, input);
		try
		{
			BeanUtilsBean.getInstance().copyProperties(pub, input);
			/*Integer magazineId = input.getMagazine().getId();
			log.info("Magazine id from business objects magazine object : "+magazineId);
			pub.setMagazineId(magazineId);*/
		} 
		catch (IllegalAccessException ex)
		{
			throw new RuntimeException("Error cloning ITransferObject",ex);
		} 
		catch (InvocationTargetException ex)
		{
			throw new RuntimeException("Error cloning ITransferObject",ex);
		}
		
		return pub;
	}
	
	
	
	public com.autentia.tnt.businessobject.Magazine transformMagazine(
			com.emc.ps.appmod.tnt.domain.publications.Magazine input){		
		com.autentia.tnt.businessobject.Magazine mag = new com.autentia.tnt.businessobject.Magazine();
		
		//BeanUtils.copyProperties(mag, input);
		try
		{
			BeanUtilsBean.getInstance().copyProperties(mag, input);
			mag.setId(input.getId());
		} 
		catch (IllegalAccessException ex)
		{
			throw new RuntimeException("Error cloning ITransferObject",ex);
		} 
		catch (InvocationTargetException ex)
		{
			throw new RuntimeException("Error cloning ITransferObject",ex);
		}
		
		log.info("-------------In transformer-------"+ mag.getName() + "transformedid - "+ mag.getId());
		return mag;
	}
	
	
	
	public List<com.autentia.tnt.businessobject.Magazine> transformMagazine(
			List<com.emc.ps.appmod.tnt.domain.publications.Magazine> input){
		log.info("-----------in list transformer");
		List<com.autentia.tnt.businessobject.Magazine> magList = new ArrayList<com.autentia.tnt.businessobject.Magazine>();
		for (com.emc.ps.appmod.tnt.domain.publications.Magazine mag: input){
			log.info("-----------in list transformer ---"+mag.getName() + " id --"+ mag.getId());
			magList.add(transformMagazine(mag));
		}
		return magList;
	}
	
	public com.emc.ps.appmod.tnt.domain.publications.Magazine transformMagazine(
			com.autentia.tnt.businessobject.Magazine input){		
		com.emc.ps.appmod.tnt.domain.publications.Magazine mag = new com.emc.ps.appmod.tnt.domain.publications.Magazine();
		
		//BeanUtils.copyProperties(mag, input);
		try
		{
			BeanUtilsBean.getInstance().copyProperties(mag, input);
		} 
		catch (IllegalAccessException ex)
		{
			throw new RuntimeException("Error cloning ITransferObject",ex);
		} 
		catch (InvocationTargetException ex)
		{
			throw new RuntimeException("Error cloning ITransferObject",ex);
		}
		
		log.info("-------------In transformer-------"+ mag.getName());
		
		return mag;
	}
	
	public com.autentia.tnt.businessobject.Tutorial transformTutorial(
			com.emc.ps.appmod.tnt.domain.publications.Tutorial input){		
		com.autentia.tnt.businessobject.Tutorial tut = new com.autentia.tnt.businessobject.Tutorial();
		
		//BeanUtils.copyProperties(tut, input);
		try
		{
			BeanUtilsBean.getInstance().copyProperties(tut, input);
			tut.setId(input.getId());
			Integer userId = input.getUserId();
			log.info("UserId from microservice Tutorial : "+userId);
			User user = userDao.getById(userId);
			log.info("User object created from userid of tutorial MS : "+user.toString());
			tut.setUser(user);			
		} 
		catch (IllegalAccessException ex)
		{
			throw new RuntimeException("Error cloning ITransferObject",ex);
		} 
		catch (InvocationTargetException ex)
		{
			throw new RuntimeException("Error cloning ITransferObject",ex);
		}
		
		return tut;
	}
	
	public com.emc.ps.appmod.tnt.domain.publications.Tutorial transformTutorial(
			com.autentia.tnt.businessobject.Tutorial input){		
		com.emc.ps.appmod.tnt.domain.publications.Tutorial tut = new com.emc.ps.appmod.tnt.domain.publications.Tutorial();
		
		//BeanUtils.copyProperties(tut, input);
		try
		{
			BeanUtilsBean.getInstance().copyProperties(tut, input);
			Integer userId = input.getUser().getId();
			log.info("UserId from tutorial business object's User object : "+userId);
			tut.setUserId(userId);
		} 
		catch (IllegalAccessException ex)
		{
			throw new RuntimeException("Error cloning ITransferObject",ex);
		} 
		catch (InvocationTargetException ex)
		{
			throw new RuntimeException("Error cloning ITransferObject",ex);
		}
		
		return tut;
	}
	
	public List<com.autentia.tnt.businessobject.Tutorial> transformTutorial(
			List<com.emc.ps.appmod.tnt.domain.publications.Tutorial> input){		
		
		log.info("-----------in list transformer");
		List<com.autentia.tnt.businessobject.Tutorial> tutList = new ArrayList<com.autentia.tnt.businessobject.Tutorial>();
		for (com.emc.ps.appmod.tnt.domain.publications.Tutorial tut: input){
			log.info("-----------in list transformer ---"+tut.getName());
			tutList.add(transformTutorial(tut));
		}
		return tutList;
		
	}
}
