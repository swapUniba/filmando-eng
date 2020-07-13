package SentimentAnalysis;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class SentimentAnalysis {
	public static StanfordCoreNLP pipeline = Pipeline.getPipeline();
	
    public static void main(String[] args) throws IOException {
    	double inizio = System.currentTimeMillis();
        Scanner dataset = new Scanner(new File(
        		"C:\\Users\\Mirco\\Java-workspace-MAP\\PreProcessing\\src\\SentimentAnalysis\\dataset_PT1 1-37500.txt"));
        FileWriter positive = new FileWriter(
        		"C:\\Users\\Mirco\\Java-workspace-MAP\\PreProcessing\\src\\SentimentAnalysis\\dataset_positivo.txt");
        //FileWriter verypositive = new FileWriter("C:\\Users\\Mirco\\Java-workspace-MAP\\PreProcessing\\src\\SentimentAnalysis\\dataset_verypositive.txt");

		int counterRecensioni=0,	counterFrasi=0;
		String riga = "";
		String idFilm = "", testoRece= "";
		//I:8493	When people ask what my favorite movie is, I almost hate to say it's Pulp Fiction. The reason I hate to say it is because peop
        while (dataset.hasNextLine()){
        	riga = dataset.nextLine();
        	counterRecensioni++;	//CONTA N RECENSIONI		37500
        	System.out.println(counterRecensioni);
        	
        	StringTokenizer st = new StringTokenizer(riga, "\t");
        	idFilm = st.nextToken();		//I:8493
        	testoRece = st.nextToken();	//When people ask what my favorite movie is, I almost hate to say it's Pulp Fiction....
			//OTTENIMENTO FRASI RECENSIONI		
			CoreDocument document = new CoreDocument(testoRece);
	        pipeline.annotate(document);
	        List<CoreSentence> frasiRecensione = document.sentences();	//divido in frasi
	        		
	        for(CoreSentence f : frasiRecensione) {	//per ogni FRASE								//SCRIVO SOLO FRASI CON LUNGHEZZA SUPERIORE A 20
	        	if ((f.sentiment().equals("Positive") || f.sentiment().equals("Very positive")) 
	        			&& (f.text().length()> 18)){	//se sentiment positivo (si puo aggiungere lunghezza min 20 caratteri)
	        		counterFrasi++;
		        	//System.out.println(idFilm +"; " + counterFrasi+"; " + sentence + "; " + sentiment);
		        	positive.write(idFilm + "\t" + counterFrasi +"\t" + f + "\n");
		        	if(counterFrasi % 100 == 0)
		        		positive.flush();
	        	}
	        	//if (sentiment.equals("Very positive")){
		        //	verypositive.write(FilmID+"\t" + SentenceCounter2 +"\t" + sentence + "\n");
	        	//}
	        }//FINE SINGOLA RECENSIONE
	        
        }//FINE DATASET
        double durata = (System.currentTimeMillis()-inizio)/60000;
        System.out.println("Tempo esecuzione:  " + durata + " minuti.");
        dataset.close();
        positive.close();
        //verypositive.close(); 
        
    }
}
