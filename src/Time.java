import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


public class Time {
    private boolean twelveHourFormat;
    private final static DateTimeFormatter MILITARYFORMATTER = DateTimeFormatter.ofPattern("   HH : mm : ss ");
    private final static DateTimeFormatter TWELVEHOURFORMATTER = DateTimeFormatter.ofPattern("hh : mm : ss");
    private final static SimpleDateFormat DATEFORMATTER = new SimpleDateFormat("MM / dd / yyyy");
    private final static String[] DAYSOFWEEK = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};


    Time() {
        twelveHourFormat = true;
    }

    //updates the hor field with the current time each second, if its midnight updates the date
    public void updateHourField(JTextField hourField, JTextField dateField) {
        while (true) {
            //for 12-hour format
            if (twelveHourFormat) {
                if (LocalTime.now().isAfter(LocalTime.NOON))
                    hourField.setText(TWELVEHOURFORMATTER.format(LocalTime.now()) + " PM");
                else
                    hourField.setText(TWELVEHOURFORMATTER.format(LocalTime.now()) + " AM");
            //for militay format
            } else
                hourField.setText(MILITARYFORMATTER.format(LocalTime.now()));
            //checks if its midnight
            if (LocalTime.now() == LocalTime.MIDNIGHT)
                updateDate(dateField);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                System.out.println("error in thread stop");
            }
        }
    }
    //Updates the dateField, gets the name of the day with the array DAYSOFWEEK
    public void updateDate(JTextField dateField) {
        dateField.setText(DAYSOFWEEK[Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1] + " " + DATEFORMATTER.format(Calendar.getInstance().getTime()));
    }



    //Setters and getters

    public void setTwelveHourFormat(boolean twelveHourFormat) {
        this.twelveHourFormat = twelveHourFormat;
    }

    public boolean getTwelveHourFormat() {
        return twelveHourFormat;
    }
}
