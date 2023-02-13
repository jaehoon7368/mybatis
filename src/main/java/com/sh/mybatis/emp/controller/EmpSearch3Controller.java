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

public class EmpSearch3Controller extends AbstractController {

	private EmpService empService;
	
	public EmpSearch3Controller(EmpService empService) {
		this.empService = empService;
	}
	
	/**
	 * query select * from emp where job_code in ('J5','J6','J7')
	 */
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		String[] jobCodes = request.getParameterValues("jobCode");
		String[] _deptCodes = request.getParameterValues("deptCode");
		List<String> deptCodes = _deptCodes != null ? Arrays.asList(_deptCodes) : null;
		
		Map<String,Object> param = new HashMap<>();
		param.put("jobCodes", jobCodes);
		param.put("deptCodes",deptCodes);
		System.out.println("param = " + param);
		
		// 2. 업무로직 - 검색
		List<Map<String,Object>> empList = empService.search3(param);
		System.out.println("empList = " + empList);
		
		// 직급코드 조회
		List<Map<String,Object>> jobList = empService.selectJobList();
		System.out.println("jobList = " + jobList);
		
		// 부서코드 조회
		List<Map<String,Object>> deptList = empService.selectDeptList();
		System.out.println("deptList = " + deptList);
		
		// 3. jsp데이터 전달
		request.setAttribute("jobList", jobList);
		request.setAttribute("empList", empList);
		request.setAttribute("deptList", deptList);
		
		return "emp/search3";
	}
}
