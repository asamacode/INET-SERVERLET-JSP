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
import com.asama.dao.ServiceDao;
import com.asama.dao.ServiceDaoImpl;
import com.asama.model.Service;


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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	
	private void getUseService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> listServiceCode = serviceDao.getListServiceName();
		List<String> listCustomerName = customerDao.getListCustomerId();
		
		request.setAttribute("listMachine", listServiceCode);
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		default:
			getListServices(request, response);
			break;
		}
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
		String code = request.getParameter("code");

		Service service = new Service();

		if (code == null) {
			request.setAttribute("NOTIFICATION", "Vui lòng nhập đầy đủ thông tin");
		} else {
			service.setCode(code);
			service.setName(request.getParameter("service_name"));
			service.setPrice(Float.valueOf(request.getParameter("service_price")));
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
