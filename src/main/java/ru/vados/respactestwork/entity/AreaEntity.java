package ru.vados.respactestwork.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "areas")
@AllArgsConstructor
public class AreaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long code;

    private boolean archive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "area")
    private List<CompanyEntity> reqCompanyList;

    @ManyToMany
    @JoinTable(
            name = "company2area",
            joinColumns = @JoinColumn(name = "area_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id")
    )
    private List<CompanyEntity> companyList;

    public AreaEntity() {}
}
