package org.easycalculator.arithmetic;

import java.text.ParseException;

/**
 * This class provides a structure definition
 * and a series of calculating methods for rationals.
 */
public class Rational extends Token {
    private int num, den; // large values might cause overflow
    private int sign = 1; // 1 indicates positivity or zero, while -1 means negativity
    // use this method to keep num and den positive
    private void processSign(){
        if(num < 0){
            sign = -sign;
            num = -num;
        }
        if(den < 0){
            sign = -sign;
            den = -den;
        }
    }
    // use this method to calculate the greatest common divisor
    private int getGCD(){
        int a = num, b = den, c = a % b;
        while(c != 0){
            a = b;
            b = c;
            c = a % b;
        }
        return b;
    }
    // use this method to do reduction of this rational object
    private void doReduction(){
        int gcd = getGCD();
        num = num / gcd;
        den = den / gcd;
    }
    // make the object a standard rational
    private void standardize(){
        processSign();
        doReduction();
    }

    /**
     * Create a arithmetic.Rational object, whose value will equal to
     * {@code num / den}.
     * <br />The rational will be automatically converted into a standard form.
     * <br />Equal rationals have the same standard form,
     * while unequal rationals have different ones.
     * @param num the numerator
     * @param den the denominator
     * @throws IllegalArgumentException if {@code den == 0}
     */
    public Rational(int num, int den) throws IllegalArgumentException {
        if(den == 0){
            throw new IllegalArgumentException("the denominator should not be zero");
        }
        this.num = num;
        this.den = den;
        standardize();
    }

    /**
     * Create a arithmetic.Rational object, whose value will equal to an integer
     * {@code num}.
     * @param num the integer
     */
    public Rational(int num) {
        this.num = num;
        this.den = 1;
        processSign();
    }

    public Rational(Rational r){
        this.sign = r.sign;
        this.num = r.num;
        this.den = r.den;
    }

    public Rational(String str) throws ParseException {
        int dot = -1, num = 0, den = 1;
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(c == '.'){
                if(dot >= 0){
                    throw new ParseException("more than one dot is not allowed", i);
                }else{
                    dot = str.length() - i - 1;
                }
            }else if(c >= '0' && c <= '9'){
                num = num * 10 + c - '0';
            }else{
                throw new ParseException("unknown digit", i);
            }
        }
        for(int i = 0; i < dot; i++){
            den = den * 10;
        }
        this.num = num;
        this.den = den;
        standardize();
    }

    /**
     * Get the sum of two rationals.
     * @param r another {@code Rational} object
     * @return a {@code Rational} object representing "this + r",
     * or a {@code null} if {@code r} is {@code null}
     */
    public Rational add(Rational r) {
        Rational result = null;
        if(r != null){
            int num = this.sign * this.num * r.den + r.sign * r.num * this.den;
            int den = this.den * r.den;
            result = new Rational(num, den);
        }
        return result;
    }

    /**
     * Get the difference between two rationals.
     * @param r another {@code Rational} object
     * @return a {@code Rational} object representing "this - r",
     * or a {@code null} if {@code r} is {@code null}
     */
    public Rational subtract(Rational r) {
        Rational result = null;
        if(r != null){
            int num = this.sign * this.num * r.den - r.sign * r.num * this.den;
            int den = this.den * r.den;
            result = new Rational(num, den);
        }
        return result;
    }

    /**
     * Get the product of two rationals.
     * @param r another {@code Rational} object
     * @return a {@code Rational} object representing <code>"this * r"</code>,
     * or a {@code null} if {@code r} is {@code null}
     */
    public Rational multiply(Rational r) {
        Rational result = null;
        if(r != null){
            int num = this.sign * this.num * r.sign * r.num;
            int den = this.den * r.den;
            result = new Rational(num, den);
        }
        return result;
    }

    /**
     * Get the quotient of two rationals.
     * @param r another {@code Rational} object
     * @return a {@code Rational} object representing <code>"this / r"</code>,
     * or a {@code null} if {@code r} is {@code null}
     * @throws ArithmeticException if {@code r} equals zero
     */
    public Rational dividedBy(Rational r) throws ArithmeticException {
        Rational result = null;
        if(r != null){
            if(r.isZero()){
                throw new ArithmeticException("divided by zero");
            }
            int num = this.sign * this.num * r.sign * r.den;
            int den = this.den * r.num;
            result = new Rational(num, den);
        }
        return result;
    }

    public Rational mod(Rational r) throws ArithmeticException{
        Rational result = null;
        if(r != null){
            if(r.isZero()){
                throw new ArithmeticException("divided by zero");
            }
            if(!this.isInteger() || !r.isInteger()){
                throw new ArithmeticException("only integers can do mods");
            }
            result = new Rational((sign * num) % (r.sign * r.num));
        }
        return result;
    }

    public Rational factorial() throws ArithmeticException{
        if(!isInteger() || isNegative()){
            throw new ArithmeticException("only non-negative integers can do factorials");
        }
        int n = 1;
        for(int i = 2; i <= num; i++){
            n = n * i;
        }
        Rational r = new Rational(n);
        return r;
    }

    public Rational negative(){
        Rational r = new Rational(this);
        if(!r.isZero()){
            r.sign = -this.sign;
        }
        return r;
    }

    /**
     * Judge if the current {@code Rational} object equals zero
     * @return {@code true} if the rational equals zero, or {@code false} otherwise
     */
    public boolean isZero(){
        return num == 0;
    }

    public boolean isInteger(){
        return isZero() || den == 1;
    }

    public boolean isNegative(){
        return sign < 0;
    }

    /**
     * Compares this object to the specified object.  The result is
     * {@code true} if and only if the argument is not
     * {@code null} and is an {@code Integer} object that
     * contains the same {@code int} value as this object.
     *
     * @param   obj   the object to compare with.
     * @return  {@code true} if the objects are the same;
     *          {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Rational){
            Rational r = (Rational) obj;
            return this.sign == r.sign && this.num == r.num && this.den == r.den;
        }
        return false;
    }

    public String toString(){
        return (sign == -1 ? "-" : "") + num + (den == 1 ? "" : "/" + den);
    }

    /**
     * Evaluate this rational.
     * @return the value of this rational
     */
    public double value() {
        return (double)sign * num / den;
    }
}
