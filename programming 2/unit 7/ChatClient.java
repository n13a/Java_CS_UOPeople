import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
  // Assigning address to be localhost
  private static String ADDRESS = "localhost";
  private static int PORT = 8080;

  public static void main(String[] args) {
    try (Socket socket = new Socket(ADDRESS, PORT)) { // Connecting to socket

      BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

      @SuppressWarnings("resource")
      Scanner scanner = new Scanner(System.in);

      // Starting a thread that listens for messages from server
      Thread serverListener = new Thread(() -> {
        try {
          String message;
          while ((message = serverReader.readLine()) != null) {
            System.out.println(message);
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
      serverListener.start();

      String userInput;
      while (true) {
        userInput = scanner.nextLine();
        writer.println(userInput);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
