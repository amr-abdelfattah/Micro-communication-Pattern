package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.LicenseDTO;
import com.example.demo.service.client.LicenseFeignClient;
import com.example.demo.service.client.OrganizationFeignClient;

@Service
public class LicenseService {
	
	@Autowired
	LicenseFeignClient licenseFeignClient;

	public List<LicenseDTO> getLicenseList() {
		return licenseFeignClient.getLicenseList();
	}

	public LicenseDTO getLicense(Long licenseID) {
		LicenseDTO model = licenseFeignClient.getLicenseDetails(licenseID);
//		model.setOrgID((long) 20);
		return model;
	}

}
