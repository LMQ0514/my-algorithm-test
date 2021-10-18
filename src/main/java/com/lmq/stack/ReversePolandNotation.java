package com.lmq.stack;

import com.lmq.util.PackNotation;

import java.util.Stack;

/**
 * 逆波兰表达式
 */
public class ReversePolandNotation {
    public static void main(String[] args) {
        String s = "123+(1+1)+9(45+9)";
        PackNotation packNotation = new PackNotation();
        String scan = packNotation.scan(s);
        String[] split = scan.split(",");
        for (String s1 : split) {
            System.out.print(s1);
        }
    }
}
class Calculator{
    public String calculate(String packNotation){
        String[] split = packNotation.split(",");
        Stack<String> stringStack = new Stack<>();

        for (String str : split) {
            if(str.matches("\\d+")){
                stringStack.push(str);
            }else {
                String num1 = stringStack.pop();
                String num2 = stringStack.pop();
                int result;
                if("+".equals(str)){
                    result = Integer.parseInt(num1) + Integer.parseInt(num2);
                }else if("-".equals(str)){
                    result = Integer.parseInt(num2) - Integer.parseInt(num1);
                }else if("*".equals(str)){
                    result = Integer.parseInt(num1) * Integer.parseInt(num2);
                }else if("/".equals(str)){
                    result = Integer.parseInt(num2) / Integer.parseInt(num1);
                }else {
                    throw new RuntimeException("非法运算符");
                }
                stringStack.push(String.valueOf(result));
            }
        }

        return stringStack.pop();
    }
}
