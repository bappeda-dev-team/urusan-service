package kk.kertaskerja.urusanservice.domain;

import org.springframework.stereotype.Service;

@Service
public class UrusanService {
    private final UrusanRepository urusanRepository;

    public UrusanService(UrusanRepository urusanRepository) {
        this.urusanRepository = urusanRepository;
    }

    public Iterable<Urusan> viewUrusanList() {
        return urusanRepository.findAll();
    }

    public Urusan viewUrusanDetail(String kodeUrusan) {
        return urusanRepository.findByKodeUrusan(kodeUrusan).orElseThrow(() -> new UrusanNotFoundException(kodeUrusan));
    }

    public Urusan addUrusan(Urusan urusan) {
        if (urusanRepository.existsByKodeUrusan(urusan.kodeUrusan())) {
            throw new UrusanAlreadyExistsException(urusan.kodeUrusan());
        }

        return urusanRepository.save(urusan);
    }

    public Urusan updateUrusan(String kodeUrusan, Urusan urusan) {
        return urusanRepository.findByKodeUrusan(kodeUrusan).map( existingUrusan -> {
            var urusanToUpdate = new Urusan(existingUrusan.id(), urusan.kodeUrusan(), urusan.namaUrusan());
            return urusanRepository.save(urusanToUpdate);
        }).orElseGet(() -> addUrusan(urusan));
    }

    public void deleteUrusan(String kodeUrusan) {
        urusanRepository.deleteByKodeUrusan(kodeUrusan);
    }
}
