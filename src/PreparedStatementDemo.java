/*
 *  Created by IntelliJ IDEA.
 *  User: Vaibhav
 *  Date: 04-Dec-20
 *  Time: 3:32 PM
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementDemo {
    public static void main(String[] args) {
        try {
            ResultSet rs = getResult(150);
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);

                System.out.println(id + "\t\t\t" + name);
            }
            int rows = insert(5000, "Shyam");
            System.out.println("no of rows affected = " + rows);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static ResultSet getResult(int id) throws SQLException {
        Connection con  = SelectQueryDemo.getDbConnection();
        String sql = "SELECT * FROM info WHERE id > ?;";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, id);
        return pstmt.executeQuery();
    }
    public static int insert(int id, String name) throws SQLException {
        Connection con = SelectQueryDemo.getDbConnection();
        String sql = "INSERT INTO info (id, name) VALUES (?,?);";
        PreparedStatement pstmt = con.prepareStatement(sql);
        //setting sql "?" parameter
        pstmt.setInt(1, id);
        pstmt.setString(2, name);
        return pstmt.executeUpdate();
    }
}
