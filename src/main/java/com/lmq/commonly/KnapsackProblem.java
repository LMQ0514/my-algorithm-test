package com.lmq.commonly;

/**
 * 动态规划实现背包问题
 */
public class KnapsackProblem {
    private int limit = 4;
    private int[] weight = new int[]{1,4,3};
    private int[] val = new int[]{1500,3000,2000};
    private String[] commodity = new String[]{"吉他","音响","电脑"};
    private int[][] v = new int[weight.length + 1][limit + 1];//v[a][b]表示第a个物品放入b容量背包的最大价值
    private int[][] programme = new int[weight.length + 1][limit + 1];//记录放入物品的方案
    public static void main(String[] args) {
        KnapsackProblem knapsackProblem = new KnapsackProblem();
        knapsackProblem.plan();
    }
    public void plan(){
        for(int i = 1;i < v.length;i++){//商品编号
            for(int j = 1;j < v[i].length;j++){//背包限重
                if(weight[i - 1] > j){
                    v[i][j] = v[i - 1][j];
                }else {
                    if((val[i - 1] + v[i - 1][j - weight[i - 1]]) > v[i - 1][j]){
                        v[i][j] = val[i - 1] + v[i - 1][j - weight[i - 1]];
                        programme[i][j] = 1;//记录此方案
                    }else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

//        for (int[] ints : programme) {
//            for (int anInt : ints) {
//                System.out.print(anInt);
//            }
//            System.out.println();
//        }
        int i = programme.length - 1;
        int j = programme[0].length - 1;
        while (i > 0 && j > 0){
            if(programme[i][j] == 1){
                System.out.println("将" + commodity[i - 1] +"放入背包");
                j -= weight[i - 1];
            }
            i--;
        }
    }
}
