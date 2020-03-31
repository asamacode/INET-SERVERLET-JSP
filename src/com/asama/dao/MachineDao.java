package com.asama.dao;

import java.util.List;

import com.asama.model.ComputerMachine;
import com.asama.model.UsedMachine;

public interface MachineDao {
	
	List<String> getListFreeMachine();

	List<ComputerMachine> getListComputerMachines();
	
	List<ComputerMachine> filterListByName(String name);
	
	ComputerMachine getComputerMachine(String code);
	
	boolean insertComputerMachine(ComputerMachine computerMachine);
	
	boolean deleteComputerMachine(String code);
	
	boolean updateComputerMachine(ComputerMachine computerMachine);
	
	boolean insertUsedMachine(UsedMachine usedMachine);
	
	boolean setUsedMachine(String code);
}
