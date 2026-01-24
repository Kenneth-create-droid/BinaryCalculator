package com.ontariotechu.sofe3980U;

import java.time.LocalTime;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("The current local time is: " + LocalTime.now());

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first binary number: ");
        Binary binary1 = new Binary(sc.nextLine().trim());

        System.out.print("Enter second binary number: ");
        Binary binary2 = new Binary(sc.nextLine().trim());

        System.out.println("\nFirst  = " + binary1.getValue());
        System.out.println("Second = " + binary2.getValue());

        Binary sum = Binary.add(binary1, binary2);
        System.out.println("\nADD      = " + sum.getValue());

        Binary orRes = Binary.or(binary1, binary2);
        System.out.println("OR       = " + orRes.getValue());

        Binary andRes = Binary.and(binary1, binary2);
        System.out.println("AND      = " + andRes.getValue());

        Binary mulRes = Binary.multiply(binary1, binary2);
        System.out.println("MULTIPLY = " + mulRes.getValue());

        sc.close();
    }
}
