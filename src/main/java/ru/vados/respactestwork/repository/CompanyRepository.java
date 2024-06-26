package ru.vados.respactestwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vados.respactestwork.entity.AreaEntity;
import ru.vados.respactestwork.entity.CompanyEntity;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long>, JpaSpecificationExecutor<CompanyEntity> {
    Optional<CompanyEntity> findByName(String name);
}
