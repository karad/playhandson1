package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
        return ok(index.render("Hello world"));
    }

    public static Result hello() {
        session("username", "kara_d");
        return ok(index.render("Hello world"));
    }
  
}
