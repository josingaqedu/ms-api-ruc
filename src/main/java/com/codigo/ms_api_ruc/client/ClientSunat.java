package com.codigo.ms_api_ruc.client;

import com.codigo.ms_api_ruc.utils.response.ResponseCompany;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "client-sunat", url = "https://api.apis.net.pe/v2/sunat")
public interface ClientSunat {
    @GetMapping("/ruc/full")
    ResponseCompany getCompanySunat(@RequestHeader(name = "Authorization") String authorization, @RequestParam(name = "numero") String numero);
}
