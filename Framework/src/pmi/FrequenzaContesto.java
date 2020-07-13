package pmi;

import main.Configurazione;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FrequenzaContesto {
	public static HashMap<String, Integer> 	insiemeLemmiContesto = new HashMap<>();//COPPIE LEMMA, CONTEESTO
	public static HashMap<Integer, Integer> FreqCont = new HashMap<>();//AD OGNI CONTESTO ASSEGNA SCORE
	 
	public static void leggiAnnotate() throws FileNotFoundException{
    	Scanner frasi = new Scanner(new File(
    			Configurazione.path + "filesFilmando2\\" + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\idFraseLemmi.txt"));
    	
    	while (frasi.hasNextLine()) {
             String riga = frasi.nextLine();			//6;war, film, film;1     
             StringTokenizer st = new StringTokenizer(riga, ";");	// Lettura id e lemmi
             int idFrase = Integer.parseInt(st.nextToken());	//6
             	//hashset<int>		//[1,2,3,4,5,6,7,8,9...]             
             String testo = st.nextToken();		//war, film, film
             int contesto = Integer.parseInt(st.nextToken());
             
             StringTokenizer st2 = new StringTokenizer(testo, ",");
             while (st2.hasMoreTokens()) {
            	 String lemma = st2.nextToken().trim();
            	 insiemeLemmiContesto.put(lemma, contesto);
             }
    	}
    	frasi.close();
    }
	
	
	
	//freq(c)= tot lemmi di tutte le frasi annotate per il contesto c.	
    //20.000 * 6000
	 public static void calcolaFreqContesto() throws Exception{
		 leggiAnnotate();
		 for(int contesto=1; contesto<=Configurazione.numeroContesti; contesto++) {
	            int contatoreLemmiContesto = 0; 
	            
	            for(String lemma: insiemeLemmiContesto.keySet()){	//LEGGO TUTTE LE FRASI ANNOTATE
	            	if(insiemeLemmiContesto.get(lemma)==contesto)//SE FRASE ANNOTATA PER CONTESTO
	            		contatoreLemmiContesto++;
	            }
	            FreqCont.put(contesto, contatoreLemmiContesto);	//lemmi rari hanno valore idf alto
		 }
		 insiemeLemmiContesto.clear();//PULIZIA
		 stampaFreqContesto();
	 }
	 
	 
////////////////////////////////////////////STAMPE//////////////////////////////////////////////////////////// 
	 public static void stampaFreqContesto(){
	    	System.out.printf("%-5s\t%-5s\n", "contesto", "frequenza");
	    	for (int contesto : FreqCont.keySet()){
	    		System.out.printf("%-5s\t%-5s\n", contesto, FreqCont.get(contesto));
	    	}
	    }    
	 
	 public static void stampaFreqContestoFile() throws FileNotFoundException{
    	 PrintWriter out = new PrintWriter(new File(
         		Configurazione.path + "filesFilmando2\\" + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\strutture\\vettore frequenza contesto.txt"));
    	
    	out.printf("%-5s\t%-5s\n", "contesto", "frequenza");
    	for (int contesto : FreqCont.keySet()){
    		out.printf("%-5s\t%-5s\n", contesto, FreqCont.get(contesto));
    	}
    	out.flush();
        out.close();
    }

}
