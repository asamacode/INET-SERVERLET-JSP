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

import com.asama.common.INetUtils;
import com.asama.model.bean.Service;
import com.asama.model.bean.UsedService;
import com.asama.model.bo.ServiceBO;

@WebServlet("/service")
public class ServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher;
	private ServiceBO serviceBO;

	public ServiceServlet() {
		super();
		serviceBO = new ServiceBO();
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
		Service service = serviceBO.getService(code);
		request.setAttribute("service", service);
		dispatcher = request.getRequestDispatcher("edit_service.jsp");
		dispatcher.forward(request, response);
	}

	private void getUseService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> listServiceCode = serviceBO.getListServiceCode();
		List<String> listCustomerName = serviceBO.getListCustomerId();

		request.setAttribute("listService", listServiceCode);
		request.setAttribute("listCustomer", listCustomerName);
		dispatcher = request.getRequestDispatcher("use_service.jsp");
		dispatcher.forward(request, response);
	}

	private void getListServices(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Service> services = serviceBO.getServices();

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
		String quantity = request.getParameter("use_quantity");

		if (serviceBO.isValidateInfo(useDate, useTime, quantity)) {
			Date date = INetUtils.str2Date(useDate);
			Time time = INetUtils.str2Time(useTime);

			UsedService usedService = new UsedService(customerId, serviceCode, date, time, Integer.parseInt(quantity));
			if (serviceBO.requestUseService(usedService)) {
				request.setAttribute("NOTIFICATION", "Đăng ký sử dụng dịch vụ thành công");
			} else {
				request.setAttribute("NOTIFICATION", "Đăng ký sử dụng dịch vụ thất bại");
			}

		} else {
			request.setAttribute("NOTIFICATION", "Vui lòng nhập thông tin hợp lệ");
		}

		getUseService(request, response);
	}

	private void deleteService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		serviceBO.deleteService(code);

		dispatcher = request.getRequestDispatcher("list_service.jsp");
		dispatcher.forward(request, response);
	}

	private void updateService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String code = request.getParameter("code");
		String name = request.getParameter("service_name");
		String price = request.getParameter("service_price");
		String unit = request.getParameter("service_unit");
		Service service = new Service();

		if (!serviceBO.isValidService(code, name, price, unit)) {
			request.setAttribute("NOTIFICATION", "Vui lòng nhập đầy đủ thông tin");
		} else {
			service.setName(name);
			service.setPrice(Float.valueOf(price));
			service.setUnit(unit);

			if (serviceBO.updateService(service)) {
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
		String price = request.getParameter("service_price");
		String unit = request.getParameter("service_unit");
		Service service = new Service();

		if (!serviceBO.isValidService(code, name, price, unit)) {
			request.setAttribute("NOTIFICATION", "Vui lòng nhập đầy đủ thông tin");
		} else {
			service.setCode(code);
			service.setName(name);
			service.setPrice(Float.valueOf(price));
			service.setUnit(unit);

			if (serviceBO.createService(service)) {
				request.setAttribute("NOTIFICATION", "Thêm thành công");
			} else {
				request.setAttribute("NOTIFICATION", "Thêm thất bại :((");
			}
		}

		showAdd(request, response);
	}

}
