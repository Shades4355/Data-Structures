
public class Circle {
    // Variables
    double radius;

    // Constructors
    Circle(double radius) {
        this.radius = radius;
    }

    // Accessors & Mutators 
    // Radius & Diameter
    public double getDiameter() { return 2 * this.radius; }
    public double getRadius() { return this.radius; }
    public void setRadius(double radius) { this.radius = radius; }
    // Area
    // Perimeter
}
