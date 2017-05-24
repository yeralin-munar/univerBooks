package com.epam.dao.book;

import com.epam.connection.ConnectionPool;
import com.epam.constant.Constant;
import com.epam.entity.Book;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yeralin Munar
 *         date: 5/23/17
 */
public class PostgreSQLBookDAO implements BookDAO {
    private static final Logger log = Logger.getLogger(PostgreSQLBookDAO.class);

    private ConnectionPool connectionPool = null;

    public PostgreSQLBookDAO(ConnectionPool connectionPool){
        this.connectionPool = connectionPool;
    }

    @Override
    public boolean delete(int id) {
        String sql = Constant.DELETE_BY_COLUMN;
        sql = sql.replace("&table", Constant.BOOK).replace("&column", Constant.ID);

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();

            connectionPool.releaseConnection(connection);
            log.debug("Delete book where id = " + id);
            return true;
        } catch (SQLException e) {
            log.error("Can't delete book where id = " + id, e);
        }
        return false;
    }

    @Override
    public Book find(int id) {
        Book book = null;

        String sql = Constant.SELECT_BY_COLUMN;
        sql = sql.replace("$table", Constant.BOOK).replace("$column", Constant.ID);

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                book = setBook(book, resultSet);
            }
            connectionPool.releaseConnection(connection);

            log.debug("Find book where id = " + id);
            return book;
        } catch (SQLException e) {
            log.error("Can't find book where id = " + id, e);
        }

        return null;
    }

    @Override
    public Book insert(Book object) {
        String sql = Constant.INSERT_BY_COLUMNS;
        sql = sql.replace("$table", Constant.BOOK)
                .replace("$columns", Constant.NAME + ", "+ Constant.AUTHOR + ", "+ Constant.FACULTY + ", " +
                        Constant.DEPARTMENT + ", " + Constant.SUBJECT + ", " + Constant.STATUS)
                .replace("$values", "?, ?, ?, ?, ?, ?");

        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            setStatement(object, statement);
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int id = resultSet.getInt("id");
            object.setId(id);

            connectionPool.releaseConnection(connection);

            log.debug("Book successfully was inserted with id = " + id);
        } catch (SQLException e) {
            log.error("Can't insert book", e);
        }

        return object;
    }

    @Override
    public boolean update(Book object) {
        String sql = Constant.UPDATE_BY_COLUMNS;
        sql = sql.replace("$table", Constant.BOOK)
                .replace("$columns", Constant.NAME + ", "+ Constant.AUTHOR + ", "+ Constant.FACULTY + ", " +
                        Constant.DEPARTMENT + ", " + Constant.SUBJECT + ", " + Constant.STATUS)
                .replace("$values", "?, ?, ?, ?, ?, ?");
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            setStatement(object, statement);
            statement.setInt(7, object.getId());

            statement.executeUpdate();

            log.debug("Book successfully was inserted with id = " + object.getId());
            return true;

        } catch (SQLException e){
            log.error("Can't update book with id = " + object.getId(), e);
        }
        return false;
    }

    @Override
    public List<Book> all() {
        List<Book> books = new ArrayList<>();
        String sql = Constant.SELECT_ALL;
        sql = sql.replace("$table", Constant.BOOK);
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            Book book = null;
            while (resultSet.next()){
                book = setBook(book, resultSet);
                books.add(book);
            }
            log.debug("Get all " + books.size() + " books");
            return books;
        } catch (SQLException e){
            log.error("Can't get all books", e);
        }

        return null;
    }

    @Override
    public List<Book> getAllBooksByColumnId(String column, int id) {
        List<Book> books = new ArrayList<>();
        String sql = Constant.SELECT_BY_COLUMN;
        sql = sql.replace("&table", Constant.BOOK).replace("&column", column);
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            Book book = null;
            while (resultSet.next()){
                book = setBook(book, resultSet);
                books.add(book);
            }
            log.debug("Get all " + books.size() + " books where "+column+" id = " + id);
            return books;
        } catch (SQLException e){
            log.error("Can't get all books where "+column+" id = " + id, e);
        }

        return null;
    }

    @Override
    public List<Book> findBookByName(String name) {
        return null;
    }

    private Book setBook(Book book, ResultSet resultSet) throws SQLException{
        book = new Book();
        book.setId(resultSet.getInt(Constant.ID));
        book.setName(resultSet.getString(Constant.NAME));
        book.setStatus(resultSet.getString(Constant.STATUS));
        return book;
    }

    private PreparedStatement setStatement(Book book, PreparedStatement statement) throws SQLException{
        statement.setString(1, book.getName());
        statement.setInt(2, book.getAuthor().getId());
        statement.setInt(3, book.getFaculty().getId());
        statement.setInt(4, book.getDepartment().getId());
        statement.setInt(5, book.getSubject().getId());
        statement.setString(6, book.getStatus());

        return statement;
    }
}
