package controllers;

import java.util.List;

import models.User;
import play.Logger;
import play.mvc.Controller;

public class Users extends Controller {

	public static void displayUsers() {
		List<User> users = User.findAll();
		render(users);
	}

	public static void addUser() {
		new User("JTE").save();
		Logger.info("Adding user");
		displayUsers();
	}

	public static void displayPickupPoints(String email) {
		getUserAndRender(email);
	}

	private static void getUserAndRender(String email) {
		List<User> users = User.find("byEmail", email).fetch();

		if (users.size() == 1) {
			User user = users.iterator().next();
			Logger.info("Displaying: %s", user);
			render(user);
		} else {
			Logger.info("No user found");
			// TODO handle error
		}
	}

	public static void displayUser(String email) {
		getUserAndRender(email);
	}

	public static void updateUser(Long id, User user) {
		Logger.info("To update: %s", user);

		// update all except password
		User userToUpdate = User.findById(id);
		userToUpdate.email = user.email;
		userToUpdate.firstName = user.firstName;
		userToUpdate.lastName = user.lastName;

		userToUpdate.save();
		displayUsers();
	}

	public static void displayMyProfile() {
		displayUser("legzo@gmail.com");
	}
}