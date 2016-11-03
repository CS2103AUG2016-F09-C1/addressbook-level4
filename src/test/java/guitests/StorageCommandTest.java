package guitests;

import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.junit.Test;

import seedu.tasklist.commons.core.Config;
import seedu.tasklist.commons.core.Messages;
import seedu.tasklist.commons.exceptions.DataConversionException;
import seedu.tasklist.commons.util.ConfigUtil;
import seedu.tasklist.logic.commands.StorageCommand;
import seedu.tasklist.storage.Storage;

//@@author A0138516A
public class StorageCommandTest extends TaskListGuiTest {

	private String testStorageCommandFilePath = "src/test/data/StorageCommandTestFolder/testTaskList.xml";
	private String defaultConfigFile = "TestConfig.json";
	private String testFileName = "testTaskList.xml";

	private Config Config = assertFilePathChange(defaultConfigFile);

	@Test
	// Change File Path
	public void storage_changePath() {
		commandBox.runCommand("check 1 ");
		commandBox.runCommand("storage " + testStorageCommandFilePath);
		assertTrue(getPath(testStorageCommandFilePath).equals(testFileName));
	}

	@Test
	// Invalid Command
	public void storage_UnknownCommand() {
		commandBox.runCommand("check 1 ");
		commandBox.runCommand("storages docs/");
		assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
	}

	@Test
	// Invalid file Path
	public void storage_InvalidPath() {
		commandBox.runCommand("check 1 ");
		commandBox.runCommand("storage doc");
		assertResultMessage(StorageCommand.MESSAGE_FILE_PATH_NOT_EXIST);
	}

	public String getPath(String filepath) {
		File file = new File(filepath);
		String fileName = file.getName().toString();
		return fileName;
	}

	private Config assertFilePathChange(String filepath) {
		Config initialTestConfig;
		String configFilepathUsed = defaultConfigFile;

		if (filepath != null) {
			configFilepathUsed = filepath;
		}

		try {
			Optional<Config> configOptional = ConfigUtil.readConfig(configFilepathUsed);
			initialTestConfig = configOptional.orElse(new Config());
		} catch (DataConversionException e) {
			initialTestConfig = new Config();
		}

		// Save config file
		try {
			ConfigUtil.saveConfig(initialTestConfig, configFilepathUsed);
		} catch (IOException e) {
		}
		return initialTestConfig;
	}

}