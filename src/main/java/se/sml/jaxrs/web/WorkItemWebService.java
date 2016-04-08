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
import se.sml.sdj.service.UserService;
import se.sml.sdj.service.WorkItemService;



@Path("/workItem")
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
	// - Skapa en work item 
	@POST
	public Response addWorkItem(WorkItemWeb workItemWeb) {

		WorkItem workItem = new WorkItem(workItemWeb.getLable(), workItemWeb.getDescription(), workItemWeb.getWorkItemNumber(), workItemWeb.getStatus());
		
		getBean(WorkItemService.class).save(workItem);
		
		URI location = uriInfo.getAbsolutePathBuilder().path(getClass(), "getByWorkItemNumberWeb").build(workItemWeb.getWorkItemNumber());

		return Response.created(location).build();
	}
	
	// Read
	@GET
	@Path("/workItemNumber/{workItemNumber}")
	public WorkItemWeb getByWorkItemNumberWeb(@PathParam("workItemNumber") String workItemNumber) {

		WorkItem workItem = getBean(WorkItemService.class).getByWorkItemNumber(workItemNumber);

		if (workItem == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		
		return new WorkItemWeb(workItem.getLable(), workItem.getDescription(), workItem.getWorkItemNumber(), workItem.getStatus());
	}

	// - Hämta alla work item baserat på status 
	@GET
	@Path("/status/{status}")
	public Collection<WorkItemWeb> getByStatusWeb(@PathParam("status") String status) {

		Collection<WorkItem> workItem = getBean(WorkItemService.class).getByStatus(status);

		if (workItem == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		
		Collection<WorkItemWeb> workItemWeb = new ArrayList<WorkItemWeb>();
		
		workItem.forEach(wi -> workItemWeb.add(new WorkItemWeb(wi.getLable(), wi.getDescription(), wi.getWorkItemNumber(), wi.getStatus())));

		return workItemWeb;
	}
	
	// - Hämta alla work item för ett Team 
	@GET
	@Path("/team/{team}")
	public Collection<WorkItemWeb> getByTeamWeb(@PathParam("team") String team) {

		Collection<WorkItem> workItem = getBean(WorkItemService.class).getByTeam(team);

		if (workItem == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		
		Collection<WorkItemWeb> workItemWeb = new ArrayList<WorkItemWeb>();
		
		workItem.forEach(wi -> workItemWeb.add(new WorkItemWeb(wi.getLable(), wi.getDescription(), wi.getWorkItemNumber(), wi.getStatus())));

		return workItemWeb;
	}
	
	// - Hämta alla work item för en User 
	@GET
	@Path("/user/{username}")
	public Collection<WorkItemWeb> getByUserWeb(@PathParam("username") String username) {

		Collection<WorkItem> workItem = getBean(UserService.class).getWorkItemsByUser(username);

		if (workItem == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		
		Collection<WorkItemWeb> workItemWeb = new ArrayList<WorkItemWeb>();
		
		workItem.forEach(wi -> workItemWeb.add(new WorkItemWeb(wi.getLable(), wi.getDescription(), wi.getWorkItemNumber(), wi.getStatus())));

		return workItemWeb;
	}
	
	// - Hämta alla work item som innehåller en viss text i sin beskrivning
	@GET
	@Path("/descriptionContaining/{text}")
	public Collection<WorkItemWeb> getByDescriptionContainingWeb(@PathParam("text") String text) {

		Collection<WorkItem> workItem = getBean(WorkItemService.class).getByDescriptionContaining(text);

		if (workItem == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		
		Collection<WorkItemWeb> workItemWeb = new ArrayList<WorkItemWeb>();
		
		workItem.forEach(wi -> workItemWeb.add(new WorkItemWeb(wi.getLable(), wi.getDescription(), wi.getWorkItemNumber(), wi.getStatus())));

		return workItemWeb;
	}
	
	// Update
	// - Ändra status på en work item 
	@PUT
	@Path("/workItemNumber/{workItemNumber}")
	public Response updateUser(@PathParam("workItemNumber") String workItemNumber, WorkItemWeb workItemWeb) {
			
		WorkItem workItem = getBean(WorkItemService.class).getByWorkItemNumber(workItemNumber);

		if (workItem == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
			
		workItem.setLable(workItemWeb.getLable()).setDescription(workItemWeb.getDescription()).setWorkItemNumber(workItemWeb.getWorkItemNumber()).setStatus(workItemWeb.getStatus());
			
		getBean(WorkItemService.class).save(workItem);
			
		return Response.noContent().build();
	}

	// Delete
	// - Ta bort en work item (inaktivera) 
	@DELETE
	@Path("/workItemNumber/{workItemNumber}")
	public Response deleteUser(@PathParam("workItemNumber") String workItemNumber) {
		
		WorkItem workItem = getBean(WorkItemService.class).getByWorkItemNumber(workItemNumber);
		
		if (workItem == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		
		workItem.setStatus("Inactive");
		
		getBean(WorkItemService.class).save(workItem);
		
		return Response.noContent().build();
	}
		
}






















