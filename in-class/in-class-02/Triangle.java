
public class Triangle {
    // variables
    protected double side1, side2, side3;

    // Constructors
    Triangle(double side1, double side2, double side3) {
        setSides(side1, side2, side3);
    }

    // Accessors and Mutators
    public void setSides(double side1, double side2, double side3) {
        // check if sides are valid
        if (side1 <= 0 || side2 <= 0 || side3 <= 0) {
            throw new IllegalArgumentException("All sides must be positive numbers.");
        } else if (side1 + side2 <= side3 || side2 + side3 <= side1 || side3 + side1 <= side2) {
            throw new IllegalTriangleException(side1, side2, side3,
                    "Sum of any two sides is not greater than the third side.");
        } else {
            this.side1 = side1;
            this.side2 = side2;
            this.side3 = side3;
        }
    }
}