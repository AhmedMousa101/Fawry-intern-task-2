package Model;
import Service.Customer;
public abstract class Book implements Comparable<Book> {
    protected String ISBN, title;
    protected int year;
    protected double price;

    public Book(String ISBN, String title, int year, double price) {
        this.ISBN = ISBN;
        this.title = title;
        this.year = year;
        this.price = price;
    }

    public String getISBN() {return ISBN;}
    public String getTitle() {return title;}
    public int getYear() {return year;}
    public double getPrice() {return price;}

    public abstract double sell(int quantity, Customer customer);
    public abstract boolean isAvailable();
    public abstract String getType();
    public abstract void addBook();
    public abstract boolean DecreaseBooks();

    @Override
    public int compareTo(Book other) {
        int Year_Compare = Integer.compare(this.getYear(), other.getYear());
        if (Year_Compare != 0){
            return Year_Compare;
        }

        int Title_Compare = this.getTitle().compareTo(other.getTitle());
        if (Title_Compare != 0){
            return Title_Compare;
        }

        return this.getISBN().compareTo(other.getISBN());
    }
}
