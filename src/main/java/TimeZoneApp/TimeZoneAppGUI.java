package TimeZoneApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeZoneAppGUI {
    private JFrame frame; // Main window frame
    private JLabel timeLabel; // Label to display the current time
    private JComboBox<String> timezoneComboBox; // Dropdown to select timezone
    private TimeUpdater timeUpdater; // Thread to update time every second
    private JLabel yourCountryTime;
    private LocalTimeUpdater localTimeUpdater;

    public TimeZoneAppGUI() {
        // Set up the main frame (window) for the application
        frame = new JFrame("Timezone App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close app on window close
        frame.setSize(400, 800); // Set window size
        frame.setLayout(new BorderLayout()); // Use border layout to organize components

        selectionTimeZone();
        yourCountryTime();

        // Make the window visible
        frame.setVisible(true);
    }

    // Method to update the timezone in the time updater thread
    private void updateTimeZone() {
        String selectedTimeZone = (String) timezoneComboBox.getSelectedItem(); // Get selected timezone
        timeUpdater.setTimeZone(selectedTimeZone); // Set timezone in the updater thread
    }

    private void selectionTimeZone(){
        // Set up the time label to show selected timezone's current time
        timeLabel = new JLabel("Select a timezone", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set font for the label
        frame.add(timeLabel, BorderLayout.CENTER); // Add label to center of frame

        // Create a dropdown with all available timezones
        timezoneComboBox = new JComboBox<>(java.util.TimeZone.getAvailableIDs());

        // Add an action listener to handle timezone selection
        timezoneComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimeZone(); // Update timezone based on selection
            }
        });
        frame.add(timezoneComboBox, BorderLayout.NORTH); // Add dropdown at top of frame

        // Initialize the time updater thread and start it
        timeUpdater = new TimeUpdater(timeLabel);
        timeUpdater.start();
    }

    private void yourCountryTime(){
        yourCountryTime=new JLabel("", SwingConstants.CENTER);
        yourCountryTime.setFont(new Font("Arial",Font.PLAIN,15));
        frame.add(yourCountryTime, BorderLayout.SOUTH);

        localTimeUpdater=new LocalTimeUpdater(yourCountryTime);
        localTimeUpdater.start();
    }
}


//    private void yourCountryTime(){
//        String defaultTimeZone= TimeZone.getDefault().getID();
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        sdf.setTimeZone(TimeZone.getDefault());
//        String localTime=sdf.format(new Date());
//
//        yourCountryTime=new JLabel("Your country time is "+localTime+" ("+defaultTimeZone +")");
//        yourCountryTime.setFont(new Font("Arial",Font.PLAIN,15));
//        frame.add(yourCountryTime,BorderLayout.SOUTH);
//    }

