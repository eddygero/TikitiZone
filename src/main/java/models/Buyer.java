package models;

public class Buyer {
    String name;
    String  age;
    String ticket;

    public Buyer(String name, String age, String ticket) {
        this.name = name;
        this.age = age;
        this.ticket = ticket;
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
}
