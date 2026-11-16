package Task10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSortedLists {

    // Свой Student — только для этого файла
    static class Student {
        String name;
        String surname;
        String specialty;
        int course;
        String group;
        double gpa;

        Student(String name, String surname, String specialty,
                int course, String group, double gpa) {
            this.name      = name;
            this.surname   = surname;
            this.specialty = specialty;
            this.course    = course;
            this.group     = group;
            this.gpa       = gpa;
        }

        @Override
        public String toString() {
            return String.format(
                    "Student{name='%s', surname='%s', specialty='%s', course=%d, group='%s', gpa=%.2f}",
                    name, surname, specialty, course, group, gpa);
        }
    }

    // GPA по убыванию
    static final Comparator<Student> BY_GPA_DESC =
            (a, b) -> Double.compare(b.gpa, a.gpa);

    // Слияние двух ОТСОРТИРОВАННЫХ списков
    static List<Student> merge(List<Student> a, List<Student> b, Comparator<Student> cmp) {
        List<Student> result = new ArrayList<>(a.size() + b.size());
        int i = 0, j = 0;
        while (i < a.size() && j < b.size()) {
            if (cmp.compare(a.get(i), b.get(j)) <= 0) {
                result.add(a.get(i++));
            } else {
                result.add(b.get(j++));
            }
        }
        while (i < a.size()) result.add(a.get(i++));
        while (j < b.size()) result.add(b.get(j++));
        return result;
    }

    public static void main(String[] args) {
        // Список 1 — уже отсортирован по GPA (по убыванию)
        List<Student> list1 = new ArrayList<>(List.of(
                new Student("Charlie", "Brown",   "Physics",          3, "PH-31",   4.9),
                new Student("Alice",   "Smith",   "Computer Science", 2, "CS-21",   4.5),
                new Student("Eve",     "Davis",   "Mathematics",      1, "MATH-11", 4.1)
        ));

        // Список 2 — уже отсортирован по GPA (по убыванию)
        List<Student> list2 = new ArrayList<>(List.of(
                new Student("Kate",  "Taylor",  "Biology",     2, "BIO-21",  4.7),
                new Student("Frank", "Miller",  "Physics",     3, "PH-31",   3.5),
                new Student("Bob",   "Johnson", "Mathematics", 1, "MATH-11", 3.8)
        ));

        // merge требует ОТСОРТИРОВАННЫХ входов
        list1.sort(BY_GPA_DESC);
        list2.sort(BY_GPA_DESC);

        System.out.println("=== Список 1 (отсортирован по GPA, убывание) ===");
        list1.forEach(System.out::println);

        System.out.println("\n=== Список 2 (отсортирован по GPA, убывание) ===");
        list2.forEach(System.out::println);

        List<Student> merged = merge(list1, list2, BY_GPA_DESC);

        System.out.println("\n=== Объединённый отсортированный список ===");
        merged.forEach(System.out::println);
    }
}
