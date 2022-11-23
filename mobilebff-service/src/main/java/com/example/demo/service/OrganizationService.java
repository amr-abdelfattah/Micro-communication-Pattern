package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.OrganizationDTO;
import com.example.demo.service.client.OrganizationFeignClient;

@Service
public class OrganizationService {
	
	@Autowired
	OrganizationFeignClient organizationFeignClient;
	
	public OrganizationDTO getOrganization(Long orgID) {
		OrganizationDTO model = organizationFeignClient.getOrganization(orgID);
		return model;
	}

}
