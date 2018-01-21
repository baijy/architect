package com.jianyu.aop.advice;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

/**
 * 后置增强
 * @author BaiJianyu
 *
 */
public class GreetingAfterAdvice implements AfterReturningAdvice {
	@Override
	public void afterReturning(Object returnObj, Method method, Object[] args, Object obj) throws Throwable {
		System.out.println("----【后置】欢迎下次光临！");
	}
	
}
