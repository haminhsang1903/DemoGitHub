package poly.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.dao.AccountDAO;
import poly.dao.BillDAO;
import poly.dao.BillInfoDAO;
import poly.dao.CategoryDAO;
import poly.dao.LoginDAO;
import poly.dao.MenuDAO;
import poly.dao.TableDAO;
import poly.entity.Account;
import poly.entity.Bill;
import poly.entity.Billinfo;
import poly.entity.Caterogy;
import poly.entity.Food;
import poly.entity.Tables;
import poly.model.Order;
import poly.model.OrderDetail;
import poly.model.OrderService;
import poly.model.TableService;

@Controller
public class HomeController {
	@Autowired
	LoginDAO daoLG;

	@Autowired
	CategoryDAO daoCate;

	@Autowired
	MenuDAO daoMenu;

	@Autowired
	OrderService orderservice;

	@Autowired
	TableDAO daoTable;

	@Autowired
	BillDAO daoBill;

	@Autowired
	AccountDAO daoAcc;

	@Autowired
	BillInfoDAO daoBillInfo;


	
	@RequestMapping("index")
	public String index() {
		return "index";
	}

	@RequestMapping("{id}order")
	public String order(ModelMap model, HttpSession session, @PathVariable("id") String id) {
		model.addAttribute("getAllFood", daoMenu.getAllMenu());
		session.setAttribute("getInfoTable", daoTable.findById(id));
		return "order";
	}

	@RequestMapping("cook")
	public String cook() {
		return "cook";
	}

	@RequestMapping("account")
	public String account(ModelMap model) {
		model.addAttribute("accountForm", new Account());
		model.addAttribute("getAccount", daoLG.getAll());
		return "account";
	}

	@RequestMapping("table")
	public String table() {
		return "table";
	}

	@RequestMapping("menu")
	public String menu(ModelMap model) {
		model.addAttribute("menuForm", new Food());
		model.addAttribute("getAllMenu", daoMenu.getAllMenu());
		return "menu";
	}

	@RequestMapping("category")
	public String category(ModelMap model) {
		model.addAttribute("getAllCategory", daoCate.getAllCategory());
		model.addAttribute("categoryForm", new Caterogy());
		return "category";
	}

	@RequestMapping("showTable")
	public String showTable(ModelMap model, HttpSession session) {
		changeStatusTable(model);
		return "showTable";
	}

	@RequestMapping("login")
	public String login(ModelMap model) {
		model.addAttribute("account", new Account("thanhhnpc00189", "123", 3, "HUYNH NHAT THANH"));
		return "login";
	}

	@RequestMapping("{username}editAccount")
	public String edit(ModelMap model, @PathVariable("username") String id) {
		model.addAttribute("accountForm", daoLG.getByID(id));
		model.addAttribute("getAccount", daoLG.getAll());
		return "account";
	}

	@RequestMapping("checklogin")
	public String checkLogin(@ModelAttribute("account") Account ac, HttpSession session) {
		if (daoLG.checkLogin(ac)) {
			System.out.println(ac.getUsername() + "\t" + ac.getPassword());
			session.setAttribute("Role", daoLG.getByID(ac.getUsername()).getRoles());
			session.setAttribute("infoAcc", ac);
			return "index";
		}
		return "login";
	}

	@RequestMapping("{id}ordernow")
	public String ordernow(ModelMap model, HttpSession session, @PathVariable("id") String id) {
		Food item = daoMenu.getById(id);
		Order order = orderservice.getCart(session);
		order.addItem(item, 1);
		model.addAttribute("getAllFood", daoMenu.getAllMenu());
		return "order";
	}

	@RequestMapping("fillBill")
	public String sendCook(ModelMap model, HttpSession session) throws ParseException {
		
		Tables tbl = (Tables) session.getAttribute("getInfoTable");
		Account ac = (Account) session.getAttribute("infoAcc");
		Order order = (Order) session.getAttribute("OrderFood");
		// If Bill by ID table co roi thi add billInfo theo
		if (daoBill.getBillByTable(tbl.getIdTable()) != null) {
			for (OrderDetail i : order.getItems()) {
				Billinfo bi = new Billinfo(i.getFood(), daoBill.getBillByTable(tbl.getIdTable()), i.getQuantity(),
						false);
				daoBillInfo.saveBillInfo(bi);
			}
			
		} else {
			Bill bill = new Bill(tbl, getDateNow(), getDateNow(), order.getTotal(), ac, false);
			daoBill.saveBill(bill);
			for (OrderDetail i : order.getItems()) {
				Billinfo bi = new Billinfo(i.getFood(), daoBill.getBillByTable(tbl.getIdTable()), i.getQuantity(),
						false);
				daoBillInfo.saveBillInfo(bi);
			}
			
		}
		orderservice.removeCart(session);
		changeStatusTable(model);
		return "showTable";
	}

	@RequestMapping("{id}pay")
	public String pay(ModelMap model, @PathVariable("id") String id) {	
		daoBill.payment(id);
		changeStatusTable(model);
		return "showTable";
	}
	
	public void changeStatusTable(ModelMap model) {
		List<TableService> statusTable = new ArrayList<TableService>();
		for (Tables i : daoTable.getAllTable()) {
			boolean chk = false;
			for (Bill j : daoBill.getTableStatus()) {			
				if (i.getIdTable().equals(j.getIdTable().getIdTable())) {
					TableService tblS = new TableService(i, true);
					statusTable.add(tblS);
					chk = true;
					break;
				}
			}
			if (!chk) {
				TableService tblS = new TableService(i, false);
				statusTable.add(tblS);
			}
				
		}
		model.addAttribute("getAllTable",statusTable);
		
	}
	
	public Date getDateNow() throws ParseException {
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		Date date = (Date) formatter.parse(dtf.format(now));
		return date;
	}

}
