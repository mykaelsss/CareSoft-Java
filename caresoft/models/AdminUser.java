package com.codingdojo.caresoft.models;

import java.util.ArrayList;
import java.util.Date;

import com.codingdojo.caresoft.interfaces.HIPAACompliantAdmin;
import com.codingdojo.caresoft.interfaces.HIPAACompliantUser;

public class AdminUser extends User implements HIPAACompliantAdmin, HIPAACompliantUser{
	
    // Inside class:
    private Integer employeeID;
    private String role;
    private ArrayList<String> securityIncidents = new ArrayList<String>();
    
    // TO DO: Implement a constructor that takes an ID and a role
    public AdminUser() {
    	
    }
    
    public AdminUser(Integer employeeID, String role) {
    		this.employeeID = employeeID;
    		this.role = role;
    }
    	
    	// TO DO: Implement HIPAACompliantUser!
    // TO DO: Implement HIPAACompliantAdmin!
    
    public void newIncident(String notes) {
        String report = String.format(
            "Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n", 
            new Date(), this.id, notes
        );
        this.securityIncidents.add(report);
    }
    public void authIncident() {
        String report = String.format(
            "Datetime Submitted: %s \n,  ID: %s\n Notes: %s \n", 
            new Date(), this.employeeID, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
        );
        this.securityIncidents.add(report);
    }
	
	@Override
	public ArrayList<String> reportSecurityIncidents() {
		return this.securityIncidents;
	}

	@Override
	public boolean assignPin(int pin) {
		int count = 0; 
		while( pin != 0) {
			pin = pin / 10;
			count++;
		}
		if(count != 6) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public boolean accessAuthorized(Integer confirmedAuthID) {
		if(this.employeeID != confirmedAuthID) {
			authIncident();
			return false;
			
		}
		else {
			return true;
		}
	}

}
