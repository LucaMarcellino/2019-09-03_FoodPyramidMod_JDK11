package it.polito.tdp.food.model;

public class PorzioneAdiacente {

	private String v2;
	private int peso;
	public PorzioneAdiacente(String v2, int peso) {
		super();
		this.v2 = v2;
		this.peso = peso;
	}
	public String getV2() {
		return v2;
	}
	public void setV2(String v2) {
		this.v2 = v2;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + peso;
		result = prime * result + ((v2 == null) ? 0 : v2.hashCode());
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
		PorzioneAdiacente other = (PorzioneAdiacente) obj;
		if (peso != other.peso)
			return false;
		if (v2 == null) {
			if (other.v2 != null)
				return false;
		} else if (!v2.equals(other.v2))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return v2 +"  "+ peso;
	}
	
	
	
	
}
