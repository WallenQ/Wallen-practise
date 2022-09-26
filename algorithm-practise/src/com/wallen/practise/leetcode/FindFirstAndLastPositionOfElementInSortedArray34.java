package com.wallen.practise.leetcode;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * <p>
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回[-1, -1]。
 * 你必须设计并实现时间复杂度为O(log n)的算法解决此问题。
 * <p>
 * 示例 1：输入：nums = [5,7,7,8,8,10], target = 8,输出：[3,4]
 * <p>
 * 示例2：输入：nums = [5,7,7,8,8,10], target = 6,输出：[-1,-1]
 * <p>
 * 示例 3：输入：nums = [], target = 0,输出：[-1,-1]
 * <p>
 * 链接：https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array
 *
 * @author wallen
 * @date 2022/8/29 18:19
 */
public class FindFirstAndLastPositionOfElementInSortedArray34 {

    public static void main(String[] args) {
        FindFirstAndLastPositionOfElementInSortedArray34 item = new FindFirstAndLastPositionOfElementInSortedArray34();
        int[] nums = {5, 7, 7, 8, 8, 10};
        int[] result = item.searchRange(nums, 7);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }

    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};

        int startIndexLeft = 0;
        int endIndexLeft = nums.length - 1;
        int left = -1;
        while (startIndexLeft <= endIndexLeft) {
            int middleIndexLeft = (startIndexLeft + endIndexLeft) / 2;
            if (nums[middleIndexLeft] == target) {
                left = middleIndexLeft;
                endIndexLeft = middleIndexLeft - 1;
            } else if (nums[middleIndexLeft] > target) {
                endIndexLeft = middleIndexLeft - 1;
            } else {
                startIndexLeft = middleIndexLeft + 1;
            }
        }

        int startIndexRight = 0;
        int endIndexRight = nums.length - 1;
        int right = -1;
        while (startIndexRight <= endIndexRight) {
            int middleIndexRight = (startIndexRight + endIndexRight) / 2;
            if (nums[middleIndexRight] == target) {
                right = middleIndexRight;
                startIndexRight = middleIndexRight + 1;
            } else if (nums[middleIndexRight] > target) {
                endIndexRight = middleIndexRight - 1;
            } else {
                startIndexRight = middleIndexRight + 1;
            }
        }
        result[0] = left;
        result[1] = right;
        return result;
    }
}
