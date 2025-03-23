package db;

import java.sql.*;
import java.util.ArrayList;

public class DbItems {
    private Connection connection;

    /**
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void openConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:sqlserver://localhost;database=java;trustServerCertificate=true";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String username = "sa";
        String password = "1111";
        this.connection = DriverManager.getConnection(url, username, password);
    }

    public ArrayList<DbItem> getItems() throws SQLException {
        ArrayList<DbItem> items = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Items");
        while(resultSet.next()){
            String name = resultSet.getString(1);
            Date dateOfFound = resultSet.getDate(2);
            String founder = resultSet.getString(3);
            DbItem item = new DbItem();
            item.Name = name;
            item.DateOfFound = dateOfFound;
            item.Founder = founder;
            items.add(item);
        }
        return items;
    }

    public void addItem(DbItem item) throws SQLException {
        PreparedStatement prep = connection.prepareStatement("insert into Items values(?, ?, ?)");
        prep.setString(1, item.getName());
        prep.setDate(2, item.getDateOfFound());
        prep.setString(3, item.getFounder());
        prep.executeUpdate();
    }

    public void removeItem(String name) {
        try {
            PreparedStatement prep = connection.prepareStatement("delete Items where Name = ?");
            prep.setString(1, name);
            prep.executeUpdate();
        }
        catch (SQLException e) {
        }
    }

    public void updateItem(String name, String newName, String newFounder) throws SQLException {
        PreparedStatement prep = connection.prepareStatement("update Items set Name = ?, Founder = ? where Items.Name = ?");
        prep.setString(3, name);
        prep.setString(2, newName);
        prep.setString(1, newFounder);
        prep.executeUpdate();
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }
}
