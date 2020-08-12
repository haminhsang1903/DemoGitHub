package poly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="BillInfo")
public class Billinfo {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@ManyToOne
	@JoinColumn(name="IDFood")
	private Food idFood;
	
	@ManyToOne
	@JoinColumn(name="IDBill")
	private Bill idBill;
	
	@Column(name="Count")
	private int count;
	
	@Column(name="Status")
	private boolean status;
	
	public Billinfo() {
		super();
	}

	public Billinfo(Food idFood, Bill idBill, int count, boolean status) {
		super();
		this.idFood = idFood;
		this.idBill = idBill;
		this.count = count;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Food getIdFood() {
		return idFood;
	}

	public void setIdFood(Food idFood) {
		this.idFood = idFood;
	}

	public Bill getIdBill() {
		return idBill;
	}

	public void setIdBill(Bill idBill) {
		this.idBill = idBill;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	
}
