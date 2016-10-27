package seedu.tasklist.ui;

import com.google.common.eventbus.Subscribe;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.StatusBar;

import seedu.tasklist.commons.core.LogsCenter;
import seedu.tasklist.commons.events.model.TaskListChangedEvent;
import seedu.tasklist.commons.events.storage.ChangePathEvent;
import seedu.tasklist.commons.util.FxViewUtil;
import seedu.tasklist.model.task.ReadOnlyTask;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class StatusBarFooter extends UiPart {
    private static final Logger logger = LogsCenter.getLogger(StatusBarFooter.class);
    private StatusBar syncStatus;
    private StatusBar saveLocationStatus;

    private GridPane mainPane;

    @FXML
    private AnchorPane saveLocStatusBarPane;

    @FXML
    private AnchorPane syncStatusBarPane;
    
    @FXML
    private StackPane progressBarPane;

    private AnchorPane placeHolder;
    
    private ProgressBar progressbar;
    
    private Text text;

    private static final String FXML = "StatusBarFooter.fxml";

    public static StatusBarFooter load(Stage stage, AnchorPane placeHolder, String saveLocation, ObservableList<ReadOnlyTask> taskList) {
        StatusBarFooter statusBarFooter = UiPartLoader.loadUiPart(stage, placeHolder, new StatusBarFooter());
        statusBarFooter.configure(saveLocation, taskList);
        return statusBarFooter;
    }

    public void configure(String saveLocation, ObservableList<ReadOnlyTask> taskList) {
        addMainPane();
        addSyncStatus();
        setSyncStatus("Not updated yet in this session");
        addSaveLocation();
        setSaveLocation("./" + saveLocation);
        registerAsAnEventHandler(this);
        addProgressBar(taskList);
    }
    
    //@@author A0140019W
    private void addProgressBar(ObservableList<ReadOnlyTask> taskList) {
        text = new Text("");
        text.setFont(Font.font("Arial", 12));
        
        progressbar = new ProgressBar(0.0);
        progressbar.prefWidthProperty().bind(progressBarPane.widthProperty());
        progressbar.getStyleClass().add("bar");
        progressBarPane.getChildren().addAll(progressbar, text);
        progressBarStatus(taskList);
    }
    //@@author
    
    private void addMainPane() {
        FxViewUtil.applyAnchorBoundaryParameters(mainPane, 0.0, 0.0, 0.0, 0.0);
        placeHolder.getChildren().add(mainPane);
    }

    private void setSaveLocation(String location) {
        this.saveLocationStatus.setText(location);
    }

    private void addSaveLocation() {
        this.saveLocationStatus = new StatusBar();
        FxViewUtil.applyAnchorBoundaryParameters(saveLocationStatus, 0.0, 0.0, 0.0, 0.0);
        saveLocStatusBarPane.getChildren().add(saveLocationStatus);
    }

    private void setSyncStatus(String status) {
        this.syncStatus.setText(status);
    }

    private void addSyncStatus() {
        this.syncStatus = new StatusBar();
        FxViewUtil.applyAnchorBoundaryParameters(syncStatus, 0.0, 0.0, 0.0, 0.0);
        syncStatusBarPane.getChildren().add(syncStatus);
    }
    
    //@@author A0140019W
    private void progressBarStatus(List<ReadOnlyTask> tasklist) {
        double count = 0;
        for (ReadOnlyTask readOnlyTask : tasklist) {
            if(readOnlyTask.isCompleted()) {
                count++;
            }
        }
        text.setText((int)count + " / " + tasklist.size());
        progressbar.setProgress(count/tasklist.size());
    }
    //@@author

    @Override
    public void setNode(Node node) {
        mainPane = (GridPane) node;
    }

    @Override
    public void setPlaceholder(AnchorPane placeholder) {
        this.placeHolder = placeholder;
    }

    @Override
    public String getFxmlPath() {
        return FXML;
    }

    @Subscribe
    public void handleTaskListChangedEvent(TaskListChangedEvent tlce) {
        String lastUpdated = (new Date()).toString();
        logger.info(LogsCenter.getEventHandlingLogMessage(tlce, "Setting last updated status to " + lastUpdated));
        setSyncStatus("Last Updated: " + lastUpdated);
        progressBarStatus(tlce.data.getTaskList());
    }
    
    //@@author A0138516A
    /**To update new file path on the UI**/
    @Subscribe
    public void handleUpdatedFilePathEvent(ChangePathEvent event) {
    	setSaveLocation("./"+event.toString());
    }
    //@@author 
    
}
