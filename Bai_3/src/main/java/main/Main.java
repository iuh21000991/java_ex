package main;

import dao.BookDAO;
import implement.BookImplement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
	public static void main(String[] args) {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MSSQL");
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();		
//	    em.getTransaction().commit();	
//		
		BookDAO bookDAO = new BookImplement();
		bookDAO.listRatedBooks("Robert C. Martin", 2).forEach(System.out::println);
//		bookDAO.countBooksByAuthor().forEach((k,v) -> System.out.println(k + " : " + v));
//		System.out.println(bookDAO.updateReviews("888-0132350800", "1", 5, "Good book!"));
	}
}	
