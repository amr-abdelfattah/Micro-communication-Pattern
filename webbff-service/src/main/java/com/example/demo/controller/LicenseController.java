package com.example.demo.controller;

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

import com.example.demo.models.LicenseDTO;
import com.example.demo.models.LicenseDTOWeb;
import com.example.demo.models.LicenseWithOrganizationDTO;
import com.example.demo.models.OrganizationDTO;
import com.example.demo.service.LicenseService;
import com.example.demo.service.OrganizationService;


@RestController
@RequestMapping("/weblicense")
public class LicenseController {
	
	@Autowired
	private LicenseService licenseService;
	
	@Autowired
	private OrganizationService organizationService;
	
	private static final Logger logger = LoggerFactory.getLogger(LicenseController.class);

	@RequestMapping(value="/getList",method = RequestMethod.GET)
	public ResponseEntity<List<LicenseDTOWeb>> getList()  {
//		logger.debug("LicenseServiceController getList Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
		List<LicenseDTO> models = licenseService.getLicenseList();
		List<LicenseDTOWeb> modelsDTO = new ArrayList<>();
		for (LicenseDTO licenseModel : models) {
			modelsDTO.add(new LicenseDTOWeb(licenseModel));
		}
		return new ResponseEntity<>(modelsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getDetails/{licenseID}",method = RequestMethod.GET)
	public ResponseEntity<LicenseWithOrganizationDTO> getDetails(@PathVariable Long licenseID)  {
//		logger.debug("LicenseServiceController getDetails Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
		LicenseDTO licenseModel = licenseService.getLicense(licenseID);
		OrganizationDTO organizationModel = organizationService.getOrganization(licenseModel.getOrgID());
		LicenseWithOrganizationDTO modelDTO = new LicenseWithOrganizationDTO(licenseModel, organizationModel);
		return new ResponseEntity<>(modelDTO, HttpStatus.OK);
	}

}
