package com.lmq;

/**
 * 双向链表
 * 可以双向查询
 */
public class DoubleLinkedList {
    public static void main(String[] args) {
        Double dbl = new Double();
        DoubleNode a = new DoubleNode(1, "a");
        DoubleNode b = new DoubleNode(2, "b");
        DoubleNode c = new DoubleNode(3, "c");

        dbl.addByOrder(b);
        dbl.addByOrder(c);
        dbl.addByOrder(a);

//        DoubleNode newNode = new DoubleNode(3, "upup");
//        dbl.update(newNode);
        dbl.showLinked();
    }
}
class Double{
    private DoubleNode head = new DoubleNode(0,null);

    public DoubleNode getHead() {
        return head;
    }

    //添加一个节点到尾部
    public void addNode(DoubleNode node){
        DoubleNode temp = head;
        while (true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }
    //按照编号添加
    public void addByOrder(DoubleNode node){
        DoubleNode cur = head;
        boolean flag = false;
        while (true){
            if(cur.next == null){
                break;
            }
            if(cur.num > node.num){
                flag = true;
                break;
            }
            cur = cur.next;
        }
        if(flag){
            node.pre = cur.pre;
            cur.pre.next = node;
            cur.pre = node;
            node.next = cur;
        }else {
            cur.next = node;
            node.pre = cur;
        }
    }
    //删除节点
    public void delete(int num){
        if(head.next == null){
            System.out.println("链表为空，无法删除");
            return;
        }
        DoubleNode cur = head.next;
        boolean flag = false;
        while (true){
            if(cur.num == num){
                flag = true;
                break;
            }
            if(cur.next == null){
                break;
            }
            cur = cur.next;
        }
        if(flag){
            if(cur.next == null){
                cur.pre.next = null;
            }else {
                cur.pre.next = cur.next;
                cur.next.pre = cur.pre;
            }
        }else {
            System.out.println("未找到节点,编号: " + num);
        }
    }
    //修改节点
    public void update(DoubleNode newNode){
        if(head.next == null){
            System.out.println("链表为空，无法修改");
            return;
        }
        DoubleNode temp = head;
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
    //查询双向链表
    public void showLinked(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空，退出");
            return;
        }
        DoubleNode temp = head.next;
        while (true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}
class DoubleNode{
    public int num;
    public Object data;
    public DoubleNode next;
    public DoubleNode pre;
    public DoubleNode(int num,Object data){
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
