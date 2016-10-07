package seedu.tasklist.model.task;

import seedu.tasklist.commons.util.CollectionUtil;
import seedu.tasklist.model.tag.UniqueTagList;

import java.util.Objects;

/**
 * Represents a Task in the task list.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Task implements ReadOnlyTask {

    private Title task;
    private StartDate startDate;
    private Description description;
    private DueDate dueDate;

    private UniqueTagList tags;

    /**
     * Every field must be present and not null.
     */
    public Task(Title title, StartDate startDate, Description description, DueDate dueDate, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(title, startDate, description, dueDate, tags);
        this.task = title;
        this.startDate = startDate;
        this.description = description;
        this.dueDate = dueDate;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }

    /**
     * Copy constructor.
     */
    public Task(ReadOnlyTask source) {
        this(source.getTitle(), source.getStartDate(), source.getDescription(), source.getDueDate(), source.getTags());
    }

    @Override
    public Title getTitle() {
        return task;
    }

    @Override
    public StartDate getStartDate() {
        return startDate;
    }

    @Override
    public Description getDescription() {
        return description;
    }

    @Override
    public DueDate getDueDate() {
        return dueDate;
    }

    @Override
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }

    /**
     * Replaces this task's tags with the tags in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        tags.setTags(replacement);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyTask) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(task, startDate, description, dueDate, tags);
    }

    @Override
    public String toString() {
        return getAsText();
    }

}
