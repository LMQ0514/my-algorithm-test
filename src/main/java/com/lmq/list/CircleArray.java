package com.lmq.list;

import java.util.Scanner;

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
        Circle circle = new Circle(4);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s()show: 显示队列");
            System.out.println("a()add: 添加数据");
            System.out.println("t()take: 取出数据");
            System.out.println("h()head: 显示队列头部");
            System.out.println("q()quit: 退出程序");
            System.out.print("请输入指令:");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    try {
                        circle.showQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    try {
                        System.out.print("请输入数据:");
                        int i1 = scanner.nextInt();
                        circle.add(i1);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 't':
                    try {
                        int i2 = circle.takeHead();
                        System.out.println("取出的数据" + i2);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int i3 = circle.getHead();
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

class Circle {
    private int maxSize;
    private int front = 0;//队列头
    private int rear = 0;//队列尾
    private int[] queue;

    public Circle(int maxSize) {
        this.maxSize = maxSize;
        queue = new int[maxSize];
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //判断队列是否为满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //添加数据
    public void add(int i) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        queue[rear] = i;
        rear = (rear + 1) % maxSize;
    }

    //查看队列头部数据
    public int getHead() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return queue[front];
    }

    //取出数据
    public int takeHead() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        int head = queue[front];
        front = (front + 1) % maxSize;
        return head;
    }

    //显示所有数据
    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        for (int i = front; i < (rear - front + maxSize) % maxSize + front; i++) {
            System.out.println("queue[" + i % maxSize + "]: " + queue[i % maxSize]);
        }
    }
}
