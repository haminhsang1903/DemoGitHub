package poly.entity;

import java.util.Collection;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Bill")
public class Bill {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "IDTable")
	private Tables idTable;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss")
	@Column(name = "DateIn")
	private Date dateIn;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss")
	@Column(name = "DateOut")
	private Date dateOut;

	@Column(name = "Amount")
	private double amount;

	@ManyToOne
	@JoinColumn(name = "Username")
	private Account username;

	@Column(name = "Status")
	private boolean status;

	@OneToMany(mappedBy = "idBill", fetch = FetchType.EAGER)
	private Collection<Billinfo> billinfo;

	public Bill() {
		super();
	}

	public Bill(Tables idTable, Date dateIn, Date dateOut, double amount, Account username, boolean status) {
		super();
		this.idTable = idTable;
		this.dateIn = dateIn;
		this.dateOut = dateOut;
		this.amount = amount;
		this.username = username;
		this.status = status;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Tables getIdTable() {
		return idTable;
	}

	public void setIdTable(Tables idTable) {
		this.idTable = idTable;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Account getUsername() {
		return username;
	}

	public void setUsername(Account username) {
		this.username = username;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Collection<Billinfo> getBillinfo() {
		return billinfo;
	}

	public void setBillinfo(Collection<Billinfo> billinfo) {
		this.billinfo = billinfo;
	}

}
