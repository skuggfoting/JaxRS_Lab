package se.sml.jaxrs.web;

import static se.sml.jaxrs.ContextLoader.getBean;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import se.sml.jaxrs.model.TeamWeb;
import se.sml.sdj.model.Team;
import se.sml.sdj.service.TeamService;

@Path("/team")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public final class TeamWebService {

	@Context
	private UriInfo uriInfo;
 
//	@GET
//	@Path("{id}")
//	public TeamWeb getTeamWeb(@PathParam("id") Long id) {
//
//		Team team = getBean(TeamService.class).findByName(id);
//
//		if (team == null) {
//			throw new WebApplicationException(Status.NOT_FOUND);
//		}
//
//		return new TeamWeb(team.getId(), team.getName(), team.getStatus());
//	}

}
