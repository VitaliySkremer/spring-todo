package ru.syomkin.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.syomkin.spring.dao.RecordDao;
import ru.syomkin.spring.entity.Record;
import ru.syomkin.spring.entity.RecordStatus;
import ru.syomkin.spring.entity.dto.RecordsContainerDto;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RecordService {
    private final RecordDao recordDao;

    @Autowired
    public RecordService(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    public RecordsContainerDto findAll(String filter) {
        List<Record> recordList = recordDao.findAll();

        int numberOfDoneRecords = 0;
        int numberOfActiveRecords = 0;

        for (Record record : recordList) {
            if (record.getStatus().equals(RecordStatus.DONE)) {
                numberOfDoneRecords++;
            } else {
                numberOfActiveRecords++;
            }
        }

        if (filter == null || filter.isBlank())
            return new RecordsContainerDto(recordList, numberOfDoneRecords, numberOfActiveRecords);

        String filterInUpperCasse = filter.toUpperCase();
        List<String> allowedFilter = Arrays
                .stream(RecordStatus.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        if (!allowedFilter.contains(filterInUpperCasse))
            return new RecordsContainerDto(recordList, numberOfDoneRecords, numberOfActiveRecords);

        List<Record> filteredRecordList = recordList
                .stream()
                .filter(item -> item.getStatus() == RecordStatus.valueOf(filterInUpperCasse))
                .collect(Collectors.toList());

        return new RecordsContainerDto(filteredRecordList, numberOfDoneRecords, numberOfActiveRecords);
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
