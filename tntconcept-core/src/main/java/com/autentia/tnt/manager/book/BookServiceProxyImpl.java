/**
 * 
 */
package com.autentia.tnt.manager.book;

import java.util.List;

import com.autentia.tnt.businessobject.Book;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.BookSearch;
import com.autentia.tnt.util.RestUtil;

/**
 * @author bj3
 *
 */
public class BookServiceProxyImpl implements BookServiceProxy {
	
	private BookBeanTransformer transform = new BookBeanTransformer();

	public BookServiceProxyImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public String getBaseURI(){
		return "http://tnt-utilities.cfapps.io/api/utilities";
	}

	/* (non-Javadoc)
	 * @see com.autentia.tnt.manager.book.BookServiceProxy#getAllBooks(com.autentia.tnt.dao.search.BookSearch, com.autentia.tnt.dao.SortCriteria)
	 */
	public List<Book> getAllBooks(BookSearch search, SortCriteria sort) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.book.Book> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.book.Book>
												(getBaseURI(), com.emc.ps.appmod.tnt.domain.book.Book.class);
		List<com.emc.ps.appmod.tnt.domain.book.Book> bookList = rest.getList("/book/list");
		return transform.transformBookList(bookList);
	}

	/* (non-Javadoc)
	 * @see com.autentia.tnt.manager.book.BookServiceProxy#getBookById(java.lang.Integer)
	 */
	public Book getBookById(Integer id) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.book.Book> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.book.Book>
												(getBaseURI(), com.emc.ps.appmod.tnt.domain.book.Book.class);
		com.emc.ps.appmod.tnt.domain.book.Book book = rest.get("/book/"+id);
		return transform.transformBook(book);
	}

	/* (non-Javadoc)
	 * @see com.autentia.tnt.manager.book.BookServiceProxy#insertBook(com.autentia.tnt.businessobject.Book)
	 */
	public void insertBook(Book book) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.book.Book> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.book.Book>
												(getBaseURI(), com.emc.ps.appmod.tnt.domain.book.Book.class);
		com.emc.ps.appmod.tnt.domain.book.Book b = transform.transformBook(book);
		rest.post("/book", b);
	}

	/* (non-Javadoc)
	 * @see com.autentia.tnt.manager.book.BookServiceProxy#updateBook(com.autentia.tnt.businessobject.Book)
	 */
	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.book.Book> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.book.Book>
												(getBaseURI(), com.emc.ps.appmod.tnt.domain.book.Book.class);
		com.emc.ps.appmod.tnt.domain.book.Book b = transform.transformBook(book);
		rest.put("/book", b);
	}

	/* (non-Javadoc)
	 * @see com.autentia.tnt.manager.book.BookServiceProxy#deleteBook(com.autentia.tnt.businessobject.Book)
	 */
	public void deleteBook(Book book) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.book.Book> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.book.Book>
												(getBaseURI(), com.emc.ps.appmod.tnt.domain.book.Book.class);
		rest.delete("/book/"+book.getId());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
