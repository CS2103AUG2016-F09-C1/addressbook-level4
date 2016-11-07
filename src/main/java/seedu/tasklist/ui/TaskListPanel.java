package seedu.tasklist.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.tasklist.commons.core.LogsCenter;
import seedu.tasklist.commons.events.ui.TaskPanelSelectionChangedEvent;
import seedu.tasklist.model.task.ReadOnlyTask;

import java.util.logging.Logger;

/**
 * Panel containing the list of tasks.
 */
public class TaskListPanel extends UiPart {
    private final Logger logger = LogsCenter.getLogger(TaskListPanel.class);
    private static final String FXML = "TaskListPanel.fxml";
    private VBox panel;
    private AnchorPane placeHolderPane;
    
    private int scrollIndex;
    private int taskListSize;

    public enum Type {
        FILTERED_TASKLIST, MAIN_TASKLIST;
    }
    
    private TaskListPanel.Type type;
    
    @FXML
    private ListView<ReadOnlyTask> taskListView;

    public TaskListPanel() {
        super();
    }

    @Override
    public void setNode(Node node) {
        panel = (VBox) node;
    }

    @Override
    public String getFxmlPath() {
        return FXML;
    }

    @Override
    public void setPlaceholder(AnchorPane pane) {
        this.placeHolderPane = pane;
    }

    public static TaskListPanel load(Stage primaryStage, AnchorPane taskListPlaceholder,
            ObservableList<ReadOnlyTask> taskList, TaskListPanel.Type type) {
        TaskListPanel taskListPanel = UiPartLoader.loadUiPart(primaryStage, taskListPlaceholder, new TaskListPanel());
        taskListPanel.type = type;
        taskListPanel.configure(taskList);
        return taskListPanel;
    }

    private void configure(ObservableList<ReadOnlyTask> taskList) {
        setConnections(taskList);
        addToPlaceholder();
    }

    private void setConnections(ObservableList<ReadOnlyTask> taskList) {
        taskListView.setItems(taskList);
        taskListView.setCellFactory(listView -> new TaskListViewCell());
        taskListSize = taskList.size();
        setEventHandlerForSelectionChangeEvent();
    }

    private void addToPlaceholder() {
        SplitPane.setResizableWithParent(placeHolderPane, false);
        placeHolderPane.getChildren().add(panel);
    }

    private void setEventHandlerForSelectionChangeEvent() {
        taskListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                logger.fine("Selection in task list panel changed to : '" + newValue + "'");
                raise(new TaskPanelSelectionChangedEvent(newValue));
            }
        });
    }

    //@@author A0146840E
    public void scrollTo(int index) {
        taskListView.scrollTo(index);
    }

    public void scrollToPrevious() {
        if (scrollIndex > 0) {
            scrollTo(--scrollIndex);
        }
    }

    public void scrollToNext() {
        if (scrollIndex < taskListSize - 1) {
            scrollTo(++scrollIndex);
        }
    }
    
    public void scrollToLast() {
        scrollIndex = taskListSize - 1;
        scrollTo(scrollIndex);
    }
    
    public void scrollToFirst() {
        scrollIndex = 0;
        scrollTo(scrollIndex);
    }

    class TaskListViewCell extends ListCell<ReadOnlyTask> {

        public TaskListViewCell() {
        }

        @Override
        protected void updateItem(ReadOnlyTask task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null ) {
                setGraphic(null);
                setText(null);
            } else if (type == TaskListPanel.Type.FILTERED_TASKLIST) {
                setGraphic(TaskCard.load(task, getIndex() + 1).getLayout());
            } else if (type == TaskListPanel.Type.MAIN_TASKLIST) {
                setGraphic(TaskCard.load(task, -1).getLayout());
            }
        }
    }
    
    public void setTaskListSize(int size) {
        taskListSize = size;
    }
    
    //@@author
    public ListView<ReadOnlyTask> getTaskListView() {
        return taskListView;
    }
}
