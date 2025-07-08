import java.util.*;

public class BookStore {
    Map<String, Book> books = new TreeMap<>();
    Set<Book> booksByYear = new TreeSet<>(); // sort the books by year

    public void addBook(Book book){
        String CurrentISBN = book.getISBN();
        if (books.containsKey(CurrentISBN)){
            Book CurrentBook = books.get(CurrentISBN);
            booksByYear.remove(CurrentBook);
            CurrentBook.addBook();
            booksByYear.add(CurrentBook);
            books.put(CurrentISBN, CurrentBook);
        }else {
            books.put(CurrentISBN, book);
            booksByYear.add(book);
        }
    }

    public void removeBook(String ISBN){
        if (!books.containsKey(ISBN)){
            throw new RuntimeException("No such book is found");
        }
        Book CurrentBook = books.get(ISBN);
        if (!CurrentBook.DecreaseBooks()){
            Erase(CurrentBook);
        }
    }

    public List<Book> removeOutdatedBooks(int endYear){
        List<Book> outdatedBooks = new ArrayList<>();
        Iterator<Book> it = booksByYear.iterator();
        while(it.hasNext()){
            Book book = it.next();
            if (book.year < endYear){
                outdatedBooks.add(book);
                books.remove(book.getISBN());
                it.remove();
            }else{
                break;
            }
        }
        return outdatedBooks;
    }

    public void Erase(Book book){
        String ISBN = book.getISBN();
        books.remove(ISBN);
        booksByYear.remove(book);
    }

    public double BuyBook(String ISBN, int quantity, String email, String address){
        if (!books.containsKey(ISBN)){
            throw new RuntimeException("No such book is found");
        }
        Customer customer = new Customer(email, address);
        Book book = books.get(ISBN);
        double price = book.sell(quantity, customer);
        if (!book.isAvailable()){
            Erase(book);
        }
        return price;
    }

}
