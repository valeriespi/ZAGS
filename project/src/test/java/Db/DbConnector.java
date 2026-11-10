package Db;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class DbConnector {

    private static final Logger log = LogManager.getLogger(DbConnector.class);

    private static final String HOST = System.getProperty("db.host", "86.57.161.116");
    private static final int PORT = Integer.getInteger("db.port", 50432);
    private static final String DATABASE = System.getProperty("db.database", "register_office");
    private static final String USER = System.getProperty("db.user", "user");
    private static final String PASSWORD = System.getProperty("db.password", "user_senla");

    private static Connection connection;

    private DbConnector() {
    }

    public static boolean isReachable() {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(HOST, PORT), 3000);
            return true;
        } catch (IOException e) {
            log.debug("БД недоступна: {}:{} — {}", HOST, PORT, e.getMessage());
            return false;
        }
    }

    public static String url() {
        return "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE;
    }

    public static Properties properties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url", url());
        properties.setProperty("hibernate.connection.username", USER);
        properties.setProperty("hibernate.connection.password", PASSWORD);
        return properties;
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            log.debug("Подключение к БД: {}", url());
            connection = DriverManager.getConnection(url(), USER, PASSWORD);
            log.debug("Соединение установлено");
        }
        return connection;
    }
}
