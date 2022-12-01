package com.codingdojo.caresoft.models;

import java.util.ArrayList;
import java.util.Date;

import com.codingdojo.caresoft.interfaces.HIPAACompliantUser;

public class Physician extends User implements HIPAACompliantUser{
	
	
	
    // Inside class:    
    private ArrayList<String> patientNotes;
	
    // TO DO: Constructor that takes an ID
    public Physician() {
    	super();
    }
    
    public Physician(Integer id) {
    		super();
    		this.id = id;
    }
    // TO DO: Implement HIPAACompliantUser!
	
    public void newPatientNotes(String notes, String patientName, Date date) {
        String report = String.format(
            "Datetime Submitted: %s \n", date);
        report += String.format("Reported By ID: %s\n", this.id);
        report += String.format("Patient Name: %s\n", patientName);
        report += String.format("Notes: %s \n", notes);
        this.patientNotes.add(report);
    }

	@Override
	public boolean assignPin(int pin) {
		int count = 0;
		while(pin != 0) {
			pin = pin/10;
			count++;
		}
		if(count != 4) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public boolean accessAuthorized(Integer confirmedAuthID) {
		if( super.getId() != confirmedAuthID) {
			return false;
		}
		else{
			return true;
		}
	}
	
    // TO DO: Setters & Getters

}
