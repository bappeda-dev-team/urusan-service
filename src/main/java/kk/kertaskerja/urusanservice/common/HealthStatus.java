package kk.kertaskerja.urusanservice.common;

import java.time.Instant;

public record HealthStatus(
        String status,
        Instant timestamp
) {}
