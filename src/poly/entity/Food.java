package poly.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Food")
public class Food {
	@Id
	@Column(name = "IDFood")
	private String id;
	
	@Column(name="nameFood")
	private String nameFood;
	
	@ManyToOne
	@JoinColumn(name = "IDCaterogy")
	private Caterogy idCategory;
	
	@Column(name = "Image")
	private String image;
	
	@Column(name = "Price")
	private double price;
	
	@OneToMany(mappedBy = "idFood", fetch = FetchType.EAGER)
	private Collection<Billinfo> billinfo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNameFood() {
		return nameFood;
	}

	public void setNameFood(String nameFood) {
		this.nameFood = nameFood;
	}

	public Caterogy getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Caterogy idCategory) {
		this.idCategory = idCategory;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Collection<Billinfo> getBillinfo() {
		return billinfo;
	}

	public void setBillinfo(Collection<Billinfo> billinfo) {
		this.billinfo = billinfo;
	}

	public Food(String id, String nameFood, Caterogy idCategory, String image, double price,
			Collection<Billinfo> billinfo) {
		super();
		this.id = id;
		this.nameFood = nameFood;
		this.idCategory = idCategory;
		this.image = image;
		this.price = price;
		this.billinfo = billinfo;
	}

	public Food() {
		super();
	}
	
}
