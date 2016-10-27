package seedu.tasklist.logic.commands;

 // @@author A0138516A
 /*
 * For those command has implemented undo function
 */
public abstract class CommandUndoExtension extends Command {

	public String MESSAGE_UNDO = "Undo: ";

	public abstract CommandResult undo();

}
