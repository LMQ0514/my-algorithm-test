package com.lmq.graph;

import java.util.ArrayList;

/**
 * 简单图结构
 */
public class Graph {
    private ArrayList<String> vertex;
    private int[][] graph;
    private boolean[] isVisit;
    private int edge;

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        String[] strings = {"A", "B", "C", "D", "E"};
        for (int i = 0;i < strings.length;i++){
            graph.insertVertex(strings[i]);
        }
        graph.insertEdge(0,1);
        graph.insertEdge(0,2);
        graph.insertEdge(1,2);
        graph.insertEdge(1,3);
        graph.insertEdge(1,4);

        graph.showGraph();
        graph.dfs();
    }

    public Graph(int n){
        this.vertex = new ArrayList<String>(n);
        this.graph = new int[n][n];
        this.isVisit = new boolean[n];
    }

    public void insertVertex(String str){
        vertex.add(str);
    }

    public void insertEdge(int v1,int v2){
        graph[v1][v2] = 1;
        graph[v2][v1] = 1;
        edge++;
    }

    public int getEdge() {
        return edge;
    }

    public ArrayList<String> getVertex() {
        return vertex;
    }

    public void showGraph(){
        for (int[] ints : graph) {
            System.out.print("|");
            for (int i : ints) {
                System.out.print(i + " ");
            }
            System.out.println("|");
        }
    }
    //获取第一个邻接节点
    private int getFirstNeighbor(int nodeIndex){
        for (int i = 0;i < vertex.size();i++){
            if(graph[nodeIndex][i] > 0){
                return i;
            }
        }
        return -1;
    }
    //获取下一个邻接节点
    private int getNextNeighbor(int n1,int n2){
        for (int k = n2 + 1;k < vertex.size();k++){
            if(graph[n1][k] > 0){
                return k;
            }
        }
        return -1;
    }

    private void dfs(boolean[] isVisit,int i){
        System.out.print(vertex.get(i) + "->");
        isVisit[i] = true;
        int w = getFirstNeighbor(i);
        while (w != -1){
            if(!isVisit[w]){
                dfs(isVisit,w);
            }
            w = getNextNeighbor(i,w);
        }
    }

    public void dfs(){
        for (int i = 0;i < vertex.size();i++){
            if(!isVisit[i]){
                dfs(isVisit,i);
            }
        }
    }
}
