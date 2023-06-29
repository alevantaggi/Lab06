package it.polito.tdp.meteo.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.meteo.DAO.MeteoDAO;

public class Model {
	
	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;
	
	private MeteoDAO dao;
	private List<Rilevamento> rilevamenti;
	private List<Controllo> migliore;

	public Model() {
		this.dao= new MeteoDAO();
	}

	public String getUmiditaMedia(int mese) {
		String s= "";
		
		for(Rilevamento r: dao.getAvgRilevamentiMese(mese)) {
			s+= r.getLocalita()+" "+r.getMese()+" "+r.getAvgUmidita()+"\n";
		}
		return s.substring(0,s.length()-1);
	}
	
	
	
	// of course you can change the String output with what you think works best
	public String trovaSequenza(int mese) {
		String s="";
		this.rilevamenti= new ArrayList<>(dao.getRilevamentiPrimiQuindiMese(mese));
		this.migliore= new ArrayList<>();
		
		int giorno=1;
		List<Controllo> parziale= new ArrayList<>();
		
		ricerca(parziale, giorno);
		
		for(Controllo c: this.migliore){
			s+= c.getCitta()+", ";
		}
		
		return s.substring(0,s.length()-2);
	}
	
	
	private void ricerca(List<Controllo> parziale, int giorno) {
		
		if(giorno==(NUMERO_GIORNI_TOTALI+1)) {			
			if(!this.migliore.isEmpty()) {
				if(parziale.get(parziale.size()-1).getCostoIncrementale() < migliore.get(migliore.size()-1).getCostoIncrementale()) 
					migliore= new ArrayList<>(parziale);
				return;
			}
		
			migliore= new ArrayList<>(parziale);
			return;
		}
		
		
		for(int i=0;i<rilevamenti.size();i++) {
			
			if(rilevamenti.get(i).getGiorno()==giorno) {
				
				if(giorno==1) {
					Controllo c= new Controllo(rilevamenti.get(i).getLocalita(), rilevamenti.get(i).getUmidita());
					parziale.add(c);
					ricerca(parziale, giorno+1);
					parziale.remove(parziale.size()-1);
				}
				
				else {
					String citta=rilevamenti.get(i).getLocalita();
					if(controlloInserimento(citta, parziale)) {
						
						int valore= cacoloIncremento(citta, parziale.get(parziale.size()-1).getCitta(),rilevamenti.get(i).getUmidita(),parziale.get(parziale.size()-1).getCostoIncrementale());
						Controllo c= new Controllo(citta, valore);
						parziale.add(c);
						
						ricerca(parziale, giorno+1);
						parziale.remove(parziale.size()-1);
					}
				}	
			}	
		}			
	}
	
	
	private boolean controlloInserimento(String citta, List<Controllo> parziale) {
		int count=0;
		
		for(Controllo c: parziale) {
			if (c.getCitta().equals(citta))
				count++;
		}
		
		if(count>=NUMERO_GIORNI_CITTA_MAX)
			return false;
		
		if(parziale.size()==1 || parziale.size()==2) {
			return parziale.get(parziale.size()-1).getCitta().equals(citta);
		}
		
		if (parziale.get(parziale.size()-1).getCitta().equals(citta))
			return true; 
		
		if(parziale.get(parziale.size()- NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN).getCitta().equals(parziale.get(parziale.size()-2).getCitta())
				&& parziale.get(parziale.size()-2).getCitta().equals(parziale.get(parziale.size()-1).getCitta()))
			return true;
		
		return false;	
	}
	
	
	private int cacoloIncremento(String firstCity, String secondCity, int primaUmi, int twoUmi) {
		
		if(firstCity.equals(secondCity))
			return (primaUmi+twoUmi);
		
		return (primaUmi+twoUmi+COST);
	}
 
}
