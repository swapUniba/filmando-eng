package vettoricontesto;

import main.Configurazione;

import java.io.*;
import java.util.*;


/*2) Questa classe calcola la MATRICE LEMMA FRASE. L’attributo principale è
	TreeMap<String, TreeMap<Integer, Double>> matriceLemmaFrase
	che associa ad ogni lemma una mappa che associa ad ogni frase lo score TF-IDF
	e matriceLemmaTF che memorizza solo il TF
 Gli altri attributi sono:
	HashSet<String> insiemeLemmi				HashSet<Integer> idFraseLemmi*/

public class MatriceLF {
    public static TreeMap<String, TreeMap<Integer, Double>> matriceLemmaFrase = new TreeMap<>();
    public static TreeMap<String, TreeMap<Integer, Double>> matriceLemmaTF = new TreeMap<>();
    
    public static int numeroFrasi = 0;
    
    public static HashSet<Integer> 		idFrasi = new HashSet<>();
    public static HashSet<String> 		insiemeLemmi = new HashSet<>();
    public static TreeMap<Integer, ArrayList<String>> idFraseLemmi = new TreeMap<>();
     
    //Il metodo leggiAnnotate() legge le annotate memorizzando il set di lemmi, gli id delle frasi annotate e poi
    //per ogni frase la lista di lemmi contenuti in essa
	public static void leggiAnnotate() throws FileNotFoundException{
    	Scanner frasi = new Scanner(new File(
    			Configurazione.path + "filesFilmando2\\"  + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\idFraseLemmi.txt"));
    	
    	while (frasi.hasNextLine()){
             String riga = frasi.nextLine();			//6;war, film, film;1     
             StringTokenizer st = new StringTokenizer(riga, ";");	// Lettura id e lemmi
             numeroFrasi++;
             //System.out.println(numeroFrasi);
             int idFrase = Integer.parseInt(st.nextToken());	//6
             idFrasi.add(idFrase);		//AGGIUNGO 
             	//hashset<int>		//[1,2,3,4,5,6,7,8,9...]             
             String testo = st.nextToken();		//war, film, film
             
             ArrayList<String> tokens = new ArrayList<>();// I lemmi vengono inseriti in TOKENS
            
             StringTokenizer st2 = new StringTokenizer(testo, ",");
             while (st2.hasMoreTokens()) {			//TRIM RIMUOVE SPAZI PRIMA E DOPO STRINGA
            	 String lemma = st2.nextToken().trim();
            	 tokens.add(lemma);
            	 insiemeLemmi.add(lemma);
             }
             idFraseLemmi.put(idFrase, tokens);	//AGGIUNGO LA COPPIA FRASE TOKENS ALLA MAPPA
    	 }
    	 frasi.close();
    }
	
    //////////////////////////////////////////INIZIO TF///////////////////////////////////////////////////
	/* Il metodo calcolaMatriceLemmaFrase() 
    1) PER ogni lemma, legge l'IDF del lemma precedentemente calcolato
    2) per ogni frase annotata calcola:   
  		a) il TF del LEMMA rispetto alla frase annotata
  		b) il TF-IDF del LEMMA	rispetto alla frase annotata
 	3) Viene inserita la coppia frase tf-id nella mappa TreeMap<Integer, Double> frasiTFIDF. 
    4) Infine viene inserita nella matriceLemmaFrase la coppia lemma - frasiTFIDF.
    */
    public static void calcolaMatriceLemmaFrase() throws Exception{
    	leggiAnnotate();
    	int count=1;
    	System.out.println(insiemeLemmi.size());
        for(String lemma : insiemeLemmi){/////////PER OGNI LEMMA l///////////////			//[war,film,ellipse...]
            double tf;
            double idf = VettoreIDF.lemmiIDF.get(lemma);		//IDF è relativo a dato lemma: es: film; 0,288								
            
            //TreeMap<Integer, Double> frasiTF = new TreeMap<>();//AGGIUNTA DA ME, per visualizzare tf   //PER MATRICE LEMMA-TF         
            TreeMap<Integer, Double> frasiTFIDF = new TreeMap<>();
            ////////////////////// CALCOLO TF////////////////////////////////////
            // TF(l,f)= # occorrenze lemma l in frase f
                		 // tot lemmi frase f
            for(int idFrase: idFraseLemmi.keySet()){//ITERO FRASI	//1;film, set, bank, robbery, cool, take, place
            	int contatoreOccorrenze = 0, 	contatoreLemmi = 0;	//NUM		DEN
            	//PER OGNI FRASE ANNOTATA f, calcolo TF lemma (scelto in insieme lemmi) rispetto alla frase f 
            	for(String lemma2: idFraseLemmi.get(idFrase)){//ITERO LEMMI FRASE
            		contatoreLemmi++;//CONTO LEMMI FRASE,	DENOM++
            		if (lemma.equals(lemma2)){
            			contatoreOccorrenze++;	//NUM++	//conta occorrenze lemmi nella frase
            		}
            	}
            	tf = (double)contatoreOccorrenze / (double)contatoreLemmi;
            	//System.out.println(lemma + "\t" + idFrase + "\t TF= " + contatoreOccorrenze + "/" + contatoreLemmi + "= " + tf );                
            	//frasiTF.put(idFrase, tf);//PER MATRICE LEMMA-TF
            	////////////////Calcolo TF-IDF///////////////////////////
            	double tfidf = tf * idf;	//TF-IDF l,f, dato il lemma								
            	//System.out.printf("%s\t%s\tTFIDF:  %.3f * %.3f = %.3f\n",lemma,idFrase, tf, idf,tfidf);
                
            	frasiTFIDF.put(idFrase, tfidf);	//1, 4,67
            }//PER OGNI FRASE ANNOTATA]		//2, 5,63
            //System.out.println("");	//AGGIUNTO DA ME PER STAMPA
            
  ///////////////////RIEMPIMENTO celle senza valore MatriceTF/////////////////////
            /*//PER MATRICE LEMMA-TF
            for (int idFrase : idFrasi){	//[1,2,3,4,5,6,7,8,9...]
                if (!frasiTF.containsKey(idFrase)){
                    frasiTF.put(idFrase, 0.0);
                }
            }
            */
            //RIEMPIMENTO celle senza valore Matrice LEMMA FRASE
            for (int idFrase : idFrasi){	//[1,2,3,4,5,6,7,8,9...]
                if (!frasiTFIDF.containsKey(idFrase)){
                    frasiTFIDF.put(idFrase, 0.0);
                }
            }
            //matriceLemmaTF.put(lemma, frasiTF);//AGGIUNTO DA ME
            matriceLemmaFrase.put(lemma, frasiTFIDF);		//war, 1, 4,67
            System.out.println(count);
            count++;
        }//////////////////////////PER OGNI LEMMA///////////			//..., 611, 4,78
    /////////////////////////PULIZIA////////////////////////////////////////////////////////
        idFraseLemmi.clear();	//pulisco tutte le frasi ANNOTATE
    	insiemeLemmi.clear();	//pulisco lista lemmi delle ANNOTATE
    }
    

    //////////////////////////////////////////STAMPE MATRICE LEMMA FRASE//////////////////////
    //STAMPA A VIDEO LEMMA FRASE
    public static void stampaMatriceLemmaFrase(){
        for (String lemma : matriceLemmaFrase.keySet()){
            System.out.printf("%-25s -> { ", lemma);
            for (int idFrase : matriceLemmaFrase.get(lemma).keySet()){
                System.out.printf("%d -> %.3f , ", idFrase, matriceLemmaFrase.get(lemma).get(idFrase));
            }
            System.out.println("}");
        }
    }
    
    //STAMPA matrice LEMMA FRASE su FILE
    public static void stampaMatriceLemmaFraseFile() throws Exception{
        PrintWriter out = new PrintWriter(new File(
        		Configurazione.path + "filesFilmando2\\"  + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi +"\\strutture\\matrice lemma frase.txt"));
        out.printf("%-13s\t", "L");
        for (int i=1; i<= numeroFrasi; i++)
        		out.print(i + "\t");	//n frasi annotate
        out.print("\n");
        
        for (String lemma : matriceLemmaFrase.keySet()){	////[war,film,ellipse...]
        	out.printf("%-13s\t", lemma);		//MIO		//war, 1, 4,67
            for (int idFrase : matriceLemmaFrase.get(lemma).keySet()){	//[1,2,3,4,5,6]
            	out.printf("%.3f\t",matriceLemmaFrase.get(lemma).get(idFrase));	//MIO
            	//out.printf("%-13s\t%d\t%.2f\n",lemma, idFrase, matriceLemmaFrase.get(lemma).get(idFrase));	//MIO
            }
            out.println("");
        }
        out.flush();
        out.close();

    }
    
    
    //AGGIUNTO DA ME,per debugging		SCRIVE MATRICE LEMMA-TF SU FILE
    public static void stampaMatriceLemmaTFFile() throws Exception{
        PrintWriter out = new PrintWriter(new File(
        		Configurazione.path + "filesFilmando2\\"  + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi +"\\strutture\\matrice lemmaTF.txt"));
        out.printf("%-13s\t", "L");
        for (int i=1; i<= numeroFrasi; i++)
        		out.print(i + "\t");	//n frasi annotate
        out.print("\n");
        
        for (String lemma : matriceLemmaTF.keySet()){	////[war,film,ellipse...]
        	out.printf("%-13s\t", lemma);		//MIO		//war, 1, 4,67
            for (int idFrase : matriceLemmaTF.get(lemma).keySet()){	//[1,2,3,4,5,6]
            	out.printf("%.3f\t",matriceLemmaTF.get(lemma).get(idFrase));	//MIO
            	//out.printf("%-13s\t%d\t%.2f\n",lemma, idFrase, matriceLemmaFrase.get(lemma).get(idFrase));	//MIO
            }
            out.println("");
        }
        out.flush();
        out.close();

    }
    
 
////////////////////////////////////////////SERIALIZZAZIONI//////////////////////////////////////////////////////////
    //legge .dat matrice	
    public static void LeggiMatriceLemmaFraseDAT() throws Exception{
    	ObjectInputStream oin = new ObjectInputStream(new FileInputStream(new File(
    			Configurazione.path + "filesFilmando2\\"  + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\serialized\\lemma-frase.dat")));
    	matriceLemmaFrase = (TreeMap<String, TreeMap<Integer, Double>>) oin.readObject();
    	oin.close();
    }
    
    //SCRIVE in .dat matrice	
    public static void ScriviMatriceLemmaFraseDAT() throws Exception{	
    	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(
    			Configurazione.path + "filesFilmando2\\"  + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\serialized\\lemma-frase.dat")));
        oos.writeObject(matriceLemmaFrase);
        oos.flush();
        oos.close();
    }

}
