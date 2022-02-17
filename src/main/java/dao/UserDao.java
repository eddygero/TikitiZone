package dao;

import models.Event;
import models.User;

import java.util.List;

public interface UserDao {
    void add (User user);

    //read
    User findById(int id);
    List<Event> userEvent(int event_Id);

    //update
    void update(int id,String name, String phoneNumber, String ticket);

    //delete
    void deleteById(int id);
}
