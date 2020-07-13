package frontend;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@WebServlet("/infoUtente")
//ELABORAZIONE DATI DA HTML
/* Questa Servlet salva tutti i dati ricevuti dalla pagina di inserimento delle anagrafiche dell’utente in un file testuale utenti+timestamp.txt, 
aggiungedo l’ID dell’utente, ovvero il timestamp del momento in cui la richiesta viene elaborata.
Viene quindi elabprato il timestamp, creato un file vuoto chiamato utente<timestamp>
I dati sono:
tempo = idUtente 	1591979405061;
eta					1,2,3,4,5					--> 1591979713294;1;uomo;6;11;14	
genere				uomo,donna
titoloStudio		6,7,8,9,10
frequenza			11,12,13
recSys				14,15
Una volta salvati i dati, si verrà rimandati alla pagina sceltacontesti.jsp.
*/
public class ServletGestioneInfoUtente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
        Long tempo = System.currentTimeMillis();	//long
        String stringaTempo = tempo + "";
        String eta = req.getParameter("eta");
        String genere = req.getParameter("genere");
        String titolo = req.getParameter("titoloStudio");
        String frequenza = req.getParameter("frequenza");
        String recSys = req.getParameter("recSys");

        String output = "\n" + tempo + ";" + eta + ";" + genere + ";" + titolo + ";" + frequenza + ";" + recSys;
        System.out.println("///////////////////////////////////INFO UTENTE//////////////////////////////////////////");
        System.out.println(output + "\n");
        Files.write(Paths.get(
        		Configurazione.path + "filesFilmando2/utenti.txt"), output.getBytes(), StandardOpenOption.APPEND);

        String url = "pagine/sceltaContesti.jsp?&tempo=" + stringaTempo;
        resp.sendRedirect(url);

    }
}
