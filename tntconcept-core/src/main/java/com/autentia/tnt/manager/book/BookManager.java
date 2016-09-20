/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.autentia.tnt.manager.book;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.Book;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.BookDAO;
import com.autentia.tnt.dao.hibernate.UserDAO;
import com.autentia.tnt.dao.search.BookSearch;
import com.autentia.tnt.util.SpringUtils;


public class BookManager {

/* Book - generated by stajanov (do not edit/delete) */



  /** Logger */
  private static final Log log = LogFactory.getLog(BookManager.class);

  /** Book DAO **/
  private BookDAO bookDAO;
  
  private BookServiceProxy bookServiceProxy = new BookServiceProxyImpl();

  /**
   * Get default BookManager as defined in Spring's configuration file.
   * @return the default singleton BookManager
   */
  public static BookManager getDefault()
  {
    return (BookManager)SpringUtils.getSpringBean("managerBook");
  }

  /** 
   * Empty constructor needed by CGLIB (Spring AOP)
   */
  protected BookManager()
  {
  }
	
  /** 
   * Default constructor 
   * @deprecated do not construct managers alone: use Spring's declared beans
   */
  public BookManager( BookDAO bookDAO )
  {
    this.bookDAO = bookDAO;
  }

  /**
   * List books. 
   * @param search search filter to apply
   * @param sort sorting criteria
   * @return the list of all books sorted by requested criterion
   */
  public List<Book> getAllEntities(BookSearch search, SortCriteria sort){
    return bookDAO.search( search, sort );
  }
  
  /**
   * Get book by primary key.
   * @return book selected by id.
   */
  public Book getEntityById(int id){
    //return bookDAO.getById(id);
	  return bookServiceProxy.getBookById(id);
  }
	
  /**
   * Insert book. 
   */
  public void insertEntity(Book book) {
    //bookDAO.insert(book);
	  bookServiceProxy.insertBook(book);
  }
	 
  /**
   * Update book. 
   */
  public void updateEntity(Book book) {
    //bookDAO.update(book);
	  bookServiceProxy.updateBook(book);
  }

  /**
   * Delete book. 
   */
  public void deleteEntity(Book book) {
    //bookDAO.delete(book);
	  bookServiceProxy.deleteBook(book);
  }

/* Book - generated by stajanov (do not edit/delete) */

	
}
