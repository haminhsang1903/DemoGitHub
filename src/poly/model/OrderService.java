package poly.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
	private static final String SESSION_KEY_SHOPPING_CART = "OrderFood";
	public Order getCart(HttpSession session){
		Order order = (Order) session.getAttribute(SESSION_KEY_SHOPPING_CART);

        if(order == null){
        	order = new Order();
            setCart(session, order);
        }

        return order;
    }

    public void setCart(HttpSession session, Order order){
        session.setAttribute(SESSION_KEY_SHOPPING_CART, order);
    }

    public void removeCart(HttpSession session){
        session.removeAttribute(SESSION_KEY_SHOPPING_CART);
    }
}
