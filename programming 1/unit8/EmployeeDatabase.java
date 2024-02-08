// Develop a program that reads the dataset and stores it in a collection.
// Write a function using the Function interface that takes an employee object as input and returns the employee's name and department as a concatenated string.
// Using streams, generate a new collection that contains the concatenated strings obtained from the previous step.
// Enhance your program to find the average salary of all employees using the stream's built-in functions.
// Generalize your program by incorporating a filter function that only includes employees whose age is above a certain threshold (e.g., 30 years).
// Summarize the program by explaining the purpose, characteristics, and usage of the Function interface in Java, highlighting how it represents a function that takes an input and produces an output.
// Compose a complete Java program that implements the given scenario, demonstrating your understanding of the Function interface, streams, and their practical applications in data manipulation.

package unit8;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.*;

// Class representing individual employees
class Employee {
  private String name;
  private int age;
  private String department;
  private double salary;

  public Employee(String name, int age, String department, double salary) {
    this.name = name;
    this.age = age;
    this.department = department;
    this.salary = salary;
  }

  // Getter methods for accessing private attributes

  public String getName() {
    return this.name;
  }

  public int getAge() {
    return this.age;
  }

  public String getDepartment() {
    return this.department;
  }

  public double getSalary() {
    return this.salary;
  }

}

public class EmployeeDatabase {

  // Method to create and return a list of Employee objects
  public static List<Employee> createDataBase() {
    List<Employee> employeesList = new ArrayList<>();
    employeesList.add(new Employee("Zach Mazillius", 36, "Services", 116962));
    employeesList.add(new Employee("Arlan Klessmann", 37, "Engineering", 150443));
    employeesList.add(new Employee("Luisa Linkie", 54, "Research and Development", 129544));
    employeesList.add(new Employee("Nadiya Nanson", 65, "Human Resources", 59170));
    employeesList.add(new Employee("Chrystal Schneidau", 24, "Engineering", 172988));
    employeesList.add(new Employee("Binnie Paddington", 56, "Support", 170744));
    employeesList.add(new Employee("Issiah Bloss", 53, "Marketing", 87750));
    employeesList.add(new Employee("Dominica Rollings", 35, "Engineering", 110114));
    employeesList.add(new Employee("Amandy Boriston", 52, "Sales", 188900));
    employeesList.add(new Employee("Hilton Gracewood", 23, "Research and Development", 179537));
    employeesList.add(new Employee("Emlynne Zemler", 22, "Legal", 99794));

    return employeesList;
  }

  // Method to create a Stream from the list of employees
  public static Stream<Employee> createStreamFromList() {
    return createDataBase().stream();
  }

  public static void main(String[] args) {

    // Create a list of Employee objects
    List<Employee> employeeList = createDataBase();

    System.out.println("Employee name and department: ");

    // Define a function to transform an Employee object into a concatenated string
    // of name and department
    Function<Employee, String> employeeToNameAndDepartment = e -> e.getName() + " - " + e.getDepartment();

    // Use streams to transform the employee list into a list of concatenated name
    // and department strings
    List<String> nameAndDepartmentList = employeeList.stream().map(employeeToNameAndDepartment)
        .collect(Collectors.toList());

    // Print the resulting list of concatenated name and department strings
    nameAndDepartmentList.forEach(System.out::println);

    // Calculate and print the average salary of all employees
    double averageSalary = employeeList.stream()
        .collect(Collectors.averagingDouble(Employee::getSalary));

    System.out.println("---".repeat(20));

    System.out.println("Average salary of all the employees: $" + String.format("%.2f", averageSalary));

    // Set an age threshold for filtering employees
    int ageThreshold = 30;

    // Use a filter to obtain a list of employees younger than the specified age
    // threshold
    List<Employee> lessThanAgeList = employeeList.stream()
        .filter(employee -> employee.getAge() < ageThreshold)
        .collect(Collectors.toList());

    System.out.println("---".repeat(20));

    System.out.println("Employees younger than " + ageThreshold + " :");
    lessThanAgeList.forEach(x -> System.out.println("Name: " + x.getName() + " | Age: " + x.getAge()));

    System.out.println("---".repeat(20));

    // Print the count of employees with a salary above 50k
    System.out.println("Number of employees with salary above 50k: ");
    long countEmployeesAbove50k = employeeList.stream()
        .filter(employee -> employee.getSalary() > 50000)
        .count();
    System.out.println(countEmployeesAbove50k);
  }
}