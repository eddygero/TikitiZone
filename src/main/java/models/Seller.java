package models;

import org.sql2o.Connection;

public class Seller extends  Buyer{
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

    @Override
    public void save() {
        if (this.price .equals(null)||this.Location.equals(null)||this.paymentModels.equals(null)){
            throw new IllegalArgumentException("Fields are required");
        }
        try(Connection conn = DB.sql2o.open()){
            String sql = "INSERT INTO buyer(name,age,ticket,price,location)VALUES(:name ,:age,:ticket,:price,:location)";
            this.id =(int) conn.createQuery(sql,true)
                    .addParameter("name",this)
                    .addParameter("age",this.age)
                    .addParameter("ticket",this.ticket)
                    .executeUpdate()
                    .getKey();
        }
    }


}
