/**
 * 
 */
package com.autentia.tnt.manager.book;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.Book;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.hibernate.UserDAO;

/**
 * @author bj3
 *
 */
public class BookBeanTransformer {
	
	private static final Log log = LogFactory.getLog(BookBeanTransformer.class);
	
	private UserDAO userDAO = new UserDAO();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Book transformBook(com.emc.ps.appmod.tnt.domain.utilities.Book inputBook){
		
		log.info("Inside individual bookTransform returning business book");
		Book outputBook = new Book();
		//BeanUtils.copyProperties(outputBook, inputBook);
		try {
			BeanUtilsBean.getInstance().copyProperties(outputBook, inputBook);
			outputBook.setId(new Long(inputBook.getId()).intValue());
			int userId = outputBook.getLentToUserId();
			log.info("Lent to user id from microservice : "+userId);
			User user = userDAO.getById(userId);
			log.info("User object created from the microservice lenttouserid : "+user.toString());
			outputBook.setLentTo(user);
			log.info(outputBook.getLentTo().toString());
		} catch (IllegalAccessException ex) {
			// TODO: handle exception
			throw new RuntimeException("Error cloning ITransferObject", ex);
		} catch (InvocationTargetException ex) {
			// TODO: handle exception
			throw new RuntimeException("Error cloning ITransferObject", ex);
		}
		return outputBook;
		
	}
	
	public com.emc.ps.appmod.tnt.domain.utilities.Book transformBook(Book inputBook){
		
		log.info("Inside individual bookTransform returning our book");
		com.emc.ps.appmod.tnt.domain.utilities.Book outputBook = new com.emc.ps.appmod.tnt.domain.utilities.Book();
		//BeanUtils.copyProperties(outputBook, inputBook);
		try {
			BeanUtilsBean.getInstance().copyProperties(outputBook, inputBook);
			User user = inputBook.getLentTo();
			log.info("User object from the insertBook business book"+user.getAccount()+"to string of same object"+user.toString());
			int userId = user.getId();
			log.info("UserId from the business book : "+userId);
			outputBook.setLentToUserId(userId);
			log.info("Lent to user id from our book after copied from business book :"+outputBook.getLentToUserId());
			
		} catch (IllegalAccessException ex) {
			// TODO: handle exception
			throw new RuntimeException("Error cloning ITransferObject", ex);
		} catch (InvocationTargetException ex) {
			// TODO: handle exception
			throw new RuntimeException("Error cloning ITransferObject", ex);
		}
		return outputBook;
		
	}
	
	public List<Book> transformBookList(List<com.emc.ps.appmod.tnt.domain.utilities.Book> inputBookList) {
		log.info("........in booklist transformer");
		List<Book> outputBookList = new ArrayList<Book>();
		for (com.emc.ps.appmod.tnt.domain.utilities.Book inputBook : inputBookList) {
			log.info(".....in booklist transformer...."+inputBook.getName());
			outputBookList.add(transformBook(inputBook));
		}
		return outputBookList;
	}

}
