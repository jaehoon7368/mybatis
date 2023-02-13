package com.sh.mybatis.emp.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class EmpDaoImpl implements EmpDao{

	@Override
	public List<Map<String, Object>> selectEmpList(SqlSession session) {
		return session.selectList("emp.selectEmpList");
	}
	
	@Override
	public List<Map<String, Object>> search1(SqlSession session, Map<String, Object> param) {
		return session.selectList("emp.search1",param);
	}
	
	@Override
	public List<Map<String, Object>> search2(SqlSession session, Map<String, Object> param) {
		return session.selectList("emp.search2",param);
	}

	@Override
	public List<Map<String, Object>> selectJobList(SqlSession session) {
		return session.selectList("emp.selectJobList");
	}
	
	@Override
	public List<Map<String, Object>> search3(SqlSession session, Map<String, Object> param) {
		return session.selectList("emp.search3",param);
	}
	
	@Override
	public List<Map<String, Object>> selectDeptList(SqlSession session) {
		return session.selectList("emp.selectDeptList");
	}
	
	@Override
	public Map<String, Object> selectOneEmp(SqlSession session, String empId) {
		return session.selectOne("emp.selectOneEmp", empId);
	}
	
	@Override
	public int updateEmp(SqlSession session, Map<String, Object> param) {
		return session.update("emp.updateEmp", param);
	}
}
