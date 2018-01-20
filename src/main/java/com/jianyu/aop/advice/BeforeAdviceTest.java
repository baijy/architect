package com.jianyu.aop.advice;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

/**
 * ��ǿ�����
 * 
 * @author jianyu.bai
 * 
 *
 */
public class BeforeAdviceTest {
	public static void main(String[] args) {
		Waiter target = new NaiveWaiter();
		BeforeAdvice advice = new GreetingBeforeAdvice();
		// Spring�ṩ�Ĵ�����
		ProxyFactory pf = new ProxyFactory();
		// ���ô���Ŀ��
		pf.setTarget(target);
		// ΪĿ�������ǿ
		pf.addAdvice(advice);

		Waiter proxy = (Waiter) pf.getProxy();
		proxy.greeTo("������");
		proxy.serveTo("����");
	}

}
