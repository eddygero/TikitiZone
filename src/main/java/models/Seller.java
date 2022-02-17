package models;

import org.sql2o.Connection;

public class Seller extends Buyer {
    private String price;
    private String location;
    private String paymentModels;
    public static final String Person2 = "seller";
    public static final String PAYMENT_MPESA="M-pesa paybil 23456";
    public static final String PAYMENT_EQUITY="Equity a/c 09897667947";
    public static final String PAYMENT_KCB="KCB  a/c 45536289374";


    public Seller(String name, String age, String ticket, String type, String price, String location, String paymentModels) {
        super(name, age, ticket, type);
        this.price = price;
        this.location = location;
        this.paymentModels = paymentModels;
        this.type= Person2;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPaymentmodels() {
        return paymentModels;
    }

    public void setPaymentmodels(String paymentModels) {
        this.paymentModels = paymentModels;
    }

    @Override
    public void save() {
        if (this.price.equals(null) || this.location.equals(null) || this.paymentModels.equals(null)) {
            throw new IllegalArgumentException("Fields are required");
        }
        try (Connection conn = DB.sql2o.open()) {
            String sql = "INSERT INTO buyer(name,age,ticket,price,location)VALUES(:name ,:age,:ticket,:price,:location)";
            this.id = (int) conn.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("age", this.age)
                    .addParameter("ticket", this.ticket)
                    .addParameter("price", this.price)
                    .addParameter("location", this.location)
                    .executeUpdate()
                    .getKey();
        }
    }


}
