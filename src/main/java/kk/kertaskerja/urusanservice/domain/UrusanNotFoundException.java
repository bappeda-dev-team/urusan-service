package kk.kertaskerja.urusanservice.domain;

public class UrusanNotFoundException extends RuntimeException {
    public UrusanNotFoundException(String kodeUrusan) {
        super("Urusan dengan kode " + kodeUrusan + " tidak ditemukan");
    }
}
