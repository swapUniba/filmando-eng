package ranking;

import vettoricontesto.VettoreIDF;
import main.Configurazione;

import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;


/* Questa classe serve a generare i vettori frase relativi ad un dato FILM
L'attributo è:
	private  TreeMap<Integer, TreeMap<String, Double>> VettoriFrase	
	che mappa ogni FRASE con la coppia 		LEMMA frase + SCORE = IDFlemma se presente, altrimenti 0;
*/

public class VettoriFrase{
	public static TreeMap<Integer, TreeMap<String, Double>> VettoriFrase = new TreeMap<>(); //associa idfrase a vettore contenete lemmi --> score
    
    ////////////////////////////CALCOLO VETTORE FRASE///////////////////////////////////
	/*Il metodo public CalcolaVettoreFrase(String frase):
    Data una stringa = frase come parametro: es: year, release, learn, lot, movie
	1) estrae i lemmi
	2) se il lemma è presente nel vettore LEMMIIDF, associa a quel lemma lo score IDF contenuto nel vettore,  
	   altrimenti score = 0.
	3) inserisce la coppia lemma-score nella mappa vettoreFrase; 
	4) restituisce il vettore frase calcolato.*/
    public static TreeMap<String, Double> CalcolaVettoreFrase(String frase){		//true, story, adapt, novel, bring, screen,
    	TreeMap<String, Double> vettoreFrase = new TreeMap<>();
    	StringTokenizer st = new StringTokenizer(frase, ",");
        while(st.hasMoreTokens()){			//true, story, adapt, novel, bring, screen,
            String parola = st.nextToken().trim();
            // Se nel vettore lemmiIDF c'è il lemma, allora prendo l'IDF di quel lemma calcolato
            if (VettoreIDF.lemmiIDF.containsKey(parola)){	//song  1,386     	
                vettoreFrase.put(parola, VettoreIDF.lemmiIDF.get(parola));
            }					//bank; 					0,386
        }//FINE LEMMI FRASE
        //RIMANENTI LEMMI del vettore IDF non presenti nella frase li metto in VETT FRASE con score=0 
        for (String lemma : VettoreIDF.lemmiIDF.keySet()){
            if (!vettoreFrase.containsKey(lemma)){
                vettoreFrase.put(lemma, 0.0);
            }
        }
        return vettoreFrase;
    }

    
    //Il metodo calcolaVettoriFraseFilm(int idFilm), dato un FILM
    //- insiersce nella mappa VettoriFrase l'id della FRASE del film + le coppie LEMMA-SCORElemma.
    public static void calcolaVettoriFraseFilm(int idFilm) throws Exception{
    	Scanner in = new Scanner(new File(
    			Configurazione.path + "filesFilmando2\\frasi singoli items\\" + Configurazione.TipoLemmi + "\\" + idFilm+".txt"));//1-40
        //I:8493;1;year, release, learn, lot, movie        
        while(in.hasNextLine()){	//PER OGNI FRASE		//I:8493;1;year, release, learn, lot, movie
        	String riga = in.nextLine();
        	StringTokenizer st = new StringTokenizer(riga,";");
        	String loc = st.nextToken();					//I:8459
        	int idFrase = Integer.parseInt(st.nextToken());//1
        	String frase = st.nextToken();
            /////////////CREAZIONE VETTORE FRASE/////////////////////////////////
            VettoriFrase.put(idFrase, CalcolaVettoreFrase(frase));//aggiungo il vett frase
       }
       in.close();//CHIUSURA FILE 1.txt //////FINE LETTURA FRASI PER UN DATO FILM( es 2.txt)                 
    }
    
    
/////////////////////////////////////////////////////STAMPE///////////////////////////////////////
    public static void stampaVettoreFrase(TreeMap<String, Double> vettoreFrase){
    	 System.out.printf("%-13s\t%-13s\n","L","SCORE");
         for (String lemma : vettoreFrase.keySet()) {	//righe matrice
          	System.out.printf("%-13s\t%.3f\n",lemma, vettoreFrase.get(lemma));
         }
         System.out.println("\n\n");
    }
    
}
