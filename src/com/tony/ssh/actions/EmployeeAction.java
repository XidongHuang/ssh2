package com.tony.ssh.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tony.ssh.entities.Employee;
import com.tony.ssh.service.DepartmentService;
import com.tony.ssh.service.EmployeeService;

public class EmployeeAction extends ActionSupport implements RequestAware, ModelDriven<Employee>, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EmployeeService employeeService;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	private DepartmentService departmentService;

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	private Integer id;

	public void setId(Integer id) {
		this.id = id;
	}

	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public String delete() {
		try {
			employeeService.delet(id);
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return "ajax-success";

	}

	public String input() {
		request.put("departments", departmentService.getAll());

		return INPUT;

	}

	public void prepareInput() {
		if (id != null) {

			model = employeeService.get(id);
		}

	}

	public String list() {

		request.put("employees", employeeService.getAll());

		return "list";
	}

	public String save() {

		if (id == null) {

			model.setCreateTime(new Date());

		}

		System.out.println(model);

		employeeService.saveOrUpdate(model);

		return SUCCESS;
	}

	public void prepareSave() {

		if (id == null) {
			model = new Employee();
		} else {

			model = employeeService.get(id);

		}

	}

	private String lastName;

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String validateLastName() throws UnsupportedEncodingException {

		if (employeeService.lastNameIsValid(lastName)) {
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));

		} else {

			inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));

		}

		return "ajax-success";
	}

	private Map<String, Object> request;

	@Override
	public void setRequest(Map<String, Object> arg0) {

		this.request = arg0;

	}

	@Override
	public void prepare() throws Exception {
	}

	private Employee model;

	@Override
	public Employee getModel() {

		return model;
	}

}
