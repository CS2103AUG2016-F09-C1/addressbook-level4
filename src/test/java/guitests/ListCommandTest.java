package guitests;

import org.junit.Test;

import seedu.tasklist.commons.core.Messages;
import seedu.tasklist.logic.commands.ListCommand;
import seedu.tasklist.testutil.TypicalTestTasks;

public class ListCommandTest extends TaskListGuiTest {

    @Test
    public void list() {
        
        //list
        commandBox.runCommand("list");
        assertResultMessage(String.format(ListCommand.MESSAGE_SUCCESS, ""));
        
        commandBox.runCommand("mark 1");
        commandBox.runCommand("list completed");
        assertResultMessage(String.format(ListCommand.MESSAGE_SUCCESS, "completed "));
        assertListSize(1);
        
        commandBox.runCommand(TypicalTestTasks.task9.getAddCommand());        
        commandBox.runCommand("list overdue");
        assertResultMessage(String.format(ListCommand.MESSAGE_SUCCESS, "overdue "));
        assertListSize(1);
        
        commandBox.runCommand("add floating");
        commandBox.runCommand("list floating");
        assertResultMessage(String.format(ListCommand.MESSAGE_SUCCESS, "floating "));
        assertListSize(1);
        
        commandBox.runCommand("list today");
        assertResultMessage(String.format(ListCommand.MESSAGE_SUCCESS, "today's "));
        assertListSize(1);
        
        commandBox.runCommand("list week");
        assertResultMessage(String.format(ListCommand.MESSAGE_SUCCESS, "this week's "));
        assertListSize(1);

        //invalid command
        commandBox.runCommand("lists");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
        commandBox.runCommand("lists 0");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
        commandBox.runCommand("list Johnny");
        assertResultMessage(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }
    
}
