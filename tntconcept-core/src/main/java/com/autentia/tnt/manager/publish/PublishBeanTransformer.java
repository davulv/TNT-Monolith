package com.autentia.tnt.manager.publish;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

public class PublishBeanTransformer {
	
	private static final Log log = LogFactory.getLog(PublishBeanTransformer.class);
	
	public com.autentia.tnt.businessobject.Publication transformPublish(
			com.emc.ps.appmod.tnt.domain.publications.Publication input){		
		com.autentia.tnt.businessobject.Publication pub = new com.autentia.tnt.businessobject.Publication();
		
		//BeanUtils.copyProperties(pub, input);
		try
		{
			BeanUtilsBean.getInstance().copyProperties(pub, input);
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
}
