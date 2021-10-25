package com.lmq.tree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 赫夫曼树
 * WPL：所有叶子节点带权路径之和
 */
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] array = new int[]{13,7,8,3,29,6,1};
        HuffmanNode node = creat(array);
        node.preOrder();
    }

    public static HuffmanNode creat(int[] array){
        HuffmanNode parentNode = null;
        ArrayList<HuffmanNode> huffmanNodes = new ArrayList<>();
        for (int value : array) {
            huffmanNodes.add(new HuffmanNode(value));
        }
        while (huffmanNodes.size() > 1){
            Collections.sort(huffmanNodes);

            HuffmanNode leftNode = huffmanNodes.get(0);
            HuffmanNode rightNode = huffmanNodes.get(1);
            parentNode = new HuffmanNode(leftNode.value + rightNode.value);
            parentNode.left = leftNode;
            parentNode.right = rightNode;

            huffmanNodes.add(parentNode);

            huffmanNodes.remove(leftNode);
            huffmanNodes.remove(rightNode);
        }
        return huffmanNodes.get(0);
    }
}

class HuffmanNode implements Comparable<HuffmanNode>{
    int value;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(int value){
        this.value = value;
    }

    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.value - o.value;
    }
}
