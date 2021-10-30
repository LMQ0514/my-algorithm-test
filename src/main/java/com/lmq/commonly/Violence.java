package com.lmq.commonly;

/**
 * 暴力匹配
 */
public class Violence {
    public static void main(String[] args) {
        String str1 = "retyryer5yeyeeryeyeyetguukjg";
        String str2 = "rye哈";
        int match = match(str1, str2);
        System.out.println(match);
    }

    public static int match(String str1,String str2){
        char[] char1 = str1.toCharArray();
        char[] char2 = str2.toCharArray();
        int index1 = 0;
        int index2 = 0;
        while (index1 < char1.length && index2 < char2.length){
            if(char1[index1] == char2[index2]){
                index1++;
                index2++;
            }else {
                index1 = index1 - (index2 - 1);
                index2 = 0;
            }

        }
        if(index2 == char2.length){
            return index1 - index2;
        }else {
            return -1;
        }
    }
}
