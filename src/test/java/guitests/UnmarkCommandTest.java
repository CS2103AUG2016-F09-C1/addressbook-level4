package guitests;

import org.junit.Test;

import guitests.guihandles.TaskCardHandle;
import seedu.tasklist.commons.core.Messages;
import seedu.tasklist.logic.commands.UnmarkCommand;
import seedu.tasklist.testutil.TestTask;

//@@author A0146840E
public class UnmarkCommandTest extends TaskListGuiTest {

    @Test
    public void unmarkTask_nonEmptyList_successResultMessage() {
        TestTask[] currentList = td.getTypicalTasks();
        
        commandBox.runCommand("list");
        commandBox.runCommand("mark 1");
        assertUnmarkSuccess(1, currentList[0]);
        
        commandBox.runCommand("list");
        commandBox.runCommand("mark 7");
        assertUnmarkSuccess(1, currentList[6]);
    }

    @Test
    public void unmarkTask_nonEmptyList_invalidTaskIndexResultMessage() {
        TestTask[] currentList = td.getTypicalTasks();
        commandBox.runCommand("list");
        
        commandBox.runCommand("unmark " + (currentList.length + 1));
        assertResultMessage(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        commandBox.runCommand("unmark " + (currentList.length + 10));
        assertResultMessage(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }
    
    @Test
    public void unmarkTask_nonEmptyList_unmarkedTaskResultMessage() {
        TestTask[] currentList = td.getTypicalTasks();
        commandBox.runCommand("list");
        
        commandBox.runCommand("unmark 1");
        assertResultMessage(UnmarkCommand.MESSAGE_UNMARKED_TASK);
        commandBox.runCommand("unmark " + currentList.length);
        assertResultMessage(UnmarkCommand.MESSAGE_UNMARKED_TASK);
    }
    
    @Test
    public void unmarkTask_nonEmptyList_invalidCommandResultMessage() {
        commandBox.runCommand("unmark -10");
        assertResultMessage(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE));
        commandBox.runCommand("unmark -1");
        assertResultMessage(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE));
        commandBox.runCommand("unmarks 1");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
        commandBox.runCommand("unmark index");
        assertResultMessage(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE));
    }
    
    @Test
    public void unmarkTask_emptyList_invalidTaskIndexResultMessage() {
        commandBox.runCommand("unmark 1");
        assertResultMessage(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }
    
    private void assertUnmarkSuccess(int index, TestTask taskToUnmark) {
        commandBox.runCommand("list completed");
        commandBox.runCommand("unmark " + index);
        taskToUnmark.setCompleted(false);
        
        //confirm the new card contains the right data
        assertResultMessage("Task unmarked: " + taskToUnmark.getAsText());
        commandBox.runCommand("list");
        TaskCardHandle markedCard = taskListPanel.navigateToTask(taskToUnmark.getTitle().fullTitle);
        assertUnmarked(taskToUnmark, markedCard);
    }
}
