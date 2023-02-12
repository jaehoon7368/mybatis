package com.sh.mybatis.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.mybatis.emp.model.dao.EmpDaoImpl;
import com.sh.mybatis.emp.model.service.EmpService;
import com.sh.mybatis.emp.model.service.EmpServiceImpl;
import com.sh.mybatis.student.model.dao.StudentDao;
import com.sh.mybatis.student.model.dao.StudentDaoImpl;
import com.sh.mybatis.student.model.service.StudentService;
import com.sh.mybatis.student.model.service.StudentServiceImpl;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 요청 url과 이를 처리할 controller객체를 매핑
	 * 
	 *  -/student/selectList.do -> StudentSelectListController
	 *  -/student/selectOne.do -> StudentSelectOneController
	 *  - ....
	 *  
	 *  모든 컨트롤러는 AbstractController의 자식클래스로, AbstractController타입으로 제어될 것.
	 */
	private Map<String,AbstractController> commandMap = new HashMap<>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
    	// 1. coomand-map.properties -> prop
    	Properties prop = new Properties();
    	String filepath = DispatcherServlet.class.getResource("/command-map.properties").getPath();
    	try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	// 2. prop -> commandMap (reflection api)
    	StudentService studentService = new StudentServiceImpl(new StudentDaoImpl());
    	EmpService empService = new EmpServiceImpl(new EmpDaoImpl());
    	
    	Set<String> propNames = prop.stringPropertyNames();
    	try {
    		for(String url : propNames) {
    			String className = prop.getProperty(url);
    			Class<?> clz = Class.forName(className);
    			AbstractController controller = null;
    			
    			if(url.startsWith("/student")) {
    				Constructor<?> constructor = clz.getDeclaredConstructor(StudentService.class); //생성자 객체
        			controller = (AbstractController)constructor.newInstance(studentService); // new XXXController()
      
    			}else if(url.startsWith("/emp")){
    				Constructor<?> constructor = clz.getDeclaredConstructor(EmpService.class); //생성자 객체
        			controller = (AbstractController)constructor.newInstance(empService); // new XXXController()
      
    			}
    			commandMap.put(url, controller);
    		}
    		
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	
    	System.out.println("commandMap : " + commandMap);
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 요청주소 가져오기
		String url = request.getRequestURI(); // /mybatis/student/selectList.do
		url = url.replace(request.getContextPath(), "");
		
		// 2. commandMap에서 해당 controller 가져오기
		AbstractController controller = commandMap.get(url);
		if(controller == null) {
			// 404 에러처리
			response.sendError(HttpServletResponse.SC_NOT_FOUND, url);
			return;
		}
		
		// 3. 전송방식에 따라 doGet 또는 doPost 호출
		String method = request.getMethod();
		String viewName = null;
		switch(method) {
		case "GET" : viewName = controller.doGet(request, response); break;
		case "POST" : viewName = controller.doPost(request, response); break;
		default : throw new RuntimeException("허용되지 않은 전송방식 - " + method);
		}
		
		// 4. 응답처리 forwarding | redirect | bypass(controller에서 응답 직접 출력)
		if(viewName != null) {
			// redirect : redirect:/
			if(viewName.startsWith("redirect:")) {
				response.sendRedirect(request.getContextPath() + viewName.replace("redirect:","")); // /mybatis/
			}
			// forward : student/selectList
			else {
				String prefix = "/WEB-INF/views/";
				String suffix = ".jsp";
				viewName = prefix + viewName + suffix;
				request.getRequestDispatcher(viewName).forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
