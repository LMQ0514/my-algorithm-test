package com.lmq.tree;

/**
 * 线索化二叉树
 * 特点：充分利用空余指针，让它们指向相邻节点
 */
public class ThreadBinaryTreeDemo {
    public static void main(String[] args) {
        ThreadNode root = new ThreadNode(1, "1");
        ThreadNode node2 = new ThreadNode(2, "3");
        ThreadNode node3 = new ThreadNode(3, "6");
        ThreadNode node4 = new ThreadNode(4, "8");
        ThreadNode node5 = new ThreadNode(5, "10");
        ThreadNode node6 = new ThreadNode(6, "14");
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node6);
        node2.setLeft(node4);
        node2.setRight(node5);
        ThreadBinaryTree threadBinaryTree = new ThreadBinaryTree(root);
        threadBinaryTree.threadNodes();
        System.out.println(node4.getRight());
        System.out.println(node5.getLeft());
    }
}

class ThreadBinaryTree{
    private ThreadNode root = null;
    private ThreadNode preNode = null;

    public ThreadBinaryTree(ThreadNode root){
        this.root = root;
    }

    public void threadNodes(){
        threadNodes(root);
    }

    public void threadNodes(ThreadNode node){
        if(node == null){
            return;
        }
        threadNodes(node.getLeft());
        if(node.getLeft() == null){
            node.setLeft(preNode);
            node.setLeftType(1);
        }
        if(preNode != null && preNode.getRight() == null){
            preNode.setRight(node);
            preNode.setRightType(1);
        }
        preNode = node;
        threadNodes(node.getRight());
    }
}

class ThreadNode {
    private int id;
    private String name;
    private ThreadNode left;
    private ThreadNode right;
    private int leftType;//0:左子树,1:前节点
    private int rightType;//0:右子树,1:后节点

    public ThreadNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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

    public ThreadNode getLeft() {
        return left;
    }

    public void setLeft(ThreadNode left) {
        this.left = left;
    }

    public ThreadNode getRight() {
        return right;
    }

    public void setRight(ThreadNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "ThreadNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
            ;
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序查找
    public ThreadNode preOrderSearch(int no) {
        if (this.id == no) {
            return this;
        }
        ThreadNode resThreadNode = null;
        if (this.left != null) {
            resThreadNode = this.left.preOrderSearch(no);
        }
        if (resThreadNode != null && resThreadNode.id == no) {
            return resThreadNode;
        }
        if (this.right != null) {
            resThreadNode = this.right.preOrderSearch(no);
        }
        return resThreadNode;
    }

    //中序查找
    public ThreadNode infixOrderSearch(int no) {
        ThreadNode resThreadNode = null;
        if (this.left != null) {
            resThreadNode = this.left.infixOrderSearch(no);
        }
        if (resThreadNode != null && resThreadNode.id == no) {
            return resThreadNode;
        }
        if (this.id == no) {
            return this;
        }
        if (this.right != null) {
            resThreadNode = this.right.infixOrderSearch(no);
        }
        return resThreadNode;
    }

    //后序查找
    public ThreadNode postOrderSearch(int no) {
        ThreadNode resThreadNode = null;
        if (this.left != null) {
            resThreadNode = this.left.postOrderSearch(no);
        }
        if (resThreadNode != null && resThreadNode.id == no) {
            return resThreadNode;
        }
        if (this.right != null) {
            resThreadNode = this.right.postOrderSearch(no);
        }
        if (resThreadNode != null && resThreadNode.id == no) {
            return resThreadNode;
        }
        if (this.id == no) {
            resThreadNode = this;
        }
        return resThreadNode;
    }

    //删除节点
    public void delete(int no) {
        if (this.left != null && this.left.id == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.id == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delete(no);
        }
        if (this.right != null) {
            this.right.delete(no);
        }
    }
}
