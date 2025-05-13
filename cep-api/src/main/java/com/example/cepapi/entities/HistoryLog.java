package com.example.cepapi.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tb_history_log")
@EqualsAndHashCode(of = "id")
public class HistoryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cep")
    private String cep;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "info_zip_code")
    private Cep infoZipCode;
}
