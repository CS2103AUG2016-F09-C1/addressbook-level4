package seedu.tasklist.ui;

import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.tasklist.commons.core.Config;
import seedu.tasklist.commons.core.GuiSettings;
import seedu.tasklist.commons.events.ui.ExitAppRequestEvent;
import seedu.tasklist.logic.Logic;
import seedu.tasklist.model.UserPrefs;
import seedu.tasklist.model.task.ReadOnlyTask;

/**
 * The Main Window. Provides the basic application layout containing a menu bar
 * and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart {

    private static final String ICON = "/images/tasklist.png";
    private static final String FXML = "MainWindow.fxml";
    public static final int MIN_HEIGHT = 600;
    public static final int MIN_WIDTH = 450;

    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private BrowserPanel browserPanel;
    private TaskListPanel taskListPanel;
    private TaskListPanel mainTaskListPanel;
    private ResultDisplay resultDisplay;
    private StatusBarFooter statusBarFooter;
    private CommandBox commandBox;
    private Config config;
    private UserPrefs userPrefs;

    // Handles to elements of this Ui container
    private VBox rootLayout;
    private Scene scene;

    private String taskListName;

    @FXML
    private AnchorPane browserPlaceholder;

    @FXML
    private AnchorPane commandBoxPlaceholder;

    @FXML
    private AnchorPane taskListPanelPlaceholder;
    
    @FXML
    private AnchorPane mainTaskListPanelPlaceholder;

    @FXML
    private AnchorPane resultDisplayPlaceholder;

    @FXML
    private AnchorPane statusbarPlaceholder;
    
    @FXML
    private MenuItem mainMenuItem, helpMenuItem, commandNextMenuItem, commandPreviousMenuItem, listNextMenuItem, listPreviousMenuItem, listFirstMenuItem, listLastMenuItem;

    public MainWindow() {
        super();
    }

    @Override
    public void setNode(Node node) {
        rootLayout = (VBox) node;
    }

    @Override
    public String getFxmlPath() {
        return FXML;
    }

    public static MainWindow load(Stage primaryStage, Config config, UserPrefs prefs, Logic logic) {
        MainWindow mainWindow = UiPartLoader.loadUiPart(primaryStage, new MainWindow());
        mainWindow.configure(config.getAppTitle(), config.getTaskListName(), config, prefs, logic);
        return mainWindow;
    }

    private void configure(String appTitle, String taskListName, Config config, UserPrefs prefs, Logic logic) {

        // Set dependencies
        this.logic = logic;
        this.taskListName = taskListName;
        this.config = config;
        this.userPrefs = prefs;

        // Configure the UI
        setTitle(appTitle);
        setIcon(ICON);
        setWindowMinSize();
        setWindowDefaultSize(prefs);
        scene = new Scene(rootLayout);
        primaryStage.setScene(scene);

        setAccelerators();
        addEventFilters();
    }

    private void setAccelerators() {
        mainMenuItem.setAccelerator(KeyCombination.valueOf("F11"));
        
        helpMenuItem.setAccelerator(KeyCombination.valueOf("F1"));
        
        commandNextMenuItem.setAccelerator(KeyCombination.valueOf("UP"));
        commandPreviousMenuItem.setAccelerator(KeyCombination.valueOf("DOWN"));
        
        listFirstMenuItem.setAccelerator(KeyCombination.valueOf("Home"));
        listLastMenuItem.setAccelerator(KeyCombination.valueOf("End"));
        listPreviousMenuItem.setAccelerator(KeyCombination.valueOf("Page Up"));
        listNextMenuItem.setAccelerator(KeyCombination.valueOf("Page Down"));
    }

    private void addEventFilters() {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
            Map<KeyCombination,Runnable> accelerators = scene.getAccelerators();
            for (KeyCombination keyCombination : accelerators.keySet()) {
                if (keyCombination.match(event)) {
                    accelerators.get(keyCombination).run();
                    event.consume();
                    return;
                }
            }
        });
    }

    void fillInnerParts() {
        browserPanel = BrowserPanel.load(browserPlaceholder);
        browserPlaceholder.setManaged(false);
        
        taskListPanel = TaskListPanel.load(primaryStage, getTaskListPlaceholder(), logic.getFilteredTaskList(), TaskListPanel.Type.Filtered);
        mainTaskListPanel = TaskListPanel.load(primaryStage, mainTaskListPanelPlaceholder, logic.getMainFilteredTaskList(), TaskListPanel.Type.Main);
        resultDisplay = ResultDisplay.load(primaryStage, getResultDisplayPlaceholder());
        statusBarFooter = StatusBarFooter.load(primaryStage, getStatusbarPlaceholder(), config.getTaskListFilePath());
        commandBox = CommandBox.load(primaryStage, getCommandBoxPlaceholder(), resultDisplay, logic);
    }

    private AnchorPane getCommandBoxPlaceholder() {
        return commandBoxPlaceholder;
    }

    private AnchorPane getStatusbarPlaceholder() {
        return statusbarPlaceholder;
    }

    private AnchorPane getResultDisplayPlaceholder() {
        return resultDisplayPlaceholder;
    }

    public AnchorPane getTaskListPlaceholder() {
        return taskListPanelPlaceholder;
    }

    public void hide() {
        primaryStage.hide();
    }

    private void setTitle(String appTitle) {
        primaryStage.setTitle(appTitle);
    }

    /**
     * Sets the default size based on user preferences.
     */
    protected void setWindowDefaultSize(UserPrefs prefs) {
        primaryStage.setHeight(prefs.getGuiSettings().getWindowHeight());
        primaryStage.setWidth(prefs.getGuiSettings().getWindowWidth());
        if (prefs.getGuiSettings().getWindowCoordinates() != null) {
            primaryStage.setX(prefs.getGuiSettings().getWindowCoordinates().getX());
            primaryStage.setY(prefs.getGuiSettings().getWindowCoordinates().getY());
        }
    }

    private void setWindowMinSize() {
        primaryStage.setMinHeight(MIN_HEIGHT);
        primaryStage.setMinWidth(MIN_WIDTH);
    }

    /**
     * Returns the current size and the position of the main Window.
     */
    public GuiSettings getCurrentGuiSetting() {
        return new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(), (int) primaryStage.getX(),
                (int) primaryStage.getY());
    }

    @FXML
    public void handleHelp() {
        HelpWindow helpWindow = HelpWindow.load(primaryStage);
        helpWindow.show();
    }
    
    /**
     * Scroll to the first task in the list view
     */
    @FXML
    private void handleListPanelSelectFirst() {
        taskListPanel.selectFirst();
    }
    
    /**
     * Scroll to the last task in the list view
     */
    @FXML
    private void handleListPanelSelectLast() {
        taskListPanel.selectLast();
    }
    
    /**
     * Scroll up and select the next task in the list view
     */
    @FXML
    private void handleListPanelSelectPrevious() {
        taskListPanel.selectPrevious();
    }
    
    /**
     * Scroll down and select the next task in the list view
     */
    @FXML
    private void handleListPanelSelectNext() {
        taskListPanel.selectNext();
    }
    
    /**
     * Set the main Window into and out of full screen mode
     */
    @FXML
    private void handleFullScreen() {
        if (primaryStage.isFullScreen()) {
            primaryStage.setFullScreen(false);
        } else {
            primaryStage.setFullScreen(true);
        }
    }

    /**
     * Scroll through the previous commands by pressing the Up Key
     */
    @FXML
    private void handlePreviousCommandTextNext() {
        commandBox.selectPreviousCommandTextNext();
    }

    /**
     * Scroll through the previous commands by pressing the Down Key
     */
    @FXML
    private void handlePreviousCommandTextPrevious() {
        commandBox.selectPreviousCommandTextPrevious();
    }

    public void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        raise(new ExitAppRequestEvent());
    }

    public TaskListPanel getTaskListPanel() {
        return this.taskListPanel;
    }

    public void loadTaskPage(ReadOnlyTask task) {
        taskListPanelPlaceholder.setManaged(false);
        browserPlaceholder.setManaged(true);
        browserPanel.loadTaskPage(task);
    }

    public void releaseResources() {
        browserPanel.freeResources();
    }
}
