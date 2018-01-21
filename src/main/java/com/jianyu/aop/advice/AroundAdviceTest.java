package com.jianyu.aop.advice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AroundAdviceTest {
	public static void main(String[] args) {
		String configPath = "com/jianyu/aop/advice/beans.xml";
		ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
		Waiter waiter = (Waiter) ctx.getBean("waiter");
		waiter.greeTo("风清扬老前辈");
	}
}
