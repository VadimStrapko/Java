package db;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class DbLogins {
    private Connection connection;
    public void getConnection(Connection connection) {
        this.connection = connection;
    }

    public void addUser(DbUser user) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        PreparedStatement prep = connection.prepareStatement("insert into Users values(?, ?, ?)");
        prep.setString(1, user.login);
        byte[] bytes = user.password.getBytes(StandardCharsets.UTF_8);
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] md5Digest = md.digest(bytes);
        prep.setString(2, new String(md5Digest));
        prep.setString(3, user.role);
        prep.executeUpdate();
    }

    public boolean getUser(DbUser user) throws SQLException, NoSuchAlgorithmException {
        PreparedStatement prep = connection.prepareStatement("select * from Users where userLogin = ?");
        prep.setString(1, user.login);
        ResultSet resultSet = prep.executeQuery();
        while (resultSet.next()) {
            byte[] bytes = user.password.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5Digest = md.digest(bytes);
            if (new String(md5Digest).equals(resultSet.getString("userPassword"))) {
                user.role = resultSet.getString("userRole");
                return true;
            }
        }
        return false;
    }
}
