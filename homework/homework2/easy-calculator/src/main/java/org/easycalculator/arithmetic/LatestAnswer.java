package org.easycalculator.arithmetic;

public class LatestAnswer extends Token {
    private static Rational latest = null;
    public static void updateAnswer(Rational r){
        if(r != null){
            latest = new Rational(r);
        }
    }
    public Rational getLatestAnswer(){
        return new Rational(latest);
    }
}
