CREATE TABLE urusan(
    id      BIGSERIAL PRIMARY KEY NOT NULL,
    kode_urusan CHAR(1) UNIQUE NOT NULL,
    nama_urusan VARCHAR(255) NOT NULL
);