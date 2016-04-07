package se.sml.jaxrs.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@XmlRootElement
public final class UserWeb {
	
//	@XmlElement
//	private final Long id;
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
//	@XmlElement(name = "workItemWeb")
//	@XmlElementWrapper(name = "workItemsWeb")
//	private final Collection<WorkItemWeb> workItemsWeb;

	@SuppressWarnings("unused")
	private UserWeb() {
		this("","","","","");
	}

	public UserWeb(String username, String firstName, String lastName, String userNumber, String status) {
//		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userNumber = userNumber;
		this.status = status;
//		this.workItemsWeb = new ArrayList<>();
	}

//	public Long getId() {
//		return id;
//	}

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

//	public Collection<WorkItemWeb> getWorkItemWeb() {
//		return workItemsWeb;
//	}

	public String getStatus() {
		return status;
	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//	public void setUserNumber(String userNumber) {
//		this.userNumber = userNumber;
//	}
//
//	public User addWorkItem(WorkItem workItem) {
//		workItems.add(workItem);
//		return this;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}

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
