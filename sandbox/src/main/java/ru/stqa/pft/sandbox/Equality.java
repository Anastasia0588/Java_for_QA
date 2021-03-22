package ru.stqa.pft.sandbox;

public class Equality {

    public static void main(String[] args){
    String a1 = "firefox";
    String a2 = new String(a1);

    System.out.println(a1 == a2);
    System.out.println(a1.equals(a2));
    }
}
