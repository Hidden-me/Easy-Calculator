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
    // use this method to do reduction of this rational object
    private void doReduction(){
        int a = num, b = den, c = a % b;
        while(c != 0){
            a = b;
            b = c;
            c = a % b;
        }
        num = a;
        den = b;
    }
    // make the object a standard rational
    private void standardize(){
        processSign();
        doReduction();
    }

    /**
     * Create a Rational object, whose value will equal to
     * <code>num / den</code>.
     * <br>The rational will be automatically converted into a standard form.
     * <br>Equal rationals have the same standard form,
     * while unequal rationals have different ones.
     * @param num the numerator
     * @param den the denominator
     * @throws IllegalArgumentException if den == 0
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
     * Create a Rational object, whose value will equal to an integer
     * <code>num</code>.
     * @param num the integer
     */
    public Rational(int num) {
        this.num = num;
        this.den = 1;
        processSign();
    }

    /**
     * Get the sum of two rationals.
     * @param r another rational object
     * @return a Rational object representing <code>"this + r"</code>,
     * or a <code>null</code> if r is <code>null</code>
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
     * @param r another rational object
     * @return a Rational object representing <code>"this - r"</code>,
     * or a <code>null</code> if r is <code>null</code>
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
     * @param r another rational object
     * @return a Rational object representing <code>"this * r"</code>,
     * or a <code>null</code> if r is <code>null</code>
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
     * @param r another rational object
     * @return a Rational object representing <code>"this / r"</code>,
     * or a <code>null</code> if r is <code>null</code>
     * @throws ArithmeticException if r equals zero
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
     * Judge if the current Rational object equals zero
     * @return true if the rational equals zero, or false otherwise
     */
    public boolean isZero(){
        return num == 0;
    }
}
