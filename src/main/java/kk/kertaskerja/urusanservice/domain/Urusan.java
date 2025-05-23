package kk.kertaskerja.urusanservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;

public record Urusan(
        @Id Long id,

        @NotBlank(message = "Kode urusan wajib terisi")
        @Pattern(
                regexp = "^[a-zA-Z0-9]$",
                message = "Format kode tidak valid"
        )
        String kodeUrusan,

        @NotBlank(message = "Nama urusan wajib terisi")
        String namaUrusan
) {
    public static Urusan of(String kodeUrusan, String namaUrusan) {
        return new Urusan(null, kodeUrusan, namaUrusan);
    }
}
