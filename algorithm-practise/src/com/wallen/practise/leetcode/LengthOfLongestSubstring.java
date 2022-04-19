package com.wallen.practise.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 无重复字符的最长子串
 *
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 		输入: "abcabcbb"
 * 		输出: 3
 * 		解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * 		输入: "bbbbb"
 * 		输出: 1
 * 		解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 		输入: "pwwkew"
 * 		输出: 3
 * 		解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 				请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * @author Wallen
 * 2020/7/30 16:32
 */
public class LengthOfLongestSubstring {
	public static void main(String[] args) {
		System.out.println(method("abcabcbb"));
		System.out.println(method("bbbbb"));
		System.out.println(method("pwwkew"));
		System.out.println(method(" "));
		System.out.println(method("au"));
	}

	public static int method(String s) {
		if (null == s || s.length() == 0) {
			return 0;
		}
		char[] chars = s.toCharArray();
		//子串长度
		int count = 1;
		int length = chars.length;
		//子串开始游标
		int i = 0;
		int j = 1;
		while (j < length) {
			boolean flag = true;
			for (int k = i; k < j; k++) {
				if (chars[k] == chars[j]) {
					if (k - i > count) {
						count = k - i;
					}
					i = k + 1;
					j = i + 1;
					flag = false;
					break;
				}
			}
			if (flag) {
				j++;
				if (j - i > count) {
					count = j - i;
				}
			}
		}
		return count;
	}

	/**
	 * 官方解法
	 *
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstring(String s) {
		// 哈希集合，记录每个字符是否出现过
		Set<Character> occ = new HashSet<Character>();
		int n = s.length();
		// 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
		int rk = -1, ans = 0;
		for (int i = 0; i < n; ++i) {
			if (i != 0) {
				// 左指针向右移动一格，移除一个字符
				occ.remove(s.charAt(i - 1));
			}
			while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
				// 不断地移动右指针
				occ.add(s.charAt(rk + 1));
				++rk;
			}
			// 第 i 到 rk 个字符是一个极长的无重复字符子串
			ans = Math.max(ans, rk - i + 1);
		}
		return ans;
	}

}
