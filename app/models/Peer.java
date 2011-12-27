package models;

public class Peer implements Comparable<Peer> {

	public User user;
	public double distance;

	@Override
	public int compareTo(Peer other) {
		return Double.compare(this.distance, other.distance);
	}

}
