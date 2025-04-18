package kk.kertaskerja.urusanservice.web;

import kk.kertaskerja.urusanservice.domain.Urusan;
import kk.kertaskerja.urusanservice.domain.UrusanService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("urusan")
public class UrusanController {
    private final UrusanService urusanService;

    public UrusanController(UrusanService urusanService) {
        this.urusanService = urusanService;
    }

    @GetMapping
    public Iterable<Urusan> get() {
        return urusanService.viewUrusanList();
    }

    @GetMapping("{kodeUrusan}")
    public Urusan getByKodeUrusan(@PathVariable String kodeUrusan) {
        return urusanService.viewUrusanDetail(kodeUrusan);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Urusan create(@RequestBody Urusan urusan) {
        return urusanService.addUrusan(urusan);
    }

    @PutMapping("{kodeUrusan}")
    public Urusan update(@PathVariable String kodeUrusan, @RequestBody Urusan urusan) {
        return urusanService.updateUrusan(kodeUrusan, urusan);
    }

    @DeleteMapping("{kodeUrusan}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String kodeUrusan) {
        urusanService.deleteUrusan(kodeUrusan);
    }
}
