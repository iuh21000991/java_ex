package entity;

// Đây là Class kế thừa User và sẽ không tạo ra bảng bên SQL
// Khi đưa qua SQL chỉ có bảng User và thêm một cột role
// Cột role sẽ lưu trữ thông tin về loại người dùng là Admin hay User
// Đây là Class Admin kế thừa từ User

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("admin")
@Getter
@Setter
@NoArgsConstructor
public class Admin extends User{

}
