package com.lmq;

/**
 * 环形数组
 * front就指向队列的第一个元素，就是是说arr[front]就是队列的第一个元素，front初始值为0
 * rear指向队列的最后一个元素的后一个位置，rear的初始值为0
 * 为什么空出一个位置？加一个空的位置，这个位置不放任何元素，作用就是为了区别队空和队满。如果队空的情况下，rear-front=0
 * 有效长度：rear-front,只在线性数组有用，在src/main/resources/static/CircleArray.png情形时，rear进入新循环，
 * 小于front,不能用rear-front得出负数，于是rear-front+maxSize,加一个长度,再（rear-front+maxSize）%maxSize得出有效长度
 * 空：rear=front
 * 满：(rear+1)%maxSize=front,即rear再前进一个位置就是front的位置(rear+1=front),取模是为了当rear比front大时还能奏效(得到位置)
 */
public class CircleArray {
    public static void main(String[] args) {
    }
}
