import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; // A class that helps in formatting dates based on a user specified pattern

public class Clock implements Runnable {
    private DateTimeFormatter fmter = DateTimeFormatter.ofPattern(
            "HH:mm:ss dd-MM-yyyy");// Getting the current time via LocaleDateTime class
                                   // and formatting it with predefined format

    @Override
    public void run() {
        System.out.print("The time is: \n");
        while (true) {
            System.out.print("\r" + fmter.format(LocalDateTime.now()));
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        Thread clockThread = new Thread(new Clock());
        clockThread.setPriority(10);
        clockThread.start();
    }
}
