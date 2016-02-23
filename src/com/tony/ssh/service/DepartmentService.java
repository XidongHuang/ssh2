package com.tony.ssh.service;

import java.util.List;

import com.tony.ssh.dao.DepartmentDao;
import com.tony.ssh.entities.Department;

public class DepartmentService {

	private DepartmentDao departmentDao;

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	public List<Department> getAll() {

		return departmentDao.getAll();

	}

}
