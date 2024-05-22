package dao;

import java.util.List;
import java.util.Map;

import entity.Book;

public interface BookDAO {
//	+ listRatedBooks(author: String, rating: int): List<Book>
	public List<Book> listRatedBooks(String author, int rating);
//Thống kê số cuốn sách được dịch sang ngôn ngữ khác của từng tác giả, kết quả sắp
//	xếp theo tên tác giả.
//	+ countBooksByAuthor(): Map<String, Long>
	public Map<String, Long> countBooksByAuthor();
	
//	Cập nhật thêm một lượt đánh giá cho một cuốn sách, cập nhật thành công nếu cuốn
//	sách và người đọc đã tồn tại, rating phải từ 1 đến 5 và bình luận không được để trống hay rỗng.
//	+ updateReviews(isbn: String, readerID: String, rating: int, comment: String): boolen
	public boolean updateReviews(String isbn, String readerID, int rating, String comment);
}	
