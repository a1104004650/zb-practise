package com.zbc.practise.reflec;

import java.lang.reflect.Method;

/**
 * @author StormT1King
 */
public class ReflecDemo {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Student s = new Student();
		Class class1 = s.getClass();
		Class class2 = Student.class;
		Class class3 = Class.forName("study.reflec.Student");
		Class class4 = Integer.TYPE;
		
		
		for (Method method : class3.getDeclaredMethods()) {
			System.out.println(method.toString());
		}
		// 成员变量
		class3.getDeclaredFields();
		// 构造方法
		class3.getDeclaredConstructors();
		
		Student s2 = (Student)class3.newInstance();
		s2.setAge(11);
		System.out.println(s2.getAge());
		
	}
}
