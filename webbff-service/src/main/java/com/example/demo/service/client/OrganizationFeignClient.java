package com.example.demo.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.models.OrganizationDTO;

@FeignClient("organization-service")
public interface OrganizationFeignClient {
    @RequestMapping(
            method= RequestMethod.GET,
            value="/org/orgDetails/{orgID}",
            consumes="application/json")
    OrganizationDTO getOrganization(@PathVariable("orgID") Long orgID);
}
