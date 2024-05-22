package dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import entity.Candidate;
import entity.Certificate;
import entity.Position;

public interface CandidateDAO {
//	Liệt kê danh sách các vị trí công việc khi biết tên vị trí (tìm tương đối) và mức lương khoảng từ,
//	kết quả sắp xếp theo tên vị trí công việc.
// + listPositions(name: String, salaryFrom: double, salaryTo: double): List<Position>
	public List<Position> listPositions(String name, double salaryFrom, double salaryTo);
//	 Liệt kê danh sách các ứng viên và số công ty mà các ứng viên này từng làm.
//	 + listCadidatesByCompanies() : Map<Candidate, Long>
	public Map<Candidate, Long> listCadidatesByCompanies();
// + listCandidatesWithLongestWorking (): Map<Candidate, Position>
	public Map<Candidate, Position> listCandidatesWithLongestWorking();
// Thêm một ứng viên mới. Trong đó mã số ứng viên phải bắt đầu là C, theo sau ít nhất là 3 ký số.
	public boolean addCandidate(Candidate candidate);
	
// Tính số năm làm việc trên một vị trí công việc nào đó khi biết mã số ứng viên.
//	+ listYearsOfExperienceByPosition(candidateID: String): Map<Position, Integer>
	public Map<Position, Integer> listYearsOfExperienceByPosition(String candidateID);
	
//	Liệt kê danh sách các ứng viên và danh sách bằng cấp của từng ứng viên.
//	+ listCadidatesAndCertificates(): Map<Candidate, Set<Certificate >>
	public Map<Candidate, Set<Certificate>> listCadidatesAndCertificates();
}
