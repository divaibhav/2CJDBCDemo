/*
 *  Created by IntelliJ IDEA.
 *  User: Vaibhav
 *  Date: 04-Dec-20
 *  Time: 3:14 PM
 */

import java.sql.*;

public class SelectQueryDemo {
    public static void main(String[] args) {
        try {
            Connection con = getDbConnection();
            Statement stmt = con.createStatement();
            int id = 100;
            String sql = "SELECT * FROM info WHERE id >" + id +" ;";
            ResultSet rs = stmt.executeQuery(sql);
            //processing result
            while (rs.next()){
                 id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println(id + "\t\t\t" + name);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public static Connection getDbConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/demo";
        String user = "root";
        String pass = "";
        return DriverManager.getConnection(url, user,pass);
    }
}
