package dao;

import java.util.List;

import exceptions.CorsoGiaEsistenteException;
import exceptions.CorsoNonEsistenteException;
import exceptions.StudenteGiaEsistenteException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import model.Corso;
import model.Studente;

public class CorsoDao {

	EntityManager em;

	public CorsoDao(EntityManager em) {
		this.em = em;
	}

	public void insertCorso(Corso c) throws CorsoGiaEsistenteException {
		if (getName(c.getNome())) {
			throw new CorsoGiaEsistenteException(
					"il corso: " + c.getNome() +" è gia esistente nel db!");
		}
		else {
			em.persist(c);
		}

	}

	public boolean getName(String nome) {
		boolean success = true;
		Query q = em.createQuery("SELECT c FROM Corso c WHERE UPPER(c.nome) like UPPER(:nome)");

		q.setParameter("nome", nome);
		@SuppressWarnings("unchecked")
		List<Corso> controvati = q.getResultList();
		if (controvati.isEmpty()) {
			success = false;
		}

		return success;

	}
	public void removeCorso(int id) throws CorsoNonEsistenteException {

		Corso c = em.find(Corso.class, id);

		if (c != null) {

			em.remove(c);
		}

		else {

			throw new CorsoNonEsistenteException("il corso che vuoi eliminare non esiste");
		}
	}
	
	public List<Corso> getListaCorsi(){
		
		Query q = em.createQuery("SELECT c FROM Corso c");
		
		List<Corso> corsitrovati=q.getResultList();
		return corsitrovati;
	
	}
	
	public Corso getCorsoByNome(String nome) throws CorsoNonEsistenteException {
		
		Query q = em.createQuery("SELECT c FROM Corso c WHERE UPPER(c.nome) like UPPER(:nome)");

		q.setParameter("nome", nome);
		@SuppressWarnings("unchecked")
		Corso controvato = (Corso) q.getSingleResult();
		if (controvato==null) {
			throw new CorsoNonEsistenteException("il corso che hai cercato non esiste!");
	    }
		else {
			return controvato;
		}
		
	}

}
