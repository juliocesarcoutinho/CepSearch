package com.example.cepapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tb_history_log")
@EqualsAndHashCode(of = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "zip_code", length = 10)
    private String zipCode;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(length = 120)
    private String street;

    @Column(length = 120)
    private String city;

    @Column(length = 2)
    private String state;
}
