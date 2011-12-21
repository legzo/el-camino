package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class PickupPoint extends Model {

	public String label;

	public float latitude;
	public float longitude;

	public Direction direction;

	@ManyToOne
	public User owner;

	public PickupPoint(User owner, String label, Direction direction) {
		super();
		this.label = label;
		this.direction = direction;
		this.owner = owner;

		owner.pickupPoints.add(this);
	}

}
