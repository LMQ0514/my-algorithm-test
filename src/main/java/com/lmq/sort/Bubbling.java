package com.lmq.sort;

import java.util.Date;
import java.util.Random;

/**
 * 冒泡排序
 * O（n^2）
 */
public class Bubbling {
    public static void main(String[] args) {
        int[] array = new int[100000];
        Random random = new Random(10000);
        for (int i = 0;i < array.length;i++) {
            array[i] = random.nextInt();
        }
        Date begin = new Date(System.currentTimeMillis());
        sort(array);
        Date end = new Date(System.currentTimeMillis());
        System.out.println((end.getTime() - begin.getTime()) / 1000);
    }

    public static int[] sort(int[] array){
        boolean flag = false;
        int temp = 0;
        for (int i = 0;i < array.length - 1;i++){
            for (int j = 0;j < array.length - i - 1;j++){
                if(array[j] > array[j + 1]){
                    flag = true;
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            if(flag){
               flag = false;
            }else {
                break;
            }
        }
        return array;
    }
}
