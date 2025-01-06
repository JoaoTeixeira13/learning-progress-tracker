package tracker.application;

import tracker.domain.model.Courses;
import tracker.domain.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Classroom {

    private static final String BACK = "back";

    private final List<Student> students;
    private final Validator validator = new Validator();
    private final Scanner scanner;

    public Classroom(Scanner scanner) {
        this.students = new ArrayList<>();
        this.scanner = scanner;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Integer getNumberOfStudents() {
        return students.size();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void listStudentIds() {
        List<String> studentIds = students.stream().map(Student::getId).toList();
        if (studentIds.isEmpty()) {
            System.out.println("No students found");
        } else {
            System.out.println("Students:");
            for (String id : studentIds) {
                System.out.println(id);
            }
        }
    }

    public Student getStudentById(String id) {

        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public void addStudents() {
        System.out.println("Enter student credentials or 'back' to return:");
        while (true) {
            String input = scanner.nextLine().trim();
            if (BACK.equals(input)) {
                System.out.printf("Total %d students have been added.\n%n", getNumberOfStudents());
                return;
            }

            Student newlyRegisteredStudent = registerStudent(input);
            if (newlyRegisteredStudent != null) {
                addStudent(newlyRegisteredStudent);
                System.out.println("The student has been added.");
            }
        }
    }

    private Student registerStudent(String registrationData) {

        String[] splitCredentials = registrationData.split(" ");
        String firstName = splitCredentials[0];
        StringBuilder builtLastName = new StringBuilder();
        for (int i = 1; i < splitCredentials.length - 1; i++) {
            if (i > 1) builtLastName.append(" ");
            builtLastName.append(splitCredentials[i]);
        }
        String email = splitCredentials[splitCredentials.length - 1];


        if (validator.areCredentialsValid(splitCredentials.length, firstName, builtLastName.toString(), email, getStudents())) {
            return new Student(firstName, builtLastName.toString(), email);
        } else {
            return null;
        }
    }

    public void addCoursePoints() {
        System.out.println("Enter an id and points or 'back' to return:");
        while (true) {
            String input = scanner.nextLine().trim();
            if (BACK.equals(input)) {
                return;
            }
            List<String> splitPointsInput = new ArrayList<>(Arrays.stream(input.split(" ")).toList());
            String id = splitPointsInput.getFirst();
            Student student = getStudentById(id);
            if (student == null) {
                System.out.printf("No student is found for id=%s.%n", id);
            } else {
                splitPointsInput.removeFirst();
                if (!validator.arePointsValid(splitPointsInput)) {
                    System.out.println("Incorrect points format.");
                } else {
                    updateStudentCoursePoints(splitPointsInput, student);
                }
            }
        }
    }

    private void updateStudentCoursePoints(List<String> splitPointsInput, Student student) {
        Integer javaPoints = Integer.valueOf(splitPointsInput.get(0));
        Integer dsaPoints = Integer.valueOf(splitPointsInput.get(1));
        Integer databasesPoints = Integer.valueOf(splitPointsInput.get(2));
        Integer springPoints = Integer.valueOf(splitPointsInput.get(3));

        Courses studentCourses = student.getCourses();
        studentCourses.getJava().addScore(javaPoints);
        studentCourses.getDsa().addScore(dsaPoints);
        studentCourses.getDatabases().addScore(databasesPoints);
        studentCourses.getSpring().addScore(springPoints);
        System.out.println("Points updated.");
    }

    public void find() {
        System.out.println("Enter an id or 'back' to return:");
        while (true) {
            String input = scanner.nextLine().trim();
            if (BACK.equals(input)) {
                return;
            }
            Student student = getStudentById(input);
            if (student == null) {
                System.out.printf("No student is found for id=%s.%n", input);
            } else {
                student.printCoursePoints();
            }
        }
    }

}
