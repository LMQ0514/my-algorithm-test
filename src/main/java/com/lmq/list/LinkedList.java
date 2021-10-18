package com.lmq.list;

/**
 * 单向链表,添加到尾部
 */
public class LinkedList {
    public static void main(String[] args) {
        Link link = new Link();
        Node a = new Node(1, "a");
        Node b = new Node(2, "b");
        Node c = new Node(3, "c");

        link.addNode(a);
        link.addNode(b);
        link.addNode(c);

        link.showLinked();
    }
}

class Link{
    private Node head = new Node(0,null);
    //添加一个节点到尾部
    public void addNode(Node node){
        Node temp = head;
        while (true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
    }

    public void showLinked(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空，退出");
            return;
        }
        Node temp = head.next;
        while (true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}
class Node{
    public int num;
    public Object data;
    public Node next;
    public Node(int num,Object data){
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
