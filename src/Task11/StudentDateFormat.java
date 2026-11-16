package Task11;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StudentDateFormat {

    // Свой Student — только для этого файла
    static class Student implements Comparable<Student> {
        private String name;
        private String surname;
        private String specialty;
        private int    course;
        private String group;
        private double gpa;
        private Date   birthDate;

        Student(String name, String surname, String specialty,
                int course, String group, double gpa, Date birthDate) {
            this.name      = name;
            this.surname   = surname;
            this.specialty = specialty;
            this.course    = course;
            this.group     = group;
            this.gpa       = gpa;
            this.birthDate = birthDate;
        }

        public Date getBirthDate()       { return birthDate; }
        public void setBirthDate(Date d) { this.birthDate = d; }

        // Дата рождения по шаблону SimpleDateFormat, например "dd.MM.yyyy"
        public String formatBirthDate(String format) {
            if (birthDate == null) return "—";
            return new SimpleDateFormat(format, Locale.getDefault()).format(birthDate);
        }

        // «Короткий / средний / полный» форматы через java.text.DateFormat
        public String formatBirthDate(DateFormatStyle style) {
            if (birthDate == null) return "—";
            DateFormat df;
            switch (style) {
                case SHORT:  df = DateFormat.getDateInstance(DateFormat.SHORT);  break;
                case MEDIUM: df = DateFormat.getDateInstance(DateFormat.MEDIUM); break;
                case FULL:   df = DateFormat.getDateInstance(DateFormat.FULL);   break;
                default:     df = DateFormat.getDateInstance();
            }
            return df.format(birthDate);
        }

        public enum DateFormatStyle { SHORT, MEDIUM, FULL }

        @Override
        public int compareTo(Student other) {
            return Double.compare(this.gpa, other.gpa);
        }

        @Override
        public String toString() {
            return String.format(
                    "Student{name='%s', surname='%s', specialty='%s', course=%d, group='%s', gpa=%.2f, birthDate=%s}",
                    name, surname, specialty, course, group, gpa,
                    birthDate == null ? "—" : new SimpleDateFormat("dd.MM.yyyy").format(birthDate));
        }
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat input = new SimpleDateFormat("dd.MM.yyyy");
        Student s = new Student(
                "Alice", "Smith", "Computer Science", 2, "CS-21", 4.5,
                input.parse("15.03.2004"));

        System.out.println("toString():");
        System.out.println(s);

        System.out.println("\nДата рождения в разных форматах:");
        System.out.println("dd.MM.yyyy        → " + s.formatBirthDate("dd.MM.yyyy"));
        System.out.println("yyyy-MM-dd        → " + s.formatBirthDate("yyyy-MM-dd"));
        System.out.println("d MMMM yyyy (RU)  → " + s.formatBirthDate("d MMMM yyyy"));
        System.out.println("EEEE, d MMM yyyy  → " + s.formatBirthDate("EEEE, d MMM yyyy"));

        System.out.println("\nСтандартные стили (DateFormat):");
        System.out.println("SHORT  → " + s.formatBirthDate(Student.DateFormatStyle.SHORT));
        System.out.println("MEDIUM → " + s.formatBirthDate(Student.DateFormatStyle.MEDIUM));
        System.out.println("FULL   → " + s.formatBirthDate(Student.DateFormatStyle.FULL));
    }
}
