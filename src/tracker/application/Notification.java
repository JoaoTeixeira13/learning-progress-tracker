package tracker.application;

import tracker.domain.model.Course;
import tracker.domain.model.Student;

import java.util.List;

public class Notification {

    private final List<Student> students;

    public Notification(List<Student> students) {
        this.students = students;
    }

    public void notifyNewCompletions() {

        int notifiedStudents = 0;

        for (Student student : students) {
            boolean isStudentNotified = false;

            for (Course course : student.getCourses().getCourses()) {

                if (course.getCompletionRate() >= 100 && !course.isNotified()) {
                    student.printCompletionNotification(course.getName());
                    course.markAsNotified();
                    isStudentNotified = true;
                }
            }
            if (isStudentNotified) {
                notifiedStudents++;
            }
        }
        System.out.printf("Total %d students have been notified.%n", notifiedStudents);

    }


}
