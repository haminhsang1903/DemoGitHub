package poly.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.entity.Food;

@Transactional
@Service
public class MenuDAO {
	@Autowired
	SessionFactory factory;
	public List<Food> getAllMenu(){
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Food";
			Query q = session.createQuery(hql);
			return q.list();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public Food getById(String id) {
		Session session = factory.getCurrentSession();
		return (Food)session.get(Food.class, id);
	}
}
