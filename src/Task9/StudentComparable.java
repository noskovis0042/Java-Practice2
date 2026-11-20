package Task9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentComparable {

    // Свой Student — только для этого файла, со своей реализацией Comparable
    static class Student implements Comparable<Student> {
        String name;
        int iDNumber;
        double gpa;

        Student(String name, int iDNumber, double gpa) {
            this.name = name;
            this.iDNumber = iDNumber;
            this.gpa = gpa;
        }

        // Естественный порядок: по GPA по убыванию,
        // при равном GPA — по iDNumber по возрастанию
        @Override
        public int compareTo(Student other) {
            int cmp = Double.compare(other.gpa, this.gpa);
            if (cmp != 0) return cmp;
            return Integer.compare(this.iDNumber, other.iDNumber);
        }

        @Override
        public String toString() {
            return name + " (ID=" + iDNumber + ", GPA=" + gpa + ")";
        }
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 103, 4.5));
        students.add(new Student("Bob",   101, 3.8));
        students.add(new Student("Carol", 105, 4.9));
        students.add(new Student("Dave",  102, 3.2));
        students.add(new Student("Eve",   104, 4.5));

        System.out.println("До сортировки:");
        for (Student s : students) System.out.println(s);

        // Collections.sort использует compareTo
        Collections.sort(students);

        System.out.println("\nПосле сортировки через compareTo (GPA убывание, затем ID):");
        for (Student s : students) System.out.println(s);

        System.out.println("\nAlice.compareTo(Carol) = " + students.get(1).compareTo(students.get(0)));
    }
}
