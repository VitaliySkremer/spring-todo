package ru.syomkin.spring.entity;

public class Record {
    private final String title;
    private RecordStatus status;

    public Record(String title, RecordStatus status) {
        this.title = title;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public RecordStatus getStatus() {
        return status;
    }

    public void setStatus(RecordStatus status) {
        this.status = status;
    }
}
