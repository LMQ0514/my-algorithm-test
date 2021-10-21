package com.lmq.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分法查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,3,3,3,3,3,3,3,10};
        List<Integer> search = search(array, 0, array.length - 1, 100);
        System.out.println(search);
    }

    public static List<Integer> search(int[] array,int left,int right,int findVal){
        int mid = (left + right) / 2;
        int midVal = array[mid];
        if(left > right){
            return null;
        }
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
