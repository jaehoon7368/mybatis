package com.sh.mybatis.emp.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public interface EmpDao {

	List<Map<String, Object>> selectEmpList(SqlSession session);

	List<Map<String, Object>> search1(SqlSession session, Map<String, Object> param);

	List<Map<String, Object>> search2(SqlSession session, Map<String, Object> param);

	List<Map<String, Object>> selectJobList(SqlSession session);

	List<Map<String, Object>> search3(SqlSession session, Map<String, Object> param);

	List<Map<String, Object>> selectDeptList(SqlSession session);

	Map<String, Object> selectOneEmp(SqlSession session,String empId);

	int updateEmp(SqlSession session, Map<String, Object> param);

}
