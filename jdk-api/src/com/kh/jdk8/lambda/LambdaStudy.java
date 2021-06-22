package com.kh.jdk8.lambda;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 
 * Lambda표현식
 * java에서 함수형프로그래밍을 지원하기 위한 api(jdk8부터 사용가능)
 * 
 * - 객체지향프로그래밍(상태(field), 행동(method)을 객체로 관리)와 달리
 * 함수를 값으로써 취급한다.
 * - 상태관리를 하지 않고, 모든 것을 불변으로 처리한다.
 * 
 */
public class LambdaStudy {

	public static void main(String[] args) {
		LambdaStudy study = new LambdaStudy();
//		study.test0();
//		study.test1();
//		study.test2();
//		study.test3();
//		study.test4();
		
//		study.test5();
		study.test6();
		
	}
	
	public void test6() {
		List<Integer> list = new ArrayList<Integer>() {
			{
				for(int i = 1; i <= 10; i++)
					add(i);
			}
		};
//		System.out.println(list);
		
		
		//특정 요소 제거
		list.removeIf(n -> n % 2 == 0);
		
		//요소 대체
		//UnaryOperator : 매개변수와 리턴타입이 같은 경우 Function대신 사용 가능
		list.replaceAll(n -> n * 100);
		
		//모든 요소 순회
		list.forEach(System.out::println);
	}
	
	/**
	 * 메소드 참조 Method Reference
	 * 람다식을 더욱 간결히 표현한 문법. 
	 * 
	 * 베이스가 되는 함수형 인터페이스에 따라 동일한 메소드참조도 기능이 달라질 수 있다. 
	 * 
	 * 1. static: Integer.parseInt("123"):Integer -> Integer::parseInt
	 * 2. non-static: "홍길동".equals(name) -> String::equals (두개의 인자를 받아서 처리)
	 * 3. 특정객체의 메소드: str::equals (한개의 인자를 받아서 처리)
	 * 4. 생성자참조: new Person() -> Person::new (함수형 인터페이스에 따라 여러 생성자를 호출가능)
	 */
	public void test5() {
//		Consumer<String> printer = s -> System.out.println(s);
		Consumer<String> printer = System.out::println;
		printer.accept("홍길동");
		
		//1. static
//		Function<String, Integer> intParser = s -> Integer.parseInt(s);
		Function<String, Integer> intParser = Integer::parseInt;
		int num = intParser.apply("1234567");
		System.out.println("num = " + (num + 1));
		
		//2. non-static
		//문자열 길이를 구하는 람다식 - 메소드참조
//		Function<String, Integer> strLength = s -> s.length();
		Function<String, Integer> strLength = String::length;
		String name = "아라비카 골드";
		System.out.println(name.length());
		System.out.println(strLength.apply(name));
		
		//equals  
//		BiFunction<String, String, Boolean> strEquals = (s1, s2) -> s1.equals(s2);
		BiFunction<String, String, Boolean> strEquals = String::equals;
		System.out.println(strEquals.apply(name, "아라비카 골드"));
		System.out.println(strEquals.apply(name, "ㅋㅋㅋㅋ"));
		
		//3. 특정객체 기준 메소드참조
		String title = "소나기";
//		Predicate<String> equalToTitle = s -> title.equals(s);
		Predicate<String> equalToTitle = title::equals;
		System.out.println(equalToTitle.test("소나기"));
		System.out.println(equalToTitle.test("장마"));
		
		//4. 생성자메소드 참조
//		Supplier<Person> personConstr = () -> new Person();
		Supplier<Person> personConstr = Person::new;
		System.out.println(personConstr.get());
		
//		BiFunction<String, Integer, Person> personConstr2 = (name_, age) -> new Person(name_, age);
		BiFunction<String, Integer, Person> personConstr2 = Person::new;
		System.out.println(personConstr2.apply("홍길동", 35));
		System.out.println(personConstr2.apply("신사임당", 47));
		
//		Function<String, Person> personConstr3 = (name_) -> new Person(name_);
		Function<String, Person> personConstr3 = Person::new;
		System.out.println(personConstr3.apply("이순신"));
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@RequiredArgsConstructor
	class Person {
		@NonNull
		private String name;
		private int age;
	}
	
	/**
	 * @실습문제
	 */
	private void test4() {
		// 1.현재시각 출력 람다식
		Runnable displayNow = () -> {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(sdf.format(new Date()));
		};
		displayNow.run();
		
		// 2.로또(1 ~ 45사이의 중복없는 난수 Set) 생성 람다식
		Supplier<Set<Integer>> lottoPublisher = () -> {
			Set<Integer> lotto = new TreeSet<>();
			while(lotto.size() < 6) {
				lotto.add(new Random().nextInt(45) + 1);
			}
			return lotto;
		};	
		for(int i = 0; i < 5; i++) {
			Set<Integer> lotto = lottoPublisher.get();
			System.out.println(i + 1  + " : "  + lotto);
		}
		
		// 3.환율계산기 : 원화 입력시 달러값을 리턴
		// 1달러는 1100원이다.
		Function<Integer, Double> wonDollarCalc = won -> {
			double rate = 1100;
			return won / rate;
		};
		System.out.println("$" + wonDollarCalc.apply(3000));
		
	}

	/**
	 * JDK가 제공하는 함수형 인터페이스
	 * - 제네릭을 사용해서 람다식 작성 타임에 매개변수나 리턴타입이 결정되도록함.
	 * 
	 * 1. java.lang.Runnable 			: 매개변수 없음 | 리턴값 없음 | run():void
	 * 2. java.util.function.Supplier<R>: 매개변수 없음 | 리턴 R     | get():R
	 * 3. java.util.function.Consumer<T>: 매개변수 T   | 리턴값 없음 | accept(T):void
	 * 4. java.util.function.Function<T,R>: 매개변수 T | 리턴 R     | apply(T):R
	 * 5. java.util.function.Predicate<T>: 매개변수 T  | 리턴 boolean | test(T):boolean
	 */
	private void test3() {
		// Runnable
		Runnable r = () -> {
			for(int i = 0; i < 10; i++) {
				System.out.println(new Date());
			}
		};
		r.run();
		
		// Supplier : 매개변수 없고, 리턴값이 있음.
		Supplier<Long> supplier = () -> new Date().getTime();
		System.out.println(supplier.get());
		
		Supplier<Integer> random1to100 = () -> new Random().nextInt(100) + 1;
		Supplier<Integer> random1to45 = () -> new Random().nextInt(45) + 1;
		System.out.println(random1to100.get());
		System.out.println(random1to45.get());
		
		
		// Consumer : 매개변수 있고, 리턴값이 없음.
		Consumer<String> consumer = name -> System.out.println("이름 : " + name);
		consumer.accept("홍길동");
	
		Consumer<Date> printTime = date -> {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(sdf.format(date));
		};
		
		printTime.accept(new Date());
		
		// Function : 매개변수 있고, 리턴값 있음.
		Function<String, Integer> function = str -> str.length();
		System.out.println(function.apply("안녕하세요"));
		System.out.println(function.apply("hello"));
		System.out.println(function.apply("bye bye"));
		
		// Predicate : 매개변수 있고, 리턴타입이 boolean
		Predicate<Integer> predicate = n -> n % 3 == 0 && n % 5 == 0;
		int num = random1to100.get();
		if(predicate.test(num)) {
			System.out.println("3의 배수 && 5의 배수 : " + num);
		}
		else {
			System.out.println("꽝!");
		}
		
	}

	/**
	 * 함수형 인터페이스
	 * - 추상메소드가 딱 하나인 인터페이스를 베이스로 람다식을 작성할 수 있다.
	 * - @FunctionalInterface 어노테이션을 사용하면, 문법오류를 컴파일타임에 확인 가능
	 */
	public void test2() {
		
		Foo max = (a, b) -> a > b ? a : b;
		System.out.println(max.process(80, 77));
		
		Foo min = (a, b) -> a < b ? a : b;
		System.out.println(min.process(80, 77));

		Foo sum = (a, b) -> a + b;
		System.out.println(sum.process(80, 77));
	
		// 인터페이스를 통해 문자열 이름과 나이를 받아 출력 가능한 람다식작성
		// 1. 인터페이스
		// 2. 람다식 작성
		// 3. 람다식 호출
		Bar printPerson 
			= (name, age) -> System.out.printf("이름 : %s, 나이 : %d%n", name, age);
		printPerson.print("홍길동", 30);
	}
	
	@FunctionalInterface
	interface Bar {
		void print(String s, int n);
	}
	
	
	
	@FunctionalInterface
	interface Foo {
		int process(int a, int b);
//		int process(int a);
	}
	

	/**
	 * 메소드만 전달 또는 변수에 저장이 불가능하므로, 인터페이스를 통해 처리해야한다.
	 * lambda식 사용시에는 인터페이스의 추상메소드가 단 하나만 존재해야한다.
	 * 보기에는 메소드만 전달하는 것처럼 보이지만, 실제로는 객체가 전달된 것임.
	 * 
	 */
	private void test1() {
//		Pita pita = (int a, int b) -> {
//			return Math.sqrt(a * a + b * b);
//		};

		//리턴절 한줄인 경우 {return } 생략가능
//		Pita pita = (int a, int b) -> Math.sqrt(a * a + b * b);
		
		//매개변수 선언부 자료형 생략가능
		Pita pita = (a, b) -> Math.sqrt(a * a + b * b);
		
		double c = pita.calc(100, 30);
		System.out.println("빗변 : " + c);
	}

	/**
	 * 피타고라스의 정리 : 빗변제곱 = 밑변제곱 + 높이제곱
	 * 
	 * 자바에서 메소드는 독립적으로 존재할 수 없다. 인자로 전달되거나, 리턴되거나 모두 불가능.
	 * 객체를 통해서만 전달 가능.
	 * lambda표현식 또한 이런 제한이 적용.
	 */
	private void test0() {
		//익명클래스 : 객체선언과 생성을 동시에 처리
		Pita pita = new Pita() {
			@Override
			public double calc(int a, int b) {
				return Math.sqrt(a * a + b * b);
			}
		};
		
		double c = pita.calc(100, 30);
		System.out.println("빗변 = " + c);
	}
	
	/**
	 * 빗변을 구하는 추상메소드 선언
	 */
	interface Pita {
		double calc(int a, int b);
	}
	

}