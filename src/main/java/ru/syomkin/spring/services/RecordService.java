package ru.syomkin.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.syomkin.spring.dao.RecordDao;
import ru.syomkin.spring.entity.Record;

import java.util.List;

@Service
public class RecordService {
    private final RecordDao recordDao;

    @Autowired
    public RecordService(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    public List<Record> findAll() {
        return  recordDao.findAll();
    }
}
