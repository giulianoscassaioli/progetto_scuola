package actions;

import java.io.IOException;

import controller.business.ScuolaEJB;
import exceptions.CorsoGiaEsistenteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Corso;

public class InsertCorso implements Actions{

	private InsertCorso() {}
	private static InsertCorso instance = null;
	
	public static InsertCorso getInstance() {
		if(instance == null) {
			instance = new InsertCorso();
		}
		return instance;
	}
	
	@Override
	public void esegui(HttpServletRequest request, HttpServletResponse response, ScuolaEJB ej) throws ServletException, IOException {
		String nome= request.getParameter("nome");
		String messaggio=null;
		
	   Corso c = new Corso();
	   c.setNome(nome);
		
		try {
		
			messaggio="inserimento avvenuto con successo!";
			ej.insertCorso(c);
			request.setAttribute("messaggio", messaggio);
			request.getServletContext().getRequestDispatcher("/landing.jsp").forward(request, response);
		} catch (CorsoGiaEsistenteException e) {
			messaggio="inserimento non avvenuto!";
			request.setAttribute("messaggio", messaggio);
			request.getServletContext().getRequestDispatcher("/errore.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		
	}

	
	
}
