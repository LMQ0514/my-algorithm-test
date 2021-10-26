package com.lmq.tree;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class HuffmanCode {
    private static StringBuilder code = new StringBuilder();
    private static Map<Byte,String> huffmanCodes = new HashMap<>();
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] strBytes = str.getBytes(StandardCharsets.UTF_8);
        byte[] result = huffmanZip(strBytes);
        System.out.println(Arrays.toString(result));
    }
    /**
     * 将一个字符串所有字符统计
     * @param bytes
     * @return
     */
    public static ArrayList<CodeNode> getNodes(byte[] bytes){
        ArrayList<CodeNode> nodes = new ArrayList<CodeNode>();
        Map<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            Integer count = map.get(b);
            if(count == null){
                map.put(b,1);
            }else{
                map.put(b,count + 1);
            }
        }

        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new CodeNode(entry.getKey(),entry.getValue()));
        }

        return nodes;
    }
    /**
     * 生成赫夫曼树
     * @param list
     * @return
     */
    public static CodeNode createTree(ArrayList<CodeNode> list){
        while (list.size() > 1){
            Collections.sort(list);
            CodeNode leftNode = list.get(0);
            CodeNode rightNode = list.get(1);
            CodeNode parentNode = new CodeNode(null, leftNode.getWeight() + rightNode.getWeight());
            parentNode.setLeft(leftNode);
            parentNode.setRight(rightNode);
            list.remove(leftNode);
            list.remove(rightNode);
            list.add(parentNode);
        }
        return list.get(0);
    }
    /**
     * 前序遍历
     * @param root
     */
    public static void preOrder(CodeNode root){
        if(root != null){
            root.preOrder();
        }else {
            return;
        }
    }
    /**
     * 根据赫夫曼树生成赫夫曼编码
     * @param node
     * @param str
     * @param builder
     */
    public static void getCode(CodeNode node,String str,StringBuilder builder){
        StringBuilder stringBuilder = new StringBuilder(builder);
        stringBuilder.append(str);
        if(node != null){
            if(node.getData() == null){
                getCode(node.getLeft(),"0",stringBuilder);
                getCode(node.getRight(),"1",stringBuilder);
            }else {
                huffmanCodes.put(node.getData(), stringBuilder.toString());
            }
        }
    }
    public static Map<Byte,String> getCode(CodeNode root){
        if(root != null){
            getCode(root,"",code);
        }
        return huffmanCodes;
    }
    /**
     * 根据霍夫曼编码压缩比特数组
     * @param bytes
     * @param huffmanCodes
     * @return
     */
    public static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
        int index = 0;
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(huffmanCodes.get(b));
        }
        byte[] code = new byte[(builder.length() + 7) / 8];
        for (int i = 0;i < builder.length();i += 8){
            String sub = "";
            if(i+8 > builder.length()){
                sub = builder.substring(i);
            }else {
                sub = builder.substring(i,i + 8);
            }
            code[index] = (byte) Integer.parseInt(sub,2);
            index++;
        }
        return code;
    }
    public static byte[] huffmanZip(byte[] former){
        ArrayList<CodeNode> nodes = getNodes(former);
        CodeNode root = createTree(nodes);
        Map<Byte, String> huffmanCode = getCode(root);
        byte[] result = zip(former, huffmanCode);
        return result;
    }
}

class CodeNode implements Comparable<CodeNode>{
    private Byte data;
    private int weight;
    private CodeNode left;
    private CodeNode right;

    public CodeNode(Byte data,int weight){
        this.data = data;
        this.weight = weight;
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public CodeNode getLeft() {
        return left;
    }

    public void setLeft(CodeNode left) {
        this.left = left;
    }

    public CodeNode getRight() {
        return right;
    }

    public void setRight(CodeNode right) {
        this.right = right;
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
        return "CodeNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(CodeNode o) {
        return this.weight - o.weight;
    }
}
