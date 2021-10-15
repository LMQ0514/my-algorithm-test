package com.lmq;

import java.util.Stack;

import static com.lmq.Order.*;

/**
 * 单向链表,顺序添加
 */
public class LinkedOrderList {
    public static void main(String[] args) {
        Order order = new Order();
        OrderNode a = new OrderNode(1, "a");
        OrderNode b = new OrderNode(2, "b");
        OrderNode c = new OrderNode(3, "c");

        order.addNode(b);
        order.addNode(a);
        order.addNode(c);

        order.showLinked();

//        System.out.println(getLength(order.getHead()));
//        System.out.println(getIndex(order.getHead(),1));
        System.out.println("反转 ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓");
//        reversList(order.getHead());
        reversPrint(order.getHead());

    }
}

class Order{
    private OrderNode head = new OrderNode(0,null);

    public OrderNode getHead() {
        return head;
    }

    //按照num顺序添加一个节点
    public void addNode(OrderNode node){
        OrderNode temp = head;
        boolean flag = false;
        while (true){
            if(temp.next == null){
                break;
            }
            //已经存在相同编号节点
            if(node.num == temp.num){
                flag = true;
                break;
            }
            if(temp.next.num > node.num){
                break;
            }
            temp = temp.next;
        }
        //添加
        if(flag){
            System.out.println("元素已存在，编号: " + node.num);
        }else {
            //添加元素
            node.next = temp.next;
            temp.next = node;
        }

    }
    //修改节点信息
    public void update(OrderNode newNode){
        if(head.next == null){
            System.out.println("链表为空，无法修改");
            return;
        }
        OrderNode temp = head;
        boolean flag = false;
        while (true){
            if(temp.num == newNode.num){
                flag = true;
                break;
            }
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        //修改
        if(flag){
            temp.data = newNode.data;
        }else {
            System.out.println("未找到节点,编号: " + newNode.num);
        }
    }
    //删除节点
    public void delete(int num){
        if(head.next == null){
            System.out.println("链表为空，无法删除");
            return;
        }
        OrderNode temp = head;
        boolean flag = false;
        while (true){
            if(temp.next == null){
                break;
            }
            if(temp.next.num == num){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //删除
        if(flag){
            temp.next = temp.next.next;
        }else {
            System.out.println("未找到节点,编号: " + num);
        }
    }
    //显示链表
    public void showLinked(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空，无法显示");
            return;
        }
        OrderNode temp = head.next;
        while (true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
    //查找链表长度
    public static int getLength(OrderNode head){
        if(head.next == null){
            return 0;
        }
        OrderNode temp = head.next;
        int count = 1;
        while (temp.next != null){
            count++;
            temp = temp.next;
        }
        return count;
    }
    //查找指定元素,index:倒数第index个
    public static OrderNode getIndex(OrderNode head,int index){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空，无法显示");
            return null;
        }

        int length = getLength(head);

        if(index <= 0 || index > length){
            System.out.println("非法索引");
        }

        OrderNode temp = head.next;
        for (int i = 0;i < length - index;i++){
            temp = temp.next;
        }
        return temp;
    }
    //反转链表
    public static void reversList(OrderNode head){
        if(head.next == null || head.next.next ==null){
            return;
        }
        OrderNode cur = head.next;
        OrderNode next = null;
        OrderNode reversHead = new OrderNode(0,null);
        while (cur != null){
            next = cur.next;
            cur.next = reversHead.next;
            reversHead.next = cur;
            cur = next;
        }
        head.next = reversHead.next;
    }
    //反向打印不改变链表结构
    public static void reversPrint(OrderNode head){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空，无法显示");
            return;
        }
        Stack<OrderNode> nodeStack = new Stack<>();
        OrderNode cur = head.next;
        while (cur != null){
            nodeStack.push(cur);
            cur = cur.next;
        }
        while (nodeStack.size() > 0){
            System.out.println(nodeStack.pop());
        }
    }
}

class OrderNode{
    public int num;
    public Object data;
    public OrderNode next;
    public OrderNode(int num,Object data){
        this.num=num;
        this.data=data;
    }
    @Override
    public String toString() {
        return "Node{" +
                "num=" + num +
                ", data=" + data +
                '}';
    }
}
