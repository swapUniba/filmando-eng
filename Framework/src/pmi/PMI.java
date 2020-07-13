package pmi;

import vettoricontesto.MatriceLC;
import main.Configurazione;

import java.io.*;
import java.util.*;


public class PMI {
	public static HashSet<String> 		insiemeLemmi = new HashSet<>();
	public static TreeMap<Integer, ArrayList<String>> idFraseLemmi = new TreeMap<>();
    public static TreeMap<Integer, Integer> idFraseidContesti = new TreeMap<>();//2;3
    
    //public static TreeMap<String, TreeMap<Integer, Integer>> MatriceFreqLemmaContesto = new TreeMap<>();
    //public static TreeMap<String, TreeMap<Integer, Double>> matriceLemmaContesto = new TreeMap<>();
    
    public static void leggiAnnotate() throws FileNotFoundException{
    	Scanner frasi = new Scanner(new File(
    			Configurazione.path + "filesFilmando2\\" + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\idFraseLemmi.txt"));
    	
    	while (frasi.hasNextLine()){
             String riga = frasi.nextLine();			//6;war, film, film;1     
             StringTokenizer st = new StringTokenizer(riga, ";");	// Lettura id e lemmi

             int idFrase = Integer.parseInt(st.nextToken());	//6
             	//hashset<int>		//[1,2,3,4,5,6,7,8,9...]             
             String testo = st.nextToken();		//war, film, film
             int contesto = Integer.parseInt(st.nextToken());
             idFraseidContesti.put(idFrase, contesto);
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


	//# occorrenze lemma l in tutte le frasi annotate per il contesto c
	//////////////////////////////////////////INIZIO TF///////////////////////////////////////////////////
    public static void calcolaPMI() throws Exception{
     	leggiAnnotate();
     	//////////////PER OGNI LEMMA l///////////////////////////////////
        for(String lemma : insiemeLemmi){ //insieme lemmi annotate[war,film,ellipse...]
        	if(FrequenzaLemma.freqLemma.get(lemma)!=null){//caso idf>=20
       		
        		int idf = FrequenzaLemma.freqLemma.get(lemma);	//LEGGO IDF
        		TreeMap<Integer,Double> PMIcontesto= new TreeMap<>();
        		//TreeMap<Integer,Integer> FreqLemmaContesto = new TreeMap<>();	//DEBUG
             
        		for(int contesto=1; contesto<=Configurazione.numeroContesti; contesto++) {
        			double PMI =0;
        			int freqCont= FrequenzaContesto.FreqCont.get(contesto);//es 128
            	 
        			int contatoreOccorrenze = 0;
        			for(int idFrase: idFraseLemmi.keySet()){//PER OGNI FRASE ANNOTATA	//1;film, set, bank, robbery, cool, take, place
        				if(idFraseidContesti.get(idFrase)==contesto){//SE ANNOTATA PER QUEL CONTESTO
            			 
        					for(String lemma2: idFraseLemmi.get(idFrase)){//ITERO LEMMI FRASE
        						if (lemma.equals(lemma2))	 
        							contatoreOccorrenze++;	//NUM++	//conta occorrenze lemmi nella frase	
        					}
        				}
        			}
        			int FreqLemmaCont = contatoreOccorrenze;
        			//FreqLemmaContesto.put(contesto, FreqLemmaCont);//DEBUG
        			//System.out.println(lemma + "\t" + contesto + " \t freq:  "+ FreqLemmaCont );
            	
        			double denominatore = idf * freqCont;//200*160
        			//System.out.println(lemma + "   " + contesto + " ---> "+ FreqLemmaCont + " / " + denominatore);
        			PMI = FreqLemmaCont /denominatore;
        			if (denominatore==0)	PMI=0;
        			//System.out.printf("%s\t %s PMI:  %.3f : %.3f = %.3f\n",lemma,contesto, numeratore, denominatore,PMI);
                 
        			PMIcontesto.put(contesto, PMI);	//1, 4,67
        		}//PER OGNI FRASE ANNOTATA]
        		//MatriceFreqLemmaContesto.put(lemma, FreqLemmaContesto);//memorizzo freq(l,c)//DEBUG
        		MatriceLC.matriceLemmaContesto.put(lemma, PMIcontesto);
        	}//fine if
        }
        idFraseLemmi.clear();	//pulisco tutte le frasi ANNOTATE
    	insiemeLemmi.clear();	//pulisco lista lemmi delle ANNOTATE
    	idFraseidContesti.clear();//pulisco vettore frase contesti ANNOTATE
    	FrequenzaLemma.freqLemma.clear();
     }

  //pongo lo score delle stop words a 1 per tutti i contesti
    public static void annullamentoScoreStopWords() throws Exception{
    	Scanner stopLemmi = new Scanner(new File( 
    			Configurazione.path + "filesFilmando2\\" + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi +"\\stoplemmi.txt"));		//ORIGINALE
        while(stopLemmi.hasNextLine()){
            String stopLemma = stopLemmi.nextLine();
            if(MatriceLC.matriceLemmaContesto.get(stopLemma)!=null) {
                for (int contesto : MatriceLC.matriceLemmaContesto.get(stopLemma).keySet()) {
                	MatriceLC.matriceLemmaContesto.get(stopLemma).put(contesto, 0.0);
                }
            }//FINE IF	
        }
        stopLemmi.close();
    }
    
    
    
    
    
/////////////////////////////////////////STAMPE//////////////////////////////////////////////////////////
    public static void stampaMatriceLemmaContesto(){
        System.out.printf("%-20s\tHA\tLA\tCO\tBA\tFR\tGM\tBM\n", "L");
        for (String lemma : MatriceLC.matriceLemmaContesto.keySet()){
            System.out.printf("%-20s\t", lemma);
            for (int idContesto : MatriceLC.matriceLemmaContesto.get(lemma).keySet()){
                System.out.printf("%.3f\t", MatriceLC.matriceLemmaContesto.get(lemma).get(idContesto));
            }
            System.out.println();
        }
    }
    
  //STAMPA MATRICE LEMMA CONTESTO SU FILE
    public static void stampaMatriceLemmaContestoFile() throws Exception {
        PrintWriter out = new PrintWriter(new File(
        		Configurazione.path + "filesFilmando2\\" + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi +"\\matrice lemma contesto.txt"));
        out.printf("%-13s\tHA\tLA\tCO\tBA\tFR\tGM\tBM\n", "L");
        for (String lemma : MatriceLC.matriceLemmaContesto.keySet()){
            out.printf("%-13s\t", lemma);
            for (int idContesto : MatriceLC.matriceLemmaContesto.get(lemma).keySet()){
                out.printf("%.11f\t", MatriceLC.matriceLemmaContesto.get(lemma).get(idContesto));
            }
            out.println();
        }
        out.flush();
        out.close();
    }
    
    

    public static void stampaIdFrasiIdContesti() {
        for (int idFrase : idFraseidContesti.keySet()){
            System.out.println(idFrase + " --> " + idFraseidContesti.get(idFrase));  
        }
    }
    
    //stampa freq semplice
    
 
  //STAMPA MATRICE LEMMA CONTESTO SU FILE
    /*
    public static void stampaMatriceFreqLemmaContestoFile() throws Exception {
        PrintWriter out = new PrintWriter(new File(
        		Configurazione.path + "filesFilmando2\\" + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi +"\\matrice freq lemma contesto.txt"));
        out.printf("%-13s\tHA\tLA\tCO\tBA\tFR\tGM\tBM\n", "L");
        for (String lemma : MatriceFreqLemmaContesto.keySet()){
            out.printf("%-13s\t", lemma);
            for (int idContesto : MatriceFreqLemmaContesto.get(lemma).keySet()){
                out.printf("%d\t", MatriceFreqLemmaContesto.get(lemma).get(idContesto));
            }
            out.println();
        }
        out.flush();
        out.close();
    }
    */

}
