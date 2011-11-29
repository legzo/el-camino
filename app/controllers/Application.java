package controllers;

import java.util.List;

import models.User;
import play.mvc.Controller;

public class Application extends Controller {

	public static void index() {
		List<User> users = User.findAll();
		render(users);
	}

	public static void addUser() {
		new User("", "", "JTE").save();

		index();
	}

}