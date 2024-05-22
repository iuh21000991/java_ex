package entity;

// Đây là Class Post có các thuộc tính gồm: id(String), title(String), content(String), views(Integer), likes(Integer), shares(Integer)
// Đây là Class Post không kế thừa từ Class User
// Lớp Post có các phương thức getter, setter, constructor không tham số, constructor đầy đủ tham số
// Lớp post khi chuyển qua SQL sẽ có thêm 2 thuộc tính của lớp Approval là approvedDate(LocalDate) và status(Status)
// Lớp Approval không có bảng trong database
// Lớp Status có các giá trị là PENDING, APPROVED, REJECTED
// Lớp post khi qua bên SQL sẽ có 2 thuộc tính nữa là admin_name và user_name để lưu thông tin về người tạo bài post
// Giữa bảng post và bảng users có mối quan hệ n-n với người có vai trò là User còn có quan hệ 1-n với người có vai trò là Admin
// Khi này sẽ có 1 bảng là Comment có các thuộc tính: comment_date, content, likes, post_id, user_name
// Áp dụng các annotation cần thiết để map lớp này với bảng posts trong database


import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Post {
	@Id
	@Column(name = "post_id")
	private String id;
	@Column
	private String title;
	@Column
	private String contents;
	@Column
	private Integer views;
	@Column
	private Integer likes;
	@Column
	private Integer shares;
	// 2 thuộc tính admin_name và user_name để lưu thông tin về người tạo bài post được lấy từ lớp User
	// thuộc tính admin_name sẽ lấy từ lớp Admin với quan hệ 1-n
	@Embedded
	private Approval approval;
	
	
	@ManyToOne
	@JoinColumn(name = "admin_name")
	private Admin admin;
	
	// thuộc tính user_name sẽ lấy từ lớp User với quan hệ 1-n
	@ManyToOne
	@JoinColumn(name = "user_name")
	private User user;
	
	

	
}
