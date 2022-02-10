package actions;

import java.io.IOException;

import controller.business.ScuolaEJB;
import exceptions.CorsoNonEsistenteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Corso;

public class CorsoViewFinal implements Actions {
	
	private CorsoViewFinal() {}
	private static CorsoViewFinal instance = null;
	
	public static CorsoViewFinal getInstance() {
		if(instance == null) {
			instance = new CorsoViewFinal();
		}
		return instance;
	}

	@Override
	public void esegui(HttpServletRequest request, HttpServletResponse response, ScuolaEJB ej) throws ServletException, IOException {
		
		String nomeCorso= request.getParameter("corsi");
		String messaggio=null;
		
		try {
			Corso c=ej.getCorso(nomeCorso);
			request.setAttribute("corso", c);
			request.getServletContext().getRequestDispatcher("/viewcorso.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CorsoNonEsistenteException e) {
			messaggio="corso non trovato!";
			request.setAttribute("messaggio", messaggio);
			request.getServletContext().getRequestDispatcher("/errore.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		
			
			
	
		
	}

}
