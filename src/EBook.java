public class EBook extends Book {
    private String FileType;

    public EBook(String ISBN, String title, int year, double price, String FileType){
        super(ISBN, title, year, price);
        this.FileType = FileType;
    }

    @Override
    public double sell(int quantity, Customer customer){
        SendToMail(customer.getEmail());
        return quantity * price;
    }

    @Override
    public boolean isAvailable(){return true;}

    @Override
    public String getType(){return "EBook";}

    public void SendToMail(String email){
        // TO DO
    }

    @Override
    public void addBook() {}

    @Override
    public boolean DecreaseBooks(){return true;}
}
