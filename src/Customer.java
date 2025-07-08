public class Customer {
    private String email;
    private String address;

    public Customer(String email, String address){
        this.email = email;
        this.address = address;
    }

    public String getEmail() {return this.email;}
    public String getAddress() {return this.address;}
}
