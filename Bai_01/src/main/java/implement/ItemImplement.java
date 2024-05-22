package implement;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import dao.ItemDAO;
import entity.Food;
import entity.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ItemImplement implements ItemDAO {

	private EntityManagerFactory emf;
	private EntityManager em;

	public ItemImplement() {
		emf = Persistence.createEntityManagerFactory("MSSQL");

	}

	@Override
	public boolean addFood(Food food) {
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(food);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return false;
	}

	@Override
	public List<Item> listItems(String supplierName) {
		try {
			em = emf.createEntityManager();
		
			List<Item> items  = em.createQuery("SELECT i FROM Item i JOIN i.ingredients ig WHERE ig.supplierName LIKE :supplierName AND i.onSpecial = true", Item.class)
								.setParameter("supplierName", "%" + supplierName + "%").getResultList();
			return items;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Food, Double> listFoodAndCost() {
		try {
			em = emf.createEntityManager();
			List<Object[]> list = new ArrayList<>();
			list = em.createQuery("SELECT f.id, SUM(ig.quantity * ig.price) + (f.preparationTime + f.servingTime) * 0.2 AS cost "
					+ "FROM Food f JOIN f.ingredients ig GROUP BY f.id, f.preparationTime, f.servingTime ORDER BY cost DESC").getResultList();
			Map<Food, Double> map = new LinkedHashMap<>();
			for (Object[] objects : list) {
				map.put(em.find(Food.class, objects[0]), (Double) objects[1]);
			}
			return map;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			em.close();
		}
	}

}
