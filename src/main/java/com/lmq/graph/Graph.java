package com.lmq.graph;

import java.util.ArrayList;

/**
 * 简单图结构
 */
public class Graph {
    private ArrayList<String> vertex;
    private int[][] graph;
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
    }

    public Graph(int n){
        this.vertex = new ArrayList<String>(n);
        this.graph = new int[n][n];
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
}
