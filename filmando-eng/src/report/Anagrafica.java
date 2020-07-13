package report;

import java.util.HashMap;
import classi.Utente;
/////////////////////////////DATI UTENTE///////////////////////////////////
//1) anagrafiche utenti;

public class Anagrafica {
	//1) anagrafiche utenti
    public static HashMap<String, HashMap<String, Integer>> infoUtenti(){
        HashMap<String, HashMap<String, Integer>> infoUtenti = new HashMap<>();
        HashMap<String, Integer> eta = new HashMap<>();
        HashMap<String, Integer> genere = new HashMap<>();
        HashMap<String, Integer> titolo = new HashMap<>();
        HashMap<String, Integer> frequenza = new HashMap<>();
        HashMap<String, Integer> recsys = new HashMap<>();

        eta.put("<18", 0);//inizializzaz
        eta.put("18-25", 0);
        eta.put("26-35", 0);
        eta.put("36-50", 0);
        eta.put(">50", 0);

        genere.put("uomo", 0);
        genere.put("donna", 0);

        titolo.put("Diploma Scuola superiore",0);
        titolo.put("Laurea Triennale",0);
        titolo.put("Laurea Magistrale",0);
        titolo.put("Dottorato di Ricerca",0);
        titolo.put("Altro",0);

        frequenza.put("0-1 volta", 0);
        frequenza.put("2-4 volte", 0);
        frequenza.put("5-7 volte", 0);

        recsys.put("si", 0);
        recsys.put("no", 0);
        
        ////////////////////////////////////LETTURA DATI UTENTE IN REPORT////////////////////////
        for (Utente u : Report.utenti){
            switch (u.getEta()){
                case 1:
                    eta.put("<18", eta.get("<18")+1);
                    break;
                case 2:
                    eta.put("18-25", eta.get("18-25")+1);
                    break;
                case 3:
                    eta.put("26-35", eta.get("26-35")+1);
                    break;
                case 4:
                    eta.put("36-50", eta.get("36-50")+1);
                    break;
                case 5:
                    eta.put(">50", eta.get(">50")+1);
                    break;
            }

            if (u.getGenere().equals("uomo")){
                genere.put("uomo", genere.get("uomo")+1);
            } else {
                genere.put("donna", genere.get("donna")+1);
            }

            switch (u.getTitoloStudio()){
                case 6:
                    titolo.put("Diploma Scuola superiore",titolo.get("Diploma Scuola superiore")+1);
                    break;
                case 7:
                    titolo.put("Laurea Triennale",titolo.get("Laurea Triennale")+1);
                    break;
                case 8:
                    titolo.put("Laurea Magistrale",titolo.get("Laurea Magistrale")+1);
                    break;
                case 9:
                    titolo.put("Dottorato di Ricerca",titolo.get("Dottorato di Ricerca")+1);
                    break;
                case 10:
                    titolo.put("Altro",titolo.get("Altro")+1);
                    break;
            }

            switch (u.getFrequenzaUscite()){
                case 11:
                    frequenza.put("0-1 volta", frequenza.get("0-1 volta")+1);
                    break;
                case 12:
                    frequenza.put("2-4 volte", frequenza.get("2-4 volte")+1);
                    break;
                case 13:
                    frequenza.put("5-7 volte", frequenza.get("5-7 volte")+1);
                    break;
            }

            switch (u.getUsoRecSys()) {
                case "14":
                    recsys.put("si", recsys.get("si")+1);
                    break;
                case "15":
                    recsys.put("no", recsys.get("no")+1);
                    break;
            }

        }
        
        infoUtenti.put("Eta", eta);
        infoUtenti.put("Genere", genere);
        infoUtenti.put("Titoli di studio", titolo);
        infoUtenti.put("Frequenze uscite", frequenza);
        infoUtenti.put("Uso Rec Sys", recsys);
        return infoUtenti;
    }
   
}
