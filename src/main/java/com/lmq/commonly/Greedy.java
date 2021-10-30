package com.lmq.commonly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 贪心算法
 *
 */
public class Greedy {
    private static HashSet<String> allRegion = new HashSet<>();//所有地区
    private static HashMap<String,HashSet<String>> broadcasts = new HashMap<>();//所有电台和其对应的地区

    public static void main(String[] args) {
        allRegion.add("北京");
        allRegion.add("上海");
        allRegion.add("天津");
        allRegion.add("广州");
        allRegion.add("深圳");
        allRegion.add("成都");
        allRegion.add("杭州");
        allRegion.add("大连");
        HashSet<String> r1 = new HashSet<>();
        r1.add("北京");
        r1.add("上海");
        r1.add("天津");
        HashSet<String> r2 = new HashSet<>();
        r2.add("广州");
        r2.add("北京");
        r2.add("深圳");
        HashSet<String> r3 = new HashSet<>();
        r3.add("成都");
        r3.add("上海");
        r3.add("杭州");
        HashSet<String> r4 = new HashSet<>();
        r4.add("上海");
        r4.add("天津");
        HashSet<String> r5 = new HashSet<>();
        r5.add("杭州");
        r5.add("大连");
        broadcasts.put("k1",r1);
        broadcasts.put("k2",r2);
        broadcasts.put("k3",r3);
        broadcasts.put("k4",r4);
        broadcasts.put("k5",r5);
        ArrayList<String> act = act();
        System.out.println(act.toString());
    }
    
    public static ArrayList<String> act(){
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        String max;
        while (allRegion.size() != 0){
            max = null;
            for (String key : broadcasts.keySet()) {
                temp.clear();
                HashSet<String> reg = broadcasts.get(key);
                temp.addAll(reg);
                temp.retainAll(allRegion);
                if(temp.size() != 0 && (max == null || temp.size() > broadcasts.get(max).size())){
                    max = key;
                }
            }
            if(max != null){
                result.add(max);
                allRegion.removeAll(broadcasts.get(max));
            }
        }
        return result;
    }
}
