import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class TextAnalysisTool {
  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      // Prompt the user to enter a text
      System.out.println("Enter text:");

      // Convert the entered text to lower case
      String userEnteredText = scanner.nextLine().toLowerCase();

      String lowerCaseText = userEnteredText.toLowerCase();

      String[] words = userEnteredText.split(" ");

      System.out.printf("Character count: %d\n", userEnteredText.length());
      System.out.printf("Word count: %d\n", words.length);

      // Using map to track the frequency of each character
      Map<Character, Integer> charMap = new HashMap<Character, Integer>();
      for (int i = 0; i < userEnteredText.length(); i++) {
        char currentChar = lowerCaseText.charAt(i);
        if (!charMap.containsKey(currentChar)) {
          charMap.put(currentChar, 0);
        }
        charMap.replace(currentChar, charMap.get(currentChar) + 1);

      }

      char mostCommonChar = ' ';
      int maxFreq = 0;

      // Loop over the map entry
      for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
        if (entry.getValue() > maxFreq) {
          mostCommonChar = entry.getKey();
          maxFreq = entry.getValue();
        }
      }

      // Because space is not visible on the terminal I decided to change the output
      // to the word (Space)
      if (mostCommonChar == ' ') {
        System.out.printf("Most common character: (Space)\n");
      } else {
        System.out.printf("Most common character: %c\n", mostCommonChar);
      }

      System.out.println("Enter a character to find its frequency in the text: ");

      String userInputChar = scanner.nextLine().toLowerCase();

      if (userInputChar.length() != 1) {
        System.out.println("You need to enter a single character.");
        return;
      }

      char enteredChar = userInputChar.charAt(0);

      System.err.printf("The \"%s\" character appeared %d times in the text\n", enteredChar,
          charMap.get(enteredChar));

      Map<String, Integer> wordMap = new HashMap<>();
      for (int i = 0; i < words.length; i++) {
        String currentWord = words[i];

        if (!wordMap.containsKey(currentWord)) {
          wordMap.put(currentWord, 0);
        }
        wordMap.replace(currentWord, wordMap.get(currentWord) + 1);
      }

      System.out.println("Type a word to search for the number of occurrences in the text:");
      String userEnterdWord = scanner.nextLine().toLowerCase();

      if (!wordMap.containsKey(userEnterdWord)) {
        System.out.println("The entered word does not exist in the text.");
      } else {
        System.out.printf("The word \"%s\" appeared in the text %d times.\n", userEnterdWord,
            wordMap.get(userEnterdWord));
      }

      System.out.printf("There is %d unique words in the text", wordMap.size());
    }
  }

}
