package poly.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.entity.Account;

@Service
public class AccountDAO {
	@Autowired
	SessionFactory factory;
	public Account findById(String id) {
		Session session = factory.getCurrentSession();
		return (Account) session.get(Account.class, id);
	}
}
