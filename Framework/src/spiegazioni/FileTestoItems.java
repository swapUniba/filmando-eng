package spiegazioni;

import main.Configurazione;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;


/*	Questa classe mantiene le strutture dati che contengono le frasi delle recensioni dei vari item in due forme: 	
 * 1) lemmizzata 2) in chiaro (necessaria per l'analisi sintattica). 
Le strutture in questione sono:
	public static TreeMap<Integer, String> frasiLemmi: 	mappa l段d frase (es 5) con la sua frase lemmizzata;
	public static TreeMap<Integer, String> frasiIntere: mappa l段d frase (es 5) con la sua versione in chiaro;
Poi abbiamo:
	public static TreeMap<Integer, String> film: 		mappa l段d del film (es 29) col suo nome (Fargo)
*/																			//key
public class FileTestoItems {												//1	 film, song, bank, honesty, amazing, film, know, people
    public static TreeMap<Integer, String> frasiLemmi = new TreeMap<>();	//2	 think, be, pill, see, day, open, phrase, nothing, favorite
    public static TreeMap<Integer, String> frasiIntere = new TreeMap<>();
    
    public static HashMap<Integer, String> film = new HashMap<Integer, String>();
    
/* Il metodo public static void leggiFrasiLocale(int locale), dato l段d di un item (film) come parametro, 
	1) legge tutte le frasi relative a quell段tem, 1) sia lemmizzate 2) sia in chiaro, 
	2) le inserisce nelle rispettive strutture. */
    
    public static void leggiFrasiLocale(int locale) throws Exception{
///////////////////////////////////////////////FRASI INTERE//////////////////////////////////////////////////////////////////
    	Scanner inIntere = new Scanner(new File(
    			Configurazione.path + "filesFilmando2\\frasi singoli items\\intere\\"+ locale +".txt"));//Lettura 29.txt TESTUALE 
        while(inIntere.hasNextLine()){	//I:8459;3685;the performances are remarkable,the cinematography is the best....
        	String riga = inIntere.nextLine();
        	StringTokenizer st = new StringTokenizer(riga, ";");
            
        	String loc = st.nextToken();					//I:8459
            int idFrase = Integer.parseInt(st.nextToken()); //3685
            String frase = st.nextToken();					//the performances...
            frasiIntere.put(idFrase, frase);	//AGGIUNGE ALLA MAPPA con key= ES 5	 e	value= FRASE INTERA
        }																//key= ES 6	 e	value= FRASE INTERA2
        inIntere.close();

///////////////////////////////////////////////FRASI LEMMA//////////////////////////////////////////////////////////////////             
        Scanner inLemmi = new Scanner(new File(
        		Configurazione.path + "filesFilmando2\\frasi singoli items\\" + Configurazione.TipoLemmi + "\\" + locale + ".txt"));        
        //LETTURA 29.txt lemmatizzato (UNIGRAMMI)
        while (inLemmi.hasNextLine()){		//I:8493;15;honesty, amazing, film, be, day, nothing
        	String riga = inLemmi.nextLine();
        	StringTokenizer st = new StringTokenizer(riga, ";");
        	
        	String loc = st.nextToken();
        	int idFrase = Integer.parseInt(st.nextToken());	//15
            String lemmi = st.nextToken();					//honesty, amazing, film, be, day, nothing
            frasiLemmi.put(idFrase, lemmi);
        }
        inLemmi.close();
        
    }
    
    
    //LETTURA SCHEDA FIL, PER MATRICE LOCALE CONTESTO
    //memorizza titolo film e idFilm
    public static void LeggiFilm() throws FileNotFoundException {
    Scanner schedaFilm = new Scanner(new File(
    		Configurazione.path + "filesFilmando2//Scheda Film.txt"));
    while(schedaFilm.hasNextLine()){	//1;Pulp Fiction;
    	String riga = schedaFilm.nextLine();
    	StringTokenizer st = new StringTokenizer(riga, ";");
    	int idFilm= 		Integer.parseInt(st.nextToken());//1
    	String titolo = 	st.nextToken();					//Pulp Fiction;
    	film.put(idFilm,titolo);
    	}
    schedaFilm.close();
    }
    
    
    
/////////////////////////////////////////////////STAMPE///////////////////////////////////////////////
    public static void StampaFrasiLocale() {
    	for (int idFrase : frasiLemmi.keySet()){
    		System.out.println("frase n   " + idFrase + "\t" + frasiLemmi.get(idFrase));	
    	}	
    	/*
    	for (int idFrase : frasiIntere.keySet()){
    		System.out.println(idFrase + "\t" + frasiIntere.get(idFrase));	
    	}	
    	*/
    }
    
////////////////////////////////////SERIALIZZAZIONE////////////////////////////////////////////////	
    //scrittura frasi .dat
    public static void ScriviFrasiLocaleDAT(int locale) throws Exception {
    	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream((new File(
    			Configurazione.path + "filesFilmando2/frasi singoli items/intere dat/"+ locale +".dat")))); //AGGIUNTO DA MIRKO, PER GENERARE LE 1,2...dat
        oos.writeObject(frasiIntere);	
    	oos.flush();					
        oos.close();					
    }
    //lettura frasi .dat
    public static void LeggiFrasiLocaleDAT(int locale) throws Exception {
    	ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(
     		Configurazione.path + "filesFilmando2/frasi singoli items/intere dat/" + locale + ".dat")));
        frasiIntere = (TreeMap<Integer, String>) ois.readObject();
        ois.close();
    }
    
}
