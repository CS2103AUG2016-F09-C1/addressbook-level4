package guitests;

import org.junit.Test;

import guitests.guihandles.TaskCardHandle;
import seedu.tasklist.commons.core.Messages;
import seedu.tasklist.commons.exceptions.IllegalValueException;
import seedu.tasklist.logic.commands.EditCommand;
import seedu.tasklist.model.task.DateTime;
import seedu.tasklist.model.task.Description;
import seedu.tasklist.model.task.Time;
import seedu.tasklist.model.task.Title;
import seedu.tasklist.testutil.TestTask;

//@@author A0146840E
public class EditCommandTest extends TaskListGuiTest {

    @Test
    public void editTask_nonEmptyList_successResultMessage() {
        TestTask[] currentList = td.getTypicalTasks();
        commandBox.runCommand("list");
        
        //edit task title
        assertEditTitleSuccess(1, "Running", currentList[0]);

        //edit task description
        assertEditDescriptionSuccess(2, "Run faster", currentList[1]);
        
        //edit task date
        assertEditStartDateTimeSuccess(3, "10102004", currentList[2]);
        assertEditEndDateTimeSuccess(3, "11102004", currentList[2]);
        
        //edit task time
        assertEditStartTimeSuccess(5, "2300", currentList[4]);
        assertEditEndTimeSuccess(6, "1234", currentList[5]);
        
        //edit everything
        assertEditSuccess(7, "title", "description", "11012014 1100", "11012014 1200", currentList[6]); 
    }

    @Test
    public void editTask_nonEmptyList_invalidDateTimeResultMessage() {
        TestTask[] currentList = td.getTypicalTasks();
        commandBox.runCommand("list");
        
        assertEditSuccess(7, "title", "description", "11012014 1100", "11012014 1200", currentList[6]);
        
        commandBox.runCommand("edit 1 s/01019999");
        assertResultMessage(Messages.MESSAGE_INVALID_DATE_TIME_ENTRY);
        commandBox.runCommand("edit 1 e/01010000");
        assertResultMessage(Messages.MESSAGE_INVALID_DATE_TIME_ENTRY);
        commandBox.runCommand("edit 1 s/01019999 e/01010000");
        assertResultMessage(Messages.MESSAGE_INVALID_DATE_TIME_ENTRY);
        commandBox.runCommand("edit 7 s/1200");
        assertResultMessage(Messages.MESSAGE_INVALID_DATE_TIME_ENTRY);
        commandBox.runCommand("edit 7 e/1100");
        assertResultMessage(Messages.MESSAGE_INVALID_DATE_TIME_ENTRY);
        
        assertEditSuccess(7, "title", "description", "10012014 2345", "11012014 2345", currentList[6]);
        
        commandBox.runCommand("edit 7 s/11012014");
        assertResultMessage(Messages.MESSAGE_INVALID_DATE_TIME_ENTRY);
        commandBox.runCommand("edit 7 e/10012014");
        assertResultMessage(Messages.MESSAGE_INVALID_DATE_TIME_ENTRY);
        
        assertEditSuccess(7, "title", "description", "10012014 0000", "11012014 2359", currentList[6]);
        
        commandBox.runCommand("edit 7 s/11012014 2359");
        assertResultMessage(Messages.MESSAGE_INVALID_DATE_TIME_ENTRY);
        commandBox.runCommand("edit 7 e/10012014 0000");
        assertResultMessage(Messages.MESSAGE_INVALID_DATE_TIME_ENTRY);
    }
    
    @Test
    public void editTask_nonEmptyList_invalidCommandResultMessage() {
        commandBox.runCommand("edits 1");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
        commandBox.runCommand("edit index");
        assertResultMessage(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
    }
    
    @Test
    public void editTask_emptyList() {
        commandBox.runCommand("clear");
        commandBox.runCommand("edit 1 CS2103");
        assertResultMessage(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        commandBox.runCommand("edit 10 CS2103");
        assertResultMessage(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    private void assertEditTitleSuccess(int index, String title, TestTask taskToEdit) {
        commandBox.runCommand("edit " + index + " " + title);
        try {
            taskToEdit.setTitle(new Title(title));
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }

        //confirm the new card contains the right data
        TaskCardHandle editedCard = taskListPanel.navigateToTask(taskToEdit.getTitle().fullTitle);
        assertMatching(taskToEdit, editedCard);
    }    
    
    private void assertEditDescriptionSuccess(int index, String description, TestTask taskToEdit) {
        commandBox.runCommand("edit " + index + " d/" + description);
        try {
            taskToEdit.setDescription(new Description(description));
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }

        //confirm the new card contains the right data
        TaskCardHandle editedCard = taskListPanel.navigateToTask(taskToEdit.getTitle().fullTitle);
        assertMatching(taskToEdit, editedCard);
    }
    
    private void assertEditStartDateTimeSuccess(int index, String dateTime, TestTask taskToEdit) {
        commandBox.runCommand("edit " + index + " s/" + dateTime);
        try {
            taskToEdit.setStartDateTime(new DateTime(dateTime));
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }

        //confirm the new card contains the right data
        TaskCardHandle editedCard = taskListPanel.navigateToTask(taskToEdit.getTitle().fullTitle);
        assertMatching(taskToEdit, editedCard);
    }
    
    private void assertEditEndDateTimeSuccess(int index, String dateTime, TestTask taskToEdit) {
        commandBox.runCommand("edit " + index + " e/" + dateTime);
        try {
            taskToEdit.setEndDateTime(new DateTime(dateTime));
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }

        //confirm the new card contains the right data
        TaskCardHandle editedCard = taskListPanel.navigateToTask(taskToEdit.getTitle().fullTitle);
        assertMatching(taskToEdit, editedCard);
    }

    private void assertEditStartTimeSuccess(int index, String time, TestTask taskToEdit) {
        commandBox.runCommand("edit " + index + " s/" + time);
        try {
            taskToEdit.getStartDateTime().setTime(new Time(time));
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }

        //confirm the new card contains the right data
        TaskCardHandle editedCard = taskListPanel.navigateToTask(taskToEdit.getTitle().fullTitle);
        assertMatching(taskToEdit, editedCard);
    }
    
    private void assertEditEndTimeSuccess(int index, String time, TestTask taskToEdit) {
        commandBox.runCommand("edit " + index + " e/" + time);
        try {
            taskToEdit.getEndDateTime().setTime(new Time(time));
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }

        //confirm the new card contains the right data
        TaskCardHandle editedCard = taskListPanel.navigateToTask(taskToEdit.getTitle().fullTitle);
        assertMatching(taskToEdit, editedCard);
    }
    
    private void assertEditSuccess(int index, String title, String description, String startDateTime, String endDateTime, TestTask taskToEdit) {
        commandBox.runCommand("edit " + index + " " + title + " d/" + description + " s/" + startDateTime + " e/" + endDateTime);
        try {
            taskToEdit.setTitle(new Title(title));
            taskToEdit.setDescription(new Description(description));
            taskToEdit.setStartDateTime(new DateTime(startDateTime));
            taskToEdit.setEndDateTime(new DateTime(endDateTime));
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }

        //confirm the new card contains the right data
        TaskCardHandle editedCard = taskListPanel.navigateToTask(taskToEdit.getTitle().fullTitle);
        assertMatching(taskToEdit, editedCard);
    }
}
