package models;

public class Seller  extends Buyer{
    private int id;
    private String price;
    private String Location;
    private  String paymentmodels;

    public Seller(String name, String age, String ticket, String price, String location, String paymentmodels) {
        super(name, age, ticket);
        this.price = price;
        Location = location;
        this.paymentmodels = paymentmodels;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getPaymentmodels() {
        return paymentmodels;
    }

    public void setPaymentmodels(String paymentmodels) {
        this.paymentmodels = paymentmodels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
