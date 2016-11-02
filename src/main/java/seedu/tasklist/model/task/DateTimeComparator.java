package seedu.tasklist.model.task;

//@@author A0146840E
/**
 * A immutable interface for comparing a Task's DateTime in the task list.
 */
public interface DateTimeComparator {

    /**
     * Check if date time is after the compared DateTime.
     */
    public boolean isDateTimeAfter(DateTime dateTime);

    /**
     * Check if date time is before current local date time.
     */
    public boolean isDateTimeAfterCurrentDateTime();
    
    /**
     * Check if date is equal to current local date.
     */
    public boolean isDateEqualCurrentDate();
    
    /**
     * Check if date is equal to one week from current local date.
     */
    public boolean isDateEqualCurrentDateTillUpcomingWeek();

    /**
     * Check if date and time is empty.
     */
    public boolean isDateTimeEmpty();
    
    /**
     * Check if date is empty.
     */
    public boolean isDateEmpty();
    
    /**
     * Check if time is empty.
     */
    public boolean isTimeEmpty();

}
