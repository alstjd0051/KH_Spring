package com.kh.jdk8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamStudy {

	public static void main(String[] args) {
		StreamStudy study = new StreamStudy();
//		study.test1();
//		study.test2();
		study.test3();
	}

	/**
	 * map
	 * a -> b
	 */
	private void test3() {
		
	}

	/**
	 * distinct filter
	 */
	private void test2() {
		List<Integer> list = Arrays.asList(1, 2, 3, 2, 4, 3, 2, 1, 5, 3, 4, 5);
		Stream<Integer> stream = list.stream();
//		stream.forEach(n -> System.out.print(n));
//		stream
//		.distinct()
//		.forEach(n -> System.out.print(" " + n));
		stream
				.distinct()
				.filter(n -> n % 2 != 0)
				.sorted() // 정렬이 필요할때
				.forEach(n -> System.out.print(" " + n));

		String[] names = {"감감찬", "강원래", "송민성", "조성모", "강호동"};
//		Stream<String> namestream = Arrays.stream(names);
		Arrays
			.stream(names)
			.filter(name -> name.startsWith("강"))
			.forEach(System.out::println);
	}

	/**
	 * stream 배열이나 컬렉션등을 일관되게 제어하려는 추상화 객체
	 * 
	 */
	private void test1() {
		int[] arr = { 1, 2, 3, 4, 5 };

		IntStream arrStream = Arrays.stream(arr);
//		arrStream.forEach(n -> System.out.println(n));
		arrStream.forEach(System.out::println);

		List<String> list = new ArrayList<>();
		list.add("홍길동");
		list.add("홍난파");
		list.add("고길동");
		list.add("홍진호");

		Stream<String> listStream = list.stream();
		listStream.sorted().forEach(System.out::println);
//		listStream.forEach(System.out::println);

		Stream<Double> doubleStream = Stream.of(0.1, 1.2, 3.456);

	}

}
