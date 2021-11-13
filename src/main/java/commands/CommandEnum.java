package commands;

import java.util.Arrays;

/**
 * The enum mapping relationships of command keywords with Command services
 */
public enum CommandEnum {
    LIST("list", new PrintListCommand()),
    DONE("done", new DoneTaskCommand()),
    TODO("todo", new AddTodoTaskCommand()),
    DEADLINE("deadline", new AddDeadlineTaskCommand()),
    EVENT("event", new AddEventTaskCommand()),
    DELETE("delete", new DeleteTaskCommand()),
    HELP("help", new HelpCommand()),
    FIND("find", new FindCommand()),
    BYE("bye", new ExitCommand()),
    FORMAT("format", new FormatCommand()),
    FINDANY("findany", new FindAnyCommand()),
    ;
    private final String key;
    private final Command command;

    CommandEnum(String key, Command command) {
        this.key = key;
        this.command = command;
    }

    public String getKey() {
        return key;
    }

    public Command getCommand() {
        return command;
    }

    public static Command getService(String key) {
        CommandEnum anEnum = Arrays.stream(CommandEnum.values())
                .filter(item -> key.equals(item.getKey()))
                .findAny()
                .orElse(null);
        if (anEnum == null) {
            return null;
        }
        return anEnum.getCommand();
    }
}