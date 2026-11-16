package Task10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingStudentsByGPA {

    // Свой Student — только для этого файла
    static class Student implements Comparable<Student> {
        private String name;        // Имя
        private String surname;     // Фамилия
        private String specialty;   // Специальность
        private int    course;      // Курс
        private String group;       // Группа
        private double gpa;         // Средний балл

        Student(String name, String surname, String specialty,
                int course, String group, double gpa) {
            this.name      = name;
            this.surname   = surname;
            this.specialty = specialty;
            this.course    = course;
            this.group     = group;
            this.gpa       = gpa;
        }

        public String getName()      { return name; }
        public String getSurname()   { return surname; }
        public String getSpecialty() { return specialty; }
        public int    getCourse()    { return course; }
        public String getGroup()     { return group; }
        public double getGpa()       { return gpa; }

        public void setName(String name)           { this.name = name; }
        public void setSurname(String surname)     { this.surname = surname; }
        public void setSpecialty(String specialty) { this.specialty = specialty; }
        public void setCourse(int course)          { this.course = course; }
        public void setGroup(String group)         { this.group = group; }
        public void setGpa(double gpa)             { this.gpa = gpa; }

        // Естественный порядок — по GPA по возрастанию
        @Override
        public int compareTo(Student other) {
            return Double.compare(this.gpa, other.gpa);
        }

        @Override
        public String toString() {
            return String.format(
                    "Student{name='%s', surname='%s', specialty='%s', course=%d, group='%s', gpa=%.2f}",
                    name, surname, specialty, course, group, gpa);
        }
    }

    // Поле — список студентов (по заданию называется iDNumber)
    private List<Student> iDNumber = new ArrayList<>();

    // Заполнение
    public void setArray() {
        iDNumber.add(new Student("Alice",   "Smith",   "Computer Science", 2, "CS-21",   4.5));
        iDNumber.add(new Student("Bob",     "Johnson", "Mathematics",      1, "MATH-11", 3.8));
        iDNumber.add(new Student("Charlie", "Brown",   "Physics",          3, "PH-31",   4.9));
        iDNumber.add(new Student("Dave",    "Wilson",  "Computer Science", 2, "CS-21",   3.2));
        iDNumber.add(new Student("Eve",     "Davis",   "Mathematics",      1, "MATH-11", 4.1));
        iDNumber.add(new Student("Frank",   "Miller",  "Physics",          3, "PH-31",   3.5));
    }

    // Компараторы
    // Основной: по GPA по УБЫВАНИЮ
    public static final Comparator<Student> BY_GPA_DESC =
            (a, b) -> Double.compare(b.getGpa(), a.getGpa());

    // По другому полю — по фамилии (при равенстве — по имени)
    public static final Comparator<Student> BY_SURNAME =
            Comparator.comparing(Student::getSurname)
                    .thenComparing(Student::getName);

    // Быстрая сортировка (quicksort)
    public void quicksort() {
        quicksort(BY_GPA_DESC);
    }

    public void quicksort(Comparator<Student> cmp) {
        quicksort(iDNumber, 0, iDNumber.size() - 1, cmp);
    }

    private void quicksort(List<Student> list, int low, int high, Comparator<Student> cmp) {
        if (low < high) {
            int pivotIndex = partition(list, low, high, cmp);
            quicksort(list, low, pivotIndex - 1, cmp);
            quicksort(list, pivotIndex + 1, high, cmp);
        }
    }

    // Схема Ломуто: pivot — последний элемент
    private int partition(List<Student> list, int low, int high, Comparator<Student> cmp) {
        Student pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (cmp.compare(list.get(j), pivot) <= 0) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, high);
        return i + 1;
    }

    // Сортировка слиянием (merge sort)
    public void mergeSort() {
        mergeSort(BY_GPA_DESC);
    }

    public void mergeSort(Comparator<Student> cmp) {
        List<Student> sorted = mergeSort(iDNumber, cmp);
        iDNumber.clear();
        iDNumber.addAll(sorted);
    }

    private List<Student> mergeSort(List<Student> list, Comparator<Student> cmp) {
        if (list.size() <= 1) return new ArrayList<>(list);
        int mid = list.size() / 2;
        List<Student> left  = mergeSort(list.subList(0, mid), cmp);
        List<Student> right = mergeSort(list.subList(mid, list.size()), cmp);
        return merge(left, right, cmp);
    }

    // Слияние двух ОТСОРТИРОВАННЫХ списков
    public static List<Student> merge(List<Student> a, List<Student> b, Comparator<Student> cmp) {
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

    // Вывод
    public void outArray() {
        for (Student s : iDNumber) System.out.println(s);
    }

    // Сортировка по другому полю (по фамилии)
    public void sortBySurname() {
        quicksort(BY_SURNAME);
    }

    public List<Student> getIDNumber()          { return iDNumber; }
    public void setIDNumber(List<Student> list) { this.iDNumber = list; }

    public static void main(String[] args) {
        SortingStudentsByGPA sorter = new SortingStudentsByGPA();
        sorter.setArray();

        System.out.println("=== Исходный массив ===");
        sorter.outArray();

        System.out.println("\n=== quicksort: по GPA по убыванию ===");
        sorter.quicksort();
        sorter.outArray();

        System.out.println("\n=== mergeSort: по GPA по убыванию ===");
        sorter.mergeSort();
        sorter.outArray();

        System.out.println("\n=== Сортировка по другому полю: по фамилии ===");
        sorter.sortBySurname();
        sorter.outArray();
    }
}
