package ru.vados.respactestwork.service;

import ru.vados.respactestwork.dto.AreaDto;
import ru.vados.respactestwork.dto.AreaFilterRequest;

import java.util.List;

public interface AreaService {

    void create(AreaDto dto);
    AreaDto getById(Long id);
    List<AreaDto> getAll();
    void update(AreaDto dto);
    void setArchive(AreaDto.AreaArchiveDto dto);
    List<AreaDto> getFiltered(AreaFilterRequest filter);

}
