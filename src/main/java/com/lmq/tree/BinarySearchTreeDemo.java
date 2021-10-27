package com.lmq.tree;

/**
 * 二叉排序树
 */
public class BinarySearchTreeDemo {
    public static void main(String[] args) {
        int[] array = new int[]{45,76,23,57,5,3,7583,53,1};
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        for (int i = 0;i < array.length;i++){
            binarySearchTree.addNode(new OrderNode(array[i]));
        }
        binarySearchTree.infixOrder();
        binarySearchTree.delNode(7583);
        System.out.println("");
        binarySearchTree.infixOrder();
    }
}
class BinarySearchTree{
    private OrderNode root;
    //添加节点
    public void addNode(OrderNode node){
        if(root == null){
            root = node;
        }else {
            root.add(node);
        }
    }
    //中序遍历二叉排序树
    public void infixOrder(){
        if(root != null){
            root.infixOrder();
        }
    }
    //查找指定节点
    public OrderNode search(int value){
        if(root == null){
            return null;
        }
        return root.search(value);
    }
    //查找指定节点的父节点
    public OrderNode searchParent(int value){
        if(root == null || root.getValue() == value){
            return null;
        }
        return root.searchParent(value);
    }
    //查找指定节点右子树最小值
    public int getRightMin(OrderNode node){
        if(node.getLeft() == null){
            delNode(node.getValue());
            return node.getValue();
        }
        while (node.getLeft() != null){
            node = node.getLeft();
        }
        delNode(node.getValue());
        return node.getValue();
    }
    //删除叶子节点
    public void delNode(int value){
        OrderNode tar = search(value);
        if(tar == null){
            return;
        }
        if(root.getLeft() == null && root.getRight() == null){
            root = null;
            return;
        }
        OrderNode parentNode = searchParent(value);
        if(tar.getLeft() == null && tar.getRight() == null){//目标为叶子节点
            if(parentNode.getLeft().getValue() == value){
                parentNode.setLeft(null);
                return;
            }
            if(parentNode.getRight().getValue() == value){
                parentNode.setRight(null);
                return;
            }
        }else if(tar.getLeft() != null && tar.getRight() != null){//目标同时有左右子节点
            int rightMin = getRightMin(tar.getRight());
            tar.setValue(rightMin);
        }else {//目标只有左或右子节点
            if(tar.getLeft() != null){//目标只有左子节点
                if(parentNode == null){
                    root = tar.getLeft();
                }else {
                    if(parentNode.getLeft().getValue() == value){
                        parentNode.setLeft(tar.getLeft());
                    }else{
                        parentNode.setRight(tar.getLeft());
                    }
                }
            }else {//目标只有右子节点
                if(parentNode == null){
                    root = tar.getRight();
                }else {
                    if(parentNode.getLeft().getValue() == value){
                        parentNode.setLeft(tar.getRight());
                    }else{
                        parentNode.setRight(tar.getRight());
                    }
                }
            }
        }

    }
}
class OrderNode{
    private int value;
    private OrderNode left;
    private OrderNode right;

    public OrderNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public OrderNode getLeft() {
        return left;
    }

    public void setLeft(OrderNode left) {
        this.left = left;
    }

    public OrderNode getRight() {
        return right;
    }

    public void setRight(OrderNode right) {
        this.right = right;
    }
    //添加节点
    public void add(OrderNode node){
        if(node == null){
            return;
        }
        if(node.value < this.value){
            if(this.left == null){
                this.left = node;
            }else {
                this.left.add(node);
            }
        }else {
            if(this.right == null){
                this.right = node;
            }else {
                this.right.add(node);
            }
        }
    }
    //中序遍历
    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.print("[" + this.value + "] ");
        if(this.right != null){
            this.right.infixOrder();
        }
    }
    //查找指定节点
    public OrderNode search(int value){
        if(this.value == value){
            return this;
        }else if(this.left != null && value < this.value){
            return this.left.search(value);
        }else if(this.right != null && value >= this.value){
            return this.right.search(value);
        }else {
            return null;
        }
    }
    //查找指定节点父节点
    public OrderNode searchParent(int value){
        if((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)){
            return this;
        }else if(this.left != null && value < this.value){
            return this.left.searchParent(value);
        }else if(this.right != null && value >= this.value){
            return this.right.searchParent(value);
        }else {
            return null;
        }
    }
}
