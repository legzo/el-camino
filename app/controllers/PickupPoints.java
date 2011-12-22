package controllers;

import models.PickupPoint;
import play.Logger;
import play.mvc.Controller;

public class PickupPoints extends Controller {

	public static void addNew(String email, PickupPoint newPP) {
		Logger.info("Adding PP %s for %s", newPP.label, email);

		newPP.owner = Users.getUser(email);
		newPP.save();

		Users.displayPickupPoints(email);
	}
}
