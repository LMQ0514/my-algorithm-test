package com.lmq.commonly;

/**
 * 汉诺塔
 * 分治算法
 */
public class HanoiTower {
    public static void main(String[] args) {
        act(2,'a','b','c');
    }

    public static void act(int num,char start,char helper,char end){
        if(num == 1){
            System.out.println("第" + num + "个盘从" + start + "塔移动到" + end +"塔");
        }else {
            act(num - 1,start,end,helper);
            System.out.println("第" + num + "个盘从" + start + "塔移动到" + end +"塔");
            act(num - 1,helper,start,end);
        }
    }
}
