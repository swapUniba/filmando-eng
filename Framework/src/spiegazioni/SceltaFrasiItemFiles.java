package spiegazioni;

import ranking.VettoriFrase;
import ranking.SimilaritaCoseno;//sia matrice similarità che calcolo
import main.Configurazione;

import java.util.*;

//Questa classe calcola le frasi per le spiegazioni per giustapposizione di frasi e per centroide.
public class SceltaFrasiItemFiles {	
//////////////////////////////////////////SINGOLO CONTESTO//////////////////////////////////////////////
/* Il metodo public static void scegliFraseSingoloContesto(int IdFilm, int contesto), 
	dato un singolo CONTESTO, calcola la FRASE con SCORE di SIMILARITA MAGGIORE tra quelle lemmatizzate in FILETESTOITEMS
	Il metodo, per ogni frase di FileTestoItems:
	1) verifica la lunghezza della frase (fra 5 e 15)
	2) Fa l'analisi sintattica della frase
	3) legge la similarità del vettore frase col vettore contesto nella matrice Similarità
	4) tiene in memoria la frase con score di similarità maggiore per quel contesto e la ritorna */
    public static int scegliFraseSingoloContesto(int IdFilm, int contesto) throws Exception{
        double max = 0;
        int IdFraseScoreMax=1;        
    	for (int idFrase : FileTestoItems.frasiLemmi.keySet()){	//es frase 3 su 7500 del film 1.txt letto prima     	
			
    		if(controlloLunghezza(idFrase) == true){
				if(POSTagger.syntax(FileTestoItems.frasiIntere.get(idFrase))){//CONTROLLO FRASE IMPERSONALE
					
					double score = SimilaritaCoseno.Similarita.get(IdFilm).get(contesto).get(idFrase);	
					if(score>max){
						IdFraseScoreMax = idFrase;
						max=score;
					}
				}//FILTRO FRASI
			}
        }//FINE FILE 	1.txt      
    	return IdFraseScoreMax;
    }
    
    //NUOVO CONTROLLO
    //Controlla lunghezza della frase //HA DAI 5 AI 28 LEMMI
    public static boolean controlloLunghezza(int idFrase){
    	String testoFrase = FileTestoItems.frasiIntere.get(idFrase);
    	String[] words = testoFrase.split(" ");
    	int lunghezzaFrase = words.length;
    	//System.out.println(testoFrase + "\t\t"+  lunghezzaFrase);
        if (lunghezzaFrase >= 5 && lunghezzaFrase <= 28) 	
        	return true;
        else												
        	return false;
    }

    
///////////////////////////////////////////////////COMBINAZIONE CONTESTI///////////////////////////////////////////////////////
    /* Il metodo public static void scegliFrasiCombinazioneContesti(TreeMap<String,Double> centroide), 
   	dato il CENTROIDE di una combinazione di contesti, calcola le 3 FRASI con SCORE di SIMILARITA MAGGIORE tra quelle lemmatizzate in FILETESTOITEMS
   	Il metodo, per ogni frase di FileTestoItems lemmatizzata:
	1) verifica la lunghezza della frase (fra 5 e 15)
	2) Fa l'analisi sintattica della frase
   	3) calcola le similarità dei vettori frase e il vettore contesto CENTROIDE
	4) Inserisce lo score di simialità in una TreeMap score -frase, ordinata dal più grande al piu piccolo
	Se lo score è uguale a quello di un'altra frase, aggiunge la frase alla lista delle frasi con quello stesso score
   	5) Inserisce in una lista idFrasi le prime 3 frasi con score maggiore.*/
    public static ArrayList<Integer> scegliFrasiCombinazioneContesti(TreeMap<String,Double> centroide) throws Exception {
        TreeMap<Double, TreeSet<Integer>> valoriSimilarita = new TreeMap<>(Collections.reverseOrder());	// Insieme valori di similarita
        for (int idFrase : FileTestoItems.frasiLemmi.keySet()){//es frase 3 su 7500 del film 1.txt letto prima
        	
        	if(controlloLunghezza(idFrase) == true){
        		if(POSTagger.syntax(FileTestoItems.frasiIntere.get(idFrase))){//CONTROLLO FRASE IMPERSONALE
        			TreeMap<String, Double> vettoreFrase = VettoriFrase.CalcolaVettoreFrase(FileTestoItems.frasiLemmi.get(idFrase));
        			double score = SimilaritaCoseno.calcolaScoreFrase(vettoreFrase, centroide);
        		/////////////////////////////////////////////////////
        			if(valoriSimilarita.get(score) == null){//score nuovo mai trovato, aggiungo idfrase + score
        				TreeSet<Integer> lista = new TreeSet<>();
        				lista.add(idFrase);
        				valoriSimilarita.put(score, lista);//frase --> score
        			}
        			else{
        				valoriSimilarita.get(score).add(idFrase);//aggiungo frase con quello score gia inserito
        			}
        		/////////////////////////////////////////////////////////
        		}//FILTRO FRASI
        	}
        }//FINE FILE 	1.txt  
        /////////////////////////////////////////////////SELEZIONE PRIME 3 FRASI////////////////////////
        ArrayList<Integer> idFrasi = rimuoviFrasi(valoriSimilarita);
        return idFrasi;
    }
    
    //rimozione delle frasi, eccetto le PRIME 3 per una data COMBINAZIONE DI CONTESTI
    public static ArrayList<Integer> rimuoviFrasi(TreeMap<Double, TreeSet<Integer>> valoriSimilarita){
    	ArrayList<Integer> idFrasi = new ArrayList<>();
        int c=0;
        for(double score : valoriSimilarita.keySet()){
        	System.out.printf("    %.5f --->  %d \t" + valoriSimilarita.get(score) + "\n", score,valoriSimilarita.get(score).size());
        	for(int idFrase : valoriSimilarita.get(score)){
        		idFrasi.add(idFrase);
        		c++;
        		if(c==3) 
        			return idFrasi;
        	}
        }
        System.out.println();
        return idFrasi;
    }
    
   
}
