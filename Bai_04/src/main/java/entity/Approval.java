package entity;
// Đây là Class Approval không có bảng trong database
// Lớp Status có các giá trị là PENDING, APPROVED, REJECTED
// Lớp này không phải là POST

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lớp này được đem vaò lớp Post để lưu thông tin không phải là bảng trong database
// Class này không phải là bảng trong database
// Class này sẽ được nhúng vào Class Post




@Embeddable
public class Approval {
	@Column(name = "approved_date")
	private String approvedDate;

	@Column
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public enum Status {
		PENDING, APPROVED, REJECTED;
	}
}
