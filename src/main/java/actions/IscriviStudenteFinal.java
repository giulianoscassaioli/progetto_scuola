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

public class IscriviStudenteFinal implements Actions{
	

	private IscriviStudenteFinal() {
	}

	private static IscriviStudenteFinal instance = null;

	public static IscriviStudenteFinal getInstance() {
		if (instance == null) {
			instance = new IscriviStudenteFinal();
		}
		return instance;
	}

	@Override
	public void esegui(HttpServletRequest request, HttpServletResponse response, ScuolaEJB ej)
			throws ServletException, IOException, NumberFormatException, StudenteNonEsistenteException, StudenteNonIscrittoException{
		
		
		String matricola = request.getParameter("studenti");
		String nomecorso = request.getParameter("corsi");
		
		String messaggio = null;
		
		Corso c = new Corso();
		
		c.setNome(nomecorso);

			List<Corso> corsiStudente = ej.getStudenteByPK(Integer.parseInt(matricola));
			
			boolean notSuccess= false;
			
			for(Corso cc: corsiStudente) {
				if(cc.getNome().equals(nomecorso)) {
					notSuccess=true;
				}
			}
			
			if(notSuccess) {
				
				messaggio="lo studente è gia inscritto al corso!";
				request.setAttribute("messaggio", messaggio);
				request.getServletContext().getRequestDispatcher("/errore.jsp").forward(request, response);
				//throw new IllegalArgumentException("lo studente è gia inscritto al corso!");
				return;
			}
			else {
				
				try {
					messaggio = "iscrizione avvenuta con successo!";
					ej.iscriviStudente(Integer.parseInt(matricola), c.getNome());
					request.setAttribute("messaggio", messaggio);
					request.getServletContext().getRequestDispatcher("/landing.jsp").forward(request, response);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (StudenteNonEsistenteException e) {
					messaggio="lo studente non esiste!";
					request.setAttribute("messaggio", messaggio);
					request.getServletContext().getRequestDispatcher("/errore.jsp").forward(request, response);
				}
				
				
			  
		}
	
		
		/*Corso c = new Corso();
	
		c.setNome(nomecorso);

		
		try {
			messaggio = "iscrizione avvenuta con successo!";
			ej.iscriviStudente(Integer.parseInt(matricola), c.getNome());
			request.setAttribute("messaggio", messaggio);
			request.getServletContext().getRequestDispatcher("/landing.jsp").forward(request, response);
		} catch (StudenteNonEsistenteException e) {
			messaggio="iscrizione non avvenuta!";
			request.setAttribute("messaggio", messaggio);
			request.getServletContext().getRequestDispatcher("/errore.jsp").forward(request, response);
			e.printStackTrace();
		}*/
			

}
}
