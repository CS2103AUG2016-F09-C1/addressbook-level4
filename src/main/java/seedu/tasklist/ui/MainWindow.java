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
    private TaskListPanel filteredTaskListPanel;
    private TaskListPanel mainTaskListPanel;
    private ResultDisplay resultDisplay;
    private StatusBarFooter statusBarFooter;
    private CommandBox commandBox;
    private Config config;
    // Handles to elements of this Ui container
    private VBox rootLayout;
    private Scene scene;

    @FXML
    private AnchorPane commandBoxPlaceholder;

    @FXML
    private AnchorPane filteredTaskListPanelPlaceholder;
    
    @FXML
    private AnchorPane mainTaskListPanelPlaceholder;

    @FXML
    private AnchorPane resultDisplayPlaceholder;

    @FXML
    private AnchorPane statusbarPlaceholder;
    
    @FXML
    private MenuItem mainMenuItem, helpMenuItem,
                        commandNextMenuItem, commandPreviousMenuItem, 
                        filteredListNextMenuItem, filteredListPreviousMenuItem, filteredListFirstMenuItem, filteredListLastMenuItem,
                        mainListNextMenuItem, mainListPreviousMenuItem, mainListFirstMenuItem, mainListLastMenuItem;

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
        this.config = config;
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

    //@@author A0146840E
    private void setAccelerators() {
        mainMenuItem.setAccelerator(KeyCombination.valueOf("F11"));
        
        helpMenuItem.setAccelerator(KeyCombination.valueOf("F1"));
        
        commandNextMenuItem.setAccelerator(KeyCombination.valueOf("UP"));
        commandPreviousMenuItem.setAccelerator(KeyCombination.valueOf("DOWN"));
        
        filteredListFirstMenuItem.setAccelerator(KeyCombination.valueOf("Home"));
        filteredListLastMenuItem.setAccelerator(KeyCombination.valueOf("End"));
        filteredListPreviousMenuItem.setAccelerator(KeyCombination.valueOf("Page Up"));
        filteredListNextMenuItem.setAccelerator(KeyCombination.valueOf("Page Down"));
        
        mainListFirstMenuItem.setAccelerator(KeyCombination.valueOf("Ctrl+Home"));
        mainListLastMenuItem.setAccelerator(KeyCombination.valueOf("Ctrl+End"));
        mainListPreviousMenuItem.setAccelerator(KeyCombination.valueOf("Ctrl+Page Up"));
        mainListNextMenuItem.setAccelerator(KeyCombination.valueOf("Ctrl+Page Down"));
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

    public void fillInnerParts() {      
        mainTaskListPanel = TaskListPanel.load(primaryStage, mainTaskListPanelPlaceholder, logic.getMainFilteredTaskList(), TaskListPanel.Type.MAIN_TASKLIST);
        filteredTaskListPanel = TaskListPanel.load(primaryStage, getTaskListPlaceholder(), logic.getFilteredTaskList(), TaskListPanel.Type.FILTERED_TASKLIST);
        resultDisplay = ResultDisplay.load(primaryStage, getResultDisplayPlaceholder());
        statusBarFooter = StatusBarFooter.load(primaryStage, getStatusbarPlaceholder(), config.getTaskListFilePath(), logic.getMainFilteredTaskList());
        commandBox = CommandBox.load(primaryStage, getCommandBoxPlaceholder(), resultDisplay, logic);
    }

    //@@author
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
        return filteredTaskListPanelPlaceholder;
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
    
    //@@author A0146840E
    /**
     * Scroll to the first task in the main tasks list view
     */
    @FXML
    private void handleMainListPanelScrollToFirst() {
        mainTaskListPanel.scrollToFirst();
    }
    
    /**
     * Scroll to the last task in the main tasks list view
     */
    @FXML
    private void handleMainListPanelScrollToLast() {
        mainTaskListPanel.scrollToLast();
    }
    
    /**
     * Scroll up in the main tasks list view
     */
    @FXML
    private void handleMainListPanelScrollUp() {
        mainTaskListPanel.scrollToPrevious();
    }
    
    /**
     * Scroll down in the main tasks list view
     */
    @FXML
    private void handleMainListPanelScrollDown() {
        mainTaskListPanel.scrollToNext();
    }
    
    /**
     * Scroll to the first task in the filtered tasks list view
     */
    @FXML
    private void handleFilteredListPanelScrollToFirst() {
        filteredTaskListPanel.scrollToFirst();
    }
    
    /**
     * Scroll to the last task in the filtered tasks list view
     */
    @FXML
    private void handleFilteredListPanelScrollToLast() {
        filteredTaskListPanel.scrollToLast();
    }
    
    /**
     * Scroll up in the filtered tasks list view
     */
    @FXML
    private void handleFilteredListPanelScrollUp() {
        filteredTaskListPanel.scrollToPrevious();
    }
    
    /**
     * Scroll down in the filtered tasks list view
     */
    @FXML
    private void handleFilteredListPanelScrollDown() {
        filteredTaskListPanel.scrollToNext();
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

    //@@author
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
        return this.filteredTaskListPanel;
    }

}
