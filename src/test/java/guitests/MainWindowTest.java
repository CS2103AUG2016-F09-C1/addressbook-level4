package guitests;

import static org.junit.Assert.*;

import org.junit.Test;

import seedu.tasklist.testutil.TypicalTestTasks;

//@@author A0146840E
public class MainWindowTest extends TaskListGuiTest {
       
    @Test
    public void mainMenu_usingF11HotKey_maximizedWindow() {
        mainMenu.toggleFullScreen();
        assertTrue(mainMenu.isFullScreen());
    }
    
    @Test
    public void mainMenu_usingF11HotKey_minimizedWindow() {
        mainMenu.toggleFullScreen();
        mainMenu.toggleFullScreen();
        assertFalse(mainMenu.isFullScreen());
    }
    
    @Test
    public void mainMenu_usingMenu_maximizedWindow() {       
        mainMenu.toggleFullScreenUsingMenu();
        assertTrue(mainMenu.isFullScreen());
    }
    
    @Test
    public void mainMenu_usingMenu_minimizedWindow() {       
        mainMenu.toggleFullScreenUsingMenu();
        assertFalse(mainMenu.isFullScreen());
    }
    
    @Test
    public void mainWindow_usingUpHotKey_displayPreviousCommand() {
        commandBox.runCommand(TypicalTestTasks.task9.getAddCommand());
        mainMenu.pressUpKey();
        assertTrue(commandBox.getCommandInput().equals(TypicalTestTasks.task9.getAddCommand()));
    }
        
    @Test
    public void mainWindow_usingDownHotKey_displayPreviousCommand() {
        commandBox.runCommand(TypicalTestTasks.task8.getAddCommand());
        commandBox.runCommand(TypicalTestTasks.task9.getAddCommand());
        mainMenu.pressUpKey();
        mainMenu.pressDownKey();
        assertTrue(commandBox.getCommandInput().equals(TypicalTestTasks.task8.getAddCommand()));
    }
    
    @Test
    public void mainWindow_usingMenu_displayPreviousCommand() {
        commandBox.runCommand(TypicalTestTasks.task9.getAddCommand());
        mainMenu.selectPreviousCommandUsingMenu();
        assertTrue(commandBox.getCommandInput().equals(TypicalTestTasks.task9.getAddCommand()));
    }
    
}
