CREATE TABLE tb_history_log
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    cep           VARCHAR(255) NULL,
    date_time     datetime NULL,
    info_zip_code TEXT NULL,
    CONSTRAINT pk_tb_history_log PRIMARY KEY (id)
);