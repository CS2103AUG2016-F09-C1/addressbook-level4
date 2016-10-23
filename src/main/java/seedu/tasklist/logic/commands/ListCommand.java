package seedu.tasklist.logic.commands;

import static seedu.tasklist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.HashSet;
import java.util.Set;

import seedu.tasklist.commons.exceptions.IllegalValueException;

/**
 * Lists tasks in the task list to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List tasks in the task list. \n"
            + "Parameters: [TYPE]\nTYPE: completed, overdue or floating\n" + "Example: \n" + COMMAND_WORD + " \n"
            + COMMAND_WORD + " completed\n" + COMMAND_WORD + " overdue\n" + COMMAND_WORD + " floating";

    public static final String MESSAGE_SUCCESS = "Listed all %1$stasks";

    private enum List {
        ALL, COMPLETED_TASKS, OVERDUE_TASKS, FLOATING_TASKS
    }

    private ListCommand.List type;

    public ListCommand() {
    }

    /**
     * List Command Constructor
     * 
     * @param args
     *            containing the requested parameter
     * @throws IllegalValueException
     *             if list command arguments are invalid
     */
    public ListCommand(String args) throws IllegalValueException {
        switch (args.trim()) {
        case "":
            type = ListCommand.List.ALL;
            break;
        case "completed":
            type = ListCommand.List.COMPLETED_TASKS;
            break;
        case "overdue":
            type = ListCommand.List.OVERDUE_TASKS;
            break;
        case "floating":
            type = ListCommand.List.FLOATING_TASKS;
            break;
        default:
            throw new IllegalValueException("");
        }
    }

    @Override
    public CommandResult execute() {
        final Set<String> keywordSet = new HashSet<>();
        switch (type) {
        case ALL:
            model.updateFilteredTaskListToShowAll();
            return new CommandResult(String.format(MESSAGE_SUCCESS, ""));
        case COMPLETED_TASKS:
            keywordSet.add("isCompleted");
            model.updateFilteredTaskList(keywordSet);
            return new CommandResult(String.format(MESSAGE_SUCCESS, "completed "));
        case OVERDUE_TASKS:
            keywordSet.add("isOverdue");
            model.updateFilteredTaskList(keywordSet);
            return new CommandResult(String.format(MESSAGE_SUCCESS, "overdue "));
        case FLOATING_TASKS:
            keywordSet.add("isFloating");
            model.updateFilteredTaskList(keywordSet);
            return new CommandResult(String.format(MESSAGE_SUCCESS, "floating "));
        default:
            return new CommandResult(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
    }

    /**
     * Parses arguments in the context of the list task command.
     *
     * @param args
     *            full command args string
     * @return the prepared command
     */
    @Override
    public Command prepare(String args) {
        try {
            return new ListCommand(args);
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
    }
}
