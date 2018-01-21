package com.jianyu.aop.advice;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * 方法前的增强
 * @author jianyu.bai
 *
 */
public class GreetingBeforeAdvice implements MethodBeforeAdvice {
	public void before(Method arg0, Object[] args, Object obj) throws Throwable {
		String clientName = (String) args[0];
		System.out.println("----【before】面带笑容，满怀热忱地走向：" + clientName);
	}
}
