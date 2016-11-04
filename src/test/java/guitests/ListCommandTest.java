package guitests;

import org.junit.Test;

import seedu.tasklist.commons.core.Messages;
import seedu.tasklist.logic.commands.ListCommand;

//@@author A0146840E
public class ListCommandTest extends TaskListGuiTest {

    @Test
    public void listTasks_nonEmptyList_successResultMessage() {
        commandBox.runCommand("list");
        assertResultMessage(String.format(ListCommand.MESSAGE_SUCCESS, ""));
        
        commandBox.runCommand("mark 1");
        commandBox.runCommand("list completed");
        assertResultMessage(String.format(ListCommand.MESSAGE_SUCCESS, "completed "));
        assertListSize(1);
        
        commandBox.runCommand("add overdue e/11112011");        
        commandBox.runCommand("list overdue");
        assertResultMessage(String.format(ListCommand.MESSAGE_SUCCESS, "overdue "));
        assertListSize(1);
        
        commandBox.runCommand("add floating");
        commandBox.runCommand("list floating");
        assertResultMessage(String.format(ListCommand.MESSAGE_SUCCESS, "floating "));
        assertListSize(1);
    }
    
    @Test
    public void listTasks_nonEmptyList_noTaskResultMessage() {
        commandBox.runCommand("list");
        assertResultMessage(String.format(ListCommand.MESSAGE_SUCCESS, ""));
        
        commandBox.runCommand("list today");
        assertResultMessage(ListCommand.MESSAGE_NO_TASK_TODAY);
        assertListSize(0);
        
        commandBox.runCommand("list week");
        assertResultMessage(ListCommand.MESSAGE_NO_TASK_WEEK);
        assertListSize(0);
    }
    
    @Test
    public void listTasks_nonEmptyList_invalidCommandResultMessage() {
        commandBox.runCommand("lists");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
        commandBox.runCommand("lists 0");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
        commandBox.runCommand("list Johnny");
        assertResultMessage(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }
    
    @Test
    public void listTasks_emptyList() {
        commandBox.runCommand("clear");
        
        commandBox.runCommand("list today");
        assertResultMessage(ListCommand.MESSAGE_NO_TASK_TODAY);
        
        commandBox.runCommand("list week");
        assertResultMessage(ListCommand.MESSAGE_NO_TASK_WEEK);
    }
    
}
