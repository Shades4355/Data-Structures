// Triangular Prism HAS A Triangle

public class TriangularPrism {
    // Variables
    double length, width, height;

    // Constructors
    TriangularPrism(Triangle edges, Rectangle sides) {
        this.length = sides.getSide1();
        this.width = sides.getSide2();
        this.height = edges.getSide2() / (edges.getSide3() / 2);
    }

    // Accessors & Mutators
    // Length
    public double getLength() { return this.length; }
    public void setLength(double length) { this.length = length; }
    // width
    public double getWidth() { return this.width; }
    public void setWidth(double width) { this.width = width; }
    // Height
    public double getHeight() { return this.height; }
    public void setHeight(double height) { this.height = height; }

    // Area
    // Perimeter
}
