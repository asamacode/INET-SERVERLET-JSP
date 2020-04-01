package com.asama.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.asama.model.ComputerMachine;
import com.asama.model.UsedMachine;
import com.asama.utils.DbUtils;

public class MachineDaoImpl implements MachineDao {

	Connection connection = null;
	ResultSet resultSet;
	PreparedStatement preparedStatement;

	@Override
	public List<ComputerMachine> getListComputerMachines() {
		List<ComputerMachine> list = new ArrayList<ComputerMachine>();
		ComputerMachine computerMachine = null;
		String query = "SELECT * FROM MAY";
		try {
			connection = DbUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				computerMachine = new ComputerMachine();
				computerMachine.setCode(resultSet.getString("MaMay"));
				computerMachine.setPosition(resultSet.getString("ViTri"));
				computerMachine.setStatus(resultSet.getBoolean("TrangThai"));
				list.add(computerMachine);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
				}
			}

		}

		return list;
	}

	@Override
	public ComputerMachine getComputerMachine(String code) {
		ComputerMachine computerMachine = null;
		String query = "SELECT * FROM MAY WHERE MaMay = ?";
		try {
			connection = DbUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, code);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				computerMachine = new ComputerMachine();
				computerMachine.setCode(code);
				computerMachine.setPosition(resultSet.getString("ViTri"));
				computerMachine.setStatus(resultSet.getBoolean("TrangThai"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
				}
			}

		}

		return computerMachine;
	}

	@Override
	public boolean insertComputerMachine(ComputerMachine computerMachine) {

		boolean flag = false;
		String query = "INSERT INTO MAY VALUES (?,?,?)";

		try {
			connection = DbUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, computerMachine.getCode());
			preparedStatement.setString(2, computerMachine.getPosition());
			preparedStatement.setBoolean(3, computerMachine.getStatus());

			flag = preparedStatement.executeUpdate() != 0;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
				}
			}

		}
		return flag;
	}

	@Override
	public boolean deleteComputerMachine(String code) {
		boolean flag = false;
		String query = "DELETE FROM MAY WHERE MaMay = ?";

		try {
			connection = DbUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, code);

			flag = preparedStatement.executeUpdate() != 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
				}
			}

		}

		return flag;
	}

	@Override
	public boolean updateComputerMachine(ComputerMachine computerMachine) {
		boolean flag = false;
		String query = "UPDATE MAY SET ViTri = ?, TrangThai = ? WHERE MaMay = ?";

		try {
			connection = DbUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, computerMachine.getPosition());
			preparedStatement.setBoolean(2, computerMachine.getStatus());
			preparedStatement.setString(3, computerMachine.getCode());

			flag = preparedStatement.executeUpdate() != 0;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
				}
			}

		}
		return flag;
	}

	@Override
	public List<String> getListFreeMachine() {
		List<String> list = new ArrayList<>();
		String query = "SELECT * FROM MAY WHERE TrangThai = 0";
		try {
			connection = DbUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				list.add(resultSet.getString("MaMay"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
				}
			}

		}

		return list;
	}

	@Override
	public List<ComputerMachine> filterListByName(String name) {
		
		return null;
	}

	@Override
	public boolean insertUsedMachine(UsedMachine usedMachine) {
		boolean flag = false;
		String query = "INSERT INTO SUDUNGMAY VALUES (?,?,?,?,?)"; 
		
		try {
			connection = DbUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, usedMachine.getCustomerId());
			preparedStatement.setString(2, usedMachine.getMachineCode());
			preparedStatement.setDate(3, usedMachine.getStartDate());
			preparedStatement.setTime(4, usedMachine.getStartTime());
			preparedStatement.setInt(5, usedMachine.getUseTime());
			
			setUsedMachine(usedMachine.getMachineCode());
			flag = preparedStatement.executeUpdate() != 0;
		} catch (SQLException e) {
		}
		return flag;
	}

	@Override
	public boolean setUsedMachine(String code) {
		boolean flag = false;
		String query = "UPDATE MAY SET TrangThai = 1 WHERE MaMay = ?";
		
		connection = DbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, code);
			
			flag = preparedStatement.executeUpdate() != 0;
		} catch (SQLException e) {
		}
		return flag;
	}

}
