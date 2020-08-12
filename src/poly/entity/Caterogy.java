package poly.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Category")
public class Caterogy {
	
	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "Name")
	private String name;
	
	@OneToMany(mappedBy = "idCategory", fetch = FetchType.EAGER)
	private Collection<Food> food;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Food> getFood() {
		return food;
	}

	public void setFood(Collection<Food> food) {
		this.food = food;
	}

	public Caterogy(String id, String name, Collection<Food> food) {
		super();
		this.id = id;
		this.name = name;
		this.food = food;
	}

	public Caterogy() {
		super();
	}
	
	
}
