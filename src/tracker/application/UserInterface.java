package tracker.application;

import java.util.Scanner;

public class UserInterface {

    private final Scanner scanner = new Scanner(System.in);
    private final Classroom classroom = new Classroom(scanner);
    private final Notification notification = new Notification(classroom.getStudents());
    private final Statistics statistics = new Statistics(classroom.getStudents(), scanner);


    public void start() {
        System.out.print("Learning Progress Tracker\n");

        while (true) {
            switch (scanner.nextLine().toLowerCase().trim()) {
                case "" -> System.out.println("No input.");
                case "add points" -> classroom.addCoursePoints();
                case "add students" -> classroom.addStudents();
                case "back" -> System.out.println("Enter 'exit' to exit the program.");
                case "exit" -> {
                    System.out.println("Bye!");
                    return;
                }
                case "find" -> classroom.find();
                case "list" -> classroom.listStudentIds();
                case "notify" -> notification.notifyNewCompletions();
                case "statistics" -> statistics.getStatistics();
                default -> System.out.println("Unknown command!");
            }
        }
    }

}
