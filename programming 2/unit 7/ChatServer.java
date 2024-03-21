import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {
  private static final int PORT = 8080;
  private static Set<PrintWriter> clientWriters = new HashSet<>();
  private static int userID = 0;

  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(PORT)) {
      System.out.println("Server is running in port " + PORT);
      while (true) {
        new ClientHandler(serverSocket.accept()).start(); // Accept incoming connections and start a new thread for each
                                                          // client
      }
    } catch (IOException e) {
      e.printStackTrace(); // Print any exceptions that occur
    }
  }

  private static class ClientHandler extends Thread {
    private Socket socket;
    private PrintWriter writer;
    private int userId;

    public ClientHandler(Socket socket) {
      this.socket = socket;
      this.userId = userID++; // Assign a unique user ID to each client and increment it by 1
    }

    public void run() {
      try {
        // Setting up I/O streams for the client socket
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
        clientWriters.add(writer);

        System.out.println("User " + userId + " connected.");

        String message;
        // Continuously reading messages from the client and broadcasting them to all
        // clients
        while ((message = reader.readLine()) != null) {
          System.out.println("User " + userID + " Sent a message");
          broadcastMessage("User " + userId + ": " + message);
        }
      } catch (IOException e) {
        System.out.println("User " + userId + " disconnected.");
      } finally {
        // This blocks runs at the end and removes the client's writer from the set of
        // client writers
        if (writer != null) {
          clientWriters.remove(writer);
        }
        try {
          socket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    private void broadcastMessage(String message) {
      // this method displays the messsage sent by a user to all the connected users
      for (PrintWriter writer : clientWriters) {
        writer.println(message);
      }
    }
  }
}
