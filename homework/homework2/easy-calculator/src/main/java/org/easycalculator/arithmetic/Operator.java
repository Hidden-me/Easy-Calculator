package org.easycalculator.arithmetic;

/**
 * This class provides a definition of operators.
 */
public class Operator extends Token {
    private char op;
    // valid operator chars
    private static char[] ops = {'+', '-', '*', '/', '%', '#', '!', '(', ')'};
    private static int[] prior = {0, 0, 1, 1, 1, 2, 3, -1, -1};

    public static boolean isOperator(char c){
        for(char op : ops){
            if(op == c){
                return true;
            }
        }
        return false;
    }
    public Operator(char c) throws IllegalArgumentException{
        if(!isOperator(c)){
            throw new IllegalArgumentException("unknown operator");
        }
        this.op = c;
    }
    public char getOp(){
        return op;
    }
    public int getPriority() throws IllegalStateException{
        for(int i = 0; i < ops.length; i++){
            if(op == ops[i]){
                return prior[i];
            }
        }
        throw new IllegalStateException("unknown operator");
    }

    @Override
    public String toString() {
        return String.valueOf(op);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Operator){
            return ((Operator)obj).op == this.op;
        }
        return false;
    }
}
