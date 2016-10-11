package com.autentia.tnt.manager.admin;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.Idea;

public class IdeaBeanTransformer {

	private static final Log log = LogFactory.getLog(IdeaBeanTransformer.class);
	
	public IdeaBeanTransformer() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	
	public Idea transformIdea(com.emc.ps.appmod.tnt.domain.bulletin.Idea inputIdea) {
		
		log.info("Inside individual transformIdea returning business Idea");
		Idea outputIdea = new Idea();
		try{
			BeanUtilsBean.getInstance().copyProperties(outputIdea, inputIdea);
			log.info("After tarnsforming to business idea : "+outputIdea.getName());
		}catch (IllegalAccessException ex) {
			// TODO: handle exception
			throw new RuntimeException("Error cloning ITransferObject", ex);
		} catch (InvocationTargetException ex) {
			// TODO: handle exception
			throw new RuntimeException("Error cloning ITransferObject", ex);
		}
		return outputIdea;
	}
	
	public com.emc.ps.appmod.tnt.domain.bulletin.Idea transformIdea(Idea inputIdea) {
	
		log.info("Inside individual transformIdea returning our Idea");
		com.emc.ps.appmod.tnt.domain.bulletin.Idea outputIdea = new com.emc.ps.appmod.tnt.domain.bulletin.Idea();
		try{
			BeanUtilsBean.getInstance().copyProperties(outputIdea, inputIdea);
		}catch (IllegalAccessException ex) {
			// TODO: handle exception
			throw new RuntimeException("Error cloning ITransferObject", ex);
		} catch (InvocationTargetException ex) {
			// TODO: handle exception
			throw new RuntimeException("Error cloning ITransferObject", ex);
		}
		return outputIdea;
	}
	
	public List<Idea> transformIdeaList(List<com.emc.ps.appmod.tnt.domain.bulletin.Idea> inputIdeaList) {
		log.info("........in transformIdeaList");
		List<Idea> outputIdeaList = new ArrayList<Idea>();
		for (com.emc.ps.appmod.tnt.domain.bulletin.Idea inputIdea : inputIdeaList) {
			log.info(".....in transformIdeaList ...."+inputIdea.getName());
			outputIdeaList.add(transformIdea(inputIdea));
		}
		return outputIdeaList;
	}


}
