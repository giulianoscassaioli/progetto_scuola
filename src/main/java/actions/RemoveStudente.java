package actions;

import java.io.IOException;

import controller.business.ScuolaEJB;
import exceptions.CorsoGiaEsistenteException;
import exceptions.StudenteGiaEsistenteException;
import exceptions.StudenteNonEsistenteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Corso;

public class RemoveStudente implements Actions{
	
	private RemoveStudente() {}
	private static RemoveStudente instance = null;
	
	public static RemoveStudente getInstance() {
		if(instance == null) {
			instance = new RemoveStudente();
		}
		return instance;
	}

	
	@Override
	public void esegui(HttpServletRequest request, HttpServletResponse response, ScuolaEJB ej) throws ServletException, IOException{
		String matricola= request.getParameter("matricola");
		String messaggio=null;
		
		
		try {
		
			messaggio="rimozione studente avvenuta con successo!";
			ej.removeStudente(matricola);
			request.setAttribute("messaggio", messaggio);
			request.getServletContext().getRequestDispatcher("/landing.jsp").forward(request, response);
		} catch (StudenteNonEsistenteException e) {
			messaggio="rimozione studente non avvenuta!";
			request.setAttribute("messaggio", messaggio);
			request.getServletContext().getRequestDispatcher("/errore.jsp").forward(request, response);
			e.printStackTrace();
		}
}
}
