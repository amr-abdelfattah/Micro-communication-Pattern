package com.example.demo.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.models.LicenseDTO;

@FeignClient("licensing-service")
public interface LicenseFeignClient {
    @RequestMapping(
            method= RequestMethod.GET,
            value="/bfflicense/getList",
            consumes="application/json")
    List<LicenseDTO> getLicenseList();
    
    @RequestMapping(
            method= RequestMethod.GET,
            value="/bfflicense/getDetails/{licenseID}",
            consumes="application/json")
    LicenseDTO getLicenseDetails(@PathVariable("licenseID") Long licenseID);
}
