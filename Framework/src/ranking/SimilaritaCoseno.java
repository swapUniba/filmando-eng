package ranking;

import main.Configurazione;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.TreeMap;

/* Questa classe calcola la SIMILARITA DEL COSENO tra 2 vettori (vettore frase e vettore contesto) (entrambi hanno la stessa dimensione), 
Inoltre memorizza i calcoli delle similarità per ogni frase di ogni film nella martrice Similarita.
L'attributo è:
- TreeMap<Integer, TreeMap<Integer, TreeMap<Integer, Double>>> Similarita
che per ogni film memorizza una mappa che per ogni contesto associa la coppia frase-score. 
*/
public class SimilaritaCoseno{
	public static TreeMap<Integer, TreeMap<Integer, TreeMap<Integer, Double>>> Similarita = new TreeMap<>();

    //Il metodo calcolaScoreFrase(TreeMap<String, Double> frase, TreeMap<String, Double> contesto)
    //calcola la similarità tra vettore frase e il vettore contesto (anche centroide)
    public static double calcolaScoreFrase(TreeMap<String, Double> frase, TreeMap<String, Double> contesto){
        double similarita = 0;	//Score(fk,i,C) = sim(fk,i,C) = Vfk,i * C / ||Vfk,i||*||C||
        double numeratore = 0, denA = 0, denB = 0, denominatore = 0;
        
        for (String lemma : frase.keySet()){	 //bank, account....	//PROD SCALARE VETT FRASE * VETT CONTESTO
            double a = frase.get(lemma);		//0,040
            double b = contesto.get(lemma);		//0,038	HA MOLTI 0
            numeratore += a*b;			//somma prodotti
            					 //+0,040**2
            denA += Math.pow(frase.get(lemma), 2);		//NORMA VETT FRASE
            denB += Math.pow(contesto.get(lemma), 2);	//NORMA VETT CONTESTO
        }
        denominatore = Math.sqrt(denA) * Math.sqrt(denB);
        if (denominatore == 0){
            similarita = 0;
        } else {
            similarita = numeratore / denominatore;
            //System.out.printf("%.4f / %.3f = %.4f\n", numeratore,denominatore,similarita);//AGGIUNTO DA ME
        }
        return similarita;
    }


/////////////////////////////////////////////STAMPE///////////////////////////////////////
    //STAMPA A VIDEO SIMILARITA 		//idfilm 	idcont 	idfrase-sim
    public static void stampaSimilarita() throws Exception{
    	System.out.println("FILM\tFRASE\tCONT\tSIMILAR");
    	for (int idFilm : Similarita.keySet()){
    		for (int idContesto : Similarita.get(idFilm).keySet()){
    			for (int idFrase : Similarita.get(idFilm).get(idContesto).keySet()){	
    				System.out.printf("%d\t%d\t%d\t%.3f\n", idFilm, idFrase, idContesto, Similarita.get(idFilm).get(idContesto).get(idFrase));
    			}
    			System.out.println();
    		}
    		System.out.println();
    	}
    }
	
    //SCRIVE SU FILE SIMILARITA con riga = contesto colonna = frase,	per ogni film
    //idfilm idfrase idcont sim
    public static void stampaSimilaritaFile() throws Exception{
    	for (int idFilm : Similarita.keySet()){
    		PrintWriter out = new PrintWriter(new File(
    				Configurazione.path + "filesFilmando2\\"  + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\strutture\\similarita frase contesto\\"+ idFilm  + ".txt"));
    		out.printf("%-6s\t", "FR.");
    		for (int id : Similarita.get(idFilm).get(1).keySet())
    			out.printf("%d\t", id);
    		out.println();

    		for (int idContesto : Similarita.get(idFilm).keySet()){
    			for (int idFrase : Similarita.get(idFilm).get(idContesto).keySet()){
    				if(idFrase == 1) {
    					if (idContesto ==1)	out.printf("%-6s\t%.3f\t", "HA", Similarita.get(idFilm).get(idContesto).get(idFrase));
    					if (idContesto ==2)	out.printf("%-6s\t%.3f\t", "LA", Similarita.get(idFilm).get(idContesto).get(idFrase));
    					if (idContesto ==3)	out.printf("%-6s\t%.3f\t", "CO", Similarita.get(idFilm).get(idContesto).get(idFrase));
    					if (idContesto ==4)	out.printf("%-6s\t%.3f\t", "BA", Similarita.get(idFilm).get(idContesto).get(idFrase));
    					if (idContesto ==5)	out.printf("%-6s\t%.3f\t", "FR", Similarita.get(idFilm).get(idContesto).get(idFrase));
    					if (idContesto ==6)	out.printf("%-6s\t%.3f\t", "GM", Similarita.get(idFilm).get(idContesto).get(idFrase));
    					if (idContesto ==7)	out.printf("%-6s\t%.3f\t", "BM", Similarita.get(idFilm).get(idContesto).get(idFrase));
    					//out.printf("%-13d\t%.3f\t", idContesto, Similarita.get(idFilm).get(idContesto).get(idFrase));
    				}
    				else 
    					out.printf("%.3f\t", Similarita.get(idFilm).get(idContesto).get(idFrase));
    			}
    			out.println();
    		}
    		out.flush();
    		out.close();
    	}

    }
    
 
////////////////////////////SERIALIZZAZIONE MATRICE SIMILARITA/////////////////////////////////////////
    public static void stampaFileSimilaritaDat() throws Exception {
    	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(
    			Configurazione.path +"filesFilmando2\\"  + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\serialized\\similarita.dat")));
    	oos.writeObject(Similarita);
    	oos.flush();
    	oos.close();
    }

    //DESERIALIZZAZIONE MATRICE SIMILARITA
    public static void leggiSimilaritaDat() throws Exception {
    	ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(
    			Configurazione.path +"filesFilmando2\\"  + Configurazione.tecnica + "\\" + Configurazione.TipoLemmi + "\\serialized\\similarita.dat")));
    	Similarita = (TreeMap<Integer, TreeMap<Integer, TreeMap<Integer, Double>>>) ois.readObject();
    	ois.close();
    }
    
    
}
