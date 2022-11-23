package com.optimagrowth.license.repository;

import com.optimagrowth.license.models.LicenseModel;

import org.springframework.data.repository.CrudRepository;

public interface LicenseRepository extends CrudRepository<LicenseModel, Long> {
	
}
