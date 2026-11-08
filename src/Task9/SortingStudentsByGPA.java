package Task9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortingStudentsByGPA implements Comparator<SortingStudentsByGPA.Student> {

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

    // Comparator: по GPA в порядке УБЫВАНИЯ
    @Override
    public int compare(Student a, Student b) {
        return Double.compare(b.gpa, a.gpa);
    }

    // Quicksort по этому компаратору
    public void sort(List<Student> list) {
        if (list != null && list.size() > 1) {
            quickSort(list, 0, list.size() - 1);
        }
    }

    private void quickSort(List<Student> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private int partition(List<Student> list, int low, int high) {
        Student pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (compare(list.get(j), pivot) < 0) {
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

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 103, 4.5));
        students.add(new Student("Bob",   101, 3.8));
        students.add(new Student("Carol", 105, 4.9));
        students.add(new Student("Dave",  102, 3.2));
        students.add(new Student("Eve",   104, 4.1));

        SortingStudentsByGPA sorter = new SortingStudentsByGPA();
        sorter.sort(students);

        System.out.println("Отсортировано по GPA (убывание, quicksort):");
        for (Student s : students) System.out.println(s);
    }
}