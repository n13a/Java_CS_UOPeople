// Develop a Student Record Management System in Java for a university. The system should enable administrators to effectively manage student records, including adding new students, updating student information, and viewing student details.

// Requirements:

// Student Class:

// Create a Student class with private instance variables for storing student information such as name, ID, age, and grade.

// Student Management Class:

// Create a StudentManagement class with private static variables to store a list of students and the total number of students.

// Administrator Interface:

// Display a menu with options to add a new student, update student information, and view student details.

// Prompt the administrator for necessary inputs and perform the requested operations using the StudentManagement class.

// Error Handling:

// Implement error handling to handle cases where the student ID is not found or invalid inputs are provided.

// Documentation:

// Provide comprehensive documentation

// Include instructions for running the program and interacting with the administrator interface.

// Remember to use appropriate variable names and follow coding best practices.

// Submit the assignment in MS Word or PDF file. Your submission should contain the program code, explanation, and output screenshot.

// This assignment will be assessed by your instructor using the rubric below.

import java.util.Scanner;
import java.util.ArrayList;

// Object representation of student
class StudentRecord {
  private String name;
  private int age;
  private String ID;
  private double GPA;
  private String field;

  // StudentRecord constructor
  public StudentRecord(String name, int age, double GPA, String field, int totalStudents) {
    this.name = name;
    this.age = age;
    this.GPA = GPA;
    this.field = field;
    this.ID = generateStudentId(name, totalStudents);
  }

  // method for displaying the info of student
  public String displayStudentInfo() {
    return "Student ID: " + ID + "\nName: " + name + "\nAge: " + age + "\nGPA: " + GPA + "\nField: " + field;

  }

  // getters
  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public Double getGPA() {
    return GPA;
  }

  public String getField() {
    return field;
  }

  public String getID() {
    return ID;
  }

  // setters
  public void setName(String name) {
    this.name = name;
  }

  public void setField(String field) {
    this.field = field;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setGPA(double GPA) {
    this.GPA = GPA;
  }

  // A method that gets name and current total number of students and generates a
  // unique ID
  private String generateStudentId(String name, int totalStudents) {
    return name.replaceAll("\\s", "") + (int) (totalStudents + 1);
  }
}

// class for managing the student records
class RecordManagement {
  // List of all the students
  private static ArrayList<StudentRecord> studentList = new ArrayList<>();
  // Total number of students
  public static int numOfStudents = 0;

  // Scanner class for getting the values entered by the user
  Scanner scanner = new Scanner(System.in);

  // Method for adding students
  public void addStudent(Scanner scanner) {
    System.out.print("Enter student name: ");
    String name = scanner.nextLine();
    int age;
    do { // A do while that ensures that the entered age is positive
      System.out.print("Enter student age: ");
      age = scanner.nextInt();
      scanner.nextLine();
      if (age < 0) {
        System.out.println("The age must be positive integer. Try again.");
      }

    } while (age <= 0);
    System.out.print("Enter student GPA: ");
    double GPA = scanner.nextDouble();
    scanner.nextLine();

    System.out.print("Enter student's field of study: ");
    String field = scanner.nextLine();

    // Instantiating new student object
    StudentRecord newStudent = new StudentRecord(name, age, GPA, field, numOfStudents);
    // adding new student to the list
    studentList.add(newStudent);
    numOfStudents++;
    System.out.println("New student record successfully added. \n");
  }

  // method for updating the student info.
  public void updateStudentInfo(Scanner scanner) {
    System.out.print("Enter student's ID: ");
    String studentId = scanner.nextLine();

    try {
      // looping over all the students to find the student with the entered ID
      for (StudentRecord student : studentList) {
        if (student.getID().equals(studentId)) {
          int newAge;
          do {
            System.out.print("Enter new age: ");
            newAge = scanner.nextInt();
            scanner.nextLine();
            if (newAge < 0) {
              System.out.println("The age must be a positive integer. Try again.");
            }
          } while (newAge <= 0);

          student.setAge(newAge);

          System.out.print("Enter new GPA: ");
          double newGPA = scanner.nextDouble();
          scanner.nextLine();
          student.setGPA(newGPA);

          System.out.print("Enter new field of study: ");
          String newField = scanner.nextLine();
          student.setField(newField);

          System.out.println("Information updated.");
          return;
        }
      }

      System.out.println("Student ID not found.");
    } catch (Exception e) {
      System.out.println("Invalid input. Please enter a valid value.");
      scanner.nextLine();
    }
  }

  // Displaying info of indidual students based on entered ID
  public void displayIndividualStudentInfo(Scanner scanner) {
    System.out.print("Enter student ID: ");
    String studentId = scanner.nextLine();

    boolean studentFound = false;
    for (StudentRecord student : studentList) {
      if (student.getID().equals(studentId)) {
        studentFound = true;
        System.out.println(student.displayStudentInfo());
      }
    }

    if (!studentFound) {
      System.out.println("The entered ID does not exists.");
    }

    System.out.println("---".repeat(20));
  }

  // Method that lists all the students
  public void displayAllStudents() {
    System.out.println("List of all student records: ");
    System.out.println("---".repeat(20));
    for (StudentRecord student : studentList) {
      System.out.println(student.displayStudentInfo());
      System.out.println("---".repeat(20));
    }
    System.out.println("There are " + numOfStudents + " students.\n");
  }
}

// the main class
public class StudentRecordManagementSystem {
  public static void main(String[] args) {
    RecordManagement recordManagement = new RecordManagement();
    Scanner scanner = new Scanner(System.in);
    boolean stateOfProgram = true;
    while (stateOfProgram) {
      System.out.println(
          "What operation do you want to do? \n 1. Add new student\n 2. Update student record \n 3. View student detail\n 4. Display all the students \n 5. Quit\n");
      try {
        int choice = scanner.nextInt();
        scanner.nextLine();

        // Switch statement that executes certain methods based on entered value by the
        // user
        switch (choice) {
          case 1 -> recordManagement.addStudent(scanner);
          case 2 -> recordManagement.updateStudentInfo(scanner);
          case 3 -> recordManagement.displayIndividualStudentInfo(scanner);
          case 4 -> recordManagement.displayAllStudents();
          case 5 -> stateOfProgram = false;
          default -> {
            System.out.println("Wrong operation. Try again.");
          }
        }
      } catch (Exception e) {
        System.out.println("Invalid input. Please enter a number.");
        scanner.nextLine();
      }
    }
    System.out.println("SYSTEM SHUTTING DOWN...");
  }
}
