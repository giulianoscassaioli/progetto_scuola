package controller.presentation;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import actions.ActionFactory;
import controller.business.ScuolaEJB;

@WebServlet("/gestione/*")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Servlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request, response);
	}

	
	
	@EJB
	ScuolaEJB sj;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		String operazione= request.getPathInfo();
		

		
		try {
			ActionFactory.getInstance().getAction(operazione).esegui(request, response, sj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	
	}

}
