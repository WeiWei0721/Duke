package parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import commands.*;
import task.*;
import ui.TextUi;

import static common.Messages.*;


public class Parser {

    public static final Pattern KEYWORDS_ARGS_FORMAT =
            Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)"); // one or more keywords separated by whitespace

    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

//    public static final Pattern ADD_TASK_DATA_ARGS_FORMAT =
//            Pattern.compile("\\b(todo)\\b (?<taskDescription>.*)|\\b(deadline|event)\\b (?<TaskDescription>.*) (\\/(?<taskDate>.*)) ");

    public static final Pattern ADD_DEADLINE_DATA_ARGS_FORMAT =
            Pattern.compile("(?<deadlineDescription>.*) ((\\/by)(?<deadlineDate>.*))");

    public static final Pattern ADD_EVENT_DATA_ARGS_FORMAT =
            Pattern.compile("(?<eventDescription>.*) ((\\/at)/(?<EventDate>.*))");



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

        final String commandWord = matcher.group("commandWord").toUpperCase().trim();
        final String arguments = matcher.group("arguments").trim();

        try{
            commandEnum = CommandEnum.valueOf(commandWord);
        }catch (IllegalArgumentException e){
            return new IncorrectCommand(MESSAGE_INVALID_INPUT);
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
            case DONE:
                return doDoneTaskCommand(arguments);
            case DELETE:
                return doDeleteTaskCommand(arguments);
            case FIND:
                return doPrepareFindTaskCommand(arguments);
            case HELP:
                return doHelpCommand();
            case BYE:
                return new ExitCommand();
            default:
                return new IncorrectCommand(MESSAGE_INVALID_INPUT);
        }

    }

    private Command doPrepareFindTaskCommand(String arguments) {
        final Matcher matcher = KEYWORDS_ARGS_FORMAT.matcher(arguments.trim());
        if(!matcher.matches()){
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,FindCommand.MESSAGE_USAGE));
        }

        //keywords delimited by whitespace
        final String[] keywords = matcher.group("keywords").split("\\s+");
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));

        return new FindCommand(keywordSet);
    }


    private Command doPrintListCommand(){
        return new PrintListCommand();
    }

    private Command doHelpCommand() { return new HelpCommand(); }

    /**
     * Parses arguments in the context of the add person command.
     *
     * @param args full command args string
     * @return the prepared command
     */

    private Command doAddTodoTaskCommand(String argument){
        try{
            System.out.println("arguments: "+argument);
            Task testTask = new Task(argument);
            //testTask.toString();
            System.out.println(testTask.toString());
            return new AddTodoTaskCommand(new Todo(argument));
        }catch (Exception e){
            return new IncorrectCommand(MESSAGE_EMPTY_TASK_DESC);
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

            System.out.println(task + "\n "+ by);

            return new AddDeadlineTaskCommand(new Deadline(task,Parser.parseStringDateTimeFromText(by)));
        }catch (Exception e){
            return new IncorrectCommand(MESSAGE_EMPTY_TASK_DESC);
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

            return new AddEventTaskCommand(new Event(task,Parser.parseStringDateTimeFromText(at)));
        }catch (Exception e){
            return new IncorrectCommand(MESSAGE_EMPTY_TASK_DESC);
        }

    }

    private Command doDoneTaskCommand(String arguments) {
        try{
            //System.out.println("INTO doDoneTaskCommand\n");
            int taskNo = Integer.parseInt(arguments);
            return new DoneTaskCommand(taskNo -1);
        }catch (Exception e){
            return new IncorrectCommand(MESSAGE_TASK_MARK_DONE_FAILED);

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


    public static String parseDateForDisplay (LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hhmm a");
        return dateTime.format(formatter);
    }

    public static String parseDateForStorage (LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        return dateTime.format(formatter);
    }

    public static LocalDateTime parseStringDateTimeFromText(String dataTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        return LocalDateTime.parse(dataTime, formatter);
    }


}
