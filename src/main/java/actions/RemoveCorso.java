package actions;

import java.io.IOException;

import controller.business.ScuolaEJB;
import exceptions.CorsoGiaEsistenteException;
import exceptions.CorsoNonEsistenteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Corso;

public class RemoveCorso implements Actions {
	
	private RemoveCorso() {}
	private static RemoveCorso instance = null;
	
	public static RemoveCorso getInstance() {
		if(instance == null) {
			instance = new RemoveCorso();
		}
		return instance;
	}
	
	@Override
	public void esegui(HttpServletRequest request, HttpServletResponse response, ScuolaEJB ej) throws ServletException, IOException {
		String id= request.getParameter("id");
		String messaggio=null;
		
	 
		
		try {
		
			messaggio="rimozione corso avvenuta con successo!";
			ej.removeCorso(id);
			request.setAttribute("messaggio", messaggio);
			request.getServletContext().getRequestDispatcher("/landing.jsp").forward(request, response);
		} catch (CorsoNonEsistenteException e) {
			messaggio="rimozione non avvenuta!";
			request.setAttribute("messaggio", messaggio);
			request.getServletContext().getRequestDispatcher("/errore.jsp").forward(request, response);
			e.printStackTrace();
		}

}
}