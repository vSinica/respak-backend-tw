package ru.vados.respactestwork.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class CompanyByNameDto {
    private Long id;

    private String name;

    private String companyType;

    private String inn;

    private String kpp;

    private String ogrn;

    private String registrationAreaName;

    private LocalDate createDate;

    private boolean archive;

    private List<String> growingAreaListNames;
}
