package TimeZoneApp;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeUpdater extends Thread {
    private JLabel timeLabel; // Label to display the time
    private String timeZone = "UTC"; // Default timezone is UTC

    // Constructor that takes in the label we want to update with the time
    public TimeUpdater(JLabel timeLabel) {
        this.timeLabel = timeLabel;
    }

    // Method to update the timezone based on user selection
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone; // Set the timezone to the selected value
    }

    // This method runs in a loop, continuously updating the time display
    @Override
    public void run() {
        // Set up the date format we want to display, e.g., "2023-11-10 15:30:45"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Infinite loop to update the time every second
        while (true) {
            // Set the timezone in the formatter
            sdf.setTimeZone(TimeZone.getTimeZone(timeZone));

            // Get the current date and time formatted in the selected timezone
            String time = sdf.format(new Date());

            // Update the label with the formatted time string
            timeLabel.setText("Current time: " + time);

            try {
                // Pause for 1 second before updating again
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace(); // Print any interruption errors
            }
        }
    }
}
