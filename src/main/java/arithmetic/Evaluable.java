package arithmetic;

/**
 * An interface for evaluable objects.
 */
public interface Evaluable {
    /**
     * Evaluate this expression.
     * <br />This method should be implemented by subclasses.
     * @return the value of this expression
     * @throws ArithmeticException if this expression is invalid
     */
    public abstract double evaluate() throws ArithmeticException;
}
