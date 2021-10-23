package com.lmq.tree;

/**
 * 二叉树遍历
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        Node root = new Node(1, "刘备");
        Node node2 = new Node(2, "关羽");
        Node node3 = new Node(3, "张飞");
        Node node4 = new Node(4, "赵云");
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        BinaryTree binaryTree = new BinaryTree(root);
//        binaryTree.preOrder();
//        binaryTree.infixOrder();
        binaryTree.postOrder();
    }
}

class BinaryTree{
    private Node root;

    public BinaryTree(Node root){
        this.root = root;
    }

    //前序遍历
    public void preOrder(){
        root.preOrder();
    }
    //中序遍历
    public void infixOrder(){
        root.infixOrder();
    }
    //后序遍历
    public void postOrder(){
        root.postOrder();
    }
}

class Node{
    private int id;
    private String name;
    private Node left;
    private Node right;

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }
    //中序遍历
    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();;
        }
        System.out.println(this);
        if(this.right != null){
            this.right.infixOrder();
        }
    }
    //后序遍历
    public void postOrder(){
        if(this.left != null){
            this.left.postOrder();
        }
        if(this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);
    }
}
