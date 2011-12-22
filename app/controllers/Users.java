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
		User user = getUser(email);
		render(user);
	}

	public static void displayUser(String email) {
		User user = getUser(email);
		render(user);
	}

	public static User getUser(String email) {
		List<User> users = User.find("byEmail", email).fetch();
		User user = null;
		if (users.size() == 1) {
			user = users.iterator().next();
			Logger.info("Displaying: %s", user);
		} else {
			Logger.info("No user found");
			// TODO handle error
		}
		return user;
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
		User user = getUser(getConnectedUserLogin());
		render(user);
	}

	public static void displayMyPickupPoints() {
		User user = getUser(getConnectedUserLogin());
		render(user);
	}

	public static String getConnectedUserLogin() {
		return "legzo@gmail.com";
	}

	public static User getConnectedUser() {
		return getUser(getConnectedUserLogin());
	}

}