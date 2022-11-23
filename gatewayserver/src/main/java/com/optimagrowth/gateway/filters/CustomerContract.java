package com.optimagrowth.gateway.filters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CustomerContract {
	
	private Map<String, List<String>> contracts = new HashMap<>();
	private static CustomerContract instance = new CustomerContract();
	
	public static CustomerContract getInstance() {
		return instance;
	}
	
	private CustomerContract() {
		setupContracts();
	}
	
	private void setupContracts() {
		
		contracts.put("/license/getList", new ArrayList<>(){{
			add( "http://licensing-service/microlicense/getList" );
			// Can add multiple calls to be mapped to
		}});
		
		contracts.put("/license/getDetails", new ArrayList<>(){{
			add( "http://licensing-service/microlicense/getDetails/1" );
			add( "http://organization-service/org/orgDetails/10" );
		}});
		
	}
	
	public Optional<List<String>> getMappedEndpoints(String endpointKey) {
		return Optional.ofNullable(contracts.get(endpointKey));
	}
	
}
