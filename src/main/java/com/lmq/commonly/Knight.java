package com.lmq.commonly;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Knight {
    private static int X;
    private static int Y;
    private static boolean[][] visited;
    private static boolean finish;

    public static void main(String[] args) {
        System.out.println("骑士周游算法，开始运行~~");
        X = 8;
        Y = 8;
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X][Y];
        int row = 1;
        int column = 1;
        long start = System.currentTimeMillis();
        act(chessboard,row - 1,column - 1,1);
        long end = System.currentTimeMillis();
        for (int[] ints : chessboard) {
            for (int i : ints) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println("共耗时" + (end - start) + "毫秒");
    }

    public static void act(int[][] chessboard,int row,int column,int step){
        chessboard[row][column] = step;
        visited[row][column] = true;
        ArrayList<Point> points = next(new Point(column, row));
        sort(points);
        while (!points.isEmpty()){
            Point point = points.remove(0);
            if(!visited[point.y][point.x]){
                act(chessboard,point.y,point.x,step + 1);
            }
        }

        if(step < X*Y && !finish){
            chessboard[row][column] = 0;
            visited[row][column] = false;
        }else {
            finish = true;
        }
    }

    public static ArrayList<Point> next(Point curPoint){
        ArrayList<Point> res = new ArrayList<Point>();
        Point point = new Point();
        //5位
        if((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y - 1) >= 0){
            res.add(new Point(point));
        }
        //6位
        if((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y - 2) >= 0){
            res.add(new Point(point));
        }
        //7位
        if((point.x = curPoint.x + 1) < X && (point.y = curPoint.y - 2) >= 0){
            res.add(new Point(point));
        }
        //0位
        if((point.x = curPoint.x + 2) < X && (point.y = curPoint.y - 1) >= 0){
            res.add(new Point(point));
        }
        //1位
        if((point.x = curPoint.x + 2) < X && (point.y = curPoint.y + 1) < Y){
            res.add(new Point(point));
        }
        //2位
        if((point.x = curPoint.x + 1) < X && (point.y = curPoint.y + 2) < Y){
            res.add(new Point(point));
        }
        //3位
        if((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y + 2) < Y){
            res.add(new Point(point));
        }
        //4位
        if((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y + 1) < Y){
            res.add(new Point(point));
        }
        return res;
    }

    public static void sort(ArrayList<Point> points){
        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                if(count1 > count2){
                    return 1;
                }else if(count1 == count2){
                    return 0;
                }else {
                    return -1;
                }
            }
        });
    }
}
