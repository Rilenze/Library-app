package sample;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        BookDAO dao = BookDAO.getInstance();

        System.out.println("Enter the title or author of the book: ");
        Scanner entry = new Scanner(System.in);
        String search = entry.nextLine();
        for (Book b : dao.searchBook(search)) {
            System.out.println("ID: "+b.getId()+", Title: "+b.getTitle()+", Author: "+b.getAuthor());
        }

    }
}
