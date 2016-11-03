package guitests.guihandles;

import guitests.GuiRobot;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import seedu.tasklist.TestApp;

import java.util.Arrays;

/**
 * Provides a handle to the main menu of the app.
 */
public class MainMenuHandle extends GuiHandle {
    public MainMenuHandle(GuiRobot guiRobot, Stage primaryStage) {
        super(guiRobot, primaryStage, TestApp.APP_TITLE);
    }

    public GuiHandle clickOn(String... menuText) {
        Arrays.stream(menuText).forEach((menuItem) -> guiRobot.clickOn(menuItem));
        return this;
    }

    public HelpWindowHandle openHelpWindowUsingMenu() {
        clickOn("Help", "F1");
        return new HelpWindowHandle(guiRobot, primaryStage);
    }

    public HelpWindowHandle openHelpWindowUsingAccelerator() {
        useF1Accelerator();
        return new HelpWindowHandle(guiRobot, primaryStage);
    }

    private void useF1Accelerator() {
        guiRobot.push(KeyCode.F1);
        guiRobot.sleep(500);
    }
    
    //@@author A0146840E
    public void toggleFullScreenUsingMenu() {
        clickOn("File", "FullScreen");
        guiRobot.sleep(500);
    }
    
    public void toggleFullScreen() {
        useF11Accelerator();
    }
    
    public boolean isFullScreen() {
        return primaryStage.isFullScreen();
    }
    
    private void useF11Accelerator() {
        guiRobot.push(KeyCode.F11);
        guiRobot.sleep(500);
    }
    
    public void selectPreviousCommandUsingMenu() {
        clickOn("Navigate", "Commands", "Show Previous Command");
        guiRobot.sleep(500);
    }
    
    public void pressUpKey() {
        useUpAccelerator();
    }
    
    private void useUpAccelerator() {
        guiRobot.push(KeyCode.UP);
        guiRobot.sleep(500);
    }
    
    public void pressDownKey() {
        useDownAccelerator();
    }
    
    private void useDownAccelerator() {
        guiRobot.push(KeyCode.DOWN);
        guiRobot.sleep(500);
    }
    
}
