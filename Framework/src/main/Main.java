package main;

import vettoricontesto.*;
import pmi.*;
import ranking.*;
import spiegazioni.*;

public class Main{

    public static void main(String[] args) {
        try {
            double inizio = System.currentTimeMillis();
            System.out.println("Configurazione: " + Configurazione.TipoLemmi);
            System.out.println("Tecnica: " + Configurazione.tecnica);
            
            if(Configurazione.tecnica.equals("normale")){
   ///////////////////////////////////////1) GENERAZIONE VETTORI CONTESTO//////////////////////////////////////            
            	System.out.println("/////////////////////////////////////1) GENERAZIONE VETTORI CONTESTO///////////////////////////");
            	//1) MATRICE FRASE CONTESTO
            	MatriceFC.calcolaMatriceFraseContesto();
            	MatriceFC.stampaMatriceFraseContestoFile();	//AGGIUNTO DA ME
            	System.out.println("MATRICE FRASE CONTESTO calcolata.");
            
            	//2) IDF E TF
            	VettoreIDF.calcolaIDF();
            	VettoreIDF.annullamentoScoreStopWordsIDF();//annullo score IDF stoplemmi
            	VettoreIDF.stampaIDFFile();
            	VettoreIDF.scriviIDFDAT();//SERIALIZZAZIONE
            	//VettoreIDF.leggiIDFDAT();//DESERIALIZZAZIONE
            	System.out.println("IDF calcolati.");
            
            	//2) MATRICE LEMMA FRASE
            	MatriceLF.calcolaMatriceLemmaFrase();
            	MatriceLF.stampaMatriceLemmaFraseFile();
            	System.out.println("MATRICE LEMMA FRASE calcolata.");
                     
            	//3) MATRICE LEMMA CONTESTO
            	MatriceLC.calcolaMatriceLemmaContesto();
            	System.out.println("MATRICE LEMMA CONTESTO calcolata.");
            	MatriceLC.annullamentoScoreStopWords();	//ANNULLAMENTO STOP WORDS	//AGGIUNTO DA MIRKO
            	MatriceLC.stampaMatriceLemmaContestoFile();
            	MatriceLC.ScriviMatriceLemmaContestoDAT();//SERIALIZZAZIONE
            	//MatriceLC.LeggiMatriceLemmaContestoDAT();//DESERIALIZZAZIONE

            }
            else {
            	///////////////////////////////////////1) GENERAZIONE VETTORI CONTESTO//////////////////////////////////////            
            	System.out.println("/////////////////////////////////////1) GENERAZIONE VETTORI CONTESTO///////////////////////////");
            	FrequenzaLemma.calcolaFreqLemma();
            	FrequenzaLemma.annullamentoScoreStopWords();//annullo score IDF stoplemmi
            	FrequenzaLemma.stampaIDFFile();
            	FrequenzaLemma.scriviFreqLemmaDAT();
            	//FrequenzaLemma.leggiFreqLemmaDAT();
            	System.out.println("FREQUENZE LEMMI calcolate.\n");

            	FrequenzaContesto.calcolaFreqContesto();
            	FrequenzaContesto.stampaFreqContestoFile();
            	System.out.println("FREQUENZE CONTESTI calcolate.\n");

            	PMI.calcolaPMI();
            	PMI.stampaMatriceLemmaContestoFile();
            	PMI.annullamentoScoreStopWords();		//annullo score PMI stopwords
            	System.out.println("MATRICE PMI CONTESTO calcolata.");
  
            }
            
          //4) VETTORI CONTESTO
        	VettoriContesto.calcolaVettoriContesto();
        	VettoriContesto.stampaVettoriContestoFile();
        	//VettoriContesto.scriviVettoriContestoDAT();//SERIALIZZAZIONE VETT CONTESTO
        	//VettoriContesto.leggiVettoriContestoDAT();//DESERIALIZZAZIONE VETT CONTESTO
        	System.out.println("VETTORI CONTESTO calcolati\n");
        	//VettoriContesto.calcolaSimilaritaContesti();
        	
////////////////////////////////////////2) RANKING FILM///////////////////////////////////////////
            System.out.println("//////////////////////////////////2) RANKING FILM/////////////////////////////////////////////");
            MatriceLocaleContesto.calcolaMatriceLocaleContesto();						//DEBUG
            MatriceLocaleContesto.stampaMatriceLocaleContestoFile();		//1	FASE
            MatriceLocaleContesto.stampaFileMatriceLocaleContestoDat();	//SCRIVI IN FORMATO .DAT
            SimilaritaCoseno.stampaFileSimilaritaDat();				//SCRIVI IN FORMATO .DAT
            //MatriceLocaleContesto.leggiMatriceLocaleContestoDat();		//LEGGI IN FORMATO .DAT
            //SimilaritaCoseno.leggiSimilaritaDat();					//LEGGI IN FORMATO .DAT
            System.out.println("MATRICE FILM CONTESTO calcolata\n");
         
            
//////////////////////////////////////////3) GENERAZIONE TOP 10 FILM///////////////////////////////////////////////
           System.out.println("/////////////////////////////////3) GENERAZIONE TOP 10 FILM////////////////////////////////////////////////");
           Top10LocaliPerCombinazione.calcolaTop10FilmSingoloContesto();
           Top10LocaliPerCombinazione.calcolaTop10FilmCombContesti();//AGGIUNTO MODULO DI RIUDZIONE SCORE
           Top10LocaliPerCombinazione.stampaContestiItemTop10();//STAMPA A VIDEO TOP 10
           Top10LocaliPerCombinazione.stampaContestiItemTop10File();
           Top10LocaliPerCombinazione.scriviTop10File();	//SCRIVI IN FORMATO .DAT 	//WEBAPP
           System.out.println("TOP10 FILM trovati\n");
           
           
/////////////////////////////////////////4) SPIEGAZIONI////////////////////////////////////////////////			
            System.out.println("/////////////////////////////////4) SPIEGAZIONI FILM////////////////////////////////////////////////"); 
            MatriceLocaleContesto.leggiMatriceLocaleContestoDat();//RIPRISTINO
            
            System.out.println("///////////////////////1) SPIEGAZIONI GIUSTAPPOSIZIONE DI FRASI///////////////////////////////");
            MatriceLocaliContestiFrasi.calcolaMatriceLocaliContestiFrasi();
            MatriceLocaliContestiFrasi.stampaMatriceLocaliContestiFrasiFile();			//2	FASE	
            MatriceLocaliContestiFrasi.scriviMatrice();	//SCRIVI IN FORMATO .DAT		//WEBAPP
            MatriceLocaliContestiFrasi.scriviCombinazioni();		//STAMPA A VIDEO COMB
            MatriceLocaliContestiFrasi.scriviCombinazioniFile();						//2	FASE
            System.out.println("MATRICE FILM-CONTESTI-FRASI calcolata\n");
            
             /////////////////MATRICE CONTESTI ITEM FRASI//////////
             System.out.println("///////////////////////////////2) SPIEGAZIONI CENTROIDE///////////////////////////////");
             MatriceContestiItemFrasi.calcolaMatriceContestiItemFrasi();	//RUN COMPLETA
             MatriceContestiItemFrasi.scriviCombinazioni();		     //STAMPA A VIDEO COMB
             MatriceContestiItemFrasi.scriviCombinazioniFile();			//3	FASE
             MatriceContestiItemFrasi.scriviMatrice();	//SCRIVI IN FORMATO .DAT	//WEBAPP
             //MatriceContestiItemFrasi.stampaMatriceContestiItemFrasiFile(); //STAMPA SU FILE
             System.out.println("MATRICE CONTESTI FILM FRASI calcolata.\n");
             
             System.out.println("FINE");
             
 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
             
             //GENERAZIONE .dat
             /*
             for(int i=1; i<=Configurazione.numeroLocali; i++) {
                 FileTestoItems.leggiFrasiLocale(i);
                 FileTestoItems.ScriviFrasiLocaleDAT(i);
                 System.out.println(i);
             }
			 */     
             double durata = (System.currentTimeMillis()-inizio)/60000;
             System.out.println("Tempo esecuzione:  " + durata + " minuti.");

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
