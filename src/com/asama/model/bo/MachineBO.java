package com.asama.model.bo;

import java.util.List;

import com.asama.common.INetUtils;
import com.asama.model.bean.ComputerMachine;
import com.asama.model.bean.UsedMachine;
import com.asama.model.dao.CustomerDao;
import com.asama.model.dao.CustomerDaoImpl;
import com.asama.model.dao.MachineDao;
import com.asama.model.dao.MachineDaoImpl;

public class MachineBO {

	private MachineDao machineDao;
	private CustomerDao customerDao;

	public MachineBO() {
		machineDao = new MachineDaoImpl();
		customerDao = new CustomerDaoImpl();
	}

	public boolean isValidInfo(String startDate, String startTime, String useTime) {
		if (startDate.equals("") || startTime.equals("") || useTime.equals("") || !INetUtils.isValidDate(startDate)
				|| !INetUtils.isValidTime(startTime)) {
			return false;
		}
		return true;
	}

	public boolean requestUseMachine(UsedMachine usedMachine) {
		return machineDao.insertUsedMachine(usedMachine);
	}

	public List<String> getListMachines() {
		return machineDao.getListFreeMachine();
	}

	public List<String> getListCustomers() {
		return customerDao.getListCustomerId();
	}

	public List<ComputerMachine> getComputerMachines() {
		return machineDao.getListComputerMachines();
	}

	public boolean createMachine(ComputerMachine computerMachine) {
		return machineDao.insertComputerMachine(computerMachine);
	}

	public boolean isValidMachine(String code, String pos) {
		if ("".equals(code) || "".equals(pos)) {
			return false;
		}
		return true;
	}

	public boolean updateMachineInfo(ComputerMachine computerMachine) {
		return machineDao.updateComputerMachine(computerMachine);
	}

	public boolean isValidCode(String code) {
		return !code.equals("");
	}

	public ComputerMachine getMachine(String code) {
		return machineDao.getComputerMachine(code);
	}

	public void deleteMachine(String code) {
		machineDao.deleteComputerMachine(code);
	}
}
