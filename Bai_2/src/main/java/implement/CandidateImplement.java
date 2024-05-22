package implement;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dao.CandidateDAO;
import entity.Candidate;
import entity.Certificate;
import entity.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CandidateImplement implements CandidateDAO {

	private EntityManagerFactory emf;
	private EntityManager em;

	public CandidateImplement() {
		emf = Persistence.createEntityManagerFactory("MSSQL");
	}

	@Override
	public List<Position> listPositions(String name, double salaryFrom, double salaryTo) {
		try {
			em = emf.createEntityManager();
			List<Position> list = em.createQuery("SELECT p FROM Position p WHERE (p.name LIKE :name) AND (p.salary >= :salaryFrom AND  p.salary <= :salaryTo)", Position.class)
									.setParameter("name", "%"+name+"%")
									.setParameter("salaryFrom", salaryFrom)
									.setParameter("salaryTo", salaryTo)
									.getResultList();
			return list;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			em.close();
		}

		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<Candidate, Long> listCadidatesByCompanies() {
		try {
            em = emf.createEntityManager();
            List<Object[]> list = em.createQuery("SELECT c, COUNT(e.companyName) From Candidate c JOIN c.experiences e Group By c").getResultList();
            Map<Candidate, Long> map = new HashMap<>();
			for (Object[] objects : list) {
				map.put((Candidate) objects[0], (Long) objects[1]);
			}
            return map;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            em.close();
        }
		return null;
	}
	@SuppressWarnings("unchecked")
	public Map<Candidate, Position> listCandidatesWithLongestWorking() {
		try {
			String query = "SELECT c, p FROM Candidate c JOIN c.experiences e JOIN e.position p "
					+ "WHERE DATEDIFF(DAY, e.fromDate, e.toDate) = ("
					+ "    SELECT MAX(DATEDIFF(DAY, e2.fromDate, e2.toDate))"
					+ "    FROM Experience e2"
					+ "    WHERE e2.candidate.id = c.id)";
			em = emf.createEntityManager();
			List<Object[]> list = em.createQuery(query).getResultList();
			Map<Candidate, Position> map = new HashMap<>();
			for (Object[] objects : list) {
				map.put((Candidate) objects[0], (Position) objects[1]);
			}
			return map;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			em.close();
		}
		return null;
	}
	
	public boolean addCandidate(Candidate candidate) {
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(candidate);
			em.getTransaction().commit();
		}catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
		}
		finally {
			em.close();
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public Map<Position, Integer> listYearsOfExperienceByPosition(String candidateID) {
		try {
			em = emf.createEntityManager();
			String query = "SELECT e.position, DATEDIFF(YEAR, e.fromDate, e.toDate) FROM "
					+ "Experience e WHERE e.candidate.id = :candidateID";
			List<Object[]> list = em.createQuery(query)
									.setParameter("candidateID", candidateID)
									.getResultList();
		
			Map<Position, Integer> map = new LinkedHashMap<>();
			for (Object[] objects : list) {
				if (map.containsKey((Position) objects[0])) {
					map.put((Position) objects[0], map.get((Position) objects[0]) + Integer.parseInt(objects[1].toString()));
				} else {
					map.put((Position) objects[0], Integer.parseInt(objects[1].toString()));
				}
			}
			return map;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			em.close();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Map<Candidate, Set<Certificate>> listCadidatesAndCertificates() {
		try {
			em = emf.createEntityManager();
			String query = "SELECT c, c.certificates FROM Candidate c";
			List<Object[]> list = em.createQuery(query).getResultList();
			Map<Candidate, Set<Certificate>> map = new LinkedHashMap<>();
			for (Object[] objects : list) {
				Certificate certificate = (Certificate) objects[1];
				if (map.containsKey((Candidate) objects[0])) {
					Set<Certificate> set = map.get((Candidate) objects[0]);
                    set.add(certificate);
                    map.put((Candidate) objects[0], set);
                } else {
                	Set<Certificate> set = new LinkedHashSet<>();
                	set.add(certificate);
                    map.put((Candidate) objects[0], set);
                 }
			}
			return map;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		finally {
			em.close();
		}
		return null;
	}
}
