/**
 * 
 */
package com.autentia.tnt.manager.book;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.Book;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.BookSearch;
import com.autentia.tnt.manager.publish.MagazineServiceProxyImpl;
import com.autentia.tnt.util.RestUtil;
import com.autentia.tnt.util.SpringUtils;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

/**
 * @author bj3
 *
 */
public class BookServiceProxyImpl implements BookServiceProxy {
	
	private static final Log log = LogFactory.getLog(BookServiceProxyImpl.class);
	
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
		RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Book> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Book>
												(getBaseURI(), com.emc.ps.appmod.tnt.domain.utilities.Book.class);
		ClientResponse response =  rest.getList("/book/list");
		List<com.emc.ps.appmod.tnt.domain.utilities.Book> bookList = response.getEntity(
				new GenericType<List<com.emc.ps.appmod.tnt.domain.utilities.Book>>() {});
		log.info("---just before transforming--"+bookList.size());
		List<Book> outputBookList = transform.transformBookList(bookList);
		log.info("---after transforming---"+outputBookList.size());
		return outputBookList;
	}

	/* (non-Javadoc)
	 * @see com.autentia.tnt.manager.book.BookServiceProxy#getBookById(java.lang.Integer)
	 */
	public Book getById(int id) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Book> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Book>
												(getBaseURI(), com.emc.ps.appmod.tnt.domain.utilities.Book.class);
		com.emc.ps.appmod.tnt.domain.utilities.Book book = rest.get("/book/"+id);
		return transform.transformBook(book);
	}

	/* (non-Javadoc)
	 * @see com.autentia.tnt.manager.book.BookServiceProxy#insertBook(com.autentia.tnt.businessobject.Book)
	 */
	public void insertBook(Book book) {
		// TODO Auto-generated method stub
		book.setOwnerId(SpringUtils.getPrincipal().getId());
		RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Book> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Book>
												(getBaseURI(), com.emc.ps.appmod.tnt.domain.utilities.Book.class);
		com.emc.ps.appmod.tnt.domain.utilities.Book b = transform.transformBook(book);
		rest.post("/book", b);
	}

	/* (non-Javadoc)
	 * @see com.autentia.tnt.manager.book.BookServiceProxy#updateBook(com.autentia.tnt.businessobject.Book)
	 */
	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		book.setOwnerId(SpringUtils.getPrincipal().getId());
		RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Book> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Book>
												(getBaseURI(), com.emc.ps.appmod.tnt.domain.utilities.Book.class);
		com.emc.ps.appmod.tnt.domain.utilities.Book b = transform.transformBook(book);
		rest.put("/book", b);
	}

	/* (non-Javadoc)
	 * @see com.autentia.tnt.manager.book.BookServiceProxy#deleteBook(com.autentia.tnt.businessobject.Book)
	 */
	public void deleteBook(Book book) {
		// TODO Auto-generated method stub
		RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Book> rest = new RestUtil<com.emc.ps.appmod.tnt.domain.utilities.Book>
												(getBaseURI(), com.emc.ps.appmod.tnt.domain.utilities.Book.class);
		rest.delete("/book/"+book.getId());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
