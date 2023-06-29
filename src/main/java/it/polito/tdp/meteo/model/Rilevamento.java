package it.polito.tdp.meteo.model;

import java.util.Date;

public class Rilevamento {
	
	private String localita;
	private Date data;
	private int giorno;
	private int umidita;
	private String mese;
	private Double avgUmidita;

	public Rilevamento(String localita, Date data, int umidita) {
		this.localita = localita;
		this.data = data;
		this.umidita = umidita;
	}

	
	
	
	public Rilevamento(String localita, String mese, Double avgUmidita) {
		this.localita = localita;
		this.mese = mese;
		this.avgUmidita = avgUmidita;
	}

	


	public Rilevamento(String localita, int giorno, int umidita) {
		this.localita = localita;
		this.giorno = giorno;
		this.umidita = umidita;
	}




	public int getGiorno() {
		return giorno;
	}




	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getUmidita() {
		return umidita;
	}

	public void setUmidita(int umidita) {
		this.umidita = umidita;
	}
	
	

	// @Override
	// public String toString() {
	// return localita + " " + data + " " + umidita;
	// }

	public String getMese() {
		return mese;
	}




	public Double getAvgUmidita() {
		return avgUmidita;
	}




	@Override
	public String toString() {
		return String.valueOf(umidita);
	}

	

}
