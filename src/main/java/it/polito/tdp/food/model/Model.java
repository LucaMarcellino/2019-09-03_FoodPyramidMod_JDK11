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
	private double pesoMax;
	private List<String> camminoMax;
	
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
	
	public String cercaCammino(String partenza, int N) {
		this.camminoMax = null ;
		this.pesoMax = 0.0 ;
		
		List<String> parziale = new ArrayList<>() ;
		parziale.add(partenza) ;
		
		search(parziale, 1, N);
		String s= ("Il peso del cammino è: "+pesoMax+"\nEd il cammino che parte da "+ partenza +" è:\n");
		for(int i=0; i<camminoMax.size();i++)
			s+=(camminoMax.get(i)+"\n");
		return s;
	}
	
	private void search(List<String> parziale, int livello, int N) {
		
		if(livello == N+1) {
			double peso = pesoCammino(parziale) ;
			if(peso>this.pesoMax) {
				this.pesoMax=peso ;
				this.camminoMax = new ArrayList<>(parziale);
			}
			return ;
		}
		
		List<String> vicini = Graphs.neighborListOf(this.grafo, parziale.get(livello-1)) ;
		for(String v : vicini) {
			if(!parziale.contains(v)) {
				parziale.add(v) ;
				search(parziale, livello+1, N) ;
				parziale.remove(parziale.size()-1) ;
			}
		}
	}

	private double pesoCammino(List<String> parziale) {
		double peso = 0.0 ;
		for(int i=1; i<parziale.size(); i++) {
			double p = this.grafo.getEdgeWeight(this.grafo.getEdge(parziale.get(i-1), parziale.get(i))) ;
			peso += p ;
		}
		return peso ;
	}
	
	public double getPesoMax() {
		return pesoMax;
	}

	public List<String> getCamminoMax() {
		return camminoMax;
	}
		
		
		

	
	
	
	
	
	
	
}
