package com.lmq.commonly;

import java.util.Arrays;

public class FloydDemo {
    public static void main(String[] args) {
        char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int max = 666666;
        int[][] ints = {
                {0, 5, 7, max, max, max, 2},
                {5, 0, max, 9, max, max, 3},
                {7, max, 0, max, 8, max, max},
                {max, 9, max, 0, max, max, max},
                {max, max, 8, max, 0, max, max},
                {max, max, max, 4, 5, 0, 6},
                {2, 3, max, max, 4, 6, 0}
        };
        FloydGraph floydGraph = new FloydGraph(chars, ints);
        floydGraph.floyd();
    }
}

class FloydGraph {
    private char[] vertex;
    private int[][] dis;
    private int[][] infix;

    public FloydGraph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.dis = matrix;
        this.infix = new int[vertex.length][vertex.length];
        for (int i = 0; i < vertex.length; i++) {
            Arrays.fill(infix[i], i);
        }
    }

    public void floyd() {
        int len = 0;
        for (int mid = 0; mid < vertex.length; mid++) {

            for (int start = 0; start < vertex.length; start++) {

                for (int end = 0; end < vertex.length; end++) {
                    len = dis[start][mid] + dis[mid][end];
                    if (dis[start][end] > len) {
                        dis[start][end] = len;
                        infix[start][end] = infix[mid][end];
                    }
                }

            }

        }
        show();
    }

    private void show() {
        System.out.println("中间节点");
        for (int k = 0; k < vertex.length; k++) {
            for (int i = 0; i < vertex.length; i++) {
                int index = infix[k][i];
                System.out.print(vertex[index] + "  ");
            }
            System.out.println();
        }
        System.out.println("邻接矩阵");
        for (int l = 0; l < vertex.length; l++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%3d", dis[l][j]);
            }
            System.out.println();
        }
    }
}
