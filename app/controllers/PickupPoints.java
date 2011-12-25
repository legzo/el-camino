package controllers;

import models.PickupPoint;
import play.Logger;
import play.mvc.Controller;

public class PickupPoints extends Controller {

	public static void addNew(String email, PickupPoint newPP) {
		Logger.info("Adding PP %s for %s", newPP.label, email);

		Logger.info("Latitude %s, Longitude %s", newPP.latitude, newPP.longitude);

		newPP.owner = Users.getConnectedUser();
		newPP.save();

		Users.displayMyPickupPoints();
	}
}
