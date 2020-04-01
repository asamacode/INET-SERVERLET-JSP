package com.asama.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.asama.model.Service;
import com.asama.model.UsedService;
import com.asama.utils.DbUtils;

public class ServiceDaoImpl implements ServiceDao {

	private Connection connection = null;
	private ResultSet resultSet;
	private PreparedStatement preparedStatement;

	@Override
	public List<String> getListServiceName() {
		String query = "SELECT * FROM DICHVU";
		List<String> list = new ArrayList<String>();

		try {
			connection = DbUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				list.add(resultSet.getString("MaDV"));
			}
		} catch (SQLException e) {
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
	public List<Service> getListServices() {
		String query = "SELECT * FROM DICHVU";
		List<Service> list = new ArrayList<Service>();
		Service service = null;

		try {
			connection = DbUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				service = new Service();
				service.setCode(resultSet.getString("MaDV"));
				service.setName(resultSet.getNString("TenDV"));
				service.setPrice(resultSet.getFloat("DonGia"));
				service.setUnit(resultSet.getNString("DonViTinh"));

				list.add(service);
			}
		} catch (SQLException e) {
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
	public Service getSingleService(String code) {
		Service service = null;
		String query = "SELECT * FROM DICHVU WHERE MaDV = ?";
		try {
			connection = DbUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, code);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				service = new Service();
				service.setCode(code);
				service.setName(resultSet.getNString("TenDV"));
				service.setPrice(resultSet.getFloat("DonGia"));
				service.setUnit(resultSet.getNString("DonViTinh"));
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

		return service;
	}

	@Override
	public boolean insertService(Service service) {
		boolean flag = false;
		String query = "INSERT INTO DICHVU VALUES (?,?,?,?)";

		try {
			connection = DbUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, service.getCode());
			preparedStatement.setNString(2, service.getName());
			preparedStatement.setNString(3, service.getUnit());
			preparedStatement.setFloat(4, service.getPrice());

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
	public boolean updateService(Service service) {
		boolean flag = false;
		String query = "UPDATE DICHVU SET TenDV = ?, DonViTinh = ?, DonGia = ? WHERE MaDV = ?";

		try {
			connection = DbUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setNString(1, service.getName());
			preparedStatement.setNString(2, service.getUnit());
			preparedStatement.setFloat(3, service.getPrice());

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
	public boolean deleteService(String code) {
		boolean flag = false;
		String query = "DELETE FROM DICHVU WHERE MaDV = ?";

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
	public boolean saveUsedService(UsedService usedService) {
		boolean flag = false;
		String query = "INSERT INTO SUDUNGDICHVU VALUES (?,?,?,?,?)"; 
		
		try {
			connection = DbUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, usedService.getCustomerId());
			preparedStatement.setString(2, usedService.getServiceCode());
			preparedStatement.setDate(3, usedService.getUseDate());
			preparedStatement.setTime(4, usedService.getUseTime());
			preparedStatement.setInt(5, usedService.getQuantity());
			
			flag = preparedStatement.executeUpdate() != 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
