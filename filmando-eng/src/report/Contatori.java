package report;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import classi.ValutazioneTipo1_2;
import frontend.Configurazione;
/*
/////////////////////////////CONTATORI///////////////////////////
10) contatori configurazioni elaborate;
11) contatori numero di contesti selezionati dagli utenti
12) contatori film suggeriti;
*/
public class Contatori {
//////////////////////////////////////////////CONTATORI///////////////////////////////////////////////// 
	//10) contatori numero valutazoni per UNIGRAMMI, BIGRAMMI, UNIBI
	public static HashMap<String, Integer> contatoriConfigurazioni(){
		HashMap<String, Integer> contatori = new HashMap<String, Integer>();
		contatori.put("unigrammi",0);	//INIZIALIZZAZ
		contatori.put("bigrammi", 0);
		contatori.put("unibigrammi",0);
		
		for (ValutazioneTipo1_2 v1 : Report.valutazioni1){//CENTROIDE + FRASI SINGOLE
			contatori.put(v1.configurazione, contatori.get(v1.configurazione) + 1);
		}
		return contatori;
	}
	
	//10.2) contatori numero valutazoni per PMI, NORMALE
	public static HashMap<String, Integer> contatoriTenica(){
		HashMap<String, Integer> contatori2 = new HashMap<String, Integer>();
		contatori2.put("pmi",0);	//INIZIALIZZAZ
		contatori2.put("normale", 0);
		
		for (ValutazioneTipo1_2 v1 : Report.valutazioni1){//CENTROIDE + FRASI SINGOLE
			contatori2.put(v1.tecnica, contatori2.get(v1.tecnica) + 1);
		}
		return contatori2;
	}
	
	//11) contatori NUMERO CONTESTI TOTALI elaborati dagli utenti.
	public static HashMap<Integer, Integer> contatoriNumeroContesti(){
		HashMap<Integer, Integer> contatori = new HashMap<Integer, Integer>();
		contatori.put(1,0);	//INIZIALIZZAZ
		contatori.put(2, 0);
		contatori.put(3,0);

		for (ValutazioneTipo1_2 v1 : Report.valutazioni1){	//per ogni rigo sommo quanti contesti conto
			contatori.put(v1.getNumeroContesti(), contatori.get(v1.getNumeroContesti()) + 1);
		}
		return contatori;
	}


	//12) contatori NUMERO TOTALE FILM SUGGERITI suggeriti;
	public static HashMap<Integer, Integer> contatoriLocaliSuggeriti(){
		HashMap<Integer, Integer> contatoriLocali = new HashMap<Integer, Integer>();
		
		for (ValutazioneTipo1_2 v1 : Report.valutazioni1){
			Integer locale = v1.getLocale();
			if (contatoriLocali.containsKey(locale))	//se presente sommo 1
				contatoriLocali.put(locale, contatoriLocali.get(locale) + 1);
			else 				//altrimenti aggiungo e conto 1
				contatoriLocali.put(locale, 1);	// prima occorrenza
		}
		return contatoriLocali;
	}
	
	//PER AGGIUNGERE IL TITOLO DEL FILM
    public static String getTitoloLocale(int locale) throws FileNotFoundException {
    	Scanner infoLocale = new Scanner(new File(
            Configurazione.path + "filesFilmando2/info utili/" + locale + ".txt"));
        infoLocale.nextLine();  //IDFILM
        String nomeLocale = infoLocale.nextLine().replaceAll("'", "");//TITOLO FILM
        infoLocale.close();
        return nomeLocale;
    }


}
