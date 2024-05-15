package ru.vados.respactestwork.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vados.respactestwork.dto.AreaDto;
import ru.vados.respactestwork.dto.AreaFilterRequest;
import ru.vados.respactestwork.entity.AreaEntity;
import ru.vados.respactestwork.entity.CompanyEntity;
import ru.vados.respactestwork.repository.AreaRepository;
import ru.vados.respactestwork.repository.CompanyRepository;
import ru.vados.respactestwork.specification.AreaSpecification;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AreaServiceImpl implements AreaService{

    private final AreaRepository areaRepository;
    private final CompanyRepository companyRepository;
    private final AreaSpecification areaSpecification;

    @Override
    @Transactional
    public void create(AreaDto dto){
        final var entity = convert(dto);
        areaRepository.save(entity);

        companyRepository.saveAll(entity.getReqCompanyList()
                .stream().peek(a -> a.setArea(entity))
                .collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public void update(AreaDto dto){
        final var entity = convert(dto);
        areaRepository.save(entity);

        companyRepository.saveAll(entity.getReqCompanyList()
                .stream().peek(a -> a.setArea(entity))
                .collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public AreaDto getById(Long id){
        return convert(areaRepository.findById(id).orElseThrow());
    }

    @Override
    @Transactional
    public List<AreaDto> getAll(){
        return areaRepository.findAllByArchiveIsFalse().stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<AreaDto> getFiltered(AreaFilterRequest filter){
        final var spec = areaSpecification.build(filter);
        return areaRepository.findAll(spec).stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void setArchive(AreaDto.AreaArchiveDto dto){
        final var entity = areaRepository.findById(dto.getId()).orElseThrow();
        entity.setArchive(true);
        areaRepository.save(entity);
    }

    public AreaDto convert(AreaEntity entity){
        return AreaDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .archive(entity.isArchive())
                .companyListId(entity.getCompanyList().stream()
                        .map(CompanyEntity::getId)
                        .collect(Collectors.toList()))
                .reqCompanyListId(entity.getReqCompanyList().stream()
                        .map(CompanyEntity::getId)
                        .collect(Collectors.toList()))
                .build();
    }

    public AreaEntity convert(AreaDto dto){
        return AreaEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .code(dto.getCode())
                .archive(dto.isArchive())
                .companyList(dto.getCompanyListId().stream()
                        .map(a -> companyRepository.findById(a).orElseThrow())
                        .collect(Collectors.toList()))
                .reqCompanyList(dto.getReqCompanyListId().stream()
                        .map(a -> companyRepository.findById(a).orElseThrow())
                        .collect(Collectors.toList()))
                .build();
    }

}
