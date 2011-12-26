package models;

import java.text.DecimalFormat;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import play.db.jpa.Model;

@Entity
public class PickupPoint extends Model {

	@Transient
	private final static DecimalFormat distanceFormat = new DecimalFormat("#.##");

	public String label;

	public String address;

	public float latitude;
	public float longitude;

	@Embedded
	public Instant time;

	public Direction direction;

	@ManyToOne
	public User owner;

	public PickupPoint(String label, Direction direction) {
		this(null, label, direction);
	}

	public PickupPoint(User owner, String label, Direction direction) {
		super();
		this.label = label;
		this.direction = direction;
		this.owner = owner;

		if (owner != null) {
			owner.pickupPoints.add(this);
		}
	}

	public double distanceTo(PickupPoint otherPP) {
		double distance = -1f;

		double theta = this.longitude - otherPP.longitude;
		distance = Math.sin(Math.toRadians(this.latitude)) * Math.sin(Math.toRadians(otherPP.latitude))
				+ Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(otherPP.latitude))
				* Math.cos(Math.toRadians(theta));
		distance = Math.acos(distance);
		distance = Math.toDegrees(distance);
		distance = distance * 60 * 1.1515;
		distance = distance * 1.609344;

		return distance;
	}

	public String distanceToAsString(PickupPoint otherPP) {
		double distance = distanceTo(otherPP);
		return String.format("%s kms", distanceFormat.format(distance));
	}

}
