package it.polito.tdp.food.model;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Model m = new Model();
		m.creaGrafo(20);
		System.out.print(m.getVicini("baby carrot"));
	}

}
