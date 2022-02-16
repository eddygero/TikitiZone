import models.Buyer;
import models.Event;
import models.Seller;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

//route for the homepage
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


//route for my Events Page
        get("/myEvents", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "myEvents.hbs");
        }, new HandlebarsTemplateEngine());


        //route for my Events Page
        get("/seller", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "seller.hbs");
        }, new HandlebarsTemplateEngine());

//route for getting the form
        get("/create/buyer/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "signup.hbs");
        }, new HandlebarsTemplateEngine());
        //creates a buyer
        post("/create/Buyer", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String type =request.queryParams("type");
            String name = request.queryParams("name");
            String age = request.queryParams("age");
            String ticket = request.queryParams("ticket");
            String location = request.queryParams("location");
            String price = request.queryParams("price");
            String paymentModels = request.queryParams("paymentModels");
            model.put("name",name);
            model.put("age",age);
            model.put("ticket",ticket);
            model.put("location",location);
            model.put("price",price);
            model.put("paymentModels",paymentModels);
            if (type.equals(Seller.Person2)){
                Seller seller = new Seller(name, age,ticket,Seller.Person2,location,price,paymentModels);
                seller.save();

            }else{
                Buyer buyer = new Buyer(name, age, ticket,type);
                buyer.save();
            }
            response.redirect("");
            return new ModelAndView(model, "signup.hbs");
        }, new HandlebarsTemplateEngine());


//route for viewing the  buyers
        get("/event/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("buyers", Buyer.all());
            return new ModelAndView(model, "events-form.hbs");
        }, new HandlebarsTemplateEngine());

//route for creating events
        post("/create/events", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int Buyer_id = Integer.parseInt(request.queryParams("location"));
            String title = request.queryParams("title");
            String location = request.queryParams("location");
            int price =Integer.parseInt(request.queryParams("price"));
            String imageUrl = request.queryParams("imageUrl");
            String host = request.queryParams("host");
            String description = request.queryParams("description");
            Event event = new Event( Buyer_id,title,location,price,host,imageUrl,description);
            event.save();
            response.redirect("/view/events");
            return new ModelAndView(model, "events-form.hbs");
        }, new HandlebarsTemplateEngine());

         //route for displaying the events
        get("/view/Events", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Event> events = Event.all();
            List<String>buyers = new ArrayList<>();
            List<String>type = new ArrayList<>();
            for (Event event: events){
                String buyer_name = Buyer.find(event.getBuyer_id()).getName();
                String buyer_type =Buyer.find(event.getBuyer_id()).getType();
                buyers.add(buyer_name);
                type.add(buyer_type);
            }
            model.put("buyers", buyers);
            model.put("events", events);
            model.put("type", type);
            return new ModelAndView(model, "myEvents.hbs");
        }, new HandlebarsTemplateEngine());


    }

}
