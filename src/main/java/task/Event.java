package task;

import parser.Parser;

import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime at;
    protected String displayDateTime;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
        this.type = "E";
    }

    public LocalDateTime getAt() {
        return at;
    }

    public String getDisplayDateTime(){
        return this.displayDateTime = Parser.parseDateForDisplay(at);
    }

    @Override
    public String toString() {

        return "[E]" + super.toString() + " (at: " + getDisplayDateTime() + ")";
    }

}
