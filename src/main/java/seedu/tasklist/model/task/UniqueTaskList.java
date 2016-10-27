package seedu.tasklist.model.task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.tasklist.commons.exceptions.DuplicateDataException;
import seedu.tasklist.commons.util.CollectionUtil;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A list of tasks that enforces uniqueness between its elements and does not allow nulls.
 *
 * Supports a minimal set of list operations.
 *
 * @see Task#equals(Object)
 * @see CollectionUtil#elementsAreUnique(Collection)
 */
public class UniqueTaskList implements Iterable<Task> {

    /**
     * Signals that an operation would have violated the 'no duplicates' property of the list.
     */
    public static class DuplicateTaskException extends DuplicateDataException {
        protected DuplicateTaskException() {
            super("Operation would result in duplicate tasks");
        }
    }

    /**
     * Signals that an operation targeting a specified task in the list would fail because
     * there is no such matching task in the list.
     */
    public static class TaskNotFoundException extends Exception {}

    public static class TaskCompletionException extends Exception {}
    
    private final ObservableList<Task> internalList = FXCollections.observableArrayList();

    /**
     * Constructs empty TaskList.
     */
    public UniqueTaskList() {}

    /**
     * Returns true if the list contains an equivalent task as the given argument.
     */
    public boolean contains(ReadOnlyTask toCheck) {
        assert toCheck != null;
        return internalList.contains(toCheck);
    }
    
    //@@author A0146840E
    /**
     * Sort the internal list in ascending end date time with floating tasks at the end.
     */
    private void sort() {
        List<Task> completedList = internalList.stream().filter(t -> t.isCompleted()).collect(Collectors.toList());
        internalList.removeAll(completedList);
        
        List<Task> floatingList = internalList.stream().filter(t -> t.isFloating()).collect(Collectors.toList());
        internalList.removeAll(floatingList);
        
        internalList.sort(new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                if (!t1.getEndDateTime().isDateEmpty() && !t2.getEndDateTime().isDateEmpty() 
                        && t1.getEndDateTime().isDateTimeAfter(t2.getEndDateTime())) {
                    return 1;
                } else {
                    return 0;
                } 
            }
        });
        
        internalList.addAll(floatingList);
        internalList.addAll(completedList);
    }
    
    //@@author
    /**
     * Adds a task and sorts the list.
     *
     * @throws DuplicateTaskException if the task to add is a duplicate of an existing task in the list.
     */
    public void add(Task toAdd) throws DuplicateTaskException {
        assert toAdd != null;
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }
        internalList.add(toAdd);
        sort();
    }

    //@@author A0146840E
    /**
     * Edits the equivalent task and sorts the list.
     *
     * @throws TaskNotFoundException if no such task could be found in the list.
     */
    public void edit(Task taskToEdit, ReadOnlyTask toEdit) throws TaskNotFoundException {
        assert toEdit != null;
        assert taskToEdit != null;
        final int notFoundInList = -1;
        
        int index = internalList.lastIndexOf(toEdit);
        if (index == notFoundInList) {
            throw new TaskNotFoundException();
        }
        internalList.set(index, taskToEdit);
        sort();
    }
    
    //@@author
    /**
     * Removes the equivalent task from the list.
     *
     * @throws TaskNotFoundException if no such task could be found in the list.
     */
    public boolean remove(ReadOnlyTask toRemove) throws TaskNotFoundException {
        assert toRemove != null;
        final boolean taskFoundAndDeleted = internalList.remove(toRemove);
        if (!taskFoundAndDeleted) {
            throw new TaskNotFoundException();
        }
        return taskFoundAndDeleted;
    }
    
    //@@author A0146840E
    /**
     * Mark the equivalent task from the list.
     *
     * @throws TaskNotFoundException if no such task could be found in the list.
     * @throws TaskCompletionException if task is already completed in the list.
     */
    public boolean mark(ReadOnlyTask toMark) throws TaskNotFoundException, TaskCompletionException {
        assert toMark != null;
        final int taskNotFound = -1;
        
        final int index = internalList.indexOf(toMark);
        if (index == taskNotFound) {
            throw new TaskNotFoundException();
        } else if (internalList.get(index).isCompleted()) {
            throw new TaskCompletionException();
        }
        
        final Task completeTask = internalList.get(index);
        completeTask.setCompleted(true);
        internalList.set(index, completeTask);
        sort();
        
        return true;
    }
    
    /**
     * Unmark the equivalent task from the list.
     *
     * @throws TaskNotFoundException if no such task could be found in the list.
     * @throws TaskCompletionException if task is already completed in the list.
     */
    public boolean unmark(ReadOnlyTask toUnmark) throws TaskNotFoundException, TaskCompletionException {
        assert toUnmark != null;
        final int taskNotFound = -1;
        
        final int index = internalList.indexOf(toUnmark);
        if (index == taskNotFound) {
            throw new TaskNotFoundException();
        } else if (!internalList.get(index).isCompleted()) {
            throw new TaskCompletionException();
        }
        
        final Task completeTask = internalList.get(index);
        completeTask.setCompleted(false);
        internalList.set(index, completeTask);
        sort();
        
        return true;
    }
    
    //@@author A0138516A
    public void insert(int targetIndex, Task undoTask) throws TaskNotFoundException{
    	internalList.add(targetIndex-1, undoTask);
	}
    
    //@@author A0138516A
    public void replace(Task beforeEdit, Task afterEdit) throws TaskNotFoundException{
		int indexToReplace = internalList.indexOf(afterEdit);
		internalList.set(indexToReplace, beforeEdit);		
	}

    //@@author
    public ObservableList<Task> getInternalList() {
        return internalList;
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTaskList // instanceof handles nulls
                && this.internalList.equals(
                ((UniqueTaskList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
