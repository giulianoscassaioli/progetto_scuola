package actions;

import controller.business.ScuolaEJB;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ModificaFinal implements Actions{
	
	private ModificaFinal() {}
	private static ModificaFinal instance = null;
	
	public static ModificaFinal getInstance() {
		if(instance == null) {
			instance = new ModificaFinal();
		}
		return instance;
	}

	@Override
	public void esegui(HttpServletRequest request, HttpServletResponse response, ScuolaEJB ej) throws Exception {
		String idvecchioCorso= request.getParameter("corsifrequentati");
		String idnuovoCorso= request.getParameter("corsinonfrequentati");
		String matricola= request.getParameter("matricola");
		ej.modificaIscrizione(Integer.parseInt(matricola),Integer.parseInt(idvecchioCorso),Integer.parseInt(idnuovoCorso));
		
		String messaggio="modifica avvenuta con successo!";
		request.setAttribute("messaggio", messaggio);
		request.getServletContext().getRequestDispatcher("/landing.jsp").include(request, response);
		
		
	}

}
