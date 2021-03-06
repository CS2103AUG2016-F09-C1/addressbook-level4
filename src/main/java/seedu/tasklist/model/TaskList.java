package seedu.tasklist.model;

import javafx.collections.ObservableList;
import seedu.tasklist.model.tag.Tag;
import seedu.tasklist.model.tag.UniqueTagList;
import seedu.tasklist.model.task.ReadOnlyTask;
import seedu.tasklist.model.task.Task;
import seedu.tasklist.model.task.UniqueTaskList;
import seedu.tasklist.model.task.UniqueTaskList.TaskNotFoundException;
import seedu.tasklist.model.task.UniqueTaskList.TaskCompletionException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Wraps all data at the task list level
 * Duplicates are not allowed (by .equals comparison)
 */
public class TaskList implements ReadOnlyTaskList {

    private final UniqueTaskList task;
    private final UniqueTagList tags;

    {
        task = new UniqueTaskList();
        tags = new UniqueTagList();
    }

    public TaskList() {}

    /**
     * Tasks and Tags are copied into this task list
     */
    public TaskList(ReadOnlyTaskList toBeCopied) {
        this(toBeCopied.getUniqueTaskList(), toBeCopied.getUniqueTagList());
    }

    /**
     * Tasks and Tags are copied into this task list
     */
    public TaskList(UniqueTaskList tasks, UniqueTagList tags) {
        resetData(tasks.getInternalList(), tags.getInternalList());
    }

    public static ReadOnlyTaskList getEmptyTaskList() {
        return new TaskList();
    }

//// list overwrite operations

    public ObservableList<Task> getTask() {
        return task.getInternalList();
    }

    public void setTask(List<Task> tasks) {
        this.task.getInternalList().setAll(tasks);
    }

    public void setTags(Collection<Tag> tags) {
        this.tags.getInternalList().setAll(tags);
    }

    public void resetData(Collection<? extends ReadOnlyTask> newTasks, Collection<Tag> newTags) {
        setTask(newTasks.stream().map(Task::new).collect(Collectors.toList()));
        setTags(newTags);
    }

    public void resetData(ReadOnlyTaskList newData) {
        resetData(newData.getTaskList(), newData.getTagList());
    }

//// task-level operations

    /**
     * Adds a task to the task list.
     * Also checks the new task's tags and updates {@link #tags} with any new tags found,
     * and updates the Tag objects in the task to point to those in {@link #tags}.
     *
     * @return index of the task in the task list
     * @throws UniqueTaskList.DuplicateTaskException if an equivalent task already exists.
     */
    public int addTask(Task t) throws UniqueTaskList.DuplicateTaskException {
        syncTagsWithMasterList(t);
        return task.add(t);
    }

    /**
     * Ensures that every tag in this task:
     *  - exists in the master list {@link #tags}
     *  - points to a Tag object in the master list
     */
    private void syncTagsWithMasterList(Task task) {
        final UniqueTagList taskTags = task.getTags();
        tags.mergeFrom(taskTags);

        // Create map with values = tag object references in the master list
        final Map<Tag, Tag> masterTagObjects = new HashMap<>();
        for (Tag tag : tags) {
            masterTagObjects.put(tag, tag);
        }

        // Rebuild the list of task tags using references from the master list
        final Set<Tag> commonTagReferences = new HashSet<>();
        for (Tag tag : taskTags) {
            commonTagReferences.add(masterTagObjects.get(tag));
        }
        task.setTags(new UniqueTagList(commonTagReferences));
    }

    public boolean removeTask(ReadOnlyTask key) throws UniqueTaskList.TaskNotFoundException {
        if (task.remove(key)) {
            return true;
        } else {
            throw new UniqueTaskList.TaskNotFoundException();
        }
    }
    
    //@@author A0146840E
    /**
     * Edits a task in the task list.
     * Also checks the new task's tags and updates {@link #tags} with any new tags found,
     * and updates the Tag objects in the task to point to those in {@link #tags}.
     * 
     * @return index of the task in the task list
     * @throws TaskNotFoundException if task does not exist.
     */
    public int editTask(Task taskToEdit, ReadOnlyTask key) throws TaskNotFoundException {
        syncTagsWithMasterList(taskToEdit);
        return task.edit(taskToEdit, key);
    }

    /**
     * Mark a task in the task list.
     * 
     * @throws TaskNotFoundException if task does not exist.
     */
    public boolean markTask(ReadOnlyTask key) throws UniqueTaskList.TaskNotFoundException, TaskCompletionException {
        if (task.mark(key)) {
            return true;
        } else {
            throw new UniqueTaskList.TaskNotFoundException();
        }
    }
    
    /**
     * Unmark a task in the task list.
     * 
     * @throws TaskNotFoundException if task does not exist.
     */
    public boolean unmarkTask(ReadOnlyTask key) throws UniqueTaskList.TaskNotFoundException, TaskCompletionException {
        if (task.unmark(key)) {
            return true;
        } else {
            throw new UniqueTaskList.TaskNotFoundException();
        }
    }
    
    //@@author A0138516A
    public int insertTask(int targetIndex, Task undoTask) throws TaskNotFoundException{
    	syncTagsWithMasterList(undoTask);
    	return task.insert(targetIndex,undoTask);
    	
	}
    
    //@@author A0138516A
    public int replace(Task beforeEdit, Task afterEdit) throws TaskNotFoundException {
    	syncTagsWithMasterList(afterEdit);
    	return task.replace(beforeEdit,afterEdit);
		
	}
    //@@author
//// tag-level operations

    public void addTag(Tag t) throws UniqueTagList.DuplicateTagException {
        tags.add(t);
    }

//// util methods

    @Override
    public String toString() {
        return task.getInternalList().size() + " task, " + tags.getInternalList().size() +  " tags";
    }

    @Override
    public List<ReadOnlyTask> getTaskList() {
        return Collections.unmodifiableList(task.getInternalList());
    }

    @Override
    public List<Tag> getTagList() {
        return Collections.unmodifiableList(tags.getInternalList());
    }

    @Override
    public UniqueTaskList getUniqueTaskList() {
        return this.task;
    }

    @Override
    public UniqueTagList getUniqueTagList() {
        return this.tags;
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskList // instanceof handles nulls
                && this.task.equals(((TaskList) other).task)
                && this.tags.equals(((TaskList) other).tags));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(task, tags);
    }
}
