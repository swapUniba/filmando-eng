package spiegazioni;

import ranking.Top10LocaliPerCombinazione;
import vettoricontesto.VettoriContesto;//CENTROIDE
import main.Configurazione;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

/*
Questa classe seleziona le frasi da suggerire agli utenti nell’approccio col CENTROIDE. 
La struttura principale è:
-	public static HashMap<HashSet<Integer>, HashMap<Integer, ArrayList<Integer>>> 	matriceContestiItemFrasi;
con insime le combinaizoni dei contesti
*/																							 														
					//[2,3,6]					//3(film)			//123
public class MatriceContestiItemFrasi{								//234
				// COMB CONTESTI,  					FILM, 		ID FRASI 
	public static HashMap<HashSet<Integer>, HashMap<Integer, ArrayList<Integer>>> matriceContestiItemFrasi = new HashMap<>();	//AGGIUNTO
    public static HashSet<HashSet<Integer>> combinazioni = Top10LocaliPerCombinazione.combinazioni;
    

    /* Per calcolare la matriceContestiItemFrasi:
    1)  per ogni combinazione di contesti
    - si calcola il centroide della combinazione
    - si prende la top10 film e si iterano(con score di similarità più alto)
    2) Si leggono le frasi lemmatizzate (+ intere) relative a quel film
	3) Vengono scelte le 3 frasi con score maggiore tramite il metodo scegliFrasiCombinazioneContesti(centroide) della classe SceltaFrasiItems. 
	4) Si inseriscono in una mappa che assegna ad ogni film la lista delle 3 frasi scelte. 
	3) Al termine dello scorrimento degli item della top 10, questa struttura viene inserita nella matrice assieme alla chiave = combinazione dei contesti.*/
    public static void calcolaMatriceContestiItemFrasi() throws Exception {
        for (HashSet<Integer> combinazione : combinazioni){ //PER OGNI COMBINAZIONE DI CONTESTI
            System.out.println("////////////////////COMBINAZIONE " + combinazione + "/////////////////////////////");
            HashMap<Integer, ArrayList<Integer>> localeFrasi = new HashMap<>();
            TreeMap<String,Double> centroide = VettoriContesto.CalcolaCentroide(combinazione);	//CALCOLO CENTROIDE COMBINAZIONE
            
            for (int locale : Top10LocaliPerCombinazione.contestiItemTop10.get(combinazione)) {//PER OGNI FILM IN TOP10 DI QUELLA COMBINAZIONE
            	//System.out.println("idFilm:\t" + locale);
            	FileTestoItems.leggiFrasiLocale(locale);
            	ArrayList<Integer> idFrasi = SceltaFrasiItemFiles.scegliFrasiCombinazioneContesti(centroide);//RITORNO INSIEME DI FRASI
                            //ID FILM TOP10	//ARRAY FRASI
            	localeFrasi.put(locale, idFrasi);
            	System.out.println("FILM n " + locale + "\t frasi: " + idFrasi + "\n");
            ///////////////////////////////PULIZIA///////////////////////////////////////            
            	FileTestoItems.frasiLemmi.clear();
            	FileTestoItems.frasiIntere.clear();
            }													//234    														
           								//[2,3,6]	//3(film)	//123
            matriceContestiItemFrasi.put(combinazione, localeFrasi);
        }

    }
    

////////////////////////////////////STAMPA//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //SCRITTURA MATRICE CONTESTI ITEM FRASI SU FILE, visualizzaz excel 
    public static void stampaMatriceContestiItemFrasiFile() throws Exception {
    	PrintWriter out = new PrintWriter(new File(
    			Configurazione.path + "filesFilmando2\\" + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\matrice contesti item frasi.txt"));
    	out.printf("FILM\t");
    	HashSet<Integer> SetFilm  = new HashSet<>();
    	//CREAZIONE KEYSET ANCHE CELLE NULL
    	for(HashSet<Integer> combinazione:  matriceContestiItemFrasi.keySet()) {
    		for(int idFilm:  matriceContestiItemFrasi.get(combinazione).keySet()){//10 FILM
    			SetFilm.add(idFilm);
    		}
    	}
    	//SCRIVO RIGA DEI FILM
    	for(int keyFilm: SetFilm)
    		out.printf(keyFilm + "\t");
    	out.println();
    	
    	for(HashSet<Integer> combinazione:  matriceContestiItemFrasi.keySet()) {	//[1, 3] 	
    		String row= "";
    		for(int keyFilm: SetFilm){//ANZICHE COMB.KEYSET()					//129 	5		6	263	
    			if(matriceContestiItemFrasi.get(combinazione).get(keyFilm) == null)
    				row += "0" + "\t";
    			else{
    				row += matriceContestiItemFrasi.get(combinazione).get(keyFilm) + "\t\t\t" ;
    			}
    		}
    		out.println(combinazione +  "\t" + row);//[112,32,321]
    	}
    	out.flush();
    	out.close();
    }
    //COMB		FILM	FRASI
    						//3,7		96		32835,32711,32495
	/////////////////		//3,7		112		39034,39211,39197
    //AGGIUNTO DA ME, STAMPA A SCHERMO, PER OGNI CONTESTO/COMBINAZIONE E PER OGNI FILM IN TOP10, LE FRASI GENERATE COL CENTROIDE x spiegazioni
    public static void scriviCombinazioni() {    	
    	System.out.println("COMB\tFILM\tFRASI");	////[2,3,6],[2,3],[2]
    	for(HashSet<Integer> combinazione:  Top10LocaliPerCombinazione.combinazioni) {		//for(HashSet<Integer> combinazione:  matriceContestiItemFrasi.keySet()) {
    		String comb="";
    		for(int k : combinazione){
    			comb+= Integer.toString(k);
    			comb+=",";
			}
    		for(int idFilm:  Top10LocaliPerCombinazione.contestiItemTop10.get(combinazione)) {		//ifor(int idFilm:  matriceContestiItemFrasi.get(combinazione).keySet()) {//10 FILM
    			String frasi = "";
    			for(int idFrase:  matriceContestiItemFrasi.get(combinazione).get(idFilm)) {//TUTTE LE FRASI SPIEGAZIONE (3)
    				frasi += Integer.toString(idFrase);
    				frasi +=",";
    			}
    			System.out.println(comb.substring(0,comb.length()-1) + "\t" + idFilm + "\t" + frasi.substring(0,frasi.length()-1));
    		}
    	}
    }
    
  ///////////////////////////////////////////////////////
  //AGGIUNTO DA ME,SCRIVE SU FILE PER OGNI CONTESTO/COMBINAZIONE E PER OGNI FILM IN TOP10, LE FRASI GENERATE COL CENTROIDE x spiegazioni
    public static void scriviCombinazioniFile() throws Exception {   	
    	PrintWriter out = new PrintWriter(new File(
    			Configurazione.path + "filesFilmando2\\"  + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\contesti-item-frasi.txt"));
    	for(HashSet<Integer> combinazione:  Top10LocaliPerCombinazione.combinazioni) {		//for(HashSet<Integer> combinazione:  matriceContestiItemFrasi.keySet()) {
    		String comb="";
    		for(int k : combinazione){
    			comb+= Integer.toString(k);
    			comb+=",";
			}
    		for(int idFilm:  Top10LocaliPerCombinazione.contestiItemTop10.get(combinazione)) {		//for(int idFilm:  matriceContestiItemFrasi.get(combinazione).keySet()) {//10 FILM
    			String frasi = "";
    			for(int idFrase:  matriceContestiItemFrasi.get(combinazione).get(idFilm)) {//TUTTE LE FRASI SPIEGAZIONE (3)
    				frasi += Integer.toString(idFrase);
    				frasi +=",";
    			}
    			out.println(comb.substring(0,comb.length()-1) + "\t" + idFilm + "\t" + frasi.substring(0,frasi.length()-1));
    		}
    	}
    	out.flush();
    	out.close();
    }
 
    
/////////////////////////////////////////////////SERIALIZZAZIONE//////////////////////////////////////////////////////  
    public static void leggiMatrice() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(
        		Configurazione.path + "filesFilmando2/"  + Configurazione.tecnica + "/" + Configurazione.TipoLemmi + "/serialized/contesti-item-frasi.dat")));
        matriceContestiItemFrasi = (HashMap<HashSet<Integer>, HashMap<Integer, ArrayList<Integer>>>) ois.readObject();
        ois.close();
    }
   
    public static void scriviMatrice() throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream((new File(
        		Configurazione.path + "filesFilmando2/"  + Configurazione.tecnica + "/" + Configurazione.TipoLemmi + "/serialized/contesti-item-frasi.dat"))));
        oos.writeObject(matriceContestiItemFrasi);
        oos.flush();
        oos.close();
    }

}
