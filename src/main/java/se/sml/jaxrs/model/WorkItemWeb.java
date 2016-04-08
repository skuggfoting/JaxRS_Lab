package se.sml.jaxrs.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@XmlRootElement
public class WorkItemWeb {
	
//	@XmlElement
//	private final Long id;
	@XmlElement
	private final String lable;
	@XmlElement
	private final String description;
	@XmlElement
	private final String workItemNumber;
	@XmlElement
	private final String status;
//	@XmlElement(name = "IssueWeb")
//	@XmlElementWrapper(name = "issuesWeb")
//	private final IssueWeb issueWeb;

	@SuppressWarnings("unused")
	private WorkItemWeb() {
		this("","","","");
	}

	public WorkItemWeb(String lable, String description, String workItemNumber, String status) {
//		this.id = id;
		this.lable = lable;
		this.description = description;
		this.workItemNumber = workItemNumber;
		this.status = status;
//		this.issueWeb = new IssueWeb();
	}

//	public Long getId() {
//		return id;
//	}

	public String getLable() {
		return lable;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getWorkItemNumber() {
		return workItemNumber;
	}
	
	public String getStatus() {
		return status;
	}
	
//	public IssueWeb getIssueWeb() {
//		return issueWeb;
//	}
	
//	public void setLable(String lable) {
//		this.lable = lable;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//	
//	public void addIssue(Issue issue) {
//		this.issue = issue;
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

		if (otherObj instanceof WorkItemWeb) {
			WorkItemWeb otherWorkItemWeb = (WorkItemWeb) otherObj;
			return this.workItemNumber.equals(otherWorkItemWeb.workItemNumber);
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result += 37 * workItemNumber.hashCode();
		return result;
	}
}
