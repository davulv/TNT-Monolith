/**
 * 
 */
package com.autentia.tnt.manager.book;

import org.springframework.beans.BeanUtils;

import com.autentia.tnt.businessobject.Book;

/**
 * @author bj3
 *
 */
public class BookBeanTransformer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Book transformBook(com.emc.ps.appmod.tnt.domain.book.Book inputBook){
		
		Book outputBook = new Book();
		BeanUtils.copyProperties(inputBook, outputBook);
		return outputBook;
		
	}
	
	public com.emc.ps.appmod.tnt.domain.book.Book transformBook(Book inputBook){
		
		com.emc.ps.appmod.tnt.domain.book.Book outputBook = new com.emc.ps.appmod.tnt.domain.book.Book();
		BeanUtils.copyProperties(inputBook, outputBook);
		return outputBook;
		
	}

}
