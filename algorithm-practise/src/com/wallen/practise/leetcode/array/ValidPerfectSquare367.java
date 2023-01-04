package com.wallen.practise.leetcode.array;

/**
 * 有效的完全平方数
 * <p>
 * 给你一个正整数 num 。如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 * 完全平方数 是一个可以写成某个整数的平方的整数。换句话说，它可以写成某个整数和自身的乘积。
 * 不能使用任何内置的库函数，如 sqrt 。
 * <p>
 * 示例 1：输入：num = 16，输出：true，解释：返回 true ，因为 4 * 4 = 16 且 4 是一个整数。
 * 示例 2：输入：num = 14，输出：false，解释：返回 false ，因为 3.742 * 3.742 = 14 但 3.742 不是一个整数。
 * <p>
 * 提示：1 <= num <= 231 - 1
 * 链接：https://leetcode.cn/problems/valid-perfect-square
 *
 * @author Wallen
 * @date 2023/1/4 15:13
 */
public class ValidPerfectSquare367 {

    public static void main(String[] args) {
        ValidPerfectSquare367 test = new ValidPerfectSquare367();
        System.out.println(test.isPerfectSquare(16));
        System.out.println(test.isPerfectSquare(14));
        System.out.println(test.isPerfectSquare(2147483647));
    }

    public boolean isPerfectSquare(int num) {
        int left = 0, right = num;

        while (left <= right) {
            int middle = (left + right) / 2;
            long result = (long) middle * middle;
            if (result == num) {
                return true;
            } else if (result > num) {
                right = middle - 1;
            } else if (result < num) {
                left = middle + 1;
            }
        }
        return false;
    }
}
