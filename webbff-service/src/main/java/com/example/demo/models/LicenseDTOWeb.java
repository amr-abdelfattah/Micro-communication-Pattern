package com.example.demo.models;

import lombok.Data;

@Data
public class LicenseDTOWeb {
	//id  // No need
    private String title;
    private String longDescription; // For Web
    private String date; // both
    private String url; // For Web
    private String licenseType; // both
	
	public LicenseDTOWeb(LicenseDTO model) {
		this.title = model.getTitle();
		this.longDescription = model.getLongDescription();
		this.date = model.getDate();
		this.url = model.getUrl();
		this.licenseType = model.getLicenseType();
	}
}