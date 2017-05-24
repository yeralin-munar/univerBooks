package com.epam.connection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Yeralin Munar
 *         date: 5/20/17
 */
public class ConnectionPool {
    private static final Logger log = Logger.getLogger(ConnectionPool.class);
    private static final String PROPERTIES_FILE = "db";
    private static final int DEFAULT_POOL_SIZE = 10;

    private static ConnectionPool instance;
    private BlockingQueue<Connection> connectionBlockingQueue;

    private ConnectionPool(){
        init();
    }

    private ConnectionPool(String driver, String url, String user, String password, int poolSize)
            throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connectionBlockingQueue = new ArrayBlockingQueue<Connection>(poolSize);
        for (int i=0; i < poolSize; i++){
            Connection connection;
            if (user != null)
                connection = DriverManager.getConnection(url, user, password);
            else
                connection = DriverManager.getConnection(url);

            connectionBlockingQueue.offer(connection);
        }

    }

    private static void init(){
        if (instance == null){
            ResourceBundle resourceBundle = ResourceBundle.getBundle(PROPERTIES_FILE);
            String driver = resourceBundle.getString("driver");
            String url = resourceBundle.getString("url");
            String user = resourceBundle.getString("user");
            String password = resourceBundle.getString("password");
            String poolSizeStr = resourceBundle.getString("poolsize");
            int poolSize = (poolSizeStr != null) ? Integer.parseInt(poolSizeStr) : DEFAULT_POOL_SIZE;

            try {
                try {
                    instance = new ConnectionPool(driver, url, user, password, poolSize);
                } catch (SQLException e) {
                    log.error("Driver "+driver+" not found.", e);
                }
                log.debug("Connection pool succesfully initialized.");
            } catch (ClassNotFoundException e){
                log.error("Driver "+driver+" not found.", e);
            }
        }
    }

    public static ConnectionPool getInstance(){
        if (instance == null){
            return new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection(){
        Connection connection = null;
        try {
            connection = connectionBlockingQueue.take();
        } catch (InterruptedException e){
            log.error("Free connection waiting interrupted. Returned null connection.", e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection){
        try {
            if (!connection.isClosed()){
                if (!connectionBlockingQueue.offer(connection)){
                    log.error("Connection not added. Possible leakage of connections.");
                }
            }else {
                log.error("Trying to release connection. Possible leakage of connections");
            }
        }catch (SQLException e){
            log.error("SQLException at connection isClosed() checking. Connection not added.", e);
        }
    }

    private void clearConnetionQueue() throws SQLException{
        Connection connection;
        while ((connection = connectionBlockingQueue.poll()) != null){
            if (!connection.getAutoCommit()){
                connection.commit();
            }
            connection.close();
        }
    }

    public static void dispose() throws SQLException{
        if (instance != null){
            instance.clearConnetionQueue();
            instance = null;
        }
    }
}
