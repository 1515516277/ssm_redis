package com.ming.test;

import java.util.UUID;

import com.ming.entity.EmpEntity;
import com.ming.service.Empservice;

public class Testclass {

	
	public static void addtest(Empservice empservice) {
		EmpEntity emp=new EmpEntity();
		System.out.println("��ʼ");
		for (int i = 0; i < 1000; i++) {
			emp.setName(UUID.randomUUID().toString().substring(0,4));
			emp.setEmail("www.123");
			if(i%2==0) {
				emp.setGender("1");
			}else {
				emp.setGender("0");
			}
			emp.setdId(1);
			empservice.insertemp(emp);
		}
		System.out.println("����");
	}

	public static void jsonxml(){
		String json="";
	}
}
