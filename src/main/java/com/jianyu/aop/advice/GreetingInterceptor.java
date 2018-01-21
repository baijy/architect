package com.jianyu.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 环绕增强
 * 
 * @author BaiJianyu
 *
 */
public class GreetingInterceptor implements MethodInterceptor {

	// 对方法进行拦截
	// 获得方法的参数和返回值
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object[] args = invocation.getArguments();
		String clientName = (String) args[0];
		System.out.println("----【环绕】面带笑容，满怀热忱地走向：" + clientName);
		Object obj = invocation.proceed();
		System.out.println("----【环绕】祝您用餐愉快：" + clientName);
		return obj;
	}

}
