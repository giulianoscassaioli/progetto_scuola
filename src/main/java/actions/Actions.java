package actions;


import controller.business.ScuolaEJB;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Actions {
	
	public void esegui(HttpServletRequest request, HttpServletResponse response,ScuolaEJB  ej) throws Exception;

}
