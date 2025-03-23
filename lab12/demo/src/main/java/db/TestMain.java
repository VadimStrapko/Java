package db;

import jakarta.servlet.http.Cookie;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;

public class TestMain {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, UnsupportedEncodingException, NoSuchAlgorithmException {
        DbUser dbUser = new DbUser();
        dbUser.login = "hello";
        dbUser.password = "hello123";
        dbUser.role = "user";
        Cookie[] cookies = new Cookie[3];
        Cookie visitCount = null;
        Cookie role = null;
        Cookie lastOnline = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("visitCount")) {
                visitCount = cookie;
            }
            if (cookie.getName().equals("role")) {
                role = cookie;
            }
            if (cookie.getName().equals("lastOnline")) {
                lastOnline = cookie;
            }
        }
        if (visitCount == null) {
            visitCount = new Cookie("visitCount", "0");
        }
        else
        {
            try {
                visitCount.setValue(String.valueOf(Integer.parseInt(visitCount.getValue()) + 1));
            }
            catch (Exception ex) {
                visitCount.setValue(String.valueOf(1));
            }
        }

        if (role == null) {
            role = new Cookie("role", dbUser.role);
        }

        lastOnline = new Cookie("lastOnline", new Date().toString());

    }
}
