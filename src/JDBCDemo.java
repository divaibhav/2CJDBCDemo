/*
 *  Created by IntelliJ IDEA.
 *  User: Vaibhav
 *  Date: 03-Dec-20
 *  Time: 2:43 PM
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {
    public static void main(String[] args) {
        try {
            //step 1
            //load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //step 2
            //create connection
            String url = "jdbc:mysql://localhost:3306/demo";
            String user = "root";
            String pass = "";
            Connection con  = DriverManager.getConnection(url, user, pass);
            //step 3
            //prepare statement
            Statement stmt = con.createStatement();
            String query = "INSERT INTO `info` (`id`, `name`) VALUES ('4000', 'Test Insert');";
            //step 4
            //execute query
            int row  = stmt.executeUpdate(query);
            //step 5
            //process the result
            System.out.println("no of rows affected = " + row);

        }
        catch (ClassNotFoundException e){
            System.out.println("class not found");
            System.out.println(e.getMessage());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
