package com.weiwei.parser;


import com.weiwei.commands.Command;
import com.weiwei.commands.CommandEnum;
import com.weiwei.exception.BusinessException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.weiwei.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static com.weiwei.common.Messages.MESSAGE_INVALID_INPUT;

public class Parser {

    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parserCommand(String userInput) throws BusinessException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new BusinessException(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        final String commandWord = matcher.group("commandWord").toLowerCase().trim();
        final String arguments = matcher.group("arguments").trim();

        Command command = CommandEnum.getService(commandWord);
        if (command == null) {
            throw new BusinessException(MESSAGE_INVALID_INPUT);
        }
        command.setArguments(arguments);

        return command;
    }

}
