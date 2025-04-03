package org.example.server.connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class ConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class.getName());
    private static final String PROPERTY_PATH = "db";
    private static final int INITIAL_CAPACITY = 10;
    private ArrayBlockingQueue<Connection> freeConnections = new ArrayBlockingQueue<>(INITIAL_CAPACITY);
    private ArrayBlockingQueue<Connection> releaseConnections = new ArrayBlockingQueue<>(INITIAL_CAPACITY);
    private static ReentrantLock lock = new ReentrantLock();
    private volatile static ConnectionPool connectionPool;

    public static ConnectionPool getInstance() {
        try {
            lock.lock();
            if (connectionPool == null) {
                connectionPool = new ConnectionPool();
            }
        }
        catch (Exception e) {
            LOGGER.info("без шансов");
            throw new RuntimeException();
        }
        finally {
            lock.unlock();
        }
        return connectionPool;
    }

    private ConnectionPool() {
        try {
            lock.lock();
            if (connectionPool != null) {
                throw new UnsupportedOperationException();
            }
            else {
                init();
            }
        }
        finally {
            lock.unlock();
        }
    }

    private void init() {
        Properties properties = new Properties();
        ResourceBundle res = ResourceBundle.getBundle(PROPERTY_PATH, Locale.getDefault());
        if (res == null) {
            LOGGER.info("без шансов 2");
        }
        else {
            String connectionURL = res.getString("db.url");
            String user = res.getString("db.user");
            String password = res.getString("db.password");
            String className = res.getString("db.driver");
            for (int i = 0; i < INITIAL_CAPACITY; i++) {
                try {
                    Class.forName(className);
                    Connection connection = DriverManager.getConnection(connectionURL, user, password);
                    freeConnections.add(connection);
                }
                catch (Exception e) {
                    LOGGER.info(e.getMessage());
                    throw new RuntimeException();
                }
            }
        }
    }

    public Connection getConnection() {
        try {
            Connection connection = freeConnections.take();
            releaseConnections.offer(connection);
            LOGGER.info("взято подключение");
            return connection;
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void releaseConnection(Connection connection) {
        releaseConnections.remove(connection);
        freeConnections.offer(connection);
        LOGGER.info("Connection was released, the are free connection " + freeConnections.size());
    }

    public void destroy() {
        for (int i = 0; i < freeConnections.size(); i++) {
            try {
                Connection connection = (Connection) freeConnections.take();
                connection.close();
            } catch (InterruptedException e) {
                LOGGER.info("ошибка при закрытии");
            } catch (SQLException e) {
                LOGGER.info("бд не закрыта");
                throw new RuntimeException("database is not closed", e);
            }
        }
        try {
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                java.sql.Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }
        } catch (SQLException e) {
            LOGGER.info("Драйвер не дерегене");
        }
    }
}
