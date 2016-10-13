package seedu.tasklist.logic.commands;

import seedu.tasklist.logic.parser.CommandParser;

/**
 * Lists all tasks in the task list to the user.
 */
public class ListCommand extends Command implements CommandParser {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";

    public ListCommand() {}

    @Override
    public CommandResult execute() {
        model.updateFilteredListToShowAll();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public Command prepare(String args) {
        return new ListCommand();
    }
}
