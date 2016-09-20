/**
 * 
 */
package com.autentia.tnt.manager.book;

import java.util.List;

import com.autentia.tnt.businessobject.Book;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.BookSearch;

/**
 * @author bj3
 *
 */
public interface BookServiceProxy {

	public List<Book> getAllBooks(BookSearch search, SortCriteria sort);
	
	public Book getBookById(Integer id);
	
	public void insertBook(Book book);
	
	public void updateBook(Book book);
	
	public void deleteBook(Book book);
}
