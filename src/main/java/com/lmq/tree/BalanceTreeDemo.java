package com.lmq.tree;

/**
 * 平衡二叉树
 * 相对排序二叉树加入旋转方法
 */
public class BalanceTreeDemo {
    public static void main(String[] args) {
        int[] array = new int[]{10,11,7,6,8,9};
        BalanceTree balanceTree = new BalanceTree();
        for (int i = 0;i < array.length;i++){
            balanceTree.addNode(new BalanceNode(array[i]));
        }
        balanceTree.infixOrder();
        System.out.println();
        System.out.println(balanceTree.getRoot().getLeftHeight());
        System.out.println(balanceTree.getRoot().getRightHeight());
        System.out.println("***************");
        System.out.println(balanceTree.search(8).getLeft().getValue());
        System.out.println(balanceTree.search(8).getRight().getValue());
    }
}
class BalanceTree{
    private BalanceNode root;
    //添加节点
    public void addNode(BalanceNode node){
        if(root == null){
            root = node;
        }else {
            root.add(node);
        }
    }

    public BalanceNode getRoot() {
        return root;
    }

    //中序遍历二叉排序树
    public void infixOrder(){
        if(root != null){
            root.infixOrder();
        }
    }
    //查找指定节点
    public BalanceNode search(int value){
        if(root == null){
            return null;
        }
        return root.search(value);
    }
    //查找指定节点的父节点
    public BalanceNode searchParent(int value){
        if(root == null || root.getValue() == value){
            return null;
        }
        return root.searchParent(value);
    }
    //查找指定节点右子树最小值
    public int getRightMin(BalanceNode node){
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
        BalanceNode tar = search(value);
        if(tar == null){
            return;
        }
        if(root.getLeft() == null && root.getRight() == null){
            root = null;
            return;
        }
        BalanceNode parentNode = searchParent(value);
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
class BalanceNode{
    private int value;
    private BalanceNode left;
    private BalanceNode right;

    public BalanceNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BalanceNode getLeft() {
        return left;
    }

    public void setLeft(BalanceNode left) {
        this.left = left;
    }

    public BalanceNode getRight() {
        return right;
    }

    public void setRight(BalanceNode right) {
        this.right = right;
    }
    //添加节点
    public void add(BalanceNode node){
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
        if(getLeftHeight() - getRightHeight() > 1){
            if(this.left != null && this.left.getRightHeight() > this.left.getLeftHeight()){
                this.left.leftRotate();
                rightRotate();
            }else {
                rightRotate();
            }
            return;
        }
        if(getRightHeight() - getLeftHeight() > 1){
            if(this.right != null && this.right.getLeftHeight() > this.right.getRightHeight()){
                this.right.rightRotate();
                leftRotate();
            }else {
                leftRotate();
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
    public BalanceNode search(int value){
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
    public BalanceNode searchParent(int value){
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
    //计算树的高度
    public int getHeight(){
        int leftHeight = 0;
        int rightHeight = 0;
        if(this.left != null){
            leftHeight = this.left.getHeight();
        }
        if(this.right != null){
            rightHeight = this.right.getHeight();
        }
        return Math.max(leftHeight,rightHeight) + 1;
    }
    //计算左子树高度
    public int getLeftHeight(){
        if(this.left == null){
            return 0;
        }
        return this.left.getHeight();
    }
    //计算右子树高度
    public int getRightHeight(){
        if(this.right == null){
            return 0;
        }
        return this.right.getHeight();
    }
    //左旋
    public void leftRotate(){
        BalanceNode newNode = new BalanceNode(value);
        newNode.left = left;
        newNode.right = right.left;
        value = right.value;
        left = newNode;
        right = right.right;
    }
    //右旋
    public void rightRotate(){
        BalanceNode newNode = new BalanceNode(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }
}