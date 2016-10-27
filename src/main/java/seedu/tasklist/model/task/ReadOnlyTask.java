package seedu.tasklist.model.task;

import seedu.tasklist.model.tag.UniqueTagList;

/**
 * A read-only immutable interface for a Task in the task list.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface ReadOnlyTask {
    
    //@@author A0140019W
    Title getTitle();
    Description getDescription();
    DateTime getStartDateTime();
    DateTime getEndDateTime();
    
    //@@author A0146840E
    boolean isCompleted();
    boolean isOverdue();
    boolean isFloating();

    //@@author
    /**
     * The returned TagList is a deep copy of the internal TagList,
     * changes on the returned list will not affect the task's internal tags.
     */
    UniqueTagList getTags();

    /**
     * Returns true if both have the same state. (interfaces cannot override .equals)
     */
    default boolean isSameStateAs(ReadOnlyTask other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getTitle().toString().equals(this.getTitle().toString())
                && other.getDescription().toString().equals(this.getDescription().toString())
                && other.getStartDateTime().toString().equals(this.getStartDateTime().toString())
                && other.getEndDateTime().toString().equals(this.getEndDateTime().toString())
                && String.valueOf(other.isCompleted()).equals(String.valueOf(this.isCompleted())));
    }

    /**
     * Formats the task as text, showing Title, Description, Start and End DateTime and Tags.
     */
    default String getAsText() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append(" Description: ")
                .append(getDescription())
                .append(" Start: ")
                .append(getStartDateTime().toString().replace(":", ""))
                .append(" End: ")
                .append(getEndDateTime().toString().replace(":", ""))
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
    
    //@@author A0146840E
    /**
     * Formats the task as text, showing all details.
     */
    default String getAllAsText() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Title: ")
                .append(getTitle())
                .append("\nDescription: ")
                .append(getDescription())
                .append("\nStart: ")
                .append(getStartDateTime())
                .append("\nEnd: ")
                .append(getEndDateTime())
                .append("\nTags: ");
        getTags().forEach(builder::append);
        builder.append("\nCompleted: ")
                .append(isCompleted());
        return builder.toString();
    }

    //@@author
    /**
     * Returns a string representation of this Task's tags
     */
    default String tagsString() {
        final StringBuffer buffer = new StringBuffer();
        final String separator = ", ";
        getTags().forEach(tag -> buffer.append(tag).append(separator));
        if (buffer.length() == 0) {
            return "";
        } else {
            return buffer.substring(0, buffer.length() - separator.length());
        }
    }

}
