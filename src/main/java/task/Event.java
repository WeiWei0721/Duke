package task;

import commands.FormatCommand;
import parser.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
        this.type = "E";
    }



    public LocalDateTime getStart(){

        return start;
    }

    public LocalDateTime getEnd(){
        return end;
    }

    public String getAt() {
        return Parser.parseDateForDisplay(getStart()) + " - " + Parser.parseDateForDisplay(getEnd());
    }

    @Override
    public String toString() {

        return "[E]" + super.toString() + " (at: " + getAt() + ")";
    }

}
