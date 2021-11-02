package com.lmq.commonly;

import java.util.Arrays;

/**
 * 迪杰斯特拉算法求最短路径
 */
public class DijkstraDemo {
    public static void main(String[] args) {
        char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int m = Integer.MAX_VALUE;
        int[][] ints = {
                {m,5,7,m,m,m,2},
                {5,m,m,9,m,m,3},
                {7,m,m,m,8,m,m},
                {m,9,m,m,m,4,m},
                {m,m,8,m,m,5,4},
                {m,m,m,4,5,m,6},
                {2,3,m,m,4,6,m}
        };
        DjiGraph djiGraph = new DjiGraph(chars, ints);
        djiGraph.dji(6);
        djiGraph.djiShow();
    }
}
class DjiGraph{
    private int count;      //顶点数
    private char[] vertex;  //顶点数组
    private int[][] line;   //邻接矩阵
    private VisitVertex vv;

    public DjiGraph(char[] vertex, int[][] line) {
        this.count = vertex.length;
        this.vertex = vertex;
        this.line = line;
    }
    public void dji(int index){
        vv = new VisitVertex(count,index,vertex);
        update(index);
        for (int i = 1;i < count;i++){
            index = vv.updateArr();
            update(index);
        }
    }
    //更新VisitVertex
    public void update(int tarIndex){
        int len = 0;
        for (int i = 0;i < count;i++){
            len = vv.getDis(tarIndex) + line[tarIndex][i];
            if(!vv.isVisit(i) && len < vv.getDis(i) && len >= 0){
                vv.updateDis(i,len);
                vv.updatePre(i,tarIndex);
            }
        }
    }

    public void djiShow(){
        vv.show();
    }
}
class VisitVertex{
    private int[] already_arr;//已经访问过的节点
    private int[] pre_visit;  //前一个顶点的下标
    private int[] dis;        //到每个顶点的距离
    private char[] vertex;
    /**
     * @param count 节点数
     * @param index 当前节点下标
     */
    public VisitVertex(int count,int index,char[] vertex) {
        this.already_arr = new int[count];
        this.pre_visit = new int[count];
        this.dis = new int[count];
        this.vertex = vertex;
        already_arr[index] = 1;
        Arrays.fill(dis,Integer.MAX_VALUE);
        dis[index] = 0;
    }
    /**
     * 判断节点是否已访问
     * @param index 节点下标
     */
    public boolean isVisit(int index){
        return already_arr[index] == 1;
    }
    /**
     *修改节点间的距离
     * @param index 下标
     * @param len   距离
     */
    public void updateDis(int index,int len){
        dis[index] = len;
    }
    /**
     * 修改节点前驱节点
     * @param index 下标
     * @param pre   前驱节点的下标
     */
    public void updatePre(int index,int pre){
        pre_visit[index] = pre;
    }
    /**
     *得到当前节点到目标节点的距离
     * @param index
     * @return
     */
    public int getDis(int index){
        return dis[index];
    }
    //寻找新的访问节点
    public int updateArr(){
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0;i < already_arr.length;i++){
            if(already_arr[i] == 0 && min > dis[i]){
                min = dis[i];
                index = i;
            }
        }
        already_arr[index] = 1;
        return index;
    }

    public void show(){
        int count = 0;
        for (int i : dis) {
            if(i != Integer.MAX_VALUE){
                System.out.println(vertex[count] + "-->" + i);
            }
            count++;
        }
    }
}