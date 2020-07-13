package report;

import java.util.HashMap;
import classi.ValutazioneTipo1_2;
import frontend.Configurazione;

public class MedieMetriche {
    ////////////13) MEDIE metriche per CONTESTI - CENTROIDE vs SINGOLE//////////////////
    public static HashMap<Integer, HashMap<String, HashMap<String, Double>>> medieMetrichePerContesti(){
        HashMap<Integer, HashMap<String, HashMap<String, Double>>> preferenze = new HashMap<>();
        HashMap<Integer, Integer> contatori = new HashMap<>();
        
        for (int i=1; i<=Configurazione.numeroContesti; i++){//INIZIALIZZAZ
            contatori.put(i, 0);
        }
        
        for (int i=1; i<=Configurazione.numeroContesti; i++){
            HashMap<String, HashMap<String, Double>> t = new HashMap<>();
            HashMap<String, Double> c = new HashMap<>();
            c.put("trasparenza", 0.0);
            c.put("persuasione", 0.0);
            c.put("coinvolgimento", 0.0);
            c.put("fiducia", 0.0);

            t.put("centroide", c);

            HashMap<String, Double> d = new HashMap<>();
            d.put("trasparenza", 0.0);
            d.put("persuasione", 0.0);
            d.put("coinvolgimento", 0.0);
            d.put("fiducia", 0.0);
            
            t.put("singole", d);
            preferenze.put(i, t);
        }
        
        for (ValutazioneTipo1_2 v1 : Report.valutazioni1){
        	if(v1.getTipoFrasi().equals("centroide")){//CENTROIDE
        		for (int contesto : v1.listaContesti){
                	preferenze.get(contesto).get("centroide").put("trasparenza", preferenze.get(contesto).get("centroide").get("trasparenza") + (double)v1.getTrasparenza());
                	preferenze.get(contesto).get("centroide").put("persuasione", preferenze.get(contesto).get("centroide").get("persuasione") + (double)v1.getPersuasione());
                	preferenze.get(contesto).get("centroide").put("coinvolgimento", preferenze.get(contesto).get("centroide").get("coinvolgimento") + (double)v1.getCoinvolgimento());
                	preferenze.get(contesto).get("centroide").put("fiducia", preferenze.get(contesto).get("centroide").get("fiducia") + (double)v1.getFiducia());

                	contatori.put(contesto, contatori.get(contesto)+1);
            	}
        	}
        }
        
        //FRASI SINGOLE
        for (ValutazioneTipo1_2 v2 : Report.valutazioni1){
        	if(v2.getTipoFrasi().equals("frasisingole")){//FRASI SINGOLE
        		for (int contesto : v2.listaContesti){
        			preferenze.get(contesto).get("singole").put("trasparenza", preferenze.get(contesto).get("singole").get("trasparenza") + (double)v2.getTrasparenza());
        			preferenze.get(contesto).get("singole").put("persuasione", preferenze.get(contesto).get("singole").get("persuasione") + (double)v2.getPersuasione());
        			preferenze.get(contesto).get("singole").put("coinvolgimento", preferenze.get(contesto).get("singole").get("coinvolgimento") + (double)v2.getCoinvolgimento());
        			preferenze.get(contesto).get("singole").put("fiducia", preferenze.get(contesto).get("singole").get("fiducia") + (double)v2.getFiducia());
        			
        			contatori.put(contesto, contatori.get(contesto)+1);
        		}
        	}
        }
        
        for (int contesto=1; contesto<=Configurazione.numeroContesti; contesto++){
            //centroide
            preferenze.get(contesto).get("centroide").put("trasparenza", preferenze.get(contesto).get("centroide").get("trasparenza") / contatori.get(contesto));
            preferenze.get(contesto).get("centroide").put("persuasione", preferenze.get(contesto).get("centroide").get("persuasione") / contatori.get(contesto));
            preferenze.get(contesto).get("centroide").put("coinvolgimento", preferenze.get(contesto).get("centroide").get("coinvolgimento") / contatori.get(contesto));
            preferenze.get(contesto).get("centroide").put("fiducia", preferenze.get(contesto).get("centroide").get("fiducia") / contatori.get(contesto));
            //singole
            preferenze.get(contesto).get("singole").put("trasparenza", preferenze.get(contesto).get("singole").get("trasparenza") / contatori.get(contesto));
            preferenze.get(contesto).get("singole").put("persuasione", preferenze.get(contesto).get("singole").get("persuasione") / contatori.get(contesto));
            preferenze.get(contesto).get("singole").put("coinvolgimento", preferenze.get(contesto).get("singole").get("coinvolgimento") / contatori.get(contesto));
            preferenze.get(contesto).get("singole").put("fiducia", preferenze.get(contesto).get("singole").get("fiducia") / contatori.get(contesto));
        }
        return preferenze;
    }
    

    //////////14) MEDIA metriche per CONTESTI GENERALE////////////////
    public static HashMap<Integer, HashMap<String, Double>> medieMetrichePerContestiGenerale(){
        HashMap<Integer, HashMap<String, Double>> preferenze = new HashMap<>();
        HashMap<Integer, Integer> contatori = new HashMap<>();
        
        for (int i=1; i<=Configurazione.numeroContesti; i++){//INIZIALIZZAZ
            contatori.put(i, 0);
        }
        
        for (int i=1; i<=Configurazione.numeroContesti; i++){
            HashMap<String, Double> c = new HashMap<>();
            c.put("trasparenza", 0.0);
            c.put("persuasione", 0.0);
            c.put("coinvolgimento", 0.0);
            c.put("fiducia", 0.0);
            preferenze.put(i, c);
        }
        /*
        for (ValutazioneTipo1_2 v1 : Report.valutazioni1){
        	if(v1.getTipoFrasi().equals("centroide")){//CENTROIDE
        		for (int contesto : v1.listaContesti){
                	preferenze.get(contesto).put("trasparenza", preferenze.get(contesto).get("trasparenza") + (double)v1.getTrasparenza());
                	preferenze.get(contesto).put("persuasione", preferenze.get(contesto).get("persuasione") + (double)v1.getPersuasione());
                	preferenze.get(contesto).put("coinvolgimento", preferenze.get(contesto).get("coinvolgimento") + (double)v1.getCoinvolgimento());
                	preferenze.get(contesto).put("fiducia", preferenze.get(contesto).get("fiducia") + (double)v1.getFiducia());
                	contatori.put(contesto, contatori.get(contesto)+1);
            	}
        	}
        }
        */
        for (ValutazioneTipo1_2 v2 : Report.valutazioni1){
        	//if(v2.getTipoFrasi().equals("frasisingole")){//FRASI SINGOLE
        		for (int contesto : v2.listaContesti){
        			preferenze.get(contesto).put("trasparenza", preferenze.get(contesto).get("trasparenza") + (double)v2.getTrasparenza());
        			preferenze.get(contesto).put("persuasione", preferenze.get(contesto).get("persuasione") + (double)v2.getPersuasione());
        			preferenze.get(contesto).put("coinvolgimento", preferenze.get(contesto).get("coinvolgimento") + (double)v2.getCoinvolgimento());
        			preferenze.get(contesto).put("fiducia", preferenze.get(contesto).get("fiducia") + (double)v2.getFiducia());
        			contatori.put(contesto, contatori.get(contesto)+1);
        		}
        	//}
        }
        
        for (int contesto=1; contesto<=Configurazione.numeroContesti; contesto++){
            preferenze.get(contesto).put("trasparenza", preferenze.get(contesto).get("trasparenza") / contatori.get(contesto));
            preferenze.get(contesto).put("persuasione", preferenze.get(contesto).get("persuasione") / contatori.get(contesto));
            preferenze.get(contesto).put("coinvolgimento", preferenze.get(contesto).get("coinvolgimento") / contatori.get(contesto));
            preferenze.get(contesto).put("fiducia", preferenze.get(contesto).get("fiducia") / contatori.get(contesto));
        }
        return preferenze;
    }
    
}
