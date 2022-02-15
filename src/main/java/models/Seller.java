package models;

public class Seller  extends Buyer{
    private String price;
    private String Location;
    private  String paymentModels;

    public Seller(String name, String age, String ticket, String price, String location, String paymentmodels) {
        super(name, age, ticket);
        this.price = price;
        Location = location;
        this.paymentModels = paymentmodels;
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
        return paymentModels;
    }

    public void setPaymentmodels(String paymentModels) {
        this.paymentModels = paymentModels;
    }
}
