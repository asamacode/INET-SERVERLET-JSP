package com.asama.model.bo;

import java.util.List;

import com.asama.common.INetUtils;
import com.asama.model.bean.Service;
import com.asama.model.bean.UsedService;
import com.asama.model.bean.dao.CustomerDao;
import com.asama.model.bean.dao.CustomerDaoImpl;
import com.asama.model.bean.dao.ServiceDao;
import com.asama.model.bean.dao.ServiceDaoImpl;

public class ServiceBO {

	private ServiceDao serviceDao;
	private CustomerDao customerDao;

	public ServiceBO() {
		serviceDao = new ServiceDaoImpl();
		customerDao = new CustomerDaoImpl();
	}

	public Service getService(String code) {
		return serviceDao.getSingleService(code);
	}

	public List<String> getListServiceCode() {
		return serviceDao.getListServiceName();
	}

	public List<String> getListCustomerId() {
		return customerDao.getListCustomerId();
	}

	public List<Service> getServices() {
		return serviceDao.getListServices();
	}

	public boolean requestUseService(UsedService usedService) {
		return serviceDao.saveUsedService(usedService);
	}
	
	public boolean deleteService(String code) {
		return serviceDao.deleteService(code);
	}
	
	public boolean updateService(Service service) {
		return serviceDao.updateService(service);
	}
	
	public boolean createService(Service service) {
		return serviceDao.insertService(service);
	}

	public boolean isValidateInfo(String useDate, String useTime, String quantity) {
		if (useDate.equals("") || useTime.equals("") || quantity.equals("") || !INetUtils.isValidDate(useDate)
				|| !INetUtils.isValidTime(useTime)) {
			return false;
		}
		return true;
	}

	public boolean isValidService(String code, String name, String price, String unit) {
		if (code.equals("") || name.equals("") || price.equals("") || unit.equals("")) {
			return false;
		}
		return true;
	}
}
