package org.easycalculator.io;

import org.easycalculator.arithmetic.*;

import java.text.ParseException;

/**
 * This class reads strings and convert them into rows of tokens.
 */
public class TokenStream {
    private String expression;
    private static boolean isEmptyChar(char c){
        return c == ' ' || c == '\r' || c == '\n' || c == '\t';
    }
    private static boolean isNumberChar(char c){
        return (c >= '0' && c <= '9') || c == '.';
    }
    private Rational nextNumber(boolean cursorMove){
        int i = 0, dots = 0;
        char c = expression.charAt(0);
        while(isNumberChar(c)){
            if(c == '.'){
                dots++;
            }
            if(dots > 1){
                break;
            }
            i++;
            if(i > expression.length() - 1){
                break;
            }
            c = expression.charAt(i);
        }
        String num = expression.substring(0, i);
        if(cursorMove){
            expression = expression.substring(i);
        }
        Rational result = null;
        try {
            result = new Rational(num);
        }catch(ParseException e){
            e.printStackTrace();
        }
        return result;
    }
    public static String trimStart(String str){
        int i = 0;
        while(isEmptyChar(str.charAt(i))){
            i++;
        }
        return str.substring(i);
    }

    public TokenStream(String str){
        this.expression = str;
        if(str.equals("")){
            return;
        }
        while(isEmptyChar(expression.charAt(expression.length() - 1))){
            expression = expression.substring(0, expression.length() - 1);
        }
    }

    /**
     * Get the next token in this stream.
     * @param cursorMove whether the position of cursor will change
     *      <br />Set it to {@code false} so that the return value
     *                   will not change on next call,
     *      or set it to {@code true} to push the stream forward.
     * @return the next token in this stream, or {@code null} if
     * this stream has reached its end or some error occurs during the process.
     */
    public Token getNextToken(boolean cursorMove) throws ArithmeticException{
        if(!hasNextToken()){
            return null;
        }
        expression = trimStart(expression);
        char head = expression.charAt(0);
        if(isNumberChar(head)){
            return nextNumber(cursorMove);
        }else if(Operator.isOperator(head)){
            if(cursorMove){
                expression = expression.substring(1);
            }
            return new Operator(head);
        }else if(expression.substring(0, 3).equals("ANS")){
            if(cursorMove){
                expression = expression.substring(3);
            }
            return new LatestAnswer();
        }else{
            throw new ArithmeticException("unknown symbol");
        }
    }

    public Token getNextToken() throws ArithmeticException {
        return getNextToken(true);
    }

    /**
     * Judge whether the stream has reached its end.
     * @return {@code true} if the stream still has following tokens,
     * or {@code false} otherwise.
     */
    public boolean hasNextToken() {
        char[] chars = expression.toCharArray();
        int cur = 0;
        while (cur < chars.length && isEmptyChar(chars[cur])) {
            cur++;
        }
        return cur < chars.length;
    }
}
