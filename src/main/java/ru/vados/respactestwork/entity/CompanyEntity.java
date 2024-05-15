package ru.vados.respactestwork.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "companys")
@AllArgsConstructor
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String companyType;

    private String inn;

    private String kpp;

    private String ogrn;

    @JoinColumn(name = "reg_area_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AreaEntity area;

    private LocalDate createDate;

    private boolean archive;

    @ManyToMany
    @JoinTable(
            name = "company2area",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "area_id")
    )
    private List<AreaEntity> areaList;

    public CompanyEntity() {}
}
