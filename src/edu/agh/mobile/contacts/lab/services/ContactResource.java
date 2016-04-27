package edu.agh.mobile.contacts.lab.services;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import edu.agh.mobile.contacts.lab.services.model.Contact;


public class ContactResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;

	public ContactResource(UriInfo uriInfo, Request request, String id) {
		super();
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Contact getTodo() {
		Contact todo = ContactDao.getModel().get(id);
		return todo;
	}

	@DELETE
	public void deleteTodo() {
		ContactDao.getModel().remove(id);
	}

}
