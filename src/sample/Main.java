package sample;

import java.util.Scanner;

public class Main {
    private static Scanner entry = new Scanner(System.in);
    private static BookDAO dao;


    public static void main(String[] args) throws ClassNotFoundException {
        dao = BookDAO.getInstance();

        int option = 0;
        do {
            System.out.println("Enter the option:\n1 - search\n2 - insert\n3 - update\n4 - delete\n0 - end of program");
            option = entry.nextInt();
            if (entry.hasNextLine()) entry.nextLine();
            switch (option) {
                case 1:
                    search();
                    break;
                case 2:
                    insert();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    delete();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("You haven't chose the right option");
            }

        } while(option != 0);
    }

    private static void delete() {
        System.out.println("Enter id of the book you want to change: ");
        int id = entry.nextInt();
        dao.deleteBook(new Book(id, "", "", ""));
    }

    private static void update() {
        System.out.println("Enter id of the book you want to change: ");
        int id = entry.nextInt();
        if (entry.hasNextLine()) entry.nextLine();
        System.out.println("Enter the title of the book: ");
        String title = entry.nextLine();
        System.out.println("Enter the author of the book: ");
        String author = entry.nextLine();
        System.out.println("Enter the isbn of the book: ");
        String isbn = entry.nextLine();
        dao.updateBook(new Book(id, title, author, isbn));
    }

    private static void insert() {
        System.out.println("Enter the title of the book: ");
        String title = entry.nextLine();
        System.out.println("Enter the author of the book: ");
        String author = entry.nextLine();
        System.out.println("Enter the isbn of the book: ");
        String isbn = entry.nextLine();
        dao.insertBook(new Book(0, title, author, isbn));
    }

    private static void search() {
        System.out.println("Enter the title or author of the book: ");
        String search = entry.nextLine();
        for (Book b : dao.searchBook(search)) {
            System.out.println("ID: "+b.getId()+", Title: "+b.getTitle()+", Author: "+b.getAuthor());
        }
    }
}
