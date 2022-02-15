package models;


import java.sql.Timestamp;
import java.util.Date;

public class Event {
    private  int id;
    private String title;
    private String location;
    private Date date = new Date();
    private Timestamp eventTime;
    private int price;
    private String host;
    private String ImageUrl;
    private String description;

    public Event(String title, String location,  int price, String host, String imageUrl, String description) {
        this.title = title;
        this.location = location;
        this.eventTime = new Timestamp(date.getTime());
        this.price = price;
        this.host = host;
        ImageUrl = imageUrl;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
}
