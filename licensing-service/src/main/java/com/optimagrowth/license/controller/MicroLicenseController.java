package com.optimagrowth.license.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.optimagrowth.license.models.LicenseModel;
import com.optimagrowth.license.models.dto.LicenseDTOMobile;
import com.optimagrowth.license.models.dto.LicenseDTOWeb;
import com.optimagrowth.license.models.dto.LicenseDetailsDTO;
import com.optimagrowth.license.models.dto.LicenseProtocol;
import com.optimagrowth.license.service.ProposedLicenseService;
import com.optimagrowth.license.utils.UserContextHolder;


@RestController
@RequestMapping("/microlicense")
public class MicroLicenseController {
	
	@Autowired
	private ProposedLicenseService licenseService;	
	private static final Logger logger = LoggerFactory.getLogger(LicenseController.class);

	@RequestMapping(value="/getList",method = RequestMethod.GET)
	public ResponseEntity<List<LicenseProtocol>> getList()  {
		logger.debug("LicenseServiceController getList Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
		List<LicenseModel> models = licenseService.getLicenseList();
		List<LicenseProtocol> modelsDTO = new ArrayList<>();
		for (LicenseModel licenseModel : models) {
			if (UserContextHolder.getContext().isMobileClient()) {
				modelsDTO.add(new LicenseDTOMobile(licenseModel));
			} else {
				modelsDTO.add(new LicenseDTOWeb(licenseModel));
			}
		}
		return new ResponseEntity<>(modelsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getDetails/{licenseID}",method = RequestMethod.GET)
	public ResponseEntity<LicenseProtocol> getDetails(@PathVariable Long licenseID)  {
		logger.debug("LicenseServiceController getDetails Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
		LicenseModel licenseModel = licenseService.getLicense(licenseID);
		LicenseProtocol modelDTO = new LicenseDetailsDTO(licenseModel);;
		return new ResponseEntity<>(modelDTO, HttpStatus.OK);
	}

}