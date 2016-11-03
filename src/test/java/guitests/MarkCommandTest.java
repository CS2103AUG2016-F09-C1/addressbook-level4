package guitests;

import org.junit.Test;

import guitests.guihandles.TaskCardHandle;
import seedu.tasklist.commons.core.Messages;
import seedu.tasklist.logic.commands.MarkCommand;
import seedu.tasklist.testutil.TestTask;

//@@author A0146840E
public class MarkCommandTest extends TaskListGuiTest {

    @Test
    public void markTask_nonEmptyList_successResultMessage() {
        TestTask[] currentList = td.getTypicalTasks();
        commandBox.runCommand("list");
        
        assertMarkSuccess(1, currentList[0]);
        assertMarkSuccess(1, currentList[1]);     
    }

    @Test
    public void markTask_nonEmptyList_invalidTaskIndexResultMessage() {
        TestTask[] currentList = td.getTypicalTasks();
        commandBox.runCommand("list");
        
        commandBox.runCommand("mark " + (currentList.length + 1));
        assertResultMessage(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        commandBox.runCommand("mark " + (currentList.length + 10));
        assertResultMessage(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void markTask_nonEmptyList_markedTaskResultMessage() {
        TestTask[] currentList = td.getTypicalTasks();
        commandBox.runCommand("list");
        
        commandBox.runCommand("mark " + (currentList.length - 1));
        commandBox.runCommand("mark " + (currentList.length));
        assertResultMessage(MarkCommand.MESSAGE_MARKED_TASK);
    }

    @Test
    public void markTask_nonEmptyList_invalidCommandResultMessage() {
        commandBox.runCommand("mark -10");
        assertResultMessage(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        commandBox.runCommand("mark -1");
        assertResultMessage(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        commandBox.runCommand("marks 1");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
        commandBox.runCommand("mark index");
        assertResultMessage(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
    }

    @Test
    public void markTask_emptyList_invalidTaskIndexResultMessage() {
        commandBox.runCommand("mark 1");
        assertResultMessage(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }
    
    private void assertMarkSuccess(int index, TestTask taskToMark) {
        commandBox.runCommand("mark " + index);
        taskToMark.setCompleted(true);
        
        //confirm the new card contains the right data
        TaskCardHandle markedCard = taskListPanel.navigateToTask(taskToMark.getTitle().fullTitle);
        assertMarked(taskToMark, markedCard);
        assertResultMessage("Task marked: " + taskToMark.getAsText());
    }
}
