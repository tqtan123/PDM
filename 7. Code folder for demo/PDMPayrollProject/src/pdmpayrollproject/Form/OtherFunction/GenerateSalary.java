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


public class GenerateSalary     {
    
    public GenerateSalary() throws SQLException{
        String today = new  SimpleDateFormat("YYYY-MM-dd").format(Calendar.getInstance().getTime());
        String thisMonth = new  SimpleDateFormat("MM").format(Calendar.getInstance().getTime());
        String thisYear = new  SimpleDateFormat("YYYY").format(Calendar.getInstance().getTime());
        String description = "Salary of " +thisMonth +"-" + thisYear;
        int payrollID;
        int employeeID;
                
        SQLRun objSQLRun = new SQLRun();
        String sql;
        ResultSet rs;
        
        sql = "SELECT * FROM salary,payroll WHERE month(payroll_date)='"+thisMonth +"' AND year(payroll_date) ='"+thisYear +"' AND salary_payroll_id =payroll_id ;";
        rs = objSQLRun.sqlQuery(sql);
        
        if (!rs.isBeforeFirst() ) {    
            sql =" SELECT employee_id FROM employee";
            rs = objSQLRun.sqlQuery(sql);

            try {
                while (rs.next()){
                    employeeID = rs.getInt(1);
                    //get number of days attendance in month
                    sql = "SELECT COUNT(attendance_id) AS number FROM attendance "
                            + "WHERE attendance_employee_id ='" + employeeID
                            + "' AND month(attendance_date) ='"+thisMonth
                            + "' AND year(attendance_date) ='"+ thisYear+ "';";
                    System.out.println(sql);
                    ResultSet rs2;
                    rs2 = objSQLRun.sqlQuery(sql);
                    rs2.next();
                    int daycount = rs2.getInt(1);
                    
                    if (daycount!=0) {
                        System.out.println("gogogogo");
                        double salary = rs2.getInt(1);
                        
                        sql = "SELECT employee_payrate,job_basic_salary FROM job,employee WHERE employee_id = '"+employeeID+"' AND job_id = employee_job_id ;" ;
                        System.out.println(sql);
                        rs2 = objSQLRun.sqlQuery(sql);
                        rs2.next();
                        double basicSalary =  rs2.getDouble("job_basic_salary");
                        double payRate = rs2.getDouble("employee_payrate");
                        salary = salary * basicSalary * payRate;
                        
                        sql = "SELECT payroll_id FROM payroll WHERE month(payroll_date)='"+thisMonth +"' AND year(payroll_date) ='"+thisYear +"' AND payroll_employee_id ='"+employeeID+"' ;";
                        System.out.println(sql);
                        rs2 = objSQLRun.sqlQuery(sql);  
                        rs2.next();
                        payrollID = rs2.getInt(1);

                        sql = "INSERT INTO salary(salary_amount,salary_description,salary_payroll_id) VALUE ('"+salary+"','"+description+"','"+payrollID +"');";
                        System.out.println(sql);
                        objSQLRun.sqlUpdate(sql);

                    }  
                    


                }
            } catch (SQLException ex) {
                Logger.getLogger(ManagePayroll.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null,"Genenerated Sucessfully");
        } 
        else {
            sql =" SELECT employee_id FROM employee";
            rs = objSQLRun.sqlQuery(sql);

            try {
                while (rs.next()){
                    employeeID = rs.getInt(1);
                    //get number of days attendance in month
                    sql = "SELECT COUNT(attendance_id) AS number FROM attendance "
                            + "WHERE attendance_employee_id ='" + employeeID
                            + "' AND month(attendance_date) ='"+thisMonth
                            + "' AND year(attendance_date) ='"+ thisYear+ "';";
                    System.out.println(sql);
                    ResultSet rs2;
                    rs2 = objSQLRun.sqlQuery(sql);
                    rs2.next();
                    int daycount = rs2.getInt(1);
                    
                    if (daycount!=0) {
                        System.out.println("gogogogo");
                        double salary = rs2.getInt(1);
                        
                        sql = "SELECT employee_payrate,job_basic_salary FROM job,employee WHERE employee_id = '"+employeeID+"' AND job_id = employee_job_id ;" ;
                        System.out.println(sql);
                        rs2 = objSQLRun.sqlQuery(sql);
                        rs2.next();
                        double basicSalary =  rs2.getDouble("job_basic_salary");
                        double payRate = rs2.getDouble("employee_payrate");
                        salary = salary * basicSalary * payRate;
                        
                        sql = "SELECT payroll_id FROM payroll WHERE month(payroll_date)='"+thisMonth +"' AND year(payroll_date) ='"+thisYear +"' AND payroll_employee_id ='"+employeeID+"' ;";
                        System.out.println(sql);
                        rs2 = objSQLRun.sqlQuery(sql);  
                        rs2.next();
                        payrollID = rs2.getInt(1);
                        
                        sql = "UPDATE salary SET salary_amount =  '"+salary+"' ,salary_description ='"+ description+ "' WHERE salary_payroll_id = '"+payrollID + "';";
                        System.out.println(sql);
                        objSQLRun.sqlUpdate(sql);

                    }  

                }
            } catch (SQLException ex) {
                Logger.getLogger(ManagePayroll.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null,"Genenerated Sucessfully");
        
            
        }

    }
    
}
