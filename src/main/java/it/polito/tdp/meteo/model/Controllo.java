package it.polito.tdp.meteo.model;

public class Controllo {
	private String citta;
	private Integer costoIncrementale;
	
	public Controllo(String citta, Integer costoIncrementale) {
		this.citta = citta;
		this.costoIncrementale = costoIncrementale;
	}

	public String getCitta() {
		return citta;
	}

	public Integer getCostoIncrementale() {
		return costoIncrementale;
	}
		
	
}
