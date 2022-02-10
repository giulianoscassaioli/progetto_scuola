package actions;

import java.util.List;

import controller.business.ScuolaEJB;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Corso;
import model.Studente;

public class Disiscrivi implements Actions{
	
	private Disiscrivi() {
	}

	private static Disiscrivi instance = null;

	public static Disiscrivi getInstance() {
		if (instance == null) {
			instance = new Disiscrivi();
		}
		return instance;
	}

	@Override
	public void esegui(HttpServletRequest request, HttpServletResponse response, ScuolaEJB ej) throws Exception {
		
		List <Corso> corsi=ej.getListaCorsi();
		List <Studente> studenti=ej.getListaStudenti();
	
		request.setAttribute("listacorsi", corsi);
		request.setAttribute("listastudenti", studenti);
		request.getServletContext().getRequestDispatcher("/disiscrizionestudente.jsp").forward(request, response);
	
		
	}

}
