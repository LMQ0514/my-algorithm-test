package com.lmq.commonly;

/**
 * KMP算法匹配字符串
 */
public class KMP {
    private static int[] next;
    public static void main(String[] args) {
        String str1 = "ABCDABCDRBFYCP";
        String str2 = "ABCDRha";
        kmpNext(str2);
        int match = match(next, str1, str2);
        System.out.println(match);
    }

    public static int match(int[] next,String string,String tar){
        for (int i = 0,j = 0;i < string.length();i++){
            while (j > 0 && string.charAt(i) != tar.charAt(j)){
                j = next[j - 1];
            }
            if(string.charAt(i) == tar.charAt(j)){
                j++;
            }
            if(j == tar.length()){
                return i - j + 1;
            }
        }
        return -1;
    }

    public static int[] kmpNext(String str){
        next = new int[str.length()];
        next[0]= 0;
        for (int i = 1,j = 0;i < str.length();i++){
            while (j > 0 && str.charAt(i) != str.charAt(j)){
                j = next[j - 1];
            }
            if(str.charAt(i) == str.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
