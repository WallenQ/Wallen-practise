package com.wallen.practise.other;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 大数计算
 * 题目：a=4444的4444次幂，b是a各位数字之和，c是b各位数字之和，d是c各位数字之和，求d
 *
 * @author Wallen
 * 2019/11/26 11:08
 */
public class largeNumber {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		//底数
		int base = 4444;
		//指数
		int index = 4444;
		//计算幂值
		String value = exponentiation(String.valueOf(base), index);
		System.out.println("exponentiation:" + value);
		//计算各位数字之和
		int times = 3;
		String result = summation(value, times);
		System.out.println("summation:" + result);
		long end = System.currentTimeMillis();
		System.out.println("run time:" + (end - start));
		System.out.println("success!");
	}

	/**
	 * 求和
	 *
	 * @param value
	 * @param times 次数
	 */
	private static String summation(String value, int times) {
		while (times-- > 0) {
			List<Integer> result = new ArrayList<>();
			for (int i = 0 ; i < value.length() ; i++ ) {
				result.add(Integer.valueOf(String.valueOf(value.charAt(i))));
			}
			long sum = result.stream().mapToInt(Integer::intValue).sum();
			value = String.valueOf(sum);
		}
		return value;
	}

	/**
	 * 取幂运算
	 *
	 * @param base  底数
	 * @param index 指数
	 */
	private static String exponentiation(String base, int index) {
		BigInteger bigInteger = new BigInteger(base);
		BigInteger value = new BigInteger("1");
		for (int i = 0; i < index; i++) {
			value = value.multiply(bigInteger);
		}
		return value.toString();
	}
}
