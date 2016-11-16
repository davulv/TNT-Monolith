package com.autentia.tnt.manager.bulletin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.BulletinBoard;
import com.autentia.tnt.businessobject.BulletinBoardCategory;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.SearchCriteria;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.UserDAO;
import com.autentia.tnt.util.RestUtil;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

public class BulletinBoardServiceproxyImpl implements BulletinBoardServiceProxy {
	
	private static final Log log = LogFactory.getLog(BulletinBoardServiceproxyImpl.class);
	
	private BulletinBoardBeanTransformer transformer = new BulletinBoardBeanTransformer();
	
	private CategoryServiceProxy categoryServiceProxy = new CategoryServiceProxyImpl();
	
	private UserDAO userDao = new UserDAO();

	public BulletinBoardServiceproxyImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public String getBaseURI(){
		return "http://tnt-edge.cfapps.io/zuul/api/bulletin";
	}
	
	public BulletinBoard afterTransform(BulletinBoard outputBulletinBoard, com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard inputbulletinBoard) {
		Integer categoryId = inputbulletinBoard.getBulletinBoardCategoryId();
		log.info("category id from input BulletinBoard from MS : "+categoryId);
		BulletinBoardCategory category = (BulletinBoardCategory) categoryServiceProxy.getById(categoryId);
		log.info("Category object created from id : "+category.getName());
		outputBulletinBoard.setCategory(category);
		Integer userId = inputbulletinBoard.getUserId();
		log.info("user id from inputBulletinBoard from MS : "+userId);
		User user = userDao.getById(userId);
		log.info("User object created from userId : "+user.getAccount());
		outputBulletinBoard.setUser(user);
		return outputBulletinBoard;
	}
	
	public com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard afterTransform(com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard outputBulletinBoard, BulletinBoard inputBulletinBoard) {
		Integer categoryId = inputBulletinBoard.getCategory().getId();
		log.info("Category Id from input business BB : "+categoryId);
		outputBulletinBoard.setBulletinBoardCategoryId(categoryId);
		Integer userId = inputBulletinBoard.getUser().getId();
		log.info("User Id from Input business BB : "+userId);
		outputBulletinBoard.setUserId(userId);
		return outputBulletinBoard;
	}

	public Object getById(int id) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard>
										(getBaseURI(), com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard.class);
		com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard bulletinBoard = rest.get("/message/"+id);
		BulletinBoard outputBulletinBoard = transformer.transformBulletinBoard(bulletinBoard);
		return afterTransform(outputBulletinBoard, bulletinBoard);
	}

	public List<BulletinBoard> getAllBulletinBoards(SearchCriteria search, SortCriteria sort) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard>
										(getBaseURI(), com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard.class);
		ClientResponse response =  rest.getList("/message/list");
		List<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard> bulletinBoardList = response.getEntity(
				new GenericType<List<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard>>() {});
		log.info("---just before transforming--"+bulletinBoardList.size());
		List<BulletinBoard> outputBulletinBoardList = new ArrayList<BulletinBoard>();
		for (com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard bulletinBoard : bulletinBoardList) {
			BulletinBoard outputBulletinBoard = transformer.transformBulletinBoard(bulletinBoard);
			outputBulletinBoard = this.afterTransform(outputBulletinBoard, bulletinBoard);
			outputBulletinBoardList.add(outputBulletinBoard);
		}
		return outputBulletinBoardList;
	}

	public void insertBulletinBoard(BulletinBoard bulletinBoard) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard>
										(getBaseURI(), com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard.class);
		com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard outputBulletinBoard = transformer.transformBulletinBoard(bulletinBoard);
		outputBulletinBoard = this.afterTransform(outputBulletinBoard, bulletinBoard);
		rest.post("/message", outputBulletinBoard);
	}

	public void updateBulletinBoard(BulletinBoard bulletinBoard) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard>
										(getBaseURI(), com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard.class);
		com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard outputBulletinBoard = transformer.transformBulletinBoard(bulletinBoard);
		outputBulletinBoard = this.afterTransform(outputBulletinBoard, bulletinBoard);
		rest.put("/message", outputBulletinBoard);

	}

	public void deleteBulletinBoard(BulletinBoard bulletinBoard) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard>
										(getBaseURI(), com.emc.ps.appmod.tnt.domain.bulletin.BulletinBoard.class);
		rest.delete("/message/"+bulletinBoard.getId());
	}

	public Integer getNumOfMsgs(Integer categoryId) {
		// TODO Auto-generated method stub
		RestUtil<Integer> rest = new RestUtil<Integer>(getBaseURI(), Integer.class);
		return rest.get("/message/category="+categoryId);
	}

}
