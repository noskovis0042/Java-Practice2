package Task8;

import java.util.Scanner;

public class Task1 {
    // Треугольная последовательность: 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, ...
    // k       — текущее число
    // printed — сколько раз k уже выведено (включая текущий вызов)
    // n       — сколько членов ещё осталось вывести
    static void triangle(int n, int k, int printed) {
        // База: все n членов выведены
        if (n == 0) return;

        System.out.print(k + " ");

        if (printed == k) {
            // k выведено ровно k раз — переходим к следующему числу
            triangle(n - 1, k + 1, 1);
        } else {
            // продолжаем выводить то же k
            triangle(n - 1, k, printed + 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        triangle(n, 1, 1);
    }
}