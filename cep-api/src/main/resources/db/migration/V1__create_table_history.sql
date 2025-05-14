CREATE TABLE tb_history_log
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    zip_code      VARCHAR(10)           NULL,
    date_time     datetime              NULL,
    street        VARCHAR(120)          NULL,
    city          VARCHAR(120)          NULL,
    state         VARCHAR(2)            NULL,
    CONSTRAINT pk_tb_history_log PRIMARY KEY (id)
);