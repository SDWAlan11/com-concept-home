package com.concept.client;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@Startup
@Singleton
public class ZuoraAccountCacheClient {
	
	public String getMemberNumberFromEmployeeId(String employeeId) {
		try {
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8082/zuora-account-dataservice/account/employee/" + employeeId);
			JsonObject response = target.request(MediaType.APPLICATION_JSON).get(JsonObject.class);
			if(response.containsKey("memberNumber")) {
				return response.getString("memberNumber");
			}
			else {
				return "";
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	
    @PostConstruct
	public void call() {
		ZuoraAccountCacheClient zuoraAccountCacheClient = new ZuoraAccountCacheClient();
		System.out.println(zuoraAccountCacheClient.getMemberNumberFromEmployeeId("GRP2-24HFuvRuPCARLXX"));
	}

}
