package com.lmq;

import java.util.Stack;

/**
 * 逆波兰表达式
 */
public class ReversePolandNotation {
    public static void main(String[] args) {
        String packNotation = "3,4,+,51,*,6,-";
        Calculator calculator = new Calculator();
        String calculate = calculator.calculate(packNotation);
        System.out.println(calculate);
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
