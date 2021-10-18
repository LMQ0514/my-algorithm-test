package com.lmq.stack;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 中缀表达式转换为后缀表达式
 */
public class Transformation {
    public ArrayList<String> parse(String notation){
        String[] split = notation.split(",");
        Stack<String> stack = new Stack<>();
        ArrayList<String> strings = new ArrayList<>();
        for (String str : split) {
            if(str.matches("\\d+")){
                strings.add(str);
            }else if("(".equals(str)){
                stack.push(str);
            }else if(")".equals(str)){
                while (!"(".equals(stack.peek())){
                    strings.add(stack.pop());
                }
                stack.pop();
            }else if(!str.equals("")){
                while (stack.size() > 0 && getPriority(stack.peek()) >= getPriority(str)){
                    strings.add(stack.pop());
                }
                stack.push(str);
            }
        }
        while (stack.size() > 0){
            strings.add(stack.pop());
        }
        return strings;
    }
    public static int getPriority(String s){
        int priority = 0;
        switch (s){
            case "+":
                priority = 1;
                break;
            case "-":
                priority = 1;
                break;
            case "*":
                priority = 2;
                break;
            case "/":
                priority = 2;
            default:
                break;
        }
        return priority;
    }
}
