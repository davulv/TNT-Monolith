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

/**
 * @author bj3
 *
 */
public class BookBeanTransformer {
	
	private static final Log log = LogFactory.getLog(BookBeanTransformer.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Book transformBook(com.emc.ps.appmod.tnt.domain.utilities.Book inputBook){
		
		Book outputBook = new Book();
		//BeanUtils.copyProperties(outputBook, inputBook);
		try {
			BeanUtilsBean.getInstance().copyProperties(outputBook, inputBook);
			outputBook.setId(new Long(inputBook.getId()).intValue());
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
		
		com.emc.ps.appmod.tnt.domain.utilities.Book outputBook = new com.emc.ps.appmod.tnt.domain.utilities.Book();
		//BeanUtils.copyProperties(outputBook, inputBook);
		try {
			BeanUtilsBean.getInstance().copyProperties(outputBook, inputBook);
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
