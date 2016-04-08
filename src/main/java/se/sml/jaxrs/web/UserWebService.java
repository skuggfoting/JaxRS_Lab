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

import se.sml.jaxrs.model.UserWeb;
import se.sml.jaxrs.model.WorkItemWeb;
import se.sml.sdj.model.User;
import se.sml.sdj.model.WorkItem;
import se.sml.sdj.service.UserService;
import se.sml.sdj.service.WorkItemService;

@Path("/users")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public final class UserWebService {

	@Context
	private UriInfo uriInfo;

/*
CRUD Verb:
@POST		C
@GET		R
@PUT		U
@DELETE		D
*/	
	
	// Create
	// - Skapa en User
	@POST
	public Response addUser(UserWeb userWeb) {

		User user = new User(userWeb.getUsername(), userWeb.getFirstName(), userWeb.getLastName(), userWeb.getUserNumber(), userWeb.getStatus());
		
		getBean(UserService.class).save(user);
		
		URI location = uriInfo.getAbsolutePathBuilder().path(getClass(), "getUserByUserNumberWeb").build(userWeb.getUserNumber());

		return Response.created(location).build();
	}
	
	// Read
	// - Hämta en User baserat på user id
	@GET
	@Path("/userNumber/{userNumber}")
	public UserWeb getByUserNumberWeb(@PathParam("userNumber") String userNumber) {

		User user = getBean(UserService.class).getByUserNumber(userNumber);

		if (user == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}

		return new UserWeb(user.getUsername(), user.getFirstName(), user.getLastName(), user.getUserNumber(), user.getStatus());
	}
	
	// - Söka efter en User baserat på förnamn
	@GET
	@Path("/firstName/{firstName}")
	public Collection<UserWeb> getByFirstNameWeb(@PathParam("firstName") String firstName) {

		Collection<User> user = getBean(UserService.class).getByFirstName(firstName);

		if (user == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		
		Collection<UserWeb> userWeb = new ArrayList<UserWeb>();
		
		user.forEach(u -> userWeb.add(new UserWeb(u.getUsername(), u.getFirstName(), u.getLastName(), u.getUserNumber(), u.getStatus())));

		return userWeb;
	}
	
	// - Söka efter en User baserat på efternamn
	@GET
	@Path("/lastName/{lastName}")
	public Collection<UserWeb> getByLastNameWeb(@PathParam("lastName") String lastName) {

		Collection<User> user = getBean(UserService.class).getByLastName(lastName);

		if (user == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		
		Collection<UserWeb> userWeb = new ArrayList<UserWeb>();
		
		user.forEach(u -> userWeb.add(new UserWeb(u.getUsername(), u.getFirstName(), u.getLastName(), u.getUserNumber(), u.getStatus())));

		return userWeb;
	}
	
	// - Söka efter en User baserat på användarnamn
	@GET
	@Path("/username/{username}")
	public UserWeb getByUsernameWeb(@PathParam("username") String username) {

		User user = getBean(UserService.class).getByUsername(username);

		if (user == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}

		return new UserWeb(user.getUsername(), user.getFirstName(), user.getLastName(), user.getUserNumber(), user.getStatus());
	}

	// - Hämta alla User som ingår i ett visst team
	@GET
	@Path("/team/{team}")
	public Collection<UserWeb> getByTeamWeb(@PathParam("team") String team) {

		Collection<User> user = getBean(UserService.class).getUsersByTeam(team);

		if (user == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		
		Collection<UserWeb> userWeb = new ArrayList<UserWeb>();
		
		user.forEach(u -> userWeb.add(new UserWeb(u.getUsername(), u.getFirstName(), u.getLastName(), u.getUserNumber(), u.getStatus())));

		return userWeb;
	}
	
	// Update
	// - Uppdatera en User 
	@PUT
	@Path("/userNumber/{userNumber}")
	public Response update(@PathParam("userNumber") String userNumber, UserWeb userWeb) {
			
		User user = getBean(UserService.class).getByUserNumber(userNumber.toString());
			
		if (user == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
			
		user.setUsername(userWeb.getUsername()).setFirstName(userWeb.getFirstName()).setLastName(userWeb.getLastName()).setUserNumber(userWeb.getUserNumber()).setStatus(userWeb.getStatus());
			
		getBean(UserService.class).save(user);
			
		return Response.noContent().build();
	}
	
	// - Tilldela en work item till en User 
	@PUT
	@Path("/addWorkItem/{userNumber}")
	public Response addWorkItem(@PathParam("userNumber") String userNumber, WorkItemWeb workItemWeb) {
			
		User user = getBean(UserService.class).getByUserNumber(userNumber.toString());
			
		if (user == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		
		WorkItem workItem = new WorkItem(workItemWeb.getLable(), workItemWeb.getDescription(), workItemWeb.getWorkItemNumber(), workItemWeb.getStatus());
		user.addWorkItem(workItem);
			
		getBean(WorkItemService.class).save(workItem);
		getBean(UserService.class).save(user);
			
		return Response.noContent().build();
	}

	// Delete
	// - Ta bort en User (inaktivera) 
	@DELETE
	@Path("/userNumber/{userNumber}")
	public Response delete(@PathParam("userNumber") String userNumber) {
		
		User user = getBean(UserService.class).getByUserNumber(userNumber);
		
		if (user == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		
		user.setStatus("Inactive");
		
		getBean(UserService.class).save(user);
		
		return Response.noContent().build();
	}
		
}






















