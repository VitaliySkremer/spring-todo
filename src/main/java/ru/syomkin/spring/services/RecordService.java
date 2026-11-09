package ru.syomkin.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.syomkin.spring.dao.RecordDao;
import ru.syomkin.spring.entity.Record;
import ru.syomkin.spring.entity.RecordStatus;

import java.util.List;
import java.util.UUID;

@Service
public class RecordService {
    private final RecordDao recordDao;

    @Autowired
    public RecordService(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    public List<Record> findAll() {
        return recordDao.findAll();
    }

    public void addRecord(String title) {
        String titleNormalized = title.trim();

        if (titleNormalized.isEmpty()) return;

        recordDao.addRecord(new Record(titleNormalized));
    }

    public void makeRecordDone(UUID id) {
        recordDao.makeRecordStatus(id, RecordStatus.DONE);
    }

    public void deleteRecord(UUID id) {
        recordDao.deleteRecord(id);
    }
}
