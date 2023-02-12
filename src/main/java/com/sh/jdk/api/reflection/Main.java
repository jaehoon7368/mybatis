package com.sh.jdk.api.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) throws Exception {
		Main main = new Main();
//		main.test1();
//		main.test2();
		main.test3();
		
	}

	/**
	 * 필드 제어
	 */
	private void test3() throws Exception{
		Class<?> clz = Class.forName("com.sh.jdk.api.reflection.Sample");
		Sample sample = (Sample)clz.getDeclaredConstructor(int.class,String.class).newInstance(123,"여보세요");
		
		Field num = clz.getDeclaredField("num");
		num.setAccessible(true);
		Object value = num.get(sample);
		
		num.set(sample, 789);
		value = num.get(sample);
		
		System.out.println(value);
		
	}

	/**
	 * 메소드 호출
	 */
	private void test2() throws Exception{
		Class<?> clz = Class.forName("com.sh.jdk.api.reflection.Sample");
		Sample sample = (Sample)clz.getDeclaredConstructor(null).newInstance(null);
		
		Method setNum = clz.getDeclaredMethod("setNum", int.class);
		Object returnValue = setNum.invoke(sample, 100);
		
		System.out.println(sample);
		System.out.println(returnValue); //null
		
		//getNum 호출
		Method getNum = clz.getDeclaredMethod("getNum");
		returnValue = getNum.invoke(sample);
		System.out.println(returnValue);
		
		
	}

	/**
	 * Reflection API
	 * - 클래스객체를 통해서 클래스정보/객체정보 열람, 객체생성, 필드값주입, 메소드호출등 제어하는 API
	 * 
	 *  객체 생성
	 */
	private void test1() throws Exception{
		// Sample 클래스객체
		Class<?> clz = Class.forName("com.sh.jdk.api.reflection.Sample");
		Class<?> clz2 = Sample.class;
		Class<?> clz3 = new Sample().getClass();
		System.out.println(clz == clz2); //true
		System.out.println(clz2 == clz3); //true
		
		//기본생성자를 이용한 객체 생성
		Constructor<Sample> constructor = (Constructor<Sample>) clz.getDeclaredConstructor(null);
		Sample sample = constructor.newInstance(null);
		System.out.println(sample);
		
		// 파라미터생성자를 이용한 객체 셍성
		Constructor<Sample> constructor2 = (Constructor<Sample>) clz.getDeclaredConstructor(int.class,String.class);
		Sample sample2 = constructor2.newInstance(10,"홍길동");
		System.out.println(sample2);
	}

}
