package dao;

import models.DB;
import models.Event;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oUserDao implements UserDao {

    private final Sql2o sql2o;
    public Sql2oUserDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(User user) {
        try (Connection con= DB.sql2o.open()){
            String sql="INSERT INTO users (name, phonenumber, ticket, event_Id) VALUES (:name,:phoneNumber, :ticket, :event_Id)";
            int id =(int) con.createQuery(sql,true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        }
    }

    @Override
    public User findById(int id) {
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM users WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        }
    }

    @Override
    public void update( int id,String name, String phoneNumber, String ticket) {
        String sql = "UPDATE users SET (name, phonenumber, ticket) = (:name,:phonenumber,:ticket) WHERE id=:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("phonenumber", phoneNumber)
                    .addParameter("ticket", ticket)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Event> userEvent(int event_id) {
        String sql = "SELECT * FROM users where event_Id = :event_Id";
        try (Connection con = DB.sql2o.open()){
           return con.createQuery(sql)
                .addParameter("event_Id", event_id)
                .executeAndFetch(Event.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from users WHERE id = :id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
