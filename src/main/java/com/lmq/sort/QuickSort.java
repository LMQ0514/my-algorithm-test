package com.lmq.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] array = new int[]{5,3,8,7,4,0,6,1,2};
//        int[] array = new int[]{3,2,2};
        sort(array,0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }
    /**
     *
     * @param array
     * @param left  每组数据左下标
     * @param right 每组数据右下标
     */
    public static void sort(int[] array,int left,int right){
        int leftIndex = left;
        int rightIndex = right;
        int temp = 0;
        int pivot = array[(rightIndex + leftIndex) / 2];
        //找到每组数中比pivot大的和小的数据
        while (rightIndex > leftIndex){
            //往左找
            while (pivot > array[leftIndex]){
                leftIndex += 1;
            }
            //往右找
            while (pivot < array[rightIndex]){
                rightIndex -= 1;
            }
            //数组已经排好顺序
            if(leftIndex >= rightIndex){
                break;
            }
            temp = array[leftIndex];
            array[leftIndex] = array[rightIndex];
            array[rightIndex] = temp;
            //防止出现等数死循环
            if(array[leftIndex] == pivot){
                rightIndex -= 1;
            }
            if(array[rightIndex] == pivot){
                leftIndex += 1;
            }
        }
        //向结束递归逼近的条件
        //当出现两个元素数组时，如果没有如下判断会死归
        if(leftIndex == rightIndex){
            leftIndex += 1;
            rightIndex -= 1;
        }
        if(left < rightIndex){
            sort(array,left,rightIndex);
        }
        if(right > leftIndex){
            sort(array,leftIndex,right);
        }
    }
}
