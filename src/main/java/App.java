import dao.Sql2oEventDao;
import dao.Sql2oUserDao;
import models.DB;
import models.Event;
import models.User;
import org.sql2o.Connection;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
public class App{
static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
        return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
        }
public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        Sql2oEventDao eventDao = new Sql2oEventDao(DB.sql2o);
        Sql2oUserDao userDao = new Sql2oUserDao(DB.sql2o);
        Connection conn = DB.sql2o.open();

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Event> allEvents = eventDao.getAll();
            model.put("events", allEvents);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //form to post Event
        get("/events/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "eventform.hbs");
        }, new HandlebarsTemplateEngine());

        //process event form
        post("/events/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String title = request.queryParams("title");
            String location = request.queryParams("location");
            int price = Integer.parseInt(request.queryParams("price"));
            String time = request.queryParams("time");
            String host = request.queryParams("host");
            String imageUrl = request.queryParams("imageUrl");
            String description = request.queryParams("description");
            Event event = new Event(title, location, time, price, host,imageUrl,description);
            eventDao.add(event);
            model.put("events", event);

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        // event description
        get("/events/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int eventId = Integer.parseInt(request.params("id"));
            Event foundEvent = eventDao.findById(eventId);
            model.put("events", foundEvent);
            return new ModelAndView(model, "eventDetails.hbs");
        }, new HandlebarsTemplateEngine());

        post("/events/:id/user/ticket" , (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String phoneNumber = request.queryParams("phoneNumber");
            String ticket = request.queryParams("ticket");
            int eventId = Integer.parseInt(request.params("id"));

            User user = new User(name,phoneNumber,ticket,eventId);
            userDao.add(user);
            Event foundEvent = eventDao.findById(eventId);
            model.put("events",foundEvent);
            model.put("user",user);
            return new ModelAndView(model,"success-user.hbs");
        }, new HandlebarsTemplateEngine());

        get("/events/:id/delete",(request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int eventId = Integer.parseInt(request.params("id"));
            Event foundEvent = eventDao.findById(eventId);
            eventDao.deleteById(foundEvent.getId());
            return new ModelAndView(model, "success-delete.hbs");
        }, new HandlebarsTemplateEngine());

        //show user details
        get("/events/:id/user/ticket/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            int userId = Integer.parseInt(request.params("id"));
            User ticket = userDao.findById(userId);
            Event found = eventDao.findById(ticket.getEvent_Id());
            model.put("event",found);
            model.put("userTicket", ticket);
            return new ModelAndView(model, "user.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
