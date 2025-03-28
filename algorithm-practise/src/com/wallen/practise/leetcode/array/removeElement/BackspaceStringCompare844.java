package com.wallen.practise.leetcode.array.removeElement;

/**
 * 844. 比较含退格的字符串
 * <p>
 * 给定 s 和 t 两个字符串，当它们分别被输入到空白的文本编辑器后，如果两者相等，返回 true 。# 代表退格字符。
 * 注意：如果对空文本输入退格字符，文本继续为空。
 * <p>
 * 示例 1：输入：s = "ab#c", t = "ad#c", 输出：true, 解释：s 和 t 都会变成 "ac"。
 * 示例 2：输入：s = "ab##", t = "c#d#", 输出：true, 解释：s 和 t 都会变成 ""。
 * 示例 3：输入：s = "a#c", t = "b", 输出：false, 解释：s 会变成 "c"，但 t 仍然是 "b"。
 * <p>
 * 提示：
 * 1 <= s.length, t.length <= 200
 * s 和 t 只含有小写字母以及字符 '#'
 * <p>
 * 进阶：你可以用 O(n) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
 * <p>
 * 链接：https://leetcode.cn/problems/backspace-string-compare/
 *
 * @author Wallen
 * @date 2025/3/28 14:26
 */
public class BackspaceStringCompare844 {

    public static void main(String[] args) {
        BackspaceStringCompare844 backspaceStringCompare844 = new BackspaceStringCompare844();

        //System.out.println(backspaceStringCompare844.backspaceCompare("ab#c", "ad#c"));
        //System.out.println(backspaceStringCompare844.backspaceCompare("ab##", "c#d#"));
        //System.out.println(backspaceStringCompare844.backspaceCompare("a#c", "b"));
        //System.out.println(backspaceStringCompare844.backspaceCompare("a##c", "#a#c"));
        //System.out.println(backspaceStringCompare844.backspaceCompare("bxj##tw", "bxo#j##tw"));
        System.out.println(backspaceStringCompare844.backspaceCompare("a#c###", "ad#c"));
    }

    public boolean backspaceCompare(String s, String t) {
        byte[] sBytes = s.getBytes();
        byte[] tBytes = t.getBytes();
        int    sIndex = sBytes.length - 1, tIndex = tBytes.length - 1;

        while (sIndex >= 0 || tIndex >= 0) {
            int backspace = 0;
            while (sIndex >= 0 && (sBytes[sIndex] == '#' || backspace > 0)) {
                if (sBytes[sIndex] == '#') {
                    backspace++;
                } else if (backspace > 0) {
                    backspace--;
                }
                sIndex--;
            }

            backspace = 0;
            while (tIndex >= 0 && (tBytes[tIndex] == '#' || backspace > 0)) {
                if (tBytes[tIndex] == '#') {
                    backspace++;
                } else if (backspace > 0) {
                    backspace--;
                }
                tIndex--;
            }

            if (sIndex >= 0 && tIndex >= 0) {
                if (sBytes[sIndex--] != tBytes[tIndex--]) {
                    return false;
                }
            } else {
                return sIndex < 0 && tIndex < 0;
            }
        }
        return true;
    }
}
