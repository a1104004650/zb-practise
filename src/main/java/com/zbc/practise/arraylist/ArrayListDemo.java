package com.zbc.practise.arraylist;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class ArrayListDemo implements Serializable { 
	private static final long serialVersionUID = 996890129747019948L; 
	private String name;
	// arrayList的值也被transient修饰
	// Java的serialization提供了一种持久化对象实例的机制。当持久化对象时，可能有一个特殊的对象数据
	// 成员，我们不想用serialization机制来保存它。为了在一个特定对象的一个域上关闭serialization，可以
	// 在这个域前加上关键字transient
	private transient String psw;
	ArrayList list;
	
	public ArrayListDemo(String name, String psw) { 
		this.name = name;
		this.psw = psw;
	} 
	
	@Override
	public String toString() {
		return "name=" + name + ", psw=" + psw;
	} 
	
	
	
	public static void main(String[] args) {
		ArrayListDemo userInfo = new ArrayListDemo("张三", "123456"); 
		System.out.println(userInfo); 
		try	{ 
			// 序列化，被设置为transient的属性没有被序列化 
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("UserInfo.out")); 
			o.writeObject(userInfo); 
			o.close(); 
		} catch (Exception e) { 
			// TODO: handle exception 
			e.printStackTrace(); 
		}
		try { 
			// 重新读取内容 
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("UserInfo.out")); 
			ArrayListDemo readUserInfo = (ArrayListDemo) in.readObject();
			//读取后psw的内容为null
			System.out.println(readUserInfo.toString()); 
			// 被标记为transient的属性在对象被序列化的时候不会被保存。
		} catch (Exception e) { 
			// TODO: handle exception 
			e.printStackTrace(); 
		} 
		
	}
} 