package Task8;

import java.util.Scanner;

public class Task15 {
    static void printDigitsReversed(int n) {
        // База: одна цифра
        if (n < 10) {
            System.out.print(n);
            return;
        }
        // Печатаем последнюю цифру, затем рекурсивно — остальные
        System.out.print((n % 10));
        printDigitsReversed(n / 10);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        printDigitsReversed(n);
    }
}