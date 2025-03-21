package com.wallen.practise.leetcode.array.binarySearch;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * <p>
 * 示例 1:输入: nums = [1,3,5,6], target = 5, 输出: 2
 * 示例 2:输入: nums = [1,3,5,6], target = 2, 输出: 1
 * 示例 3:输入: nums = [1,3,5,6], target = 7, 输出: 4
 * <p>
 * 提示:1 <= nums.length <= 104, -104 <= nums[i] <= 104, nums 为无重复元素的升序排列数组, -104 <= target <= 104
 * <p>
 * 链接：https://leetcode.cn/problems/search-insert-position
 *
 * @author wallen
 * @date 2022/9/26 15:16
 */
public class SearchInsertPosition35 {
    public static void main(String[] args) {
        SearchInsertPosition35 test = new SearchInsertPosition35();
        int[] nums = {1, 3, 5, 6};
        int target = 2;
        System.out.println(test.searchInsert(nums, target));
    }

    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        if (left == nums.length) {
            return nums.length;
        }
        return nums[left] > target ? left : left + 1;
    }
}
