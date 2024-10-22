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
        int[][] result = generateMatrix(10);
        for (int[] ints : result) {
            for (int anInt : ints) {
                if (anInt < 10) {
                    System.out.print("00" + anInt + " ");
                } else if (anInt < 100) {
                    System.out.print("0" + anInt + " ");
                } else {
                    System.out.print(anInt + " ");
                }
            }
            System.out.println();
        }
    }

    public static int[][] generateMatrix(int n) {
        int     count  = n;
        int[][] result = new int[n][n];
        int     xIndex = -1;
        int     yIndex = 0;
        int     value  = 0;
        while (count > 0) {
            for (int i = 0; i < count; i++) {
                result[yIndex][xIndex + 1 + i] = ++value;
            }
            xIndex = xIndex + count;

            for (int j = 0; j < count - 1; j++) {
                result[yIndex + 1 + j][xIndex] = ++value;
            }
            yIndex = yIndex + count - 1;
            count--;

            for (int i = 0; i < count; i++) {
                result[yIndex][xIndex - 1 - i] = ++value;
            }
            xIndex = xIndex - count;

            for (int j = 0; j < count - 1; j++) {
                result[yIndex -1 - j][xIndex] = ++value;
            }
            yIndex = yIndex - count + 1;
            count--;
        }
        return result;
    }
}
