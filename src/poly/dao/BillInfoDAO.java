package poly.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.entity.Billinfo;

@Service
public class BillInfoDAO {
	@Autowired
	SessionFactory factory;
	public void saveBillInfo(Billinfo bi) {
		Session session = factory.openSession();
		Transaction tran = session.beginTransaction();
		try {
			session.save(bi);
			tran.commit();
		} catch (Exception e) {
		//	System.out.println(e);
			tran.rollback();
			// TODO: handle exception
		}
		finally {
			session.close();
		}
		
	}
}
