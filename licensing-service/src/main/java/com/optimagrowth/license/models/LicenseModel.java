package com.optimagrowth.license.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
//@Table(name="License")
public class LicenseModel {
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
	
//    @Column(columnDefinition="text")
    private String title;
    
//    @Column(columnDefinition="text")
    private String shortDescription;
    
//    @Column(columnDefinition="text")
    private String longDescription;
    
//    @Column(columnDefinition="date")
    private String date;
    
//    @Column(columnDefinition="url")
    private String url;
    
//    @Column(columnDefinition="type")
    private String licenseType;
    
//    @Column(columnDefinition="orgID")
    private Long orgID;

}
