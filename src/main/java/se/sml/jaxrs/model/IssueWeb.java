package se.sml.jaxrs.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import se.sml.sdj.model.Issue;

@XmlRootElement
public class IssueWeb {

	@XmlElement
	private final String note;

	IssueWeb() {
		this(null);
	}

	public IssueWeb(Issue issue) {
		this.note = issue.getIssue();
	}

	public String getIssue() {
		return note;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
