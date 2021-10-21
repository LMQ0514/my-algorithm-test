package com.lmq.sort;

import java.util.Date;
import java.util.Random;

/**
 * 1，希尔排序(交换)
 * 根据数组长度确定步长，根据步长分组比较
 *
 * 2，希尔排序(插入)
 * 根据数组长度确定步长，根据步长分组比较
 *
 * O(n*log n)
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] array = new int[100000];
        Random random = new Random(10000);
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }
        Date begin = new Date(System.currentTimeMillis());
        //sortByChange(array);//6,8,8
        sortByInsert(array);//0.015,0.014,0.013
        Date end = new Date(System.currentTimeMillis());
        System.out.println((end.getTime() - begin.getTime()));
//        int[] array = new int[]{5,2,3,9,7,1,6,8,4};
//        sortByInsert(array);
//        for (int i : array) {
//            System.out.print(i + " ");
//        }
    }

    public static void sortByChange(int[] array) {
        int temp = 0;
        for (int gap = array.length / 2; gap > 0; gap /= 2) {//分几次组，最后一次步长是1
            for (int i = gap; i < array.length; i++) {//对所有组循环，以所有组的第二个元素开始为索引
                for (int j = i - gap; j >= 0; j -= gap) {//每组第一个元素和它的组内第二个元素比较
                    if (array[j + gap] < array[j]) {
                        temp = array[j];
                        array[j] = array[j + gap];
                        array[j + gap] = temp;
                    }
                }
            }
        }
    }

    public static void sortByInsert(int[] array){
        for (int gap = array.length / 2; gap > 0; gap /= 2) {//分几次组，最后一次步长是1
            for (int i = gap; i < array.length; i++) {//对所有组循环，以所有组的第二个元素开始为索引
                int insertIndex = i;
                int insertVal = array[insertIndex];
                if(array[insertIndex] < array[insertIndex - gap]){
                    while (insertIndex - gap >= 0 && insertVal < array[insertIndex - gap]){
                        array[insertIndex] = array[insertIndex - gap];
                        insertIndex -= gap;
                    }
                    array[insertIndex] = insertVal;
                }
            }
        }
    }
}
