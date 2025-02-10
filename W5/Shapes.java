//package W5;

import java.lang.Math;

// Abstract class Shape
abstract class Shape {
    protected String color;
    Shape(String color){
        this.color = color;
    }

    abstract double getArea();

    void displayColor(){
        System.out.println("Shape color: " + this.color);
    }
}

// Subclass Circle
class Circle extends Shape {
    private double radius;
    // Constructor
    public Circle(String color, double radius) {
        super(color);
        if (radius >= 0) {
            this.radius = radius;
        } else {
            this.radius = 0;
        }
    }

    @Override
    double getArea(){
        return Math.PI * this.radius * this.radius;
    }
}

// Subclass Rectangle
class Rectangle extends Shape {
    private double width, height;
    // Constructor
    public Rectangle(String color, double width, double height) {
        super(color);
        if (width >= 0) {
            this.width = width;
        } else {
            this.width = 0;
        }
        if (height >= 0) {
            this.height = height;
        } else {
            this.height = 0;
        }
    }

    @Override
    double getArea(){
        return this.width * this.height;
    }
}
