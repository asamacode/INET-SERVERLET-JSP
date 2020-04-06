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
import com.asama.model.bean.ComputerMachine;
import com.asama.model.bean.UsedMachine;
import com.asama.model.bo.MachineBO;

@WebServlet("/machine")
public class MachineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher;
	private MachineBO machineBO;

	public MachineServlet() {
		machineBO = new MachineBO();
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
		String startDate = request.getParameter("start_date");
		String startTime = request.getParameter("start_time");
		String useTime = request.getParameter("use_time");

		if (machineBO.isValidInfo(startDate, startTime, useTime)) {
			Date date = INetUtils.str2Date(startDate);
			Time time = INetUtils.str2Time(startTime);
			UsedMachine usedMachine = new UsedMachine(customerId, machineCode, date, time, 
					Integer.parseInt(useTime));
			if (machineBO.requestUseMachine(usedMachine)) {
				request.setAttribute("NOTIFICATION", "Đăng ký sử dụng thành công");
			} else {
				request.setAttribute("NOTIFICATION", "Đăng ký sử dụng thất bại");
			}
		} else {
			request.setAttribute("NOTIFICATION", "Vui lòng nhập thông tin hợp lệ");
		}
		
		getUseMachine(request, response);
	}

	private void getUseMachine(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> listMachineCode = machineBO.getListMachines();
		List<String> listCustomerName = machineBO.getListCustomers();

		request.setAttribute("listMachine", listMachineCode);
		request.setAttribute("listCustomer", listCustomerName);
		dispatcher = request.getRequestDispatcher("use_machine.jsp");
		dispatcher.forward(request, response);
	}

	private void getListComputerMachines(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<ComputerMachine> computerMachines = machineBO.getComputerMachines();

		request.setAttribute("listComputer", computerMachines);
		dispatcher = request.getRequestDispatcher("list_machine.jsp");
		dispatcher.forward(request, response);
	}

	private void saveMachine(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		String pos = request.getParameter("postion");

		ComputerMachine computerMachine = new ComputerMachine();

		if (!machineBO.isValidMachine(code, pos)) {
			request.setAttribute("NOTIFICATION", "Vui lòng nhập đầy đủ thông tin");
		} else {
			computerMachine.setCode(code);
			computerMachine.setPosition(pos);
			computerMachine.setStatus(false);

			if (machineBO.createMachine(computerMachine)) {
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

		if (!machineBO.isValidCode(code)) {
			request.setAttribute("NOTIFICATION", "Vui lòng nhập đầy đủ thông tin");
		} else {
			computerMachine.setCode(code);
			computerMachine.setPosition(request.getParameter("postion"));
			computerMachine.setStatus(status);

			if (machineBO.updateMachineInfo(computerMachine)) {
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
		ComputerMachine computerMachine = machineBO.getMachine(code);
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
		machineBO.deleteMachine(code);

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
