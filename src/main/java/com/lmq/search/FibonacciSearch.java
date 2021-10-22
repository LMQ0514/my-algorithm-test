package com.lmq.search;

import java.util.Arrays;

/**
 * 斐波那契查找
 * 相比插值查找，没有使用乘除法，节省时间
 */
public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] ints = new int[]{1,2,3,4,5,6,7,8,9};
        int search = search(ints, 9);
        System.out.println(search);
    }
    //得到斐波那契数组
    public static int[] fib(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2;i < maxSize;i++){
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }
    public static int search(int[] array,int findVal){
        int left = 0;//左端
        int right = array.length - 1;//右端
        int k = 0;//斐波那契数组下标
        int mid = 0;//查找索引
        int[] fib = fib();//斐波那契数组
        //fib[k] - 1:斐波那契数组与目标数组长度匹配，得到下标k
        //斐波那契数组不小于原数组,-1因为斐波那契数组表示长度
        while (right > fib[k] - 1){
            k++;
        }
        //斐波那契数组的值并非连续，可能大于原数组长度，补位
        int[] temp = Arrays.copyOf(array,fib[k]);
        for (int j = array.length;j < fib[k];j++){
            temp[j] = array[right];
        }
        //关键要理解斐波那契数列作用，斐波那契数列的元素表示的是方法所操作数组的长度(temp数组)
        while (left <= right){
            mid = left + fib[k - 1] - 1;
            if(findVal < temp[mid]){
                right = mid -1;
                k--;//mid左边有（k-1）个元素，fib[k - 1] = fib[k - 2] + fib[k - 3],长度变为f[k] = f[k - 1]，k = k - 1.
            }else if(findVal > temp[mid]){
                left = mid + 1;
                k -= 2;//mid右边有（k-2）个元素，fib[k - 2] = fib[k - 3] + fib[k - 4],长度变为f[k] = f[k - 2]，k = k - 2.
            }else {
                if(mid <= right){
                    return mid;
                }else {
                    return right;
                }
            }
        }
        return -1;
    }
}
