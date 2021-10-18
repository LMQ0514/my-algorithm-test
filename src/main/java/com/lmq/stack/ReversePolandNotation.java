package com.lmq.stack;

import com.lmq.util.PackNotation;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 逆波兰表达式
 */
public class ReversePolandNotation {
    public static void main(String[] args) {
        String s = "11+((2+3)*4)-5";
        PackNotation packNotation = new PackNotation();
        Transformation transformation = new Transformation();
        Calculator calculator = new Calculator();
        String scan = packNotation.scan(s);
        ArrayList<String> parse = transformation.parse(scan);
        System.out.println(calculator.calculate(parse));
    }
}
class Calculator{
    public String calculate(ArrayList<String> packNotation){
        Stack<String> stringStack = new Stack<>();

        for (String str : packNotation) {
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
