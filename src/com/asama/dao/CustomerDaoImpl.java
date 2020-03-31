package com.asama.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.asama.model.Customer;
import com.asama.utils.DbUtils;

public class CustomerDaoImpl implements CustomerDao {

	Connection connection = null;
	ResultSet resultSet;
	PreparedStatement preparedStatement;

	@Override
	public List<Customer> getListCustomers() {
		List<Customer> list = new ArrayList<Customer>();
		Customer customer = null;
		String query = "SELECT * FROM KHACHHANG";
		try {
			connection = DbUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				customer = new Customer();
				customer.setId(resultSet.getString("MaKH"));
				customer.setName(resultSet.getNString("TenKH"));
				customer.setPhone(resultSet.getString("SoDienThoai"));
				customer.setAddress(resultSet.getNString("DiaChi"));
				customer.setEmail(resultSet.getString("DiaChiEmail"));
				list.add(customer);
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
	public Customer getSingleCustomer(String id) {
		String query = "SELECT * FROM KHACHHANG WHERE MaKH = ?";
		Customer customer = null;
		try {
			connection = DbUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				customer = new Customer();
				customer.setId(id);
				customer.setName(resultSet.getNString("TenKH"));
				customer.setEmail(resultSet.getString("DiaChiEmail"));
				customer.setPhone(resultSet.getString("SoDienThoai"));
				customer.setAddress(resultSet.getNString("DiaChi"));
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

		return customer;
	}

	@Override
	public boolean insertCustomer(Customer customer) {
		boolean flag = false;
		String query = "INSERT INTO KHACHHANG VALUES (?,?,?,?,?)";

		try {
			connection = DbUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, customer.getId());
			preparedStatement.setString(2, customer.getName());
			preparedStatement.setString(3, customer.getAddress());
			preparedStatement.setString(4, customer.getPhone());
			preparedStatement.setString(5, customer.getEmail());

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
	public boolean updateCustomer(Customer customer) {
		boolean flag = false;
		String query = "UPDATE KHACHHANG SET DiaChi = ?, SoDienThoai = ?, DiaChiEmail = ? WHERE MaKH = ?";

		try {
			connection = DbUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, customer.getAddress());
			preparedStatement.setString(2, customer.getPhone());
			preparedStatement.setString(3, customer.getEmail());
			preparedStatement.setString(4, customer.getId());

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
	public boolean deleteCustomer(String id) {
		boolean flag = false;
		String query = "DELETE FROM KHACHHANG WHERE MaKH = ?";

		try {
			connection = DbUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);

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
	public List<String> getListCustomerId() {
		List<String> list = new ArrayList<>();
		String query = "SELECT * FROM KHACHHANG";
		try {
			connection = DbUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				list.add(resultSet.getString("MaKH"));
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
	public List<Customer> filterListByName(String name) {
		String query = "SELECT * FROM KHACHHANG WHERE TenKH LIKE N'% "+ name +"'";
		List<Customer> list = new ArrayList<>();
		Customer customer = null;
		try {
			connection = DbUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				customer = new Customer();
				customer.setId(resultSet.getString("MaKH"));
				customer.setName(resultSet.getNString("TenKH"));
				customer.setAddress(resultSet.getNString("DiaChi"));
				customer.setEmail(resultSet.getString("DiaChiEmail"));
				customer.setPhone(resultSet.getString("SoDienThoai"));
				
				list.add(customer);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
