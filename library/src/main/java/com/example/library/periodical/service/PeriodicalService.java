package com.example.library.periodical.service;

import com.example.library.periodical.entity.PeriodicalAcceptanceRecord;
import com.example.library.periodical.entity.PeriodicalBindingRecord;
import com.example.library.periodical.entity.PeriodicalCatalogEntry;
import com.example.library.periodical.entity.PeriodicalInterviewRecord;
import com.example.library.periodical.entity.PeriodicalOrder;
import com.example.library.periodical.entity.PeriodicalVisitRecord;
import com.example.library.periodical.repository.PeriodicalAcceptanceRecordRepository;
import com.example.library.periodical.repository.PeriodicalBindingRecordRepository;
import com.example.library.periodical.repository.PeriodicalCatalogEntryRepository;
import com.example.library.periodical.repository.PeriodicalInterviewRecordRepository;
import com.example.library.periodical.repository.PeriodicalOrderRepository;
import com.example.library.periodical.repository.PeriodicalVisitRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class PeriodicalService {

    private final PeriodicalVisitRecordRepository visitRecordRepository;
    private final PeriodicalOrderRepository orderRepository;
    private final PeriodicalInterviewRecordRepository interviewRecordRepository;
    private final PeriodicalAcceptanceRecordRepository acceptanceRecordRepository;
    private final PeriodicalBindingRecordRepository bindingRecordRepository;
    private final PeriodicalCatalogEntryRepository catalogEntryRepository;

    public PeriodicalService(PeriodicalVisitRecordRepository visitRecordRepository,
                             PeriodicalOrderRepository orderRepository,
                             PeriodicalInterviewRecordRepository interviewRecordRepository,
                             PeriodicalAcceptanceRecordRepository acceptanceRecordRepository,
                             PeriodicalBindingRecordRepository bindingRecordRepository,
                             PeriodicalCatalogEntryRepository catalogEntryRepository) {
        this.visitRecordRepository = visitRecordRepository;
        this.orderRepository = orderRepository;
        this.interviewRecordRepository = interviewRecordRepository;
        this.acceptanceRecordRepository = acceptanceRecordRepository;
        this.bindingRecordRepository = bindingRecordRepository;
        this.catalogEntryRepository = catalogEntryRepository;
    }

    public List<PeriodicalVisitRecord> listVisits() {
        return visitRecordRepository.findAll();
    }

    public List<PeriodicalOrder> listOrders() {
        return orderRepository.findAll();
    }

    public List<PeriodicalInterviewRecord> listInterviewRecords() {
        return interviewRecordRepository.findAll();
    }

    public List<PeriodicalAcceptanceRecord> listAcceptanceRecords() {
        return acceptanceRecordRepository.findAll();
    }

    public List<PeriodicalBindingRecord> listBindingRecords() {
        return bindingRecordRepository.findAll();
    }

    public List<PeriodicalCatalogEntry> listCatalogEntries() {
        return catalogEntryRepository.findAll();
    }

    public void addVisitRecord(String title, String issn, String recommender, String recommendDate, String reason) {
        String visitId = generateVisitId();
        Date date = Date.valueOf(recommendDate);
        PeriodicalVisitRecord record = new PeriodicalVisitRecord(visitId, title, issn, recommender, date, reason);
        visitRecordRepository.save(record);
    }

    public void addOrder(String title, String issn, String supplier, Integer quantity, Double unitPrice, String orderDate) {
        String orderId = generateOrderId();
        Date date = Date.valueOf(orderDate);
        PeriodicalOrder order = new PeriodicalOrder(orderId, title, issn, supplier, quantity, unitPrice, date, "已下单");
        orderRepository.save(order);
    }

    public void addInterviewRecord(String title, String issn, String publisher, String interviewer, String interviewDate, String notes) {
        String interviewId = generateInterviewId();
        Date date = Date.valueOf(interviewDate);
        PeriodicalInterviewRecord record = new PeriodicalInterviewRecord(interviewId, title, issn, publisher, interviewer, date, notes);
        interviewRecordRepository.save(record);
    }

    public void addAcceptanceRecord(String title, String issn, String publisher, Integer receivedQuantity, String checker, String acceptanceDate, String status) {
        String acceptanceId = generateAcceptanceId();
        Date date = Date.valueOf(acceptanceDate);
        PeriodicalAcceptanceRecord record = new PeriodicalAcceptanceRecord(acceptanceId, title, issn, publisher, receivedQuantity, checker, date, status);
        acceptanceRecordRepository.save(record);
    }

    @Transactional
    public void addBindingRecordAndCatalog(String title, String issn, String volumeInfo, String binder, String bindDate, String shelfLocation) {
        String bindId = generateBindId();
        Date date = Date.valueOf(bindDate);
        PeriodicalBindingRecord record = new PeriodicalBindingRecord(bindId, title, issn, volumeInfo, binder, date, shelfLocation);
        bindingRecordRepository.save(record);

        String catalogId = generateCatalogId();
        PeriodicalCatalogEntry entry = new PeriodicalCatalogEntry(catalogId, title, issn, volumeInfo, date, shelfLocation);
        catalogEntryRepository.save(entry);
    }

    private String generateVisitId() {
        long count = visitRecordRepository.count() + 1;
        return String.format("PV%06d", count);
    }

    private String generateOrderId() {
        long count = orderRepository.count() + 1;
        return String.format("PO%06d", count);
    }

    private String generateInterviewId() {
        long count = interviewRecordRepository.count() + 1;
        return String.format("PI%06d", count);
    }

    private String generateAcceptanceId() {
        long count = acceptanceRecordRepository.count() + 1;
        return String.format("PA%06d", count);
    }

    private String generateBindId() {
        long count = bindingRecordRepository.count() + 1;
        return String.format("PB%06d", count);
    }

    private String generateCatalogId() {
        long count = catalogEntryRepository.count() + 1;
        return String.format("PC%06d", count);
    }
}
