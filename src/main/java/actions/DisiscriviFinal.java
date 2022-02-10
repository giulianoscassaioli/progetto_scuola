package actions;

import java.io.IOException;
import java.util.List;

import controller.business.ScuolaEJB;
import exceptions.StudenteNonEsistenteException;
import exceptions.StudenteNonIscrittoException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Corso;

public class DisiscriviFinal implements Actions{
	private DisiscriviFinal() {
	}

	private static DisiscriviFinal instance = null;

	public static DisiscriviFinal getInstance() {
		if (instance == null) {
			instance = new DisiscriviFinal();
		}
		return instance;
	}

	@Override
	public void esegui(HttpServletRequest request, HttpServletResponse response, ScuolaEJB ej) throws ServletException, IOException  {

		String matricola = request.getParameter("studenti");
		String nomecorso = request.getParameter("corsi");
		
		String messaggio = null;
		
		Corso c = new Corso();
		
		c.setNome(nomecorso);
		
		try {
			messaggio = "disiscrizione avvenuta con successo!";
			ej.disiscriviStudente(Integer.parseInt(matricola) , nomecorso);
			request.setAttribute("messaggio", messaggio);
			request.getServletContext().getRequestDispatcher("/landing.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StudenteNonIscrittoException e) {
			messaggio="lo studente non era gia iscritto a questo corso!";
			request.setAttribute("messaggio", messaggio);
			request.getServletContext().getRequestDispatcher("/errore.jsp").forward(request, response);
		}
			  
		
	}

}
