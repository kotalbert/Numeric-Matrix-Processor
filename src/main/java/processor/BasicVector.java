package processor;

class BasicVector extends Vector {
    public BasicVector(int length) {
        super(length);
    }

    public BasicVector(int[] elements) {
        super(elements);
    }

    public static void main(String[] args) {

        Vector v1 = new BasicVector(10);
        System.out.println(v1);

        int[] elements = {1, 2, 3, 4, 5, 6};
        Vector v2 = new BasicVector(elements);
        System.out.println(v2);

        assert !v1.equals(v2);

        Vector v3 = new BasicVector(elements);
        assert v2.dotProduct(v3) == 41;




    }
}
