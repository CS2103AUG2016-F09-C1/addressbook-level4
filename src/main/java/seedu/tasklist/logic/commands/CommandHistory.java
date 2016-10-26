package seedu.tasklist.logic.commands;

import java.util.EmptyStackException;
import java.util.Stack;

//@@author A0138516A
/*
 *To store all the command used
 */

public class CommandHistory {

	private static Stack<Command> commandHistory = new Stack<Command>();

	public static void addCommandHistory(Command command) {
		commandHistory.push(command);
	}
	
	public static CommandExtenstion getCommandHistory() {
		if (commandHistory.size() > 0) {
			return (CommandExtenstion)commandHistory.pop();
		}else
			throw new EmptyStackException();
	}
}
