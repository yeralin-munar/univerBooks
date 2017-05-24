package com.epam.dao.book;

import com.epam.dao.DAO;
import com.epam.entity.Book;

import java.util.List;

/**
 * @author Yeralin Munar
 *         date: 5/23/17
 */
public interface BookDAO extends DAO<Book> {
    List<Book> getAllBooksByColumnId(String column, int id);
    List<Book> findBookByName(String name);
}
