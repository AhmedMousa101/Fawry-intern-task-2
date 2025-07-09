package Model;
import Service.Customer;
public class ShowCase extends Book {
    public ShowCase(String ISBN, String title, int year, double price) {
        super(ISBN, title, year, price);
    }

    @Override
    public double sell(int quantity, Customer customer) {
        throw new RuntimeException("Can not be bought");
    }

    @Override
    public boolean isAvailable(){return false;}

    @Override
    public String getType(){return "ShowCase";}

    @Override
    public void addBook() {}

    @Override
    public boolean DecreaseBooks(){return true;}
}
