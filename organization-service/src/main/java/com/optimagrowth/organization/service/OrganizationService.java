package com.optimagrowth.organization.service;

import java.nio.charset.Charset;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import com.optimagrowth.organization.models.OrgaizationDTO;


@Service
public class OrganizationService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrganizationService.class);


	public OrgaizationDTO getinfo(){
		try {
			Thread.sleep(20000); // 20 Seconds
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//    	OrgaizationDTO model = new OrgaizationDTO();
//    	model.setName("BU Org");
//    	model.setInformation("Info in Org Service: ");
		OrgaizationDTO model = createModel();
    	return model;
    }
	
	private OrgaizationDTO createModel() {
		OrgaizationDTO model = new OrgaizationDTO();
    	model.setName(generateText(10));
    	model.setInformation(generateText(500));
    	return model;
	}
	
	private String generateText(int numberCharacters) {
		byte[] array = new byte[numberCharacters]; // length is bounded by 7
	    new Random().nextBytes(array);
	    String generatedString = new String(array, Charset.forName("UTF-8"));
	    return generatedString;
	}


}