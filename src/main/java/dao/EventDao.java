package dao;

import models.Event;

import java.util.List;

public interface EventDao {

    void add (Event event);

    //read
    List<Event> getAll();
    Event findById(int id);

    //update
    void update(int id,String title, String location, String eventTime, int price, String host, String imageUrl, String description);

    //delete
    void deleteById(int id);
}
