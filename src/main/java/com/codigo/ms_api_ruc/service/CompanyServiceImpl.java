package com.codigo.ms_api_ruc.service;

import com.codigo.ms_api_ruc.client.ClientSunat;
import com.codigo.ms_api_ruc.enity.CompanyEntity;
import com.codigo.ms_api_ruc.exception.CompanyNotFoundException;
import com.codigo.ms_api_ruc.repository.CompanyRepository;
import com.codigo.ms_api_ruc.utils.constants.Constants;
import com.codigo.ms_api_ruc.utils.response.ResponseCompany;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Objects;

@Service
public class CompanyServiceImpl implements CompanyService{
    private final CompanyRepository companyRepository;
    private final ClientSunat clientSunat;

    @Value("${token.api}")
    private String token;

    public CompanyServiceImpl(CompanyRepository companyRepository, ClientSunat clientSunat) {
        this.companyRepository = companyRepository;
        this.clientSunat = clientSunat;
    }

    @Override
    public CompanyEntity getCompany(String ruc) {
        return findCompany(ruc);
    }

    private CompanyEntity findCompany(String ruc) {
        CompanyEntity companyEntityFinal = new CompanyEntity();

        CompanyEntity existCompany = companyRepository.findByNumeroDocumento(ruc);

        if(Objects.nonNull(existCompany)) {
            // set audit data
            existCompany.setActualizadoPor(Constants.USER_UPDATED);
            existCompany.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));

            // update company
            companyEntityFinal = companyRepository.save(existCompany);
        } else {
            ResponseCompany responseCompany = null;
            try {
                responseCompany = executeApi(ruc);

                if (Objects.nonNull(responseCompany)) {
                    CompanyEntity companyEntity = new CompanyEntity();

                    // set data
                    companyEntity.setRazonSocial(responseCompany.getRazonSocial());
                    companyEntity.setTipoDocumento(responseCompany.getTipoDocumento());
                    companyEntity.setNumeroDocumento(responseCompany.getNumeroDocumento());
                    companyEntity.setEstado(responseCompany.getEstado());
                    companyEntity.setCondicion(responseCompany.getCondicion());
                    companyEntity.setDireccion(responseCompany.getDireccion());
                    companyEntity.setUbigeo(responseCompany.getUbigeo());
                    companyEntity.setViaTipo(responseCompany.getViaTipo());
                    companyEntity.setViaNombre(responseCompany.getViaNombre());
                    companyEntity.setZonaCodigo(responseCompany.getZonaCodigo());
                    companyEntity.setZonaTipo(responseCompany.getZonaTipo());
                    companyEntity.setNumero(responseCompany.getNumero());
                    companyEntity.setInterior(responseCompany.getInterior());
                    companyEntity.setLote(responseCompany.getLote());
                    companyEntity.setDpto(responseCompany.getDpto());
                    companyEntity.setManzana(responseCompany.getManzana());
                    companyEntity.setKilometro(responseCompany.getKilometro());
                    companyEntity.setDistrito(responseCompany.getDistrito());
                    companyEntity.setProvincia(responseCompany.getProvincia());
                    companyEntity.setDepartamento(responseCompany.getDepartamento());
                    companyEntity.setEsAgenteRetencion(responseCompany.getEsAgenteRetencion());
                    companyEntity.setTipo(responseCompany.getTipo());
                    companyEntity.setActividadEconomica(responseCompany.getActividadEconomica());
                    companyEntity.setNumeroTrabajadores(responseCompany.getNumeroTrabajadores());
                    companyEntity.setTipoFacturacion(responseCompany.getTipoFacturacion());
                    companyEntity.setTipoContabilidad(responseCompany.getTipoContabilidad());
                    companyEntity.setComercioExterior(responseCompany.getComercioExterior());

                    // set audit data
                    companyEntity.setCreadoPor(Constants.USER_CREATED);
                    companyEntity.setFechaCreacion(new Timestamp(System.currentTimeMillis()));

                    companyEntityFinal =  companyRepository.save(companyEntity);
                }
            } catch (Exception e) {
                throw new CompanyNotFoundException("Company with ruc: " + ruc + " is not valid");
            }
        }

        return companyEntityFinal;
    }

    private ResponseCompany executeApi(String ruc) {
        String bearerToken = Constants.BEARER + token;
        return clientSunat.getCompanySunat(bearerToken, ruc);
    }
}
