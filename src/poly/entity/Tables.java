package poly.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Tables")
public class Tables {
	@Id
	@Column(name = "IDTable")
	private String idTable;
	
	@Column(name="NameTable")
	private String nameTable;
	
	
	@OneToMany(mappedBy = "idTable", fetch = FetchType.EAGER)
	private Collection<Bill> bill;

	public String getIdTable() {
		return idTable;
	}

	public void setIdTable(String idTable) {
		this.idTable = idTable;
	}

	public String getNameTable() {
		return nameTable;
	}

	public void setNameTable(String nameTable) {
		this.nameTable = nameTable;
	}

	public Collection<Bill> getBill() {
		return bill;
	}

	public void setBill(Collection<Bill> bill) {
		this.bill = bill;
	}

	public Tables(String idTable, String nameTable, Collection<Bill> bill) {
		super();
		this.idTable = idTable;
		this.nameTable = nameTable;
		this.bill = bill;
	}
	public Tables(String idTable, String nameTable) {
		super();
		this.idTable = idTable;
		this.nameTable = nameTable;
	}
	public Tables() {
		super();
	}
	
}
