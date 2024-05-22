package entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Đặc tả bài toán:
//Cho mô hình lớp một phần của ứng dụng hệ thống quản lý tuyển dụng nhân sự trong công ty, được mô tả như sau:
//Công ty có nhiều vị trí công việc (Position) cần tuyển dụng như: Manager, Developer, Tester, Business
//Analyst, … Mỗi vị trí có thể tuyển nhiều nhân sự, mỗi ứng viên ứng tuyển (Candidate) chỉ có thể ứng
//tuyển (APPLY) cho một vị trí công việc.
//Mỗi ứng viên có các bằng cấp (Certificate) mà họ đạt được nhất định và cũng có thể có những kinh
//nghiệm làm việc (Experience) tại các công ty khác nhau. Mỗi kinh nghiệm trên một vị trí làm việc sẽ
//gồm có một số thông tin như: tên công ty (companyName), từ thời gian (fromDate), đến thời gian
//(toDate), mô tả công việc (description).
//Thông tin về vị trí công việc bao gồm: Mã số (id), tên vị trí (name), mô tả công việc (description),
//lương cơ bản (salary) và số lượng tuyển dụng (number).
//Thông tin về ứng viên bao gồm: Mã số (id), họ tên (fullName), năm sinh (yearOfBirth), giới tính
//(gender), email, số điện thoại (phone), mô tả bản thân (description).
//Thông tin về bằng cấp bao gồm: Mã số (id), tên bằng cấp (name), nơi cấp (organization), ngày cấp (issueDate).

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "experiences")
public class Experience {
	@Id
	@Column(name = "company_name")
	private String companyName;
	@Column(name = "from_date")
	private LocalDate fromDate;
	@Column(name = "to_date")
	private LocalDate toDate;
	@Column(name = "description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "candidate_id")
	@Id
	private Candidate candidate;
	@ManyToOne
	@JoinColumn(name = "position_id")
	@Id
	private Position position;
}
