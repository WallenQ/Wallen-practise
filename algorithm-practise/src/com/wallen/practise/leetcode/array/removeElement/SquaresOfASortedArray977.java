package com.wallen.practise.leetcode.array.removeElement;

import java.util.Arrays;

/**
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 * <p>
 * 示例 1： 输入：nums = [-4,-1,0,3,10] 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100] 排序后，数组变为 [0,1,9,16,100]
 * <p>
 * 示例 2： 输入：nums = [-7,-3,2,3,11] 输出：[4,9,9,49,121]
 * <p>
 * 提示：
 * 1 <= nums.length <= 104,-104 <= nums[i] <= 104,nums 已按 非递减顺序 排序
 * <p>
 * 进阶：请你设计时间复杂度为 O(n) 的算法解决本问题
 *
 * @author wallen
 * @date 2024/4/23 19:45
 */
public class SquaresOfASortedArray977 {

    public static void main(String[] args) {
        int[] nums = new int[]{-4,-1,0,3,10};
        int[] result = new SquaresOfASortedArray977().sortedSquares(nums);
        System.out.println(Arrays.toString(result));
    }

    public int[] sortedSquares(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int index = nums.length -1;
        int[] result = new int[nums.length];
        while (start <= end) {
            if (nums[start] * nums[start] > nums[end] * nums[end]){
                result[index--] = nums[start] * nums[start];
                start++;
            } else {
                result[index--] = nums[end] * nums[end];
                end--;
            }
        }

        return result;
    }
}
