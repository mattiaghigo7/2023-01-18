package it.polito.tdp.nyc.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.nyc.db.NYCDao;

public class Model {
	
	private NYCDao dao;
	private List<String> provider;
	
	private Graph<Location, DefaultWeightedEdge> grafo;
	private List<Location> vertici;
	private Map<String,Location> vMap;
	private List<Coppia> archi;
	
	private List<Location> migliore;
	
	public Model() {
		this.dao=new NYCDao();
		this.provider=new ArrayList<>(dao.getAllProvider());
	}
	
	public List<Location> calcolaPercorso(String s, Location l){
		this.migliore=new ArrayList<>();
		List<Location> parziale=new ArrayList<>();
		ricorsione(parziale,s,l,this.vertici);
		return migliore;
	}
	
	private void ricorsione(List<Location> parziale, String s, Location location, List<Location> adiacenti) {
		if(parziale.size()>0) {
			if(parziale.get(parziale.size()-1).equals(location)) {	
				if(parziale.size()>migliore.size()) {
					this.migliore=new ArrayList<>(parziale);
				}
				return;
			}
		}
		for(Location l : adiacenti) {
			if(!parziale.contains(l) && !l.getName().toLowerCase().contains(s.toLowerCase())) {
				parziale.add(l);
				ricorsione(parziale, s, location, this.getAdiacenti(l));
				parziale.remove(l);
			}
		}
	}
	
	private List<Location> getAdiacenti(Location l) {
		return Graphs.successorListOf(this.grafo, l);
	}

	public void creaGrafo(String provider, double d) {
		this.grafo=new SimpleWeightedGraph<Location, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		this.vertici=new ArrayList<>(dao.getVertici(provider));
		this.vMap=new HashMap<>();
		for(Location l : this.vertici) {
			this.vMap.put(l.getName(), l);
		}
		Graphs.addAllVertices(this.grafo, this.vertici);
//		System.out.println(grafo.vertexSet().size());
		
		this.archi=new ArrayList<>(dao.getArchi(provider, vMap));
		for(Coppia c : this.archi) {
			double distanza = LatLngTool.distance(c.getL1().getCoords(), c.getL2().getCoords(), LengthUnit.KILOMETER);
			if(distanza>0 && distanza<=d) {
				Graphs.addEdge(this.grafo, c.getL1(), c.getL2(), distanza);
			}
		}
//		System.out.println(grafo.edgeSet().size());
	}
	
	public List<VerticeVicini> getViciniMax() {
		List<VerticeVicini> r = new ArrayList<>();
		int max = 0;
		for(Location l : this.vertici) {
			if(Graphs.successorListOf(this.grafo, l).size()>max) {
				r=new ArrayList<>();
				r.add(new VerticeVicini(l, Graphs.successorListOf(this.grafo, l).size()));
				max = Graphs.successorListOf(this.grafo, l).size();
			}
			else if(Graphs.successorListOf(this.grafo, l).size()==max) {
				r.add(new VerticeVicini(l, Graphs.successorListOf(this.grafo, l).size()));
			}
		}
		return r;
	}
	
	public List<String> getProvider(){
		return this.provider;
	}
	
	public Set<Location> getVertici() {
		return this.grafo.vertexSet();
	}
	
	public int getVerticiSize() {
		return this.grafo.vertexSet().size();
	}
	
	public int getArchiSize() {
		return this.grafo.edgeSet().size();
	}
}
