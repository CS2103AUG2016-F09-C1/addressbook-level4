package guitests;

import static org.junit.Assert.*;

import org.junit.Test;

import guitests.guihandles.TaskCardHandle;
import seedu.tasklist.model.task.Title;
import seedu.tasklist.testutil.TestTask;
import seedu.tasklist.testutil.TestUtil;
import seedu.tasklist.testutil.TypicalTestTasks;

//@@author A0138516A
public class UndoCommandTest extends TaskListGuiTest {

	@Test
	public void undoAddTaskSuccess() {
		TestTask[] currentList = td.getTypicalTasks();
		commandBox.runCommand("list");

		// add one task
		TestTask taskToAdd = TypicalTestTasks.taskTest;
		commandBox.runCommand(taskToAdd.getAddCommand());
		TestUtil.addTasksToList(currentList, taskToAdd);
		commandBox.runCommand("undo");
		assertTrue(taskListPanel.isListMatching(currentList));
	}

	@Test
	public void undoDeleteTaskSuccess() {
		TestTask[] currentList = td.getTypicalTasks();
		commandBox.runCommand("list");
		// add one task
		TestTask taskToAdd = TypicalTestTasks.taskTest;
		commandBox.runCommand(taskToAdd.getAddCommand());
		currentList = TestUtil.addTasksToList(currentList, taskToAdd);
		commandBox.runCommand("delete " + currentList.length);
		commandBox.runCommand("undo");
		assertTrue(taskListPanel.isListMatching(currentList));
	}

	@Test
	public void undoEditTaskSuccess() {
		TestTask[] currentList = td.getTypicalTasks();
		commandBox.runCommand("list");
		// add one task
		TestTask taskToAdd = TypicalTestTasks.taskTest;
		commandBox.runCommand(taskToAdd.getAddCommand());
		currentList = TestUtil.addTasksToList(currentList, taskToAdd);
		Title beforeEditTitle = currentList[currentList.length - 1].getTitle();
		commandBox.runCommand("edit " + currentList.length + " Order 2 pizza");
		Title afterEditTitle = currentList[currentList.length - 1].getTitle();
		assertTrue(beforeEditTitle.equals(afterEditTitle));
	}

	@Test
	public void undoMarkAndUnmarkSuccess() {
		// Test for mark command
		TestTask[] currentList = td.getTypicalTasks();
		TestTask beforeMarkTitle = currentList[0];
		commandBox.runCommand("list");
		commandBox.runCommand("mark " + 1);
		beforeMarkTitle.setCompleted(true);
		commandBox.runCommand("undo");
		beforeMarkTitle.setCompleted(false);
		TaskCardHandle afterMark = taskListPanel.navigateToTask(currentList[0].getTitle().fullTitle);
		assertUnmarked(beforeMarkTitle, afterMark);

		// Test for unmark command
		commandBox.runCommand("list");
		commandBox.runCommand("mark " + currentList.length);
		beforeMarkTitle.setCompleted(true);
		TestTask beforeUnMarkTitle = currentList[currentList.length-1];
		commandBox.runCommand("unmark " + currentList.length);
		beforeUnMarkTitle.setCompleted(false);
		commandBox.runCommand("undo");
		currentList[currentList.length-1].setCompleted(true);
		TaskCardHandle afterUnMark = taskListPanel.navigateToTask(currentList[currentList.length-1].getTitle().fullTitle);
		assertMarked(beforeUnMarkTitle, afterUnMark);
	}
}
