package processor;


/**
 * Abstract class for vector, 1d matrix;
 */
public abstract class Vector {
    public final int length;
    private final double[] elements;

    /**
     * Construct zero vector of given length.
     *
     * @param length
     */
    public Vector(int length) {
        this.length = length;
        this.elements = new double[length];

    }

    /**
     * Create vector from elements array.
     *
     * @param elements
     */
    public Vector(double[] elements) {
        this(elements.length);
        if (length >= 0) System.arraycopy(elements, 0, this.elements, 0, length);

    }

    public double getElement(int i) {
        return elements[i];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass())
            return false;

        Vector other = (Vector) obj;

        if (this.length != other.length)
            return false;

        for (int i = 0; i < this.length; i++) {
            if (this.getElement(i) != other.getElement(i))
                return false;
        }

    return true;

    }

    public double dotProduct(Vector other) throws ArithmeticException {
        if (this.length != other.length)
            throw new ArithmeticException("Attempting dot product for diferent vectors length.");
        double acumulator = 0;
        for (int i = 0; i < length; i++) {
           acumulator += this.getElement(i) * other.getElement(i);
        }
        return acumulator;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < length; i++) {
            if (i == 0) {
                sb
                        .append(elements[i]);
            } else {
                sb
                        .append(", ")
                        .append(elements[i]);
            }
        }
        sb.append("]");

        return sb.toString();
    }



}
