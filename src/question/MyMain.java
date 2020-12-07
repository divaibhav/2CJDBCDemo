/*
 *  Created by IntelliJ IDEA.
 *  User: Vaibhav
 *  Date: 07-Dec-20
 *  Time: 4:12 PM
 */
/**
 * create a database "gla", in that database create a table "student", which contain following columns
 * rollno (int),
 * name(text),
 * email(varchar) 250,
 * cpi(double).
 *
 * Perform the following queries
 * 1. Insert  - > INSERT INTO `student` (`rollno`, `name`, `email`, `cpi`) VALUES ('12', 'Vaibhav', 'vai@gmail.com', '5.73');
 * 2. update where rollno is 12 set cpi t0 8.91 - > UPDATE `student` SET `cpi`= 8.91 WHERE rollno = 12
 * 3. Delete where cpi = 9.9 -> DELETE FROM `student` WHERE cpi = 9.9;
 * 4. Select all the data - > SELECT * FROM student;
 */
package question;

import java.sql.*;

public class MyMain {
    public static void main(String[] args) {
        try {
            System.out.println("inserting data");
            //calling insert method
            int rows = insertData(12,"vaibhav", "v@gmail.com", 6.12);
            System.out.println("no of rows affected = " + rows);
            //calling slectAll()
            System.out.println("talbe data");
            ResultSet rs = selectAll();
            if(rs != null){
                while (rs.next()){
                    int rollno = rs.getInt("rollno");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    double cpi = rs.getDouble("cpi");
                    System.out.println(rollno +"\t" + name +"\t" + email +"\t" + cpi);

                }
            }
            System.out.println("updating");
            rows = updateData(12, "abc@gmail.com");
            System.out.println("no of rows affected = " + rows);
            System.out.println("deleting");
            rows = deleteData(54);
            System.out.println("no of rows affected = " + rows);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    //connection
    public static Connection getDbConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/gla";
        String user = "root";
        String pass = "";
        Connection con = DriverManager.getConnection(url, user, pass);
        return con;
    }
    //insert
    public static int insertData(int rollNo, String name, String email, double cpi) throws SQLException {
        Connection con  = getDbConnection();
        String sql = "INSERT INTO `student` (`rollno`, `name`, `email`, `cpi`) VALUES (?,?,?,?);";
        PreparedStatement pstmt = con.prepareStatement(sql);
        //setting parameter
        pstmt.setInt(1, rollNo);
        pstmt.setString(2, name);
        pstmt.setString(3, email);
        pstmt.setDouble(4, cpi);
        int rows = pstmt.executeUpdate();
        return rows;
    }
    // update
    //on the basis of rollno
    public static int updateData(int rollno, String email) throws SQLException {
        Connection con = getDbConnection();
        String sql = "UPDATE student SET email = ? WHERE rollno = ?;";
        PreparedStatement pstmt = con.prepareStatement(sql);
        //setting parameters
        pstmt.setString(1, email);
        pstmt.setInt(2, rollno);
        int rows = pstmt.executeUpdate();
        return rows;
    }

    // select
    //select all
    public static ResultSet selectAll() throws SQLException {
        Connection con = getDbConnection();
        String sql = "SELECT * FROM student;";
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        return rs;
    }

    //delete
    // on the basis of rollno
    public static int deleteData(int rollno) throws SQLException {
        Connection con = getDbConnection();
        String sql = "DELETE FROM student WHERE rollno = ?;";
        PreparedStatement pstmt = con.prepareStatement(sql);
        //set parameter
        pstmt.setInt(1, rollno);
        int rows = pstmt.executeUpdate();
        return rows;
    }
}
