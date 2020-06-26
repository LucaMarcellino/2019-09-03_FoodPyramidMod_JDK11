package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDAO;

public class Model {
	private FoodDAO dao;
	private Graph<String, DefaultWeightedEdge> grafo;
	private List<String> porzioni;
	
	public Model() {
		this.dao= new FoodDAO();
		this.porzioni=new ArrayList<String>();
	}
	
	
	public void creaGrafo(int calorie) {
		this.grafo= new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, dao.getVertex(calorie));
		
		for(Arco a : dao.getEdge(calorie)) {
			if(grafo.vertexSet().contains(a.getV1())  &&  grafo.vertexSet().contains(a.getV2()))
				Graphs.addEdgeWithVertices(grafo, a.getV1(), a.getV2(), a.getPeso());
		}
		
		
	}
	
	public List<String> getVertex() {
		List<String> vertici = new ArrayList<String>(grafo.vertexSet());
		return vertici;
	}

	public int getEdge() {
		return grafo.edgeSet().size();
	}
	
	public List<PorzioneAdiacente> getVicini(String partenza) {
		List<String>vicini = new ArrayList<String>(Graphs.neighborListOf(grafo, partenza));
		List <PorzioneAdiacente> result = new ArrayList<>();
		System.out.println(vicini);
		for(String s: vicini) {
			PorzioneAdiacente pa= new PorzioneAdiacente(s, (int)grafo.getEdgeWeight(grafo.getEdge(partenza, s)));
			result.add(pa);
		}
		
		return result;
		
	}
	
	
	
	
	
	
	
	
	
}
