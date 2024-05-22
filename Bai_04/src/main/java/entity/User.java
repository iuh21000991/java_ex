package entity;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lớp user có các thuộc tính gồm: username(String) {id}, password(String), email(String)
// Lớp user có các phương thức getter, setter, constructor không tham số, constructor đầy đủ tham số
// Áp dụng các annotation cần thiết để map lớp này với bảng users trong database
// Đặc biệt bảng users bên SQL sẽ có thêm một thuộc tính là role thuộc tính này đươc lấy từ Lớp Admin và lớp  Admin không có bất kỳ thuộc tính nào và kế thừ từ lớp User
// Không có bảng Admin trong database
// Sau đây hãy là cách tạo Lớp User để thực hiện yêu cầu trên

@DiscriminatorColumn(name = "role")
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@Column(name = "user_name")
	private String username;
	@Column
	private String password;
	@Column
	private String email;
	
}
