package kk.kertaskerja.urusanservice.common.exception;

import java.time.Instant;

public record ApiError(
        int status,
        String message,
        Instant timestamp,
        String path
) {}
