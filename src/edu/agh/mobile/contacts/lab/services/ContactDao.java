package edu.agh.mobile.contacts.lab.services;

import java.util.HashMap;
import java.util.Map;

import edu.agh.mobile.contacts.lab.services.model.Contact;

public class ContactDao {
	
	private static Map<String, Contact> contentProvider = new HashMap<String, Contact>();

	static {
		Contact contact = new Contact("1", "Jan Kowalski", "001 100 100 100", "jk@domain.com");
		contentProvider.put("1", contact);
		contact = new Contact("2", "John Smith", "002 200 200 200", "js@domena.pl");
		contentProvider.put("2", contact);		
	}
	
	public static Map<String, Contact> getModel() {
		return contentProvider;
	}

}
