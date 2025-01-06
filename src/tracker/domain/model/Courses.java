package tracker.domain.model;

import java.util.List;

public class Courses {
    private final List<Course> courses;

    public Courses() {
        this.courses = List.of(new Course("Java", 600),
                new Course("DSA", 400),
                new Course("Databases", 480),
                new Course("Spring", 550));
    }

    public List<Course> getCourses() {
        return this.courses;
    }

    public Course getJava() {
        return courses.get(0);
    }

    public Course getDsa() {
        return courses.get(1);
    }

    public Course getDatabases() {
        return courses.get(2);
    }

    public Course getSpring() {
        return courses.get(3);
    }

}
