package actions;

import java.io.IOException;
import java.util.List;

import controller.business.ScuolaEJB;
import exceptions.CorsoNonEsistenteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Corso;
import model.Studente;

public class CorsoView implements Actions {
	
	private CorsoView() {}
	private static CorsoView instance = null;
	
	public static CorsoView getInstance() {
		if(instance == null) {
			instance = new CorsoView();
		}
		return instance;
	}

	@Override
	public void esegui(HttpServletRequest request, HttpServletResponse response, ScuolaEJB ej) throws ServletException, IOException {
		
		
		List <Corso> corsi=ej.getListaCorsi();
	
	
		
		request.setAttribute("listacorsi", corsi);
		request.getServletContext().getRequestDispatcher("/selectcorso.jsp").forward(request, response);
		
		
			
			
	
		
	}

}
