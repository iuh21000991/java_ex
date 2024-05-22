package entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "positions")
public class Position {
	@Id
	@Column(name = "position_id")
	private String id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "salary")
	private double salary;
	@Column(name = "number")
	private int number;
	

	@OneToMany(mappedBy = "position")
	@ToString.Exclude
	private List<Candidate> candidates;
	

	@OneToMany(mappedBy = "position")
	@ToString.Exclude
	private List<Experience> experiences;
}
