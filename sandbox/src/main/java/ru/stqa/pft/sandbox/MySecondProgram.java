package ru.stqa.pft.sandbox;

public class MySecondProgram {
    public static void main (String[] args){
        Point p1 = new Point(2.0, 4);
        Point p2 = new Point(3, 5);
        System.out.println("Расстояние между тчками p1 и p2 = " + distance(p1, p2));
    }

    public static double distance(Point p1, Point p2){
        return Math.sqrt((p2.x - p1.x)*2 + (p2.y - p1.y)*2);
    }

}

