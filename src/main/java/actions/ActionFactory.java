package actions;

public class ActionFactory {
	
	
	private static ActionFactory instance=null;
	
	
	private ActionFactory() {}


	
	
	public static ActionFactory getInstance() {
		
		if(instance==null) {
			
			instance = new ActionFactory();
		}
		return instance;
	}


	public static void setInstance(ActionFactory instance) {
		ActionFactory.instance = instance;
	}
	
	
	
	
	public Actions getAction(String operazione) {
		
		switch (operazione) {
		     case "/studente/inserisci":
		    	 return  InsertStudente.getInstance();
		    	 
		     case "/corso/inserisci":
		    	 return  InsertCorso.getInstance();
		     	 
		     case "/corso/rimuovi":
		    	 return  RemoveCorso.getInstance();
		     	 
		     case "/studente/rimuovi":
		    	 return  RemoveStudente.getInstance();
		     case "/studente/iscrivi":
		    	 return  IscriviStudente.getInstance();
		      
		     case "/studente/iscriviFinal":
		    	 return  IscriviStudenteFinal.getInstance();
		     
		    case "/corso/view":
		    	 return  CorsoView.getInstance();
		    
		    case "/corso/viewFinal":
		    	 return  CorsoViewFinal.getInstance();
		    
		    case "/studente/disiscrivi":
		    	 return  Disiscrivi.getInstance();
		   
		    case "/studente/disiscriviFinal":
		    	 return  DisiscriviFinal.getInstance();
		    	 
		    case "/studente/modifica":
		    	 return  Modifica.getInstance();
		    	 
		    case "/studente/modifica2":
		    	 return  Modifica2.getInstance();
		    	 
		    case "/studente/modificaFinal":
		    	 return  ModificaFinal.getInstance();
		
		}
		return null;
		
		
	}







	
	
	
	
	

}
