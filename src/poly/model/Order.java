package poly.model;

import java.util.ArrayList;
import java.util.List;

import poly.entity.Food;
import poly.entity.Tables;

public class Order {
	
	public static Tables table = null;
	
	private List<OrderDetail> items;
	private double total;
	
	public Order() {
		items = new ArrayList<OrderDetail>();
		total = 0;
	}
	
	public double getTotal() {
		total = 0;
		for(OrderDetail item : items) {
			total += item.getFood().getPrice() * item.getQuantity();
		}
		return total;
	}
	public OrderDetail getItem(Food food) {
		for(OrderDetail item : items) {
			if(item.getFood().getId().equals(food.getId())) {
				return item;
			}
		}
		return null;
	}
	
	public List<OrderDetail> getItems(){
		return items;
	}
	
	public void addItem(OrderDetail item) {
		addItem(item.getFood(), item.getQuantity());
	}
	
	public void addItem(Food food, int quantity) {
		OrderDetail item = getItem(food);
		if(item != null) {
			item.setQuantity(quantity+item.getQuantity());
		}else {
			item = new OrderDetail(food);
			item.setQuantity(quantity);
			items.add(item);
		}
	}
}
