package poly.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.entity.Caterogy;

@Service
@Transactional
public class CategoryDAO {
	@Autowired
	SessionFactory factory;
	public List<Caterogy> getAllCategory() {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Caterogy";
			Query q = session.createQuery(hql);
			return q.list();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
