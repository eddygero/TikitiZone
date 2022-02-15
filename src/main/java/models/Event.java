package models;


import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Event {
    private  int id;
    private int Buyer_id;
    private String title;
    private String location;
    private Date date = new Date();
    private Timestamp eventTime;
    private int price;
    private String host;
    private String ImageUrl;
    private String description;

    public Event(int buyer_id,  String title, String location, int price, String host, String imageUrl, String description) {
        Buyer_id = buyer_id;
        this.title = title;
        this.location = location;
        this.eventTime = new Timestamp(date.getTime());;
        this.price = price;
        this.host = host;
       this. ImageUrl = imageUrl;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuyer_id() {
        return Buyer_id;
    }

    public void setBuyer_id(int buyer_id) {
        Buyer_id = buyer_id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Timestamp getEventTime() {
        return eventTime;
    }

    public void setEventTime(Timestamp eventTime) {
        this.eventTime = eventTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void save() {
        if (this.title.equals(null)||this.location.equals(null)||(this.host.equals(null)||this.ImageUrl.equals(null))){
            throw new IllegalArgumentException("Fields are required");
        }
        try(Connection conn = DB.sql2o.open()){
            String sql = "INSERT INTO events (title,location,price,host,imageUrl,description,Buyer_id,eventTime)VALUES(:title,:location,:price,:host,:imageUrl,:description, :Buyer_id,:eventTime)";
            this.id =(int) conn.createQuery(sql,true)
                    .addParameter("title",this.title)
                    .addParameter("location",this.location)
                    .addParameter("price",this.price)
                    .addParameter("host",this.host)
                    .addParameter("imageUrl",this.ImageUrl)
                    .addParameter("description",this.description)
                    .addParameter("Buyer_id",this.Buyer_id)
                    .addParameter("eventTime",this.eventTime)
                    .executeUpdate()
                    .getKey();
        }
        }
    public static List<Event> all() {
        try(Connection conn = DB.sql2o.open()){
            String sql ="SELECT * FROM evens";
            return conn.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Event.class);
        }
    }
    public static Event find(int id){
        try (Connection con=DB.sql2o.open()){
            String sql= "SELECT * FROM events WHERE id=:id";
            Event event=  con.createQuery(sql)
                    .addParameter("id",id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Event.class);
            return event;
        }
    }

}

