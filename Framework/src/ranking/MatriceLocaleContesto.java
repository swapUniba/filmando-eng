package ranking;

import vettoricontesto.VettoriContesto;
import spiegazioni.FileTestoItems;//per titoli film
import main.Configurazione;

import java.io.*;
import java.util.TreeMap;


/*Questa classe calcola la matrice LOCALE CONTESTO, che assegna, per ogni FILM, il suo SCORE in ogni CONTESTO.
L'attributo è:
-	public static TreeMap<Integer, TreeMap<Integer, Double>> matriceLocaleContesto;
*/

public class MatriceLocaleContesto {
    // id locale -> (id contesto -> score locale per il contesto)
    public static TreeMap<Integer, TreeMap<Integer, Double>> 	matriceLocaleContesto = new TreeMap<>();

    /*Il metodo public static void calcolaMatriceLocaleContesto() calcola la matrice:
	1) per ogni film, leggo tutte le frasi di quel film e ne calcolo i vettori frase, nella classe VettoriFrase
	2) per ogni contesto, itero i vettori frase e calcolo la similarità tra vettore frase e vettore contesto
	3) Sommo gli score di similarità di tutti i vettori frase per quel dato contesto
	4) Divido per il (numero di frasi con similarità diversa da 0/ logaritmo di tale numero), 
	ottenendo uno score di quel film per un dato contesto e li inserisco in contestoScore
	5) Infine inserisco in matriceLocaleContesto l'id del film e contestoScore.*/
    
    public static void calcolaMatriceLocaleContesto() throws Exception{
        for (int idLocale=1; idLocale <= Configurazione.numeroLocali; idLocale++){/////////////PER OGNI FILM	// 	40					288
        	TreeMap<Integer, Double> contestoScore = new TreeMap<>();	//1(HA)	--> 0,45        	
        	TreeMap<Integer, TreeMap<Integer, Double>> ContestoFrase = new TreeMap<>();        
        	System.out.println("/////////////FILM n " + idLocale + "///////////");

        	VettoriFrase.calcolaVettoriFraseFilm(idLocale);//CALCOLO VETTORI FRASE PER UN DATO FILM
        //////////////////PER OGNI CONTESTO/////////////////////
            for (int contesto=1; contesto <= Configurazione.numeroContesti; contesto++){
            	int contatoreFrasi = 0;	//azzero counter a ogni contesto
            	double sommatoriaSimilarita = 0;
            	TreeMap<Integer, Double> FraseSimilar = new TreeMap<>();//PER CREAZIONE DI MATRICE SIMILARITA 	
        /////////////////PER OGNI VETTORE FRASE//////////////////////////////
                for(int idFrase : VettoriFrase.VettoriFrase.keySet()) {
                 //CALCOLO SIMILARITA DEL COSENO FRA VETTORE FRASE i-esimo E VETTORE CONTESTO contesto
                	double score = SimilaritaCoseno.calcolaScoreFrase(
                			VettoriFrase.VettoriFrase.get(idFrase), VettoriContesto.vettoriContesto.get(contesto));
                	
                	sommatoriaSimilarita += score;	//somma similarità frasi
                	if(score!=0)	contatoreFrasi++;		//NON INCREMENTO IL CONTATORE FRASI SE SIM=0
                	FraseSimilar.put(idFrase, score);//PER CREAZIONE DI SIMILARITA          	 	
                }//FINE VETT FRASE
                
                double similarita = sommatoriaSimilarita / (contatoreFrasi/	(Math.log(contatoreFrasi))	)	;	//MIRKO
                if (contatoreFrasi==0)	similarita = 0;//CASO IN CUI FRASI TUTTE CON SIMILARITA = 0
                System.out.printf("//CONTESTO n %d\t sim = %.4f / %d = %.4f\n", contesto, sommatoriaSimilarita, contatoreFrasi, similarita); //AGGIUNTO DA ME
                //System.out.printf("//CONTESTO n %d\t sim = %.4f\n", contesto, similarita);	//AGGIUNTO DA ME
                /////////////////	1(HA)		0,45
                contestoScore.put(contesto, similarita);
                ContestoFrase.put(contesto, FraseSimilar);//AGGIUNTO DA ME, PER CREAZIONE DI SIMILARITA	                						
            
            }//FINE CONTESTO		//210		1	0.45
            matriceLocaleContesto.put(idLocale, contestoScore);
            SimilaritaCoseno.Similarita.put(idLocale, ContestoFrase);//AGGIUNTO DA ME					//1 (HA) 
            
            VettoriFrase.VettoriFrase.clear();//pulisco i vettori frase di quel dato film (prima di andare a nuovo film)
        }// FINE FILM

    }
    
   
//////////////////////////////////////STAMPA////////////////////////////////////////////////////////////////
    //SCRITTURA SU FILE AGGIUNTO DA ME		// scrittura matrice locale contesto.txt    
    public static void stampaMatriceLocaleContestoFile() throws Exception{
        FileTestoItems.LeggiFilm();//PER AGGIUNTA TITOLI
    	PrintWriter out = new PrintWriter(new File(
        		Configurazione.path + "filesFilmando2\\"  + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi +"\\matrice locale contesto.txt"));
        out.printf("%-13s\tHA\tLA\tCO\tBA\tFR\tGM\tBM\n", "FILM");
        for (int idLocale : matriceLocaleContesto.keySet()){
        	out.printf("%-13s\t", FileTestoItems.film.get(idLocale));
            for (int idContesto : matriceLocaleContesto.get(idLocale).keySet()){
                out.printf("%.4f\t", matriceLocaleContesto.get(idLocale).get(idContesto));
            }
            out.println();
        }
        out.flush();
        out.close();
    }
       
    
//////////////////////////////////////SERIALIZZAZIONE////////////////////////////////////////////
    //SERIALIZZAZIONE MATRICE LOCALE CONTESTO
    public static void stampaFileMatriceLocaleContestoDat() throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(
        		Configurazione.path +"filesFilmando2\\" + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\serialized\\locali-contesto.dat")));
        oos.writeObject(matriceLocaleContesto);
        oos.flush();
        oos.close();
    }
    
    //DESERIALIZZAZIONE MATRICE LOCALE CONTESTO
    public static void leggiMatriceLocaleContestoDat() throws Exception {
    	ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(
    			Configurazione.path +"filesFilmando2\\" + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\serialized\\locali-contesto.dat")));
    	matriceLocaleContesto = (TreeMap<Integer, TreeMap<Integer, Double>>) ois.readObject();
    	ois.close();
    }

}
