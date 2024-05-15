package ru.vados.respactestwork.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.vados.respactestwork.dto.CompanyFilterRequest;
import ru.vados.respactestwork.entity.CompanyEntity;

import java.time.LocalDate;
import java.util.List;

@Component
public class CompanySpecification {
    public Specification<CompanyEntity> build(CompanyFilterRequest filter) {
        final var specsList = List.of(withName(filter.getName()),
                withInn(filter.getInn()),
                withArea(filter.getAreaId()),
                ///withCreateDate(filter.getCreateDate()),
                withArchive(filter.isArchive())
        );

        return specsList.stream()
                .skip(1)
                .reduce(specsList.get(0), Specification::and);
    }

    private Specification<CompanyEntity> withName(String name) {
        return (root, query, cb) -> name == null ? cb.conjunction() : cb.equal(root.get("name"), name);
    }

    private Specification<CompanyEntity> withInn(String inn) {
        return (root, query, cb) -> inn == null ? cb.conjunction() : cb.equal(root.get("inn"), inn);
    }

    private Specification<CompanyEntity> withArea(Long areaId) {
        return (root, query, cb) -> areaId == null ? cb.conjunction() : cb.equal(root.get("area").get("id"), areaId);
    }

    private Specification<CompanyEntity> withCreateDate(LocalDate createDate) {
        return (root, query, cb) -> createDate == null ? cb.conjunction() : cb.equal(root.get("createDate"), createDate);
    }

    private Specification<CompanyEntity> withArchive(Boolean isArchive) {
        return (root, query, cb) -> isArchive == null ? cb.conjunction() : cb.equal(root.get("archive"), isArchive);
    }

}
