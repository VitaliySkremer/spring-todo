package ru.syomkin.spring.dao;

import org.springframework.stereotype.Repository;
import ru.syomkin.spring.entity.Record;
import ru.syomkin.spring.entity.RecordStatus;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}
