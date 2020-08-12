package poly.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.entity.Tables;

@Transactional
@Service
public class TableDAO {
	@Autowired
	SessionFactory factory;
	public List<Tables> getAllTable(){
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Tables";
			Query q = session.createQuery(hql);
			return q.list();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public Tables findById(String id) {
		Session session = factory.getCurrentSession();
		return (Tables) session.get(Tables.class, id);
	}
	
}
