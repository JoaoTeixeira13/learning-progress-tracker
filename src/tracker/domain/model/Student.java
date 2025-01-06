package tracker.domain.model;

import java.util.UUID;

public class Student {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Courses courses;

    public Student(String firstName, String lastName, String email) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.courses = new Courses();
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Courses getCourses() {
        return courses;
    }

    public void printCoursePoints() {
        System.out.printf("%s points: Java=%d; DSA=%d; Databases=%d; Spring=%d%n",
                id,
                courses.getJava().getScore(),
                courses.getDsa().getScore(),
                courses.getDatabases().getScore(),
                courses.getSpring().getScore()
        );
    }

    public void printCompletionNotification(String course) {
        System.out.printf("To: %s%n", email);
        System.out.println("Re: Your Learning Progress");
        System.out.printf("Hello, %s %s! You have accomplished our %s course!%n", firstName, lastName, course);
    }

}
