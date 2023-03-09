package sample;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");// write your code here
        String url = "jdbc:mysql://localhost/library";
        try {
            Connection conn = DriverManager.getConnection(url, "root", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM book");

            while (rs.next()) {
                int id = rs.getInt(1);
                String title  = rs.getString(2);
                System.out.println("ID: "+id+" Title: "+title);
            }
            conn.close();
        } catch (SQLException throwables) {
            System.out.println("Error while working with database");
            throwables.printStackTrace();
        }
    }
}
