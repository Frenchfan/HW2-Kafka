SET search_path TO hw2;

CREATE TABLE IF NOT EXISTS extended_metrics
(
    id              BIGSERIAL PRIMARY KEY,
    kafka_id        BIGINT       NOT NULL,
    authorized_user VARCHAR(255) NOT NULL,
    timestamp       TIMESTAMP    NOT NULL,
    temperature     INT          NOT NULL,
    humidity        INT          NOT NULL,
    comment         VARCHAR(255)
);

