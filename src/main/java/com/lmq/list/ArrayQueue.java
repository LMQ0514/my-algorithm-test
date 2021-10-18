package com.lmq.list;

import java.util.Scanner;

/**
 * 用线性数组模拟队列
 * 缺点：数组只能用一次
 */
public class ArrayQueue {
    public static void main(String[] args) {
        Queue queue = new Queue(3);
        char key =' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s()show: 显示队列");
            System.out.println("a()add: 添加数据");
            System.out.println("t()take: 取出数据");
            System.out.println("h()head: 显示队列头部");
            System.out.println("q()quit: 退出程序");
            System.out.print("请输入指令:");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    try {
                        queue.showQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    try {
                        System.out.print("请输入数据:");
                        int i1 = scanner.nextInt();
                        queue.add(i1);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 't':
                    try {
                        int i2 = queue.takeHead();
                        System.out.println("取出的数据" + i2);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int i3 = queue.getHead();
                        System.out.println("头部的数据" + i3);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'q':
                    loop = false;
                    break;
            }
        }
        System.out.println("程序退出");
    }
}
class Queue{
    private int maxSize;
    private int front = -1;//队列头
    private int rear = -1;//队列尾
    private int[] queue;

    public Queue(int maxSize){
        this.maxSize=maxSize;
        queue = new int[maxSize];
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return front == rear;
    }
    //判断队列是否为满
    public boolean isFull(){
        return rear == maxSize - 1;
    }
    //添加数据
    public void add(int i){
        if (isFull()){
            throw new RuntimeException("队列已满");
        }
        rear++;
        queue[rear] = i;
    }
    //查看队列头部数据
    public int getHead(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return queue[front + 1];
    }
    //取出数据
    public int takeHead(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        int head = queue[front + 1];
        front++;
        return head;
    }
    //显示所有数据
    public void showQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        for (int i = 0;i < queue.length;i++){
            System.out.println("queue[" + i + "]: " + queue[i]);
        }
    }
}
