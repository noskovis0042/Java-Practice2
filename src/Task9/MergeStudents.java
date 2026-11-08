package Task9;

import java.util.ArrayList;
import java.util.List;

public class MergeStudents {

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

    // Quicksort по GPA (убывание) — чтобы списки были отсортированы перед слиянием
    static void quickSortByGpa(List<Student> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSortByGpa(list, low, pi - 1);
            quickSortByGpa(list, pi + 1, high);
        }
    }

    private static int partition(List<Student> list, int low, int high) {
        Student pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            // по убыванию: больший GPA — левее
            if (list.get(j).gpa > pivot.gpa) {
                i++;
                Student tmp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, tmp);
            }
        }
        Student tmp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, tmp);
        return i + 1;
    }

    static void sortByGpa(List<Student> list) {
        if (list != null && list.size() > 1) {
            quickSortByGpa(list, 0, list.size() - 1);
        }
    }

    // Слияние двух ОТСОРТИРОВАННЫХ по GPA (убывание) списков
    static List<Student> mergeByGPA(List<Student> a, List<Student> b) {
        List<Student> result = new ArrayList<>(a.size() + b.size());
        int i = 0, j = 0;
        while (i < a.size() && j < b.size()) {
            if (a.get(i).gpa >= b.get(j).gpa) {
                result.add(a.get(i++));
            } else {
                result.add(b.get(j++));
            }
        }
        while (i < a.size()) result.add(a.get(i++));
        while (j < b.size()) result.add(b.get(j++));
        return result;
    }

    // Полный сценарий: два произвольных списка → один отсортированный
    static List<Student> mergeAndSort(List<Student> a, List<Student> b) {
        List<Student> aCopy = new ArrayList<>(a);
        List<Student> bCopy = new ArrayList<>(b);
        sortByGpa(aCopy);
        sortByGpa(bCopy);
        return mergeByGPA(aCopy, bCopy);
    }

    public static void main(String[] args) {
        List<Student> list1 = new ArrayList<>();
        list1.add(new Student("Alice", 103, 4.5));
        list1.add(new Student("Bob",   101, 3.8));

        List<Student> list2 = new ArrayList<>();
        list2.add(new Student("Carol", 105, 4.9));
        list2.add(new Student("Dave",  102, 3.2));
        list2.add(new Student("Eve",   104, 4.1));

        List<Student> merged = mergeAndSort(list1, list2);

        System.out.println("Объединённый отсортированный список (по GPA, убывание):");
        for (Student s : merged) System.out.println(s);
    }
}