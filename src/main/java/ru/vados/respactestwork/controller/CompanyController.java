package ru.vados.respactestwork.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vados.respactestwork.dto.AreaDto;
import ru.vados.respactestwork.dto.CompanyByNameDto;
import ru.vados.respactestwork.dto.CompanyDto;
import ru.vados.respactestwork.dto.CompanyFilterRequest;
import ru.vados.respactestwork.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/api/company")
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @Operation(summary = "Получить фермерство по имени")
    @GetMapping("{companyName}")
    public CompanyByNameDto getByName(@PathVariable String companyName){
        return companyService.getCompanyByName(companyName);
    }

    @Operation(summary = "Создать фермерство")
    @PutMapping()
    public void create(@RequestBody CompanyDto dto){
        companyService.create(dto);
    }

    @Operation(summary = "Обновить фермерство по идентификатору")
    @PostMapping()
    public void update(@RequestBody CompanyDto dto){
        companyService.update(dto);
    }

    @Operation(summary = "Изменить статус архивности фермерства")
    @PostMapping("/archive")
    public void setArchive(@RequestBody CompanyDto.CompanyArchiveDto dto){
        companyService.setArchive(dto);
    }

    @Operation(summary = "Получить фермерство по нескольим параметрам")
    @GetMapping("/filter")
    public List<CompanyDto> getFilter(@RequestBody CompanyFilterRequest filter){
        return companyService.getfilter(filter);
    }
}
