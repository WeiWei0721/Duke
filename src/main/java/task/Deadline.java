package task;

import parser.Parser;

import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;
    protected String displayDateTime;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    public LocalDateTime getBy() {

        return by;
    }

    public String getDisplayDateTime() {
        return this.displayDateTime = Parser.parseDateForDisplay(by);
    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + " (by: " + getDisplayDateTime() + ")";
    }
}
