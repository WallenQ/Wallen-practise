package com.wallen.practise.leetcode.array.minimumSizeSubarray;

/**
 * 长度最小的子数组
 * <p>
 * 给定一个含有 n 个正整数的数组和一个正整数 target ，找出该数组中满足其总和大于等于 target 的长度最小的连续子数组
 * [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * <p>
 * 示例 1： 输入：target = 7, nums = [2,3,1,2,4,3] 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * <p>
 * 示例 2：输入：target = 4, nums = [1,4,4] 输出：1
 * <p>
 * 示例 3： 输入：target = 11, nums = [1,1,1,1,1,1,1,1] 输出：0
 * <p>
 * 提示： 1 <= target <= 109，1 <= nums.length <= 105，1 <= nums[i] <= 105
 * <p>
 * 进阶：如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 *
 * @author wallen
 * @date 2024/4/29 13:47
 */
public class MinimumSizeSubarraySum209 {

    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 3, 1, 2, 4, 3};
        System.out.println(minSubArrayLen(7, nums1));
        int[] nums2 = new int[]{1, 4, 4};
        System.out.println(minSubArrayLen(4, nums2));
        int[] nums3 = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
        System.out.println(minSubArrayLen(11, nums3));
        int[] nums4 = new int[]{1, 2, 3, 4, 5};
        System.out.println(minSubArrayLen(11, nums4));
    }


    public static int minSubArrayLen(int target, int[] nums) {
        //滑动窗口-3
        //return method3(target, nums);
        //滑动窗口
        return method2(target, nums);
        //暴力求解
        //return method1(target, nums);
    }

    /**
     * 滑动窗口-简洁版
     *
     * @param target
     * @param nums
     * @return
     */
    public static int method3(int target, int[] nums) {
        int lo = 0, hi = 0, sum = 0, min = Integer.MAX_VALUE;
        while (hi < nums.length) {
            sum += nums[hi++];
            while (sum >= target) {
                min = Math.min(min, hi - lo);
                sum -= nums[lo++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    /**
     * 滑动窗口
     *
     * @param target
     * @param nums
     * @return
     */
    public static int method2(int target, int[] nums) {
        int result = nums.length + 1;
        int slow = 0;
        int sum = 0;
        int fast = 0;
        while (slow < nums.length) {
            while (fast < nums.length && sum < target) {
                sum += nums[fast++];
            }
            if (sum >= target) {
                if (fast - slow < result) {
                    result = fast - slow;
                }
                sum -= nums[slow];
            }
            slow++;
        }
        if (result > nums.length) {
            return 0;
        }
        return result;
    }

    /**
     * 暴力求解
     *
     * @param target
     * @param nums
     * @return
     */
    public static int method1(int target, int[] nums) {

        int result = nums.length + 1;
        int slow = 0;
        while (slow < nums.length) {
            int fast = slow;
            int sum = 0;
            while (fast < nums.length && sum < target) {
                sum += nums[fast++];
            }
            slow++;
            if (sum >= target && fast - slow + 1 < result) {
                result = fast - slow + 1;
            }
        }
        if (result > nums.length) {
            return 0;
        }
        return result;
    }
}
