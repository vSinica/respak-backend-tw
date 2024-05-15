package ru.vados.respactestwork.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AreaFilterRequest {
    private String name;
    private Long code;
}
