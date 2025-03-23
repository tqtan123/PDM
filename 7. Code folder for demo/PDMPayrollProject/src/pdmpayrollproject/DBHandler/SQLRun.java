/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdmpayrollproject.DBHandler;

import java.sql.*;

public class SQLRun {

    //create and initialize variables
    Statement statement = null;

    //method to query data
    public ResultSet sqlQuery(String sql) {
        try {
            statement = DbConnection.getDbConnection().createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //method to update data
    public int sqlUpdate(String sql) {
        try {
            statement = DbConnection.getDbConnection().createStatement();
            return statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    

}