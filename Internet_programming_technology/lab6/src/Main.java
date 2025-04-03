import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.sql.SQLException;


public class Main {

    static {
        new DOMConfigurator().doConfigure("log/log4j.xml",
                LogManager.getLoggerRepository());
    }

    private static final Logger LOG = Logger.getLogger(Main.class);
    public static void main(String[] args) throws SQLException {
        LOG.info("Начало программы!");
        DAO dao = new DAO();
        dao.getConnection();
        System.out.println("Запрос 1:");
        LOG.info("Вызываем запрос 1");
        dao.Query1();
        LOG.info("Вызываем запрос 2");
        System.out.println("Запрос 2:");
        dao.Query2("Могилевская область");
        System.out.println("Запрос 3:");
        LOG.info("Вызываем запрос 3");
        dao.Query3();
        System.out.println("Запрос 4:");
        LOG.info("Вызываем запрос 4");
        dao.Query4();
        System.out.println("Транзакция:");
        LOG.info("Вызываем транзакцию");
        dao.QueryTransaction();
        dao.closeConnection();
    }
}