package models;

import java.text.DecimalFormat;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Address {

	@Transient
	private final static DecimalFormat distanceFormat = new DecimalFormat("#.##");

	public String formattedAddress;
	public float latitude;
	public float longitude;

	public Address() {
	}

	public Address(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public double distanceTo(Address other) {
		double distance = -1f;

		double theta = this.longitude - other.longitude;
		distance = Math.sin(Math.toRadians(this.latitude)) * Math.sin(Math.toRadians(other.latitude))
				+ Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(other.latitude))
				* Math.cos(Math.toRadians(theta));
		distance = Math.acos(distance);
		distance = Math.toDegrees(distance);
		distance = distance * 60 * 1.1515;
		distance = distance * 1.609344;

		return distance;
	}

	public String distanceToAsString(Address other) {
		double distance = distanceTo(other);
		return String.format("%s kms", distanceFormat.format(distance));
	}

}
