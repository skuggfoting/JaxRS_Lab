package se.sml.jaxrs.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@XmlRootElement
public class IssueWeb {

	@XmlElement
	private final String note;

	IssueWeb() {
		this("");
	}

	public IssueWeb(String note) {
		this.note = note;
	}

	public String getIssue() {
		return note;
	}

//	public void setIssue(String note) {
//		this.note = note;
//	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
