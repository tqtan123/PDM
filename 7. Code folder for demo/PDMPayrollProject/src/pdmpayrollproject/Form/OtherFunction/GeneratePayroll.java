/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdmpayrollproject.Form.OtherFunction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pdmpayrollproject.DBHandler.SQLRun;
import pdmpayrollproject.Form.Manage.ManagePayroll;


public class GeneratePayroll     {
    
    public GeneratePayroll() throws SQLException{
        String today = new  SimpleDateFormat("YYYY-MM-dd").format(Calendar.getInstance().getTime());
        String thisMonth = new  SimpleDateFormat("MM").format(Calendar.getInstance().getTime());
        String thisYear = new  SimpleDateFormat("YYYY").format(Calendar.getInstance().getTime());
        String status = "pending";
        int employeeID;
                
        SQLRun objSQLRun = new SQLRun();
        String sql;
        ResultSet rs;
        
        sql = "SELECT * FROM payroll WHERE month(payroll_date)='"+thisMonth +"' AND year(payroll_date) ='"+thisYear + "';";
        rs = objSQLRun.sqlQuery(sql);
        
        if (!rs.isBeforeFirst() ) {    
            sql =" SELECT employee_id FROM employee";
            rs = objSQLRun.sqlQuery(sql);

            try {
                while (rs.next()){
                    employeeID = rs.getInt(1);
                    sql = "INSERT INTO payroll(payroll_date,payroll_status,payroll_employee_id) VALUE ('"+today+"','"+status+"','"+employeeID +"');";
                    objSQLRun.sqlUpdate(sql);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ManagePayroll.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null,"Genenerated Sucessfully");
        } 
        else {
            JOptionPane.showMessageDialog(null,"Already genenerated for this month");
        }

    }
    
}
