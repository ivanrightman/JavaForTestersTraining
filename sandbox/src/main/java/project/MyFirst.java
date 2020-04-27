package project;

public class MyFirst {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Point p1 = new Point(10, 5);
        Point p2 = new Point(5, 10);
        System.out.println("Расстояние между p1 и p2 = " + p1.distance(p2));
    }
}