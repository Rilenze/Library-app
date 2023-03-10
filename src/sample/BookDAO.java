package sample;

import java.sql.*;
import java.util.ArrayList;

public class BookDAO {
    private static BookDAO instance = null;
    private Connection conn;
    private PreparedStatement searchQuery, insertQuery, updateQuery, deleteQuery, maxIdQuery;

    private BookDAO() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost/library";
        try {
            conn = DriverManager.getConnection(url, "root", "password");

            searchQuery = conn.prepareStatement("SELECT * FROM book WHERE title=? OR author=?");
            insertQuery = conn.prepareStatement("INSERT INTO book VALUES (?, ?, ?, ?)");
            maxIdQuery = conn.prepareStatement("SELECT max(id) FROM book");
            updateQuery = conn.prepareStatement("UPDATE book SET title=?, author=?, isbn=? WHERE id=?");
            deleteQuery = conn.prepareStatement("DELETE FROM book WHERE id=?");
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
            searchQuery.setString(1, search);
            searchQuery.setString(2, search);
            ResultSet rs = searchQuery.executeQuery();
            while (rs.next()) {
                books.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return books;
    }

    public void insertBook(Book b) {
        try {
            ResultSet maxId = maxIdQuery.executeQuery();
            if (maxId.next()) {
                b.setId(maxId.getInt(1) + 1);
            }
            else {
                b.setId(1);
            }
            insertQuery.setInt(1, b.getId());
            insertQuery.setString(2, b.getTitle());
            insertQuery.setString(3, b.getAuthor());
            insertQuery.setString(4, b.getIsbn());
            insertQuery.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateBook(Book b) {
        try {
            updateQuery.setInt(4, b.getId());
            updateQuery.setString(1, b.getTitle());
            updateQuery.setString(2, b.getAuthor());
            updateQuery.setString(3, b.getIsbn());
            updateQuery.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void deleteBook(Book b) {
        try {
            deleteQuery.setInt(1, b.getId());
            deleteQuery.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
