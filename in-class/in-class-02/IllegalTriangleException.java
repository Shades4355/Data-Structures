
public class IllegalTriangleException extends Exception {
    // Constructors
    public IllegalTriangleException(double side1, double side2, double side3, String error) {
        super("Invalid triangle: " + error
                + "\nSide 1: " + Double.toString(side1)
                + "\nSide 2: " + Double.toString(side2)
                + "\nSide 3: " + Double.toString(side3));
    }
}
