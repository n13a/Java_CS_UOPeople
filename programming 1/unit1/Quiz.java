import java.util.Scanner; // importing Scanner class

public class Quiz {
  // Initializing necessary variables
  private String[] questions;
  private String[][] options;
  private static int playerScore;
  private char[] correctAnswers;


  public void QuizQuestions() { // Method to initialize the quiz session with questions, options, and correct answers
    playerScore = 0; // Assign value 0 to player score
    questions = new String[] { // All the quiz questions in an array
        "What is the powerhouse of the cell?",
        "Which of the following is NOT a type of blood cell?",
        "What is the process by which green plants make their own food using sunlight?",
        "Which part of the human brain is responsible for coordinating voluntary muscle movements and maintaining balance?",
        "In genetics, what term describes the different forms of a gene that can occupy a particular locus?"
    };

    options = new String[][] { // Options for each question
        { "Mitochondrion", "Nucleus", "Endoplasmic reticulum", "Golgi apparatus" },
        { "Red blood cell", "White blood cell", "Platelet", "Plasma cell" },
        { "Respiration", "Photosynthesis", "Fermentation", "Transpiration" },
        { "Cerebrum", "Cerebellum", "Medulla oblongata", "Thalamus" },
        { "Chromosome", "Genotype", "Allele", "Phenotype" }
    };

    correctAnswers = new char[] { 'A', 'D', 'B', 'B', 'C' }; // Answers to each questions in order
  }

  public void QuizPlay() {
    try (Scanner scanner = new Scanner(System.in)) { // Scanner gives the capability to get user input
      for (int i = 0; i < questions.length; i++) {
        System.out.println(questions[i]); // Display question
        for (int j = 0; j < 4; j++) {
            System.out.print((char) ('A' + j) + ") " + options[i][j] + "\n");
        }
        String answer = scanner.next().toUpperCase(); // Get user input and store it in answer variable
        switch (answer) { // Switch statement that checks if the user entered valid character and the options is correct.
          case "A", "B", "C", "D" -> {
            if (answer.equals(String.valueOf(correctAnswers[i]))) { // Conditional that compares the correct answer with character entered by the user
              System.out.println("Correct!");
              playerScore++;
            } else {
              System.out.println("Wrong answer! The correct answer is " + correctAnswers[i]);
            }
          }
          default -> System.out.println("You need to enter A, B, C, or D.");
        }
        System.out.println("---".repeat(20)); // Accessory
      }
    } catch (Exception e) { // Handle exception
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Quiz quiz = new Quiz(); // Initialize Quiz method 
    quiz.QuizQuestions(); // Initialize questions
    quiz.QuizPlay();
    System.out.println("Final Score: " + ((double)playerScore / 5) * 100 + '%' + '\n'); // Show the result
  }
}
