import java.sql.*;
import java.util.Scanner;

public class LibraryManagementSystem {
    private static Connection connection;

    public static void main(String[] args) {
        try {
            connection = DatabaseConnection.getConnection();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Library Management System");
                System.out.println("1. Add a New Book");
                System.out.println("2. Update Book Details");
                System.out.println("3. Delete a Book");
                System.out.println("4. Search for a Book");
                System.out.println("5. Add a New Member");
                System.out.println("6. Loan a Book");
                System.out.println("7. Return a Book");
                System.out.println("8. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                switch (choice) {
                    case 1:
                        addNewBook(scanner);
                        break;
                    case 2:
                        updateBookDetails(scanner);
                        break;
                    case 3:
                        deleteBook(scanner);
                        break;
                    case 4:
                        searchForBook(scanner);
                        break;
                    case 5:
                        addNewMember(scanner);
                        break;
                    case 6:
                        loanBook(scanner);
                        break;
                    case 7:
                        returnBook(scanner);
                        break;
                    case 8:
                        System.out.println("Exiting...");
                        connection.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addNewBook(Scanner scanner) {
        try {
            System.out.print("Enter title: ");
            String title = scanner.nextLine();
            System.out.print("Enter author: ");
            String author = scanner.nextLine();
            System.out.print("Enter publisher: ");
            String publisher = scanner.nextLine();
            System.out.print("Enter year published: ");
            int yearPublished = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            String sql = "INSERT INTO books (title, author, publisher, year_published) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, publisher);
            pstmt.setInt(4, yearPublished);
            pstmt.executeUpdate();
            System.out.println("Book added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateBookDetails(Scanner scanner) {
        try {
            System.out.print("Enter book ID to update: ");
            int bookId = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            System.out.print("Enter new title: ");
            String title = scanner.nextLine();
            System.out.print("Enter new author: ");
            String author = scanner.nextLine();
            System.out.print("Enter new publisher: ");
            String publisher = scanner.nextLine();
            System.out.print("Enter new year published: ");
            int yearPublished = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            String sql = "UPDATE books SET title = ?, author = ?, publisher = ?, year_published = ? WHERE book_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, publisher);
            pstmt.setInt(4, yearPublished);
            pstmt.setInt(5, bookId);
            pstmt.executeUpdate();
            System.out.println("Book updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteBook(Scanner scanner) {
        try {
            System.out.print("Enter book ID to delete: ");
            int bookId = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            String sql = "DELETE FROM books WHERE book_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, bookId);
            pstmt.executeUpdate();
            System.out.println("Book deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void searchForBook(Scanner scanner) {
        try {
            System.out.println("Search by: 1. Title 2. Author 3. Year Published");
            int searchChoice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            String sql = null;
            PreparedStatement pstmt = null;
            switch (searchChoice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    sql = "SELECT * FROM books WHERE title LIKE ?";
                    pstmt = connection.prepareStatement(sql);
                    pstmt.setString(1, "%" + title + "%");
                    break;
                case 2:
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    sql = "SELECT * FROM books WHERE author LIKE ?";
                    pstmt = connection.prepareStatement(sql);
                    pstmt.setString(1, "%" + author + "%");
                    break;
                case 3:
                    System.out.print("Enter year published: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    sql = "SELECT * FROM books WHERE year_published = ?";
                    pstmt = connection.prepareStatement(sql);
                    pstmt.setInt(1, year);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    return;
            }
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Book ID: " + rs.getInt("book_id"));
                System.out.println("Title: " + rs.getString("title"));
                System.out.println("Author: " + rs.getString("author"));
                System.out.println("Publisher: " + rs.getString("publisher"));
                System.out.println("Year Published: " + rs.getInt("year_published"));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addNewMember(Scanner scanner) {
        try {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            System.out.print("Enter phone: ");
            String phone = scanner.nextLine();

            String sql = "INSERT INTO members (name, email, phone) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.executeUpdate();
            System.out.println("Member added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void loanBook(Scanner scanner) {
        try {
            System.out.print("Enter book ID: ");
            int bookId = scanner.nextInt();
            System.out.print("Enter member ID: ");
            int memberId = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            System.out.print("Enter loan date (YYYY-MM-DD): ");
            String loanDate = scanner.nextLine();
            System.out.print("Enter return date (YYYY-MM-DD): ");
            String returnDate = scanner.nextLine();

            String sql = "INSERT INTO loans (book_id, member_id, loan_date, return_date) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, bookId);
            pstmt.setInt(2, memberId);
            pstmt.setDate(3, Date.valueOf(loanDate));
            pstmt.setDate(4, Date.valueOf(returnDate));
            pstmt.executeUpdate();
            System.out.println("Book loaned successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void returnBook(Scanner scanner) {
        try {
            System.out.print("Enter loan ID: ");
            int loanId = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            System.out.print("Enter return date (YYYY-MM-DD): ");
            String returnDate = scanner.nextLine();

            String sql = "UPDATE loans SET return_date = ? WHERE loan_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setDate(1, Date.valueOf(returnDate));
            pstmt.setInt(2, loanId);
            pstmt.executeUpdate();
            System.out.println("Book returned successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
