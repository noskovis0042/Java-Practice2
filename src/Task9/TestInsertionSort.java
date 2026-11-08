package Task9;

public class TestInsertionSort {

    // Свой Student — только для этого файла
    static class Student {
        String name;
        int iDNumber;
        double gpa;

        Student(String name, int iDNumber, double gpa) {
            this.name = name;
            this.iDNumber = iDNumber;
            this.gpa = gpa;
        }

        @Override
        public String toString() {
            return name + " (ID=" + iDNumber + ", GPA=" + gpa + ")";
        }
    }

    // Сортировка вставками по iDNumber (возрастание)
    static void insertionSort(Student[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Student key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].iDNumber > key.iDNumber) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Student[] students = {
                new Student("Alice", 103, 4.5),
                new Student("Bob",   101, 3.8),
                new Student("Carol", 105, 4.9),
                new Student("Dave",  102, 3.2),
                new Student("Eve",   104, 4.1)
        };

        System.out.println("До сортировки:");
        for (Student s : students) System.out.println(s);

        insertionSort(students);

        System.out.println("\nПосле сортировки по iDNumber (вставками):");
        for (Student s : students) System.out.println(s);
    }
}