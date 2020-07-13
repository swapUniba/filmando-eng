package vettoricontesto;

import main.Configurazione;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;


/* 1) Questa classe calcola la MATRICE FRASE CONTESTO. Possiede come attributo principale il seguente:
	TreeMap<Integer, TreeMap<Integer, Double>> matriceFraseContesto
	che assegna, all’ID della frase annotata (es 239)
	una mappa che a sua volta assegna ad ogni contesto (1-7) i valori 1 o 0, 
	se la frase è stata annotata o meno per quel contesto.
*/
public class MatriceFC {
    public static TreeMap<Integer, TreeMap<Integer, Double>> matriceFraseContesto = new TreeMap<>();
    public static TreeMap<Integer, ArrayList<Integer>> idFraseidContesti = new TreeMap<>();
    
    
    //Il metodo leggiIDFraseIDContesti serve a popolare la struttura idFraseidContesti 
    //che per ogni frase associa i contesti per cui essa è stata annotata
    public static void leggiIDFraseIDContesti() throws FileNotFoundException{
    	Scanner fileFrasiContesti = new Scanner(
        		new File(Configurazione.path + "filesFilmando2\\" + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\idFraseLemmi.txt"));        		
        while (fileFrasiContesti.hasNextLine()){		//7;1,7			//PER OGNI FRASE (es frase 7)
        	String riga = fileFrasiContesti.nextLine();            
            StringTokenizer st = new StringTokenizer(riga, ";");
            int idFrase = Integer.parseInt(st.nextToken());					//7
            st.nextToken();												//testo
            StringTokenizer st2 = new StringTokenizer(st.nextToken(), ",");	//1,7
                            
            ArrayList<Integer> idContesti = new ArrayList<>();          
            while (st2.hasMoreTokens()) {			//1,7
                int idContesto = Integer.parseInt(st2.nextToken());	//1	
                idContesti.add(idContesto);
            }
            idFraseidContesti.put(idFrase, idContesti);	//AGGIUNGO LA COPPIA idFRASE ID CONTESTI ALLA MAPPA
    	 }  
        fileFrasiContesti.close();
    }
    
    /*Il metodo calcolaMatriceFraseContesto
    legge il file delle annotazioni e riempie la matriceFraseContesto:
    - presa una frase, per ogni contesto mette 1 se è stata annotata per quel contesto, 0 altrimenti
    */
    //SCRITTURA MATRICE FC  DEL FILE IDFRASE CONTESTI.txt
    public static void calcolaMatriceFraseContesto() throws FileNotFoundException {
    	leggiIDFraseIDContesti();			//7;1,7	
    	for(int idFrase: idFraseidContesti.keySet()){	//7;1,7			//PER OGNI FRASE
            TreeMap<Integer, Double> contestoPunteggio = new TreeMap<>();   	// mappa contesto -> 1/0  
             for(int idContesto: idFraseidContesti.get(idFrase)) {//1,7	
                    contestoPunteggio.put(idContesto, 1.0);	//metto punteggio a 1 per ogni contesto associato all'IDFrase	
             }
             for (int i=1; i<= Configurazione.numeroContesti; i++) {	//riempio i restanti contesti non presenti a 0
            	 if (!contestoPunteggio.containsKey(i)) {
            		 contestoPunteggio.put(i, 0.0);
                 }						//7		//1		//1.0	
             }							//7		//2		//0.0
             matriceFraseContesto.put(idFrase, contestoPunteggio);
        }
    	//////////////////////////////////PULIZIA////////////////////////////////
    	idFraseidContesti.clear();
    }

////////////////////////////////////////////////////STAMPE////////////////////////////////////////////////
    //STAMPA A VIDEO MATRICEFC
    public static void stampaMatriceFraseContesto() {
        for (int idFrase : matriceFraseContesto.keySet()){
            System.out.printf("%-6d -> {", idFrase);
            for (int idContesto : matriceFraseContesto.get(idFrase).keySet()){
                System.out.print(idContesto + " -> " + matriceFraseContesto.get(idFrase).get(idContesto));
                if (idContesto != Configurazione.numeroContesti) {
                    System.out.print(",\t");
                }
            }
            System.out.println(" }");
        }
    }
    
    //SCRITTURA SU FILE matrice FRASE CONTESTO
    public static void stampaMatriceFraseContestoFile() throws Exception {
        PrintWriter out = new PrintWriter(new File(
        		Configurazione.path + "filesFilmando2\\"  + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\strutture\\matrice frase contesto.txt"));
        out.print("F\tHA\tLA\tCO\tBA\tFR\tGM\tBM\n");
        for (int idFrase : matriceFraseContesto.keySet()){
            out.printf("%-6d\t", idFrase);
            for (int idContesto : matriceFraseContesto.get(idFrase).keySet()){
                out.printf("%.0f\t", matriceFraseContesto.get(idFrase).get(idContesto));
            }
            out.println();
        }
        out.flush();
        out.close();
    }
    
}
