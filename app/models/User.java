package models;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class User extends Model {

	public String email;
	public String password;
	public String firstName;
	public String lastName;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "hour", column = @Column(name = "toHour")),
		@AttributeOverride(name = "minute", column = @Column(name = "toMinute"))
	})
	public Instant toTime;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "hour", column = @Column(name = "fromHour")),
		@AttributeOverride(name = "minute", column = @Column(name = "fromMinute"))
	})
	public Instant fromTime;

	@Embedded
	public Address address = new Address();

	public String carModel = "voiture non identifiée";
	public int availableSeats = 0;

	public User(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	public String getFullName() {
		return String.format("%s %s", firstName, lastName);
	}

}