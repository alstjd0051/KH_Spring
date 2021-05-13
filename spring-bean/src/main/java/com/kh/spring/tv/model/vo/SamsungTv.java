package com.kh.spring.tv.model.vo;

public class SamsungTv implements Tv {

	private RemoteControl remocon;

	public void setRemocon(RemoteControl remocon) {
		this.remocon = remocon;
	}

	public SamsungTv() {
		System.out.println("SamsungTv��ü�� �����߽��ϴ�.");
	}

	@Override
	public void powerOn() {
		System.out.println("SamsungTv�� ������ �׽��ϴ�.");
	}

	public void changeChannel(int no) {
		this.remocon.changeChannel(no);
		
	}


}