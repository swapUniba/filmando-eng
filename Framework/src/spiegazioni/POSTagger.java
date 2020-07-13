package spiegazioni;

import java.util.*;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

//FILTRO FRASI IMPERSONALI
	//DT: determiner			the, this, a, an, all, these, every, another, each
	//PRP: pronoun, personal 	it, it's, I, you, he, she, we, they, one
	//IN: preposition or conjunction, subordinating		if , for, of, by, on, at,  after, while, like, out,from,  in, with, without,  before, through, whether, because, though, unlike, despite
	//RB: adverb				just, much, very, so, not, always, early, also, yet, even, still, sometimes, obviously, aside, anyway, overall, instead, indeed, therefore, eventually, generally, fast, regardless, otherwise, finally, highly, unfortunately, meanwhile, however
	//VBZ: verb, present tense, 3rd person singular		drives, manages, tries, uses
public class POSTagger {
	public static StanfordCoreNLP pipeline = Pipeline.getPipeline();
						
	public static boolean syntax(String testoFrase) {
		testoFrase = testoFrase.replaceAll("^\\p{Punct}+|\\p{Punct}+$", "");//RIMUOVO PUNTEGGIATURA ALL'INIZIO E FINE
		CoreDocument document = new CoreDocument(testoFrase);	  //CREATE A DOCUMENT OBJECT
		pipeline.annotate(document);
		List<CoreLabel> text = document.tokens();
		
		if(text.size()>4){//SE CI SONO ALMENO 5 TOKEN
			String POS = text.get(0).get(PartOfSpeechAnnotation.class);	//JJ		//CD
			if(POS.equals("DT") || POS.equals("PRP")  ||  POS.equals("VBZ") || POS.equals("IN")) { //|| POS.equals("RB")) {
				String firstToken = text.get(0).originalText().toLowerCase();
				if(!firstToken.equals("i") 	&& !firstToken.equals("we") 	&&  !firstToken.equals("she")   && 	!firstToken.equals("he")  && 	!firstToken.equals("you") &&  	!firstToken.equals("they") && 			!firstToken.equals("if") && 	!firstToken.equals("with") &&
				   !firstToken.equals("of")  &&		!firstToken.equals("by")  &&	!firstToken.equals("on")  && 	!firstToken.equals("at")	&&	!firstToken.equals("because") && !firstToken.equals("yet") && !firstToken.equals("another") && 
				   !firstToken.equals("just") && !firstToken.equals("much")		&&	!firstToken.equals("very") && 	!firstToken.equals("not")		&& 	!firstToken.equals("so")  && 	!firstToken.equals("early")	&&	!firstToken.equals("always")	&& !firstToken.equals("also")  && !firstToken.equals("fast"))
						if(I(testoFrase))
							return true;
			}
		}
		return false;
	}
		
	//FITRO CON RIMOZIONE DEGLI I,WE...
	public static boolean I(String testoFrase) {		
		String[] words = testoFrase.split(" ");
		for(String word: words) {
			String wordCl = word.replaceAll("\\p{Punct}", "");//RIMUOVO PUNTEGGIATURA
			if (wordCl.toLowerCase().equals("i") ||  
				//wordCl.toLowerCase().equals("he") || 	wordCl.toLowerCase().equals("she") || 
				wordCl.toLowerCase().equals("we") || wordCl.toLowerCase().equals("they") || 
				
				wordCl.toLowerCase().equals("me") || wordCl.toLowerCase().equals("my") || 
				//NO POSSESSIVI
				wordCl.toLowerCase().equals("mine") ||	wordCl.toLowerCase().equals("yours") || 
				//wordCl.toLowerCase().equals("his") ||  wordCl.toLowerCase().equals("hers") ||
				wordCl.toLowerCase().equals("ours") || wordCl.toLowerCase().equals("theirs") || 
				
				wordCl.toLowerCase().equals("not")		)
					return false;
		}
    	return true;
    }

}

/*
"DT NN VBZ", 			"DT NNP VBZ",	"DT NNS VBP",	"DT NN CC NNS VB JJ", 	"DT NNS CC NN VB JJ", 	"DT NN CC NNS VB JJ" ,
"DT NNS CC NNS VB JJ",	"DT NNPS VBP", 	"DT NN VBD", 	"DT NNP VBD", 			"DT NNS VBD",			"DT NNPS VBD",		
"DT VBZ", 				"DT VBP",		"DT JJS NN", 	"DT JJS NNS",
	
"PRP VBZ DT", 	"PRP VBZ RB", 	"PRP VBZ CD",	"PRP RB VBZ",   "PRP VBD RB",  	"PRP VBD DT", 
"PRP VBD CD", 	"PRP VBD VBZ",
	
"RB JJ", 	"RB VBG", 	"RB VBN",
	
"JJ NN",	"JJ NNS",	
			  		
"CD IN DT"
*/
