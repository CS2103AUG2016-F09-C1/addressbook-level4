package seedu.tasklist.model.task;

import java.time.LocalDate;
import java.time.LocalTime;
import seedu.tasklist.commons.exceptions.IllegalValueException;

//@@author A0140019W
/**
 * Represents a Task's date and time in the task list.
 * Guarantees: details are present and not null, field values are validated.
 */
public class DateTime implements DateTimeComparator {
    public static final String MESSAGE_DATETIME_CONSTRAINTS = "Invalid Date Time format\nDate format: DDMMYYYY\n24-hour Time format: HHMM";
    
    private static final int DATE_LENGTH = 8;
    private static final int TIME_LENGTH = 4;
    private static final int VALID_DATE_TIME_LENGTH = 0;    // Valid Date Time length is either 0, 4 or 8
    
    private Date date;
    private Time time;

    public DateTime() throws IllegalValueException {
        this.date = new Date("");
        this.time = new Time("");
    };
    
    public DateTime(String arguments) throws IllegalValueException {
        this();
        String[] args = arguments.trim().split(" ");
        for (String datetime : args) {
            if (datetime.length() == TIME_LENGTH) {
                this.time = new Time(datetime);
            } else if (datetime.length() == DATE_LENGTH) {
                this.date = new Date(datetime);
            } else if (datetime.length() != VALID_DATE_TIME_LENGTH) {
                throw new IllegalValueException(MESSAGE_DATETIME_CONSTRAINTS);
            }
        }
    }
    
    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        String dateTime = date + " " + time;
        return dateTime.trim();
    }

    //@@author A0146840E
    /**
     * Check if this date time is after the compared DateTime.
     */
    @Override
    public boolean isDateTimeAfter(DateTime dateTime) {
        return isDateAfter(dateTime) || isDateEqualAndTimeAfter(dateTime) || isNoDateAndTimeAfter(dateTime);
    }

    /**
     * Check if this date is after the compared DateTime's date.
     */
    private boolean isDateAfter(DateTime dateTime) {
        return !isDateEmpty() && !dateTime.isDateEmpty() && getDate().getLocalDate().isAfter(dateTime.getDate().getLocalDate());
    }
    
    /**
     * Check if both dates are equal and the compared DateTime's time is after this object's time
     */
    private boolean isDateEqualAndTimeAfter(DateTime dateTime) {
        return !isDateEmpty() && !isTimeEmpty() && !dateTime.isDateEmpty() && !dateTime.isTimeEmpty()
                && getDate().getLocalDate().isEqual(dateTime.getDate().getLocalDate())
                && (getTime().getLocalTime().equals(dateTime.getTime().getLocalTime())
                || getTime().getLocalTime().isAfter(dateTime.getTime().getLocalTime()));
    }
    
    /**
     * Check if both dates are empty and the compared DateTime's time is after this object's time
     */
    private boolean isNoDateAndTimeAfter(DateTime dateTime) {
        return isDateEmpty() && !isTimeEmpty() 
                && dateTime.isDateEmpty() && !dateTime.isTimeEmpty()
                && getTime().getLocalTime().isAfter(dateTime.getTime().getLocalTime());
    }

    /**
     * Check if this date time is before current local date time.
     */
    @Override
    public boolean isDateTimeAfterCurrentDateTime() {                
        return isDateAfterCurrentDate() || isDateEqualAndTimeAfterCurrentTime() || isNoDateAndTimeAfterCurrentTime();
    }

    /**
     * Check if this date is after the current local date
     */
    private boolean isDateAfterCurrentDate() {
        return !isDateEmpty() && getDate().getLocalDate().isBefore(LocalDate.now());
    }
   
    /**
     * Check if this date is equal to current local date and this time is after the current local time
     */
    private boolean isDateEqualAndTimeAfterCurrentTime() {
        return !isDateEmpty() && !isTimeEmpty() && getDate().getLocalDate().isEqual(LocalDate.now()) 
                && getTime().getLocalTime().isBefore(LocalTime.now());
    }
    
    /**
     * Check if this date is empty and this time is not empty and this time is after the current local time
     */
    private boolean isNoDateAndTimeAfterCurrentTime() {
        return isDateEmpty() && !isTimeEmpty() && getTime().getLocalTime().isBefore(LocalTime.now());
    }

    /**
     * Check if date is equal to current local date.
     */
    @Override
    public boolean isDateEqualCurrentDate() {
        return !isDateEmpty() && getDate().getLocalDate().isEqual(LocalDate.now());
    }
    
    /**
     * Check if date is equal to one week from current local date.
     */
    @Override
    public boolean isDateEqualCurrentDateTillUpcomingWeek() {
        return !isDateEmpty() && getDate().getLocalDate().isAfter(LocalDate.now().minusDays(1))
                && getDate().getLocalDate().isBefore(LocalDate.now().plusWeeks(1));
    }
    
    /**
     * Check if date and time is empty.
     */
    @Override
    public boolean isDateTimeEmpty() {
        return getDate().getLocalDate() == null && getTime().getLocalTime() == null;
    }

    /**
     * Check if date is empty.
     */
    @Override
    public boolean isDateEmpty() {
        return getDate().getLocalDate() == null;
    }

    /**
     * Check if time is empty.
     */
    @Override
    public boolean isTimeEmpty() {
        return getTime().getLocalTime() == null;
    }

}
