package com.example.demo.models;


import lombok.Data;

@Data
public class LicenseDTO {
	
	//id  // No need
	
    private String title;
    private String shortDescription; // For Mobile    
    private String longDescription; // For Web
    private String date; // both
    private String url; // For Web
    private String licenseType; // both
    private Long orgID;
	
}
