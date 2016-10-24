package seedu.tasklist.model;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.tasklist.commons.core.ComponentManager;
import seedu.tasklist.commons.core.LogsCenter;
import seedu.tasklist.commons.core.UnmodifiableObservableList;
import seedu.tasklist.commons.events.model.TaskListChangedEvent;
import seedu.tasklist.commons.util.StringUtil;
import seedu.tasklist.model.task.DateTime;
import seedu.tasklist.model.task.ReadOnlyTask;
import seedu.tasklist.model.task.Task;
import seedu.tasklist.model.task.UniqueTaskList;
import seedu.tasklist.model.task.UniqueTaskList.TaskCompletionException;
import seedu.tasklist.model.task.UniqueTaskList.TaskNotFoundException;

import java.util.Comparator;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Represents the in-memory model of the task list data.
 * All changes to any model should be synchronized.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TaskList taskList;
    private FilteredList<Task> filteredTask;
    private final FilteredList<Task> mainFilteredTaskList;

    /**
     * Initializes a ModelManager with the given TaskList
     * TaskList and its variables should not be null
     */
    public ModelManager(TaskList src, UserPrefs userPrefs) {
        super();
        assert src != null;
        assert userPrefs != null;

        logger.fine("Initializing with task list: " + src + " and user prefs " + userPrefs);

        taskList = new TaskList(src);
        filteredTask = new FilteredList<>(taskList.getTask());
        mainFilteredTaskList = new FilteredList<>(taskList.getTask());
        
        updateFilteredTaskList("week");
    }

    public ModelManager() {
        this(new TaskList(), new UserPrefs());
    }

    public ModelManager(ReadOnlyTaskList initialData, UserPrefs userPrefs) {
        taskList = new TaskList(initialData);
        filteredTask = new FilteredList<>(taskList.getTask());
        mainFilteredTaskList = new FilteredList<>(taskList.getTask());
        
        updateFilteredTaskList("week");
    }

    @Override
    public void resetData(ReadOnlyTaskList newData) {
        taskList.resetData(newData);
        indicateTaskListChanged();
    }

    @Override
    public ReadOnlyTaskList getTaskList() {
        return taskList;
    }

    /** Raises an event to indicate the model has changed */
    private void indicateTaskListChanged() {
        taskList.sort();
        raise(new TaskListChangedEvent(taskList));
    }

    @Override
    public synchronized void deleteTask(ReadOnlyTask target) throws TaskNotFoundException {
        taskList.removeTask(target);
        indicateTaskListChanged();
    }
    
    @Override
    public void markTask(ReadOnlyTask target) throws TaskNotFoundException, TaskCompletionException {
        taskList.markTask(target);
        indicateTaskListChanged();
    }
    
    @Override
    public void unmarkTask(ReadOnlyTask target) throws TaskNotFoundException, TaskCompletionException {
        taskList.unmarkTask(target);
        indicateTaskListChanged();
    }

    @Override
    public synchronized void addTask(Task task) throws UniqueTaskList.DuplicateTaskException {
        taskList.addTask(task);
        updateFilteredTaskListToShowAll();
        indicateTaskListChanged();
    }
    
    @Override
    public void editTask(Task taskToEdit, ReadOnlyTask target) throws TaskNotFoundException {
        taskList.editTask(taskToEdit, target);
        updateFilteredTaskListToShowAll();
        indicateTaskListChanged();
    }
    
    @Override
    public void updateTaskListAfterFilePathChange() {
    	indicateTaskListChanged();
    }
    
    //=========== Filtered Task List Accessors ===============================================================

    @Override
    public UnmodifiableObservableList<ReadOnlyTask> getFilteredTaskList() {
        return new UnmodifiableObservableList<>(filteredTask);
    }

    @Override
    public void updateFilteredTaskListToShowAll() {
        filteredTask.setPredicate(null);
    }
    
    @Override
    public void updateFilteredTaskList(Set<String> keywords){
        updateFilteredTaskList(new PredicateExpression(new TaskContentQualifier(keywords)));
    }
    
    @Override
    public void updateFilteredTaskList(String status){
        updateFilteredTaskList(new PredicateExpression(new TaskStatusQualifier(status)));
    }
    
    private void updateFilteredTaskList(Expression expression) {
        filteredTask.setPredicate(expression::satisfies);
    }
    
    @Override
    public UnmodifiableObservableList<ReadOnlyTask> getMainFilteredTaskList() {
        return new UnmodifiableObservableList<>(mainFilteredTaskList);
    }

    //========== Inner classes/interfaces used for filtering ==================================================

    interface Expression {
        boolean satisfies(ReadOnlyTask task);
        String toString();
    }

    private class PredicateExpression implements Expression {

        private final Qualifier qualifier;

        private PredicateExpression(Qualifier qualifier) {
            this.qualifier = qualifier;
        }

        @Override
        public boolean satisfies(ReadOnlyTask task) {
            return qualifier.run(task);
        }

        @Override
        public String toString() {
            return qualifier.toString();
        }
    }

    interface Qualifier {
        boolean run(ReadOnlyTask task);
        String toString();
    }

    private class TaskContentQualifier implements Qualifier {
        private Set<String> keyWords;
        
        private TaskContentQualifier(Set<String> keyWords) {
            this.keyWords = keyWords;
        }

        @Override
        public boolean run(ReadOnlyTask task) {
            return keyWords.stream()
                    .filter(keyword -> StringUtil.containsIgnoreCase(task.getAsText(), keyword))
                    .findAny()
                    .isPresent();   
        }

        @Override
        public String toString() {
            return "Task content =" + String.join(", ", keyWords);
        }
    }
    
    private class TaskStatusQualifier implements Qualifier {
        private String status;
        
        private TaskStatusQualifier(String status) {
            this.status = status;
        }

        @Override
        public boolean run(ReadOnlyTask task) {
            if (status.contains("isCompleted")) {
                return task.isCompleted();
            } else if (status.equals("isOverdue")) {
                return task.isOverdue();
            } else if (status.equals("isFloating")) {
                return task.isFloating();
            } else if (status.equals("today")) {
                return task.isOverdue() || !task.isCompleted() && task.getEndDateTime().isDateEqualCurrentDate();
            } else if (status.equals("week")) {
                return task.isOverdue() || !task.isCompleted() && task.getEndDateTime().isDateEqualCurrentDateTillUpcomingWeek();
            } else {
                return false;
            }
        }

        @Override
        public String toString() {
            return "Task status =" + String.join(", ", status);
        }
    }
}
