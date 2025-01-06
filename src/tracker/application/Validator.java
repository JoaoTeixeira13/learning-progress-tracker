package tracker.application;

import tracker.domain.model.Student;

import java.util.List;
import java.util.regex.Pattern;

public class Validator {
    private static final int MINIMUM_REQUIRED_CREDENTIALS_AMOUNT = 3;

    public boolean areCredentialsValid(int credentialsAmount, String firstName, String lastName, String email, List<Student> students) {

        if (credentialsAmount < MINIMUM_REQUIRED_CREDENTIALS_AMOUNT) {
            System.out.println("Incorrect credentials");
            return false;
        }

        if (!isNameValid(firstName)) {
            System.out.println("Incorrect first name.");
            return false;
        }

        if (!isNameValid(lastName)) {
            System.out.println("Incorrect last name.");
            return false;
        }

        if (!isEmailValid(email)) {
            System.out.println("Incorrect email.");
            return false;
        }

        if (isEmailAlreadyUsed(email, students)) {
            System.out.println("This email is already taken.");
            return false;
        }
        return true;
    }

    public boolean arePointsValid(List<String> points) {
        if (points.size() != 4) {
            return false;
        }
        for (String point : points) {
            if (point == null || point.isEmpty()) {
                return false;
            }

            try {
                if (Integer.parseInt(point) < 0) {
                    return false;
                }

            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    private boolean isNameValid(String name) {
        if (name.length() < 2) {
            return false;
        }
        Pattern pattern = Pattern.compile("[a-zA-Z]+([ '\\-]*[a-zA-Z]*)*");
        Pattern patternNot = Pattern.compile("(\\w*''\\w*)|(\\w*-'\\w*)|(\\w*'-\\w*)|(\\w*--\\w*)|('\\w)|(\\w+')|(-\\w)|(\\w+-)");
        return !patternNot.matcher(name).matches() && pattern.matcher(name).matches();
    }

    private boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile("[\\w.]+@\\w+\\.\\w+");
        return pattern.matcher(email).matches();
    }

    private boolean isEmailAlreadyUsed(String email, List<Student> students) {
        List<String> existingEmails = students.stream().map(Student::getEmail).toList();
        return existingEmails.contains(email);
    }
}
