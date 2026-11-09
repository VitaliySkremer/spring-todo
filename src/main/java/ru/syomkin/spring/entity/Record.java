package ru.syomkin.spring.entity;

import java.util.UUID;

public class Record {
    private final UUID id;
    private final String title;
    private RecordStatus status;

    public Record(String title) {
        id = UUID.randomUUID();
        this.title = title;
        this.status = RecordStatus.ACTIVE;
    }

    public Record(String title, RecordStatus status) {
        id = UUID.randomUUID();
        this.title = title;
        this.status = status;
    }

    public UUID getId() {
        return id;
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
