package Model;
import Service.Customer;
import Service.Shipping;

public class PaperBook extends Book {
    private int InStock;

    public PaperBook(String ISBN, String title, int year, double price) {
        super(ISBN, title, year, price);
        InStock += 1;
    }

    @Override
    public double sell(int quantity, Customer customer){
        if (InStock < quantity){
            throw new RuntimeException("Not Available");
        }
        InStock -= quantity;
        Shipping.ship(this, customer.getAddress());
        return quantity * price;
    }

    @Override
    public boolean isAvailable() {
        return InStock > 0;
    }

    @Override
    public String getType(){return "PaperBook";}

    public int getQuantity() {return InStock;}

    @Override
    public void addBook(){
        InStock += 1;
    }

    @Override
    public boolean DecreaseBooks(){
        InStock -= 1;
        return InStock > 0;
    }
}