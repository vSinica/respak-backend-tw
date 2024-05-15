package ru.vados.respactestwork.service;

import ru.vados.respactestwork.dto.CompanyByNameDto;
import ru.vados.respactestwork.dto.CompanyDto;
import ru.vados.respactestwork.dto.CompanyFilterRequest;

import java.util.List;

public interface CompanyService {
    CompanyByNameDto getCompanyByName(String name);
    void create(CompanyDto dto);
    void update(CompanyDto dto);
    void setArchive(CompanyDto.CompanyArchiveDto dto);
    List<CompanyDto> getfilter(CompanyFilterRequest filter);

}
