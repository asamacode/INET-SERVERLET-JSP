package com.asama.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asama.dao.CustomerDao;
import com.asama.dao.CustomerDaoImpl;
import com.asama.dao.ServiceDao;
import com.asama.dao.ServiceDaoImpl;
import com.asama.model.Service;
import com.asama.model.UsedService;
import com.asama.utils.INetUtils;

@WebServlet("/service")
public class ServiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher;
	private ServiceDao serviceDao;
	private CustomerDao customerDao;

	public ServiceController() {
		super();
		serviceDao = new ServiceDaoImpl();
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
			getServiceInfo(request, response);
			break;
		case "list":
			getListServices(request, response);
			break;
		case "use":
			getUseService(request, response);
			break;
		default:
			getListServices(request, response);
			break;
		}
	}

	private void showAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dispatcher = request.getRequestDispatcher("new_service.jsp");
		dispatcher.forward(request, response);
	}

	private void getServiceInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String code = request.getParameter("code");
		Service service = serviceDao.getSingleService(code);
		request.setAttribute("service", service);
		dispatcher = request.getRequestDispatcher("edit_service.jsp");
		dispatcher.forward(request, response);
	}

	private void getUseService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> listServiceCode = serviceDao.getListServiceName();
		List<String> listCustomerName = customerDao.getListCustomerId();

		request.setAttribute("listService", listServiceCode);
		request.setAttribute("listCustomer", listCustomerName);
		dispatcher = request.getRequestDispatcher("use_service.jsp");
		dispatcher.forward(request, response);
	}

	private void getListServices(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Service> services = serviceDao.getListServices();

		request.setAttribute("listService", services);
		dispatcher = request.getRequestDispatcher("list_service.jsp");
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
			saveService(request, response);
			break;
		case "edit":
			updateService(request, response);
			break;
		case "delete":
			deleteService(request, response);
			break;
		case "use":
			requestUseService(request, response);
			break;
		default:
			getListServices(request, response);
			break;
		}
	}

	private void requestUseService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String serviceCode = request.getParameter("service_code");
		String customerId = request.getParameter("customer_id");
		String useDate = request.getParameter("use_date");
		String useTime = request.getParameter("use_time");
		int quantity = Integer.parseInt(request.getParameter("use_quantity"));

		Date date = INetUtils.str2Date(useDate);
		Time time = INetUtils.str2Time(useTime);

		UsedService usedService = new UsedService(customerId, serviceCode, date, time, quantity);

		if (serviceDao.saveUsedService(usedService)) {
			request.setAttribute("NOTIFICATION", "Đăng ký sử dụng dịch vụ thành công");
		} else {
			request.setAttribute("NOTIFICATION", "Đăng ký sử dụng dịch vụ thất bại");
		}

		getUseService(request, response);
	}

	private void deleteService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		serviceDao.deleteService(code);

		dispatcher = request.getRequestDispatcher("list_service.jsp");
		dispatcher.forward(request, response);
	}

	private void updateService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String code = request.getParameter("code");

		Service service = new Service();

		if (code == null) {
			request.setAttribute("NOTIFICATION", "Vui lòng nhập đầy đủ thông tin");
		} else {
			service.setName(request.getParameter("service_name"));
			service.setPrice(Float.valueOf(request.getParameter("service_price")));
			service.setUnit(request.getParameter("service_unit"));

			if (serviceDao.updateService(service)) {
				request.setAttribute("NOTIFICATION", "Chỉnh sửa thành công");
			} else {
				request.setAttribute("NOTIFICATION", "Chỉnh sửa thất bại :((");
			}
		}

		getServiceInfo(request, response);
	}

	private void saveService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("service_code");
		String name = request.getParameter("service_name");
		Float price = Float.valueOf(request.getParameter("service_price"));
		Service service = new Service();

		if (code == null || code.length() < 5) {
			request.setAttribute("NOTIFICATION", "Vui lòng nhập đầy đủ thông tin");
		} else if (name == null || name.length() < 5) {
			request.setAttribute("NOTIFICATION", "Tên dịch vụ phải trên 5 ký tự");
		} else if (price == null || price < 1000) {
			request.setAttribute("NOTIFICATION", "Giá dịch vụ phải lớn hơn 1000");
		} else {
			service.setCode(code);
			service.setName(name);
			service.setPrice(price);
			service.setUnit(request.getParameter("service_unit"));

			if (serviceDao.insertService(service)) {
				request.setAttribute("NOTIFICATION", "Thêm thành công");
			} else {
				request.setAttribute("NOTIFICATION", "Thêm thất bại :((");
			}
		}

		showAdd(request, response);
	}

}
