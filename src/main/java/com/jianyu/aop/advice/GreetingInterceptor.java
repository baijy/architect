package com.jianyu.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 环绕增强
 * @author BaiJianyu
 *
 */
public class GreetingInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object[] args = invocation.getArguments();
		String clientName = (String) args[0];
		System.out.println("【环绕】面带笑容，满怀热忱地走向：" + clientName);
		return null;
	}

}
