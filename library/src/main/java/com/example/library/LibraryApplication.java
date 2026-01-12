package com.example.library;

import com.example.library.common.entity.AcquisitionRecord;
import com.example.library.common.entity.CirculationBook;
import com.example.library.common.repository.AcquisitionRecordRepository;
import com.example.library.common.repository.CirculationBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {

    @Autowired
    private AcquisitionRecordRepository acquisitionRecordRepository;

    @Autowired
    private CirculationBookRepository circulationBookRepository;

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 插入一些采访问卷数据
        acquisitionRecordRepository.save(new AcquisitionRecord("001", "Java编程", "作者1", "1234567890123", "出版社1", "图书"));
        acquisitionRecordRepository.save(new AcquisitionRecord("002", "Spring入门", "作者2", "1234567890124", "出版社2", "教程"));

        // 插入一些图书流通库数据
        circulationBookRepository.save(new CirculationBook("1234567890123", "B001", "Java编程", "2026-01-12"));
        circulationBookRepository.save(new CirculationBook("1234567890124", "B002", "Spring入门", "2026-01-12"));
    }
}
