package pdmpayrollproject.DBHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    static Connection conn = null;

    static final String DB_USER_NAME = "root";
    static final String DB_PASSWORD = "";
    static final String DB_NAME = "payroll";
    static final String DB_URL = "jdbc:mysql://127.0.0.1/";

    public static Connection getDbConnection() {

        try {
            conn = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER_NAME, DB_PASSWORD);
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

