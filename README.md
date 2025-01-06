# Learning Progress Tracker

Welcome to the Learning Progress Tracker! This Java backend application helps to track and manage the learning progress of students enrolled in various courses (Java, DSA, Databases and Spring).

## Features

- **Interactive CLI:** The application has a command-line interface for users to enter commands and receive responses.
- **Student Management:** Allows adding students with valid credentials and lists all registered students.
- **Learning Progress Tracking:** Tracks courses and points for each student. Provides commands to update and view student progress.
- **Statistics:** Provides comprehensive statistics on course popularity, activity, and difficulty.
- **Notifications:** Notifies students upon course completion with personalized messages.

## Commands

### General Commands

- `exit`: Terminates the program.
- `add students`: Initiates the process to add student credentials.
- `list`: Lists all registered students by their unique IDs.
- `find`: Allows searching for a student by their ID and displays their progress.
- `add courses`: Updates the learning progress for a student by their ID.
- `statistics`: Provides detailed statistics about courses and enables viewing top learners in each course.
- `notify`: Generates notifications for students who have completed courses.

### Adding Students

When the `add students` command is used, the application prompts:

```Enter student credentials or 'back' to return:```

Enter the credentials in the format:


`FirstName LastName EmailAddress
`

The application checks and validates each part of the credentials. Use `back` to finish adding students and see the total number of added students.

### Listing Students
Use the `list` command to display all registered students in the format:

```
Students:
10000  
10001  
```

### Adding Points
When the `add points` command is used, the application prompts:


```
Enter an id and points or 'back' to return:
```
Enter the progress in the format:


```
studentId JavaPoints DSAPoints DatabasesPoints SpringPoints
```
Points must be non-negative integers. Use `back` to return to the main command mode.

### Finding a Student
Use the `find` command to display a student's progress. The application prompts:

```
Enter an id or 'back' to return:
```

Enter the student's ID to see their course details.

### Statistics
Use the `statistics` command to display information about courses. The application provides the following course statistics:

- Most popular
- Least popular
- Highest activity
- Lowest activity
- Easiest course
- Hardest course

The application prompts:

```
Type the name of a course to see details or 'back' to quit:
```
Enter the name of a course to view detailed information on enrolled students and their progress.

### Notifications
Use the `notify` command to send personalized notifications to students who have completed courses:

```
To: johnd@email.net
Re: Your Learning Progress
Hello, John Doe! You have accomplished our Java course!

To: johnd@email.net
Re: Your Learning Progress
Hello, John Doe! You have accomplished our DSA course!

Total 1 students have been notified.
```
Example Usage

```
> add students
Enter student credentials or 'back' to return:
> John Doe johnd@email.net
The student has been added.
> Jane Spark jspark@yahoo.com
The student has been added.
> back
Total 2 students have been added.
> list
Students:
10000
10001
> add courses
Enter an id and courses or 'back' to return:
> 10000 600 400 0 0
Points updated.
> back
> notify
To: johnd@email.net
Re: Your Learning Progress
Hello, John Doe! You have accomplished our Java course!
To: johnd@email.net
Re: Your Learning Progress
Hello, John Doe! You have accomplished our DSA course!
Total 1 students have been notified.
> exit
Bye!
```
### Conclusion

The Learning Progress Tracker is a robust tool to manage students' progress across various courses. With its interactive CLI, real-time progress tracking, and personalized notifications, it ensures an efficient learning management system. Use the above commands to navigate and utilize all its features effectively. Happy tracking!

