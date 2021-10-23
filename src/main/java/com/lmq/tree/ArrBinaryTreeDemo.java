package com.lmq.tree;

/**
 * 数组以树的形式遍历
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] array =new int[]{0,1,2,3,4,5,6,7,8,9};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(array);
//        arrBinaryTree.preOrder();
        arrBinaryTree.preOrder(array,2);
    }
}
class ArrBinaryTree{
    private int[] array;

    public ArrBinaryTree(int[] array){
        this.array = array;
    }

    public void preOrder(){
        preOrder(array,0);
    }

    public void preOrder(int[] array,int index){
        if(index < 0 || index > array.length - 1){
            return;
        }
        System.out.print(array[index] + " ");
        if((index * 2 + 1) < array.length){
            preOrder(array,index * 2 + 1);
        }
        if((index * 2 + 2) < array.length){
            preOrder(array,index * 2 + 2);
        }
    }
}