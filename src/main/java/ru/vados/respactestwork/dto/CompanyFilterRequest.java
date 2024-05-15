package ru.vados.respactestwork.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CompanyFilterRequest {
    private String name;
    private String inn;
    private Long areaId;
    private LocalDate createDate;
    private boolean archive;
}
