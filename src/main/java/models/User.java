package models;

public class User {
    private String name;
    private String phoneNumber;
    private String ticket;
    private int event_Id;
    private int id;

    public User(String name, String phoneNumber, String ticket, int event_Id) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.ticket = ticket;
        this.event_Id= event_Id;
    }

    public int getEvent_Id() {
        return event_Id;
    }

    public void setEvent_Id(int event_Id) {
        this.event_Id = event_Id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getTicket() {
        return ticket;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
}
