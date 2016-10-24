package seedu.tasklist.model.task;

import java.time.LocalDate;
import java.time.LocalTime;
import seedu.tasklist.commons.exceptions.IllegalValueException;

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

    @Override
    public boolean isDateTimeAfter(DateTime dateTime) {
        // Compare Dates
        boolean isDateAfter = !isDateEmpty() && !dateTime.isDateEmpty() 
                && getDate().getLocalDate().isAfter(dateTime.getDate().getLocalDate());
        
        // Dates are equal, compare Time
        boolean isDateEqualAndTimeAfter = !isDateEmpty() && !isTimeEmpty() && !dateTime.isDateEmpty() && !dateTime.isTimeEmpty()
                && getDate().getLocalDate().isEqual(dateTime.getDate().getLocalDate())
                && (getTime().getLocalTime().equals(dateTime.getTime().getLocalTime())
                || getTime().getLocalTime().isAfter(dateTime.getTime().getLocalTime()));
        
        // No dates, compare Time
        boolean isNoDateAndTimeAfter = isDateEmpty() && !isTimeEmpty() && dateTime.isDateEmpty() && !dateTime.isTimeEmpty()
                && getTime().getLocalTime().isAfter(dateTime.getTime().getLocalTime());
        
        return isDateAfter || isDateEqualAndTimeAfter || isNoDateAndTimeAfter;
    }

    @Override
    public boolean isDateTimeAfterCurrentDateTime() {
        // Compare Dates
        boolean isDateAfter = (!isDateEmpty() && getDate().getLocalDate().isBefore(LocalDate.now()));
        
        // Dates are equal, compare Time
        boolean isDateEqualAndTimeAfter = !isDateEmpty() && !isTimeEmpty() && getDate().getLocalDate().isEqual(LocalDate.now())
                && getTime().getLocalTime().isBefore(LocalTime.now());
        
        // No dates, compare Time
        boolean isNoDateAndTimeAfter = !isTimeEmpty() && getTime().getLocalTime().isBefore(LocalTime.now());
                
        return isDateAfter || isDateEqualAndTimeAfter || isNoDateAndTimeAfter;
    }

    @Override
    public boolean isDateEqualCurrentDate() {
        return !isDateEmpty() && getDate().getLocalDate().isEqual(LocalDate.now());
    }
    
    @Override
    public boolean isDateEqualUpcomingWeek() {
        return !isDateEmpty() && getDate().getLocalDate().isBefore(LocalDate.now().plusWeeks(1));
    }
    
    @Override
    public boolean isDateTimeEmpty() {
        return getDate().getLocalDate() == null && getTime().getLocalTime() == null;
    }

    @Override
    public boolean isDateEmpty() {
        return getDate().getLocalDate() == null;
    }

    @Override
    public boolean isTimeEmpty() {
        return getTime().getLocalTime() == null;
    }
}
