package POSAnalysisAnnotate;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class POSAnalysisAnnotate {
	public static StanfordCoreNLP pipeline = Pipeline.getPipeline();
	public static HashSet<String> POSTypes = new HashSet<>(
			Arrays.asList("JJ","JJR", "JJS",				//aggettivi, comparativi e superlativi
							"NN", "NNS",					//nomi singolari, plurali
							"RB","RBR","RBS",				//avverbi, comparativi e superlativi
							"VB","VBD","VBG","VBN","VBP","VBZ")); 	//verbo base, passato ,gerundio e participio, non 3rdps, 3rdps													
	//public static HashSet<String> stopLemmi = new HashSet<>();
	
	public static ArrayList<String> syntax(String testoFrase) {
		ArrayList<String> lemmi = new ArrayList<String>();	//PREPARO LISTA LEMMI
		
		CoreDocument document = new CoreDocument(testoFrase);	  //CREATE A DOCUMENT OBJECT
		pipeline.annotate(document);						//ANNOTATE THE DOCUMENT
		
		List<CoreLabel> text = document.tokens();//TOKENS OF THE DOCUMENT
    	for(CoreLabel token : text) {
	        String POS = token.get(PartOfSpeechAnnotation.class);
	        
	        for(String POSType: POSTypes) 
	        	if(POS.equals(POSType)) 
	        		lemmi.add(token.get(LemmaAnnotation.class).toLowerCase());	
	        }
    	return lemmi;
    }
	
    public static void main(String[] args) throws Exception {
    	Scanner fileAnnotate = new Scanner(new File(
        				"C:\\Users\\Mirco\\Java-workspace-MAP\\PreProcessing\\src\\POSAnalysisAnnotate\\annotate.txt"));        
        
        FileWriter outUni = new FileWriter("C:\\Users\\Mirco\\Java-workspace-MAP\\PreProcessing\\src\\POSAnalysisAnnotate\\idFraseLemmiUni.txt");
        FileWriter outBi = new FileWriter("C:\\Users\\Mirco\\Java-workspace-MAP\\PreProcessing\\src\\POSAnalysisAnnotate\\idFraseLemmiBi.txt");
        FileWriter outUniBi = new FileWriter("C:\\Users\\Mirco\\Java-workspace-MAP\\PreProcessing\\src\\POSAnalysisAnnotate\\idFraseLemmiUniBi.txt");
        //stopLemmi();//LEGGO STOPLEMMI 
        while (fileAnnotate.hasNextLine()) {	//2;Stellar cast and therefore 
        	String riga = fileAnnotate.nextLine();
        	StringTokenizer st = new StringTokenizer(riga, ";");
        	int id = Integer.parseInt(st.nextToken());	//2
        	String testoFrase = st.nextToken();					//Stellar cast and therefore 
        	System.out.println(testoFrase);
        	ArrayList<String> lemmiFrase = syntax(testoFrase);
			int n=lemmiFrase.size();
			
			String rigaoutUni="";		String rigaoutBi="";	String rigaoutUniBi="";
			        
			if (n>=2){	//considero solo le frasi con almeno 2 parole
			//costruzione riga del file di output di unigrammi
				rigaoutUni = id + ";";
			    rigaoutBi = "";		
			    boolean primo= true;
			    for(int j=0; j<n-1; j++){	//scorrimento lemmi frase
			    	if(primo==false) {   	//se non è il primo lemma aggiungo la virgola         
			    		rigaoutUni += ", ";
			            rigaoutBi += ", ";
			        }
			        rigaoutUni += lemmiFrase.get(j);		//aggiunta lemma alla frase
			        rigaoutBi += lemmiFrase.get(j) + " " + lemmiFrase.get(j+1);
			        primo = false;
			    }
			    rigaoutUni = rigaoutUni + ", " + lemmiFrase.get(n-1);
			    // riga unigrammi -> pronta
			    // riga bigrammi -> va aggiunta intestazione
			    // riga unigrammi e bigrammi -> aggiungere alla riga unigrammi la riga bigrammi
			    rigaoutUniBi = rigaoutUni + ", " + rigaoutBi;
			    rigaoutBi = id + ";" + rigaoutBi;    
			    //scrittura su file    
			    outUni.write(rigaoutUni + "\n");
			    outBi.write(rigaoutBi + "\n");
			    outUniBi.write(rigaoutUniBi + "\n");
			    //stampa a schermo
			    System.out.println("Uni:" + rigaoutUni);
			    System.out.println("Bi:" + rigaoutBi);
			    System.out.println("UniBi:" + rigaoutUniBi+ "\n");
			}//FINE IF
		
		}//FINE WHILE
        
        outUni.close();
        outBi.close();
        outUniBi.close();
        fileAnnotate.close();
    }  
    
    

	//////////////////////////////////////
	/*
	public static void stopLemmi() throws Exception {
		Scanner stopFile = new Scanner(new File(
				"C:\\Users\\Mirco\\Java-workspace-MAP\\3) POSAnalysisAnnotate\\src\\Test\\stoplemmi.txt"));		//ORIGINALE
		while(stopFile.hasNextLine()){
			stopLemmi.add(stopFile.nextLine());
		}
		stopFile.close();
	}
	*/
	/*
	public static ArrayList<String> rimozioneStopWords(ArrayList<String> lemmiFrase) throws Exception {
		ArrayList<String> lemmiStop = new ArrayList<String>();
		for (String lemmaFrase: lemmiFrase) {	//LEMMI ESTRATTI DALLA FRASE
			boolean controllo = false;
			for (String stopLemma: stopLemmi) {	//LISTA STOPLEMMI (UNIGRAMMI)
				if(lemmaFrase.equals(stopLemma))
					controllo = true;
					break;
				}
			if(controllo==false)
				lemmiStop.add(lemmaFrase);
		}
		return lemmiStop;
	}
	*/
}
