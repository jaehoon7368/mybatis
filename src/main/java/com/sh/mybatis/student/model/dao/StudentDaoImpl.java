package com.sh.mybatis.student.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sh.mybatis.student.model.dto.Student;

public class StudentDaoImpl implements StudentDao{

	@Override
	public int insertStudent(SqlSession session, Student student) {
		
		//session.insert(statement:String, patameter:Object)
		// statement - mapper파일의 namespace.id
		// parameter - 0 / 1개
		return session.insert("student.insertStudent", student);
	}

	@Override
	public int insertStudent(SqlSession session, Map<String, Object> studentMap) {
		return session.insert("student.insertStudent",studentMap);
	}

	@Override
	public int getTotalCount(SqlSession session) {
		return session.selectOne("student.getTotalCount");
	}

	@Override
	public Student selectOneStudent(SqlSession session, int no) {
		return session.selectOne("student.selectOneStudent",no);
	}

	@Override
	public int studentUpdate(SqlSession session, Student student) {
		return session.update("student.studentUpdate", student);
	}

	@Override
	public Map<String, Object> selectOneStudentMap(SqlSession session, int no) {
		return session.selectOne("student.selectOneStudentMap", no);
	}

	@Override
	public int studentDelete(SqlSession session, int no) {
		return session.update("student.studentDelete", no);
	}

	@Override
	public List<Student> selectStudentList(SqlSession session) {
		return session.selectList("student.selectStudentList");
	}
	
	@Override
	public List<Map<String, Object>> selectStudentMapList(SqlSession session) {
		return session.selectList("student.selectStudentMapList");
	}

}
