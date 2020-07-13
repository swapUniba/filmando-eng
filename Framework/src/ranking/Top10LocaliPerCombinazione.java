package ranking;

import spiegazioni.FileTestoItems;//per stampa a video titoli film
import main.Configurazione;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeMap;


/*La classe calcola, per ogni contesto/combinazione di contesti i primi 10 film con score maggiore; 
Possiede gli attributi, ovvero:
-	public static HashSet<HashSet<Integer>> combinazioni che memorizza le possibili combinazioni di contesti
-	public static HashMap<HashSet<Integer>, HashSet<Integer>> contestiItemTop10
che per ogni contesto/combinazione memorizza un insieme di 10 film.
.*/

public class Top10LocaliPerCombinazione {		// contesti -> top 10 item con quel contesto    
	public static HashSet<HashSet<Integer>> combinazioni = new HashSet<>();
	public static HashMap<HashSet<Integer>, HashSet<Integer>> contestiItemTop10 = new HashMap<>();	//AGGIUNTA DA MIRKO
	
    public static int MAXFILM = 10;//DEBUG=3
     
    //CREAZIONE COMBINAZIONI POSSIBILI in base ad alternatività contesti		//1/2	3/4/5		6/7
    public static void generaCombinazioni(){
    	 for (int a = 0; a <= 2; a++) {	//alta attenzione,bassa attenzione
             for (int b = 2; b <= 5; b++) {	//coppia, bambini, amici
                 if (b == 2) b = 0;
                 for (int c = 5; c <= 7; c++) {	//buon umore, cattivo umore
                     if (c == 5) c = 0;
                     HashSet<Integer> ins = new HashSet<>();
                     ins.add(a);
                     ins.add(b);
                     ins.add(c);
                     if (ins.contains(0)) ins.remove(0);
                     combinazioni.add(ins);                     
                     if (c == 0) c = 5;
                 }
                 if (b == 0) b = 2;
             }
         }
    	 //rimuovo combinazione vuota
    	 Iterator<HashSet<Integer>> iter = combinazioni.iterator();
    	 while(iter.hasNext()) {
    	     if((iter.next().size()) == 0)
    	         iter.remove();
    	 }
    	 
    }
    
    
    /*Il metodo public static void generaTop10FilmSingoloContesto():
	1) Per ogni CONTESTO SINGOLO, e per ogni FILM
	 - prendo lo SCORE del FILM in tale CONTESTO dalla MATRICE LOCALE CONTESTO
	 - inserisco la coppia SCORE-FILM per quel contesto nella mappa ordinata ScoreItem.
	3) Si selezionano i primi 10 film
	4) Inserisco in contestiItemTop10:	 il contesto e l'insieme dei film in top10
	5) Si riducono gli score dei film in top10 del contesto esaminato */
    public static void calcolaTop10FilmSingoloContesto() throws Exception{
    	for (int contesto=1; contesto <= Configurazione.numeroContesti; contesto++){
    			System.out.println("///////////////////////CONTESTO///////////////////////////////");
    			System.out.println("////////////////////////"+ contesto + "///////////////////////////////");
                												//SCORE		//IDFILM
    			TreeMap<Double, Integer> ScoreItem = new TreeMap<Double, Integer>(Collections.reverseOrder());												//30 (FILM)			//3 (CONTESTO)	0,455 (SCORE)
    			for (int idLocale : MatriceLocaleContesto.matriceLocaleContesto.keySet()){	//PER OGNI FILM (288)
    				double score = MatriceLocaleContesto.matriceLocaleContesto.get(idLocale).get(contesto);	//1 (film)		0,0376	...	
    				ScoreItem.put(score, idLocale);
    			}
    			HashSet<Integer> top10 = rimuoviFilm(ScoreItem);//rimuofo film non in top10
    			HashSet<Integer> cont = new HashSet<Integer>(Arrays.asList(contesto));
    			contestiItemTop10.put(cont, top10);	//[2]		230	 //INSERISCO CONTESTO + LISTA TOP10 IDFILM
    													//			120
    			System.out.println("Contesto: " + contesto + "\tTop10Score: " + top10);
    			RiduciSingoloScore(cont);//RIDUCO SCORE DI OGNI FILM IN TOP10 APPENA APPARSO IN 1 CONTESTO SINGOLO
    		}
    }
    
    /*Il metodo generaTop10FilmCombContesti(); 
    1) Calcola tutte le possibili COMBINAZIONI DI CONTESTI [[1], [2], [3], [4], [1, 3], [5], [1, 4], [2, 3],...
	2) Seleziona solo le COMBINAZIONI di CONTESTI
	Per ogni COMBINAZIONE, e per ogni FILM
	3) Prendo i contesti singoli della combinazione, e per ogni contesto:
	 - prendo lo SCORE del FILM in tale CONTESTO dalla MATRICE LOCALE CONTESTO
	 - calcola la MEDIA degli SCORE per il FILM nei contesti della combinazione, ottenendo cosi lo SCORE del FILM nella COMBINAZIONI DI CONTESTI.
	 - inserisco la coppia MEDIASCORE-FILM per quella COMBINAZIONE nella mappa ordinata ScoreItem.
	4) Si selezionano i primi 10 film da ScoreItem
	5) Inserisco in contestiItemTop10 la combinazione e l'insieme dei film top10
	6) Si riducono gli score dei film in top10 per i contesti della combinazione appena esaminata 
	*/
    public static void calcolaTop10FilmCombContesti() throws Exception{
    	generaCombinazioni(); //RICAVO COMBINAZ CONTESTI
    	for(int i=2; i<=3; i++){
    		for (HashSet<Integer> combinazione : combinazioni){ //PER OGNI COMBINAZIONE [2,4,7],[2,4],[2,3],[4,7],[]								
    			if(combinazione.size()==i){	//i=2 o 3
    				System.out.println("///////////////////////COMBINAZIONE///////////////////////////////");
    				System.out.println("////////////////////////"+ combinazione+ "///////////////////////////////");
    			/////////////MEDIA DEGLI SCORE FILM -CONTESTO IN MATRICE LOCALE CONTESTO /////////IDFILM	//SCORE
    				TreeMap<Double, Integer> ScoreItem = new TreeMap<Double, Integer>(Collections.reverseOrder());
                												//30 (FILM)			//3 (CONTESTO)	0,455 (SCORE)
    				for (int idLocale : MatriceLocaleContesto.matriceLocaleContesto.keySet()) {	//PER OGNI FILM (288)
    					//System.out.println("/////////////FILM n " + idLocale + "///////////");//AGGIUNTO DA ME
    					double mediaScore = 0;				// media score contesti
    					for (int contesto : combinazione){	//2 in [2,4]
    						//System.out.printf("//CONTESTO n %d\t VALUE = %.4f\n", contesto , MatriceLocaleContesto.matriceLocaleContesto.get(idLocale).get(contesto));	//AGGIUNTO DA ME
    						mediaScore += MatriceLocaleContesto.matriceLocaleContesto.get(idLocale).get(contesto);
    					}
    					mediaScore /= combinazione.size();	//0,16516+0,10179/2 = ....
    					//System.out.printf("\t\t-->MEDIA: %.4f\n\n",mediaScore);
    					ScoreItem.put(mediaScore, idLocale);	//1 (film)		0,0376	...	
    				}					//[3,6]			//2				0,0463
    			///////////////////////////////////////////////////////////////////////////////   
    				HashSet<Integer> top10 = rimuoviFilm(ScoreItem);
    				contestiItemTop10.put(combinazione, top10);	//[2,3]		230	 //INSERISCO COMBINAZIONE + LISTA DEI TOP10 IDFILM
                										//					120 ...
    				System.out.println("Combinazione: " + combinazione + "\tTop10Score: " + top10);
    				RiduciSingoloScore(combinazione);//RIDUCO SCORE DI OGNI FILM IN TOP 10 APPENA APPARSO IN 1 COMBINAZIONE PER QUELLA COPPIA/TRIPLA DI CONTESTI
    			}
    		
    		}//COMB
    	
    	}//FINE CONTESTI N DIMENSIONE
    	
    }
    
    //RIMOZIONE FILM NON TOP 10, estraendo l'INSIEME dei FILM in TOP 10, per un dato CONTESTO
    public static HashSet<Integer> rimuoviFilm(TreeMap<Double,Integer> MediaScoreItem){
    	HashSet<Integer> top10 = new HashSet<>();			// CREO TOP 10 FILM PER DATO CONTESTO
    	int c=0;
    	for(double score : MediaScoreItem.keySet()){
            top10.add(MediaScoreItem.get(score));
            System.out.println("FILM: " + MediaScoreItem.get(score) + "\t Score: " + score);
            c++;
            if(c==MAXFILM) 	return top10;
        }
    	return top10;
    }
    
    
    
    //Score riduzione:	2/ln4= 1,442		2/ln5= 1,242			2/ln6= 1,116
    // RIDUCE SCORE SINGOLA TOP 10, in funzione della CARDINALITA CONTESTO/COMBINAZIONE
    public static void RiduciSingoloScore(HashSet<Integer> combinazione){
    		int index= combinazione.size();//1,2 o 3		//TOP10(2) --> [288, 48, 129, 98, 51, 228, 244, 72, 153, 93]
    		System.out.println("REDUCE---------->>//////////////CONTESTO " + combinazione + "///////////////////");
    		for(int idFilmTop10: contestiItemTop10.get(combinazione)){				 // id locale -> (id contesto -> punteggio contesto)
    			for(int contesto: combinazione) {//[2,4]-->2
    				System.out.printf("Film: %d\t Cont: %d \t OLDscore: %.4f\t\t ",idFilmTop10, contesto, MatriceLocaleContesto.matriceLocaleContesto.get(idFilmTop10).get(contesto));
    				MatriceLocaleContesto.matriceLocaleContesto.get(idFilmTop10).put(contesto, MatriceLocaleContesto.matriceLocaleContesto.get(idFilmTop10).get(contesto)/	(2/Math.log(index+3))	);
    				System.out.printf("NEWscore: %.4f\n", MatriceLocaleContesto.matriceLocaleContesto.get(idFilmTop10).get(contesto));
    			}
    		}
	}

    
//////////////////////////////////////////////////////////////////STAMPA//////////////////////////////////////////////////    
    //AGGIUNTO DA ME, STAMPA A VIDEO TOP 10 IN ORDINE, PRIMA CONTESTI SIMBOLI POI COMBINAZIONI
    //[1,3,6]		[84,56,77,102,288,240...]
    public static void stampaContestiItemTop10() throws FileNotFoundException{
    	FileTestoItems.LeggiFilm();
    	System.out.println("COMB\t TOP10FILM");
        for(HashSet<Integer> comb : contestiItemTop10.keySet()){
        	if(comb.size() == 1) {
        		System.out.printf(comb + "\t");
        		for(int combSing : contestiItemTop10.get(comb))
        			System.out.printf(FileTestoItems.film.get(combSing) + " (" + combSing + ")"+ "\t");
        		System.out.println();
        	}
        }
        for(HashSet<Integer> comb : contestiItemTop10.keySet()){
        	if(comb.size() == 2) {
        		System.out.printf(comb + "\t");
        		for(int combSing : contestiItemTop10.get(comb))
        			System.out.printf(FileTestoItems.film.get(combSing) + " (" + combSing + ")"+  "\t");
        		System.out.println();
        	}
        }
        for(HashSet<Integer> comb : contestiItemTop10.keySet()){
        	if(comb.size() == 3) {
        		System.out.printf(comb + "\t");
        		for(int combSing : contestiItemTop10.get(comb))
        			System.out.printf(FileTestoItems.film.get(combSing) + " (" + combSing + ")"+  "\t");
        		System.out.println();
        	}
        } 
    }
    
    
    //AGGIUNTO DA ME, SCRITTURA TOP 10 IN ORDINE SU FILE 		// scrittura contesti item.txt    
    public static void stampaContestiItemTop10File() throws Exception {
    	FileTestoItems.LeggiFilm();
    	PrintWriter out = new PrintWriter(new File(
        		Configurazione.path + "filesFilmando2\\" + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\top10combinazioni-items.txt"));
        out.println("COMB\t TOP10FILM");
        for(HashSet<Integer> comb : contestiItemTop10.keySet()){
        	if(comb.size() == 1) {
        		out.printf(comb + "\t");
        		for(int combSing : contestiItemTop10.get(comb))
        			out.printf(FileTestoItems.film.get(combSing) + " (" + combSing + ")"+  "\t");
        		out.println();
        	}
        }
        for(HashSet<Integer> comb : contestiItemTop10.keySet()){
        	if(comb.size() == 2) {
        		out.printf(comb + "\t");
        		for(int combSing : contestiItemTop10.get(comb))
        			out.printf(FileTestoItems.film.get(combSing) + " (" + combSing + ")"+  "\t");
        		out.println();
        	}
        }
        for(HashSet<Integer> comb : contestiItemTop10.keySet()){
        	if(comb.size() == 3) {
        		out.printf(comb + "\t");
        		for(int combSing : contestiItemTop10.get(comb))
        			out.printf(FileTestoItems.film.get(combSing) + " (" + combSing + ")"+  "\t");
        		out.println();
        	}
        } 
        out.flush();
        out.close();
    }
    


////////////////////////////////////////////SERIALIZZAZIONE//////////////////////////////////////////////////////////
    //////LETTURA .DAT   
    public static void leggiTop10File() throws Exception{
    	ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(
    			Configurazione.path + "filesFilmando2/"  + Configurazione.tecnica + "/" + Configurazione.TipoLemmi + "/serialized/top10combinazioni-items.dat")));	//mirko: top5combinazioni-items-bari
    	contestiItemTop10 = (HashMap<HashSet<Integer>, HashSet<Integer>>)ois.readObject();
    	ois.close();
    }

    //SCRITTURA .DAT
    public static void scriviTop10File() throws Exception{
    	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(
    			Configurazione.path + "filesFilmando2/"  + Configurazione.tecnica + "/" + Configurazione.TipoLemmi + "/serialized/top10combinazioni-items.dat")));
    	oos.writeObject(contestiItemTop10);	//MIRKO: 	oos.writeObject(contestiItemBari)
    	oos.flush();
    	oos.close();      
    }

}
