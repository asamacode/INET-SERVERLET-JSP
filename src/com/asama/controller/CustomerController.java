package com.asama.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asama.dao.CustomerDao;
import com.asama.dao.CustomerDaoImpl;
import com.asama.model.Customer;

@WebServlet("/customer")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher;
	private CustomerDao customerDao;

	public CustomerController() {
		customerDao = new CustomerDaoImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action == null) {
			action = "list";
		}
		switch (action) {
		case "add":
			showAdd(request, response);
			break;
		case "edit":
			getCustomerInfo(request, response);
			break;
		case "list":
			getListCustomer(request, response);
			break;
		case "search":
			searchCustomer(request, response);
			break;
		default:
			getListCustomer(request, response);
			break;
		}
	}

	private void getListCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Customer> customers = customerDao.getListCustomers();

		request.setAttribute("listCustomer", customers);
		dispatcher = request.getRequestDispatcher("list_customer.jsp");
		dispatcher.forward(request, response);
	}

	private void saveCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("customer_id");
		String name = request.getParameter("name");
		String addr = request.getParameter("address");
		String phone = request.getParameter("customer_phone");
		String email = request.getParameter("customer_email");

		Customer customer = new Customer();

		if (id == null || id.length() < 7) {
			request.setAttribute("NOTIFICATION", "Vui lòng nhập đầy đủ thông tin");
		} else if (name == null || name.length() < 8) {
			request.setAttribute("NOTIFICATION", "Họ tên phải trên 8 ký tự");
		} else if (addr == null || addr.length() < 15) {
			request.setAttribute("NOTIFICATION", "Địa chỉ phải trên 15 ký tự");
		} else if (phone == null || phone.length() < 10 || phone.length() > 11) {
			request.setAttribute("NOTIFICATION", "Số điện thoại không hợp lệ");
		} else if (email == null || email.length() < 5) {
			request.setAttribute("NOTIFICATION", "Email không hợp lệ");
		} else {
			customer.setId(id);
			customer.setName(name);
			customer.setAddress(addr);
			customer.setPhone(phone);
			customer.setEmail(email);

			if (customerDao.insertCustomer(customer)) {
				request.setAttribute("NOTIFICATION", "Thêm thành công");
			} else {
				request.setAttribute("NOTIFICATION", "Thêm thất bại :((");
			}
		}

		showAdd(request, response);
	}

	private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("customer_id");

		Customer customer = new Customer();

		if (id == null) {
			request.setAttribute("NOTIFICATION", "Vui lòng nhập đầy đủ thông tin");
		} else {
			customer.setId(id);
			customer.setName(request.getParameter("customer_name"));
			customer.setAddress(request.getParameter("customer_address"));
			customer.setPhone(request.getParameter("customer_phone"));
			customer.setEmail(request.getParameter("customer_email"));

			if (customerDao.updateCustomer(customer)) {
				request.setAttribute("NOTIFICATION", "Chỉnh sửa thành công");
			} else {
				request.setAttribute("NOTIFICATION", "Chỉnh sửa thất bại :((");
			}
		}

		getCustomerInfo(request, response);
	}

	private void getCustomerInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("customer_id");
		Customer customer = customerDao.getSingleCustomer(id);
		request.setAttribute("customer", customer);
		dispatcher = request.getRequestDispatcher("edit_customer.jsp");
		dispatcher.forward(request, response);
	}

	private void showAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dispatcher = request.getRequestDispatcher("new_customer.jsp");
		dispatcher.forward(request, response);
	}

	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("customer_id");
		customerDao.deleteCustomer(id);

		dispatcher = request.getRequestDispatcher("list_customer.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding("utf-8");
		}
		String action = request.getParameter("action");

		if (action == null) {
			action = "list";
		}
		switch (action) {
		case "add":
			saveCustomer(request, response);
			break;
		case "edit":
			updateCustomer(request, response);
			break;
		case "delete":
			deleteCustomer(request, response);
			break;
		case "search":
			searchCustomer(request, response);
			break;
		default:
			getListCustomer(request, response);
			break;
		}
	}

	private void searchCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("q");

		if (name != null) {
			List<Customer> customers = customerDao.filterListByName(name);
			request.setAttribute("listCustomer", customers);

			dispatcher = request.getRequestDispatcher("list_customer.jsp");
			dispatcher.forward(request, response);
		}

	}

}
