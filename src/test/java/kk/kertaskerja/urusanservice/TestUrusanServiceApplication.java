package kk.kertaskerja.urusanservice;

import org.springframework.boot.SpringApplication;

public class TestUrusanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(UrusanServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
