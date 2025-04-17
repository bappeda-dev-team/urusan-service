package kk.kertaskerja.urusanservice.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UrusanServiceTests {
    @Mock
    private UrusanRepository urusanRepository;

    @InjectMocks
    private UrusanService urusanService;

    @Test
    void whenUrusanToCrateAlreadyExistsThenThrowsException() {
        var kodeUrusan = "1";
        var urusanToCreate = Urusan.of(kodeUrusan, "Test Urusan");
        when(urusanRepository.existsByKodeUrusan(kodeUrusan)).thenReturn(true);

        assertThatThrownBy(() -> urusanService.addUrusan(urusanToCreate))
                .isInstanceOf(UrusanAlreadyExistsException.class)
                .hasMessage("Urusan dengan kode " + kodeUrusan + " sudah ada");
    }

    @Test
    void whenKodeUrusanToReadDoesNotExistThenThrowsException() {
        var kodeUrusan = "1";
        when(urusanRepository.findByKodeUrusan(kodeUrusan)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> urusanService.viewUrusanDetail(kodeUrusan))
                .isInstanceOf(UrusanNotFoundException.class)
                .hasMessage("Urusan dengan kode " + kodeUrusan + " tidak ditemukan");
    }

    @Test
    void whenUrusanToCreatesAreValidThenUrusanCreated() {
        var urusanToCreate = Urusan.of("1", "Test Urusan");
        when(urusanRepository.existsByKodeUrusan("1")).thenReturn(false);

        // mock invocation
        when(urusanRepository.save(urusanToCreate)).thenAnswer(invocation -> invocation.getArgument(0));

        Urusan actualUrusan = urusanService.addUrusan(urusanToCreate);
        assertThat(actualUrusan.kodeUrusan()).isEqualTo(urusanToCreate.kodeUrusan());
        assertThat(actualUrusan.namaUrusan()).isEqualTo(urusanToCreate.namaUrusan());
    }
}
