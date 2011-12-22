package models;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class User extends Model {

	public String email;
	public String password;
	public String firstName;
	public String lastName;

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	public Collection<PickupPoint> pickupPoints = new HashSet<PickupPoint>();

	public User(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}

	public String getFullName() {
		return String.format("%s %s", firstName, lastName);
	}

}