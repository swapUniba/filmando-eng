package vettoricontesto;

import main.Configurazione;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

/*La classe VettoreIDF calcola l'IDF dei lemmi contenuti nelle frasi annotate come n frasi comnteneti quel lemma in tutto il dataset.
Gli altri attributi sono:
	HashMap<String, Double> lemmiIDF 	che per ogni lemma associa lo score IDF del lemma e
	HashSet<String> insiemeLemmi				HashSet<Integer> idFraseLemmi
*/
public class VettoreIDF{
	public static int numeroFrasi = 0;
	
	public static HashSet<Integer> 	idFrasi = new HashSet<>();
	public static HashSet<String> 			insiemeLemmi = new HashSet<>();//650
	public static TreeMap<Integer, ArrayList<String>> idFraseLemmi = new TreeMap<>();
	
	public static HashMap<String, Double> lemmiIDF = new HashMap<>();//AD OGNI LEMMA ASSEGNA IDF
    
	
	/*Il metodo calcolaInsiemeLemmi() che partendo dalle FRASI ANNOTATE, //6;war, film, film;3
	1) inserisce i lemmi all'interno del set insiemeLemmi 	*/
    public static void calcolaInsiemeLemmi() throws Exception{
    	Scanner frasi = new Scanner(new File(
    			Configurazione.path + "filesFilmando2\\"  + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\idFraseLemmi.txt"));
    	
    	while (frasi.hasNextLine()){
             String riga = frasi.nextLine();			//6;war, film, film;1     
             StringTokenizer st = new StringTokenizer(riga, ";");	// Lettura id e lemmi
             
             int idFrase = Integer.parseInt(st.nextToken());	//6          
             String testo = st.nextToken();		//war, film, film
             
             StringTokenizer st2 = new StringTokenizer(testo, ",");
             while (st2.hasMoreTokens()){			//TRIM RIMUOVE SPAZI PRIMA E DOPO STRINGA
            	 String lemma = st2.nextToken().trim();//war, film, film ---> war
            	 insiemeLemmi.add(lemma);//CREO LISTA LEMMI DI CUI CALCOLARE IDF escluse stopwords
             }
    	 }
    	 frasi.close();
    }
	
    //LEGGO FRASI DI TUTTI I FILM
    /*Il metodo leggiDataset():
    1) legge tutte le frasi del //6;war, film, film
    2) Conta le frasi lette
    3) Inserisce nella mappa idFraseLemmi l'idfrase e i lemmi letti nella frase.*/
    public static void leggiDataset() throws FileNotFoundException{
    	int totfrasi=0;
    	for(int locale=1; locale <=Configurazione.numeroLocali; locale++) {
    		Scanner inLemmi = new Scanner(new File(
        		Configurazione.path + "filesFilmando2\\frasi singoli items\\" + Configurazione.TipoLemmi + "\\" + locale + ".txt"));
        
    		while (inLemmi.hasNextLine()){
    			String riga = inLemmi.nextLine();	//I:8493;15;honesty, amazing, film, be, day, nothing
    			numeroFrasi++;//tot frasi dataset
    			StringTokenizer st = new StringTokenizer(riga, ";");
        	
    			String loc = st.nextToken();					//I:8493
    			int idFrase = Integer.parseInt(st.nextToken());	//15
    			String testo = st.nextToken();					//honesty, amazing, film, be, day, nothing

    			ArrayList<String> tokens = new ArrayList<>();//INSERISCO LEMMI
            
    			StringTokenizer st2 = new StringTokenizer(testo, ",");
    			while (st2.hasMoreTokens()) {			//TRIM RIMUOVE SPAZI PRIMA E DOPO STRINGA
    				String lemma = st2.nextToken().trim();
    				tokens.add(lemma);
    			}
    			idFraseLemmi.put(totfrasi + idFrase, tokens);	//AGGIUNGO LA COPPIA IDFRASE TOKENS ALLA MAPPA
        	}
			inLemmi.close();
			totfrasi= numeroFrasi;
			//System.out.println(locale + ":  " + totfrasi);
    	}
    }
	
 
    /*Il metodo calcolaIDF(), per ogni LEMMA dell'insieme dei lemmi:
    1) calcola l’IDF rispetto all’intero dataset di frasi:  IDF(l) = # frasi dataset / # frasi dataset contenenti il lemma l
  	2) inserisce la coppia lemma + IDFlemma nella mappa lemmiIDF. */
    public static void calcolaIDF() throws Exception {
    	calcolaInsiemeLemmi();
    	leggiDataset();
    	// PER OGNI LEMMA nell'insieme dei lemmi, calcolato prima da generalemmi: [song, set, cool, historical, cold, film, robbe
    	for (String lemma : insiemeLemmi) {	//[song, set, cool, historical, cold, film, robbery]
            double idf = 0;			//CALCOLO IDF(l)
            int contatoreFrasiContenentiLemma = 0;	//DENOMINATORE
            
            for(int idFrase: idFraseLemmi.keySet()) {	//LEGGO TUTTE LE FRASI DATASET
            	boolean presente = false;
            	for(String lemma2: idFraseLemmi.get(idFrase))	//PER OGNI LEMMA FRASE
            		if (lemma.equals(lemma2)){
            				presente = true;	//se presente
                    		break;	
            		}
            		if (presente){
                        contatoreFrasiContenentiLemma++;	//DENOMINATORE IDF++ 
                    }
            }
            if (contatoreFrasiContenentiLemma==0)	idf=0;
    		else
    			idf = Math.log((double) numeroFrasi / (double) contatoreFrasiContenentiLemma);	//calcolo IDF
            System.out.printf("%-16s %d\n", lemma,contatoreFrasiContenentiLemma);//AGGIUNTO DA ME   
            //System.out.printf(lemma + "\t IDF = " + "ln(" + numeroFrasi + "/" + contatoreFrasiContenentiLemma + ") = " + "%.3f\n", idf);//AGGIUNTO DA ME   
            lemmiIDF.put(lemma, idf);
            			//film; 6,42
        }
    	idFraseLemmi.clear();	//pulisco tutte le frasi del dataset
    	insiemeLemmi.clear();	//pulisco lista lemmi delle annotate
    }

    
///////////////////////////////////////////////////////////////////////////////////////
	//Annullo lo score delle stop words	
	public static void annullamentoScoreStopWordsIDF() throws Exception {
		Scanner stopLemmi = new Scanner(new File( 
				Configurazione.path + "filesFilmando2\\"  + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi +"\\stoplemmi.txt"));		//ORIGINALE
		
		while(stopLemmi.hasNextLine()){
			String stopLemma = stopLemmi.nextLine();//LEGGO STOPLEMMA
			if(lemmiIDF.get(stopLemma)!=null){	//SOLO SE LEMMA PRESENTE NEI LEMMIidf
				lemmiIDF.put(stopLemma, 0.0);
			}
		}
		stopLemmi.close();
	}
   
    
////////////////////////////////////////////////////////STAMPE//////////////////////////////////////////////////////////    
    //STAMPA A VIDEO VETTORE IDF
    public static void stampaIDF(){
    	for (String lemma : lemmiIDF.keySet()){
    		System.out.printf("%-23s\t\t%.2f\n", lemma, lemmiIDF.get(lemma));
    	}
    }

    //STAMPA SU FILE VETTORE IDF
    public static void stampaIDFFile() throws Exception{
    	PrintWriter out = new PrintWriter(new File(
    			Configurazione.path + "filesFilmando2\\"  + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi +"\\strutture\\IDF_Lemmi.txt"));

    	for (String lemma : lemmiIDF.keySet()){
    		out.printf("%-12s\t%.3f\n", lemma, lemmiIDF.get(lemma));
    	}
    	out.flush();
    	out.close();
    }
 
    
    
///////////////////////////////////////////////SERIALIZZAZIONI///////////////////////////////////////////////////////	
    //SERIALIZZAZIONE lemmiIDF su file
    public static void scriviIDFDAT() throws Exception{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
			Configurazione.path + "filesFilmando2\\"  + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\serialized\\lemmiIDF.dat"));
		oos.writeObject(lemmiIDF);
		oos.flush();
		oos.close();
	}
    
	//DESERIALIZZAZIONE lemmiIDF
	public static void leggiIDFDAT() throws Exception{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				Configurazione.path + "filesFilmando2\\"  + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\serialized\\lemmiIDF.dat"));
		lemmiIDF = (HashMap<String, Double>) ois.readObject();
		ois.close();
	}
	
}
