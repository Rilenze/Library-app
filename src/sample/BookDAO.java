package sample;

import java.sql.*;
import java.util.ArrayList;

public class BookDAO {
    private static BookDAO instance = null;
    private Connection conn;
    private PreparedStatement ps;

    private BookDAO() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost/library";
        try {
            conn = DriverManager.getConnection(url, "root", "password");

            ps = conn.prepareStatement("SELECT * FROM book WHERE title=? OR author=?");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static BookDAO getInstance() throws ClassNotFoundException {
        if (instance == null) instance = new BookDAO();
        return instance;
    }

    public ArrayList<Book> searchBook(String search) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            ps.setString(1, search);
            ps.setString(2, search);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                books.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return books;
    }
}
