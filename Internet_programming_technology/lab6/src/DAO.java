import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DAO implements IConnect, IQuery {

    private static final org.apache.log4j.Logger LOG = Logger.getLogger(Main.class);
    private String url;

    private String user;
    private String password;
    private Connection con;
    private Statement statement;
    //private static final Logger logger = Logger.getLogger(Main.class);

    public ArrayList<String> getProperties() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
        url = resourceBundle.getString("db.url");
        user = resourceBundle.getString("db.username");
        password = resourceBundle.getString("db.password");
        ArrayList<String> prop = new ArrayList<>();
        prop.add(url);
        prop.add(user);
        prop.add(password);
        return prop;
    }

    public Boolean getConnection() {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost;database=WEATHER;trustServerCertificate=true;";
            con = DriverManager.getConnection(connectionUrl, "sa", "1111");
            LOG.info("Подключение к бд прошло успешно!");
            statement = con.createStatement();
            LOG.info("Создаём statement");
            return true;
        }
        catch(Exception ex) {
            System.out.println(ex);
            LOG.info("Возникла ошибка при вызове метода getConnection()");
            return false;
        }
    }
    public void closeConnection() {
        try {
            statement.close();
            con.close();
            LOG.info("Соединение закрыто");
        }
        catch (Exception ex) {
            LOG.info("Возникла ошибка при закрытии соединения");
            System.out.println(ex);
        }
    }

    public String Query1() {
        try {
            ResultSet resultSet = statement.executeQuery("select Регион, Дата, Температура, Осадки from ПОГОДА " +
                    "where Регион = \'Могилевская область\'");
            LOG.info("Запрос 1 выполнен, выводим результат");
            while(resultSet.next())
            {
                String region = resultSet.getString(1);
                String date = resultSet.getString(2);
                String temperature = resultSet.getString(3);
                String osadki = resultSet.getString(4);
                System.out.println("Регион: " + region + ", Дата: " + date + ", Температура: " + temperature + ", Осадки: " + osadki);
            }
            LOG.info("Вывод запроса 1 завершен");
            return resultSet.toString();
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            return "";
        }
    }

    public void Query2(String region) {
        try{
            String sql =  "select Дата from Погода where Осадки = 'Снег' and Температура < 0 and Регион = ?";
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, region);
            ResultSet resultSet = prep.executeQuery();
            LOG.info("Подготовленный запрос 2 выполнен, выводим результат");
            while(resultSet.next())
            {
                String date = resultSet.getString(1);
                System.out.println("Дата: " + date);
            }
            LOG.info("Вывод запроса 1 завершен");
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }

    public void Query3(){
        try{
            ResultSet resultSet = statement.executeQuery("select * from ПОГОДА inner join РЕГИОН on ПОГОДА.Регион = РЕГИОН.Название " +
                    "inner join ТИП_ЖИТЕЛЕЙ on РЕГИОН.[Тип жителей] = ТИП_ЖИТЕЛЕЙ.НАЗВАНИЕ " +
                    "where Язык = \'Русский\' and  DATEPART(ISO_WEEK, ПОГОДА.Дата) = DATEPART(ISO_WEEK, DATEADD(WEEK, -1, GETDATE()))");
            LOG.info("Запрос 2 выполнен, выводим результат");
            while(resultSet.next())
            {
                String id = resultSet.getString(1);
                String region = resultSet.getString(2);
                String date = resultSet.getString(3);
                String temperature = resultSet.getString(4);
                String osadki = resultSet.getString(5);
                System.out.println("Id: " + id + ", Регион: " + region + ", Дата: " + date + ", Температура: " + temperature + ", Осадки: " + osadki);
            }
            LOG.info("Вывод запроса 3 завершен");
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    public void Query4(){
        try{
            LOG.info("Запрос 2 выполнен, выводим результат");
            ResultSet resultSet = statement.executeQuery("select ПОГОДА.Регион, РЕГИОН.ПЛОЩАДЬ, AVG(Температура) [Средняя температура] " +
                    "from ПОГОДА inner join РЕГИОН on РЕГИОН.Название = ПОГОДА.Регион " +
                    "where DATEPART(ISO_WEEK, ПОГОДА.Дата) = DATEPART(ISO_WEEK, DATEADD(WEEK, -1, GETDATE())) " +
                    "group by  ПОГОДА.Регион, РЕГИОН.Площадь having РЕГИОН.Площадь > 20000");
            while(resultSet.next())
            {
                String region = resultSet.getString(1);
                String square = resultSet.getString(2);
                String temperature = resultSet.getString(3);
                System.out.println("Регион: " + region + ", Площадь: " + square + ", Средняя температура: " + temperature);
            }
            LOG.info("Вывод запроса 4 завершен");
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    public void QueryTransaction() throws SQLException {
        try{
            LOG.info("Начало транзакции");
            con.setAutoCommit(false);
            statement.executeUpdate("Insert into ПОГОДА(id, Регион, Дата, Температура, Осадки) values" +
                    " (30, 'Гомельская область', '2023-09-21', 10.3, 'Дождь' )");
            con.commit();
        }
        catch(Exception ex)
        {
            LOG.info("Транзакция не выполнена, выполняем rollback");
            con.rollback();
            System.out.println(ex);
        }
    }
}
