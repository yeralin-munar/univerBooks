package com.epam.dao;

import com.epam.connection.ConnectionPool;
import com.epam.dao.book.BookDAO;
import com.epam.dao.book.PostgreSQLBookDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;

/**
 * @author Yeralin Munar
 *         date: 5/23/17
 */
public class DAOFactory {
    private static final Logger log = Logger.getLogger(DAOFactory.class);

    private ConnectionPool connectionPool;
    private Connection connection;

    public DAOFactory(){
        connectionPool = ConnectionPool.getInstance();

        try {
            connection = connectionPool.getConnection();
        } catch (Exception e){
            log.error("Can't get connection from pool", e);
        }
    }

    public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public BookDAO getBookDAO(){
        return new PostgreSQLBookDAO(connectionPool);
    }
}
