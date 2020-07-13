package report;

import frontend.Configurazione;
import classi.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/* Questa classe legge i valori contenuti nei log e popola le strutture utenti e valutazioni.
vengono costruite  mappe, che contengono MEDIE sulle  informazioni: */
public class Report {
    public static HashSet<Utente> 				utenti = null;					
    public static HashSet<ValutazioneTipo1_2> 	valutazioni1 = null; 
    public static HashSet<ValutazioneTipo1_2> 	valutazioni2 = null;
    public static HashSet<ValutazioneTipo3> 	valutazioni3 = null;
    public static HashSet<ValutazioneTipo4> 	valutazioni4 = null;

    /////////////////////////////////////////COSTRUTTORE//////////////////////////////////////////
    //Questa classe popola le strutture utenti, valutazioni1, valutazioni2 , valutazioni3 e valutazioni4
    public static void leggiLog() throws Exception {
        ////////////////////////////////////////////////////UTENTI//////////////////////
        leggiUtente();
        //////////////////////////////////////////////////VALUTAZIONI/////////////////////////////////
        leggiValutazione1();
        //leggiValutazione2();
        leggiValutazione3();
        leggiValutazione4();
        
        //this.controllo();
    }

    public static void leggiUtente() throws FileNotFoundException {
    	utenti = new HashSet<>();
    	Scanner users = new Scanner(new File(Configurazione.path + "filesFilmando2/utenti.txt"));
    	users.nextLine();	//1586298607561;2;donna;6;11;14
    
    	while(users.hasNextLine()){
    		String[] riga = users.nextLine().split(";");
    		
    		Utente u = new Utente();
    		u.setId(riga[0]);									//1586298607561
    		u.setEta(Integer.parseInt(riga[1]));				//2
    		u.setGenere(riga[2]);								//donna
    		u.setTitoloStudio(Integer.parseInt(riga[3]));		//6
    		u.setFrequenzaUscite(Integer.parseInt(riga[4]));	//11
    		u.setUsoRecSys(riga[5]);							//14
    		utenti.add(u);
    	}
    	users.close();
    }
    
    //CENTROIDE
    public static void leggiValutazione1() throws FileNotFoundException {
    	valutazioni1 = new HashSet<>(); 
    	Scanner val1 = new Scanner(new File(Configurazione.path + "filesFilmando2/valutazione1.txt"));
    	val1.nextLine();
    	//1591983865169;bigrammi;normale;centroide;281;2;4,6;3;5;5;5
    	while(val1.hasNextLine()){
    		String[] riga = val1.nextLine().split(";");
    		ValutazioneTipo1_2 v1 = new ValutazioneTipo1_2();

    		v1.setId(riga[0]);				//1591983865169
    		v1.setConfigurazione(riga[1]);	//bigrammi 
    		v1.setTecnica(riga[2]);			//normale //agg
    		v1.setTipoFrasi(riga[3]);		//centroide //agg
    		v1.setLocale(Integer.parseInt(riga[4]));	//281
    		v1.setNumeroContesti(Integer.parseInt(riga[5]));	//2
    		
    		ArrayList<Integer> lc = new ArrayList<>();
    		String[] lista = riga[6].split(",");	//4,6
    		for (String s : lista){
    			lc.add(Integer.parseInt(s));
    		}
    		v1.setListaContesti(lc);	//4,6
    		
    		v1.setTrasparenza(Integer.parseInt(riga[7]));		//3
    		v1.setPersuasione(Integer.parseInt(riga[8]));		//5
    		v1.setCoinvolgimento(Integer.parseInt(riga[9]));	//5
    		v1.setFiducia(Integer.parseInt(riga[10]));			//5
    		//////////////////CONTROLLO///////////////////////
    		HashSet<ValutazioneTipo1_2> rim = new HashSet<>();
    		for (ValutazioneTipo1_2 v : valutazioni1){
    			if (v.getId().equals(v1.getId())){//SE UTENTI UGUALI IN VAL1 RIMUOVO
    				rim.add(v);
    			}
    		}
    		valutazioni1.removeAll(rim);
    		///////////////////////////////////////////////
    		valutazioni1.add(v1);
    	}
    	val1.close();
    }
    
    /*
    //frasi singole
    public void leggiValutazione2() throws FileNotFoundException {
    	 valutazioni2 = new HashSet<>();
    	 Scanner val2 = new Scanner(new File(Configurazione.path+"filesFilmando2/valutazione2.txt"));
         val2.nextLine();

         while(val2.hasNextLine()){
             String[] riga = val2.nextLine().split(";");
             ValutazioneTipo1_2 v2 = new ValutazioneTipo1_2();

             v2.setId(riga[0]);
             v2.setConfigurazione(riga[1]);
             //v2.setCitta(riga[2]);
             v2.setLocale(Integer.parseInt(riga[3]));
             v2.setNumeroContesti(Integer.parseInt(riga[4]));
             ArrayList<Integer> lc = new ArrayList<>();
             String[] lista = riga[5].split(",");
             for (String s : lista){
                 lc.add(Integer.parseInt(s));
             }
             v2.setListaContesti(lc);
             
             v2.setTrasparenza(Integer.parseInt(riga[6]));
             v2.setPersuasione(Integer.parseInt(riga[7]));
             v2.setCoinvolgimento(Integer.parseInt(riga[8]));
             v2.setFiducia(Integer.parseInt(riga[9]));

             HashSet<ValutazioneTipo1_2> rim = new HashSet<>();
             for (ValutazioneTipo1_2 v : valutazioni2){
                 if (v.getId().equals(v2.getId())){
                     rim.add(v);
                 }
             }
             valutazioni2.removeAll(rim);
             valutazioni2.add(v2);
         }
         val2.close();
    }
    */
    
    public static void leggiValutazione3() throws FileNotFoundException {
    	valutazioni3 = new HashSet<>();
    	Scanner val3 = new Scanner(new File(Configurazione.path+"filesFilmando2/valutazione3.txt"));
        val3.nextLine();
        //1591983865169;bigrammi;normale;centroide;281;2;4,6;1;1;0;2;2
        while(val3.hasNextLine()){
            String[] riga = val3.nextLine().split(";");
            ValutazioneTipo3 v3 = new ValutazioneTipo3();

            v3.setId(riga[0]);
            v3.setConfigurazione(riga[1]);
            v3.setTecnica(riga[2]);//agg
    		v3.setTipoFrasi(riga[3]);//agg
            v3.setLocale(Integer.parseInt(riga[4]));
            v3.setNumeroContesti(Integer.parseInt(riga[5]));
            
            ArrayList<Integer> lc = new ArrayList<>();
            String[] lista = riga[6].split(",");
            for (String s : lista){
                lc.add(Integer.parseInt(s));
            }
            v3.setListaContesti(lc);
            
            v3.setPreferenza0(Integer.parseInt(riga[7]));//confronto tra framew e distribuz
            
            v3.setTrasparenza(Integer.parseInt(riga[8]));
            v3.setPersuasione(Integer.parseInt(riga[9]));
            v3.setCoinvolgimento(Integer.parseInt(riga[10]));
            v3.setFiducia(Integer.parseInt(riga[11]));
            
            ///////////////////////////////CONTROLLO/////////////////////////
            HashSet<ValutazioneTipo3> rim = new HashSet<>();
            for (ValutazioneTipo3 v : valutazioni3){
                if (v.getId().equals(v3.getId())){
                    rim.add(v);
                }
            }
            valutazioni3.removeAll(rim);
            //////////////////////////////////////////////////////////
            valutazioni3.add(v3);
        }
        val3.close();
    }
    
    
	public static void leggiValutazione4() throws FileNotFoundException {
		valutazioni4 = new HashSet<>();
		Scanner val4 = new Scanner(new File(Configurazione.path+"filesFilmando2/valutazione4.txt"));
        val4.nextLine();

        while(val4.hasNextLine()){
            String[] riga = val4.nextLine().split(";");
            ValutazioneTipo4 v4 = new ValutazioneTipo4();

            v4.setId(riga[0]);
            v4.setConfigurazione(riga[1]);
            v4.setTecnica(riga[2]);//agg
    		v4.setTipoFrasi(riga[3]);//agg
            v4.setLocale(Integer.parseInt(riga[4]));
            v4.setNumeroContesti(Integer.parseInt(riga[5]));
            ArrayList<Integer> lc = new ArrayList<>();
            String[] lista = riga[6].split(",");
            for (String s : lista){
                lc.add(Integer.parseInt(s));
            }
            v4.setListaContesti(lc);
            
            v4.setPreferenza0(Integer.parseInt(riga[7]));//confronto tra framew e pucariello
            
            v4.setTrasparenza(Integer.parseInt(riga[8]));
            v4.setPersuasione(Integer.parseInt(riga[9]));
            v4.setCoinvolgimento(Integer.parseInt(riga[10]));
            v4.setFiducia(Integer.parseInt(riga[11]));
            
            ///////////////////////////////CONTROLLO/////////////////////////
            HashSet<ValutazioneTipo4> rim = new HashSet<>();
            for (ValutazioneTipo4 v : valutazioni4){
                if (v.getId().equals(v4.getId())){
                    rim.add(v);
                }
            }
            valutazioni4.removeAll(rim);
            /////////////////////////////////////////////
            valutazioni4.add(v4);
        }
        val4.close();
	}
	
	
    private void controllo(){
        HashSet<String> rimozione = new HashSet<>();
        ArrayList<HashSet<String>> listaId = new ArrayList<HashSet<String>>();

        HashSet<String> id0 = new HashSet<>();
        for (Utente u : utenti){
            String id = u.getId();
            id0.add(id);
        }
        listaId.add(id0);

        HashSet<String> id1 = new HashSet<>();
        for (ValutazioneTipo1_2 v1 : valutazioni1){
            String id = v1.getId();
            id1.add(id);
        }
        listaId.add(id1);

        HashSet<String> id2 = new HashSet<>();
        for (ValutazioneTipo1_2 v2 : valutazioni2){
            String id = v2.getId();
            id2.add(id);
        }
        listaId.add(id2);

        HashSet<String> id3 = new HashSet<>();
        for (ValutazioneTipo3 v3 : valutazioni3){
            String id = v3.getId();
            id3.add(id);
        }
        listaId.add(id3);

        HashSet<String> id4 = new HashSet<>();
        for (ValutazioneTipo4 v4 : valutazioni4){
            String id = v4.getId();
            id4.add(id);
        }
        listaId.add(id4);

        listaId.get(0).retainAll(listaId.get(1));
        listaId.get(0).retainAll(listaId.get(2));
        listaId.get(0).retainAll(listaId.get(3));
        listaId.get(0).retainAll(listaId.get(4));

        HashSet<String> mantenere = listaId.get(0);

        HashSet<Utente> rimUt = new HashSet<>();
        for (Utente u : utenti){
            if (!mantenere.contains(u.getId())){
                rimUt.add(u);
            }
        }
        utenti.removeAll(rimUt);

        HashSet<ValutazioneTipo1_2> rimV1 = new HashSet<>();
        for (ValutazioneTipo1_2 v1 : valutazioni1){
            if (!mantenere.contains(v1.getId())){
                rimV1.add(v1);
            }
        }
        valutazioni1.removeAll(rimV1);

        HashSet<ValutazioneTipo1_2> rimV2 = new HashSet<>();
        for (ValutazioneTipo1_2 v2 : valutazioni2){
            if (!mantenere.contains(v2.getId())){
                rimV2.add(v2);
            }
        }
        valutazioni2.removeAll(rimV2);

        HashSet<ValutazioneTipo3> rimV3 = new HashSet<>();
        for (ValutazioneTipo3 v3 : valutazioni3){
            if (!mantenere.contains(v3.getId())){
                rimV3.add(v3);
            }
        }
        valutazioni3.removeAll(rimV3);

        HashSet<ValutazioneTipo4> rimV4 = new HashSet<>();
        for (ValutazioneTipo4 v4 : valutazioni4){
            if (!mantenere.contains(v4.getId())){
                rimV4.add(v4);
            }
        }
        valutazioni4.removeAll(rimV4);

        /*
        for (Utente u : utenti){
            String id = u.getId();
            boolean presente1 = false, presente2 = false, presente3 = false, presente4 = false;
            for (ValutazioneTipo1_2 v1 : valutazioni1){
                if (v1.getId().equals(id)){
                    presente1 = true;
                }
            }
            for (ValutazioneTipo1_2 v2 : valutazioni2){
                if (v2.getId().equals(id)){
                    presente2 = true;
                }
            }
            for (ValutazioneTipo3 v3 : valutazioni3){
                if (v3.getId().equals(id)){
                    presente3 = true;
                }
            }
            for (ValutazioneTipo4 v4 : valutazioni4){
                if (v4.getId().equals(id)){
                    presente4 = true;
                }
            }

            boolean presente = presente1 && presente2 && presente3 && presente4;
            if (!presente){
                rimozione.add(u);
            }
        }
        */
        /*
        for (Utente u : rimozione){
            String id = u.getId();
            HashSet<ValutazioneTipo1_2> rim1 = new HashSet<>();
            for (ValutazioneTipo1_2 v1 : valutazioni1){
                if (v1.getId().equals(id)) rim1.add(v1);
            }
            valutazioni1.removeAll(rim1);

            HashSet<ValutazioneTipo1_2> rim2 = new HashSet<>();
            for (ValutazioneTipo1_2 v2 : valutazioni2){
                if (v2.getId().equals(id)) rim2.add(v2);
            }
            valutazioni2.removeAll(rim2);

            HashSet<ValutazioneTipo3> rim3 = new HashSet<>();
            for (ValutazioneTipo3 v3 : valutazioni3){
                if (v3.getId().equals(id)) rim3.add(v3);
            }
            valutazioni3.removeAll(rim3);

            HashSet<ValutazioneTipo4> rim4 = new HashSet<>();
            for (ValutazioneTipo4 v4 : valutazioni4){
                if (v4.getId().equals(id)) rim4.add(v4);
            }
            valutazioni4.removeAll(rim4);
        }
        utenti.removeAll(rimozione);
        */
    }


}