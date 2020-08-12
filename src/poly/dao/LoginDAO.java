package poly.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.entity.Account;

@Transactional
@Service
public class LoginDAO {
	@Autowired
	SessionFactory factory;
	public boolean checkLogin(Account ac) {
		if(getByID(ac.getUsername()) != null && getByID(ac.getUsername()).getPassword().equals(ac.getPassword())) {
			return true;
		}
		return false;
	}
	public Account getByID(String id) {
		Account ac = null;
		try {
			Session session = factory.openSession();
			Transaction tran = session.beginTransaction();
			ac = (Account) session.get(Account.class, id);	
			tran.commit();
			session.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ac;
	}
	public List<Account> getAll(){
		try {
			Session session = factory.openSession();
			Transaction tran = session.beginTransaction();
			String hql = "FROM Account";
			Query q = session.createQuery(hql);
			return q.list();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
