package com.lmq.recursion;

/**
 * 迷宫问题
 */
public class Maze {
    private final static int WALL = 1;

    public static void main(String[] args) {
        int[][] map = new int[8][7];
        for (int i = 0;i < 8;i++){
            for (int j = 0;j < 7;j++){
                if(i == 0 || i == 7 || j == 0 || j == 6){
                    map[i][j] = WALL;
                }
            }
        }
        map[3][1] = 1;
        map[3][2] = 1;
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        setWay(map,1,1);
        System.out.println("******************");
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    /**
     *map[i][j]的值 1：墙，2：走的位置，3：死路
     * @param map 地图
     * @param i   起点：行
     * @param j   起点：列
     * @return
     */
    public static boolean setWay(int[][] map,int i,int j){
        if(map[6][5] == 2){
            return true;
        }else {
            if(map[i][j] == 0){
                map[i][j] = 2;
                if(setWay(map,i + 1,j)){
                    return true;
                }else if(setWay(map,i ,j + 1)){
                    return true;
                }else if(setWay(map,i - 1,j)){
                    return true;
                }else if(setWay(map,i,j - 1)){
                    return true;
                }else {
                    map[i][j] = 3;
                    return false;
                }
            }else {
                return false;
            }
            }
        }
    }

