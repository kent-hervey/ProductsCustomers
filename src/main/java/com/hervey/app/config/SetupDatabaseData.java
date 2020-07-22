package com.hervey.app.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.hervey.app.models.Vendor;
import com.hervey.app.services.ApiService;

@Component
public class SetupDatabaseData implements ApplicationListener<ContextRefreshedEvent>{

	private ApiService apiService;
	
	public SetupDatabaseData(ApiService apiService) {
		this.apiService =apiService;
	}



	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(apiService.fetchCountVendors()==0) {
			System.out.println("here we will add a record because books size is:  " + apiService.fetchCountVendors());
			
			Vendor vendor = new Vendor();
			vendor.setCompanyEmail("acme@acme.com");
			vendor.setCompanyLocation("SmallvilleLocation");
			vendor.setCompanyName("Acme Products");
			apiService.updateVendor(vendor);
				
		}
		else {
			System.out.println("we won't add becasue books size is;  " + apiService.fetchCountVendors());
		}
	}
	
	
	
	
	
	
	 
	
	

}
