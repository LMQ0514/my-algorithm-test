package com.lmq.list;

public class MergeMergeLinkedListTest {
    public static void main(String[] args) {
        MergeLinkedList linkedList1 = new MergeLinkedList(new MergeNode());
        MergeLinkedList linkedList2 = new MergeLinkedList(new MergeNode());

        linkedList1.add(1);
        linkedList1.add(8);
        linkedList1.add(20);
        linkedList2.add(2);
        linkedList2.add(3);
        linkedList2.add(10);

        MergeLinkedList merge = linkedList1.merge(linkedList2);
        merge.show();
    }
}
class MergeLinkedList{
    private MergeNode head;
    public MergeLinkedList(MergeNode head) {
        this.head = head;
    }

    private boolean isEmpty(){
        if(head.getNext() == null){
            return true;
        }else {
            return false;
        }
    }
    public void add(int data){
        MergeNode temp = head;
        while (true) {
            if(temp.getNext() == null){
                MergeNode MergeNode = new MergeNode();
                MergeNode.setData(data);
                temp.setNext(MergeNode);
                break;
            }else {
                if(data > temp.getNext().getData()){
                    temp = temp.getNext();
                }else {
                    MergeNode MergeNode = new MergeNode();
                    MergeNode.setData(data);
                    MergeNode.setNext(temp.getNext());
                    temp.setNext(MergeNode);
                    break;
                }
            }
        }
    }
    public void show(){
        if(isEmpty()){
            System.out.println("链表为空");
        }else {
            MergeNode show = head;
            while (true){
                if(show.getNext() == null){
                    break;
                }
                System.out.print(show.getNext().toString() + " ");
                show = show.getNext();
            }
        }
        System.out.println();
    }
    public int count(){
        MergeNode temp = head;
        int count = 0;
        while (true){
            if(temp.getNext() != null){
                count++;
                temp = temp.getNext();
            }else {
                break;
            }
        }
        return count;
    }
    public MergeNode findLastMergeNode(int i){
        if(i < 0){
            return null;
        }
        int count = this.count();
        int index = -1;
        MergeNode temp = head;
        if (i > count){
            return null;
        }

        while (true){
            temp = temp.getNext();
            index++;
            if (index == count - i){
                return temp;
            }
        }
    }
    public MergeLinkedList reverse(){
        if(head.getNext() == null || head.getNext().getNext() == null){
            return null;
        }
        MergeNode cur = head.getNext();
        MergeNode next = null;
        MergeNode reverseHead = new MergeNode();
        while (cur != null){
            next = cur.getNext();
            cur.setNext(reverseHead.getNext());
            reverseHead.setNext(cur);
            cur = next;
        }
        return new MergeLinkedList(reverseHead);
    }
    public MergeLinkedList merge(MergeLinkedList MergeLinkedList){
        MergeNode cur1 = head.getNext();
        MergeNode next1 = null;
        MergeNode cur2 = MergeLinkedList.head.getNext();
        MergeNode next2 = null;
        MergeNode mergeHead = new MergeNode();
        MergeNode temp = mergeHead;
        while (cur1 != null || cur2 != null){
            if(cur1 == null){
                temp.setNext(cur2);
                break;
            }else if(cur2 == null){
                temp.setNext(cur1);
                break;
            }else {
                if(cur1.getData() > cur2.getData()){
                    next2 = cur2.getNext();
                    cur2.setNext(temp.getNext());
                    temp.setNext(cur2);
                    temp = cur2;
                    cur2 = next2;
                }else {
                    next1 = cur1.getNext();
                    cur1.setNext(temp.getNext());
                    temp.setNext(cur1);
                    temp = cur1;
                    cur1 = next1;
                }
            }
        }
        return new MergeLinkedList(mergeHead);
    }
}
class MergeNode{
    private int data;
    private MergeNode next;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public MergeNode getNext() {
        return next;
    }

    public void setNext(MergeNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "MergeNode{" +
                "data=" + data +
                '}';
    }
}