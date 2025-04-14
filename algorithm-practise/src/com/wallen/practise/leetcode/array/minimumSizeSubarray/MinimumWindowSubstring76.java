package com.wallen.practise.leetcode.array.minimumSizeSubarray;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 * <p>
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意：对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * 示例 1：输入：s = "ADOBECODEBANC", t = "ABC", 输出："BANC", 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 * 示例 2：输入：s = "a", t = "a", 输出："a", 解释：整个字符串 s 是最小覆盖子串。
 * 示例 3:输入: s = "a", t = "aa", 输出: "", 解释: t 中两个字符 'a' 均应包含在 s 的子串中，因此没有符合条件的子字符串，返回空字符串。
 * <p>
 * 提示：m == s.length, n == t.length, 1 <= m, n <= 105, s 和 t 由英文字母组成
 * <p>
 * 进阶：你能设计一个在 o(m+n) 时间内解决此问题的算法吗？
 * 链接：https://leetcode.cn/problems/minimum-window-substring
 *
 * @author Wallen
 * @date 2025/4/2 15:29
 */
public class MinimumWindowSubstring76 {

    public static void main(String[] args) {
        MinimumWindowSubstring76 minimumWindowSubstring76 = new MinimumWindowSubstring76();
        System.out.println(minimumWindowSubstring76.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(minimumWindowSubstring76.minWindow("a", "a"));
        System.out.println(minimumWindowSubstring76.minWindow("a", "aa"));
    }

    Map<Character, Integer> currentMap = new HashMap<>();
    Map<Character, Integer> tMap       = new HashMap<>();

    /**
     * 在滑动窗口类型的问题中都会有两个指针，一个用于「延伸」现有窗口的 r 指针，和一个用于「收缩」窗口的 l 指针。在任意时刻，
     * 只有一个指针运动，而另一个保持静止。我们在 s 上滑动窗口，通过移动 r 指针不断扩张窗口。当窗口包含 t 全部所需的字符后，
     * 如果能收缩，我们就收缩窗口直到得到最小窗口。
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = -1, resultL = -1, resultR = -1, resultLength = Integer.MAX_VALUE, sLength = s.length() - 1;
        while (right < sLength) {
            ++right;
            if (tMap.containsKey(s.charAt(right))) {
                currentMap.put(s.charAt(right), currentMap.getOrDefault(s.charAt(right), 0) + 1);
            }
            while (check() && left <= right) {
                if (right - left + 1 < resultLength) {
                    resultLength = right - left + 1;
                    resultL = left;
                    resultR = right + 1;
                }
                if (currentMap.containsKey(s.charAt(left))) {
                    currentMap.put(s.charAt(left), currentMap.getOrDefault(s.charAt(left), 0) - 1);
                }
                ++left;
            }
        }
        return resultL == -1 ? "" : s.substring(resultL, resultR);
    }

    private boolean check() {
        for (Map.Entry<Character, Integer> next : tMap.entrySet()) {
            if (currentMap.getOrDefault(next.getKey(), 0) < next.getValue()) {
                return false;
            }
        }
        return true;
    }
}
