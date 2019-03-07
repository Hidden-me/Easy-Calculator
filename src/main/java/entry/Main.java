package entry;

import arithmetic.*;
import input.TokenStream;

import java.util.*;

public class Main {
    private static ArrayList<Token> createAntiPolish(TokenStream ts){
        ArrayList<Token> tokens = new ArrayList<Token>();
        Stack<Token> stack = new Stack<Token>();
        boolean lastIsNumber = false;
        while(ts.hasNextToken()){
            Token t = ts.getNextToken();
            // convert "ANS" to numbers
            if(t instanceof LatestAnswer){
                t = ((LatestAnswer)t).getLatestAnswer();
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
    private static Rational evaluate(ArrayList<Token> tokens){
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
    private static Rational evaluate(String str){
        TokenStream ts = new TokenStream(str);
        ArrayList<Token> tokens = createAntiPolish(ts);
        Rational result = null;
        try{
            result = evaluate(tokens);
        }catch(Exception e){
            System.out.println("error");
        }
        return result;
    }
    // merge consecutive '+' and '-'
    private static String preProcess(String exp){
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
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter(";");
        String exp = sc.next();
        exp = preProcess(exp);
        while(!(exp.equals("quit") || exp.equals("q") ||
                exp.equals("exit") || exp.equals("e"))){
            Rational ans = null;
            try{
                ans = evaluate(exp);
            }catch(Exception e){
                System.out.println("error");
            }
            if(ans != null){
                System.out.println(ans.value());
                LatestAnswer.updateAnswer(ans);
            }
            exp = sc.next();
            exp = preProcess(exp);
        }
    }
}
