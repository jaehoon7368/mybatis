package com.sh.mybatis.student.model.service;

import static com.sh.mybatis.common.SqlSessionTemplate.getSqlSession;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sh.mybatis.common.SqlSessionTemplate;
import com.sh.mybatis.student.model.dao.StudentDao;
import com.sh.mybatis.student.model.dto.Student;

public class StudentServiceImpl implements StudentService {

	private StudentDao studentDao;
	
	public StudentServiceImpl(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public int insertStudent(Student student) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = 0;
		try {
			result = studentDao.insertStudent(session,student);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		}finally{
			session.close(); //DBCP에 반환(메모리 해제 아님)
		}
		return result;
	}

	@Override
	public int insertStudent(Map<String, Object> studentMap) {	
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = 0;
		try {
			result = studentDao.insertStudent(session, studentMap);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		}finally {
			session.close();
		}
		return result;
	}

	@Override
	public int getTotalCount() {
		SqlSession session = getSqlSession();
		int totalCount = studentDao.getTotalCount(session);
		session.close();
		return totalCount;
	}

	@Override
	public Student selectOneStudent(int no) {
		SqlSession session = getSqlSession();
		Student student = studentDao.selectOneStudent(session,no);
		session.close();
		return student;
	}

	@Override
	public int studentUpdate(Student student) {
		SqlSession session = getSqlSession();
		int result = 0;
		try {
			result = studentDao.studentUpdate(session,student);
			session.commit();
		} catch (Exception e) {
			session.rollback();
		}finally {
			session.close();
		}
		return result;
	}

	@Override
	public Map<String, Object> selectOneStudentMap(int no) {
		SqlSession session = getSqlSession();
		Map<String,Object> studentMap = studentDao.selectOneStudentMap(session,no);
		session.close();
		return studentMap;
	}

	@Override
	public int studentDelete(int no) {
		SqlSession session = getSqlSession();
		int result = 0;
		try {
			result = studentDao.studentDelete(session,no);
			session.commit();
		} catch (Exception e) {
			session.rollback();
		}finally {
			session.close();
		}
		return result;
	}

	@Override
	public List<Student> selectStudentList() {
		SqlSession session = getSqlSession();
		List<Student> studentList = studentDao.selectStudentList(session);
		session.close();
		return studentList;
	}

	@Override
	public List<Map<String, Object>> selectStudentMapList() {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> studentMapList = studentDao.selectStudentMapList(session);
		session.close();
		return studentMapList;
	}
}
