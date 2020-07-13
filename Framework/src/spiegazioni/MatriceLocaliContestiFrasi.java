package spiegazioni;

import ranking.MatriceLocaleContesto;
import ranking.Top10LocaliPerCombinazione;
import main.Configurazione;
//import ranking.SimilaritaCoseno;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;


/*La classe MatriceLocaliContestiFrasi calcola la matrice LOCALI CONTESTI FRASI che:
  per ogni FILM e per ogni CONTESTO calcola la FRASE con SCORE MAGGIORE. 
L'attributo è:
-	public static HashMap<Integer, HashMap<Integer, Integer>> localiContestiFrasi.*/

public class MatriceLocaliContestiFrasi{
	//(frasi singole)	 FILM N 23 		CONTESTO 5 		FRASE N 5646
    public static HashMap<Integer, HashMap<Integer, Integer>> localiContestiFrasi = new HashMap<>();

  //////////////////////////////CALCOLO MATRICE///////////////////////////////////////////
    /*  Il metodo calcolaMatriceLocaliContestiFrasi():
    1) Per ogni FILM viene letta ogni FRASE INTERA e LEMMAMTIZZATA
    2) per ogni CONTESTO, si calcola la FRASE con SCORE MAGGIORE per il CONTESTO selezionato col metodo scegliFraseSingoloContesto;
    3) Si salva la coppia contesto- frase con score maggiore
    4) si inserisce nella MATRICE LOCALI CONTESTI FRASI l'id FILM + la mappa contestoFrase (la lista dei CONTESTI + FRASI individuate)
    */
    public static void calcolaMatriceLocaliContestiFrasi() throws Exception{
        for (int locale : MatriceLocaleContesto.matriceLocaleContesto.keySet()){ //1,2,3..............288
        	System.out.println("/////////////////////FILM n " + locale + "/////////////////");
            HashMap<Integer, Integer> contestoFrase = new HashMap<>();	// Mappa contesto (ES 3) -> frase (es 23)
            FileTestoItems.leggiFrasiLocale(locale); //Lettura frasi film 1.txt (lemma + intere)
        /////////////////////////PER OGNI CONTESTO////////////////////////////////
            for (int contesto=1; contesto <= Configurazione.numeroContesti; contesto++){
            	////RESITUZIONE FRASE CON SCORE MAGGIORE PER FILM DATO IN CONTESTO
                int idFrase = SceltaFrasiItemFiles.scegliFraseSingoloContesto(locale,contesto);	
                					//3		//23
                contestoFrase.put(contesto, idFrase);		// Inserimento nella mappa CONTESTO -> IDFRASE  x dato film
                System.out.println("//CONTESTO n " + contesto + "\t frase: " + idFrase);
            }//FINE CONTESTO
            //////////////////////////////PULIZIA/////////////////////////////////////////////////
            FileTestoItems.frasiLemmi.clear();	//ELIMINO FRASI INTERE LETTE DEL FILM 1
            FileTestoItems.frasiIntere.clear(); //ELIMINO LEMMI FRASI LETTI DEL FILM 1
            // Inserimento in					 matrice LOCALE -> CONTESTO -> IDFRASE
            localiContestiFrasi.put(locale, contestoFrase);	//2		//3		//23
        }
        ranking.SimilaritaCoseno.Similarita.clear();//PULIZIA
    }

    
//////////////////////////////////////STAMPE////////////////////////////////////////////////////////////////
  //SCRITTURA SU FILE LOCALI CONTESTI FRASE
	public static void stampaMatriceLocaliContestiFrasiFile() throws Exception{
    	PrintWriter out = new PrintWriter(new File(
    			Configurazione.path + "filesFilmando2\\"  + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\locale-contesto-frase.txt"));
        out.print("FILM\tHA\tLA\tCO\tBA\tFR\tGM\tBM\n");
        for (int locale : localiContestiFrasi.keySet()){
        	out.printf("%-6d\t", locale);
        	for (int idContesto : localiContestiFrasi.get(locale).keySet()){
        		out.printf("%d\t", localiContestiFrasi.get(locale).get(idContesto));
            }
            out.println();
        }
        out.flush();
        out.close();
    }
	
	///////////////////////////////////////////////////////
	//STAMPA A VIDEO PER OGNI CONTESTO/COMBINAZIONE E PER OGNI FILM IN TOP10, LE FRASI SINGOLE x spiegazioni
	public static void scriviCombinazioni(){
		for(HashSet<Integer> combinazione:  Top10LocaliPerCombinazione.combinazioni) {
			String comb="";
			for(int k : combinazione){	//[2,3]
				comb+= Integer.toString(k);
				comb+=",";//2,3
			}
			for(int idFilm:  Top10LocaliPerCombinazione.contestiItemTop10.get(combinazione)) {//10 FILM
				String frasi = "";
				for(int contesto : combinazione){
					frasi += Integer.toString(localiContestiFrasi.get(idFilm).get(contesto));
					frasi +=",";
				}
				System.out.println(comb.substring(0,comb.length()-1) + "\t" + idFilm + "\t" + frasi.substring(0,frasi.length()-1));
			}
		}
	}
	
	
	
	///////////////////////////////////////////////////////
	//SCRIVE SU FILE PER OGNI CONTESTO/COMBINAZIONE E PER OGNI FILM IN TOP10, LE FRASI SINGOLE x spiegazioni
	public static void scriviCombinazioniFile() throws Exception {   	
		PrintWriter out = new PrintWriter(new File(
			Configurazione.path + "filesFilmando2\\"  + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\contesti-item-frasi-singole.txt"));
	
		for(HashSet<Integer> combinazione:  Top10LocaliPerCombinazione.combinazioni) {
			String comb="";
			for(int k : combinazione){	//[2,3]
				comb+= Integer.toString(k);
				comb+=",";//2,3
			}
			for(int idFilm:  Top10LocaliPerCombinazione.contestiItemTop10.get(combinazione)) {//10 FILM
				String frasi = "";
				for(int contesto : combinazione){
					frasi += Integer.toString(localiContestiFrasi.get(idFilm).get(contesto));
					frasi +=",";
				}
				out.println(comb.substring(0,comb.length()-1) + "\t" + idFilm + "\t" + frasi.substring(0,frasi.length()-1));
			}
		}
		out.flush();
		out.close();
	}
	

///////////////////////////////////////// SERIALIZZAZIONE //////////////////////////////////////////////////////
    public static void scriviMatrice() throws Exception {
    	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(
    		Configurazione.path + "filesFilmando2/"  + Configurazione.tecnica + "/" + Configurazione.TipoLemmi + "/serialized/locali-contesto-frase.dat")));
    	oos.writeObject(localiContestiFrasi);
    	oos.flush();
    	oos.close();
    }

    public static void leggiMatrice() throws Exception {
    	ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(
    		Configurazione.path + "filesFilmando2/"  + Configurazione.tecnica + "/" + Configurazione.TipoLemmi + "/serialized/locali-contesto-frase.dat")));
    	localiContestiFrasi = (HashMap<Integer, HashMap<Integer, Integer>>) ois.readObject();
    	ois.close();
    }

}
