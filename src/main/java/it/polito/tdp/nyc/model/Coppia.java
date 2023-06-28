package it.polito.tdp.nyc.model;

import java.util.Objects;

public class Coppia {
	
	private Location l1;
	private Location l2;
	
	public Coppia(Location l1, Location l2) {
		super();
		this.l1 = l1;
		this.l2 = l2;
	}
	public Location getL1() {
		return l1;
	}
	public Location getL2() {
		return l2;
	}
	@Override
	public int hashCode() {
		return Objects.hash(l1, l2);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coppia other = (Coppia) obj;
		return Objects.equals(l1, other.l1) && Objects.equals(l2, other.l2);
	}
}
