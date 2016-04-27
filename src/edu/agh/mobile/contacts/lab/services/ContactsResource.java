package edu.agh.mobile.contacts.lab.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import edu.agh.mobile.contacts.lab.services.model.Contact;

//Use path: http://localhost:8080/catalog/service/*

@Path("/contacts")
public class ContactsResource {
	
	@Context
	UriInfo uriInfo;	//context variable = request path
	@Context
	Request request;	//context variable = request

	  // Return the list of contacts for applications
	  // Use http://localhost:8080/catalog/service/contacts
	  @GET
	  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	  public List<Contact> getTodos() {
	    List<Contact> todos = new ArrayList<Contact>();
	    todos.addAll(ContactDao.getModel().values());
	    return todos;
	  }

	  // Returns the number of contacts
	  // Use http://localhost:8080/catalog/service/contacts/count
	  @GET
	  @Path("count")
	  @Produces(MediaType.TEXT_PLAIN)
	  public String getCount() {
	    int count = ContactDao.getModel().size();
	    return String.valueOf(count);
	  }

	  // Creates a contact from a POST request with id/name/phone/email params
	  @POST
	  @Produces(MediaType.TEXT_PLAIN)
	  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	  public Response newContact(@FormParam("id") String id,
	      @FormParam("name") String name,
	      @FormParam("phone") String phone,
	      @FormParam("email") String email,
	      @Context HttpServletResponse servletResponse) throws IOException {
	    Contact contact = new Contact(id, name);
	    if (name != null) {
	      contact.setPhone(phone);
	      contact.setEmail(email);
	    }
	    
	    Response res;
		if (ContactDao.getModel().containsKey(contact.getId())) {				
			res = Response.status(Response.Status.NOT_MODIFIED)
					.entity("Contact already exists").build();	//HTTP response 304
		} else {	
			res = Response.status(Response.Status.CREATED)
					.entity("Contact successfully created").build(); 		//HTTP response 201
		}
		ContactDao.getModel().put(contact.getId(), contact);
		return res;
	    
	    // Optional: redirection (function return type void):
	    //servletResponse.sendRedirect("../index.html");
	    //servletResponse.sendRedirect("../service/contacts/"+id);
	  }


	  // Use http://localhost:8080/catalog/service/contacts/{id}
	  @Path("{id}")
	  public ContactResource getTodo(@PathParam("id") String id) {
	    return new ContactResource(uriInfo, request, id);
	  }

}
