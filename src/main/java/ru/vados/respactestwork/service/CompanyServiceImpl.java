package ru.vados.respactestwork.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vados.respactestwork.dto.CompanyByNameDto;
import ru.vados.respactestwork.dto.CompanyDto;
import ru.vados.respactestwork.dto.CompanyFilterRequest;
import ru.vados.respactestwork.entity.AreaEntity;
import ru.vados.respactestwork.entity.CompanyEntity;
import ru.vados.respactestwork.repository.AreaRepository;
import ru.vados.respactestwork.repository.CompanyRepository;
import ru.vados.respactestwork.specification.CompanySpecification;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    private final AreaRepository areaRepository;
    private final CompanyRepository companyRepository;
    private final CompanySpecification companySpecification;

    @Override
    @Transactional
    public CompanyByNameDto getCompanyByName(String name){
        final var entity = companyRepository.findByName(name).orElseThrow();
        return CompanyByNameDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .companyType(entity.getCompanyType())
                .inn(entity.getInn())
                .kpp(entity.getKpp())
                .ogrn(entity.getOgrn())
                .registrationAreaName(entity.getArea().getName())
                .growingAreaListNames(entity.getAreaList().stream()
                        .map(AreaEntity::getName)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    @Transactional
    public List<CompanyDto> getfilter(CompanyFilterRequest filter){
        final var spec = companySpecification.build(filter);
        return companyRepository.findAll(spec).stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void create(CompanyDto dto){
        final var entity = convert(dto);
        companyRepository.save(entity);
    }

    @Override
    @Transactional
    public void update(CompanyDto dto){
        final var entity = convert(dto);
        companyRepository.save(entity);
    }

    @Override
    @Transactional
    public void setArchive(CompanyDto.CompanyArchiveDto dto){
        final var entity = companyRepository.findById(dto.getId()).orElseThrow();
        entity.setArchive(dto.isArchive());
        companyRepository.save(entity);
    }

    public CompanyDto convert(CompanyEntity entity){
        return CompanyDto.builder()
                .id(entity.getId())
                .inn(entity.getInn())
                .kpp(entity.getKpp())
                .ogrn(entity.getOgrn())
                .name(entity.getName())
                .companyType(entity.getCompanyType())
                .areaId(entity.getArea().getId())
                .createDate(entity.getCreateDate())
                .areaListId(entity.getAreaList().stream()
                        .map(AreaEntity::getId)
                        .collect(Collectors.toList()))
                .archive(entity.isArchive())
                .build();
    }

    public CompanyEntity convert(CompanyDto dto){
        return CompanyEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .companyType(dto.getCompanyType())
                .kpp(dto.getKpp())
                .ogrn(dto.getOgrn())
                .inn(dto.getInn())
                .createDate(dto.getCreateDate())
                .area(areaRepository.findById(dto.getAreaId()).orElseThrow())
                .areaList(dto.getAreaListId().stream()
                        .map(a -> areaRepository.findById(a).orElseThrow())
                        .collect(Collectors.toList()))
                .archive(dto.isArchive())
                .build();
    }
}
