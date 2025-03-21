package com.wallen.practise.leetcode.array.binarySearch;

/**
 * 69. x 的平方根
 * 给你一个非负整数 x ，计算并返回x的 算术平方根 。
 * 由于返回类型是整数，结果只保留 整数部分，小数部分将被舍去 。
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 * <p>
 * 示例 1：输入：x = 4,输出：2,
 * 示例 2：输入：x = 8,输出：2，解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 * <p>
 * 提示：0 <= x <= 231 - 1
 * <p>
 * 链接：https://leetcode.cn/problems/sqrtx
 *
 * @author wallen
 * @date 2022/9/30 17:42
 */
public class Sqrtx69 {
    public static void main(String[] args) {
        Sqrtx69 sqrtx69 = new Sqrtx69();
        System.out.println(sqrtx69.mySqrt(2147395599));
    }

    public int mySqrt(int x) {
        int start = 0;
        int end = x;
        while (start <= end) {
            int middle = (start + end) / 2;
            if ((long)middle * (long)middle == x) {
                return middle;
            } else if ((long)middle * (long)middle > x) {
                end = middle - 1;
            } else  if ((long)middle * (long)middle < x){
                start = middle + 1;
            }
        }

        return start - 1;
    }
}
