package ru.syomkin.spring.dao;

import org.springframework.stereotype.Repository;
import ru.syomkin.spring.entity.Record;
import ru.syomkin.spring.entity.RecordStatus;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
public class RecordDao {
    private final List<Record> records = new ArrayList<>(
            Arrays.asList(
                    new Record("Take a shower", RecordStatus.ACTIVE),
                    new Record("Buy flowers", RecordStatus.DONE),
                    new Record("Go to the gym", RecordStatus.ACTIVE)
            )
    );

    public List<Record> findAll() {
        return new ArrayList<>(records);
    }

    public void addRecord(Record record) {
        this.records.add(record);
    }

    public void makeRecordStatus(UUID id, RecordStatus newStatus) {
        for (Record record : records) {
            if (record.getId().equals(id)) {
                record.setStatus(newStatus);
                break;
            }
        }
    }

    public void deleteRecord(UUID id) {
        records.removeIf(record -> record.getId().equals(id));
    }
}
