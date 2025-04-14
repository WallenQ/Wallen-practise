package com.wallen.practise.leetcode.array.spiralMatrix;

import java.util.Arrays;
import java.util.List;

/**
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * <p>
 * 示例 1： 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]] 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2： 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]] 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 * <p>
 * https://leetcode.cn/problems/spiral-matrix/description/
 *
 * @author Wallen
 * @date 2024/10/22 17:44
 */
public class SpiralMatrix54 {

    public static void main(String[] args) {
        int[][]       matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<Integer> result = spiralOrder(matrix);
        if (result != null) {
            System.out.println(Arrays.toString(result.toArray()));
        }
    }

    public static List<Integer> spiralOrder(int[][] matrix) {

        return null;
    }
}
