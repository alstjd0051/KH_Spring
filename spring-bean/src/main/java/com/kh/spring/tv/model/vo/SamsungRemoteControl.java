package com.kh.spring.tv.model.vo;

public class SamsungRemoteControl implements RemoteControl {

	@Override
	public void changeChannel(int no) {
		System.out.println("����1�� ������, ä���� " + no + "�� �����մϴ�.");
	}

}