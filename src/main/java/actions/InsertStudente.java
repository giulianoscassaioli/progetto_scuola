package actions;

import java.io.IOException;

import controller.business.ScuolaEJB;
import exceptions.StudenteGiaEsistenteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Studente;

public class InsertStudente implements Actions{


		private InsertStudente() {}
		private static InsertStudente instance = null;
		
		public static InsertStudente getInstance() {
			if(instance == null) {
				instance = new InsertStudente();
			}
			return instance;
		}

	@Override
	public void esegui(HttpServletRequest request, HttpServletResponse response, ScuolaEJB ej) throws ServletException, IOException  {
		
		String nome= request.getParameter("nome");
		String cognome= request.getParameter("cognome");
		String messaggio=null;
		Studente s= new Studente();
		s.setCognome(cognome);
		s.setNome(nome);
		
	
		try {
			messaggio="inserimento avvenuto correttamente!";
			request.setAttribute("messaggio", messaggio);
			ej.inserisciStudente(s);
			request.getServletContext().getRequestDispatcher("/landing.jsp").forward(request, response);
		} catch (StudenteGiaEsistenteException e) {
			
			messaggio="inserimento studente non avvenuto!";
			request.setAttribute("messaggio", messaggio);
			request.getServletContext().getRequestDispatcher("/errore.jsp").forward(request, response);
			e.printStackTrace();
		}
	
		
	
		
		
	}
	
	

}
