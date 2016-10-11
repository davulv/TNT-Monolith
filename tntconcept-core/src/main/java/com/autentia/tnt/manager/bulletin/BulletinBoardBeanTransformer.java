package com.autentia.tnt.manager.bulletin;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.BulletinBoard;

public class BulletinBoardBeanTransformer {

	private static final Log log = LogFactory.getLog(BulletinBoardBeanTransformer.class);
	
	public BulletinBoard transformBulletinBoard(com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard inputBulletinBoard) {
		
		log.info("Inside individual transformBulletinBoard returning business bulletinBoard");
		BulletinBoard outputBulletinBoard = new BulletinBoard();
		try{
			BeanUtilsBean.getInstance().copyProperties(outputBulletinBoard, inputBulletinBoard);
			log.info("After tarnsforming to business bulletinBoard : "+outputBulletinBoard.getTitle());
		}catch (IllegalAccessException ex) {
			// TODO: handle exception
			throw new RuntimeException("Error cloning ITransferObject", ex);
		} catch (InvocationTargetException ex) {
			// TODO: handle exception
			throw new RuntimeException("Error cloning ITransferObject", ex);
		}
		return outputBulletinBoard;
	}
	
	public com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard transformBulletinBoard(BulletinBoard inputBulletinBoard) {
	
		log.info("Inside individual transformBulletinBoard returning our bulletinBoard");
		com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard outputBulletinBoard = new com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard();
		try{
			BeanUtilsBean.getInstance().copyProperties(outputBulletinBoard, inputBulletinBoard);
		}catch (IllegalAccessException ex) {
			// TODO: handle exception
			throw new RuntimeException("Error cloning ITransferObject", ex);
		} catch (InvocationTargetException ex) {
			// TODO: handle exception
			throw new RuntimeException("Error cloning ITransferObject", ex);
		}
		return outputBulletinBoard;
	}
	
	public List<BulletinBoard> transformBulletinBoardList(List<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard> inputBulletinBoardList) {
		log.info("........in transformBulletinBoardList");
		List<BulletinBoard> outputBulletinBoardList = new ArrayList<BulletinBoard>();
		for (com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard inputBulletinBoard : inputBulletinBoardList) {
			log.info(".....in transformBulletinBoardList ...."+inputBulletinBoard.getTitle());
			outputBulletinBoardList.add(transformBulletinBoard(inputBulletinBoard));
		}
		return outputBulletinBoardList;
	}

	
}
