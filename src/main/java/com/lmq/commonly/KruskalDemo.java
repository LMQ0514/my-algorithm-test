package com.lmq.commonly;

import java.util.Arrays;

public class KruskalDemo {
    private static int count;
    public static void main(String[] args) {
        char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        count = chars.length;
        int[][] line = new int[][]{
                {0,12,-1,-1,-1,16,14},
                {12,0,10,-1,-1,7,-1},
                {-1,10,0,3,5,6,-1},
                {-1,-1,3,0,4,-1,-1},
                {-1,-1,5,4,0,2,8},
                {16,7,6,-1,2,0,9},
                {14,-1,-1,-1,8,9,0}
        };

        KruGraph kruGraph = new KruGraph(chars, line);
//        kruGraph.showGraph();
//        System.out.println(kruGraph.getLineCount());
        System.out.println(Arrays.toString(kruGraph.getEdges()));
    }

}

class KruGraph{
    private int count;      //顶点数
    private char[] vertex;  //顶点数组
    private int[][] line;   //邻接矩阵
    private Edge[] edges;   //所有边

    public KruGraph(char[] vertex, int[][] line) {
        this.count = vertex.length;
        this.vertex = vertex;
        this.line = line;
    }
    //打印邻接矩阵
    public void showGraph(){
        for (int i = 0;i < count;i++){
            for (int j = 0;j < count;j++){
                System.out.printf("%4d",line[i][j]);
            }
            System.out.println();
        }
    }
    //统计边的数量
    public int getLineCount(){
        int lineCount = 0;
        for (int i = 0;i < count;i++){
            for (int j = 0;j < count;j++){
                if(line[i][j] > 0){
                    lineCount++;
                }
            }
        }
        return lineCount;
    }
    //对所有边进行排序
    public void sortEdge(Edge[] edges){
        for (int i = 0;i < edges.length;i++){
            for (int j = 0;j <edges.length - 1;j++){
                Edge temp = null;
                if(edges[j].getWeight() > edges[j + 1].getWeight()){
                    temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }
    /**
     * @param c 顶点值
     * @return  指定顶点下标
     */
    public int getIndex(char c){
        int index = -1;
        for (int i = 0;i < vertex.length;i++){
            if(c == vertex[i]){
                index = i;
            }
        }
        return  index;
    }
    /**
     * @return 所有边
     */
    public Edge[] getEdges() {
        int index = 0;
        Edge[] edges = new Edge[getLineCount()];
        for (int i = 0;i < vertex.length;i++){
            for (int j = i + 1;j < vertex.length;j++){
                if(line[i][j] > 0){
                    edges[index++] = new Edge(vertex[i],vertex[j],line[i][j]);
                }
            }
        }
        return edges;
    }
}

class Edge{
    private char start;
    private char end;
    private int weight;

    public Edge(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public char getStart() {
        return start;
    }

    public char getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return ("{" + start + "--->" + end + " weight: " + weight + "}");
    }
}