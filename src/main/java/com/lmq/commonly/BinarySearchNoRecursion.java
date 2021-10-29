package com.lmq.commonly;

/**
 * 常用算法之非递归的二分查找
 */
public class BinarySearchNoRecursion {
    public static void main(String[] args) {
        int[] array = new int[]{1,3,44,55,61,74,81,99,100};
        int search = search(array, 1001);
        System.out.println(search);
    }

    /**
     * 二分查找的非递归实现
     * @param array 目标升序数组
     * @param tar   目标数
     * @return      找到的目标数的下标
     */
    public static int search(int[] array,int tar){
        int left = 0;
        int right = array.length - 1;
        int mid;
        while (left <= right){
            mid =(left + right) / 2;
            if(array[mid] == tar){
                return mid;
            }else if(array[mid] > tar){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
