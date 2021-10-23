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
        Node node5 = new Node(5, "黄忠");
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        BinaryTree binaryTree = new BinaryTree(root);
        binaryTree.preOrder();
//        binaryTree.infixOrder();
//        binaryTree.postOrder();
//        System.out.println(binaryTree.preOrderSearch(1));
//        System.out.println(binaryTree.infixOrderSearch(1));
//        System.out.println(binaryTree.postOrderSearch(1));
        System.out.println("*******************************");
        binaryTree.delete(14);
        binaryTree.preOrder();
    }
}

class BinaryTree{
    private Node root;

    public BinaryTree(Node root){
        this.root = root;
    }

    //前序遍历
    public void preOrder(){
        if(root != null){
            root.preOrder();
        }else {
            System.out.println("该树为空");
        }
    }
    //中序遍历
    public void infixOrder(){
        root.infixOrder();
    }
    //后序遍历
    public void postOrder(){
        root.postOrder();
    }
    //前序查找
    public Node preOrderSearch(int no){
        return root.preOrderSearch(no);
    }
    //中序查找
    public Node infixOrderSearch(int no){
        return root.infixOrderSearch(no);
    }
    //后序查找
    public Node postOrderSearch(int no){
        return root.postOrderSearch(no);
    }
    //删除
    public void delete(int no){
        if(root != null){
            if(root.getId() == no){
                root = null;
                return;
            }
            root.delete(no);
        }else {
            System.out.println("该树为空");
        }
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
    //前序查找
    public Node preOrderSearch(int no){
        if(this.id == no){
            return this;
        }
        Node resNode = null;
        if(this.left != null){
            resNode = this.left.preOrderSearch(no);
        }
        if(resNode != null && resNode.id == no){
            return resNode;
        }
        if(this.right != null){
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }
    //中序查找
    public Node infixOrderSearch(int no){
        Node resNode = null;
        if(this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }
        if(resNode != null && resNode.id == no){
            return resNode;
        }
        if(this.id == no){
            return this;
        }
        if(this.right != null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }
    //后序查找
    public Node postOrderSearch(int no){
        Node resNode = null;
        if(this.left != null){
            resNode = this.left.postOrderSearch(no);
        }
        if(resNode != null && resNode.id == no){
            return resNode;
        }
        if(this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        if(resNode != null && resNode.id == no){
            return resNode;
        }
        if(this.id == no){
            resNode = this;
        }
        return resNode;
    }
    //删除节点
    public void delete(int no){
        if(this.left != null && this.left.id == no){
            this.left = null;
            return;
        }
        if(this.right != null && this.right.id == no){
            this.right = null;
            return;
        }
        if(this.left != null){
            this.left.delete(no);
        }
        if(this.right != null){
            this.right.delete(no);
        }
    }
}
