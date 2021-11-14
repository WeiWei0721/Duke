package com.weiwei.task;


import com.weiwei.util.DateUtil;

import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
        this.type = "E";
    }


    public LocalDateTime getStart() {

        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public String getAt() {
        return DateUtil.parseDateForDisplay(getStart()) + " - " + DateUtil.parseDateForDisplay(getEnd());
    }

    @Override
    public String toString() {

        return "[E]" + super.toString() + " (at: " + getAt() + ")";
    }

}
