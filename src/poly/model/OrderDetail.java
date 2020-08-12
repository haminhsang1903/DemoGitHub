package poly.model;

import poly.entity.Food;

public class OrderDetail {
	private Food food;
	private int quantity;
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public OrderDetail(Food food) {
		super();
		this.food = food;
		this.quantity = 1;
	}
	public OrderDetail() {
		super();
	}
	
}
