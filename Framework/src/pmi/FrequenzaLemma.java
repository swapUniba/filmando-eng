package pmi;

import vettoricontesto.VettoreIDF;
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


//CODICE NORMALE, CALCOLO IDF DA ANNOTATE

public class FrequenzaLemma{
	public static int numeroFrasi = 0;
	
	public static HashSet<String> 		insiemeLemmi = new HashSet<>();//650
	public static HashSet<Integer> 		idFrasi = new HashSet<>();
	public static TreeMap<Integer, ArrayList<String>> idFraseLemmi = new TreeMap<>();
	//public static HashMap<String, Double> lemmiIDF = new HashMap<>();//AD OGNI LEMMA ASSEGNA FREQUENZA
	public static HashMap<String, Integer> freqLemma = new HashMap<>();//AD OGNI LEMMA ASSEGNA IDF
    
	
/*Il 1 metodo che viene eseguito è generaLemmi(), che partendo dalle FRASI ANNOTATE, //6;war, film, film
1) suddivide i vari lemmi in base alla configurazione, 
2) assegna ad ognuno in id numerico, 														1	2		2
3) inserisce gli id nell’insieme degli id 	e 		i lemmi nell’insieme dei lemmi.*/
    
	//CREO INSIEME DEI LEMMI DI CUI CALCOLARE IDF
    public static void calcolaInsiemeLemmi() throws Exception{
    	Scanner frasi = new Scanner(new File(
    			Configurazione.path + "filesFilmando2\\" + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\idFraseLemmi.txt"));
    	
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

    //per calcolo occorrenze lemma in frasi di tutto il dataset
    //LEGGO FRASI DI TUTTI I FILM
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
    	System.out.println(numeroFrasi);//tot frasi dataset
    }
	
	
    //IDF(l) = # occorrenze lemma in tutte frasi dataset
    //invariato
    public static void calcolaFreqLemma() throws Exception{
    	calcolaInsiemeLemmi();
    	leggiDataset();
    	for (String lemma : insiemeLemmi){	//[song, set, cool, historical, cold, film, robbery] SET LEMMI ANNOTATE
            int FreqLemma = 0;
            for(int idFrase: idFraseLemmi.keySet()) {	//LEGGO TUTTE LE FRASI DEL DATASET
            	for(String lemma2: idFraseLemmi.get(idFrase))	//PER OGNI LEMMA FRASE DATASET
            		if (lemma.equals(lemma2)){
            			FreqLemma++;	
            		}
            }
    		if(FreqLemma>=60){//non eccessivamente rari
    			double idf = Math.log((double) numeroFrasi /(double) FreqLemma);
    			System.out.printf("%-16s %d        %.5f\n", lemma, FreqLemma, idf );//AGGIUNTO DA ME
    			freqLemma.put(lemma, FreqLemma);	//lemmi rari hanno valore idf alto			
    			VettoreIDF.lemmiIDF.put(lemma, idf);
    		}
        }
    	idFraseLemmi.clear();	//pulisco tutte le frasi del dataset
    	insiemeLemmi.clear();	//pulisco lista lemmi delle annotate
    	
    }
    
    //////////////////////////////////////
    //elimino lo score delle stop words	in LEMMIIDF
    public static void annullamentoScoreStopWords() throws Exception{
    	Scanner stopLemmi = new Scanner(new File( 
    			Configurazione.path + "filesFilmando2\\" + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi +"\\stoplemmi.txt"));		//ORIGINALE

    	while(stopLemmi.hasNextLine()){
    		String stopLemma = stopLemmi.nextLine();//LEGGO STOPLEMMA
    		if(VettoreIDF.lemmiIDF.get(stopLemma)!=null){	//SOLO SE LEMMA PRESENTE NEI LEMMIidf
    			VettoreIDF.lemmiIDF.put(stopLemma, 0.0);
    		}
    	}
    	stopLemmi.close();
    }

    
/////////////////////////////////////////////////////STAMPE//////////////////////////////////////////////////////////    
    //////////////////////////////////////////////////STAMPE IDF////////////////////////////////////////////////////////////////////////////
    //stampa idf semplice
    public static void stampaIDF(){
    	for (String lemma : VettoreIDF.lemmiIDF.keySet()){
    		System.out.printf("%-23s\t\t%.2f\n", lemma, VettoreIDF.lemmiIDF.get(lemma));
    	}
    }

    //AGGIUNTO DA ME
    public static void stampaIDFFile() throws Exception{
    	PrintWriter out = new PrintWriter(new File(
    			Configurazione.path + "filesFilmando2\\" + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi +"\\strutture\\IDF_Lemmi.txt"));

    	for (String lemma : VettoreIDF.lemmiIDF.keySet()){
    		out.printf("%-12s\t %.5f\n", lemma, VettoreIDF.lemmiIDF.get(lemma));
    	}
    	out.flush();
    	out.close();
    }
 
    
    
///////////////////////////////////////////////SERIALIZZAZIONI///////////////////////////////////////////////////////	
  //SERIALIZZAZIONE freqLemma su file
    public static void scriviFreqLemmaDAT() throws Exception{
    	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
    			Configurazione.path + "filesFilmando2\\" + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\serialized\\freqLemma.dat"));
    	oos.writeObject(freqLemma);
    	oos.flush();
    	oos.close();
    }

    //DESERIALIZZAZIONE freqLemma
    public static void leggiFreqLemmaDAT() throws Exception{
    	ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
    			Configurazione.path + "filesFilmando2\\" + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\serialized\\freqLemma.dat"));
    	freqLemma = (HashMap<String, Integer>) ois.readObject();
    }
    
    
    
    //SERIALIZZAZIONE lemmiIDF su file
    public static void scriviIDFDAT() throws Exception{
    	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
    			Configurazione.path + "filesFilmando2\\" + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\serialized\\lemmiIDF.dat"));
    	oos.writeObject(VettoreIDF.lemmiIDF);
    	oos.flush();
    	oos.close();
    }

    //DESERIALIZZAZIONE lemmiIDF
    public static void leggiIDFDAT() throws Exception{
    	ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
    			Configurazione.path + "filesFilmando2\\" + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\serialized\\lemmiIDF.dat"));
    	VettoreIDF.lemmiIDF = (HashMap<String, Double>) ois.readObject();

    }

    
}
