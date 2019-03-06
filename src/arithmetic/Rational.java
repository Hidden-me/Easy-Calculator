package arithmetic;

import org.jetbrains.annotations.Contract;

/**
 * This class provides a structure definition
 * and a series of calculating methods for rationals.
 */
public class Rational {
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
    public Rational substract(Rational r) {
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

    /**
     * Judge if the current {@code Rational} object equals zero
     * @return {@code true} if the rational equals zero, or {@code false} otherwise
     */
    public boolean isZero(){
        return num == 0;
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
    public boolean equals(Object obj){
        if(obj instanceof Rational){
            Rational r = (Rational) obj;
            return this.sign == r.sign && this.num == r.num && this.den == r.den;
        }
        return false;
    }
}
