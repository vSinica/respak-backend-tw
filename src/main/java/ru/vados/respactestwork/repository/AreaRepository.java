package ru.vados.respactestwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vados.respactestwork.entity.AreaEntity;

import java.util.List;

public interface AreaRepository extends JpaRepository<AreaEntity, Long>, JpaSpecificationExecutor<AreaEntity> {
    List<AreaEntity> findAllByArchiveIsFalse();
}
