package poly.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.entity.Account;
import poly.entity.Bill;
import poly.entity.Tables;

@Service
public class BillDAO {
	@Autowired
	public SessionFactory factory;

	public void saveBill(Bill bill) {
		Session session = factory.openSession();
		Transaction tran = session.beginTransaction();
		try {
			session.save(bill);
			tran.commit();

		} catch (NullPointerException e) {
			System.out.println(e);
			tran.rollback();
		} finally {
			session.close();
		}
	}

	public Bill getBillByTable(String idTable) {
		Session session = factory.openSession();
		Transaction tran = session.beginTransaction();
		try {
			String hql = "FROM Bill b where b.idTable.idTable like :key and b.status = 0";
			Query q = session.createQuery(hql);
			q.setParameter("key", idTable);
			List<Bill> list = q.list();
			return list.get(0);
		} catch (Exception e) {
			// System.out.println("Hello"+e);
			tran.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	public List<Bill> getTableStatus() {
		Session session = factory.openSession();
		Transaction tran = session.beginTransaction();
		try {
			String hql = "FROM Bill b where b.status = 0";
			Query q = session.createQuery(hql);
			return q.list();
		} catch (Exception e) {
			// System.out.println("Hello"+e);
			tran.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	public void payment(String idTable) {
		Session session = factory.openSession();
		Transaction tran = session.beginTransaction();
		try {		
			String hql = "update Bill b set b.status = 1 where b.idTable.idTable like :key";
			Query q = session.createQuery(hql);
			q.setParameter("key", idTable);
			q.executeUpdate();
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
		} finally {
			session.close();
		}
	}

}
