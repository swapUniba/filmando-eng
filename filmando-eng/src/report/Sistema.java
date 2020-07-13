package report;

import java.util.HashMap;
import classi.ValutazioneTipo4;
public class Sistema {
    
	
	//15) SISTEMA VS BASELINE con CONTESTI
    public static HashMap<Integer, HashMap<String, Double>> sistemaVsBaselineContesti(){
        HashMap<Integer, HashMap<String, Double>> preferenzaNumeroContesti = new HashMap<Integer, HashMap<String, Double>>();

        int centroide1 = 0, singole1 = 0, sistema1 = 0, baseline1 = 0, indifferente1 = 0, totale1 = 0;
        int centroide2 = 0, singole2 = 0, sistema2 = 0, baseline2 = 0, indifferente2 = 0, totale2 = 0;
        int centroide3 = 0, singole3 = 0, sistema3 = 0, baseline3 = 0, indifferente3 = 0, totale3 = 0;

        for(ValutazioneTipo4 v4 : Report.valutazioni4){	//FRAMEWORK VS BASELINE PUCARIELLO
        	///////////////////////////////1 CONTESTO///////////////////////////
        	if(v4.getNumeroContesti() == 1){
                totale1++;
                if (v4.TipoFrasi.equals("centroide")){//== 1
                    if (v4.getPreferenza0() == 0){
                        indifferente1++;
                    } else if (v4.getPreferenza0() == 1){
                        centroide1++;
                        sistema1++;
                    } else {
                        baseline1++;
                    }
                } 
                else {//FRASI SINGOLE
                    if (v4.getPreferenza0() == 0){
                        indifferente1++;
                    } else if (v4.getPreferenza0() == 1){
                        singole1++;
                        sistema1++;
                    } else {
                        baseline1++;
                    }

                }
            }
            /////////////////////////////////2 CONTESTI/////////////////////////
            else if(v4.getNumeroContesti() ==2){
                totale2++;
                if (v4.TipoFrasi.equals("centroide")){

                    if (v4.getPreferenza0() == 0){
                        indifferente2++;
                    } else if (v4.getPreferenza0() == 1){
                        centroide2++;
                        sistema2++;
                    } else {
                        baseline2++;
                    }

                } else {//FRASI SINGOLE
                    if (v4.getPreferenza0() == 0){
                        indifferente2++;
                    } else if (v4.getPreferenza0() == 1){
                        singole2++;
                        sistema2++;
                    } else {
                        baseline2++;
                    }

                }
            }
        	///////////////////////////////3 CONTESTI///////////////////////////
            else if(v4.getNumeroContesti()==3){
                if (v4.TipoFrasi.equals("centroide")){
                	
                    if (v4.getPreferenza0() == 0){
                        indifferente3++;
                    } else if (v4.getPreferenza0() == 1){
                        centroide3++;
                        sistema3++;
                    } else {
                        baseline3++;
                    }

                } 
                else {//FRASI SINGOLE
                    if (v4.getPreferenza0() == 0){
                        indifferente3++;
                    } else if (v4.getPreferenza0() == 1){
                        singole3++;
                        sistema3++;
                    } else {
                        baseline3++;
                    }
                }
                totale3++;
            }
        }

        HashMap<String, Double> contesto1 = new HashMap<String, Double>();
        HashMap<String, Double> contesto2 = new HashMap<String, Double>();
        HashMap<String, Double> contesto3 = new HashMap<String, Double>();

        contesto1.put("centroide", totale1 != 0 ? (double)centroide1/totale1 : 0);
        contesto1.put("singole", totale1 != 0 ? (double)singole1/totale1 : 0);
        contesto1.put("sistema", totale1 != 0 ? (double)sistema1/totale1 : 0);
        contesto1.put("baseline", totale1 != 0 ? (double)baseline1/totale1 : 0);

        contesto2.put("centroide", totale2 != 0 ? (double)centroide2/totale2 : 0);
        contesto2.put("singole", totale2 != 0 ? (double)singole2/totale2 : 0);
        contesto2.put("sistema", totale2 != 0 ? (double)sistema2/totale2 : 0);
        contesto2.put("baseline", totale2 != 0 ? (double)baseline2/totale2 : 0);

        contesto3.put("centroide", totale3 != 0 ? (double)centroide3/totale3 : 0);
        contesto3.put("singole", totale3 != 0 ? (double)singole3/totale3 : 0);
        contesto3.put("sistema", totale3 != 0 ? (double)sistema3/totale3 : 0);
        contesto3.put("baseline", totale3 != 0 ? (double)baseline3/totale3 : 0);

        preferenzaNumeroContesti.put(1, contesto1);
        preferenzaNumeroContesti.put(2, contesto2);
        preferenzaNumeroContesti.put(3, contesto3);

        return preferenzaNumeroContesti;
    }

    
    //16)
    public static HashMap<Integer, HashMap<String, Double>> sistemaVsBaselineSingoliContesti(){
        HashMap<Integer, HashMap<String, Double>> preferenzaNumeroContesti = new HashMap<Integer, HashMap<String, Double>>();

        int centroide1 = 0, singole1 = 0, sistema1 = 0, baseline1 = 0, indifferente1 = 0, totale1 = 0;
        int centroide2 = 0, singole2 = 0, sistema2 = 0, baseline2 = 0, indifferente2 = 0, totale2 = 0;
        int centroide3 = 0, singole3 = 0, sistema3 = 0, baseline3 = 0, indifferente3 = 0, totale3 = 0;
        int centroide4 = 0, singole4 = 0, sistema4 = 0, baseline4 = 0, indifferente4 = 0, totale4 = 0;
        int centroide5 = 0, singole5 = 0, sistema5 = 0, baseline5 = 0, indifferente5 = 0, totale5 = 0;
        int centroide6 = 0, singole6 = 0, sistema6 = 0, baseline6 = 0, indifferente6 = 0, totale6 = 0;
        int centroide7 = 0, singole7 = 0, sistema7 = 0, baseline7 = 0, indifferente7 = 0, totale7 = 0;

        for(ValutazioneTipo4 v4 : Report.valutazioni4){//FRAMEWORK vs BASELINE PUCARIELLO
            //----------------------CONTESTO 1------------------------
        	if(v4.getListaContesti().contains(1)){
                totale1++;
                if (v4.TipoFrasi.equals("centroide")){
                    if (v4.getPreferenza0() == 0){
                        indifferente1++;
                    } else if (v4.getPreferenza0() == 1){
                        centroide1++;
                        sistema1++;
                    } else {
                        baseline1++;
                    }
                } 
                else {//FRASI SINGOLE
                    if (v4.getPreferenza0() == 0){
                        indifferente1++;
                    } else if (v4.getPreferenza0() == 1){
                        singole1++;
                        sistema1++;
                    } else {
                        baseline1++;
                    }
                }
            }      
        	//----------------------CONTESTO 2------------------------
            if(v4.getListaContesti().contains(2)){
                totale2++;
                if (v4.TipoFrasi.equals("centroide")){
                    if (v4.getPreferenza0() == 0){
                        indifferente2++;
                    } else if (v4.getPreferenza0() == 1){
                        centroide2++;
                        sistema2++;
                    } else {
                        baseline2++;
                    }
                } 
                else {//FRASI SINGOLE
                    if (v4.getPreferenza0() == 0){
                        indifferente2++;
                    } else if (v4.getPreferenza0() == 1){
                        singole2++;
                        sistema2++;
                    } else {
                        baseline2++;
                    }

                }
            }
          //----------------------CONTESTO 3------------------------
            if(v4.getListaContesti().contains(3)){
                totale3++;
                if (v4.TipoFrasi.equals("centroide")){
                    if (v4.getPreferenza0() == 0){
                        indifferente3++;
                    } else if (v4.getPreferenza0() == 1){
                        centroide3++;
                        sistema3++;
                    } else {
                        baseline3++;
                    }
                } 
                else {//FRASI SINGOLE
                    if (v4.getPreferenza0() == 0){
                        indifferente3++;
                    } else if (v4.getPreferenza0() == 1){
                        singole3++;
                        sistema3++;
                    } else {
                        baseline3++;
                    }

                }
            }
          //----------------------CONTESTO 4------------------------
            if(v4.getListaContesti().contains(4)){
                totale4++;
                if (v4.TipoFrasi.equals("centroide")){
                    if (v4.getPreferenza0() == 0){
                        indifferente4++;
                    } else if (v4.getPreferenza0() == 1){
                        centroide4++;
                        sistema4++;
                    } else {
                        baseline4++;
                    }
                } 
                else {//FRASI SINGOLE
                    if (v4.getPreferenza0() == 0){
                        indifferente4++;
                    } else if (v4.getPreferenza0() == 1){
                        singole4++;
                        sistema4++;
                    } else {
                        baseline4++;
                    }
                }
            }
          //----------------------CONTESTO 5------------------------
            if(v4.getListaContesti().contains(5)){
                totale5++;
                if (v4.TipoFrasi.equals("centroide")){
                    if (v4.getPreferenza0() == 0){
                        indifferente5++;
                    } else if (v4.getPreferenza0() == 1){
                        centroide5++;
                        sistema5++;
                    } else {
                        baseline5++;
                    }
                } 
                else {//FRASI SINGOLE
                    if (v4.getPreferenza0() == 0){
                        indifferente5++;
                    } else if (v4.getPreferenza0() == 1){
                        singole5++;
                        sistema5++;
                    } else {
                        baseline5++;
                    }
                }
            }
          //----------------------CONTESTO 6------------------------
            if(v4.getListaContesti().contains(6)){
                totale6++;
                if (v4.TipoFrasi.equals("centroide")){
                    if (v4.getPreferenza0() == 0){
                        indifferente6++;
                    } else if (v4.getPreferenza0() == 1){
                        centroide6++;
                        sistema6++;
                    } else {
                        baseline6++;
                    }
                } 
                else {//FRASI SINGOLE
                    if (v4.getPreferenza0() == 0){
                        indifferente6++;
                    } else if (v4.getPreferenza0() == 1){
                        singole6++;
                        sistema6++;
                    } else {
                        baseline6++;
                    }
                }
            }
          //----------------------CONTESTO 7------------------------
            if(v4.getListaContesti().contains(7)){
                totale7++;
                if (v4.TipoFrasi.equals("centroide")){
                    if (v4.getPreferenza0() == 0){
                        indifferente7++;
                    } else if (v4.getPreferenza0() == 1){
                        centroide7++;
                        sistema7++;
                    } else {
                        baseline7++;
                    }
                } 
                else {//FRASI SINGOLE
                    if (v4.getPreferenza0() == 0){
                        indifferente7++;
                    } else if (v4.getPreferenza0() == 1){
                        singole7++;
                        sistema7++;
                    } else {
                        baseline7++;
                    }
                }
            }
        }
        
        HashMap<String, Double> contesto1 = new HashMap<String, Double>();
        HashMap<String, Double> contesto2 = new HashMap<String, Double>();
        HashMap<String, Double> contesto3 = new HashMap<String, Double>();
        HashMap<String, Double> contesto4 = new HashMap<String, Double>();
        HashMap<String, Double> contesto5 = new HashMap<String, Double>();
        HashMap<String, Double> contesto6 = new HashMap<String, Double>();
        HashMap<String, Double> contesto7 = new HashMap<String, Double>();

        contesto1.put("centroide", totale1 != 0 ? (double)centroide1/totale1 : 0);
        contesto1.put("singole", totale1 != 0 ? (double)singole1/totale1 : 0);
        contesto1.put("sistema", totale1 != 0 ? (double)sistema1/totale1 : 0);
        contesto1.put("baseline", totale1 != 0 ? (double)baseline1/totale1 : 0);

        contesto2.put("centroide", totale2 != 0 ? (double)centroide2/totale2 : 0);
        contesto2.put("singole", totale2 != 0 ? (double)singole2/totale2 : 0);
        contesto2.put("sistema", totale2 != 0 ? (double)sistema2/totale2 : 0);
        contesto2.put("baseline", totale2 != 0 ? (double)baseline2/totale2 : 0);

        contesto3.put("centroide", totale3 != 0 ? (double)centroide3/totale3 : 0);
        contesto3.put("singole", totale3 != 0 ? (double)singole3/totale3 : 0);
        contesto3.put("sistema", totale3 != 0 ? (double)sistema3/totale3 : 0);
        contesto3.put("baseline", totale3 != 0 ? (double)baseline3/totale3 : 0);

        contesto4.put("centroide", totale4 != 0 ? (double)centroide4/totale4 : 0);
        contesto4.put("singole", totale4 != 0 ? (double)singole4/totale4 : 0);
        contesto4.put("sistema", totale4 != 0 ? (double)sistema4/totale4 : 0);
        contesto4.put("baseline", totale4 != 0 ? (double)baseline4/totale4 : 0);

        contesto5.put("centroide", totale5 != 0 ? (double)centroide5/totale5 : 0);
        contesto5.put("singole", totale5 != 0 ? (double)singole5/totale5 : 0);
        contesto5.put("sistema", totale5 != 0 ? (double)sistema5/totale5 : 0);
        contesto5.put("baseline", totale5 != 0 ? (double)baseline5/totale5 : 0);

        contesto6.put("centroide", totale6 != 0 ? (double)centroide6/totale6 : 0);
        contesto6.put("singole", totale6 != 0 ? (double)singole6/totale6 : 0);
        contesto6.put("sistema", totale6 != 0 ? (double)sistema6/totale6 : 0);
        contesto6.put("baseline", totale6 != 0 ? (double)baseline6/totale6 : 0);

        contesto7.put("centroide", totale7 != 0 ? (double)centroide7/totale7 : 0);
        contesto7.put("singole", totale7 != 0 ? (double)singole7/totale7 : 0);
        contesto7.put("sistema", totale7 != 0 ? (double)sistema7/totale7 : 0);
        contesto7.put("baseline", totale7 != 0 ? (double)baseline7/totale7 : 0);

        preferenzaNumeroContesti.put(1, contesto1);
        preferenzaNumeroContesti.put(2, contesto2);
        preferenzaNumeroContesti.put(3, contesto3);
        preferenzaNumeroContesti.put(4, contesto1);
        preferenzaNumeroContesti.put(5, contesto2);
        preferenzaNumeroContesti.put(6, contesto3);
        preferenzaNumeroContesti.put(7, contesto3);
        
        return preferenzaNumeroContesti;
    }

    
    //17) SISTEMA vs BASELINE PUCARIELLO con FILM
    public static HashMap<Integer, HashMap<String, Double>> sistemaVsBaselineFilm(){
        HashMap<Integer, HashMap<String, Double>> preferenzaNumeroContesti = new HashMap<Integer, HashMap<String, Double>>();

        HashMap<Integer, Integer> centroide =new HashMap<>();
        HashMap<Integer, Integer> singole =new HashMap<>();
        HashMap<Integer, Integer> baseline =new HashMap<>();
        HashMap<Integer, Integer> indifferente =new HashMap<>();
        HashMap<Integer, Integer> sistema =new HashMap<>();
        HashMap<Integer, Integer> totale =new HashMap<>();
        
        for(ValutazioneTipo4 v4 : Report.valutazioni4){//FRAMEWORK VS BASELINE PUCARIELLO
            Integer locale = v4.getLocale();			//LEGGO FILM
            
            if (totale.containsKey(locale)){
                if (v4.TipoFrasi.equals("centroide")) {
                    if (v4.getPreferenza0() == 0) {
                        if(indifferente.containsKey(locale)){
                            indifferente.put(locale, indifferente.get(locale)+1);
                        } else{
                            indifferente.put(locale, 1);
                        }
                    } 
                    else if (v4.getPreferenza0() == 1) {
                        if(centroide.containsKey(locale)) {
                            centroide.put(locale, centroide.get(locale) + 1);
                        }else{
                            centroide.put(locale, 1);
                        }
                        if(sistema.containsKey(locale)) {
                            sistema.put(locale, sistema.get(locale) + 1);
                        }else{
                            sistema.put(locale, 1);
                        }
                    } else {
                        if(baseline.containsKey(locale)) {
                            baseline.put(locale, baseline.get(locale) + 1);
                        }else{
                            baseline.put(locale, 1);
                        }
                    }
                } 
                else {	//FRASI SINGOLE
                    if (v4.getPreferenza0() == 0) {
                        if(indifferente.containsKey(locale)){
                            indifferente.put(locale, indifferente.get(locale)+1);
                        } else{
                            indifferente.put(locale, 1);
                        }
                    } else if (v4.getPreferenza0() == 1) {
                        if(singole.containsKey(locale)) {
                            singole.put(locale, singole.get(locale) + 1);
                        } else{
                            singole.put(locale,1);
                        }
                        if(sistema.containsKey(locale)) {
                            sistema.put(locale, sistema.get(locale) + 1);
                        }else{
                            sistema.put(locale, 1);
                        }
                    } 
                    else {
                            if(baseline.containsKey(locale)) {
                                baseline.put(locale, baseline.get(locale) + 1);
                            }else{
                                baseline.put(locale, 1);
                            }
                    }
                }
                // aggiornamento occorrenze
                totale.put(locale, totale.get(locale)+1);

            } 
            
            else {
                if (v4.TipoFrasi.equals("centroide")) {

                    if (v4.getPreferenza0() == 0) {
                        indifferente.put(locale, 1);
                    } else if (v4.getPreferenza0() == 1) {
                        centroide.put(locale, 1);
                        sistema.put(locale, 1);
                    } else {
                        baseline.put(locale, 1);
                    }
                } 
                else {//FRASI SINGOLE
                    if (v4.getPreferenza0() == 0) {
                        indifferente.put(locale, 1);
                    } else if (v4.getPreferenza0() == 1) {
                        singole.put(locale, 1);
                        sistema.put(locale, 1);
                    } else {
                        baseline.put(locale, 1);
                    }
                }
                // prima occorrenza
                totale.put(locale, 1);
            }
        }


        for (int film : Contatori.contatoriLocaliSuggeriti().keySet()){
            Integer totale1 = totale.get(film);
            HashMap<String, Double> contesto = new HashMap<String, Double>();

            if(centroide.containsKey(film)) {
                contesto.put("centroide", totale1 != 0 ? (double) centroide.get(film) / totale1 : 0);
            }else{
                contesto.put("centroide", 0.0);
            }
            if(singole.containsKey(film)) {
                contesto.put("singole", totale1 != 0 ? (double) singole.get(film) / totale1 : 0);
            }
            else {
                contesto.put("singole", 0.0);
            }
            if(sistema.containsKey(film)) {
                contesto.put("sistema", totale1 != 0 ? (double) sistema.get(film) / totale1 : 0);
            } else{
                contesto.put("sistema", 0.0);
            }
            if(baseline.containsKey(film)) {
                contesto.put("baseline", totale1 != 0 ? (double) baseline.get(film) / totale1 : 0);
            }else{
                contesto.put("baseline", 0.0);
            }
            preferenzaNumeroContesti.put(film, contesto);
        }
        return preferenzaNumeroContesti;
    }

}
