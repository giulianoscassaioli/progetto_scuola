package actions;

import java.util.List;

import controller.business.ScuolaEJB;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Corso;
import model.Studente;

public class Modifica implements Actions{
	
	private Modifica() {}
	private static Modifica instance = null;
	
	public static Modifica getInstance() {
		if(instance == null) {
			instance = new Modifica();
		}
		return instance;
	}

	@Override
	public void esegui(HttpServletRequest request, HttpServletResponse response, ScuolaEJB ej) throws Exception {
		List <Studente> studenti=ej.getListaStudenti();
	     
		request.setAttribute("listastudenti", studenti);
		request.getServletContext().getRequestDispatcher("/modificaiscrizionestudente1.jsp").include(request, response);
		
	}

}
