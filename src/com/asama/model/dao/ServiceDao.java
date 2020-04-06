package com.asama.model.dao;

import java.util.List;

import com.asama.model.bean.Service;
import com.asama.model.bean.UsedService;

public interface ServiceDao {
	
	List<String> getListServiceName();
	
	List<Service> getListServices();
	
	Service getSingleService(String code);
	
	boolean insertService(Service service);
	
	boolean updateService(Service service);
	
	boolean deleteService(String code);
	
	boolean saveUsedService(UsedService usedService);
}
