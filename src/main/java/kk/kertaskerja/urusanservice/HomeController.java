package kk.kertaskerja.urusanservice;

import kk.kertaskerja.urusanservice.common.HealthStatus;
import kk.kertaskerja.urusanservice.config.KertasKerjaProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class HomeController {
    private final KertasKerjaProperties kertasKerjaProperties;

    public HomeController(KertasKerjaProperties kertasKerjaProperties) {
        this.kertasKerjaProperties = kertasKerjaProperties;
    }

    @GetMapping("/")
    public HealthStatus status() {
        return new HealthStatus(
                kertasKerjaProperties.getStatus(),
                Instant.now()
        );
    }
}
