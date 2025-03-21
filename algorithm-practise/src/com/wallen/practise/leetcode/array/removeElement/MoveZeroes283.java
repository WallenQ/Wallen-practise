package com.wallen.practise.leetcode.array.removeElement;

/**
 * 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * <p>
 * 示例 1: 输入: nums = [0,1,0,3,12] 输出: [1,3,12,0,0]
 * 示例 2: 输入: nums = [0] 输出: [0]
 * <p>
 * 提示: 1 <= nums.length <= 104, -231 <= nums[i] <= 231 - 1
 * <p>
 * 进阶：你能尽量减少完成的操作次数吗？
 * <p>
 * 链接：https://leetcode.cn/problems/move-zeroes/
 *
 * @author Wallen
 * @date 2025/3/21 14:06
 */
public class MoveZeroes283 {

    public static void main(String[] args) {
        int[] nums = {0, 0, 0, 0, 1, 0, 0, 0, 3, 12};
        System.out.println("原始数组：");
        for (int num : nums) {
            System.out.println(num);
        }
        MoveZeroes283 item = new MoveZeroes283();
        item.moveZeroes1(nums);
        System.out.println("移动后的数组：");
        for (int num : nums) {
            System.out.println(num);
        }
    }

    public void moveZeroes(int[] nums) {
        int slowIndex = 0, fastIndex = 1;
        while (fastIndex < nums.length) {
            if (nums[slowIndex] == 0) {
                if (nums[fastIndex] != 0) {
                    int temp = nums[slowIndex];
                    nums[slowIndex] = nums[fastIndex];
                    nums[fastIndex] = temp;
                    slowIndex++;
                }
                fastIndex++;
            } else {
                slowIndex++;
                if (fastIndex == slowIndex) {
                    fastIndex = slowIndex + 1;
                }
            }
        }
    }

    /**
     * 官方解题
     * 使用双指针，左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部。
     * 右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，同时左指针右移。
     * 注意到以下性质：
     * 1.左指针左边均为非零数；
     * 2.右指针左边直到左指针处均为零。
     * 因此每次交换，都是将左指针的零与右指针的非零数交换，且非零数的相对顺序并未改变。
     *
     * @param nums
     */
    public void moveZeroes1(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

}
