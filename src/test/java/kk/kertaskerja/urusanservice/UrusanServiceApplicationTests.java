package kk.kertaskerja.urusanservice;

import kk.kertaskerja.urusanservice.domain.Urusan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("integration")
class UrusanServiceApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void whenGetRequestToHomeThenHealthStatusReturned() {
        webTestClient.get()
                .uri("/")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.status").isEqualTo("URUSAN SERVICE IS UP");
    }

    @Test
    void whenGetUrusanByKodeThatNotExistingThenReturnNotFound() {
        var nonExistingKodeUrusan = "H";
        webTestClient.get()
                .uri("/urusan/" + nonExistingKodeUrusan)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.status").isEqualTo("404")
                .jsonPath("$.message").isEqualTo("Urusan dengan kode " + nonExistingKodeUrusan + " tidak ditemukan");
    }

    @Test
    void whenGetUrusanByKodeThatNotValidThenReturnNotFound() {
        var nonExistingKodeUrusan = "H-123-456";
        webTestClient.get()
                .uri("/urusan/" + nonExistingKodeUrusan)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.status").isEqualTo("404")
                .jsonPath("$.message").isEqualTo("Urusan dengan kode " + nonExistingKodeUrusan + " tidak ditemukan");
    }

    @Test
    void whenPostUrusanThatAlreadyExistsThenReturnUnprocessableEntity() {
        var urusanToCreate = Urusan.of("0", "Test Urusan");

        webTestClient.post()
                .uri("/urusan")
                .bodyValue(urusanToCreate)
                .exchange()
                .expectStatus().isCreated();

        // Test duplicate record not created
        webTestClient.post()
                .uri("/urusan")
                .bodyValue(urusanToCreate)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody()
                .jsonPath("$.status").isEqualTo("422")
                .jsonPath("$.message").isEqualTo("Urusan dengan kode 0 sudah ada");
    }

}
