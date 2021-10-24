package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import commands.*;
import task.*;

import static common.Messages.*;


public class Parser {

    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWOrd>\\S+)(?<arguments>.*)");

    public static final Pattern ADD_TASK_DATA_ARGS_FORMAT =
            Pattern.compile("\\b(todo)\\b (?<taskDescription>.*)|\\b(deadline|event)\\b (?<TaskDescription>.*) (\\/(?<taskDate>.*)) ");

    public static final Pattern ADD_DEADLINE_DATA_ARGS_FORMAT =
            Pattern.compile("(?<deadlineDescription>.*) (\\/(?<deadlineDate>.*))");

    public static final Pattern ADD_EVENT_DATA_ARGS_FORMAT =
            Pattern.compile("(?<eventDescription>.*) (\\/(?<EventDate>.*))");



    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parserCommand(String userInput){
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if(!matcher.matches()){
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        CommandEnum commandEnum = null;

        final String commandWrod = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        try{
            commandEnum = CommandEnum.valueOf(commandWrod);
        }catch (IllegalArgumentException e){
            return new IncorrectCommand("Incorrect Command: " + commandWrod);
        }

        switch (commandEnum){
            case LIST:
                return doPrintListCommand();
            case TODO:
                return doAddTodoTaskCommand(arguments);
            case DEADLINE:
                return doAddDeadlineTaskCommand(arguments);
            case EVENT:
                return doAddEventTaskCommand(arguments);
            case DELETE:
                return doDeleteTaskCommand(arguments);
            case BYE:
                return new ExitCommand();
            default:
                return new IncorrectCommand(MESSAGE_INVALID_INPUT);
        }

    }

    /**
     * Parses arguments in the context of the add person command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command doPrintListCommand(){
        return new PrintListCommand();
    }

    private Command doAddTodoTaskCommand(String argument){
        try{
            return new AddTodoTaskCommand(new Todo(argument));
        }catch (Exception e){
            return new IncorrectCommand("Task unable to be added.");
        }
    }

    private Command doAddDeadlineTaskCommand(String arguments) {
        try{
            final Matcher matcher = ADD_DEADLINE_DATA_ARGS_FORMAT.matcher(arguments.trim());
            if(!matcher.matches()){
                return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
            }

            String task = matcher.group("deadlineDescription").trim();
            String by = matcher.group("deadlineDate").trim();

            return new AddDeadlineTaskCommand(new Deadline(task,by));
        }catch (Exception e){
            return new IncorrectCommand("Task unable to be added.");
        }
    }


    private Command doAddEventTaskCommand(String arguments) {
        try{
            final Matcher matcher = ADD_EVENT_DATA_ARGS_FORMAT.matcher(arguments.trim());
            if(!matcher.matches()){
                return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
            }

            String task = matcher.group("eventDescription").trim();
            String at = matcher.group("EventDate").trim();

            return new AddEventTaskCommand(new Event(task,at));
        }catch (Exception e){
            return new IncorrectCommand("Task unable to be added.");
        }

    }

    private Command doDeleteTaskCommand(String arguments) {
        try{
            int targetIndex = Integer.parseInt(arguments);
            return new DeleteTaskCommand(targetIndex);
        }catch (NumberFormatException nfe){
            return new IncorrectCommand(MESSAGE_INVALID_TASK_INDEX);
        }
    }


}
