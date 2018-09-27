package com.springmvc.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.model.Employee;
import com.springmvc.service.IEmployeeService;

@Controller
@RequestMapping("/")
public class EmployeeController {
	static Logger log = Logger.getLogger(EmployeeController.class);

	@Autowired
	IEmployeeService service;
	
	@Autowired
	MessageSource messageSource;

	/*
	 * List all existing employees.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listEmployees(ModelMap model, @RequestParam(name = "keyword", required=false) String keyword) {

		List<Employee> employees = service.findAllEmployees(keyword);
		model.addAttribute("employees", employees);
		model.addAttribute("keyword", keyword);
		return "allemployees";
	}

	/*
	 * Add a new employee.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newEmployee(ModelMap model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		model.addAttribute("edit", false);
		return "registration";
	}

	/*
	 * Handling POST request for validating the user input and saving employee in database.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveEmployee(@Valid Employee employee, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}
		
		if(!service.isEmployeeNumberUnique(employee.getId(), employee.getCode())){
			FieldError codeError =new FieldError("Employee","code",messageSource.getMessage("non.unique.code", new String[]{employee.getCode()}, Locale.getDefault()));
		    result.addError(codeError);
			return "registration";
		}
		
		service.saveEmployee(employee);

		model.addAttribute("success", "employee " + employee.getFirstName() + " registered successfully");
		return "success";
	}


	/*
	 * Provide the existing employee for updating.
	 */
	@RequestMapping(value = { "/edit-{code}-employee" }, method = RequestMethod.GET)
	public String editEmployee(@PathVariable String code, ModelMap model) {
		Employee employee = service.findEmployeeByNumber(code);
		model.addAttribute("employee", employee);
		model.addAttribute("edit", true);
		return "registration";
	}
	
	/*
	 * Handling POST request for validating the user input and updating employee in database.
	 */
	@RequestMapping(value = { "/edit-{code}-employee" }, method = RequestMethod.POST)
	public String updateEmployee(@Valid Employee employee, BindingResult result,
			ModelMap model, @PathVariable String code) {

		if (result.hasErrors()) {
			return "registration";
		}

		if(!service.isEmployeeNumberUnique(employee.getId(), employee.getCode())){
			FieldError codeError =new FieldError("Employee","code",messageSource.getMessage("non.unique.code", new String[]{employee.getCode()}, Locale.getDefault()));
		    result.addError(codeError);
			return "registration";
		}

		service.updateEmployee(employee);

		model.addAttribute("success", "Employee " + employee.getFirstName()	+ " updated successfully");
		return "success";
	}

	
	/*
	 * Delete an employee by it's CODE value.
	 */
	@RequestMapping(value = { "/delete-{code}-employee" }, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable String code) {
		service.deleteEmployeeByNumber(code);
		return "redirect:/list";
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView logAndHandleException(HttpServletRequest request, Exception ex){
		log.error("Requested URL="+request.getRequestURL());
		log.error("Exception Raised=", ex);
		
		ModelAndView modelAndView = new ModelAndView();
		
		String rootcause = "";
				if(ex.getMessage() != null){
					rootcause = ex.getMessage();
				}else{
					rootcause = ex.toString();
				}
		
	    modelAndView.addObject("rootcause", rootcause);
	    modelAndView.addObject("url", request.getRequestURL());
	    
	    modelAndView.setViewName("error");
	    return modelAndView;
	}	

}
