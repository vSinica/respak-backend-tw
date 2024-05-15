package ru.vados.respactestwork.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Builder
public class AreaDto {
    private Long id;

    private String name;

    private Long code;

    private boolean archive;

    private List<Long> reqCompanyListId;

    private List<Long> companyListId;

    @Getter
    @Setter
    public static class AreaArchiveDto {
        private Long id;
    }
}
