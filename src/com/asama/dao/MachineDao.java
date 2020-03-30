package com.asama.dao;

import java.util.List;

import com.asama.model.ComputerMachine;

public interface MachineDao {
	
	List<String> getListFreeMachine();

	List<ComputerMachine> getListComputerMachines();
	
	ComputerMachine getComputerMachine(String code);
	
	boolean insertComputerMachine(ComputerMachine computerMachine);
	
	boolean deleteComputerMachine(String code);
	
	boolean updateComputerMachine(ComputerMachine computerMachine);
}
