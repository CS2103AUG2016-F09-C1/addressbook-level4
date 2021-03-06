package seedu.tasklist.logic.parser;

import seedu.tasklist.commons.core.Messages;
import seedu.tasklist.logic.commands.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@@author A0146840E
/**
 * Parses user input.
 */
public class Parser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());

        CommandParser incorrectCommandParser = new IncorrectCommand();
        
        if (!matcher.matches()) {
            return incorrectCommandParser.prepare(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        Object object = null;
        try {
            Class<?> classDefinition = Class.forName("seedu.tasklist.logic.commands."
                    + commandWord.substring(0, 1).toUpperCase() + commandWord.substring(1).toLowerCase() + "Command");
            object = classDefinition.newInstance();
            
            return ((CommandParser) object).prepare(arguments);
        } catch (Exception e) {
            return incorrectCommandParser.prepare(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

}