package com.lmq.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 插值查找
 */
public class InsertSearch {
    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,4,5,6,7,9,9};
        List<Integer> search = search(array, 0, array.length - 1, 9);
        System.out.println(search);
    }

    public static List<Integer> search(int[] array, int left, int right, int findVal){
        if(left > right || findVal > array[array.length - 1] || findVal < array[0]){
            return null;
        }
        if(array[left] == array[right] && array[left] != findVal){
            return null;
        }
        //自适应
        int mid = left + (right - left) * (findVal - array[left]) / (array[right] - array[left]);
        if(mid > right || mid < left){
            return null;
        }
        int midVal = array[mid];
        if(midVal == findVal){
            ArrayList<Integer> search = new ArrayList<>();
            search.add(mid);
            List<Integer> searchLeft = search(array, left, mid - 1, findVal);
            List<Integer> searchRight = search(array, mid + 1, right, findVal);
            if(searchLeft != null){
                for (Integer integer : searchLeft) {
                    search.add(integer);
                }
            }
            if(searchRight != null){
                for (Integer integer : searchRight) {
                    search.add(integer);
                }
            }
            return search;
        }else if(findVal < midVal){
            return search(array, left, mid - 1, findVal);
        }else {
            return search(array, mid + 1, right, findVal);
        }
    }
}
