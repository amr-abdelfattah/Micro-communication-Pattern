package com.optimagrowth.license.models.dto;

import com.optimagrowth.license.models.LicenseModel;

import lombok.Data;

@Data
public class LicenseDTO implements LicenseProtocol {
	
	//id  // No need
	
    private String title;
    private String shortDescription; // For Mobile    
    private String longDescription; // For Web
    private String date; // both
    private String url; // For Web
    private String licenseType; // both
    private Long orgID; // both
	
	public LicenseDTO(LicenseModel model) {
		this.title = model.getTitle();
		this.shortDescription = model.getShortDescription();
		this.longDescription = model.getLongDescription();
		this.date = model.getDate();
		this.url = model.getUrl();
		this.licenseType = model.getLicenseType();
		this.orgID = model.getOrgID();
	}
}
