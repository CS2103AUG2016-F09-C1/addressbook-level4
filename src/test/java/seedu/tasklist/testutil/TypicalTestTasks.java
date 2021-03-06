package seedu.tasklist.testutil;

import seedu.tasklist.commons.exceptions.IllegalValueException;
import seedu.tasklist.model.TaskList;
import seedu.tasklist.model.task.*;

//@@author A0146840E
/**
 * Contains a list of tasks for testing
 */
public class TypicalTestTasks {

    public static TestTask task1, task2, task3, task4, task5, task6, task7, task8, task9, task10;

    public TypicalTestTasks() {
        try {
            task1 = new TaskBuilder().withTitle("Exam Revision").withEndDateTime("02022202")
                    .withDescription("studying in NUS").withStartDateTime("01012001").withTags("urgent").build();
            task2 = new TaskBuilder().withTitle("Upcoming Quiz").withEndDateTime("04042204 1700")
                    .withDescription("have a hard time in comsci").withStartDateTime("30032003 1300")
                    .withTags("notime", "toughlife").build();
            task3 = new TaskBuilder().withTitle("Competition 2006").withDescription("train harder")
                    .withStartDateTime("05052005").withEndDateTime("06062206").build();
            task4 = new TaskBuilder().withTitle("Test Failure").withStartDateTime("05052007")
                    .withDescription("study harder").withEndDateTime("06062207").build();
            task5 = new TaskBuilder().withTitle("Tutorial 1").withStartDateTime("01012001")
                    .withDescription("refer to lecture notes").withEndDateTime("10042212").build();
            task6 = new TaskBuilder().withTitle("Quiz Failure").withStartDateTime("03032003")
                    .withDescription("keep trying").withEndDateTime("05012213").build();
            task7 = new TaskBuilder().withTitle("Task Name").withStartDateTime("04042004")
                    .withDescription("Description Name").withEndDateTime("05062214").build();

            // Manually added
            task8 = new TaskBuilder().withTitle("Revision").withStartDateTime("05062001").withDescription("dont stop studying")
                    .withEndDateTime("01102214").build();
            task9 = new TaskBuilder().withTitle("Hard Labor").withStartDateTime("07062001 2359")
                    .withDescription("need to get strong").withEndDateTime("03072215 0000").build();
            
            task10 = new TaskBuilder().withTitle("Order pizza").withStartDateTime("05112216 1030")
                    .withDescription("need order two large pizza").withEndDateTime("05112216 1100").build();
            
        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

    public static void loadTaskListWithSampleData(TaskList ab) {

        try {
            ab.addTask(new Task(task1));
            ab.addTask(new Task(task2));
            ab.addTask(new Task(task3));
            ab.addTask(new Task(task4));
            ab.addTask(new Task(task5));
            ab.addTask(new Task(task6));
            ab.addTask(new Task(task7));
        } catch (UniqueTaskList.DuplicateTaskException e) {
            assert false : "not possible";
        }
    }

    public TestTask[] getTypicalTasks() {
        return new TestTask[] { task1, task2, task3, task4, task5, task6, task7 };
    }

    public TaskList getTypicalTaskList() {
        TaskList ab = new TaskList();
        loadTaskListWithSampleData(ab);
        return ab;
    }
}
