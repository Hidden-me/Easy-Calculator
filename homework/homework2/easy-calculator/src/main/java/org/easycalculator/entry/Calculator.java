package org.easycalculator.entry;

import org.easycalculator.arithmetic.*;
import org.easycalculator.io.TokenStream;

import java.util.*;

public class Calculator {
    private Rational ans = null;
    private void updateAnswer(Rational r){
        if(r != null){
            ans = new Rational(r);
        }
    }
    private static String preProcess(String exp){
        exp = exp.trim();
        char[] s = exp.toCharArray();
        String result = "";
        for(int i = 0; i < s.length; i++){
            if(s[i] == '+' || s[i] == '-'){
                char c = s[i++];
                while(i < s.length && (s[i] == '+' || s[i] == '-')){
                    if(c == s[i]){
                        c = '+';
                    }else{
                        c = '-';
                    }
                    i++;
                }
                result += c;
                i--;
            }else{
                result += s[i];
            }
        }
        return result;
    }
    private ArrayList<Token> createAntiPolish(TokenStream ts){
        ArrayList<Token> tokens = new ArrayList<Token>();
        Stack<Token> stack = new Stack<Token>();
        boolean lastIsNumber = false;
        while(ts.hasNextToken()){
            Token t = ts.getNextToken();
            // convert "ANS" to numbers
            if(t instanceof LatestAnswer){
                t = getLatestAnswer();
            }
            if(t instanceof Rational){
                // a number
                tokens.add(t);
                lastIsNumber = true;
            }else{
                // an operator
                Operator op = (Operator)t;
                char c = op.getOp();
                if(c == '('){
                    stack.push(t);
                }else if(c == ')'){
                    while(((Operator)stack.peek()).getOp() != '('){
                        tokens.add(stack.pop());
                        if(stack.empty()){
                            throw new ArithmeticException("unclosed bracket");
                        }
                    }
                    stack.pop();
                }else{
                    switch (c){
                        case '+':
                        case '-':
                            if(!lastIsNumber){
                                // sign
                                if(c == '-'){
                                    t = new Operator('#');
                                    op = (Operator)t;
                                }else{
                                    // positive signs have no effect
                                    lastIsNumber = false;
                                    break;
                                }
                            }
                        case '*':
                        case '/':
                        case '%':
                            lastIsNumber = false;
                        case '!':
                            while(!stack.empty() &&
                                    ((Operator)stack.peek()).getPriority()
                                            >= op.getPriority()){
                                tokens.add(stack.pop());
                            }
                            stack.push(t);
                            break;
                    }
                }
            }
        }
        while(!stack.empty()){
            if(((Operator)stack.peek()).getOp() == '('){
                throw new ArithmeticException("unclosed bracket");
            }
            tokens.add(stack.pop());
        }
        return tokens;
    }
    private static Rational evaluateTokens(ArrayList<Token> tokens){
        if(tokens.size() == 0){
            throw new IllegalArgumentException("empty expressions have no value");
        }
        Stack<Token> stack = new Stack<Token>();
        for(Token t : tokens){
            if(t instanceof Operator){
                Rational r = null, a, b;
                char c = ((Operator)t).getOp();
                switch (c){
                    case '+':
                        b = (Rational) stack.pop();
                        a = (Rational) stack.pop();
                        r = a.add(b);
                        break;
                    case '-':
                        b = (Rational) stack.pop();
                        a = (Rational) stack.pop();
                        r = a.subtract(b);
                        break;
                    case '*':
                        b = (Rational) stack.pop();
                        a = (Rational) stack.pop();
                        r = a.multiply(b);
                        break;
                    case '/':
                        b = (Rational) stack.pop();
                        a = (Rational) stack.pop();
                        r = a.dividedBy(b);
                        break;
                    case '%':
                        b = (Rational) stack.pop();
                        a = (Rational) stack.pop();
                        r = a.mod(b);
                        break;
                    case '#':
                        a = (Rational) stack.pop();
                        r = a.negative();
                        break;
                    case '!':
                        a = (Rational) stack.pop();
                        r = a.factorial();
                        break;
                }
                stack.push(r);
            }else{
                stack.push(t);
            }
        }
        if(stack.size() != 1){
            throw new ArithmeticException("invalid expression");
        }
        return (Rational) stack.peek();
    }
    public String evaluate(String str){
        Rational result = null;
        try{
            TokenStream ts = new TokenStream(str);
            ArrayList<Token> tokens = createAntiPolish(ts);
            result = evaluateTokens(tokens);
            updateAnswer(result);
            return String.valueOf(result.value());
        }catch(Exception e){
            return "error";
        }
    }
    public void resolveOnConsole() {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter(";");
        String exp = sc.next();
        exp = preProcess(exp);
        while(!(exp.equals("quit") || exp.equals("q") ||
                exp.equals("exit") || exp.equals("e"))){
            String result = evaluate(exp);
            System.out.println(result);
            exp = sc.next();
            exp = preProcess(exp);
        }
    }
    public Rational getLatestAnswer(){
        return new Rational(ans);
    }
}
