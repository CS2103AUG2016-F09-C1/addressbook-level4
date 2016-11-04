package seedu.tasklist.logic;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.tasklist.logic.commands.CommandResult;
import seedu.tasklist.model.task.ReadOnlyTask;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     */
    CommandResult execute(String commandText);

    //@@author A0146840E
    /** Returns the filtered list of tasks */
    ObservableList<ReadOnlyTask> getFilteredTaskList();
    
    /** Returns the weekly filtered list of tasks */
    ObservableList<ReadOnlyTask> getMainFilteredTaskList();

    /** Returns the entire list of tasks */
    List<ReadOnlyTask> getTaskList();
}
