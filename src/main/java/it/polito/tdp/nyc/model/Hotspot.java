package it.polito.tdp.nyc.model;

public class Hotspot {
	private Integer objectId;
	private String borough;
	private String type;
	private String provider;
	private String name;
	private String location;
	private double latitude;
	private double longitude;
	private String location_T;
	private String city;
	private String SSID;
	private String sourceID;
	private int boroCode;
	private String boroName;
	private String NTACode;
	private String NTAName;
	private int postcode;
	
	

	public Hotspot(Integer objectId, String borough, String type, String provider, String name, String location,
			double latitude, double longitude, String location_T, String city, String sSID, String sourceID,
			int boroCode, String boroName, String nTACode, String nTAName, int postcode) {
		super();
		this.objectId = objectId;
		this.borough = borough;
		this.type = type;
		this.provider = provider;
		this.name = name;
		this.location = location;
		this.latitude = latitude;
		this.longitude = longitude;
		this.location_T = location_T;
		this.city = city;
		SSID = sSID;
		this.sourceID = sourceID;
		this.boroCode = boroCode;
		this.boroName = boroName;
		NTACode = nTACode;
		NTAName = nTAName;
		this.postcode = postcode;
	}
	

	public Integer getObjectId() {
		return objectId;
	}



	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}



	public String getBorough() {
		return borough;
	}



	public void setBorough(String borough) {
		this.borough = borough;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getProvider() {
		return provider;
	}



	public void setProvider(String provider) {
		this.provider = provider;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public double getLatitude() {
		return latitude;
	}



	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}



	public double getLongitude() {
		return longitude;
	}



	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}



	public String getLocation_T() {
		return location_T;
	}



	public void setLocation_T(String location_T) {
		this.location_T = location_T;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getSSID() {
		return SSID;
	}



	public void setSSID(String sSID) {
		SSID = sSID;
	}



	public String getSourceID() {
		return sourceID;
	}



	public void setSourceID(String sourceID) {
		this.sourceID = sourceID;
	}



	public int getBoroCode() {
		return boroCode;
	}



	public void setBoroCode(int boroCode) {
		this.boroCode = boroCode;
	}



	public String getBoroName() {
		return boroName;
	}



	public void setBoroName(String boroName) {
		this.boroName = boroName;
	}



	public String getNTACode() {
		return NTACode;
	}



	public void setNTACode(String nTACode) {
		NTACode = nTACode;
	}



	public String getNTAName() {
		return NTAName;
	}



	public void setNTAName(String nTAName) {
		NTAName = nTAName;
	}



	public int getPostcode() {
		return postcode;
	}



	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((objectId == null) ? 0 : objectId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hotspot other = (Hotspot) obj;
		if (objectId == null) {
			if (other.objectId != null)
				return false;
		} else if (!objectId.equals(other.objectId))
			return false;
		return true;
	}
	
	
	
}
