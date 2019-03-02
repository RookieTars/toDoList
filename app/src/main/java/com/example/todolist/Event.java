package com.example.todolist;

import org.litepal.crud.DataSupport;

public class Event extends DataSupport {
    private int id;
    private String eventTitle;
    private String eventContent;
    private int eventGrade;
    private boolean finished;

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventContent() {
        return eventContent;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    public int getEventGrade() {
        return eventGrade;
    }

    public void setEventGrade(int eventGrade) {
        this.eventGrade = eventGrade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
