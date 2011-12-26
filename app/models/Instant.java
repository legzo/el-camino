package models;

import javax.persistence.Embeddable;

import play.data.validation.Max;
import play.data.validation.Min;

@Embeddable
public class Instant {

	@Min(value = 0)
	@Max(value = 24)
	public int hour;

	@Min(value = 0)
	@Max(value = 60)
	public int minute;

	public Instant(int hour, int minute) {
		super();
		this.hour = hour;
		this.minute = minute;
	}

	public String toString() {
		return String.format("%s:%s", hour, minute);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + hour;
		result = prime * result + minute;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		Instant other = (Instant) obj;
		if (hour != other.hour)
			return false;
		if (minute != other.minute)
			return false;
		return true;
	}

}
