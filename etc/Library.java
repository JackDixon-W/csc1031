package etc;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public Book searchByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public void listBooks() {
        for (Book book : books) {
            System.out.println("-----" + 
                                "\nTitle: " + book.getTitle() +
                                "\nAuthor: " + book.getAuthor() +
                                "\nIsbn: " + book.getIsbn() +
                                "\n");
        }
    }
}

class Book {
    private String title;
    private String author;
    private int isbn;
    Book(String title, String author, int isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getIsbn() {
        return isbn;
    }
}

class Main {
    public static void main(String[] args) {
        Library library = new Library();
        // Adding books
        library.addBook(new Book("1984", "George Orwell", 1234));
        library.addBook(new Book("Brave New World", "Aldous Huxley", 50));
        library.addBook(new Book("Fahrenheit 451", "Ray Bradbury", 111));
        // Listing all books
        System.out.println("All Books in Library:");
        library.listBooks();
        // Searching for a book
        System.out.println("\nSearch Result: " + library.searchByTitle("1984"));
    }
}