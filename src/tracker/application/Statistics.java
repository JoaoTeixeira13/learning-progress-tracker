package tracker.application;

import tracker.domain.model.Course;
import tracker.domain.model.Courses;
import tracker.domain.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Statistics {

    private static final String JAVA = "Java";
    private static final String DSA = "DSA";
    private static final String DATABASES = "Databases";
    private static final String SPRING = "Spring";

    private final List<Student> students;
    private final Scanner scanner;

    public Statistics(List<Student> students, Scanner scanner) {
        this.students = students;
        this.scanner = scanner;
    }

    public void getStatistics() {

        Map<String, Integer> enrollment = new HashMap<>();
        Map<String, Integer> activity = new HashMap<>();
        Map<String, List<Integer>> scoresMap = new HashMap<>();

        for (Student student : students) {
            Courses courses = student.getCourses();

            processCourse(enrollment, activity, scoresMap, "Java", courses.getJava());
            processCourse(enrollment, activity, scoresMap, "DSA", courses.getDsa());
            processCourse(enrollment, activity, scoresMap, "Databases", courses.getDatabases());
            processCourse(enrollment, activity, scoresMap, "Spring", courses.getSpring());
        }

        Map<String, Double> averageScores = new HashMap<>();
        for (Map.Entry<String, List<Integer>> entry : scoresMap.entrySet()) {
            String course = entry.getKey();
            List<Integer> scores = entry.getValue();
            double averageScore = scores.stream().mapToInt(Integer::intValue).average().orElse(0);
            averageScores.put(course, averageScore);
        }

        System.out.println("Type the name of a course to see details or 'back' to quit:");
        processAndPrintCategory("Most popular", enrollment, true);
        processAndPrintCategory("Least popular", enrollment, false);
        processAndPrintCategory("Highest activity", activity, true);
        processAndPrintCategory("Lowest activity", activity, false);
        processAndPrintCategory("Easiest course", averageScores, true);
        processAndPrintCategory("Hardest course", averageScores, false);

        while (true) {
            switch (scanner.nextLine().toLowerCase().trim()) {
                case "back" -> {
                    return;
                }
                case "java" -> getCourseStatistics("Java");
                case "dsa" -> getCourseStatistics("DSA");
                case "databases" -> getCourseStatistics("Databases");
                case "spring" -> getCourseStatistics("Spring");
                default -> System.out.println("Unknown course.");
            }
        }
    }

    private void processCourse(Map<String, Integer> enrollment, Map<String, Integer> activity,
                               Map<String, List<Integer>> scoresMap, String courseName, Course course) {
        if (course.getScore() > 0) {
            enrollment.put(courseName, enrollment.getOrDefault(courseName, 0) + 1);
            activity.put(courseName, activity.getOrDefault(courseName, 0) + course.getSubmissionCount());
            scoresMap.computeIfAbsent(courseName, k -> new ArrayList<>()).add(course.getScore());
        }
    }

    private void processAndPrintCategory(String categoryName, Map<String, ? extends Number> map, boolean isMax) {
        List<String> courses = getCoursesWithMinOrMaxValue(map, isMax);
        boolean isInvalid = courses.isEmpty() || (courses.size() == 4 && !isMax);
        String result = isInvalid ? "n/a" : String.join(", ", courses);
        System.out.printf("%s: %s%n", categoryName, result);
    }

    private List<String> getCoursesWithMinOrMaxValue(Map<String, ? extends Number> map, boolean isMax) {
        if (map.isEmpty()) {
            return List.of();
        }
        double edgeValue = isMax
                ? map.values().stream().mapToDouble(Number::doubleValue).max().orElse(Double.MIN_VALUE)
                : map.values().stream().mapToDouble(Number::doubleValue).min().orElse(Double.MAX_VALUE);
        return map.entrySet().stream()
                .filter(entry -> entry.getValue().doubleValue() == edgeValue)
                .map(Map.Entry::getKey)
                .toList();
    }

    private void getCourseStatistics(String courseName) {
        System.out.println(courseName);
        System.out.println("id    points    completed");

        List<Student> studentsCopy = getOrderedStudentsCopy(courseName);

        for (Student student : studentsCopy) {

            Integer courseScore = switch (courseName) {
                case JAVA -> student.getCourses().getJava().getScore();
                case DSA -> student.getCourses().getDsa().getScore();
                case DATABASES -> student.getCourses().getDatabases().getScore();
                case SPRING -> student.getCourses().getSpring().getScore();
                default -> throw new IllegalStateException("Unexpected value: " + courseName);
            };

            Double completionRate = switch (courseName) {
                case JAVA -> student.getCourses().getJava().getCompletionRate();
                case DSA -> student.getCourses().getDsa().getCompletionRate();
                case DATABASES -> student.getCourses().getDatabases().getCompletionRate();
                case SPRING -> student.getCourses().getSpring().getCompletionRate();
                default -> throw new IllegalStateException("Unexpected value: " + courseName);
            };

            if (courseScore != 0) {
                System.out.printf("%s %s        %s%%%n", student.getId(), courseScore, completionRate);
            }
        }
    }

    private List<Student> getOrderedStudentsCopy(String courseName) {
        List<Student> studentsCopy = new ArrayList<>(students);
        studentsCopy.sort((s1, s2) -> {
            int compareScores = switch (courseName) {
                case JAVA -> s2.getCourses().getJava().getScore().compareTo(s1.getCourses().getJava().getScore());
                case DSA -> s2.getCourses().getDsa().getScore().compareTo(s1.getCourses().getDsa().getScore());
                case DATABASES -> s2.getCourses().getDatabases().getScore().compareTo(s1.getCourses().getDatabases().getScore());
                case SPRING -> s2.getCourses().getSpring().getScore().compareTo(s1.getCourses().getSpring().getScore());
                default -> throw new IllegalStateException("Unexpected value: " + courseName);
            };
            if (compareScores == 0) {
                return s1.getId().compareTo(s2.getId());
            }
            return compareScores;
        });
        return studentsCopy;
    }
}
