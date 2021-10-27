package com.lmq.tree;

import javax.crypto.CipherInputStream;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class HuffmanCode {
    private static StringBuilder code = new StringBuilder();
    private static Map<Byte,String> huffmanCodes = new HashMap<>();
    private static int zeroCount = 0;//防止最后一个字节丢失'0'！！！！！！
    public static void main(String[] args) {
//        String input = "D:\\IDEA\\my-algorithm-test\\src\\main\\resources\\static\\CircleArray.png";
//        String output = "D:\\IDEA\\my-algorithm-test\\src\\main\\resources\\zip\\CircleArray.zip";
//        zipFile(input,output);
        String input = "D:\\IDEA\\my-algorithm-test\\src\\main\\resources\\zip\\CircleArray.zip";
        String output = "D:\\IDEA\\my-algorithm-test\\src\\main\\resources\\unzip\\CircleArray.png";
        unZip(input,output);
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
        //生成二进制字符串
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(huffmanCodes.get(b));
        }
        byte[] code = new byte[(builder.length() + 7) / 8];
        for (int i = 0;i < builder.length();i += 8){
            String sub = "";
            if(i+8 > builder.length()){
                sub = builder.substring(i);
                for (int k = 0;k < sub.length();k++){
                    if(sub.charAt(k) == '1'){
                        break;
                    }
                    if(sub.charAt(k) == '0'){
                        zeroCount++;
                    }
                }
            }else if (i+8 == builder.length()){
                sub = builder.substring(i);
                for (int k = 0;k < sub.length();k++){
                    if(sub.charAt(k) == '1'){
                        break;
                    }
                    if(sub.charAt(k) == '0'){
                        zeroCount++;
                    }
                }
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
    /**
     * 将字节转化为二进制字符串
     * @param b    需要转化的字节
     * @param flag 是否为最后一个字节
     * @return
     */
    public static String toBitString(byte b,boolean flag){
        int temp = b;
        if(!flag){
            temp |= 256;
        }
        String string = Integer.toBinaryString(temp);
        if(!flag){
            return string.substring(string.length() - 8);
        }else {
            for (int i = 0;i < zeroCount;i++){
                string = "0" + string;
            }
            return string;
        }
    }
    /**
     * 解码
     * @param huffmanCodes
     * @param bytes
     * @return
     */
    public static byte[] decode(Map<Byte,String> huffmanCodes,byte[] bytes){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0;i < bytes.length;i++){
            boolean flag = (i == bytes.length - 1);
            stringBuilder.append(toBitString(bytes[i], flag));
        }
        HashMap<String, Byte> huffmanDecodes= new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            huffmanDecodes.put(entry.getValue(), entry.getKey());
        }
        ArrayList<Byte> list = new ArrayList<>();
        for (int j = 0;j < stringBuilder.length();){
            int count = 1;
            boolean isValue = true;
            Byte b = null;
            while (isValue){
                String substring = stringBuilder.substring(j, j + count);
                b = huffmanDecodes.get(substring);
                if(b != null){
                    isValue = false;
                }else {
                    count++;
                }
            }
            list.add(b);
            j += count;
        }
        byte[] resByte = new byte[list.size()];
        for (int k = 0;k < list.size();k++){
            resByte[k] = list.get(k);
        }
        return resByte;
    }
    //压缩文件
    public static void zipFile(String input,String output){
        FileInputStream fileInputStream = null;
        OutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream(input);
            outputStream = new FileOutputStream(output);
            objectOutputStream = new ObjectOutputStream(outputStream);
            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            byte[] zip = huffmanZip(bytes);
            objectOutputStream.writeObject(zip);
            objectOutputStream.writeObject(huffmanCodes);
            objectOutputStream.writeObject(zeroCount);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fileInputStream.close();
                outputStream.close();
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //解压文件
    public static void unZip(String input,String output){
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(input);
            objectInputStream = new ObjectInputStream(fileInputStream);
            fileOutputStream = new FileOutputStream(output);
            byte[] bytes = (byte[]) objectInputStream.readObject();
            Map<Byte, String> map = (Map<Byte, String>) objectInputStream.readObject();
            zeroCount = (int) objectInputStream.readObject();
            byte[] decode = decode(map, bytes);
            fileOutputStream.write(decode);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fileInputStream.close();
                objectInputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
