package com.kh.java.enum_;

import lombok.AllArgsConstructor;
import lombok.Data;

public class EnumTest {

	public static final int COLOR_BLACK = 0;
	public static final int COLOR_RED = 1;
	public static final int COLOR_BLUE = 2;
	
	public static final int ITEM_OUTER = 0;
	public static final int ITEM_INNER = 1;
	public static final int ITEM_PANTS = 2;
	
	public static void main(String[] args) {
		EnumTest et = new EnumTest();
//		et.test1();
//		et.test2();
		et.test3();
	}

	/**
	 * enum�� ���ΰ��� ���� ó���� �� �ִ�.
	 * 
	 * MenuType.KR -> "kr"
	 * 
	 */
	private void test3() {
		Item item1 = new Item("��Ʋ����", ItemType.GLASSES);
		Item item2 = new Item("����", ItemType.PERFUME);
		Item item3 = new Item("����", ItemType.COSMETIC);
		
		System.out.println(item1.getItemType().getValue());
		System.out.println(item2.getItemType().getValue());
		System.out.println(item3.getItemType().getValue());
		
		//{"name":"��Ʋ����", "itemType":123}
		Item item4 = new Item("��Ʋ����", ItemType.valueOf(123));
		System.out.println(item4);
		
	}
	
	@Data
	@AllArgsConstructor
	class Item {
		private String name;
		private ItemType itemType;
	}
	

	/**
	 * enum�� enum��.��������� �����Ѵ�.
	 * - namespace�� ���ؼ��� ����� �� �ִ�.
	 * - ���� ���� Ÿ�԰����� �����ϴ�.
	 * - �ڵ尡����, ��Ÿ������� ���
	 */
	private void test2() {
		Car car1 = new Car("�ҳ�Ÿ", CarColor.BLACK);
		Car car2 = new Car("�׷���", CarColor.WHITE);
		System.out.println(car1);
		System.out.println(car2);
	}

	@Data
	@AllArgsConstructor
	class Car {
		private String name;
		private CarColor color;
	}
	
	/**
	 * ����� �Ѱ�
	 *  - ���� ���� ��������� ����.
	 *  - �ڵ� ������, ���޷��� �����ش�.
	 */
	private void test1() {
		Person p1 = new Person("ȫ�浿", COLOR_BLACK);
		Person p2 = new Person("�Ż��Ӵ�", ITEM_INNER);
		System.out.println(p1);
		System.out.println(p2);
	}
	
	@Data
	@AllArgsConstructor
	class Person {
		private String name;
		private int color;
	}
	
	
	
	
}