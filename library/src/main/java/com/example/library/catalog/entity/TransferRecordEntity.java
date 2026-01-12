package com.example.library.catalog.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transfer_record")
public class TransferRecordEntity {

    @Id
    @Column(length = 8)
    private String transferId;

    @Column(length = 13, nullable = false)
    private String isbn;

    @Column(length = 8, nullable = false)
    private String bookId;

    @Column(length = 20, nullable = false)
    private String movePos;

    private LocalDate transferDate;

    // 添加与 CatalogBookEntity 的关联
    @ManyToOne
    @JoinColumn(name = "bookId", referencedColumnName = "bookId", insertable = false, updatable = false)
    private CatalogBookEntity catalogBook;

    public TransferRecordEntity() {}

    public TransferRecordEntity(String transferId, String bookId, String movePos, LocalDate transferDate) {
        this.transferId = transferId;
        this.bookId = bookId;
        this.movePos = movePos;
        this.transferDate = transferDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTransferId() {
        return transferId;
    }
    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public String getBookId() {
        return bookId;
    }
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getMovePos() {
        return movePos;
    }
    public void setMovePos(String movePos) {
        this.movePos = movePos;
    }

    public LocalDate getTransferDate() {
        return transferDate;
    }
    public void setTransferDate(LocalDate transferDate) {
        this.transferDate = transferDate;
    }

    // 获取与 CatalogBookEntity 关联的字段
    public CatalogBookEntity getCatalogBook() {
        return catalogBook;
    }

    public void setCatalogBook(CatalogBookEntity catalogBook) {
        this.catalogBook = catalogBook;
    }
}
