package io.pivotal.controller;

import io.pivotal.model.Employee;
import io.pivotal.repo.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	@Autowired
	private EmployeeRepository repository;
	
	@RequestMapping(method = RequestMethod.GET, path = "/query")
	@ResponseBody
	public List<Employee> query() throws Exception {

		List<Employee> results = new ArrayList<Employee>();
		repository.findAll().forEach(item->results.add(item));;
		
		return results;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/save")
	@ResponseBody
	public String save() throws Exception {

		Employee emp1 = new Employee(1, "Richard", 32);
		repository.save(emp1);
		
		return emp1.toString();
	}
}
