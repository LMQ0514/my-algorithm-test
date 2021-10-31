package com.lmq.commonly;

import java.util.Arrays;

/**
 * 普利姆算法解决修路问题
 */
public class PrimDemo {
    public static void main(String[] args) {
        Prim prim = new Prim();
        char[] data = {'A','B','C','D','E','F','G'};
        int count = data.length;
        int[][] weight = new int[][]{
                {-1,5,7,-1,-1,-1,2},
                {5,-1,-1,9,-1,-1,3},
                {7,-1,-1,-1,8,-1,-1},
                {-1,9,-1,-1,-1,4,-1},
                {-1,-1,8,-1,-1,5,4},
                {-1,-1,-1,4,5,-1,6},
                {2,3,-1,-1,4,6,-1}
        };
        Graph graph = new Graph(7);
        prim.createGraph(graph,count,data,weight);
        prim.getWay(graph,0);
    }
}
class Prim{
    public void createGraph(Graph graph,int count,char[] data,int[][] weight){
        graph.count = count;
        for (int i = 0;i < count;i++){
            graph.data[i] = data[i];
            for (int j = 0;j < count;j++){
                graph.weight[i][j] = weight[i][j];
            }
        }
    }
    
    public void showGraph(Graph graph){
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 计算最短路径
     * @param graph 目标图
     * @param v     第一个点下标
     */
    public void getWay(Graph graph,int v){
        int min = Integer.MAX_VALUE;
        int[] visited = new int[graph.count];
        int a = -1;//已经访问过节点的下标
        int b = -1;//尚未访问过节点的下标
        visited[v] = 1;
        for (int k = 1;k < graph.count;k++){
            for (int i = 0;i < graph.count;i++){
                for (int j = 0;j < graph.count;j++){
                    if(visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] != -1 && graph.weight[i][j] < min){
                        min = graph.weight[i][j];
                        a = i;
                        b = j;
                    }
                }
            }
            System.out.println(graph.data[a] + "--->" + graph.data[b] + "权值为：" + graph.weight[a][b]);
            visited[b] = 1;
            min = Integer.MAX_VALUE;
        }
    }
}
class Graph{
    int count;
    char[] data;
    int[][] weight;

    public Graph(int count) {
        this.count = count;
        data = new char[count];
        weight = new int[count][count];
    }
}
