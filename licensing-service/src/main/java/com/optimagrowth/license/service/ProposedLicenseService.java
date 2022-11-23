package com.optimagrowth.license.service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.optimagrowth.license.models.LicenseModel;
import com.optimagrowth.license.utils.UserContextHolder;

@Service
public class ProposedLicenseService {
	
	private static final Logger logger = LoggerFactory.getLogger(LicenseService.class);

	public List<LicenseModel> getLicenseList(){
		logger.debug("GetLicense List Correlation id: {}",
				UserContextHolder.getContext().getCorrelationId());
		List<LicenseModel> modelsList = new ArrayList<>();
		try {
			Thread.sleep(10000); // 10 Seconds
			for (int i = 0; i < 20; i++) {
				modelsList.add(createModel());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return modelsList;
	}

	public LicenseModel getLicense(Long licenseID) {
//		LicenseModel model = new LicenseModel();
//		model.setOrgID((long) 10);
		try {
			Thread.sleep(5000); // 5 Seconds
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LicenseModel model = createModel();
		return model;
	}
	
	private LicenseModel createModel() {
		LicenseModel model = new LicenseModel();
		model.setTitle(generateText(20));
		model.setLongDescription(generateText(5000));
		model.setShortDescription(generateText(50));
		model.setId(new Random().nextLong());
		model.setOrgID((long) 10);
		return model;
	}
	
	private String generateText(int numberCharacters) {
		byte[] array = new byte[numberCharacters]; // length is bounded by 7
	    new Random().nextBytes(array);
	    String generatedString = new String(array, Charset.forName("UTF-8"));
	    return generatedString;
	}

}
