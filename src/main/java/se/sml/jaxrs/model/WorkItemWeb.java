package se.sml.jaxrs.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import se.sml.sdj.model.WorkItem;

import static se.sml.jaxrs.model.parser.Parser.issueParser;

@XmlRootElement
public class WorkItemWeb {

	@XmlElement
	private final String lable;
	@XmlElement
	private final String description;
	@XmlElement
	private final String workItemNumber;
	@XmlElement
	private final String status;
	@XmlElement
	private final IssueWeb issue;

	@SuppressWarnings("unused")
	private WorkItemWeb() {
		this(null);
	}

	public WorkItemWeb(WorkItem workItem) {
		this.lable = workItem.getLable();
		this.description = workItem.getDescription();
		this.workItemNumber = workItem.getWorkItemNumber();
		this.status = workItem.getStatus();
		this.issue = issueParser(workItem.getIssue());
	}

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

	public IssueWeb getIssueWeb() {
		return issue;
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
