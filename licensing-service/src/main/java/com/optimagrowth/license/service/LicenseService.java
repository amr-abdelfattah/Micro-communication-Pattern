package com.optimagrowth.license.service;



import com.optimagrowth.license.service.client.OrganizationRestTemplateClient;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.optimagrowth.license.models.LicenseModel;
import com.optimagrowth.license.service.client.OrganizationFeignClient;

import com.optimagrowth.license.utils.UserContextHolder;



@Service
public class LicenseService {

	@Autowired
	OrganizationFeignClient organizationFeignClient;

	@Autowired
	OrganizationRestTemplateClient orgRest;

	private static final Logger logger = LoggerFactory.getLogger(LicenseService.class);



	public String getinfo(){
		logger.debug("New Service Correlation id: {}",
				UserContextHolder.getContext().getCorrelationId());

		return "License Service is called";
	}

	public String getlicensinfo(){
		logger.debug("New Service Correlation id: {}",
				UserContextHolder.getContext().getCorrelationId());


		//return "License-service called organization-service: \n" + organizationFeignClient.getOrganization();
		return "License-service called organization-service: \n" + orgRest.getOrganization();

	}

}
