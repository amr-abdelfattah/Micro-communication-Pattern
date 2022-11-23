package com.optimagrowth.organization.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.optimagrowth.organization.models.OrgaizationDTO;
import com.optimagrowth.organization.service.OrganizationService;


@RestController
@RequestMapping(value="org")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;
    private static final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

//    @RequestMapping(value="/orgservice",method = RequestMethod.GET)
//    public String getorginfo(@RequestHeader("custom-header") String ch) {
//        String ret_msg="[custom-header]: (" + ch + ")";
//        return ret_msg;
//
//
//    }

    @RequestMapping(value="/orgDetails/{orgID}",method = RequestMethod.GET)
    public ResponseEntity<OrgaizationDTO> getorginfo(@PathVariable("orgID") Long orgID) {
    	logger.debug("LicenseServiceController getorginfo id: {}", orgID);
    	OrgaizationDTO orgDTO = organizationService.getinfo();
    	return new ResponseEntity<>(orgDTO, HttpStatus.OK);
    }

}
