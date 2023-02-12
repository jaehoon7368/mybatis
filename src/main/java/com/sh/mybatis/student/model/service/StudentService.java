package com.sh.mybatis.student.model.service;

import java.util.List;
import java.util.Map;

import com.sh.mybatis.student.model.dto.Student;

public interface StudentService {

	int insertStudent(Student student);

	int insertStudent(Map<String, Object> studentMap);

	int getTotalCount();

	Student selectOneStudent(int no);

	int studentUpdate(Student student);

	Map<String, Object> selectOneStudentMap(int no);

	int studentDelete(int no);

	List<Student> selectStudentList();

	List<Map<String, Object>> selectStudentMapList();

}
