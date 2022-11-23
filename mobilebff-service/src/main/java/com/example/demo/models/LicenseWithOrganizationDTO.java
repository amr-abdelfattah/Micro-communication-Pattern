package com.example.demo.models;

import lombok.Data;

@Data
public class LicenseWithOrganizationDTO {
	
//	private LicenseDTOMobile license;
	private LicenseDetailsDTO license;
    private OrganizationDTO organization;    
    
	
	public LicenseWithOrganizationDTO(LicenseDTO license, OrganizationDTO organization) {
//		this.license = new LicenseDTOMobile(license);
		this.license = new LicenseDetailsDTO(license);
		this.organization = organization;
	}
	
}
