package com.example.demo.models;

import lombok.Data;

@Data
public class LicenseDTOMobile {
	//id  // No need
	
    private String title;
    private String shortDescription; // For Mobile    
    private String date; // both
    private String licenseType; // both
	
	public LicenseDTOMobile(LicenseDTO model) {
		this.title = model.getTitle();
		this.shortDescription = model.getShortDescription();
		this.date = model.getDate();
		this.licenseType = model.getLicenseType();
	}
}
