package it.polito.tdp.nyc.db;

public class TestDao {

	public static void main(String[] args) {
		NYCDao dao = new NYCDao();
		System.out.println(dao.getAllHotspot().size());
	}

}
