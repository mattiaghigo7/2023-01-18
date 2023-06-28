package it.polito.tdp.nyc.model;

import java.util.Objects;

import com.javadocmd.simplelatlng.LatLng;

public class Location {

	private String name;
	private LatLng coords;
	
	public Location(String name, LatLng coords) {
		super();
		this.name = name;
		this.coords = coords;
	}

	public String getName() {
		return name;
	}

	public LatLng getCoords() {
		return coords;
	}

	@Override
	public int hashCode() {
		return Objects.hash(coords, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		return Objects.equals(coords, other.coords) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return name;
	}
	
}
