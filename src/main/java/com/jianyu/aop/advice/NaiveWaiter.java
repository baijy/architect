package com.jianyu.aop.advice;

/**
 * һ������ķ���Ա
 * @author jianyu.bai
 *
 */
public class NaiveWaiter implements Waiter {

	public void greeTo(String name) {
		System.out.println("���·��ķ���Ա��˿�" + name + "�ʺ�");
	}

	public void serveTo(String name) {
		System.out.println("���·��ķ���ԱΪ�˿�" + name + "����");
	}

}
