package ru.vados.respactestwork.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vados.respactestwork.dto.AreaDto;
import ru.vados.respactestwork.dto.AreaFilterRequest;
import ru.vados.respactestwork.service.AreaService;

import java.util.List;

@RestController
@RequestMapping("/api/area")
@AllArgsConstructor
public class AreaController {

    private final AreaService areaService;

    @Operation(summary = "Получить район по идентификатору")
    @GetMapping("/{areaId}")
    public AreaDto getById(@PathVariable("areaId") String areaId){
        return areaService.getById(Long.parseLong(areaId));
    }

    @Operation(summary = "Получить все не архивные районы")
    @GetMapping()
    public List<AreaDto> getAll(){
        return areaService.getAll();
    }

    @Operation(summary = "Получить все не архивные районы по коду района и подходящему названию")
    @GetMapping("/filter")
    public List<AreaDto> getFilterAll(@RequestBody AreaFilterRequest filter){
        return areaService.getFiltered(filter);
    }

    @Operation(summary = "Создать район")
    @PutMapping
    public void create(@RequestBody AreaDto dto) {
        areaService.create(dto);
    }

    @Operation(summary = "Обновить район по идентификатору")
    @PostMapping
    public void update(@RequestBody AreaDto dto) {
        areaService.update(dto);
    }

    @Operation(summary = "Отправить район в архив")
    @PostMapping("/archive")
    public void setArchive(@RequestBody AreaDto.AreaArchiveDto dto){
        areaService.setArchive(dto);
    }

}
