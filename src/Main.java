import Model.*;
import Service.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PaperBook paperbook = new PaperBook("Paper book", "Paper book title", 2020, 100);
        EBook ebook = new EBook("Ebook", "Ebook book title", 2014, 95.6, "pdf");
        ShowCase showcasebook = new ShowCase("Showcase", "Showcase book title", 2011, 234.99);
        PaperBook paperBook2 = new PaperBook("Paper book 2", "Paper book 2 title", 2026, 142.63);
        Book[] books = {paperbook, ebook, showcasebook, paperBook2};
        // Call test methods
        {
            testNonAddedBookPurchase(paperbook);
        }
        {
            testBookAdd(paperbook);
            testBookAdd(ebook);
            testShowcaseBookAdd(showcasebook);
        }
        {
            testBookPurchase(paperbook);
            testBookPurchase(ebook);
            testShowcaseBookPurchase(showcasebook);
        }
        {
            testPaperBookMultiplePurchases();
            testEBookMultiplePurchases(ebook);
        }
        {
            testOutdatedBooksremoval(books, 2021);
        }
    }

    /** Test if I can buy a non added book */
    public static void testNonAddedBookPurchase(Book book){
        BookStore store = new BookStore();
        try{
            store.BuyBook(book.getISBN(), 1, "Mail@mail.com", "Street");  
        } catch(RuntimeException e){
            System.out.println(e.getMessage() + ": Success");
            return;
        }
        System.out.println("The book can be bought: Failed");
    }

    /** Test if I can add a book */
    public static void testBookAdd(Book book){
        BookStore store = new BookStore();
        try{
            store.addBook(book);
        } catch(RuntimeException e){
            System.out.println("Cannot add a " +  book.getType() + " Filed");
            return;
        }
        System.out.println(book.getType() + " is added: success");
    }

    /** Test if I can add a Showcase book */
    public static void testShowcaseBookAdd(Book book){
        BookStore store = new BookStore();
        try{
            store.addBook(book);
        } catch(RuntimeException e){
            System.out.println("Shwocase books are not added : Failed");
            return;
        }
        System.out.println("Cannot add a Showcase Book: Success");
    }

    /** Test if I can buy books */
    public static void testBookPurchase(Book book){
        BookStore store = new BookStore();
        store.addBook(book);
        try{
            store.BuyBook(book.getISBN(), 1, "Mail@mail.com", "Street");            
        } catch(RuntimeException e){
            System.out.println("Cannot buy a " +  book.getType() + " Failed");
            return;
        }
        System.out.println(book.getType() + " is bought: success");
    }

    /** Test if I can buy a Showcase book */
    public static void testShowcaseBookPurchase(Book book){
        BookStore store = new BookStore();
        store.addBook(book);
        try{
            store.BuyBook(book.getISBN(), 1, "Mail@mail.com", "Street");  
        }   catch(RuntimeException e){
            System.out.println("Cannot buy a Showcase Book: Success");
            return;
        }
        System.out.println("Shwocase books are bought : Failed");
    }


    /** Test if I can buy a paper book more times than its quantity */
    public static void testPaperBookMultiplePurchases(){
        BookStore store = new BookStore();
        PaperBook book = new PaperBook("Paper book", "Paper book title", 2020, 100);
        store.addBook(book);
        store.BuyBook(book.getISBN(), 1, "Mail@mail.com", "Street"); 
        try {
            store.BuyBook(book.getISBN(), 1, "Mail@mail.com", "Street"); 
        } catch(RuntimeException e){
            System.out.println("A paper book cannot be purchased more than the quantity: Success");
            return;
        }
        System.out.println("A paper book can be purchased more than the quantity: Failed");
    }

    /** Test if I can buy an Ebook more than one time */
    public static void testEBookMultiplePurchases(Book book){
        BookStore store = new BookStore();
        store.addBook(book);
        store.BuyBook(book.getISBN(), 1, "Mail@mail.com", "Street"); 
        try {
            store.BuyBook(book.getISBN(), 1, "Mail@mail.com", "Street"); 
        } catch(RuntimeException e){
            System.out.println("An Ebook cannot be purchased more than the quantity: Failed");
            return;
        }
        System.out.println("A paper book can be purchased more than the quantity: Success");
    }

    /** Test outdated books removal */
    public static void testOutdatedBooksremoval(Book[] books, int year){
        BookStore store = new BookStore();
        List<Book> outdatedBooks = new ArrayList<>();
        for (int i = 0; i < books.length; i++) {
            store.addBook(books[i]);
            if (books[i].getYear() < year){
                outdatedBooks.add(books[i]);
            }
        }
        List<Book> outdatedBookscheck = store.removeOutdatedBooks(year);
        if (outdatedBooks.size() != outdatedBookscheck.size()){
            System.out.println("Outdated Books were not successfully removed: Failed");
            return;
        }
        Collections.sort(outdatedBookscheck);
        Collections.sort(outdatedBooks);
        for (int i = 0; i < outdatedBookscheck.size(); i++) {
            if (!outdatedBookscheck.get(i).getISBN().equals(outdatedBooks.get(i).getISBN())){
                System.out.println("Outdated Books were not successfully removed: Failed20");
                return;
            }
        }
        System.out.println("Outdated Books were successfully removed: Success");
    }

}