package controller.business;

import java.util.List;

import dao.CorsoDao;
import dao.StudenteDao;
import exceptions.CorsoGiaEsistenteException;
import exceptions.CorsoNonEsistenteException;
import exceptions.StudenteGiaEsistenteException;
import exceptions.StudenteNonEsistenteException;
import exceptions.StudenteNonIscrittoException;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import model.Corso;
import model.Studente;

/**
 * Session Bean implementation class ScuolaEJB
 */
@Stateless
@LocalBean
public class ScuolaEJB implements ScuolaEJBLocal {

	@PersistenceContext(unitName = "scuolaPS")
	EntityManager em;

	private CorsoDao cd=null;
	private StudenteDao sd=null;
	
	
	
	//getter di tipo lazy dei dao
	public CorsoDao getCd() {
		if(cd==null) {
			cd=new CorsoDao(em);
		}
		return cd;
	}


	
	public StudenteDao getSd() {
		if(sd==null){
			sd= new StudenteDao(em);
		}
		return sd;
	}



	public ScuolaEJB() {
		// TODO Auto-generated constructor stub
	}

	//*************METODI******************
	
	public void inserisciStudente(Studente s) throws StudenteGiaEsistenteException {

		getSd().inserisciStudente(s);

	}
	
	public void insertCorso(Corso c) throws CorsoGiaEsistenteException {
		getCd().insertCorso(c);
		
	}

	public void removeStudente(int matricola) throws StudenteNonEsistenteException {

			removeStudente(matricola);
	}
	

	public void removeStudente(String matricola)  throws StudenteNonEsistenteException {
		
		getSd().removeStudente(Integer.parseInt(matricola));
		
	}
	
	public void removeCorso(int id) throws CorsoNonEsistenteException {
		
		  removeCorso(id);
	}


	public void removeCorso(String id) throws CorsoNonEsistenteException{
		
		getCd().removeCorso(Integer.parseInt(id));
		
	}
	
	public void iscriviStudente(int matricola,String nomeCorso) throws StudenteNonEsistenteException  {
		
		getSd().iscriviStudente(matricola, nomeCorso);
	}
	
     public List<Corso> getListaCorsi(){
		return getCd().getListaCorsi();
	
	}
     
     public List<Studente> getListaStudenti(){
 		return getSd().getListaStudenti() ;
 	
 	}
     
     public Corso getCorso(String nome) throws CorsoNonEsistenteException {
    	return getCd().getCorsoByNome(nome);
    	 
     }

     public List<Corso> getStudenteByPK(int matricola) throws StudenteNonEsistenteException, StudenteNonIscrittoException {
    	 
    	return getSd().getStudenteByPK(matricola);
     }
     
     public void disiscriviStudente(int matricola,String nomeCorso) throws StudenteNonIscrittoException  {
    	 
    	 getSd().disiscrivi(matricola, nomeCorso);
     }
     
     public List<Corso> getCorsiNonFrequentati(int matricola) throws StudenteNonEsistenteException, StudenteNonIscrittoException{
    	 
    	return getSd().getCorsiNonFrequentati(matricola);
     }
     
     public void modificaIscrizione(int matricola,int idvecchioCorso, int idnuovoCorso ) throws StudenteNonEsistenteException {
    	 
    	 getSd().modificaIscrizione(matricola, idvecchioCorso, idnuovoCorso);
     }
}
