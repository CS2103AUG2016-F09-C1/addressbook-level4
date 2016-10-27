package seedu.tasklist.logic.commands;

 // @@author A0138516A
 /*
 * For those command has implemented undo function
 */
public abstract class CommandUndoExtension extends Command {

	public String MESSAGE_UNDO = "Undo: ";

	/**
	 * Method to implement in command class to undo the command
	 * 
	 * @return feedback message of the operation result for display
	 */
	public abstract CommandResult undo();

}
