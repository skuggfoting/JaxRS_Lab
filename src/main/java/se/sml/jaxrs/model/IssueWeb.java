package se.sml.jaxrs.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Embeddable
public class IssueWeb {

	@Column()
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
