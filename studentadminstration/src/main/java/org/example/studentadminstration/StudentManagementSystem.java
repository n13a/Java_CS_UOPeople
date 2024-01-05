package org.example.studentadminstration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;

public class StudentManagementSystem extends Application {

    private ObservableList<Student> studentList = FXCollections.observableArrayList();
    private ObservableList<String> courseList = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Management System");

        // Create UI components
        TableView<Student> studentTable = createStudentTable();
        Button addStudentButton = new Button("Add Student");
        Button updateStudentButton = new Button("Update Student");
        Button addCourseButton = new Button("Add Course");
        Button displayCoursesButton = new Button("Display All Courses");

        // Set up event handlers
        addStudentButton.setOnAction(e -> showAddStudentDialog());
        updateStudentButton.setOnAction(e -> {
            Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
            if (selectedStudent != null) {
                showUpdateStudentDialog(selectedStudent);
            }
        });
        addCourseButton.setOnAction(e -> showAddCourseDialog());
        displayCoursesButton.setOnAction(e -> {
            // Create the alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("All Courses");
            alert.setHeaderText(null);

            // Get all the courses
            List<String> allCourses = getCourses();

            // Create the content text
            String contentText = allCourses.isEmpty() ? "No courses available." : String.join("\n", allCourses);

            // Set the content text
            alert.setContentText(contentText);

            // Show the alert
            alert.showAndWait();
        });

        // Create layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(studentTable, addStudentButton, updateStudentButton, addCourseButton,
                displayCoursesButton);

        // Set up the scene
        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    private TableView<Student> createStudentTable() {
        TableView<Student> table = new TableView<>();

        // Define columns
        TableColumn<Student, String> nameColumn = createStringTableColumn("Name", Student::nameProperty);
        TableColumn<Student, String> idColumn = createStringTableColumn("ID", Student::idProperty);
        TableColumn<Student, String> coursesColumn = new TableColumn<>("Courses");
        TableColumn<Student, String> gradesColumn = new TableColumn<>("Grades");

        gradesColumn.setCellValueFactory(cellData -> {
            Map<String, String> courseGrades = cellData.getValue().getCourseGrades();
            String gradesString = courseGrades.entrySet().stream()
                    .map(entry -> entry.getKey() + ": " + entry.getValue())
                    .collect(Collectors.joining(", "));
            return new SimpleStringProperty(gradesString);
        });
        table.getColumns().add(gradesColumn);

        // Set cell value factory for courses column
        coursesColumn.setCellValueFactory(cellData -> {
            List<String> courses = cellData.getValue().getCourses();
            String coursesString = String.join(", ", courses);
            return new SimpleStringProperty(coursesString);
        });

        // Add columns to the table
        table.getColumns().addAll(nameColumn, idColumn, coursesColumn);

        // Bind the table to the observable list
        table.setItems(studentList);

        return table;
    }

    private TableColumn<Student, String> createStringTableColumn(String columnHeader,
            Callback<Student, ObservableValue<String>> prop) {
        TableColumn<Student, String> column = new TableColumn<>(columnHeader);
        column.setCellValueFactory(cellData -> prop.call(cellData.getValue()));
        return column;
    }

    private void showAddStudentDialog() {
        // Create the custom dialog
        Dialog<Pair<String, Pair<String, List<String>>>> dialog = new Dialog<>();
        dialog.setTitle("Add Student");
        dialog.setHeaderText("Enter student information:");

        // Set the button types
        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        // Create the grid and add components to it
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nameField = new TextField();
        TextField idField = new TextField();

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("ID:"), 0, 1);
        grid.add(idField, 1, 1);

        ListView<String> courseListView = new ListView<>(getCourses());
        ComboBox<String> gradeComboBox = new ComboBox<>(FXCollections.observableArrayList("A", "B", "C", "D", "F"));
        Button addGradeButton = new Button("Add Grade");
        Map<String, String> courseGrades = new HashMap<>();
        addGradeButton.setOnAction(e -> {
            String selectedCourse = courseListView.getSelectionModel().getSelectedItem();
            String selectedGrade = gradeComboBox.getValue();
            if (selectedCourse != null && selectedGrade != null) {
                courseGrades.put(selectedCourse, selectedGrade);
            }
        });
        grid.add(new Label("Select Courses:"), 0, 2);
        grid.add(courseListView, 1, 2);
        grid.add(new Label("Grade:"), 0, 3);
        grid.add(gradeComboBox, 1, 3);
        grid.add(addGradeButton, 2, 3);

        // Enable/Disable add button depending on whether a name and id was entered
        Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
        addButton.setDisable(true);

        // Do some validation
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty() || idField.getText().trim().isEmpty());
        });
        idField.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty() || nameField.getText().trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new Pair<String, Pair<String, List<String>>>(nameField.getText(),
                        new Pair<>(idField.getText(), courseListView.getSelectionModel().getSelectedItems()));
            }
            return null;
        });

        // Show the dialog and process the result
        dialog.showAndWait().ifPresent(result -> {
            String name = result.getKey();
            Pair<String, List<String>> info = result.getValue();
            String id = info.getKey();
            List<String> selectedCourses = info.getValue();
            Student newStudent = new Student(name, id, selectedCourses);
            studentList.add(newStudent);
            newStudent.setCourseGrades(courseGrades);
        });
    }

    private void showUpdateStudentDialog(Student selectedStudent) {
        // Create the custom dialog
        Dialog<Pair<String, Pair<String, List<String>>>> dialog = new Dialog<>();
        dialog.setTitle("Update Student");
        dialog.setHeaderText("Update student information:");

        // Set the button types
        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

        // Create the grid and add components to it
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nameField = new TextField(selectedStudent.getName());
        TextField idField = new TextField(selectedStudent.getId());

        ListView<String> courseListView = new ListView<>(getCourses());
        ComboBox<String> gradeComboBox = new ComboBox<>(FXCollections.observableArrayList("A", "B", "C", "D", "F"));
        Button addGradeButton = new Button("Add Grade");
        Map<String, String> courseGrades = new HashMap<>(selectedStudent.getCourseGrades());
        addGradeButton.setOnAction(e -> {
            String selectedCourse = courseListView.getSelectionModel().getSelectedItem();
            String selectedGrade = gradeComboBox.getValue();
            if (selectedCourse != null && selectedGrade != null) {
                courseGrades.put(selectedCourse, selectedGrade);
            }
        });

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("ID:"), 0, 1);
        grid.add(idField, 1, 1);
        grid.add(new Label("Course:"), 0, 2);
        grid.add(courseListView, 1, 2);
        grid.add(new Label("Grade:"), 0, 3);
        grid.add(gradeComboBox, 1, 3);
        grid.add(addGradeButton, 2, 3);

        dialog.getDialogPane().setContent(grid);

        // Convert the result to a Pair<String, Pair<String, List<String>>> when the
        // update button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                return new Pair<>(nameField.getText(),
                        new Pair<>(idField.getText(), new ArrayList<>(courseGrades.values())));
            }
            return null;
        });

        // Process the result
        dialog.showAndWait().ifPresent(result -> {
            selectedStudent.setName(result.getKey());
            selectedStudent.setId(result.getValue().getKey());
            selectedStudent.setCourseGrades(courseGrades);
        });
    }

    private void showAddCourseDialog() {
        // Create the custom dialog
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Add Course");

        // Set the button types
        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        // Create the name and id fields
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField idField = new TextField();
        idField.setPromptText("ID");
        Course newCourse = new Course(nameField, idField);

        // Layout the dialog
        GridPane grid = new GridPane();
        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("ID:"), 0, 1);
        grid.add(idField, 1, 1);
        dialog.getDialogPane().setContent(grid);

        // Convert the result to a pair of name and id
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new Pair<>(nameField.getText(), idField.getText());
            }
            return null;
        });

        // Show the dialog and process the result
        dialog.showAndWait().ifPresent(result -> {
            String name = result.getKey();
            String id = result.getValue();
            // Add the new course to the list of courses
            getCourses().add(name + " (" + id + ")");
        });
    }

    // Student class
    private static class Student {
        private final StringProperty name;
        private final StringProperty id;
        private final ObservableList<String> courses;
        private Map<String, String> courseGrades = new HashMap<>();

        public Student(String name, String id, List<String> courses) {
            this.name = new SimpleStringProperty(name);
            this.id = new SimpleStringProperty(id);
            this.courses = FXCollections.observableArrayList(courses);
        }

        public StringProperty nameProperty() {
            return name;
        }

        public String getName() {
            return name.get();
        }

        public void setName(String newName) {
            name.set(newName);
        }

        public StringProperty idProperty() {
            return id;
        }

        public String getId() {
            return id.get();
        }

        public void setId(String newId) {
            id.set(newId);
        }

        public Map<String, String> getCourseGrades() {
            return courseGrades;
        }

        public void setCourseGrades(Map<String, String> courseGrades) {
            this.courseGrades = courseGrades;
        }

        public void setCourses(List<String> newCourses) {
            courses.setAll(newCourses);
        }

        public List<String> getCourses() {
            return new ArrayList<>(courses);
        }
    }

    private ObservableList<String> getCourses() {
        // Return an initially empty list of courses
        return courseList;
    }
}

class Course {
    private String courseID; // Course code
    private String courseName; // Course name

    // Constructor for Course class
    public Course(String courseCode, String courseName, int maxCapacity) {
        this.courseID = courseCode;
        this.courseName = courseName;
    }

    public Course(TextField nameField, TextField idField) {
    }

    // Getter methods for Course class
    public String getCourseCode() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    // Method to change the course code
    public void changeCourseCode(String newCourseCode) {
        this.courseID = newCourseCode;
    }

    // Method to change the course name
    public void changeCourseName(String newCourseName) {
        this.courseName = newCourseName;
    }

}
