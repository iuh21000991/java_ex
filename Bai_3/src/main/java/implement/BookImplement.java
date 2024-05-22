package implement;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import dao.BookDAO;
import entity.Book;
import entity.Person;
import entity.Reviews;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class BookImplement implements BookDAO {

	private EntityManagerFactory emf;
	private EntityManager em;

	public BookImplement() {
		emf = Persistence.createEntityManagerFactory("MSSQL");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> listRatedBooks(String author, int rating) {
		try {
			em = emf.createEntityManager();
			String query = "SELECT ba.ISBN FROM [dbo].[books_authors] ba JOIN [dbo].[reviews] r "
					+ "ON ba.ISBN = r.ISBN WHERE ba.author = :author and r.rating >= :rating "
					+ "GROUP BY ba.ISBN";
			
			List<Book> books = new ArrayList<>(); 
			em.createNativeQuery(query).setParameter("author", author).setParameter("rating", rating)
					.getResultList().forEach(e -> {
					books.add(em.find(Book.class, e));
			});
			return books;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public Map<String, Long> countBooksByAuthor() {
		try {
			em = emf.createEntityManager();
			String query = "SELECT au, COUNT(b) FROM BookTranslation b JOIN b.authors au GROUP BY au ORDER BY au ASC";
			Map<String, Long> books = new LinkedHashMap<>();
			em.createQuery(query, Object[].class).getResultList().forEach(e -> {
				Object[] o = (Object[]) e;
                books.put((String) o[0], Long.parseLong(o[1].toString()));
			});
           return books;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return null;
	}
	
//	Cập nhật thêm một lượt đánh giá cho một cuốn sách, cập nhật thành công nếu cuốn
//	sách và người đọc đã tồn tại, rating phải từ 1 đến 5 và bình luận không được để trống hay rỗng.
//	+ updateReviews(isbn: String, readerID: String, rating: int, comment: String): boolen
	public boolean updateReviews(String isbn, String readerID, int rating, String comment) {
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();

			Book book = em.find(Book.class, isbn);
			Person person = em.find(Person.class, readerID);

			if (book != null && person != null) {
				if (rating < 1 || rating > 5 || comment == null || comment.isEmpty()) {
					return false;
				}
				else {
					Reviews reviews = new Reviews();
					reviews.setBook(book);
					reviews.setPerson(person);
					reviews.setRating(rating);
					reviews.setComment(comment);
					em.persist(reviews);
					em.getTransaction().commit();
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return false;
	}
}
