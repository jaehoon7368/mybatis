package com.sh.mybatis.emp.model.service;

import java.util.List;
import java.util.Map;

import static com.sh.mybatis.common.SqlSessionTemplate.getSqlSession;
import org.apache.ibatis.session.SqlSession;

import com.sh.mybatis.emp.model.dao.EmpDao;

public class EmpServiceImpl implements EmpService {
	
	private EmpDao empDao;

	public EmpServiceImpl(EmpDao empDao) {
		this.empDao = empDao;
	}


	@Override
	public List<Map<String, Object>> selectEmpList() {
		SqlSession session = getSqlSession();
		List<Map<String,Object>> empList = empDao.selectEmpList(session);
		session.close();
		return empList;
	}
	
	@Override
	public List<Map<String, Object>> search1(Map<String, Object> param) {
		SqlSession session = getSqlSession();
		List<Map<String,Object>> empList = empDao.search1(session,param);
		session.close();
		return empList;
	}
	
	@Override
	public List<Map<String, Object>> search2(Map<String, Object> param) {
		SqlSession session = getSqlSession();
		List<Map<String,Object>> empList = empDao.search2(session,param);
		session.close();
		return empList;
	}
	
	@Override
	public List<Map<String, Object>> selectJobList() {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> jobList = empDao.selectJobList(session);
		session.close();
		return jobList;
	}
	
	@Override
	public List<Map<String, Object>> search3(Map<String, Object> param) {
		SqlSession session = getSqlSession();
		List<Map<String,Object>> empList = empDao.search3(session,param);
		session.close();
		return empList;
	}
	
	@Override
	public List<Map<String, Object>> selectDeptList() {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> deptList = empDao.selectDeptList(session);
		session.close();
		return deptList;
	}
	
	@Override
	public Map<String, Object> selectOneEmp(String empId) {
		SqlSession session = getSqlSession();
		Map<String, Object> selectOneEmp = empDao.selectOneEmp(session,empId);
		session.close();
		return selectOneEmp;
	}
	
	@Override
	public int updateEmp(Map<String, Object> param) {
		SqlSession session = getSqlSession();
		int result = 0;
		try {
			result = empDao.updateEmp(session,param);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		}finally {
			session.close();
		}
		return result;
	}
}
