package kk.kertaskerja.urusanservice.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UrusanAlreadyExistsException extends RuntimeException {
    public UrusanAlreadyExistsException(String kodeUrusan) {
        super("Urusan dengan kode " + kodeUrusan + " sudah ada");
    }
}
