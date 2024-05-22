package main;

import dao.CandidateDAO;
import implement.CandidateImplement;

public class Main {
	public static void main(String[] args) {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MSSQL");
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();		
//	    em.getTransaction().commit();

		CandidateDAO candidateDAO = new CandidateImplement();
		candidateDAO.listCadidatesAndCertificates().forEach((k, v) -> {
			System.out.println(k.getFullName() + " " + v.size());
		});
	}
}

