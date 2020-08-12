package poly.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
public class Account {
	@Id
	@Column(name = "Username")
	private String username;
	
	@Column(name="Password")
	private String password;
	
	@Column(name = "Role")
	private Integer roles;
	
	@Column(name = "Fullname")
	private String fullname;
	
	@OneToMany(mappedBy = "username", fetch = FetchType.EAGER)
	private Collection<Bill> bill;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public Integer getRoles() {
		return roles;
	}

	public void setRoles(Integer roles) {
		this.roles = roles;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Collection<Bill> getBill() {
		return bill;
	}

	public void setBill(Collection<Bill> bill) {
		this.bill = bill;
	}

	public Account(String username, String password, int role, String fullname, Collection<Bill> bill) {
		super();
		this.username = username;
		this.password = password;
		this.roles = role;
		this.fullname = fullname;
		this.bill = bill;
	}
	public Account(String username, String password, int role, String fullname) {
		super();
		this.username = username;
		this.password = password;
		this.roles = role;
		this.fullname = fullname;
	}
	public Account() {
		super();
	}
	
}
