package kk.kertaskerja.urusanservice.domain;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UrusanRepository extends CrudRepository<Urusan, Long> {
    Optional<Urusan> findByKodeUrusan(String kodeUrusan);
    boolean existsByKodeUrusan(String kodeUrusan);

    @Modifying
    @Transactional
    @Query("delete from urusan where kode_urusan = :kodeUrusan")
    void deleteByKodeUrusan(String kodeUrusan);
}
