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
import com.asama.dao.MachineDao;
import com.asama.dao.MachineDaoImpl;
import com.asama.model.ComputerMachine;
import com.asama.model.UsedMachine;
import com.asama.utils.INetUtils;

@WebServlet("/machine")
public class MachineController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher;
	private MachineDao machineDao;
	private CustomerDao customerDao;

	public MachineController() {
		machineDao = new MachineDaoImpl();
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
			getMachineInfo(request, response);
			break;
		case "list":
			getListComputerMachines(request, response);
			break;
		case "use":
			getUseMachine(request, response);
			break;
		default:
			getListComputerMachines(request, response);
			break;
		}
	}

	private void requestUseMachine(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String customerId = request.getParameter("customerId");
		String machineCode = request.getParameter("machineCode");
		Date startDate = INetUtils.str2Date(request.getParameter("start_date"));
		Time startTime = INetUtils.str2Time(request.getParameter("start_time"));
		int useTime = Integer.parseInt(request.getParameter("use_time"));

		UsedMachine usedMachine = new UsedMachine(customerId, machineCode, startDate, startTime, useTime);

		if (machineDao.insertUsedMachine(usedMachine)) {
			request.setAttribute("NOTIFICATION", "Đăng ký sử dụng thành công");
		} else {
			request.setAttribute("NOTIFICATION", "Đăng ký sử dụng thất bại");
		}
		getUseMachine(request, response);
	}

	private void getUseMachine(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> listMachineCode = machineDao.getListFreeMachine();
		List<String> listCustomerName = customerDao.getListCustomerId();

		request.setAttribute("listMachine", listMachineCode);
		request.setAttribute("listCustomer", listCustomerName);
		dispatcher = request.getRequestDispatcher("use_machine.jsp");
		dispatcher.forward(request, response);
	}

	private void getListComputerMachines(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<ComputerMachine> computerMachines = machineDao.getListComputerMachines();

		request.setAttribute("listComputer", computerMachines);
		dispatcher = request.getRequestDispatcher("list_machine.jsp");
		dispatcher.forward(request, response);
	}

	private void saveMachine(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		String pos = request.getParameter("postion");

		ComputerMachine computerMachine = new ComputerMachine();

		if (code == null || code.length() < 5) {
			request.setAttribute("NOTIFICATION", "Vui lòng nhập đầy đủ thông tin");
		} else if (pos == null || pos.length() < 1) {
			request.setAttribute("NOTIFICATION", "Vui lòng nhập vị trí");
		} else {
			computerMachine.setCode(code);
			computerMachine.setPosition(pos);
			computerMachine.setStatus(false);

			if (machineDao.insertComputerMachine(computerMachine)) {
				request.setAttribute("NOTIFICATION", "Thêm thành công");
			} else {
				request.setAttribute("NOTIFICATION", "Thêm thất bại :((");
			}
		}

		showAdd(request, response);
	}

	private void updateMachine(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String code = request.getParameter("code");
		String stt = request.getParameter("status");
		boolean status = !stt.equals("Tắt");

		ComputerMachine computerMachine = new ComputerMachine();

		if (code == null) {
			request.setAttribute("NOTIFICATION", "Vui lòng nhập đầy đủ thông tin");
		} else {
			computerMachine.setCode(code);
			computerMachine.setPosition(request.getParameter("postion"));
			computerMachine.setStatus(status);

			if (machineDao.updateComputerMachine(computerMachine)) {
				request.setAttribute("NOTIFICATION", "Chỉnh sửa thành công");
			} else {
				request.setAttribute("NOTIFICATION", "Chỉnh sửa thất bại :((");
			}
		}

		getMachineInfo(request, response);
	}

	private void getMachineInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String code = request.getParameter("code");
		ComputerMachine computerMachine = machineDao.getComputerMachine(code);
		request.setAttribute("computer", computerMachine);
		dispatcher = request.getRequestDispatcher("edit_machine.jsp");
		dispatcher.forward(request, response);
	}

	private void showAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dispatcher = request.getRequestDispatcher("new_machine.jsp");
		dispatcher.forward(request, response);
	}

	private void deleteMachine(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		machineDao.deleteComputerMachine(code);

		dispatcher = request.getRequestDispatcher("list_machine.jsp");
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
			saveMachine(request, response);
			break;
		case "edit":
			updateMachine(request, response);
			break;
		case "delete":
			deleteMachine(request, response);
			break;
		case "use":
			requestUseMachine(request, response);
			break;
		default:
			getListComputerMachines(request, response);
			break;
		}
	}

}
