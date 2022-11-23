package com.example.demo.models;

import lombok.Data;

@Data
public class LicenseWithOrganizationDTO {
	
	private LicenseDTOWeb license;
//	private LicenseDTO license;
    private OrganizationDTO organization;    
    
	
	public LicenseWithOrganizationDTO(LicenseDTO license, OrganizationDTO organization) {
		this.license = new LicenseDTOWeb(license);
//		this.license = license;
		this.organization = organization;
	}
	
}
