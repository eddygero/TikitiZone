package dao;

import models.DB;
import models.Event;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oEventDao implements EventDao {

    private final Sql2o sql2o;
    public Sql2oEventDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Event event) {
        try (Connection con= DB.sql2o.open()){
            String sql="INSERT INTO events (title, location, eventtime, price, host, imageUrl, description) VALUES (:title,:location,:eventTime,:price,:host, :imageUrl, :description)";
            int id =(int) con.createQuery(sql,true)
                    .bind(event)
                    .executeUpdate()
                    .getKey();
            event.setId(id);
        }
    }

    @Override
    public List<Event> getAll() {
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM events")
                    .executeAndFetch(Event.class);
        }
    }

    @Override
    public Event findById(int id) {
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM events WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Event.class);
        }
    }

    @Override
    public void update( int id,String title, String location, String eventTime, int price, String host, String imageUrl, String description) {
        String sql = "UPDATE events SET (title, location, eventtime, price, host, imageUrl, description) = (:title, :location, :eventTime, :price, :host, :imageUrl, :description) WHERE id=:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("title", title)
                    .addParameter("location", location)
                    .addParameter("eventTime", eventTime)
                    .addParameter("price", price)
                    .addParameter("host", host)
                    .addParameter("id", id)
                    .addParameter("imageUrl", imageUrl)
                    .addParameter("description", description)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from events WHERE id = :id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
