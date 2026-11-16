package ru.syomkin.spring.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import ru.syomkin.spring.entity.Record;
import ru.syomkin.spring.entity.RecordStatus;

import java.util.*;

@Repository
public class RecordDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Record> findAll() {
        Query query = entityManager.createQuery("SELECT r FROM Record r ORDER BY r.id ASC");
        List<Record> records = query.getResultList();

        return records;
    }

    public void addRecord(Record record) {
        entityManager.persist(record);
    }

    public void makeRecordStatus(int id, RecordStatus newStatus) {
        Query query = entityManager.createQuery("UPDATE Record r SET r.status = :newStatus WHERE r.id = :id");
        query.setParameter("newStatus", newStatus);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public void deleteRecord(int id) {
        Query query = entityManager.createQuery("DELETE FROM Record r WHERE r.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
