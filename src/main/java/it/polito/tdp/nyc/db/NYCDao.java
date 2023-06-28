package it.polito.tdp.nyc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.javadocmd.simplelatlng.LatLng;

import it.polito.tdp.nyc.model.Coppia;
import it.polito.tdp.nyc.model.Hotspot;
import it.polito.tdp.nyc.model.Location;

public class NYCDao {
	
	public List<Hotspot> getAllHotspot(){
		String sql = "SELECT * FROM nyc_wifi_hotspot_locations";
		List<Hotspot> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Hotspot(res.getInt("OBJECTID"), res.getString("Borough"),
						res.getString("Type"), res.getString("Provider"), res.getString("Name"),
						res.getString("Location"),res.getDouble("Latitude"),res.getDouble("Longitude"),
						res.getString("Location_T"),res.getString("City"),res.getString("SSID"),
						res.getString("SourceID"),res.getInt("BoroCode"),res.getString("BoroName"),
						res.getString("NTACode"), res.getString("NTAName"), res.getInt("Postcode")));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
	public List<String> getAllProvider(){
		String sql = "SELECT DISTINCT n.Provider "
				+ "FROM nyc_wifi_hotspot_locations n "
				+ "ORDER BY n.Provider";
		List<String> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getString("n.Provider"));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
	public List<Location> getVertici(String p){
		String sql = "SELECT DISTINCT n.Location, AVG(n.Latitude) AS lat, AVG(n.Longitude) AS lon "
				+ "FROM nyc_wifi_hotspot_locations n "
				+ "WHERE n.Provider=? "
				+ "GROUP BY n.Location "
				+ "ORDER BY n.Location";
		List<Location> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, p);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Location(res.getString("n.Location"),new LatLng(res.getDouble("lat"), res.getDouble("lon"))));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
	public List<Coppia> getArchi(String p, Map<String,Location> vMap){
		String sql = "SELECT DISTINCT n1.Location, n2.Location "
				+ "FROM nyc_wifi_hotspot_locations n1, nyc_wifi_hotspot_locations n2 "
				+ "WHERE n1.Provider=? AND n1.Provider=n2.Provider AND n1.Location>n2.Location "
				+ "GROUP BY n1.Location, n2.Location";
		List<Coppia> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, p);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Coppia(vMap.get(res.getString("n1.Location")),vMap.get(res.getString("n2.Location"))));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
}
