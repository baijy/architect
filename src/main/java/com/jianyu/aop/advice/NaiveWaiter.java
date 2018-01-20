package com.jianyu.aop.advice;

/**
 * 一个年轻的服务员
 * @author jianyu.bai
 *
 */
public class NaiveWaiter implements Waiter {

	public void greeTo(String name) {
		System.out.println("拿衣服的服务员向顾客" + name + "问好");
	}

	public void serveTo(String name) {
		System.out.println("拿衣服的服务员为顾客" + name + "服务");
	}

}
