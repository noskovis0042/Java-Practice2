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

    // Сортировка слиянием (merge sort) по GPA (убывание)
    static List<Student> mergeSort(List<Student> list) {
        // База: список из 0 или 1 элемента уже отсортирован
        if (list.size() <= 1) return new ArrayList<>(list);

        int mid = list.size() / 2;
        List<Student> left  = mergeSort(list.subList(0, mid));
        List<Student> right = mergeSort(list.subList(mid, list.size()));
        return merge(left, right);
    }

    // Слияние двух ОТСОРТИРОВАННЫХ по GPA (убывание) списков
    static List<Student> merge(List<Student> a, List<Student> b) {
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

    // Полный сценарий: два списка → один отсортированный (сортировка слиянием)
    static List<Student> mergeAndSort(List<Student> a, List<Student> b) {
        List<Student> all = new ArrayList<>(a);
        all.addAll(b);
        return mergeSort(all);
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
