package com.wallen.practise.leetcode;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 示例 1: 输入: 123  输出: 321
 * 示例  2: 输入: -123 输出: -321
 * 示例  3: 输入: 120  输出: 21
 * 注意:
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。
 * 请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * @author Wallen
 * 2020/7/6 18:38
 */
public class ReverseInteger7 {

	public static void main(String[] args) {
		System.out.println(reverseIntegerMethod(-2147483648));
	}

	private static int reverseIntegerMethod(int input) {
		String inputStr = String.valueOf(input);
		String newString = inputStr.replace("-", "");
		String a = "";
		if (!newString.equals(inputStr)) {
			//负数
			a = "-";
		}
		char[] chars = newString.toCharArray();
		int length = chars.length;
		int flag = 0;
		for (int i = 0; i < length / 2; i++) {
			char temp = chars[i];
			chars[i] = chars[length - i - 1];
			if (temp == '0' && flag == 0) {
				chars[length - i - 1] = ' ';
			} else {
				flag = 1;
				chars[length - i - 1] = temp;
			}
		}

		Long resultLong = Long.valueOf(a + String.valueOf(chars));
		int resultInteger = resultLong.intValue();
		if (resultLong != (long) resultInteger) {
			return 0;
		} else {
			return resultInteger;
		}
	}

	/**
	 * 官方答案
	 *
	 * @param x
	 * @return
	 */
	public int reverse(int x) {
		int rev = 0;
		while (x != 0) {
			int pop = x % 10;
			x /= 10;
			if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
				return 0;
			}
			if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
				return 0;
			}
			rev = rev * 10 + pop;
		}
		return rev;
	}

	/**
	 * 用户推上来的更好的解法
	 *
	 * @param x
	 * @return
	 */
	public int reverseBest(int x) {
		int ans = 0;
		while (x != 0) {
			if ((ans * 10) / 10 != ans) {
				ans = 0;
				break;
			}
			ans = ans * 10 + x % 10;
			x = x / 10;
		}
		return ans;
	}
}
