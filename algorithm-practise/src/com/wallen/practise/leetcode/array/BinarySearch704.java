package com.wallen.practise.leetcode.array;

/**
 * 给定一个n个元素有序的（升序）整型数组nums 和一个目标值target ，写一个函数搜索nums中的 target，如果目标值存在返回下标，否则返回 -1。
 * <p>
 * 示例 1:
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * <p>
 * 示例2:
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 * <p>
 * 提示：
 * 1.你可以假设 nums中的所有元素是不重复的。
 * 2.n将在[1, 10000]之间。
 * 3.nums的每个元素都将在[-9999, 9999]之间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/binary-search
 *
 * @author wallen
 * @date 2022/8/17 14:56
 */
public class BinarySearch704 {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 9;
        //int[] nums = {-1,0,3,5,9,12};
        //int target = 2;

        BinarySearch704 item = new BinarySearch704();
        System.out.println(item.searchBetter(nums, target));
    }

    /**
     * 左闭右开区间
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchBetter(int[] nums, int target) {
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
        return -1;
    }

    /**
     * 左闭右闭区间
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchBetter1(int[] nums, int target) {
        // 避免当 target 小于nums[0] nums[nums.length - 1]时多次循环运算
        if (target < nums[0] || target > nums[nums.length - 1]) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * By Wallen
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int length = nums.length;
        if (length == 0) {
            return -1;
        }
        if (length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        return searchBinary(nums, 0, length, target);
    }

    /**
     * @param nums
     * @param startIndex 包含的下标
     * @param endIndex   不包含
     * @param target
     * @return
     */
    private int searchBinary(int[] nums, int startIndex, int endIndex, int target) {
        if (nums[startIndex] > target || nums[endIndex - 1] < target) {
            return -1;
        }
        if (startIndex + 1 >= endIndex) {
            return nums[startIndex] == target ? startIndex : -1;
        }
        int middleIndex = (startIndex + endIndex) / 2;
        if (target > nums[middleIndex]) {
            return searchBinary(nums, middleIndex + 1, endIndex, target);
        } else if (target < nums[middleIndex]) {
            return searchBinary(nums, startIndex, middleIndex, target);
        } else {
            return middleIndex;
        }
    }
}
