package seedu.tasklist.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.tasklist.model.task.ReadOnlyTask;

public class TaskCard extends UiPart{

    private static final String FXML = "TaskListCard.fxml";
    
    private static final String FX_BACKGROUND_PALE_GREEN = "-fx-background-color: #98FB98;";
    private static final String FX_BACKGROUND_PALE_YELLOW = "-fx-background-color: #FFFFCC;";
    private static final String FX_BACKGROUND_PALE_RED = "-fx-background-color: #ff9999;";
    private static final String FX_BACKGROUND_WHITE = "-fx-background-color: white;";
    
    private static final int NOT_REQUIRED = -1;
    
    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label startDateTime;
    @FXML
    private Label endDateTime;
    @FXML
    private Label description;
    @FXML
    private Label tags;

    private ReadOnlyTask task;
    private int displayedIndex;
    private boolean isNotRequired;

    public TaskCard() {

    }

    public static TaskCard load(ReadOnlyTask task, int displayedIndex){
        TaskCard card = new TaskCard();
        card.task = task;
        card.displayedIndex = displayedIndex;
        if (displayedIndex == NOT_REQUIRED) card.isNotRequired = true;
        return UiPartLoader.loadUiPart(card);
    }

    //@@author A0146840E
    @FXML
    public void initialize() {
        initializeId();
        initializeTitle();
        initializeDateTime();
        initializeDescription();
        initializeTags();
        setStyle();
    }
    
    private void initializeId() {
        if (isNotRequired) {
            id.setText(null);
        } else {
            id.setText(displayedIndex + ". ");
        }
    }
    
    private void initializeTitle() {
        name.setText(task.getTitle().fullTitle);
    }
    
    private void initializeDescription() {
        description.setText(task.getDescription().description);
        
        if (task.getDescription().description.equals("") || isNotRequired) {
            description.setManaged(false);
        } else {
            description.setManaged(true);
        }
    }

    private void initializeDateTime() {
        startDateTime.setText("Start:  " + task.getStartDateTime().toString().replaceAll(" ", "    Time:  "));
        
        if (task.getStartDateTime().toString().isEmpty() || (isNotRequired && task.isCompleted())) {
            startDateTime.setManaged(false);
        } else {
            startDateTime.setManaged(true);
        }
        
        endDateTime.setText("End:    " + task.getEndDateTime().toString().replaceAll(" ", "    Time:  "));
        
        if(task.getEndDateTime().toString().isEmpty() || (isNotRequired && task.isCompleted())) {
            endDateTime.setManaged(false);
        } else {
            endDateTime.setManaged(true);
        }
    }
    
    private void initializeTags() {
        tags.setText(task.tagsString());
        if (task.isCompleted()) {
            tags.setManaged(false);
        } else {
            tags.setManaged(true);
        }
    }
    
    public void setStyle() {
        if (task.isCompleted()) {
            cardPane.setStyle(FX_BACKGROUND_PALE_GREEN);
        } else if (task.isFloating()) {
            cardPane.setStyle(FX_BACKGROUND_PALE_YELLOW);
        } else if (task.isOverdue()){
            cardPane.setStyle(FX_BACKGROUND_PALE_RED);
        } else {
            cardPane.setStyle(FX_BACKGROUND_WHITE);
        }
    }
    
    //@@author
    public HBox getLayout() {
        return cardPane;
    }

    @Override
    public void setNode(Node node) {
        cardPane = (HBox)node;
    }

    @Override
    public String getFxmlPath() {
        return FXML;
    }
}
