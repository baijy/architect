package com.jianyu.aop.advice;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * ����ǰ����ǿ
 * @author jianyu.bai
 *
 */
public class GreetingBeforeAdvice implements MethodBeforeAdvice {
	public void before(Method arg0, Object[] args, Object obj) throws Throwable {
		String clientName = (String) args[0];
		System.out.println("���Ц�ݣ������ȳ�������" + clientName);
	}
}
