package se.sml.jaxrs.model;

import static se.sml.jaxrs.model.parser.Parser.workItemParser;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import se.sml.sdj.model.User;

@XmlRootElement
public final class UserWeb {

	@XmlElement
	private final String username;
	@XmlElement
	private final String firstName;
	@XmlElement
	private final String lastName;
	@XmlElement
	private final String userNumber;
	@XmlElement
	private final String status;
	@XmlElement
	private final Collection<WorkItemWeb> workItems;

	@SuppressWarnings("unused")
	private UserWeb() {
		this(null);
	}

	public UserWeb(User user) {
		this.username = user.getUsername();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.userNumber = user.getFirstName();
		this.status = user.getStatus();
		this.workItems = workItemParser(user.getWorkItem());
	}

	public String getUsername() {
		return username;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public Collection<WorkItemWeb> getWorkItemWeb() {
		return workItems;
	}

	public String getStatus() {
		return status;
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

		if (otherObj instanceof UserWeb) {
			UserWeb otherUserWeb = (UserWeb) otherObj;
			return this.username.equals(otherUserWeb.username);
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result += 37 * username.hashCode();
		return result;
	}
}
