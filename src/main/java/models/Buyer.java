package models;

import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class Buyer {
    public int id;
   public String name;
   public String  age;
   public String ticket;
    public String price;
   public String Location;
    public   String paymentModels;


    public Buyer(String name, String age, String ticket) {
        this.name = name;
        this.age = age;
        this.ticket = ticket;
        this.price = null;
        this.Location = null;
        this.paymentModels= null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void save() {
        if (this.name.equals(null)||this.age.equals(null)||this.ticket.equals(null)){
            throw new IllegalArgumentException("Fields are required");
        }
        try(Connection conn = DB.sql2o.open()){
            String sql = "INSERT INTO  buyer(name,age,ticket)VALUES(:name ,:age,:ticket)";
            this.id =(int) conn.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .addParameter("age",this.age)
                    .addParameter("ticket",this.ticket)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static Buyer find(int id){
        try (Connection con=DB.sql2o.open()){
            String sql= "SELECT * FROM  buyer WHERE id=:id";
            Buyer buyer=  con.createQuery(sql)
                    .addParameter("id",id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Buyer.class);
            return buyer;
        }
    }
    public static List<Buyer> all() {
        try(Connection conn = DB.sql2o.open()){
            String sql ="SELECT * FROM buyer";
            return conn.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Buyer.class);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buyer buyer = (Buyer) o;
        return id == buyer.id && Objects.equals(name, buyer.name) && Objects.equals(age, buyer.age) && Objects.equals(ticket, buyer.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, ticket);
    }
}
