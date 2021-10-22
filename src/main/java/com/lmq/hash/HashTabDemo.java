package com.lmq.hash;

import com.lmq.util.Emp;

import java.util.Scanner;

/**
 * 模拟哈希表
 */
public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(5);
        Scanner scanner = new Scanner(System.in);
        String key = "";
        while (true){
            System.out.println("输入a添加");
            System.out.println("输入s查看");
            System.out.println("输入l查看");
            System.out.println("输入e退出");
            key = scanner.next();
            switch (key){
                case "a":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入姓名");
                    String name = scanner.next();
                    Emp emp = new Emp(id,name);
                    hashTab.addEmp(emp);
                    break;
                case "s":
                    hashTab.showAllEmp();
                    break;
                case "l":
                    System.out.println("输入id");
                    id = scanner.nextInt();
                    hashTab.selectById(id);
                    break;
                case "d":
                    System.out.println("输入id");
                    id = scanner.nextInt();
                    hashTab.deleteById(id);
                    break;
                case "e":
                    return;
            }
        }
    }
}
class HashTab{
    private LinkedList[] linkedListArray;
    private int size;
    public HashTab(int size){
        this.size = size;
        linkedListArray = new LinkedList[size];
        for (int i = 0;i < size;i++){
            linkedListArray[i] = new LinkedList();
        }
    }
    //遍历表
    public void showAllEmp(){
        for (int i = 0;i < size;i++){
            linkedListArray[i].showList(i);
        }
    }
    //根据id删除
    public void deleteById(int id){
        linkedListArray[getHash(id)].deleteList(id,getHash(id));
    }
    //根据id查找
    public void selectById(int id){
        linkedListArray[getHash(id)].selectList(id,getHash(id));
    }
    //向表中加入数据
    public void addEmp(Emp emp){
        int id = emp.getId();
        linkedListArray[getHash(id)].listAdd(emp);
    }
    //得到hash
    public int getHash(int id){
        return id % size;
    }
}
class LinkedList{
    Emp head = null;
    //向链表中加入数据
    public void listAdd(Emp emp){
        if(head == null){
            head = emp;
            return;
        }
        Emp curEmp = head;
        while (true){
            if(curEmp.getNext() == null){
                break;
            }
            curEmp = curEmp.getNext();
        }
        curEmp.setNext(emp);
    }
    //遍历链表
    public void showList(int no){
        if(head == null){
            System.out.println("第" + (no+1) + "个链表---> 空");
            return;
        }
        Emp curEmp = head;
        while (true){
            System.out.println("第" + (no+1) + "个链表---> id : " + curEmp.getId() + " 姓名: " + curEmp.getName());
            if(curEmp.getNext() == null){
                break;
            }
            curEmp = curEmp.getNext();
        }
    }
    //根据id查找
    public void selectList(int id,int hash) {
        if(head == null){
            System.out.println("第" + (hash + 1) +"号链表还没有员工，无法查找");
            return;
        }
        Emp curEmp = head;
        while (true){
            if(curEmp.getId() == id){
                System.out.println(id + "号员工姓名为:" + curEmp.getName());
                break;
            }
            curEmp = curEmp.getNext();
            if(curEmp == null){
                System.out.println("查无此人");
            }
        }
    }
    //根据id删除
    public void deleteList(int id, int hash) {
        if(head == null){
            System.out.println("第" + (hash + 1) +"号链表还没有员工，无法删除");
            return;
        }
        if(head.getId() == id){
            head = head.getNext();
            return;
        }
        Emp curEmp = head;
        while (true){
            if(curEmp.getNext().getId() == id){
                curEmp.setNext(curEmp.getNext().getNext());
                break;
            }
            curEmp = curEmp.getNext();
            if(curEmp == null){
                System.out.println("查无此人");
            }
        }
    }
}
