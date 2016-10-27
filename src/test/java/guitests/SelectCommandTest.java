package guitests;

import org.junit.Test;

import seedu.tasklist.commons.core.Messages;
import seedu.tasklist.logic.commands.SelectCommand;
import static org.junit.Assert.assertEquals;

//@@author A0146840E
public class SelectCommandTest extends TaskListGuiTest {


    @Test
    public void selectTask_nonEmptyList() {
        commandBox.runCommand("list");
        assertSelectionInvalidIndex(10); //invalid index
        assertNoTaskSelected();

        assertSelectionSuccess(1); //first task in the list
        int taskCount = td.getTypicalTasks().length;
        assertSelectionSuccess(taskCount); //last task in the list
        int middleIndex = taskCount / 2;
        assertSelectionSuccess(middleIndex); //a task in the middle of the list

        assertSelectionInvalidIndex(taskCount + 1); //invalid index
        
        assertSelectionInvalid(0); //invalid
        assertSelectionInvalid(-1); //invalid
    }

    @Test
    public void selectTask_emptyList(){
        commandBox.runCommand("clear");
        assertListSize(0);
        assertSelectionInvalidIndex(1); //invalid index
    }

    private void assertSelectionInvalidIndex(int index) {
        commandBox.runCommand("select " + index);
        assertResultMessage("The task index provided is invalid");
    }
    
    private void assertSelectionInvalid(int index) {
        commandBox.runCommand("select " + index);
        assertResultMessage(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SelectCommand.MESSAGE_USAGE));
    }

    private void assertSelectionSuccess(int index) {
        commandBox.runCommand("select " + index);
        assertResultMessage(td.getTypicalTaskList().getTaskList().get(index-1).getAllAsText());
    }

    private void assertNoTaskSelected() {
        assertEquals(taskListPanel.getSelectedTasks().size(), 0);
    }

}
