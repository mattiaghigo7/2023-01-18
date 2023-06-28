package it.polito.tdp.nyc.model;

import java.util.Objects;

public class VerticeVicini {

	private Location Location;
	private int vicini;
	
	public VerticeVicini(Location location, int vicini) {
		super();
		Location = location;
		this.vicini = vicini;
	}

	public Location getLocation() {
		return Location;
	}

	public int getVicini() {
		return vicini;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Location, vicini);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VerticeVicini other = (VerticeVicini) obj;
		return Objects.equals(Location, other.Location) && vicini == other.vicini;
	}

	@Override
	public String toString() {
		return Location.getName() + ": " + vicini;
	}
	
	
}
