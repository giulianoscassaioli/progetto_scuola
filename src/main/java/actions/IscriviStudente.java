package actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.business.ScuolaEJB;
import exceptions.CorsoGiaEsistenteException;
import exceptions.StudenteNonEsistenteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Corso;
import model.Studente;

public class IscriviStudente implements Actions {

	private IscriviStudente() {
	}

	private static IscriviStudente instance = null;

	public static IscriviStudente getInstance() {
		if (instance == null) {
			instance = new IscriviStudente();
		}
		return instance;
	}

	@Override
	public void esegui(HttpServletRequest request, HttpServletResponse response, ScuolaEJB ej)
			throws ServletException, IOException{
		
		List <Corso> corsi=ej.getListaCorsi();
		List <Studente> studenti=ej.getListaStudenti();
	
		request.setAttribute("listacorsi", corsi);
		request.setAttribute("listastudenti", studenti);
		request.getServletContext().getRequestDispatcher("/iscrizionestudente.jsp").forward(request, response);
	
		
		
	}
	


}
