package com.sh.mybatis.emp.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.mybatis.common.AbstractController;
import com.sh.mybatis.emp.model.service.EmpService;

public class EmpUpdateController extends AbstractController {

	private EmpService empService;
	
	public EmpUpdateController(EmpService empService) {
		this.empService = empService;
	}
	
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 사용자입력값 처리
		String empId = request.getParameter("empId");
		String jobCode = request.getParameter("jobCode");
		String deptCode = request.getParameter("deptCode");
		
		Map<String,Object> param = new HashMap<>();
		param.put("empId", empId);
		param.put("jobCode", jobCode);
		param.put("deptCode", deptCode);
		System.out.println("param = " + param);
		
		// 2. 업무로직
		int result = empService.updateEmp(param);
		
		return "redirect:/emp/updateEmp.do?empId=" + empId;
	}
	
	/**
	 * query select * from emp where job_code in ('J5','J6','J7')
	 */
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empId = request.getParameter("empId");
		
		Map<String,Object> selectOneEmp = empService.selectOneEmp(empId);
		System.out.println("selectOneEmp = " + selectOneEmp);
		
		List<Map<String,Object>> jobList = empService.selectJobList();
		List<Map<String,Object>> deptList = empService.selectDeptList();
		
		request.setAttribute("selectOneEmp", selectOneEmp);
		request.setAttribute("jobList", jobList);
		request.setAttribute("deptList", deptList);
		
		
		return "/emp/updateEmp";
	}
}
