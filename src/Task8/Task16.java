package Task8;
import java.util.Scanner;

public class Task16 {
    // Функция без параметров, читает с клавиатуры,
    // возвращает [максимум, количество элементов, равных максимуму]
    static int[] solve() {
        Scanner sc = new Scanner(System.in);
        int max = 0;
        int count = 0;

        while (true) {
            int x = sc.nextInt();
            if (x == 0) break;

            if (x > max) {
                max = x;
                count = 1;
            } else if (x == max) {
                count++;
            }
        }
        return new int[]{max, count};
    }

    public static void main(String[] args) {
        int[] result = solve();
        System.out.println(result[1]); // количество элементов, равных максимуму
    }
}