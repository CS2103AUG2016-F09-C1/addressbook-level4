package seedu.tasklist.logic.commands;

import java.util.EmptyStackException;
import java.util.Stack;

//@@author A0138516A
/*
 *To store all the command used
 */

public class CommandHistory {

	private static Stack<Command> commandHistory = new Stack<Command>();
	
	/**
	 * Method to add the Command object into the commandHistory stack	
	 * @param command
	 */
	public static void addCommandHistory(Command command) {
		commandHistory.push(command);
	}
	
	/**
	 * To get the previous command and pass to UndoCommand
	 * @return the previous command
	 */
	public static CommandUndoExtension getCommandHistory() {
		if (commandHistory.size() > 0) {
			return (CommandUndoExtension) commandHistory.pop();
		} else {
			throw new EmptyStackException();
		}
	}
}
