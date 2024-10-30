package com.codigo.ms_api_ruc.repository;

import com.codigo.ms_api_ruc.enity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    CompanyEntity findByNumeroDocumento(String numeroDocumento);
}
