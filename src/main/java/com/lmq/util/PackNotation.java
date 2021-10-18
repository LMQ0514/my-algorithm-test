package com.lmq.util;

/**
 * 对中缀表达式进行包装
 */
public class PackNotation {
    public String scan(String notation){
        String pack = "";

        for (int i = 0;i < notation.length();i++){
            String charAt = null;
            if (i < notation.length() - 1){
                charAt = notation.substring(i,i+1);
            }else {
                charAt = "" + notation.substring(i).charAt(0);
            }
            if("+".equals(charAt)){
                pack = pack + ",+,";
            }else if("-".equals(charAt)){
                pack = pack + ",-,";
            }else if("*".equals(charAt)){
                pack = pack + ",*,";
            }else if("/".equals(charAt)){
                pack = pack + ",/,";
            }else if("(".equals(charAt) || ")".equals(charAt)){
                pack = pack + "," + charAt + ",";
            } else if(charAt.matches("[0-9]\\d*")){
                pack = pack + charAt;
            }
        }
        return pack;
    }
}
