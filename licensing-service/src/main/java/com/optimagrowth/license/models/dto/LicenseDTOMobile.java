package com.optimagrowth.license.models.dto;

import com.optimagrowth.license.models.LicenseModel;

import lombok.Data;

@Data
public class LicenseDTOMobile implements LicenseProtocol {
	//id  // No need
	
    private String title;
    private String shortDescription; // For Mobile    
    private String date; // both
    private String licenseType; // both
	
	public LicenseDTOMobile(LicenseModel model) {
		this.title = model.getTitle();
		this.shortDescription = model.getShortDescription();
		this.date = model.getDate();
		this.licenseType = model.getLicenseType();
	}
}
