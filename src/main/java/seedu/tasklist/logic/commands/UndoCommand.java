package seedu.tasklist.logic.commands;

import java.util.EmptyStackException;

//@@author A0138516A
/*
 * Undo the previous command
 */

public class UndoCommand extends Command {

	public static final String COMMAND_WORD = "undo";
	public static final String MESSAGE_USAGE = COMMAND_WORD + ": undo the previous command";
	public static final String MESSAGE_UNDO_FAIL = "There is no previous undo command";

	public UndoCommand() {

	}

	/**
	 * To get the previous command from the CommandHistory and pass to the specific command's undo method
	 */	
	@Override
	public CommandResult execute() {
		try {
			return CommandHistory.getCommandHistory().undo();
		} catch (EmptyStackException e) {
			return new CommandResult(MESSAGE_UNDO_FAIL);
		}

	}

	@Override
	public Command prepare(String args) {
		return new UndoCommand();
	}
}
