import java.util.Scanner;

public class Task17 {

    static Scanner sc = new Scanner(System.in);
    // это не глобальная переменная в смысле хранения данных задачи,
    // а лишь источник ввода. Но чтобы быть строже —
    // Строгий вариант: создаём Scanner внутри функции.
    // Однако при рекурсии каждый вызов создавал бы новый Scanner —
    // это работает, но некрасиво. Лучше передавать Scanner... но параметры запрещены.

    static int maxSeq() {
        Scanner sc = new Scanner(System.in); // ⚠ проблема: каждый вызов — новый Scanner
        int x = sc.nextInt();
        if (x == 0) return 0;
        int rest = maxSeq();
        return Math.max(x, rest);
    }

    public static void main(String[] args) {
        System.out.println(maxSeq());
    }
}