package report;

import java.util.HashMap;
import classi.ValutazioneTipo3;
import classi.ValutazioneTipo4;
/*////////////////////////////PREFERENZE/////////////////////////////////////
2) preferenza tra centroide e frasi singole;
3) preferenze tra centroide e frasi singole con configurazioni di lemmi;
4) preferenza tra framework e baseline;
*/

public class Preferenze {
  
    //2) preferenza tra FRAMEWORK e BASELINE DISTRIBUZIONALE;
    public static HashMap<String, Double> centroideVSSingoleGenerale(){
        int framework = 0, baselineDistr = 0;
        int indifferente = 0;
        int totale = 0;
        
        HashMap<String, Double> esiti = new HashMap<>();
        
        for (ValutazioneTipo3 v3 : Report.valutazioni3){
            totale++;
            if (v3.getPreferenza0() == 0){
                indifferente++;
            } 
            else if (v3.getPreferenza0() == 1){
            	framework++;
            } 
            else if (v3.getPreferenza0() == 2){
            	baselineDistr++;
            }
        }
        
        if (totale != 0){
            esiti.put("sistema", (double)framework/totale);
            esiti.put("baselineDistr", (double)baselineDistr/totale);
            esiti.put("indifferente", (double)indifferente/totale);
        } 
        else {
            esiti.put("sistema", 0.0);
            esiti.put("baselineDistr", 0.0);
            esiti.put("indifferente", 0.0);
        }
        return esiti;
    }
    
    
  //2) preferenza tra FRAMEWORK e BASELINE DISTRIBUZIONALE tecnica normale;
    public static HashMap<String, Double> centroideVSSingoleNormale(){
        int framework = 0, baselineDistr = 0;
        int indifferente = 0;
        int totale = 0;
        
        HashMap<String, Double> esiti = new HashMap<>();
        
        for (ValutazioneTipo3 v3 : Report.valutazioni3){
        	if(v3.getTecnica().equals("normale")) {
	            totale++;
	            if (v3.getPreferenza0() == 0){
	                indifferente++;
	            } 
	            else if (v3.getPreferenza0() == 1){
	            	framework++;
	            } 
	            else if (v3.getPreferenza0() == 2){
	            	baselineDistr++;
	            }
        	}
        }
        
        if (totale != 0){
            esiti.put("sistema", (double)framework/totale);
            esiti.put("baselineDistr", (double)baselineDistr/totale);
            esiti.put("indifferente", (double)indifferente/totale);
        } 
        else {
            esiti.put("sistema", 0.0);
            esiti.put("baselineDistr", 0.0);
            esiti.put("indifferente", 0.0);
        }
        return esiti;
    }
    
    
  //2) preferenza tra FRAMEWORK e BASELINE DISTRIBUZIONALE tecnica PMI;
    public static HashMap<String, Double> centroideVSSingolePMI(){
        int framework = 0, baselineDistr = 0;
        int indifferente = 0;
        int totale = 0;
        
        HashMap<String, Double> esiti = new HashMap<>();
        
        for (ValutazioneTipo3 v3 : Report.valutazioni3){
        	if(v3.getTecnica().equals("pmi")) {
	            totale++;
	            if (v3.getPreferenza0() == 0){
	                indifferente++;
	            } 
	            else if (v3.getPreferenza0() == 1){
	            	framework++;
	            } 
	            else if (v3.getPreferenza0() == 2){
	            	baselineDistr++;
	            }
        	}
        }
        
        if (totale != 0){
            esiti.put("sistema", (double)framework/totale);
            esiti.put("baselineDistr", (double)baselineDistr/totale);
            esiti.put("indifferente", (double)indifferente/totale);
        } 
        else {
            esiti.put("sistema", 0.0);
            esiti.put("baselineDistr", 0.0);
            esiti.put("indifferente", 0.0);
        }
        return esiti;
    }
    

    //3) preferenze tra FRAMEWORK e BASELINE DISTRIBUZIONALE con CONFIGURAZIONI LEMMI; 
    public static HashMap<String, HashMap<String, Double>> centroideVSSingolePreferenze(){
            HashMap<String, HashMap<String, Double>> esiti = new HashMap<>();
            int  singoleUni = 0, singoleBi = 0, singoleUniBi = 0,
                 centroideUni = 0, centroideBi = 0, centroideUniBi = 0,
                 indiffUni = 0, indiffBi = 0, indiffUniBi = 0,
                 totaleUni = 0, totaleBi = 0, totaleUniBi = 0;

            for (ValutazioneTipo3 v3 : Report.valutazioni3){
                if (v3.getConfigurazione().equals("unigrammi")){
                    totaleUni++;
                    if (v3.getPreferenza0() == 1){
                        centroideUni++;
                    } else if (v3.getPreferenza0() == 2){
                        singoleUni++;
                    } else {
                        indiffUni++;
                    }
                } else if (v3.getConfigurazione().equals("bigrammi")){
                    totaleBi++;
                    if (v3.getPreferenza0() == 1){
                        centroideBi++;
                    } else if (v3.getPreferenza0() == 2){
                        singoleBi++;
                    } else {
                        indiffBi++;
                    }
                } else if (v3.getConfigurazione().equals("unibigrammi")){
                    totaleUniBi++;
                    if (v3.getPreferenza0() == 1){
                        centroideUniBi++;
                    } else if (v3.getPreferenza0() == 2){
                        singoleUniBi++;
                    } else {
                        indiffUniBi++;
                    }
                }
            }
            
            HashMap<String, Double> uni = new HashMap<>();
            HashMap<String, Double> bi = new HashMap<>();
            HashMap<String, Double> unibi = new HashMap<>();
            if (totaleUni != 0){
                uni.put("sistema", (double)centroideUni/totaleUni);
                uni.put("baselineDistr", (double)singoleUni/totaleUni);
                uni.put("indifferenti", (double)indiffUni/totaleUni);

            } 
            else {
                uni.put("sistema", 0.0);
                uni.put("baselineDistr", 0.0);
                uni.put("indifferenti", 0.0);
            }
            
            if (totaleBi != 0){
                bi.put("sistema", (double)centroideBi/totaleBi);
                bi.put("baselineDistr", (double)singoleBi/totaleBi);
                bi.put("indifferenti", (double)indiffBi/totaleBi);
            } 
            else {
                bi.put("sistema", 0.0);
                bi.put("baselineDistr", 0.0);
                bi.put("indifferenti", 0.0);
            }

            if (totaleUniBi != 0){
                unibi.put("sistema", (double)centroideUniBi/totaleUniBi);
                unibi.put("baselineDistr", (double)singoleUniBi/totaleUniBi);
                unibi.put("indifferenti", (double)indiffUniBi/totaleUniBi);
            } 
            else {
                unibi.put("sistema", 0.0);
                unibi.put("baselineDistr", 0.0);
                unibi.put("indifferenti", 0.0);
            }
            
            esiti.put("unigrammi", uni);
            esiti.put("bigrammi", bi);
            esiti.put("unibigrammi", unibi);
            return esiti;
        }
        
    //3) preferenze tra FRAMEWORK e BASELINE DISTRIBUZIONALE con CONFIGURAZIONI LEMMI; 
    public static HashMap<String, HashMap<String, Double>> centroideVSSingolePreferenze2(){
            HashMap<String, HashMap<String, Double>> esiti = new HashMap<>();
            int  singoleUni = 0, singoleBi = 0, singoleUniBi = 0,
                 centroideUni = 0, centroideBi = 0, centroideUniBi = 0,
                 indiffUni = 0, indiffBi = 0, indiffUniBi = 0,
                 totaleUni = 0, totaleBi = 0, totaleUniBi = 0;

            for (ValutazioneTipo4 v4 : Report.valutazioni4){
                if (v4.getConfigurazione().equals("unigrammi")){
                    totaleUni++;
                    if (v4.getPreferenza0() == 1){
                        centroideUni++;
                    } else if (v4.getPreferenza0() == 2){
                        singoleUni++;
                    } else {
                        indiffUni++;
                    }
                } else if (v4.getConfigurazione().equals("bigrammi")){
                    totaleBi++;
                    if (v4.getPreferenza0() == 1){
                        centroideBi++;
                    } else if (v4.getPreferenza0() == 2){
                        singoleBi++;
                    } else {
                        indiffBi++;
                    }
                } else if (v4.getConfigurazione().equals("unibigrammi")){
                    totaleUniBi++;
                    if (v4.getPreferenza0() == 1){
                        centroideUniBi++;
                    } else if (v4.getPreferenza0() == 2){
                        singoleUniBi++;
                    } else {
                        indiffUniBi++;
                    }
                }
            }
            
            HashMap<String, Double> uni = new HashMap<>();
            HashMap<String, Double> bi = new HashMap<>();
            HashMap<String, Double> unibi = new HashMap<>();
            if (totaleUni != 0){
                uni.put("sistema", (double)centroideUni/totaleUni);
                uni.put("baselineDistr", (double)singoleUni/totaleUni);
                uni.put("indifferenti", (double)indiffUni/totaleUni);

            } 
            else {
                uni.put("sistema", 0.0);
                uni.put("baselineDistr", 0.0);
                uni.put("indifferenti", 0.0);
            }
            
            if (totaleBi != 0){
                bi.put("sistema", (double)centroideBi/totaleBi);
                bi.put("baselineDistr", (double)singoleBi/totaleBi);
                bi.put("indifferenti", (double)indiffBi/totaleBi);
            } 
            else {
                bi.put("sistema", 0.0);
                bi.put("baselineDistr", 0.0);
                bi.put("indifferenti", 0.0);
            }

            if (totaleUniBi != 0){
                unibi.put("sistema", (double)centroideUniBi/totaleUniBi);
                unibi.put("baselineDistr", (double)singoleUniBi/totaleUniBi);
                unibi.put("indifferenti", (double)indiffUniBi/totaleUniBi);
            } 
            else {
                unibi.put("sistema", 0.0);
                unibi.put("baselineDistr", 0.0);
                unibi.put("indifferenti", 0.0);
            }
            
            esiti.put("unigrammi", uni);
            esiti.put("bigrammi", bi);
            esiti.put("unibigrammi", unibi);
            return esiti;
        }
    
    
    
    
    //4) preferenza tra FRAMEWORK e baseline PUCARIELLO;
    public static HashMap<String, Double> sistemaVSBaseline(){
        int centroide = 0, singole = 0, sistema = 0, baseline = 0, indifferente = 0, totale = 0;
        HashMap<String, Double> esiti = new HashMap<>();
        
        for (ValutazioneTipo4 v4 : Report.valutazioni4){
            totale++;
            //if (v4.getTipoFrasi().equals("centroide")){//MODIFCATO DA ME
                if (v4.getPreferenza0() == 0){
                    indifferente++;
                } 
                else if (v4.getPreferenza0() == 1){
                    //centroide++;
                    sistema++;
                } 
                else {
                    baseline++;
                }
            //}
            //FRASI SINGOLE
            /*
            else{
                if (v4.getPreferenza0() == 0){
                    indifferente++;
                } 
                else if (v4.getPreferenza0() == 1){
                    singole++;
                    sistema++;
                } 
                else {
                    baseline++;
                }
            }*/
        }
        
        if (totale != 0){
            esiti.put("sistema", (double)sistema/totale);
            esiti.put("baseline", (double)baseline/totale);
            esiti.put("indifferente", (double)indifferente/totale);
            // esiti.put("centroide", (double)centroide/totale);
            //esiti.put("singole", (double)singole/totale);
        } 
        else {
            esiti.put("sistema", 0.0);
            esiti.put("baseline", 0.0);
            esiti.put("indifferente", 0.0);
            //esiti.put("centroide", 0.0);
            //esiti.put("singole", 0.0);
        }
        return esiti;
    }
    
  //4) preferenza tra FRAMEWORK e baseline PUCARIELLO;
    public static HashMap<String, Double> sistemaVSBaselineNormale(){
        int centroide = 0, singole = 0, sistema = 0, baseline = 0, indifferente = 0, totale = 0;
        HashMap<String, Double> esiti = new HashMap<>();
        
        for (ValutazioneTipo4 v4 : Report.valutazioni4){
        	if(v4.getTecnica().equals("normale")){
	            totale++;
	            //if (v4.getTipoFrasi().equals("centroide")){//MODIFCATO DA ME
	                if (v4.getPreferenza0() == 0){
	                    indifferente++;
	                } 
	                else if (v4.getPreferenza0() == 1){
	                    //centroide++;
	                    sistema++;
	                } 
	                else {
	                    baseline++;
	                }
	            //}
	            //FRASI SINGOLE
	            /*
	            else{
	                if (v4.getPreferenza0() == 0){
	                    indifferente++;
	                } 
	                else if (v4.getPreferenza0() == 1){
	                    singole++;
	                    sistema++;
	                } 
	                else {
	                    baseline++;
	                }
	            }*/
        	}
        }
        
        
        if (totale != 0){
            esiti.put("sistema", (double)sistema/totale);
            esiti.put("baseline", (double)baseline/totale);
            esiti.put("indifferente", (double)indifferente/totale);
            // esiti.put("centroide", (double)centroide/totale);
            //esiti.put("singole", (double)singole/totale);
        } 
        else {
            esiti.put("sistema", 0.0);
            esiti.put("baseline", 0.0);
            esiti.put("indifferente", 0.0);
            //esiti.put("centroide", 0.0);
            //esiti.put("singole", 0.0);
        }
        return esiti;
    }
    
  //4) preferenza tra FRAMEWORK e baseline PUCARIELLO;
    public static HashMap<String, Double> sistemaVSBaselinePMI(){
        int centroide = 0, singole = 0, sistema = 0, baseline = 0, indifferente = 0, totale = 0;
        HashMap<String, Double> esiti = new HashMap<>();
        
        for (ValutazioneTipo4 v4 : Report.valutazioni4){
           if(v4.getTecnica().equals("pmi")){
            totale++;
            //if (v4.getTipoFrasi().equals("centroide")){//MODIFCATO DA ME
                if (v4.getPreferenza0() == 0){
                    indifferente++;
                } 
                else if (v4.getPreferenza0() == 1){
                    //centroide++;
                    sistema++;
                } 
                else {
                    baseline++;
                }
            //}
            //FRASI SINGOLE
            /*
            else{
                if (v4.getPreferenza0() == 0){
                    indifferente++;
                } 
                else if (v4.getPreferenza0() == 1){
                    singole++;
                    sistema++;
                } 
                else {
                    baseline++;
                }
            }*/
        	}
        }
        
        if (totale != 0){
            esiti.put("sistema", (double)sistema/totale);
            esiti.put("baseline", (double)baseline/totale);
            esiti.put("indifferente", (double)indifferente/totale);
            // esiti.put("centroide", (double)centroide/totale);
            //esiti.put("singole", (double)singole/totale);
        } 
        else {
            esiti.put("sistema", 0.0);
            esiti.put("baseline", 0.0);
            esiti.put("indifferente", 0.0);
            //esiti.put("centroide", 0.0);
            //esiti.put("singole", 0.0);
        }
        return esiti;
    }
    
    
    
    
    
    
    
    
    
    
    
  //4) preferenza tra FRAMEWORK e baseline PUCARIELLO;
    public static HashMap<String, Double> sistemaVSBaselineTecniche(){
        int normale = 0, pmi = 0, sistema = 0, baseline = 0, indifferente = 0, totale = 0;
        HashMap<String, Double> esiti = new HashMap<>();
        
        for (ValutazioneTipo4 v4 : Report.valutazioni4){
            totale++;
            if (v4.getTecnica().equals("normale")){//MODIFCATO DA ME
                if (v4.getPreferenza0() == 0){
                    indifferente++;
                } 
                else if (v4.getPreferenza0() == 1){
                	normale++;
                    sistema++;
                } 
                else {
                    baseline++;
                }
            }
            //PMI
            else{
                if (v4.getPreferenza0() == 0){
                    indifferente++;
                } 
                else if (v4.getPreferenza0() == 1){
                    pmi++;
                    sistema++;
                } 
                else {
                    baseline++;
                }
            }
        }
        
        if (totale != 0){
            esiti.put("normale", (double)normale/totale);
            esiti.put("pmi", (double)pmi/totale);
            esiti.put("sistema", (double)sistema/totale);
            esiti.put("baseline", (double)baseline/totale);
            esiti.put("indifferente", (double)indifferente/totale);
        } 
        else {
            esiti.put("normale", 0.0);
            esiti.put("pmi", 0.0);
            esiti.put("sistema", 0.0);
            esiti.put("baseline", 0.0);
            esiti.put("indifferente", 0.0);
        }
        return esiti;
    }
    
    
    
}
