package dao;

import java.util.ArrayList;
import java.util.List;

import exceptions.CorsoGiaEsistenteException;
import exceptions.StudenteGiaEsistenteException;
import exceptions.StudenteNonEsistenteException;
import exceptions.StudenteNonIscrittoException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import model.Corso;
import model.Studente;

public class StudenteDao {

	EntityManager em;

	public StudenteDao(EntityManager em) {
		this.em = em;
	}

	public void inserisciStudente(Studente s) throws StudenteGiaEsistenteException {

		if (getName(s.getNome()) && getSurname(s.getCognome())) {
			throw new StudenteGiaEsistenteException(
					"lo studente: " + s.getNome() + " " + s.getCognome() + " è gia esistente nel db!");
		} else {
			em.persist(s);
		}

	}

	public boolean getName(String nome) {
		boolean success = true;
		Query q = em.createQuery("SELECT s FROM Studente s WHERE UPPER(s.nome) like UPPER(:nome)");

		q.setParameter("nome", nome);
		@SuppressWarnings("unchecked")
		List<Studente> controvati = q.getResultList();
		if (controvati.isEmpty()) {
			success = false;
		}

		return success;

	}

	public boolean getSurname(String surname) {
		boolean success = true;
		Query q = em.createQuery("SELECT s FROM Studente s WHERE UPPER(s.cognome) like UPPER(:cognome)");

		q.setParameter("cognome", surname);
		@SuppressWarnings("unchecked")
		List<Studente> controvati = q.getResultList();
		if (controvati.isEmpty()) {
			success = false;
		}

		return success;

	}

	public void removeStudente(int matricola) throws StudenteNonEsistenteException {

		Studente s1 = em.find(Studente.class, matricola);
		if (s1 != null) {
			em.remove(em.merge(s1));
		}

		else {
			throw new StudenteNonEsistenteException("lo studente che vuoi eliminare non esiste");
		}

	}

	public void iscriviStudente(int matricola, String nomecorso) throws StudenteNonEsistenteException {

		Studente s = em.find(Studente.class, matricola);
		Corso c = getNameCorso(nomecorso);

		if (s != null) {

			c.getStudenti().add(s);
			s.getCorsi().add(c);

			em.merge(s);

		} else {
			throw new StudenteNonEsistenteException("lo studente che vuoi iscrivere non esiste!");

		}

	}

	public Corso getNameCorso(String nome) {
		boolean success = true;
		Query q = em.createQuery("SELECT c FROM Corso c WHERE UPPER(c.nome) like UPPER(:nome)");

		q.setParameter("nome", nome);
		@SuppressWarnings("unchecked")
		Corso controvato = (Corso) q.getSingleResult();

		return controvato;

	}

	public List<Studente> getListaStudenti() {

		Query q = em.createQuery("SELECT s FROM Studente s");

		List<Studente> studentitrovati = q.getResultList();
		return studentitrovati;

	}
	
	public List<Corso> getListaCorsi() {

		Query q = em.createQuery("SELECT c FROM Corso c");

		List<Corso> corsitrovati = q.getResultList();
		return corsitrovati;

	}


	public List<Corso> getStudenteByPK(int matricola) throws StudenteNonEsistenteException, StudenteNonIscrittoException {

		Studente s = em.find(Studente.class, matricola);

		if (s != null) {
			if(s.getCorsi()!=null) {

			return s.getCorsi();
			
			}
			else {
				throw new StudenteNonIscrittoException("lo studente non è iscritto a nessun corso!");
			}
		}

		else {

			throw new StudenteNonEsistenteException("lo studente esiste a!");
		}

	}

	public void disiscrivi(int matricola, String nomeCorso) throws StudenteNonIscrittoException {

		Studente s = em.find(Studente.class, matricola);

		for (Corso cc : s.getCorsi()) {
			if (cc.getNome().equals(nomeCorso)) {
				s.getCorsi().remove(cc);
				em.merge(s);
				return;
			} else {
				throw new StudenteNonIscrittoException("lo studente non era gia iscritto al corso!");

			}

		}

	}
	
	public List<Corso> getCorsiNonFrequentati(int matricola) throws StudenteNonEsistenteException, StudenteNonIscrittoException{
		
		List<Corso> tuttiCorsi= getListaCorsi();
		List<Corso> corsiFrequentati=  getStudenteByPK(matricola);
		
		tuttiCorsi.removeAll(corsiFrequentati);
		
		return tuttiCorsi;
		
		
		
	}
	
	public void modificaIscrizione(int matricola,int idvecchioCorso, int idnuovoCorso ) throws StudenteNonEsistenteException {
		
		Studente s = em.find(Studente.class, matricola);   
		
		List<Corso> corsiFrequentati= s.getCorsi();
		
		Corso nuovocorso = em.find(Corso.class,idnuovoCorso );
		Corso vecchiocorso = em.find(Corso.class,idvecchioCorso );
		
		
		for(Corso c: corsiFrequentati) {
			if (c.getId()==vecchiocorso.getId()) {
				
				int index= corsiFrequentati.lastIndexOf(c);
				corsiFrequentati.set(index, nuovocorso);
			}
			
		}
		
		s.setCorsi(corsiFrequentati);
		
		em.merge(s);
		
	}

	
}
