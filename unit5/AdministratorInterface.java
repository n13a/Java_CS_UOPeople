package unit5;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Student class to represent a student
class Student {
  private String name; // Student's name
  private int id; // Student's ID
  private List<Course> coursesEnrolled; // List of courses the student is enrolled in
  private Map<Course, Integer> grades; // Map of courses and corresponding grades

  // Constructor for Student class
  public Student(String name, int id) {
    this.name = name;
    this.id = id;
    this.coursesEnrolled = new ArrayList<>();
    this.grades = new HashMap<>();
  }

  // Getter methods for Student class
  public String getName() {
    return name;
  }

  public int getStudentID() {
    return id;
  }

  public List<Course> getEnrolledCourses() {
    return coursesEnrolled;
  }

  // Setter methods
  public void changeName(String newName) {
    this.name = newName;
  }

  // Method to change the student's ID
  public void changeID(int newID) {
    this.id = newID;
  }

  // Method to enroll a student in a course
  public void enrollInCourse(Course course) {
    coursesEnrolled.add(course);
  }

  // Method to assign a grade to a student for a course
  public void assignGrade(Course course, int grade) {
    grades.put(course, grade);
  }

  // Method to get the grades of a student
  public Map<Course, Integer> getGrades() {
    return grades;
  }
}

// Course class to represent a course
class Course {
  private String courseCode; // Course code
  private String courseName; // Course name
  private int maxCapacity; // Maximum capacity of the course
  private int enrolledStudents = 0; // Total number of enrolled students across all courses

  // Constructor for Course class
  public Course(String courseCode, String courseName, int maxCapacity) {
    this.courseCode = courseCode;
    this.courseName = courseName;
    this.maxCapacity = maxCapacity;
  }

  // Getter methods for Course class
  public String getCourseCode() {
    return courseCode;
  }

  public String getCourseName() {
    return courseName;
  }

  public int getMaxCapacity() {
    return maxCapacity;
  }

  // Method to change the course code
  public void changeCourseCode(String newCourseCode) {
    this.courseCode = newCourseCode;
  }

  // Method to change the course name
  public void changeCourseName(String newCourseName) {
    this.courseName = newCourseName;
  }

  // Method to change the maximum capacity of the course
  public void changeMaxCapacity(int newMaxCapacity) {
    this.maxCapacity = newMaxCapacity;
  }

  // Method to increment the total number of enrolled students
  public void incrementEnrolledStudents() {
    enrolledStudents++;
  }

  public int getEnrolledStudents() {
    return enrolledStudents;
  }
}

// CourseManagement class to manage courses and students
class CourseManagement {
  private static List<Course> courses = new ArrayList<>(); // List of all courses
  private static List<Student> students = new ArrayList<>(); // List of all students

  // Method to add a course
  public static void addCourse(String courseCode, String courseName, int maxCapacity) {
    Course course = new Course(courseCode, courseName, maxCapacity);
    courses.add(course);
  }

  // Method to get all courses
  public static List<Course> getCourses() {
    return courses;
  }

  // Method to enroll a student in a course
  public static void enrollStudent(Student student, Course course) {
    if (course.getMaxCapacity() > course.getEnrolledStudents()) {
      student.enrollInCourse(course);
      course.incrementEnrolledStudents();
      students.add(student); // Add the student to the static variable students
      System.out.println(student.getName() + " enrolled in " + course.getCourseName());
    } else {
      System.out.println("Course has reached its maximum capacity.");
    }
  }

  // Method to assign a grade to a student for a course
  public static void assignGrade(Student student, Course course, int grade) {
    if (student.getEnrolledCourses().contains(course)) {
      student.assignGrade(course, grade);
    } else {
      System.out.println("Student is not enrolled in the specified course.");
    }
  }

  // Method to calculate the overall grade for a student
  public static void calculateOverallGrade(Student student) {
    Map<Course, Integer> studentGrades = student.getGrades();
    int totalCredits = 0;
    int weightedSum = 0;

    for (int grade : studentGrades.values()) {
      int credits = 3;
      totalCredits += credits;
      weightedSum += grade * credits;
    }

    if (totalCredits > 0) {
      double overallGrade = (double) weightedSum / totalCredits;
      System.out
          .println("Overall Grade for " + student.getName() + " (id: " + student.getStudentID() + "): " + overallGrade);
    } else {
      System.out.println("Student has not been assigned any grades.");
    }
  }

  // Method to display all courses
  public static void displayAllCourses() {
    System.out.println("Present Courses:");
    for (Course course : courses) {
      System.out.println(course.getCourseCode() + ": " + course.getCourseName());
    }
  }

  public static List<Student> getStudents() {
    return students;
  }

  // Method to find a student by their ID
  public static Student findStudentByID(int studentID) {
    for (Student student : students) {
      if (student.getStudentID() == studentID) {
        return student;
      }
    }
    return null;
  }
}

// AdministratorInterface class to interact with the user
public class AdministratorInterface {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in); // Create a Scanner object for reading user input

    // Infinite loop to keep the program running until the user decides to exit by
    // entering 0.
    while (true) {
      // Display the menu of commands
      System.out.println("\n Administrator commands:");
      System.out.println("1. Add a new course");
      System.out.println("2. Enroll students");
      System.out.println("3. Assign grades");
      System.out.println("4. Calculate overall course grades");
      System.out.println("5. Display all courses");
      System.out.println("6. Display all students");
      System.out.println("7. Edit student information");
      System.out.println("0. Exit");

      System.out.print("Enter your choice: ");
      int choice = scanner.nextInt(); // Read the user's choice

      // Switch case to handle user's choice
      switch (choice) {
        case 1:
          addNewCourse(scanner);
          break;
        case 2:
          enrollStudents(scanner);
          break;
        case 3:
          assignGrades(scanner);
          break;
        case 4:
          calculateOverallGrades(scanner);
          break;
        case 5:
          CourseManagement.displayAllCourses();
          break;
        case 6:
          AdministratorInterface.displayAllStudents();
          break;
        case 7:
          AdministratorInterface.editStudentInformation(scanner);
          break;
        case 0:
          System.out.println("Exiting program. Goodbye!");
          System.exit(0); // Exit the program
        default:
          System.out.println("Invalid choice. Please try again."); // Handle invalid choices
      }
    }
  }

  // Method to add a new course
  private static void addNewCourse(Scanner scanner) {
    System.out.print("Enter course code: ");
    String courseCode = scanner.next();
    System.out.print("Enter course name: ");
    scanner.nextLine();
    String courseName = scanner.nextLine();
    System.out.print("Enter maximum capacity: ");
    int maxCapacity = scanner.nextInt();

    CourseManagement.addCourse(courseCode, courseName, maxCapacity);
    System.out.println("Course added.");
  }

  // Method to enroll students
  private static void enrollStudents(Scanner scanner) {
    System.out.print("Enter student name: ");
    scanner.nextLine();
    String studentName = scanner.nextLine();
    System.out.print("Enter student ID: ");
    int id = scanner.nextInt();

    Student student = new Student(studentName, id);

    System.out.println("Available Courses:");
    for (Course course : CourseManagement.getCourses()) {
      System.out.println(course.getCourseCode() + ": " + course.getCourseName());
    }

    System.out.print("Enter course code to enroll student: ");
    String courseCode = scanner.next();
    Course course = findCourseByCode(courseCode);

    if (course != null) {
      CourseManagement.enrollStudent(student, course);
    } else {
      System.out.println("Course with code " + courseCode + " not found.");
    }
  }

  // Method to edit student information
  private static void editStudentInformation(Scanner scanner) {
    List<Student> students = CourseManagement.getStudents();
    if (students.isEmpty()) {
      System.out.println("Warning: There are no enrolled students.");
      return;
    }
    System.out.println("Present Students:");
    for (Student student : CourseManagement.getStudents()) {
      System.out.println("Name: " + student.getName() + ", ID: " + student.getStudentID());
    }

    System.out.print("Enter the ID of the student you want to edit: ");
    int id = scanner.nextInt();
    Student student = CourseManagement.findStudentByID(id);

    if (student != null) {
      System.out.println("Editing information for " + student.getName() + " (id: " + student.getStudentID() + ")");
      System.out.print("Enter new name (or press Enter to keep the current name): ");
      scanner.nextLine();
      String newName = scanner.nextLine();
      if (!newName.isEmpty()) {
        student.changeName(newName);
      }

      System.out.print("Enter new ID (or press Enter to keep the current ID): ");
      String newID = scanner.nextLine();
      if (!newID.isEmpty()) {
        student.changeID(Integer.parseInt(newID));
      }

      System.out.println("Student information updated.");
    } else {
      System.out.println("Student not found.");
    }
  }

  // Method to assign grades
  private static void assignGrades(Scanner scanner) {
    System.out.print("Enter student ID: ");
    int id = scanner.nextInt();
    System.out.print("Enter course code: ");
    String courseCode = scanner.next();
    System.out.print("Enter grade: ");
    int grade = scanner.nextInt();

    Student student = CourseManagement.findStudentByID(id);
    Course course = findCourseByCode(courseCode);

    if (student != null && course != null) {
      CourseManagement.assignGrade(student, course, grade);
      System.out.println("Grade assigned.");
    } else {
      System.out.println("Student or course not found.");
    }
  }

  // Method to calculate overall grades
  private static void calculateOverallGrades(Scanner scanner) {
    System.out.print("Enter student ID: ");
    int id = scanner.nextInt();

    Student student = CourseManagement.findStudentByID(id);

    if (student != null) {
      CourseManagement.calculateOverallGrade(student);
    } else {
      System.out.println("Student not found.");
    }
  }

  // Method to find a course by its code
  private static Course findCourseByCode(String courseCode) {
    for (Course course : CourseManagement.getCourses()) {
      if (course.getCourseCode().equals(courseCode)) {
        return course;
      }
    }
    return null;
  }

  private static void displayAllStudents() {
    System.out.println("Present Students:");
    for (Student student : CourseManagement.getStudents()) {
      System.out.println("Name: " + student.getName() + ", ID: " + student.getStudentID());
      System.out.println("Enrolled Courses:");
      for (Course course : student.getEnrolledCourses()) {
        System.out.println(course.getCourseCode() + ": " + course.getCourseName());
      }
      System.out.println("Grades:");
      for (Map.Entry<Course, Integer> entry : student.getGrades().entrySet()) {
        System.out.println(entry.getKey().getCourseCode() + ": " + entry.getValue());
      }
      System.out.println();
    }
  }
}
