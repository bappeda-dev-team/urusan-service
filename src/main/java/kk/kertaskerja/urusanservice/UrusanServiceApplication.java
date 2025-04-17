package kk.kertaskerja.urusanservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class UrusanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrusanServiceApplication.class, args);
    }

}
