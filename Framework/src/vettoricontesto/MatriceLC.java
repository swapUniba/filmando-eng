package vettoricontesto;

import main.Configurazione;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeMap;

/*3) La classe MatriceLC calcola la MATRICE LEMMA CONTESTO. Possiede come attributo principale:
	TreeMap<String, TreeMap<Integer, Double>> matriceLemmaContesto
	che associa ad ogni lemma una mappa di contesti e gli score associati ad essi
*/
public class MatriceLC {
    public static TreeMap<String, TreeMap<Integer, Double>> matriceLemmaContesto = new TreeMap<>();

  //Il metodo calcolaMatriceLemmaContesto() calcola la matrice:
  //1) Si effettua il prodotto tra le matrici 2) lemma-frase LF e 1) frase-contesto FC.
    public static void calcolaMatriceLemmaContesto(){
    							//2 matrice: //war, 1, 4,67			colonna verticale LF
        for (String lemma : MatriceLF.matriceLemmaFrase.keySet()){	//[20th, abandon, able, absent, absolute, ...., youth]
            TreeMap<Integer, Double> contestoPunteggio = new TreeMap<>();
            for (int idContesto=1; idContesto <= Configurazione.numeroContesti ; idContesto++) {
                double somma = 0;			//1 matrice: 					colonna verticale di FC
                for (int idFrase : MatriceFC.matriceFraseContesto.keySet()) {//[1, 2, 3, 4, 5, 6, 7, 8, 9,...608]
                        double a = MatriceLF.matriceLemmaFrase.get(lemma).get(idFrase);			//VALORE TF-IDF(l,f) di absent x frase 3
                        double b = MatriceFC.matriceFraseContesto.get(idFrase).get(idContesto); //se ho annotato la frase 3 per contesto LA
                        somma += a*b;
                }
                contestoPunteggio.put(idContesto, somma);
            }
            matriceLemmaContesto.put(lemma, contestoPunteggio);
        }
        //////////////////////PULIZIA DELLE DUE MATRICI USATE PER IL PRODOTTO
        MatriceFC.matriceFraseContesto.clear();
        MatriceLF.matriceLemmaFrase.clear();
        
    }

    
    //////////////////////////////////////////////////////////////////////////////////
    //Tale metodo azzera lo score delle stopwords	
    public static void annullamentoScoreStopWords() throws Exception {
    	Scanner stopLemmi = new Scanner(new File( 
    			Configurazione.path + "filesFilmando2\\"  + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi +"\\stoplemmi.txt"));		//ORIGINALE
        while(stopLemmi.hasNextLine()){
            String stopLemma = stopLemmi.nextLine();
            if(matriceLemmaContesto.get(stopLemma)!=null) {	//IF AGGIUNTO DA MIRKO	//film, 7
                for (int contesto : matriceLemmaContesto.get(stopLemma).keySet()) {
                    matriceLemmaContesto.get(stopLemma).put(contesto, 0.0);
                }
            }//FINE IF	
        }
        stopLemmi.close();
    }
    
    
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////STAMPE///////////////////////////////////////////////////////////
    //STAMPA MATRICE LEMMA CONTESTO
    public static void stampaMatriceLemmaContesto(){
        System.out.printf("%-20s\tHA\tLA\tCO\tBA\tFR\tGM\tBM\n", "L");
        for (String lemma : matriceLemmaContesto.keySet()){
            System.out.printf("%-20s\t", lemma);
            for (int idContesto : matriceLemmaContesto.get(lemma).keySet()){
                System.out.printf("%.3f\t", matriceLemmaContesto.get(lemma).get(idContesto));
            }
            System.out.println();
        }

    }
  
  //STAMPA MATRICE LEMMA CONTESTO SU FILE
    public static void stampaMatriceLemmaContestoFile() throws Exception {
        PrintWriter out = new PrintWriter(new File(
        		Configurazione.path + "filesFilmando2\\"  + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi +"\\matrice lemma contesto.txt"));
        out.printf("%-13s\tHA\tLA\tCO\tBA\tFR\tGM\tBM\n", "L");
        for (String lemma : matriceLemmaContesto.keySet()){
            out.printf("%-13s\t", lemma);
            for (int idContesto : matriceLemmaContesto.get(lemma).keySet()){
                out.printf("%.3f\t", matriceLemmaContesto.get(lemma).get(idContesto));
            }
            out.println();
        }
        out.flush();
        out.close();
    }
    
//////////////////////////////////////////SERIALIZZAZIONE////////////////////////////////////////////////////
    //LEGGE da .dat la matrice
    public static void LeggiMatriceLemmaContestoDAT() throws Exception{
    	ObjectInputStream oin = new ObjectInputStream(new FileInputStream(new File(
    			Configurazione.path + "filesFilmando2\\"  + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\serialized\\lemma-contesto.dat")));
    	matriceLemmaContesto = (TreeMap<String, TreeMap<Integer, Double>>) oin.readObject();
    	oin.close();
    }
    
    //SCRIVE in .dat matrice	
    public static void ScriviMatriceLemmaContestoDAT() throws Exception{	
    	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(
    			Configurazione.path + "filesFilmando2\\"  + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\serialized\\lemma-contesto.dat")));
        oos.writeObject(matriceLemmaContesto);
        oos.flush();
        oos.close();
    }

}
