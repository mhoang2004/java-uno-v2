import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void testTimer() {
        Timer timer = new Timer(1000, new ActionListener() {
            int count = -1;

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Timer tick: " + count);
                count++;

                if (count == 5) {
                    ((Timer) e.getSource()).stop();
                    System.out.println("Timer stopped.");
                }
            }
        });

        timer.start();
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setVisible(true);

        Thread timerThread = new Thread(() -> {
            testTimer();
        });
        timerThread.start();

        // Wait for the timer thread to complete
        while (timerThread.isAlive()) {
            try {
                Thread.sleep(100); // Poll every 100 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("After Timer");
    }
}
