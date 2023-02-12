package com.sh.mybatis.student.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.mybatis.common.AbstractController;
import com.sh.mybatis.student.model.dto.Student;
import com.sh.mybatis.student.model.service.StudentService;

public class StudentSelectListController extends AbstractController {
	
	private StudentService studentService;
	
	/**
	 * 의존객체 주입
	 * @param studentService
	 */
	public StudentSelectListController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<Student> studentList = studentService.selectStudentList();
		System.out.println("studentList = " + studentList);
		
		//map으로 조회
		List<Map<String,Object>> studentMapList = studentService.selectStudentMapList();
		System.out.println("studentMapList = " + studentMapList);
		
		request.setAttribute("studentList", studentList);
		request.setAttribute("studentMapList", studentMapList);
		return "student/selectList";
	}
}
