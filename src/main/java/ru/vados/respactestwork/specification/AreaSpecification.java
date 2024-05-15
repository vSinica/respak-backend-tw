package ru.vados.respactestwork.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.vados.respactestwork.dto.AreaFilterRequest;
import ru.vados.respactestwork.entity.AreaEntity;

@Component
public class AreaSpecification {
    public Specification<AreaEntity> build(AreaFilterRequest filter) {
        return withName(filter.getName())
                .and(withAreaCode(filter.getCode())
                        .and(withArchiveFalse()));
    }

    private Specification<AreaEntity> withName(String name) {
        return (root, query, cb) -> name == null ? cb.conjunction() : cb.equal(root.get("name"), name);
    }

    private Specification<AreaEntity> withAreaCode(Long code) {
        return (root, query, cb) -> code == null ? cb.conjunction() : cb.equal(root.get("code"), code);
    }

    private Specification<AreaEntity> withArchiveFalse() {
        return (root, query, cb) -> cb.equal(root.get("archive"), false);
    }
}
