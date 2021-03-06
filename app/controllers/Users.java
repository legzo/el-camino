package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Peer;
import models.User;
import play.Logger;
import play.data.validation.Required;
import play.mvc.Before;
import play.mvc.Controller;

public class Users extends Controller {

	@Before
	public static void addConnectedUser() {
		User connectedUser = getConnectedUser();
		renderArgs.put("connectedUser", connectedUser);
	}

	public static void displayUsers() {
		List<User> users = User.findAll();
		render(users);
	}

	public static void addUser() {
		new User("JTE").save();
		Logger.info("Adding user");
		displayUsers();
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
		userToUpdate.carModel = user.carModel;
		userToUpdate.availableSeats = user.availableSeats;
		userToUpdate.toTime = user.toTime;
		userToUpdate.fromTime = user.fromTime;

		// update address
		userToUpdate.address.formattedAddress = user.address.formattedAddress;
		userToUpdate.address.latitude = user.address.latitude;
		userToUpdate.address.longitude = user.address.longitude;

		userToUpdate.save();
		displayUsers();
	}

	public static void displayMyProfile() {
		User user = getConnectedUser();
		Logger.info("Displaying profile for %s", user);
		if (user == null) {
			displayUsers();
		}

		List<User> users = User.all().fetch();
		List<Peer> peers = new ArrayList<Peer>();

		for (User u : users) {
			if (!u.email.equals(user.email)) {
				Peer peer = new Peer();
				peer.user = u;
				peer.distance = user.address.distanceTo(u.address);

				peers.add(peer);
			}
		}

		Collections.sort(peers);

		render(user, peers);
	}

	public static void editMyProfile() {
		User user = getConnectedUser();
		Logger.info("Editing profile for %s", user);
		if (user == null) {
			displayUsers();
		}
		render(user);
	}

	public static void logUser(@Required String login, @Required String password) {

		if (validation.hasErrors()) {
			flash.error("Veuillez saisir un login et un mot de passe s'il vous plait");
			login();
		}

		User foundUser = User.find("byEmailAndPassword", login, password).first();
		if (foundUser == null) {
			flash.error("Les informations saisies ne correspondent à aucun utilisateur...");
		} else {
			session.put("connectedUser", foundUser.email);
		}
		login();
	}

	public static void login() {
		if (session.get("connectedUser") != null) {
			displayMyProfile();
		} else {
			render();
		}
	}

	public static void logout() {
		session.remove("connectedUser");
		displayUsers();
	}

	public static String getConnectedUserLogin() {
		return session.get("connectedUser");
	}

	public static User getConnectedUser() {
		return getUser(getConnectedUserLogin());
	}

}