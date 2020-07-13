package Splitter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

//funziona con tutti i tipi, intere, unigrammi ecc

public class Splitter {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader ( new FileReader (
    			"C:\\Users\\Mirco\\Java-workspace-MAP\\PreProcessing\\src\\Splitter\\idFraseLemmiUniBi.txt"));
        String outpath="C:\\Users\\Mirco\\Java-workspace-MAP\\PreProcessing\\src\\Splitter\\output\\";
        //idFrasefrasiIntere, idFraseLemmiBi, idFraseLemmiUni, idFraseLemmiUniBi
        String readString = null;
		String id ="";	
		int counter =0;	//NOME FILE TXT
		FileWriter fileoutput = new FileWriter(outpath + Integer.toString(counter) + ".txt");
		int contaRiga=0;
		
        while((readString = br.readLine()) != null){		//I:8493;17544;But, in all honesty,
        	StringTokenizer myTokens = new StringTokenizer(readString, ";");
        	
        	String FilmID = myTokens.nextToken();						//I:8493
        	myTokens.nextToken();			//221					
        	//int contaRiga = Integer.parseInt(myTokens.nextToken());
        	String frase = 	myTokens.nextToken();					//fiction favorite, favorite movie	

			if(id.equals(FilmID)) {		//I:8493 == a quello di prima
				contaRiga++;
				fileoutput.write(FilmID + ";" + contaRiga +";" + frase + "\n");	//scrivo semplice
				
			}
			else {			//I:8493 != 	I:8500 
				id=FilmID;		//id = I:8500
				counter++;		System.out.println(counter);		//++, come indice del film da assegnare al 2.txt
				fileoutput.close();				//chiuso file precedente
				
				fileoutput = new FileWriter(outpath + Integer.toString(counter) + ".txt");
				contaRiga=1;
				fileoutput.write(FilmID + ";" + contaRiga +";" + frase + "\n");
				}
			}
		fileoutput.close();	
		br.close();
    }
}
