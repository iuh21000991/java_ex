package main;

import dao.ItemDAO;
import implement.ItemImplement;
public class Main {
	public static void main(String[] args) {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MSSQL");
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();		
//	    em.getTransaction().commit();
		ItemDAO itemDAO = new ItemImplement();
//		itemDAO.listItems("An").forEach(System.out::println);
		itemDAO.listFoodAndCost().forEach((k, v) -> System.out.println(v));
	}
}
