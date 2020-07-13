package Filtering;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Filtering {
	
    public static void main(String[] args) throws IOException {//dataset_verypositive.txt //dataset_positivo.txt													
    	BufferedReader br = new BufferedReader ( new FileReader (
    			"C:\\Users\\Mirco\\Java-workspace-MAP\\PreProcessing\\src\\Filtering\\dataset_positivo.txt"));
        FileWriter complete = new FileWriter(
        		"C:\\Users\\Mirco\\Java-workspace-MAP\\PreProcessing\\src\\Filtering\\dataset_positivo_CLEAN.txt");

		int counter=0;
		String readString=null;
        while  ((readString = br.readLine())  != null){	//I:8493	But, in all honesty, I thought this wa
        	StringTokenizer myTokens = new StringTokenizer(readString, "\t");
        	String FilmID = myTokens.nextToken();	//I:8493
        	//int countFrasi = Integer.parseInt(myTokens.nextToken());	//16501
        	String sentence = myTokens.nextToken();	//But, in all honesty,... .
        	if(!(sentence.toLowerCase().contains("bluray") ||
        		sentence.toLowerCase().contains("dvd") ||  
        		sentence.toLowerCase().contains("cd")  || 
        		sentence.toLowerCase().contains("texture") ||
        		sentence.toLowerCase().contains("rent") || 
        		sentence.toLowerCase().contains("buy") ||
        		sentence.toLowerCase().contains("purchase") ||
        		sentence.toLowerCase().contains("quality") || 
        		sentence.toLowerCase().contains("hd") || 
        		sentence.toLowerCase().contains("3d") || 
        		sentence.toLowerCase().contains("1080p") ||
        		sentence.split("\\s+").length<5 || sentence.split("\\s+").length>80)){ //n parole nell frase
        			counter++;
					System.out.println(FilmID +"\t" + counter + "\t" + sentence);
					complete.write(FilmID +"\t" + counter + "\t" + sentence + "\n");
				}
	    }
        complete.close();
        br.close();
    }
    
}
