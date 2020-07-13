package report;

import java.util.HashMap;
import classi.ValutazioneTipo3;
import classi.ValutazioneTipo4;
/*
//////////////////////////////////////CONTRONTI VALUTAZIONI/////////////////////////////
7) confronto valutazioni metriche centroide e frasi singole;
8) confronto valutazioni metriche framework e baseline;
9) preferenze tra centroide e frasi singole in base al numero di contesti;
*/
public class Confronti {
	
    //7) confronto valutazioni metriche FRAMEWORK VS BASELINE DISTRIBUZIONALE
    public static HashMap<String, HashMap<String, HashMap<String, Double>>> centroideVSSingoleMetriche(){
        HashMap<String, HashMap<String, HashMap<String, Double>>> esiti = new HashMap<>();

        HashMap<String, HashMap<String, Double>> uni = new HashMap<>();
        HashMap<String, HashMap<String, Double>> bi = new HashMap<>();
        HashMap<String, HashMap<String, Double>> unibi = new HashMap<>();

        HashMap<String, Double> centroideUni = new HashMap<>();
        HashMap<String, Double> centroideBi = new HashMap<>();
        HashMap<String, Double> centroideUniBi = new HashMap<>();

        HashMap<String, Double> singoleUni = new HashMap<>();
        HashMap<String, Double> singoleBi = new HashMap<>();
        HashMap<String, Double> singoleUniBi = new HashMap<>();

        HashMap<String, Double> indifferentiUni = new HashMap<>();
        HashMap<String, Double> indifferentiBi = new HashMap<>();
        HashMap<String, Double> indifferentiUniBi = new HashMap<>();
        /////////////////////////////////////////////
        singoleUni.put("Trasparenza", 0.0);
        singoleUni.put("Persuasione", 0.0);
        singoleUni.put("Coinvolgimento", 0.0);
        singoleUni.put("Fiducia", 0.0);
        
        singoleBi.put("Trasparenza", 0.0);
        singoleBi.put("Persuasione", 0.0);
        singoleBi.put("Coinvolgimento", 0.0);
        singoleBi.put("Fiducia", 0.0);
        
        singoleUniBi.put("Trasparenza", 0.0);
        singoleUniBi.put("Persuasione", 0.0);
        singoleUniBi.put("Coinvolgimento", 0.0);
        singoleUniBi.put("Fiducia", 0.0);

        
        centroideUni.put("Trasparenza", 0.0);
        centroideUni.put("Persuasione", 0.0);
        centroideUni.put("Coinvolgimento", 0.0);
        centroideUni.put("Fiducia", 0.0);
        
        centroideBi.put("Trasparenza", 0.0);
        centroideBi.put("Persuasione", 0.0);
        centroideBi.put("Coinvolgimento", 0.0);
        centroideBi.put("Fiducia", 0.0);
        
        centroideUniBi.put("Trasparenza", 0.0);
        centroideUniBi.put("Persuasione", 0.0);
        centroideUniBi.put("Coinvolgimento", 0.0);
        centroideUniBi.put("Fiducia", 0.0);

        
        indifferentiUni.put("Trasparenza", 0.0);
        indifferentiUni.put("Persuasione", 0.0);
        indifferentiUni.put("Coinvolgimento", 0.0);
        indifferentiUni.put("Fiducia", 0.0);
        
        indifferentiBi.put("Trasparenza", 0.0);
        indifferentiBi.put("Persuasione", 0.0);
        indifferentiBi.put("Coinvolgimento", 0.0);
        indifferentiBi.put("Fiducia", 0.0);
        
        indifferentiUniBi.put("Trasparenza", 0.0);
        indifferentiUniBi.put("Persuasione", 0.0);
        indifferentiUniBi.put("Coinvolgimento", 0.0);
        indifferentiUniBi.put("Fiducia", 0.0);

        int totaliUni = 0, totaliBi = 0, totaliUniBi = 0;
     //////////////////////////////////////////////////////////////////////////////////////////////   
        
        for (ValutazioneTipo3 v3 : Report.valutazioni3){	//FRAMEWORK VS BASELINE DISTRIBUZIONALE
        	/////////////////////////////////////UNIGRAMMI//////////////////////////
            if (v3.getConfigurazione().equals("unigrammi")){
                totaliUni++;
                
                if (v3.getTrasparenza() == 1){
                    centroideUni.put("Trasparenza", centroideUni.get("Trasparenza")+1);
                } else if (v3.getTrasparenza() == 2){
                    singoleUni.put("Trasparenza", 	singoleUni.get("Trasparenza")+1);
                } else {
                    indifferentiUni.put("Trasparenza", indifferentiUni.get("Trasparenza")+1);
                }
                
                if (v3.getPersuasione() == 1){
                    centroideUni.put("Persuasione", centroideUni.get("Persuasione")+1);
                } else if (v3.getPersuasione() == 2){
                    singoleUni.put("Persuasione", singoleUni.get("Persuasione")+1);
                } else {
                    indifferentiUni.put("Persuasione", indifferentiUni.get("Persuasione")+1);
                }

                if (v3.getCoinvolgimento() == 1){
                    centroideUni.put("Coinvolgimento", centroideUni.get("Coinvolgimento")+1);
                } else if (v3.getCoinvolgimento() == 2){
                    singoleUni.put("Coinvolgimento", singoleUni.get("Coinvolgimento")+1);
                } else {
                    indifferentiUni.put("Coinvolgimento", indifferentiUni.get("Coinvolgimento")+1);
                }

                if (v3.getFiducia() == 1){
                    centroideUni.put("Fiducia", centroideUni.get("Fiducia")+1);
                } else if (v3.getFiducia() == 2){
                    singoleUni.put("Fiducia", singoleUni.get("Fiducia")+1);
                } else {
                    indifferentiUni.put("Fiducia", indifferentiUni.get("Fiducia")+1);
                }

            } 
            /////////////////////////////////////BIGRAMMI//////////////////////////
            else if (v3.getConfigurazione().equals("bigrammi")){
                totaliBi++;
                
                if (v3.getTrasparenza() == 1){
                    centroideBi.put("Trasparenza", centroideBi.get("Trasparenza")+1);
                } else if (v3.getTrasparenza() == 2){
                    singoleBi.put("Trasparenza", singoleBi.get("Trasparenza")+1);
                } else {
                    indifferentiBi.put("Trasparenza", indifferentiBi.get("Trasparenza")+1);
                }

                if (v3.getPersuasione() == 1){
                    centroideBi.put("Persuasione", centroideBi.get("Persuasione")+1);
                } else if (v3.getPersuasione() == 2){
                    singoleBi.put("Persuasione", singoleBi.get("Persuasione")+1);
                } else {
                    indifferentiBi.put("Persuasione", indifferentiBi.get("Persuasione")+1);
                }

                if (v3.getCoinvolgimento() == 1){
                    centroideBi.put("Coinvolgimento", centroideBi.get("Coinvolgimento")+1);
                } else if (v3.getCoinvolgimento() == 2){
                    singoleBi.put("Coinvolgimento", singoleBi.get("Coinvolgimento")+1);
                } else {
                    indifferentiBi.put("Coinvolgimento", indifferentiBi.get("Coinvolgimento")+1);
                }

                if (v3.getFiducia() == 1){
                    centroideBi.put("Fiducia", centroideBi.get("Fiducia")+1);
                } else if (v3.getFiducia() == 2){
                    singoleBi.put("Fiducia", singoleBi.get("Fiducia")+1);
                } else {
                    indifferentiBi.put("Fiducia", indifferentiBi.get("Fiducia")+1);
                }

            } 
            /////////////////////////////////////UNIBIGRAMMI//////////////////////////
            else if (v3.getConfigurazione().equals("unibigrammi")){
                totaliUniBi++;

                if (v3.getTrasparenza() == 1){
                    centroideUniBi.put("Trasparenza", centroideUniBi.get("Trasparenza")+1);
                } else if (v3.getTrasparenza() == 2){
                    singoleUniBi.put("Trasparenza", singoleUniBi.get("Trasparenza")+1);
                } else {
                    indifferentiUniBi.put("Trasparenza", indifferentiUniBi.get("Trasparenza")+1);
                }

                if (v3.getPersuasione() == 1){
                    centroideUniBi.put("Persuasione", centroideUniBi.get("Persuasione")+1);
                } else if (v3.getPersuasione() == 2){
                    singoleUniBi.put("Persuasione", singoleUniBi.get("Persuasione")+1);
                } else {
                    indifferentiUniBi.put("Persuasione", indifferentiUniBi.get("Persuasione")+1);
                }

                if (v3.getCoinvolgimento() == 1){
                    centroideUniBi.put("Coinvolgimento", centroideUniBi.get("Coinvolgimento")+1);
                } else if (v3.getCoinvolgimento() == 2){
                    singoleUniBi.put("Coinvolgimento", singoleUniBi.get("Coinvolgimento")+1);
                } else {
                    indifferentiUniBi.put("Coinvolgimento", indifferentiUniBi.get("Coinvolgimento")+1);
                }

                if (v3.getFiducia() == 1){
                    centroideUniBi.put("Fiducia", centroideUniBi.get("Fiducia")+1);
                } else if (v3.getFiducia() == 2){
                    singoleUniBi.put("Fiducia", singoleUniBi.get("Fiducia")+1);
                } else {
                    indifferentiUniBi.put("Fiducia", indifferentiUniBi.get("Fiducia")+1);
                }
                
            }
            
        }
        ///////////////////////////////
        if (totaliUni != 0){
            centroideUni.put("Trasparenza", centroideUni.get("Trasparenza")/totaliUni);
            centroideUni.put("Persuasione", centroideUni.get("Persuasione")/totaliUni);
            centroideUni.put("Coinvolgimento", centroideUni.get("Coinvolgimento")/totaliUni);
            centroideUni.put("Fiducia", centroideUni.get("Fiducia")/totaliUni);
            
            singoleUni.put("Trasparenza", singoleUni.get("Trasparenza")/totaliUni);
            singoleUni.put("Persuasione", singoleUni.get("Persuasione")/totaliUni);
            singoleUni.put("Coinvolgimento", singoleUni.get("Coinvolgimento")/totaliUni);
            singoleUni.put("Fiducia", singoleUni.get("Fiducia")/totaliUni);
            
            indifferentiUni.put("Trasparenza", indifferentiUni.get("Trasparenza")/totaliUni);
            indifferentiUni.put("Persuasione", indifferentiUni.get("Persuasione")/totaliUni);
            indifferentiUni.put("Coinvolgimento", indifferentiUni.get("Coinvolgimento")/totaliUni);
            indifferentiUni.put("Fiducia", indifferentiUni.get("Fiducia")/totaliUni);
        }
        ///////////////////////////////
        if (totaliBi != 0){
            centroideBi.put("Trasparenza", centroideBi.get("Trasparenza")/totaliBi);
            centroideBi.put("Persuasione", centroideBi.get("Persuasione")/totaliBi);
            centroideBi.put("Coinvolgimento", centroideBi.get("Coinvolgimento")/totaliBi);
            centroideBi.put("Fiducia", centroideBi.get("Fiducia")/totaliBi);
            
            singoleBi.put("Trasparenza", singoleBi.get("Trasparenza")/totaliBi);
            singoleBi.put("Persuasione", singoleBi.get("Persuasione")/totaliBi);
            singoleBi.put("Coinvolgimento", singoleBi.get("Coinvolgimento")/totaliBi);
            singoleBi.put("Fiducia", singoleBi.get("Fiducia")/totaliBi);
            
            indifferentiBi.put("Trasparenza", indifferentiBi.get("Trasparenza")/totaliBi);
            indifferentiBi.put("Persuasione", indifferentiBi.get("Persuasione")/totaliBi);
            indifferentiBi.put("Coinvolgimento", indifferentiBi.get("Coinvolgimento")/totaliBi);
            indifferentiBi.put("Fiducia", indifferentiBi.get("Fiducia")/totaliBi);
        }
        ///////////////////////////////
        if (totaliUniBi != 0){
            centroideUniBi.put("Trasparenza", centroideUniBi.get("Trasparenza")/totaliUniBi);
            centroideUniBi.put("Persuasione", centroideUniBi.get("Persuasione")/totaliUniBi);
            centroideUniBi.put("Coinvolgimento", centroideUniBi.get("Coinvolgimento")/totaliUniBi);
            centroideUniBi.put("Fiducia", centroideUniBi.get("Fiducia")/totaliUniBi);
            
            singoleUniBi.put("Trasparenza", singoleUniBi.get("Trasparenza")/totaliUniBi);
            singoleUniBi.put("Persuasione", singoleUniBi.get("Persuasione")/totaliUniBi);
            singoleUniBi.put("Coinvolgimento", singoleUniBi.get("Coinvolgimento")/totaliUniBi);
            singoleUniBi.put("Fiducia", singoleUniBi.get("Fiducia")/totaliUniBi);
            
            indifferentiUniBi.put("Trasparenza", indifferentiUniBi.get("Trasparenza")/totaliUniBi);
            indifferentiUniBi.put("Persuasione", indifferentiUniBi.get("Persuasione")/totaliUniBi);
            indifferentiUniBi.put("Coinvolgimento", indifferentiUniBi.get("Coinvolgimento")/totaliUniBi);
            indifferentiUniBi.put("Fiducia", indifferentiUniBi.get("Fiducia")/totaliUniBi);
        }
        
        uni.put("sistema", centroideUni);
        uni.put("baselineDistr", singoleUni);
        uni.put("indifferenti", indifferentiUni);

        bi.put("sistema", centroideBi);
        bi.put("baselineDistr", singoleBi);
        bi.put("indifferenti", indifferentiBi);

        unibi.put("sistema", centroideUniBi);
        unibi.put("baselineDistr", singoleUniBi);
        unibi.put("indifferenti", indifferentiUniBi);
      ///////////////////////////////////////////////////
        esiti.put("unigrammi", uni);
        esiti.put("bigrammi", bi);
        esiti.put("unibigrammi", unibi);
        return esiti;
    }
    
  //7) confronto valutazioni metriche FRAMEWORK VS BASELINE DISTRIBUZIONALE Normale
    public static HashMap<String, HashMap<String, HashMap<String, Double>>> centroideVSSingoleMetricheNormale(){
        HashMap<String, HashMap<String, HashMap<String, Double>>> esiti = new HashMap<>();

        HashMap<String, HashMap<String, Double>> uni = new HashMap<>();
        HashMap<String, HashMap<String, Double>> bi = new HashMap<>();
        HashMap<String, HashMap<String, Double>> unibi = new HashMap<>();

        HashMap<String, Double> centroideUni = new HashMap<>();
        HashMap<String, Double> centroideBi = new HashMap<>();
        HashMap<String, Double> centroideUniBi = new HashMap<>();

        HashMap<String, Double> singoleUni = new HashMap<>();
        HashMap<String, Double> singoleBi = new HashMap<>();
        HashMap<String, Double> singoleUniBi = new HashMap<>();

        HashMap<String, Double> indifferentiUni = new HashMap<>();
        HashMap<String, Double> indifferentiBi = new HashMap<>();
        HashMap<String, Double> indifferentiUniBi = new HashMap<>();
        /////////////////////////////////////////////
        singoleUni.put("Trasparenza", 0.0);
        singoleUni.put("Persuasione", 0.0);
        singoleUni.put("Coinvolgimento", 0.0);
        singoleUni.put("Fiducia", 0.0);
        
        singoleBi.put("Trasparenza", 0.0);
        singoleBi.put("Persuasione", 0.0);
        singoleBi.put("Coinvolgimento", 0.0);
        singoleBi.put("Fiducia", 0.0);
        
        singoleUniBi.put("Trasparenza", 0.0);
        singoleUniBi.put("Persuasione", 0.0);
        singoleUniBi.put("Coinvolgimento", 0.0);
        singoleUniBi.put("Fiducia", 0.0);

        
        centroideUni.put("Trasparenza", 0.0);
        centroideUni.put("Persuasione", 0.0);
        centroideUni.put("Coinvolgimento", 0.0);
        centroideUni.put("Fiducia", 0.0);
        
        centroideBi.put("Trasparenza", 0.0);
        centroideBi.put("Persuasione", 0.0);
        centroideBi.put("Coinvolgimento", 0.0);
        centroideBi.put("Fiducia", 0.0);
        
        centroideUniBi.put("Trasparenza", 0.0);
        centroideUniBi.put("Persuasione", 0.0);
        centroideUniBi.put("Coinvolgimento", 0.0);
        centroideUniBi.put("Fiducia", 0.0);

        
        indifferentiUni.put("Trasparenza", 0.0);
        indifferentiUni.put("Persuasione", 0.0);
        indifferentiUni.put("Coinvolgimento", 0.0);
        indifferentiUni.put("Fiducia", 0.0);
        
        indifferentiBi.put("Trasparenza", 0.0);
        indifferentiBi.put("Persuasione", 0.0);
        indifferentiBi.put("Coinvolgimento", 0.0);
        indifferentiBi.put("Fiducia", 0.0);
        
        indifferentiUniBi.put("Trasparenza", 0.0);
        indifferentiUniBi.put("Persuasione", 0.0);
        indifferentiUniBi.put("Coinvolgimento", 0.0);
        indifferentiUniBi.put("Fiducia", 0.0);

        int totaliUni = 0, totaliBi = 0, totaliUniBi = 0;
     //////////////////////////////////////////////////////////////////////////////////////////////   
        
        for (ValutazioneTipo3 v3 : Report.valutazioni3){	//FRAMEWORK VS BASELINE DISTRIBUZIONALE
        	if(v3.getTecnica().equals("normale")){
        	/////////////////////////////////////UNIGRAMMI//////////////////////////
            if (v3.getConfigurazione().equals("unigrammi")){
                totaliUni++;
                
                if (v3.getTrasparenza() == 1){
                    centroideUni.put("Trasparenza", centroideUni.get("Trasparenza")+1);
                } else if (v3.getTrasparenza() == 2){
                    singoleUni.put("Trasparenza", 	singoleUni.get("Trasparenza")+1);
                } else {
                    indifferentiUni.put("Trasparenza", indifferentiUni.get("Trasparenza")+1);
                }
                
                if (v3.getPersuasione() == 1){
                    centroideUni.put("Persuasione", centroideUni.get("Persuasione")+1);
                } else if (v3.getPersuasione() == 2){
                    singoleUni.put("Persuasione", singoleUni.get("Persuasione")+1);
                } else {
                    indifferentiUni.put("Persuasione", indifferentiUni.get("Persuasione")+1);
                }

                if (v3.getCoinvolgimento() == 1){
                    centroideUni.put("Coinvolgimento", centroideUni.get("Coinvolgimento")+1);
                } else if (v3.getCoinvolgimento() == 2){
                    singoleUni.put("Coinvolgimento", singoleUni.get("Coinvolgimento")+1);
                } else {
                    indifferentiUni.put("Coinvolgimento", indifferentiUni.get("Coinvolgimento")+1);
                }

                if (v3.getFiducia() == 1){
                    centroideUni.put("Fiducia", centroideUni.get("Fiducia")+1);
                } else if (v3.getFiducia() == 2){
                    singoleUni.put("Fiducia", singoleUni.get("Fiducia")+1);
                } else {
                    indifferentiUni.put("Fiducia", indifferentiUni.get("Fiducia")+1);
                }

            } 
            /////////////////////////////////////BIGRAMMI//////////////////////////
            else if (v3.getConfigurazione().equals("bigrammi")){
                totaliBi++;
                
                if (v3.getTrasparenza() == 1){
                    centroideBi.put("Trasparenza", centroideBi.get("Trasparenza")+1);
                } else if (v3.getTrasparenza() == 2){
                    singoleBi.put("Trasparenza", singoleBi.get("Trasparenza")+1);
                } else {
                    indifferentiBi.put("Trasparenza", indifferentiBi.get("Trasparenza")+1);
                }

                if (v3.getPersuasione() == 1){
                    centroideBi.put("Persuasione", centroideBi.get("Persuasione")+1);
                } else if (v3.getPersuasione() == 2){
                    singoleBi.put("Persuasione", singoleBi.get("Persuasione")+1);
                } else {
                    indifferentiBi.put("Persuasione", indifferentiBi.get("Persuasione")+1);
                }

                if (v3.getCoinvolgimento() == 1){
                    centroideBi.put("Coinvolgimento", centroideBi.get("Coinvolgimento")+1);
                } else if (v3.getCoinvolgimento() == 2){
                    singoleBi.put("Coinvolgimento", singoleBi.get("Coinvolgimento")+1);
                } else {
                    indifferentiBi.put("Coinvolgimento", indifferentiBi.get("Coinvolgimento")+1);
                }

                if (v3.getFiducia() == 1){
                    centroideBi.put("Fiducia", centroideBi.get("Fiducia")+1);
                } else if (v3.getFiducia() == 2){
                    singoleBi.put("Fiducia", singoleBi.get("Fiducia")+1);
                } else {
                    indifferentiBi.put("Fiducia", indifferentiBi.get("Fiducia")+1);
                }

            } 
            /////////////////////////////////////UNIBIGRAMMI//////////////////////////
            else if (v3.getConfigurazione().equals("unibigrammi")){
                totaliUniBi++;

                if (v3.getTrasparenza() == 1){
                    centroideUniBi.put("Trasparenza", centroideUniBi.get("Trasparenza")+1);
                } else if (v3.getTrasparenza() == 2){
                    singoleUniBi.put("Trasparenza", singoleUniBi.get("Trasparenza")+1);
                } else {
                    indifferentiUniBi.put("Trasparenza", indifferentiUniBi.get("Trasparenza")+1);
                }

                if (v3.getPersuasione() == 1){
                    centroideUniBi.put("Persuasione", centroideUniBi.get("Persuasione")+1);
                } else if (v3.getPersuasione() == 2){
                    singoleUniBi.put("Persuasione", singoleUniBi.get("Persuasione")+1);
                } else {
                    indifferentiUniBi.put("Persuasione", indifferentiUniBi.get("Persuasione")+1);
                }

                if (v3.getCoinvolgimento() == 1){
                    centroideUniBi.put("Coinvolgimento", centroideUniBi.get("Coinvolgimento")+1);
                } else if (v3.getCoinvolgimento() == 2){
                    singoleUniBi.put("Coinvolgimento", singoleUniBi.get("Coinvolgimento")+1);
                } else {
                    indifferentiUniBi.put("Coinvolgimento", indifferentiUniBi.get("Coinvolgimento")+1);
                }

                if (v3.getFiducia() == 1){
                    centroideUniBi.put("Fiducia", centroideUniBi.get("Fiducia")+1);
                } else if (v3.getFiducia() == 2){
                    singoleUniBi.put("Fiducia", singoleUniBi.get("Fiducia")+1);
                } else {
                    indifferentiUniBi.put("Fiducia", indifferentiUniBi.get("Fiducia")+1);
                }
                
            }
          }
        }
        
        ///////////////////////////////
        if (totaliUni != 0){
            centroideUni.put("Trasparenza", centroideUni.get("Trasparenza")/totaliUni);
            centroideUni.put("Persuasione", centroideUni.get("Persuasione")/totaliUni);
            centroideUni.put("Coinvolgimento", centroideUni.get("Coinvolgimento")/totaliUni);
            centroideUni.put("Fiducia", centroideUni.get("Fiducia")/totaliUni);
            
            singoleUni.put("Trasparenza", singoleUni.get("Trasparenza")/totaliUni);
            singoleUni.put("Persuasione", singoleUni.get("Persuasione")/totaliUni);
            singoleUni.put("Coinvolgimento", singoleUni.get("Coinvolgimento")/totaliUni);
            singoleUni.put("Fiducia", singoleUni.get("Fiducia")/totaliUni);
            
            indifferentiUni.put("Trasparenza", indifferentiUni.get("Trasparenza")/totaliUni);
            indifferentiUni.put("Persuasione", indifferentiUni.get("Persuasione")/totaliUni);
            indifferentiUni.put("Coinvolgimento", indifferentiUni.get("Coinvolgimento")/totaliUni);
            indifferentiUni.put("Fiducia", indifferentiUni.get("Fiducia")/totaliUni);
        }
        ///////////////////////////////
        if (totaliBi != 0){
            centroideBi.put("Trasparenza", centroideBi.get("Trasparenza")/totaliBi);
            centroideBi.put("Persuasione", centroideBi.get("Persuasione")/totaliBi);
            centroideBi.put("Coinvolgimento", centroideBi.get("Coinvolgimento")/totaliBi);
            centroideBi.put("Fiducia", centroideBi.get("Fiducia")/totaliBi);
            
            singoleBi.put("Trasparenza", singoleBi.get("Trasparenza")/totaliBi);
            singoleBi.put("Persuasione", singoleBi.get("Persuasione")/totaliBi);
            singoleBi.put("Coinvolgimento", singoleBi.get("Coinvolgimento")/totaliBi);
            singoleBi.put("Fiducia", singoleBi.get("Fiducia")/totaliBi);
            
            indifferentiBi.put("Trasparenza", indifferentiBi.get("Trasparenza")/totaliBi);
            indifferentiBi.put("Persuasione", indifferentiBi.get("Persuasione")/totaliBi);
            indifferentiBi.put("Coinvolgimento", indifferentiBi.get("Coinvolgimento")/totaliBi);
            indifferentiBi.put("Fiducia", indifferentiBi.get("Fiducia")/totaliBi);
        }
        ///////////////////////////////
        if (totaliUniBi != 0){
            centroideUniBi.put("Trasparenza", centroideUniBi.get("Trasparenza")/totaliUniBi);
            centroideUniBi.put("Persuasione", centroideUniBi.get("Persuasione")/totaliUniBi);
            centroideUniBi.put("Coinvolgimento", centroideUniBi.get("Coinvolgimento")/totaliUniBi);
            centroideUniBi.put("Fiducia", centroideUniBi.get("Fiducia")/totaliUniBi);
            
            singoleUniBi.put("Trasparenza", singoleUniBi.get("Trasparenza")/totaliUniBi);
            singoleUniBi.put("Persuasione", singoleUniBi.get("Persuasione")/totaliUniBi);
            singoleUniBi.put("Coinvolgimento", singoleUniBi.get("Coinvolgimento")/totaliUniBi);
            singoleUniBi.put("Fiducia", singoleUniBi.get("Fiducia")/totaliUniBi);
            
            indifferentiUniBi.put("Trasparenza", indifferentiUniBi.get("Trasparenza")/totaliUniBi);
            indifferentiUniBi.put("Persuasione", indifferentiUniBi.get("Persuasione")/totaliUniBi);
            indifferentiUniBi.put("Coinvolgimento", indifferentiUniBi.get("Coinvolgimento")/totaliUniBi);
            indifferentiUniBi.put("Fiducia", indifferentiUniBi.get("Fiducia")/totaliUniBi);
        }
        
        uni.put("sistema", centroideUni);
        uni.put("baselineDistr", singoleUni);
        uni.put("indifferenti", indifferentiUni);

        bi.put("sistema", centroideBi);
        bi.put("baselineDistr", singoleBi);
        bi.put("indifferenti", indifferentiBi);

        unibi.put("sistema", centroideUniBi);
        unibi.put("baselineDistr", singoleUniBi);
        unibi.put("indifferenti", indifferentiUniBi);
      ///////////////////////////////////////////////////
        esiti.put("unigrammi", uni);
        esiti.put("bigrammi", bi);
        esiti.put("unibigrammi", unibi);
        return esiti;
    }
    
    
  //7) confronto valutazioni metriche FRAMEWORK VS BASELINE DISTRIBUZIONALE
    public static HashMap<String, HashMap<String, HashMap<String, Double>>> centroideVSSingoleMetrichePMI(){
        HashMap<String, HashMap<String, HashMap<String, Double>>> esiti = new HashMap<>();

        HashMap<String, HashMap<String, Double>> uni = new HashMap<>();
        HashMap<String, HashMap<String, Double>> bi = new HashMap<>();
        HashMap<String, HashMap<String, Double>> unibi = new HashMap<>();

        HashMap<String, Double> centroideUni = new HashMap<>();
        HashMap<String, Double> centroideBi = new HashMap<>();
        HashMap<String, Double> centroideUniBi = new HashMap<>();

        HashMap<String, Double> singoleUni = new HashMap<>();
        HashMap<String, Double> singoleBi = new HashMap<>();
        HashMap<String, Double> singoleUniBi = new HashMap<>();

        HashMap<String, Double> indifferentiUni = new HashMap<>();
        HashMap<String, Double> indifferentiBi = new HashMap<>();
        HashMap<String, Double> indifferentiUniBi = new HashMap<>();
        /////////////////////////////////////////////
        singoleUni.put("Trasparenza", 0.0);
        singoleUni.put("Persuasione", 0.0);
        singoleUni.put("Coinvolgimento", 0.0);
        singoleUni.put("Fiducia", 0.0);
        
        singoleBi.put("Trasparenza", 0.0);
        singoleBi.put("Persuasione", 0.0);
        singoleBi.put("Coinvolgimento", 0.0);
        singoleBi.put("Fiducia", 0.0);
        
        singoleUniBi.put("Trasparenza", 0.0);
        singoleUniBi.put("Persuasione", 0.0);
        singoleUniBi.put("Coinvolgimento", 0.0);
        singoleUniBi.put("Fiducia", 0.0);

        
        centroideUni.put("Trasparenza", 0.0);
        centroideUni.put("Persuasione", 0.0);
        centroideUni.put("Coinvolgimento", 0.0);
        centroideUni.put("Fiducia", 0.0);
        
        centroideBi.put("Trasparenza", 0.0);
        centroideBi.put("Persuasione", 0.0);
        centroideBi.put("Coinvolgimento", 0.0);
        centroideBi.put("Fiducia", 0.0);
        
        centroideUniBi.put("Trasparenza", 0.0);
        centroideUniBi.put("Persuasione", 0.0);
        centroideUniBi.put("Coinvolgimento", 0.0);
        centroideUniBi.put("Fiducia", 0.0);

        
        indifferentiUni.put("Trasparenza", 0.0);
        indifferentiUni.put("Persuasione", 0.0);
        indifferentiUni.put("Coinvolgimento", 0.0);
        indifferentiUni.put("Fiducia", 0.0);
        
        indifferentiBi.put("Trasparenza", 0.0);
        indifferentiBi.put("Persuasione", 0.0);
        indifferentiBi.put("Coinvolgimento", 0.0);
        indifferentiBi.put("Fiducia", 0.0);
        
        indifferentiUniBi.put("Trasparenza", 0.0);
        indifferentiUniBi.put("Persuasione", 0.0);
        indifferentiUniBi.put("Coinvolgimento", 0.0);
        indifferentiUniBi.put("Fiducia", 0.0);

        int totaliUni = 0, totaliBi = 0, totaliUniBi = 0;
     //////////////////////////////////////////////////////////////////////////////////////////////   
        
        for (ValutazioneTipo3 v3 : Report.valutazioni3){	//FRAMEWORK VS BASELINE DISTRIBUZIONALE
        	/////////////////////////////////////UNIGRAMMI//////////////////////////
        	if(v3.getTecnica().equals("pmi")) {
            if (v3.getConfigurazione().equals("unigrammi")){
                totaliUni++;
                
                if (v3.getTrasparenza() == 1){
                    centroideUni.put("Trasparenza", centroideUni.get("Trasparenza")+1);
                } else if (v3.getTrasparenza() == 2){
                    singoleUni.put("Trasparenza", 	singoleUni.get("Trasparenza")+1);
                } else {
                    indifferentiUni.put("Trasparenza", indifferentiUni.get("Trasparenza")+1);
                }
                
                if (v3.getPersuasione() == 1){
                    centroideUni.put("Persuasione", centroideUni.get("Persuasione")+1);
                } else if (v3.getPersuasione() == 2){
                    singoleUni.put("Persuasione", singoleUni.get("Persuasione")+1);
                } else {
                    indifferentiUni.put("Persuasione", indifferentiUni.get("Persuasione")+1);
                }

                if (v3.getCoinvolgimento() == 1){
                    centroideUni.put("Coinvolgimento", centroideUni.get("Coinvolgimento")+1);
                } else if (v3.getCoinvolgimento() == 2){
                    singoleUni.put("Coinvolgimento", singoleUni.get("Coinvolgimento")+1);
                } else {
                    indifferentiUni.put("Coinvolgimento", indifferentiUni.get("Coinvolgimento")+1);
                }

                if (v3.getFiducia() == 1){
                    centroideUni.put("Fiducia", centroideUni.get("Fiducia")+1);
                } else if (v3.getFiducia() == 2){
                    singoleUni.put("Fiducia", singoleUni.get("Fiducia")+1);
                } else {
                    indifferentiUni.put("Fiducia", indifferentiUni.get("Fiducia")+1);
                }

            } 
            /////////////////////////////////////BIGRAMMI//////////////////////////
            else if (v3.getConfigurazione().equals("bigrammi")){
                totaliBi++;
                
                if (v3.getTrasparenza() == 1){
                    centroideBi.put("Trasparenza", centroideBi.get("Trasparenza")+1);
                } else if (v3.getTrasparenza() == 2){
                    singoleBi.put("Trasparenza", singoleBi.get("Trasparenza")+1);
                } else {
                    indifferentiBi.put("Trasparenza", indifferentiBi.get("Trasparenza")+1);
                }

                if (v3.getPersuasione() == 1){
                    centroideBi.put("Persuasione", centroideBi.get("Persuasione")+1);
                } else if (v3.getPersuasione() == 2){
                    singoleBi.put("Persuasione", singoleBi.get("Persuasione")+1);
                } else {
                    indifferentiBi.put("Persuasione", indifferentiBi.get("Persuasione")+1);
                }

                if (v3.getCoinvolgimento() == 1){
                    centroideBi.put("Coinvolgimento", centroideBi.get("Coinvolgimento")+1);
                } else if (v3.getCoinvolgimento() == 2){
                    singoleBi.put("Coinvolgimento", singoleBi.get("Coinvolgimento")+1);
                } else {
                    indifferentiBi.put("Coinvolgimento", indifferentiBi.get("Coinvolgimento")+1);
                }

                if (v3.getFiducia() == 1){
                    centroideBi.put("Fiducia", centroideBi.get("Fiducia")+1);
                } else if (v3.getFiducia() == 2){
                    singoleBi.put("Fiducia", singoleBi.get("Fiducia")+1);
                } else {
                    indifferentiBi.put("Fiducia", indifferentiBi.get("Fiducia")+1);
                }

            } 
            /////////////////////////////////////UNIBIGRAMMI//////////////////////////
            else if (v3.getConfigurazione().equals("unibigrammi")){
                totaliUniBi++;

                if (v3.getTrasparenza() == 1){
                    centroideUniBi.put("Trasparenza", centroideUniBi.get("Trasparenza")+1);
                } else if (v3.getTrasparenza() == 2){
                    singoleUniBi.put("Trasparenza", singoleUniBi.get("Trasparenza")+1);
                } else {
                    indifferentiUniBi.put("Trasparenza", indifferentiUniBi.get("Trasparenza")+1);
                }

                if (v3.getPersuasione() == 1){
                    centroideUniBi.put("Persuasione", centroideUniBi.get("Persuasione")+1);
                } else if (v3.getPersuasione() == 2){
                    singoleUniBi.put("Persuasione", singoleUniBi.get("Persuasione")+1);
                } else {
                    indifferentiUniBi.put("Persuasione", indifferentiUniBi.get("Persuasione")+1);
                }

                if (v3.getCoinvolgimento() == 1){
                    centroideUniBi.put("Coinvolgimento", centroideUniBi.get("Coinvolgimento")+1);
                } else if (v3.getCoinvolgimento() == 2){
                    singoleUniBi.put("Coinvolgimento", singoleUniBi.get("Coinvolgimento")+1);
                } else {
                    indifferentiUniBi.put("Coinvolgimento", indifferentiUniBi.get("Coinvolgimento")+1);
                }

                if (v3.getFiducia() == 1){
                    centroideUniBi.put("Fiducia", centroideUniBi.get("Fiducia")+1);
                } else if (v3.getFiducia() == 2){
                    singoleUniBi.put("Fiducia", singoleUniBi.get("Fiducia")+1);
                } else {
                    indifferentiUniBi.put("Fiducia", indifferentiUniBi.get("Fiducia")+1);
                }
                
            }
          }
        }
        ///////////////////////////////
        if (totaliUni != 0){
            centroideUni.put("Trasparenza", centroideUni.get("Trasparenza")/totaliUni);
            centroideUni.put("Persuasione", centroideUni.get("Persuasione")/totaliUni);
            centroideUni.put("Coinvolgimento", centroideUni.get("Coinvolgimento")/totaliUni);
            centroideUni.put("Fiducia", centroideUni.get("Fiducia")/totaliUni);
            
            singoleUni.put("Trasparenza", singoleUni.get("Trasparenza")/totaliUni);
            singoleUni.put("Persuasione", singoleUni.get("Persuasione")/totaliUni);
            singoleUni.put("Coinvolgimento", singoleUni.get("Coinvolgimento")/totaliUni);
            singoleUni.put("Fiducia", singoleUni.get("Fiducia")/totaliUni);
            
            indifferentiUni.put("Trasparenza", indifferentiUni.get("Trasparenza")/totaliUni);
            indifferentiUni.put("Persuasione", indifferentiUni.get("Persuasione")/totaliUni);
            indifferentiUni.put("Coinvolgimento", indifferentiUni.get("Coinvolgimento")/totaliUni);
            indifferentiUni.put("Fiducia", indifferentiUni.get("Fiducia")/totaliUni);
        }
        ///////////////////////////////
        if (totaliBi != 0){
            centroideBi.put("Trasparenza", centroideBi.get("Trasparenza")/totaliBi);
            centroideBi.put("Persuasione", centroideBi.get("Persuasione")/totaliBi);
            centroideBi.put("Coinvolgimento", centroideBi.get("Coinvolgimento")/totaliBi);
            centroideBi.put("Fiducia", centroideBi.get("Fiducia")/totaliBi);
            
            singoleBi.put("Trasparenza", singoleBi.get("Trasparenza")/totaliBi);
            singoleBi.put("Persuasione", singoleBi.get("Persuasione")/totaliBi);
            singoleBi.put("Coinvolgimento", singoleBi.get("Coinvolgimento")/totaliBi);
            singoleBi.put("Fiducia", singoleBi.get("Fiducia")/totaliBi);
            
            indifferentiBi.put("Trasparenza", indifferentiBi.get("Trasparenza")/totaliBi);
            indifferentiBi.put("Persuasione", indifferentiBi.get("Persuasione")/totaliBi);
            indifferentiBi.put("Coinvolgimento", indifferentiBi.get("Coinvolgimento")/totaliBi);
            indifferentiBi.put("Fiducia", indifferentiBi.get("Fiducia")/totaliBi);
        }
        ///////////////////////////////
        if (totaliUniBi != 0){
            centroideUniBi.put("Trasparenza", centroideUniBi.get("Trasparenza")/totaliUniBi);
            centroideUniBi.put("Persuasione", centroideUniBi.get("Persuasione")/totaliUniBi);
            centroideUniBi.put("Coinvolgimento", centroideUniBi.get("Coinvolgimento")/totaliUniBi);
            centroideUniBi.put("Fiducia", centroideUniBi.get("Fiducia")/totaliUniBi);
            
            singoleUniBi.put("Trasparenza", singoleUniBi.get("Trasparenza")/totaliUniBi);
            singoleUniBi.put("Persuasione", singoleUniBi.get("Persuasione")/totaliUniBi);
            singoleUniBi.put("Coinvolgimento", singoleUniBi.get("Coinvolgimento")/totaliUniBi);
            singoleUniBi.put("Fiducia", singoleUniBi.get("Fiducia")/totaliUniBi);
            
            indifferentiUniBi.put("Trasparenza", indifferentiUniBi.get("Trasparenza")/totaliUniBi);
            indifferentiUniBi.put("Persuasione", indifferentiUniBi.get("Persuasione")/totaliUniBi);
            indifferentiUniBi.put("Coinvolgimento", indifferentiUniBi.get("Coinvolgimento")/totaliUniBi);
            indifferentiUniBi.put("Fiducia", indifferentiUniBi.get("Fiducia")/totaliUniBi);
        }
        
        uni.put("sistema", centroideUni);
        uni.put("baselineDistr", singoleUni);
        uni.put("indifferenti", indifferentiUni);

        bi.put("sistema", centroideBi);
        bi.put("baselineDistr", singoleBi);
        bi.put("indifferenti", indifferentiBi);

        unibi.put("sistema", centroideUniBi);
        unibi.put("baselineDistr", singoleUniBi);
        unibi.put("indifferenti", indifferentiUniBi);
      ///////////////////////////////////////////////////
        esiti.put("unigrammi", uni);
        esiti.put("bigrammi", bi);
        esiti.put("unibigrammi", unibi);
        return esiti;
    }
    

    //8) preferenze tra SISTEMA e BASELINE DISTRIBUZIONALE in base al NUMERO DI CONTESTI;
    // centroide vs singole - preferenza in base al numero di contesti
    public static HashMap<Integer, HashMap<String, Double>> centroideVSsingoleNumContesti(){
        HashMap<Integer, HashMap<String, Double>> preferenzaNumeroContesti = new HashMap<Integer, HashMap<String, Double>>();
        int centroide1 = 0, centroide2 = 0, centroide3 = 0,
                singole1 = 0, singole2 = 0, singole3  = 0,
                indiff1 = 0, indiff2 = 0, indiff3  = 0,
                totale1 = 0, totale2 = 0, totale3 = 0;

        for (ValutazioneTipo3 v3 : Report.valutazioni3){//SING VS CENTROIDE
            if (v3.getNumeroContesti() == 1){
                totale1++;
                if (v3.getPreferenza0() == 1){
                    centroide1++;//SIST
                } else if (v3.getPreferenza0() == 2){
                    singole1++;//BASELINE D
                } else {
                    indiff1++;
                }
            } 
            else if (v3.getNumeroContesti() == 2){
                totale2++;
                if (v3.getPreferenza0() == 1){
                    centroide2++;
                } else if (v3.getPreferenza0() == 2){
                    singole2++;
                } else {
                    indiff2++;
                }
            } 
            else if (v3.getNumeroContesti() == 3) {
                totale3++;
                if (v3.getPreferenza0() == 1) {
                    centroide3++;
                } else if (v3.getPreferenza0() == 2) {
                    singole3++;
                } else {
                    indiff3++;
                }
            }
            
        }

        HashMap<String, Double> contesto1 = new HashMap<String, Double>();
        HashMap<String, Double> contesto2 = new HashMap<String, Double>();
        HashMap<String, Double> contesto3 = new HashMap<String, Double>();

        contesto1.put("sistema", totale1 != 0 ? (double)centroide1/totale1 : 0);
        contesto1.put("baselineDistr", totale1 != 0 ? (double)singole1/totale1 : 0);
        contesto1.put("Indifferente", totale1 != 0 ? (double)indiff1/totale1 : 0);

        contesto2.put("sistema", totale2 != 0 ? (double)centroide2/totale2 : 0);
        contesto2.put("baselineDistr", totale2 != 0 ? (double)singole2/totale2 : 0);
        contesto2.put("Indifferente", totale2 != 0 ? (double)indiff2/totale2 : 0);

        contesto3.put("sistema", totale3 != 0 ? (double)centroide3/totale3 : 0);
        contesto3.put("baselineDistr", totale3 != 0 ? (double)singole3/totale3 : 0);
        contesto3.put("Indifferente", totale3 != 0 ? (double)indiff3/totale3 : 0);

        preferenzaNumeroContesti.put(1, contesto1);
        preferenzaNumeroContesti.put(2, contesto2);
        preferenzaNumeroContesti.put(3, contesto3);

        return preferenzaNumeroContesti;
    }
    
    
    //9) confronto valutazioni metriche FRAMEWORK e BASELINE PUCARIELLO;
    public static HashMap<String, HashMap<String, HashMap<String, Double>>> sistemaVSBaselineMetriche(){
        HashMap<String, HashMap<String, HashMap<String, Double>>> esiti = new HashMap<>();

        HashMap<String, HashMap<String, Double>> uni = new HashMap<>();
        HashMap<String, HashMap<String, Double>> bi = new HashMap<>();
        HashMap<String, HashMap<String, Double>> unibi = new HashMap<>();

        HashMap<String, Double> sistemaUni = new HashMap<>();
        HashMap<String, Double> sistemaBi = new HashMap<>();
        HashMap<String, Double> sistemaUniBi = new HashMap<>();

        HashMap<String, Double> baselineUni = new HashMap<>();
        HashMap<String, Double> baselineBi = new HashMap<>();
        HashMap<String, Double> baselineUniBi = new HashMap<>();

        HashMap<String, Double> indifferentiUni = new HashMap<>();
        HashMap<String, Double> indifferentiBi = new HashMap<>();
        HashMap<String, Double> indifferentiUniBi = new HashMap<>();

        baselineUni.put("Trasparenza", 0.0);
        baselineUni.put("Persuasione", 0.0);
        baselineUni.put("Coinvolgimento", 0.0);
        baselineUni.put("Fiducia", 0.0);
        
        baselineBi.put("Trasparenza", 0.0);
        baselineBi.put("Persuasione", 0.0);
        baselineBi.put("Coinvolgimento", 0.0);
        baselineBi.put("Fiducia", 0.0);
        
        baselineUniBi.put("Trasparenza", 0.0);
        baselineUniBi.put("Persuasione", 0.0);
        baselineUniBi.put("Coinvolgimento", 0.0);
        baselineUniBi.put("Fiducia", 0.0);

        sistemaUni.put("Trasparenza", 0.0);
        sistemaUni.put("Persuasione", 0.0);
        sistemaUni.put("Coinvolgimento", 0.0);
        sistemaUni.put("Fiducia", 0.0);
        
        sistemaBi.put("Trasparenza", 0.0);
        sistemaBi.put("Persuasione", 0.0);
        sistemaBi.put("Coinvolgimento", 0.0);
        sistemaBi.put("Fiducia", 0.0);
        
        sistemaUniBi.put("Trasparenza", 0.0);
        sistemaUniBi.put("Persuasione", 0.0);
        sistemaUniBi.put("Coinvolgimento", 0.0);
        sistemaUniBi.put("Fiducia", 0.0);

        indifferentiUni.put("Trasparenza", 0.0);
        indifferentiUni.put("Persuasione", 0.0);
        indifferentiUni.put("Coinvolgimento", 0.0);
        indifferentiUni.put("Fiducia", 0.0);
        
        indifferentiBi.put("Trasparenza", 0.0);
        indifferentiBi.put("Persuasione", 0.0);
        indifferentiBi.put("Coinvolgimento", 0.0);
        indifferentiBi.put("Fiducia", 0.0);
        
        indifferentiUniBi.put("Trasparenza", 0.0);
        indifferentiUniBi.put("Persuasione", 0.0);
        indifferentiUniBi.put("Coinvolgimento", 0.0);
        indifferentiUniBi.put("Fiducia", 0.0);

        int totaliUni = 0, totaliBi = 0, totaliUniBi = 0;

        for (ValutazioneTipo4 v4 : Report.valutazioni4){	//INDISTINTAMENTE CENTROIDE FRASI SINGOLE
        	if (v4.getConfigurazione().equals("unigrammi")){
                totaliUni++;
                if (v4.getTrasparenza() == 1){
                    sistemaUni.put("Trasparenza", sistemaUni.get("Trasparenza")+1);
                } else if (v4.getTrasparenza() == 2){
                    baselineUni.put("Trasparenza", baselineUni.get("Trasparenza")+1);
                } else {
                    indifferentiUni.put("Trasparenza", indifferentiUni.get("Trasparenza")+1);
                }

                if (v4.getPersuasione() == 1){
                    sistemaUni.put("Persuasione", sistemaUni.get("Persuasione")+1);
                } else if (v4.getPersuasione() == 2){
                    baselineUni.put("Persuasione", baselineUni.get("Persuasione")+1);
                } else {
                    indifferentiUni.put("Persuasione", indifferentiUni.get("Persuasione")+1);
                }

                if (v4.getCoinvolgimento() == 1){
                    sistemaUni.put("Coinvolgimento", sistemaUni.get("Coinvolgimento")+1);
                } else if (v4.getCoinvolgimento() == 2){
                    baselineUni.put("Coinvolgimento", baselineUni.get("Coinvolgimento")+1);
                } else {
                    indifferentiUni.put("Coinvolgimento", indifferentiUni.get("Coinvolgimento")+1);
                }

                if (v4.getFiducia() == 1){
                    sistemaUni.put("Fiducia", sistemaUni.get("Fiducia")+1);
                } else if (v4.getFiducia() == 2){
                    baselineUni.put("Fiducia", baselineUni.get("Fiducia")+1);
                } else {
                    indifferentiUni.put("Fiducia", indifferentiUni.get("Fiducia")+1);
                }

            } 
            
            else if (v4.getConfigurazione().equals("bigrammi")){
                totaliBi++;
                if (v4.getTrasparenza() == 1){
                    sistemaBi.put("Trasparenza", sistemaBi.get("Trasparenza")+1);
                } else if (v4.getTrasparenza() == 2){
                    baselineBi.put("Trasparenza", baselineBi.get("Trasparenza")+1);
                } else {
                    indifferentiBi.put("Trasparenza", indifferentiBi.get("Trasparenza")+1);
                }

                if (v4.getPersuasione() == 1){
                    sistemaBi.put("Persuasione", sistemaBi.get("Persuasione")+1);
                } else if (v4.getPersuasione() == 2){
                    baselineBi.put("Persuasione", baselineBi.get("Persuasione")+1);
                } else {
                    indifferentiBi.put("Persuasione", indifferentiBi.get("Persuasione")+1);

                }

                if (v4.getCoinvolgimento() == 1){
                    sistemaBi.put("Coinvolgimento", sistemaBi.get("Coinvolgimento")+1);
                } else if (v4.getCoinvolgimento() == 2){
                    baselineBi.put("Coinvolgimento", baselineBi.get("Coinvolgimento")+1);
                } else {
                    indifferentiBi.put("Coinvolgimento", indifferentiBi.get("Coinvolgimento")+1);
                }

                if (v4.getFiducia() == 1){
                    sistemaBi.put("Fiducia", sistemaBi.get("Fiducia")+1);
                } else if (v4.getFiducia() == 2){
                    baselineBi.put("Fiducia", baselineBi.get("Fiducia")+1);
                } else {
                    indifferentiBi.put("Fiducia", indifferentiBi.get("Fiducia")+1);
                }

            } 
            
            else if (v4.getConfigurazione().equals("unibigrammi")){
                totaliUniBi++;
                if (v4.getTrasparenza() == 1){
                    sistemaUniBi.put("Trasparenza", sistemaUniBi.get("Trasparenza")+1);
                } else if (v4.getTrasparenza() == 2){
                    baselineUniBi.put("Trasparenza", baselineUniBi.get("Trasparenza")+1);
                } else {
                    indifferentiUniBi.put("Trasparenza", indifferentiUniBi.get("Trasparenza")+1);
                }

                if (v4.getPersuasione() == 1){
                    sistemaUniBi.put("Persuasione", sistemaUniBi.get("Persuasione")+1);
                } else if (v4.getPersuasione() == 2){
                    baselineUniBi.put("Persuasione", baselineUniBi.get("Persuasione")+1);
                } else {
                    indifferentiUniBi.put("Persuasione", indifferentiUniBi.get("Persuasione")+1);
                }

                if (v4.getCoinvolgimento() == 1){
                    sistemaUniBi.put("Coinvolgimento", sistemaUniBi.get("Coinvolgimento")+1);
                } else if (v4.getCoinvolgimento() == 2){
                    baselineUniBi.put("Coinvolgimento", baselineUniBi.get("Coinvolgimento")+1);
                } else {
                    indifferentiUniBi.put("Coinvolgimento", indifferentiUniBi.get("Coinvolgimento")+1);
                }

                if (v4.getFiducia() == 1){
                    sistemaUniBi.put("Fiducia", sistemaUniBi.get("Fiducia")+1);
                } else if (v4.getFiducia() == 2){
                    baselineUniBi.put("Fiducia", baselineUniBi.get("Fiducia")+1);
                } else {
                    indifferentiUniBi.put("Fiducia", indifferentiUniBi.get("Fiducia")+1);
                }
            }
        }
        
        //////////////////////////////////////////////////////////////
        if (totaliUni != 0){
            sistemaUni.put("Trasparenza", sistemaUni.get("Trasparenza")/totaliUni);
            sistemaUni.put("Persuasione", sistemaUni.get("Persuasione")/totaliUni);
            sistemaUni.put("Coinvolgimento", sistemaUni.get("Coinvolgimento")/totaliUni);
            sistemaUni.put("Fiducia", sistemaUni.get("Fiducia")/totaliUni);
            
            baselineUni.put("Trasparenza", baselineUni.get("Trasparenza")/totaliUni);
            baselineUni.put("Persuasione", baselineUni.get("Persuasione")/totaliUni);
            baselineUni.put("Coinvolgimento", baselineUni.get("Coinvolgimento")/totaliUni);
            baselineUni.put("Fiducia", baselineUni.get("Fiducia")/totaliUni);
            
            indifferentiUni.put("Trasparenza", indifferentiUni.get("Trasparenza")/totaliUni);
            indifferentiUni.put("Persuasione", indifferentiUni.get("Persuasione")/totaliUni);
            indifferentiUni.put("Coinvolgimento", indifferentiUni.get("Coinvolgimento")/totaliUni);
            indifferentiUni.put("Fiducia", indifferentiUni.get("Fiducia")/totaliUni);
        }
        /////////////////////
        if (totaliBi != 0){
            sistemaBi.put("Trasparenza", sistemaBi.get("Trasparenza")/totaliBi);
            sistemaBi.put("Persuasione", sistemaBi.get("Persuasione")/totaliBi);
            sistemaBi.put("Coinvolgimento", sistemaBi.get("Coinvolgimento")/totaliBi);
            sistemaBi.put("Fiducia", sistemaBi.get("Fiducia")/totaliBi);
            
            baselineBi.put("Trasparenza", baselineBi.get("Trasparenza")/totaliBi);
            baselineBi.put("Persuasione", baselineBi.get("Persuasione")/totaliBi);
            baselineBi.put("Coinvolgimento", baselineBi.get("Coinvolgimento")/totaliBi);
            baselineBi.put("Fiducia", baselineBi.get("Fiducia")/totaliBi);
            
            indifferentiBi.put("Trasparenza", indifferentiBi.get("Trasparenza")/totaliBi);
            indifferentiBi.put("Persuasione", indifferentiBi.get("Persuasione")/totaliBi);
            indifferentiBi.put("Coinvolgimento", indifferentiBi.get("Coinvolgimento")/totaliBi);
            indifferentiBi.put("Fiducia", indifferentiBi.get("Fiducia")/totaliBi);
        }
        /////////////////////////////////
        if (totaliUniBi != 0){
            sistemaUniBi.put("Trasparenza", sistemaUniBi.get("Trasparenza")/totaliUniBi);
            sistemaUniBi.put("Persuasione", sistemaUniBi.get("Persuasione")/totaliUniBi);
            sistemaUniBi.put("Coinvolgimento", sistemaUniBi.get("Coinvolgimento")/totaliUniBi);
            sistemaUniBi.put("Fiducia", sistemaUniBi.get("Fiducia")/totaliUniBi);
            
            baselineUniBi.put("Trasparenza", baselineUniBi.get("Trasparenza")/totaliUniBi);
            baselineUniBi.put("Persuasione", baselineUniBi.get("Persuasione")/totaliUniBi);
            baselineUniBi.put("Coinvolgimento", baselineUniBi.get("Coinvolgimento")/totaliUniBi);
            baselineUniBi.put("Fiducia", baselineUniBi.get("Fiducia")/totaliUniBi);
            
            indifferentiUniBi.put("Trasparenza", indifferentiUniBi.get("Trasparenza")/totaliUniBi);
            indifferentiUniBi.put("Persuasione", indifferentiUniBi.get("Persuasione")/totaliUniBi);
            indifferentiUniBi.put("Coinvolgimento", indifferentiUniBi.get("Coinvolgimento")/totaliUniBi);
            indifferentiUniBi.put("Fiducia", indifferentiUniBi.get("Fiducia")/totaliUniBi);
        }
        
        uni.put("sistema", sistemaUni);
        uni.put("baseline", baselineUni);
        uni.put("indifferenti", indifferentiUni);

        bi.put("sistema", sistemaBi);
        bi.put("baseline", baselineBi);
        bi.put("indifferenti", indifferentiBi);

        unibi.put("sistema", sistemaUniBi);
        unibi.put("baseline", baselineUniBi);
        unibi.put("indifferenti", indifferentiUniBi);
        ////////////////////////////////////////////////////
        esiti.put("unigrammi", uni);
        esiti.put("bigrammi", bi);
        esiti.put("unibigrammi", unibi);

        return esiti;
    }

    //9) confronto valutazioni metriche FRAMEWORK e BASELINE PUCARIELLO;
    public static HashMap<String, HashMap<String, HashMap<String, Double>>> sistemaVSBaselineMetricheNormale(){
        HashMap<String, HashMap<String, HashMap<String, Double>>> esiti = new HashMap<>();

        HashMap<String, HashMap<String, Double>> uni = new HashMap<>();
        HashMap<String, HashMap<String, Double>> bi = new HashMap<>();
        HashMap<String, HashMap<String, Double>> unibi = new HashMap<>();

        HashMap<String, Double> sistemaUni = new HashMap<>();
        HashMap<String, Double> sistemaBi = new HashMap<>();
        HashMap<String, Double> sistemaUniBi = new HashMap<>();

        HashMap<String, Double> baselineUni = new HashMap<>();
        HashMap<String, Double> baselineBi = new HashMap<>();
        HashMap<String, Double> baselineUniBi = new HashMap<>();

        HashMap<String, Double> indifferentiUni = new HashMap<>();
        HashMap<String, Double> indifferentiBi = new HashMap<>();
        HashMap<String, Double> indifferentiUniBi = new HashMap<>();

        baselineUni.put("Trasparenza", 0.0);
        baselineUni.put("Persuasione", 0.0);
        baselineUni.put("Coinvolgimento", 0.0);
        baselineUni.put("Fiducia", 0.0);
        
        baselineBi.put("Trasparenza", 0.0);
        baselineBi.put("Persuasione", 0.0);
        baselineBi.put("Coinvolgimento", 0.0);
        baselineBi.put("Fiducia", 0.0);
        
        baselineUniBi.put("Trasparenza", 0.0);
        baselineUniBi.put("Persuasione", 0.0);
        baselineUniBi.put("Coinvolgimento", 0.0);
        baselineUniBi.put("Fiducia", 0.0);

        sistemaUni.put("Trasparenza", 0.0);
        sistemaUni.put("Persuasione", 0.0);
        sistemaUni.put("Coinvolgimento", 0.0);
        sistemaUni.put("Fiducia", 0.0);
        
        sistemaBi.put("Trasparenza", 0.0);
        sistemaBi.put("Persuasione", 0.0);
        sistemaBi.put("Coinvolgimento", 0.0);
        sistemaBi.put("Fiducia", 0.0);
        
        sistemaUniBi.put("Trasparenza", 0.0);
        sistemaUniBi.put("Persuasione", 0.0);
        sistemaUniBi.put("Coinvolgimento", 0.0);
        sistemaUniBi.put("Fiducia", 0.0);

        indifferentiUni.put("Trasparenza", 0.0);
        indifferentiUni.put("Persuasione", 0.0);
        indifferentiUni.put("Coinvolgimento", 0.0);
        indifferentiUni.put("Fiducia", 0.0);
        
        indifferentiBi.put("Trasparenza", 0.0);
        indifferentiBi.put("Persuasione", 0.0);
        indifferentiBi.put("Coinvolgimento", 0.0);
        indifferentiBi.put("Fiducia", 0.0);
        
        indifferentiUniBi.put("Trasparenza", 0.0);
        indifferentiUniBi.put("Persuasione", 0.0);
        indifferentiUniBi.put("Coinvolgimento", 0.0);
        indifferentiUniBi.put("Fiducia", 0.0);

        int totaliUni = 0, totaliBi = 0, totaliUniBi = 0;

        for (ValutazioneTipo4 v4 : Report.valutazioni4){	//INDISTINTAMENTE CENTROIDE FRASI SINGOLE
          if(v4.getTecnica().equals("normale")){
        	if (v4.getConfigurazione().equals("unigrammi")){
                totaliUni++;
                if (v4.getTrasparenza() == 1){
                    sistemaUni.put("Trasparenza", sistemaUni.get("Trasparenza")+1);
                } else if (v4.getTrasparenza() == 2){
                    baselineUni.put("Trasparenza", baselineUni.get("Trasparenza")+1);
                } else {
                    indifferentiUni.put("Trasparenza", indifferentiUni.get("Trasparenza")+1);
                }

                if (v4.getPersuasione() == 1){
                    sistemaUni.put("Persuasione", sistemaUni.get("Persuasione")+1);
                } else if (v4.getPersuasione() == 2){
                    baselineUni.put("Persuasione", baselineUni.get("Persuasione")+1);
                } else {
                    indifferentiUni.put("Persuasione", indifferentiUni.get("Persuasione")+1);
                }

                if (v4.getCoinvolgimento() == 1){
                    sistemaUni.put("Coinvolgimento", sistemaUni.get("Coinvolgimento")+1);
                } else if (v4.getCoinvolgimento() == 2){
                    baselineUni.put("Coinvolgimento", baselineUni.get("Coinvolgimento")+1);
                } else {
                    indifferentiUni.put("Coinvolgimento", indifferentiUni.get("Coinvolgimento")+1);
                }

                if (v4.getFiducia() == 1){
                    sistemaUni.put("Fiducia", sistemaUni.get("Fiducia")+1);
                } else if (v4.getFiducia() == 2){
                    baselineUni.put("Fiducia", baselineUni.get("Fiducia")+1);
                } else {
                    indifferentiUni.put("Fiducia", indifferentiUni.get("Fiducia")+1);
                }

            } 
            
            else if (v4.getConfigurazione().equals("bigrammi")){
                totaliBi++;
                if (v4.getTrasparenza() == 1){
                    sistemaBi.put("Trasparenza", sistemaBi.get("Trasparenza")+1);
                } else if (v4.getTrasparenza() == 2){
                    baselineBi.put("Trasparenza", baselineBi.get("Trasparenza")+1);
                } else {
                    indifferentiBi.put("Trasparenza", indifferentiBi.get("Trasparenza")+1);
                }

                if (v4.getPersuasione() == 1){
                    sistemaBi.put("Persuasione", sistemaBi.get("Persuasione")+1);
                } else if (v4.getPersuasione() == 2){
                    baselineBi.put("Persuasione", baselineBi.get("Persuasione")+1);
                } else {
                    indifferentiBi.put("Persuasione", indifferentiBi.get("Persuasione")+1);

                }

                if (v4.getCoinvolgimento() == 1){
                    sistemaBi.put("Coinvolgimento", sistemaBi.get("Coinvolgimento")+1);
                } else if (v4.getCoinvolgimento() == 2){
                    baselineBi.put("Coinvolgimento", baselineBi.get("Coinvolgimento")+1);
                } else {
                    indifferentiBi.put("Coinvolgimento", indifferentiBi.get("Coinvolgimento")+1);
                }

                if (v4.getFiducia() == 1){
                    sistemaBi.put("Fiducia", sistemaBi.get("Fiducia")+1);
                } else if (v4.getFiducia() == 2){
                    baselineBi.put("Fiducia", baselineBi.get("Fiducia")+1);
                } else {
                    indifferentiBi.put("Fiducia", indifferentiBi.get("Fiducia")+1);
                }

            } 
            
            else if (v4.getConfigurazione().equals("unibigrammi")){
                totaliUniBi++;
                if (v4.getTrasparenza() == 1){
                    sistemaUniBi.put("Trasparenza", sistemaUniBi.get("Trasparenza")+1);
                } else if (v4.getTrasparenza() == 2){
                    baselineUniBi.put("Trasparenza", baselineUniBi.get("Trasparenza")+1);
                } else {
                    indifferentiUniBi.put("Trasparenza", indifferentiUniBi.get("Trasparenza")+1);
                }

                if (v4.getPersuasione() == 1){
                    sistemaUniBi.put("Persuasione", sistemaUniBi.get("Persuasione")+1);
                } else if (v4.getPersuasione() == 2){
                    baselineUniBi.put("Persuasione", baselineUniBi.get("Persuasione")+1);
                } else {
                    indifferentiUniBi.put("Persuasione", indifferentiUniBi.get("Persuasione")+1);
                }

                if (v4.getCoinvolgimento() == 1){
                    sistemaUniBi.put("Coinvolgimento", sistemaUniBi.get("Coinvolgimento")+1);
                } else if (v4.getCoinvolgimento() == 2){
                    baselineUniBi.put("Coinvolgimento", baselineUniBi.get("Coinvolgimento")+1);
                } else {
                    indifferentiUniBi.put("Coinvolgimento", indifferentiUniBi.get("Coinvolgimento")+1);
                }

                if (v4.getFiducia() == 1){
                    sistemaUniBi.put("Fiducia", sistemaUniBi.get("Fiducia")+1);
                } else if (v4.getFiducia() == 2){
                    baselineUniBi.put("Fiducia", baselineUniBi.get("Fiducia")+1);
                } else {
                    indifferentiUniBi.put("Fiducia", indifferentiUniBi.get("Fiducia")+1);
                }
            }
        	
          }
        }
        
        //////////////////////////////////////////////////////////////
        if (totaliUni != 0){
            sistemaUni.put("Trasparenza", sistemaUni.get("Trasparenza")/totaliUni);
            sistemaUni.put("Persuasione", sistemaUni.get("Persuasione")/totaliUni);
            sistemaUni.put("Coinvolgimento", sistemaUni.get("Coinvolgimento")/totaliUni);
            sistemaUni.put("Fiducia", sistemaUni.get("Fiducia")/totaliUni);
            
            baselineUni.put("Trasparenza", baselineUni.get("Trasparenza")/totaliUni);
            baselineUni.put("Persuasione", baselineUni.get("Persuasione")/totaliUni);
            baselineUni.put("Coinvolgimento", baselineUni.get("Coinvolgimento")/totaliUni);
            baselineUni.put("Fiducia", baselineUni.get("Fiducia")/totaliUni);
            
            indifferentiUni.put("Trasparenza", indifferentiUni.get("Trasparenza")/totaliUni);
            indifferentiUni.put("Persuasione", indifferentiUni.get("Persuasione")/totaliUni);
            indifferentiUni.put("Coinvolgimento", indifferentiUni.get("Coinvolgimento")/totaliUni);
            indifferentiUni.put("Fiducia", indifferentiUni.get("Fiducia")/totaliUni);
        }
        /////////////////////
        if (totaliBi != 0){
            sistemaBi.put("Trasparenza", sistemaBi.get("Trasparenza")/totaliBi);
            sistemaBi.put("Persuasione", sistemaBi.get("Persuasione")/totaliBi);
            sistemaBi.put("Coinvolgimento", sistemaBi.get("Coinvolgimento")/totaliBi);
            sistemaBi.put("Fiducia", sistemaBi.get("Fiducia")/totaliBi);
            
            baselineBi.put("Trasparenza", baselineBi.get("Trasparenza")/totaliBi);
            baselineBi.put("Persuasione", baselineBi.get("Persuasione")/totaliBi);
            baselineBi.put("Coinvolgimento", baselineBi.get("Coinvolgimento")/totaliBi);
            baselineBi.put("Fiducia", baselineBi.get("Fiducia")/totaliBi);
            
            indifferentiBi.put("Trasparenza", indifferentiBi.get("Trasparenza")/totaliBi);
            indifferentiBi.put("Persuasione", indifferentiBi.get("Persuasione")/totaliBi);
            indifferentiBi.put("Coinvolgimento", indifferentiBi.get("Coinvolgimento")/totaliBi);
            indifferentiBi.put("Fiducia", indifferentiBi.get("Fiducia")/totaliBi);
        }
        /////////////////////////////////
        if (totaliUniBi != 0){
            sistemaUniBi.put("Trasparenza", sistemaUniBi.get("Trasparenza")/totaliUniBi);
            sistemaUniBi.put("Persuasione", sistemaUniBi.get("Persuasione")/totaliUniBi);
            sistemaUniBi.put("Coinvolgimento", sistemaUniBi.get("Coinvolgimento")/totaliUniBi);
            sistemaUniBi.put("Fiducia", sistemaUniBi.get("Fiducia")/totaliUniBi);
            
            baselineUniBi.put("Trasparenza", baselineUniBi.get("Trasparenza")/totaliUniBi);
            baselineUniBi.put("Persuasione", baselineUniBi.get("Persuasione")/totaliUniBi);
            baselineUniBi.put("Coinvolgimento", baselineUniBi.get("Coinvolgimento")/totaliUniBi);
            baselineUniBi.put("Fiducia", baselineUniBi.get("Fiducia")/totaliUniBi);
            
            indifferentiUniBi.put("Trasparenza", indifferentiUniBi.get("Trasparenza")/totaliUniBi);
            indifferentiUniBi.put("Persuasione", indifferentiUniBi.get("Persuasione")/totaliUniBi);
            indifferentiUniBi.put("Coinvolgimento", indifferentiUniBi.get("Coinvolgimento")/totaliUniBi);
            indifferentiUniBi.put("Fiducia", indifferentiUniBi.get("Fiducia")/totaliUniBi);
        }
        
        uni.put("sistema", sistemaUni);
        uni.put("baseline", baselineUni);
        uni.put("indifferenti", indifferentiUni);

        bi.put("sistema", sistemaBi);
        bi.put("baseline", baselineBi);
        bi.put("indifferenti", indifferentiBi);

        unibi.put("sistema", sistemaUniBi);
        unibi.put("baseline", baselineUniBi);
        unibi.put("indifferenti", indifferentiUniBi);
        ////////////////////////////////////////////////////
        esiti.put("unigrammi", uni);
        esiti.put("bigrammi", bi);
        esiti.put("unibigrammi", unibi);

        return esiti;
    }
    
    //9) confronto valutazioni metriche FRAMEWORK e BASELINE PUCARIELLO;
    public static HashMap<String, HashMap<String, HashMap<String, Double>>> sistemaVSBaselineMetrichePMI(){
        HashMap<String, HashMap<String, HashMap<String, Double>>> esiti = new HashMap<>();

        HashMap<String, HashMap<String, Double>> uni = new HashMap<>();
        HashMap<String, HashMap<String, Double>> bi = new HashMap<>();
        HashMap<String, HashMap<String, Double>> unibi = new HashMap<>();

        HashMap<String, Double> sistemaUni = new HashMap<>();
        HashMap<String, Double> sistemaBi = new HashMap<>();
        HashMap<String, Double> sistemaUniBi = new HashMap<>();

        HashMap<String, Double> baselineUni = new HashMap<>();
        HashMap<String, Double> baselineBi = new HashMap<>();
        HashMap<String, Double> baselineUniBi = new HashMap<>();

        HashMap<String, Double> indifferentiUni = new HashMap<>();
        HashMap<String, Double> indifferentiBi = new HashMap<>();
        HashMap<String, Double> indifferentiUniBi = new HashMap<>();

        baselineUni.put("Trasparenza", 0.0);
        baselineUni.put("Persuasione", 0.0);
        baselineUni.put("Coinvolgimento", 0.0);
        baselineUni.put("Fiducia", 0.0);
        
        baselineBi.put("Trasparenza", 0.0);
        baselineBi.put("Persuasione", 0.0);
        baselineBi.put("Coinvolgimento", 0.0);
        baselineBi.put("Fiducia", 0.0);
        
        baselineUniBi.put("Trasparenza", 0.0);
        baselineUniBi.put("Persuasione", 0.0);
        baselineUniBi.put("Coinvolgimento", 0.0);
        baselineUniBi.put("Fiducia", 0.0);

        sistemaUni.put("Trasparenza", 0.0);
        sistemaUni.put("Persuasione", 0.0);
        sistemaUni.put("Coinvolgimento", 0.0);
        sistemaUni.put("Fiducia", 0.0);
        
        sistemaBi.put("Trasparenza", 0.0);
        sistemaBi.put("Persuasione", 0.0);
        sistemaBi.put("Coinvolgimento", 0.0);
        sistemaBi.put("Fiducia", 0.0);
        
        sistemaUniBi.put("Trasparenza", 0.0);
        sistemaUniBi.put("Persuasione", 0.0);
        sistemaUniBi.put("Coinvolgimento", 0.0);
        sistemaUniBi.put("Fiducia", 0.0);

        indifferentiUni.put("Trasparenza", 0.0);
        indifferentiUni.put("Persuasione", 0.0);
        indifferentiUni.put("Coinvolgimento", 0.0);
        indifferentiUni.put("Fiducia", 0.0);
        
        indifferentiBi.put("Trasparenza", 0.0);
        indifferentiBi.put("Persuasione", 0.0);
        indifferentiBi.put("Coinvolgimento", 0.0);
        indifferentiBi.put("Fiducia", 0.0);
        
        indifferentiUniBi.put("Trasparenza", 0.0);
        indifferentiUniBi.put("Persuasione", 0.0);
        indifferentiUniBi.put("Coinvolgimento", 0.0);
        indifferentiUniBi.put("Fiducia", 0.0);

        int totaliUni = 0, totaliBi = 0, totaliUniBi = 0;

        for (ValutazioneTipo4 v4 : Report.valutazioni4){	//INDISTINTAMENTE CENTROIDE FRASI SINGOLE
          if(v4.getTecnica().equals("pmi")){
        	if (v4.getConfigurazione().equals("unigrammi")){
                totaliUni++;
                if (v4.getTrasparenza() == 1){
                    sistemaUni.put("Trasparenza", sistemaUni.get("Trasparenza")+1);
                } else if (v4.getTrasparenza() == 2){
                    baselineUni.put("Trasparenza", baselineUni.get("Trasparenza")+1);
                } else {
                    indifferentiUni.put("Trasparenza", indifferentiUni.get("Trasparenza")+1);
                }

                if (v4.getPersuasione() == 1){
                    sistemaUni.put("Persuasione", sistemaUni.get("Persuasione")+1);
                } else if (v4.getPersuasione() == 2){
                    baselineUni.put("Persuasione", baselineUni.get("Persuasione")+1);
                } else {
                    indifferentiUni.put("Persuasione", indifferentiUni.get("Persuasione")+1);
                }

                if (v4.getCoinvolgimento() == 1){
                    sistemaUni.put("Coinvolgimento", sistemaUni.get("Coinvolgimento")+1);
                } else if (v4.getCoinvolgimento() == 2){
                    baselineUni.put("Coinvolgimento", baselineUni.get("Coinvolgimento")+1);
                } else {
                    indifferentiUni.put("Coinvolgimento", indifferentiUni.get("Coinvolgimento")+1);
                }

                if (v4.getFiducia() == 1){
                    sistemaUni.put("Fiducia", sistemaUni.get("Fiducia")+1);
                } else if (v4.getFiducia() == 2){
                    baselineUni.put("Fiducia", baselineUni.get("Fiducia")+1);
                } else {
                    indifferentiUni.put("Fiducia", indifferentiUni.get("Fiducia")+1);
                }

            } 
            
            else if (v4.getConfigurazione().equals("bigrammi")){
                totaliBi++;
                if (v4.getTrasparenza() == 1){
                    sistemaBi.put("Trasparenza", sistemaBi.get("Trasparenza")+1);
                } else if (v4.getTrasparenza() == 2){
                    baselineBi.put("Trasparenza", baselineBi.get("Trasparenza")+1);
                } else {
                    indifferentiBi.put("Trasparenza", indifferentiBi.get("Trasparenza")+1);
                }

                if (v4.getPersuasione() == 1){
                    sistemaBi.put("Persuasione", sistemaBi.get("Persuasione")+1);
                } else if (v4.getPersuasione() == 2){
                    baselineBi.put("Persuasione", baselineBi.get("Persuasione")+1);
                } else {
                    indifferentiBi.put("Persuasione", indifferentiBi.get("Persuasione")+1);

                }

                if (v4.getCoinvolgimento() == 1){
                    sistemaBi.put("Coinvolgimento", sistemaBi.get("Coinvolgimento")+1);
                } else if (v4.getCoinvolgimento() == 2){
                    baselineBi.put("Coinvolgimento", baselineBi.get("Coinvolgimento")+1);
                } else {
                    indifferentiBi.put("Coinvolgimento", indifferentiBi.get("Coinvolgimento")+1);
                }

                if (v4.getFiducia() == 1){
                    sistemaBi.put("Fiducia", sistemaBi.get("Fiducia")+1);
                } else if (v4.getFiducia() == 2){
                    baselineBi.put("Fiducia", baselineBi.get("Fiducia")+1);
                } else {
                    indifferentiBi.put("Fiducia", indifferentiBi.get("Fiducia")+1);
                }

            } 
            
            else if (v4.getConfigurazione().equals("unibigrammi")){
                totaliUniBi++;
                if (v4.getTrasparenza() == 1){
                    sistemaUniBi.put("Trasparenza", sistemaUniBi.get("Trasparenza")+1);
                } else if (v4.getTrasparenza() == 2){
                    baselineUniBi.put("Trasparenza", baselineUniBi.get("Trasparenza")+1);
                } else {
                    indifferentiUniBi.put("Trasparenza", indifferentiUniBi.get("Trasparenza")+1);
                }

                if (v4.getPersuasione() == 1){
                    sistemaUniBi.put("Persuasione", sistemaUniBi.get("Persuasione")+1);
                } else if (v4.getPersuasione() == 2){
                    baselineUniBi.put("Persuasione", baselineUniBi.get("Persuasione")+1);
                } else {
                    indifferentiUniBi.put("Persuasione", indifferentiUniBi.get("Persuasione")+1);
                }

                if (v4.getCoinvolgimento() == 1){
                    sistemaUniBi.put("Coinvolgimento", sistemaUniBi.get("Coinvolgimento")+1);
                } else if (v4.getCoinvolgimento() == 2){
                    baselineUniBi.put("Coinvolgimento", baselineUniBi.get("Coinvolgimento")+1);
                } else {
                    indifferentiUniBi.put("Coinvolgimento", indifferentiUniBi.get("Coinvolgimento")+1);
                }

                if (v4.getFiducia() == 1){
                    sistemaUniBi.put("Fiducia", sistemaUniBi.get("Fiducia")+1);
                } else if (v4.getFiducia() == 2){
                    baselineUniBi.put("Fiducia", baselineUniBi.get("Fiducia")+1);
                } else {
                    indifferentiUniBi.put("Fiducia", indifferentiUniBi.get("Fiducia")+1);
                }
            }
        	
          }
        }
        
        //////////////////////////////////////////////////////////////
        if (totaliUni != 0){
            sistemaUni.put("Trasparenza", sistemaUni.get("Trasparenza")/totaliUni);
            sistemaUni.put("Persuasione", sistemaUni.get("Persuasione")/totaliUni);
            sistemaUni.put("Coinvolgimento", sistemaUni.get("Coinvolgimento")/totaliUni);
            sistemaUni.put("Fiducia", sistemaUni.get("Fiducia")/totaliUni);
            
            baselineUni.put("Trasparenza", baselineUni.get("Trasparenza")/totaliUni);
            baselineUni.put("Persuasione", baselineUni.get("Persuasione")/totaliUni);
            baselineUni.put("Coinvolgimento", baselineUni.get("Coinvolgimento")/totaliUni);
            baselineUni.put("Fiducia", baselineUni.get("Fiducia")/totaliUni);
            
            indifferentiUni.put("Trasparenza", indifferentiUni.get("Trasparenza")/totaliUni);
            indifferentiUni.put("Persuasione", indifferentiUni.get("Persuasione")/totaliUni);
            indifferentiUni.put("Coinvolgimento", indifferentiUni.get("Coinvolgimento")/totaliUni);
            indifferentiUni.put("Fiducia", indifferentiUni.get("Fiducia")/totaliUni);
        }
        /////////////////////
        if (totaliBi != 0){
            sistemaBi.put("Trasparenza", sistemaBi.get("Trasparenza")/totaliBi);
            sistemaBi.put("Persuasione", sistemaBi.get("Persuasione")/totaliBi);
            sistemaBi.put("Coinvolgimento", sistemaBi.get("Coinvolgimento")/totaliBi);
            sistemaBi.put("Fiducia", sistemaBi.get("Fiducia")/totaliBi);
            
            baselineBi.put("Trasparenza", baselineBi.get("Trasparenza")/totaliBi);
            baselineBi.put("Persuasione", baselineBi.get("Persuasione")/totaliBi);
            baselineBi.put("Coinvolgimento", baselineBi.get("Coinvolgimento")/totaliBi);
            baselineBi.put("Fiducia", baselineBi.get("Fiducia")/totaliBi);
            
            indifferentiBi.put("Trasparenza", indifferentiBi.get("Trasparenza")/totaliBi);
            indifferentiBi.put("Persuasione", indifferentiBi.get("Persuasione")/totaliBi);
            indifferentiBi.put("Coinvolgimento", indifferentiBi.get("Coinvolgimento")/totaliBi);
            indifferentiBi.put("Fiducia", indifferentiBi.get("Fiducia")/totaliBi);
        }
        /////////////////////////////////
        if (totaliUniBi != 0){
            sistemaUniBi.put("Trasparenza", sistemaUniBi.get("Trasparenza")/totaliUniBi);
            sistemaUniBi.put("Persuasione", sistemaUniBi.get("Persuasione")/totaliUniBi);
            sistemaUniBi.put("Coinvolgimento", sistemaUniBi.get("Coinvolgimento")/totaliUniBi);
            sistemaUniBi.put("Fiducia", sistemaUniBi.get("Fiducia")/totaliUniBi);
            
            baselineUniBi.put("Trasparenza", baselineUniBi.get("Trasparenza")/totaliUniBi);
            baselineUniBi.put("Persuasione", baselineUniBi.get("Persuasione")/totaliUniBi);
            baselineUniBi.put("Coinvolgimento", baselineUniBi.get("Coinvolgimento")/totaliUniBi);
            baselineUniBi.put("Fiducia", baselineUniBi.get("Fiducia")/totaliUniBi);
            
            indifferentiUniBi.put("Trasparenza", indifferentiUniBi.get("Trasparenza")/totaliUniBi);
            indifferentiUniBi.put("Persuasione", indifferentiUniBi.get("Persuasione")/totaliUniBi);
            indifferentiUniBi.put("Coinvolgimento", indifferentiUniBi.get("Coinvolgimento")/totaliUniBi);
            indifferentiUniBi.put("Fiducia", indifferentiUniBi.get("Fiducia")/totaliUniBi);
        }
        
        uni.put("sistema", sistemaUni);
        uni.put("baseline", baselineUni);
        uni.put("indifferenti", indifferentiUni);

        bi.put("sistema", sistemaBi);
        bi.put("baseline", baselineBi);
        bi.put("indifferenti", indifferentiBi);

        unibi.put("sistema", sistemaUniBi);
        unibi.put("baseline", baselineUniBi);
        unibi.put("indifferenti", indifferentiUniBi);
        ////////////////////////////////////////////////////
        esiti.put("unigrammi", uni);
        esiti.put("bigrammi", bi);
        esiti.put("unibigrammi", unibi);

        return esiti;
    }
    
    
    

}
