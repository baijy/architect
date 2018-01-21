package com.jianyu.aop.advice;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

/**
 * 增强类测试
 * 
 * @author jianyu.bai
 * 
 *
 */
public class BeforeAdviceTest {
	public static void main(String[] args) {
		Waiter target = new NaiveWaiter();
		BeforeAdvice advice = new GreetingBeforeAdvice();
		// Spring提供的代理工厂
		ProxyFactory pf = new ProxyFactory();
		// 设置代理目标
		pf.setTarget(target);
		// 为目标添加增强
		pf.addAdvice(advice);

		Waiter proxy = (Waiter) pf.getProxy();
		proxy.greeTo("李先生");
		proxy.serveTo("王大锤");
	}

}
