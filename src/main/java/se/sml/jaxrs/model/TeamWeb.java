package se.sml.jaxrs.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import se.sml.sdj.model.Team;

@XmlRootElement
public class TeamWeb {
	
	@XmlElement
	private final String name;
	@XmlElement
	private final String status;
	@XmlElement(name = "user")
	@XmlElementWrapper(name = "users")
	private final Collection<UserWeb> users;

	@SuppressWarnings("unused")
	private TeamWeb() {
		this(null);
	}

	public TeamWeb(Team team) {
		this.name = team.getName();
		this.status = team.getStatus();
		this.users = new ArrayList<>();
	}


	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public Collection<UserWeb> getUsersWeb() {
		return users;
	}

	public void addUserWeb(UserWeb userWeb) {
		users.add(userWeb);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	@Override
	public boolean equals(Object otherObj) {
		if (this == otherObj) {
			return true;
		}

		if (otherObj instanceof TeamWeb) {
			TeamWeb otherTeamWeb = (TeamWeb) otherObj;
			return this.name.equals(otherTeamWeb.name);
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result += 37 * name.hashCode();
		return result;
	}
}
