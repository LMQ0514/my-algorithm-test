package com.lmq.list;

/**
 * 约瑟夫问题
 */
public class Josephu {
    public static void main(String[] args) {
        CircleList circleList = new CircleList();
        circleList.add(5);
        circleList.showBoys();
        circleList.play(1,2,5);
    }
}

class CircleList{
    private Boy first = null;
    private Boy curBoy = null;
    //增加玩家
    public void add(int count){
        if(count < 1){
            System.out.println("非法的参数");
            return;
        }
        for (int i = 1;i <= count;i++){
            Boy boy = new Boy(i);
            if(i == 1){
                first = boy;
                first.setNext(first);
                curBoy =first;
            }
            curBoy.setNext(boy);
            boy.setNext(first);
            curBoy = boy;
        }
    }
    //显示玩家
    public void showBoys(){
        if(first == null){
            System.out.println("队伍为空");
            return;
        }
        curBoy = first;
        while (true){
            System.out.println("小孩的编号：" + curBoy.getNum());
            if (curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }
    /**
     * @param startNum 从startNum开始
     * @param countNum 数countNum次出列
     * @param nums 小孩个数
     */
    public void play(int startNum,int countNum,int nums){
        //数据校验
        if(startNum < 1 || startNum > nums || countNum < 1 || nums < 0){
            System.out.println("非法的参数");
            return;
        }
        curBoy = first;
        //将curBoy指针移到末尾
        while (true){
            if(curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
        //移动指针到startNum
        for (int j = 0;j < startNum - 1;j++){
            first = first.getNext();
            curBoy = curBoy.getNext();
        }
        //游戏开始
        while (true){
            if (curBoy == first){
                break;
            }
            for (int i = 0;i < countNum - 1;i++){
                first = first.getNext();
                curBoy = curBoy.getNext();
            }
            System.out.println(first.getNum() + "出列");
            first = first.getNext();
            curBoy.setNext(first);
        }
        System.out.println("最后出列者:" + first.getNum());
    }
}

class Boy{
    private int num;
    private Boy next;

    public Boy(int num){
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
