package guitests;

import static org.junit.Assert.*;

import org.junit.Test;

//@@author A0146840E
public class MaximizedWindowTest extends TaskListGuiTest {

    @Test
    public void mainMenu_maximizedWindow() {
        mainMenu.toggleFullScreen();
        assertTrue(mainMenu.isFullScreen());
    }
    
    @Test
    public void mainMenu_minimizedWindow() {
        mainMenu.toggleFullScreen();
        mainMenu.toggleFullScreen();
        assertFalse(mainMenu.isFullScreen());
    }
    
}
