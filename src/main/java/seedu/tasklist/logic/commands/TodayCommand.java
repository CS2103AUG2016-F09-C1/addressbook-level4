package seedu.tasklist.logic.commands;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TodayCommand extends Command{
	public static final String COMMAND_WORD = "today";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks that need to be done by today";
    
    public static final String MESSAGE_SUCCESS = "Listed all tasks due today";
	@Override
	public Command prepare(String args) {
		return new TodayCommand();
	}

	@Override
	public CommandResult execute() {
		model.updateFilteredTaskListToday();
        return new CommandResult(MESSAGE_SUCCESS);
	}

}


