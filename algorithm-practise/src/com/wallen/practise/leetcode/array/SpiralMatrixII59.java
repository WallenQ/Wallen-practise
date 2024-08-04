package com.wallen.practise.leetcode.array;

/**
 * 59. 螺旋矩阵 II
 * <p>
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix
 * <p>
 * 示例 1： 输入：n = 3 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * <p>
 * 示例 2： 输入：n = 1 输出：[[1]]
 * <p>
 * 提示：1 <= n <= 20
 * <p>
 * https://leetcode.cn/problems/spiral-matrix-ii/description/
 *
 * @author wallen
 * @date 2024/5/14 14:16
 */
public class SpiralMatrixII59 {

    public static void main(String[] args) {
        System.out.println(generateMatrix(3));
    }

    public static int[][] generateMatrix(int n) {

        return new int[3][3];
    }
}
