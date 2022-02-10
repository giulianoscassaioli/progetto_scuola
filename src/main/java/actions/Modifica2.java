package actions;

import java.io.IOException;
import java.util.List;

import controller.business.ScuolaEJB;
import exceptions.StudenteNonEsistenteException;
import exceptions.StudenteNonIscrittoException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Corso;
import model.Studente;

public class Modifica2 implements Actions{
	
	private Modifica2() {}
	private static Modifica2 instance = null;
	
	public static Modifica2 getInstance() {
		if(instance == null) {
			instance = new Modifica2();
		}
		return instance;
	}

	@Override
	public void esegui(HttpServletRequest request, HttpServletResponse response, ScuolaEJB ej) throws NumberFormatException, StudenteNonEsistenteException, ServletException, IOException, StudenteNonIscrittoException  {
		
		String matricola = request.getParameter("studenti");
		String messaggio=null;
	    List <Corso> corsiIscritto= ej.getStudenteByPK(Integer.parseInt(matricola));
	    if(corsiIscritto.isEmpty()) {
	    	
	    	messaggio="lo studente non è iscritto a nessun corso!";
			request.setAttribute("messaggio", messaggio);
			request.getServletContext().getRequestDispatcher("/errore.jsp").forward(request, response);
	        return;
	    }
	    List <Corso> corsiNonIscritto = ej.getCorsiNonFrequentati(Integer.parseInt(matricola));
            if(corsiNonIscritto.isEmpty()) {
	    	
	    	messaggio="non puoi modificare le iscrizioni perche lo studente è iscritto a tutti i corsi!";
			request.setAttribute("messaggio", messaggio);
			request.getServletContext().getRequestDispatcher("/errore.jsp").forward(request, response);
	        return;
	    }
	    request.setAttribute("matricola", matricola);
		request.setAttribute("listaiscritto", corsiIscritto);
		request.setAttribute("listanoniscritto", corsiNonIscritto);
		request.getServletContext().getRequestDispatcher("/modificaiscrizionestudente2.jsp").include(request, response);
		
		
		

   }
}
