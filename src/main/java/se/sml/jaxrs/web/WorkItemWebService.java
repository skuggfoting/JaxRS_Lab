package se.sml.jaxrs.web;

import static se.sml.jaxrs.ContextLoader.getBean;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import se.sml.jaxrs.model.WorkItemWeb;
import se.sml.sdj.model.WorkItem;
import se.sml.sdj.service.WorkItemService;



@Path("/users")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public final class WorkItemWebService {

	@Context
	private UriInfo uriInfo;

/*
CRUD Verb:
@POST		C
@GET		R
@PUT		U
@DELETE		D
*/	
	
//	String lable, String description, String workItemNumber, String status
	
	// Create
	@POST
	public Response addWorkItem(WorkItemWeb workItemWeb) {

		WorkItem workItem = new WorkItem(workItemWeb.getLable(), workItemWeb.getDescription(), workItemWeb.getWorkItemNumber(), workItemWeb.getStatus());
		
		getBean(WorkItemService.class).save(workItem);
		
		URI location = uriInfo.getAbsolutePathBuilder().path(getClass(), "getUserByWorkItemNumberWeb").build(workItemWeb.getWorkItemNumber());

		return Response.created(location).build();
	}
//	
//	// Read
//	@GET
//	@Path("/userNumber/{userNumber}")
//	public UserWeb getUserByUserNumberWeb(@PathParam("userNumber") String userNumber) {
//
//		WorkItem user = getBean(UserService.class).findByUserNumber(userNumber);
//
//		if (user == null) {
//			throw new WebApplicationException(Status.NOT_FOUND);
//		}
//
//		return new UserWeb(user.getUsername(), user.getFirstName(), user.getLastName(), user.getUserNumber(), user.getStatus());
//	}
//	
//	@GET
//	@Path("/firstName/{firstName}")
//	public Collection<UserWeb> getUserByFirstNameWeb(@PathParam("firstName") String firstName) {
//
//		Collection<User> user = getBean(UserService.class).findByFirstName(firstName);
//
//		if (user == null) {
//			throw new WebApplicationException(Status.NOT_FOUND);
//		}
//		
//		Collection<UserWeb> userWeb = new ArrayList<UserWeb>();
//		
//		user.forEach(u -> userWeb.add(new UserWeb(u.getUsername(), u.getFirstName(), u.getLastName(), u.getUserNumber(), u.getStatus())));
//
//		return userWeb;
//	}
//	
//	@GET
//	@Path("/lastName/{lastName}")
//	public Collection<UserWeb> getUserByLastNameWeb(@PathParam("lastName") String lastName) {
//
//		Collection<User> user = getBean(UserService.class).findByLastName(lastName);
//
//		if (user == null) {
//			throw new WebApplicationException(Status.NOT_FOUND);
//		}
//		
//		Collection<UserWeb> userWeb = new ArrayList<UserWeb>();
//		
//		user.forEach(u -> userWeb.add(new UserWeb(u.getUsername(), u.getFirstName(), u.getLastName(), u.getUserNumber(), u.getStatus())));
//
//		return userWeb;
//	}
//	
//	@GET
//	@Path("/username/{username}")
//	public UserWeb getUserByUsernameWeb(@PathParam("username") String username) {
//
//		User user = getBean(UserService.class).findByUsername(username);
//
//		if (user == null) {
//			throw new WebApplicationException(Status.NOT_FOUND);
//		}
//
//		return new UserWeb(user.getUsername(), user.getFirstName(), user.getLastName(), user.getUserNumber(), user.getStatus());
//	}
//
//	@GET
//	@Path("/team/{team}")
//	public Collection<UserWeb> getUserByTeamWeb(@PathParam("team") String team) {
//
//		Collection<User> user = getBean(UserService.class).findUsersByTeam(team);
//
//		if (user == null) {
//			throw new WebApplicationException(Status.NOT_FOUND);
//		}
//		
//		Collection<UserWeb> userWeb = new ArrayList<UserWeb>();
//		
//		user.forEach(u -> userWeb.add(new UserWeb(u.getUsername(), u.getFirstName(), u.getLastName(), u.getUserNumber(), u.getStatus())));
//
//		return userWeb;
//	}
//	
//	// Update
//	@PUT
//	@Path("/userNumber/{userNumber}")
//	public Response updateUser(@PathParam("userNumber") String userNumber, UserWeb userWeb) {
//			
//		User user = getBean(UserService.class).findByUserNumber(userNumber.toString());
//			
//		if (user == null) {
//			throw new WebApplicationException(Status.NOT_FOUND);
//		}
//			
//		user.setUsername(userWeb.getUsername()).setFirstName(userWeb.getFirstName()).setLastName(userWeb.getLastName()).setUserNumber(userWeb.getUserNumber()).setStatus(userWeb.getStatus());
//			
//		getBean(UserService.class).save(user);
//			
//		return Response.noContent().build();
//	}
//	
//	@PUT
//	@Path("/addWorkItem/{userNumber}")
//	public Response addWorkItem(@PathParam("userNumber") String userNumber, WorkItemWeb workItemWeb) {
//			
//		User user = getBean(UserService.class).findByUserNumber(userNumber.toString());
//			
//		if (user == null) {
//			throw new WebApplicationException(Status.NOT_FOUND);
//		}
//		
//		WorkItem workItem = new WorkItem(workItemWeb.getLable(), workItemWeb.getDescription(), workItemWeb.getworkItemNumber(), workItemWeb.getStatus());
//		user.addWorkItem(workItem);
//			
//		getBean(WorkItemService.class).save(workItem);
//		getBean(UserService.class).save(user);
//			
//		return Response.noContent().build();
//	}
//
//	// Delete
//	@DELETE
//	@Path("/userNumber/{userNumber}")
//	public Response deleteUser(@PathParam("userNumber") String userNumber) {
//		
//		User user = getBean(UserService.class).findByUserNumber(userNumber.toString());
//		
//		if (user == null) {
//			throw new WebApplicationException(Status.NOT_FOUND);
//		}
//		
//		user.setStatus("Inactive");
//		
//		getBean(UserService.class).save(user);
//		
//		return Response.noContent().build();
//	}
		
}






















