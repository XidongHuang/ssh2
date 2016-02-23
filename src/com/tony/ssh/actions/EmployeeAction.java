package com.tony.ssh.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.tony.ssh.service.EmployeeService;

public class EmployeeAction extends ActionSupport implements RequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EmployeeService employeeService;
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	private Integer id;
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	 private InputStream inputStream;
	    public InputStream getInputStream() {
	        return inputStream;
	    }
	 
	
	
	
	public String delete(){
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
		
		
		return "delete";
		
	}
	
	
	public String list(){
		
		request.put("employees", employeeService.getAll());
		
		return "list";
	}

	private Map<String, Object> request;


	@Override
	public void setRequest(Map<String, Object> arg0) {

		this.request = arg0;
		
	}
	

}
