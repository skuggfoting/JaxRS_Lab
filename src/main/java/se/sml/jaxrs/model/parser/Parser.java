package se.sml.jaxrs.model.parser;

import java.util.ArrayList;
import java.util.Collection;

import se.sml.jaxrs.model.IssueWeb;
import se.sml.jaxrs.model.WorkItemWeb;
import se.sml.sdj.model.Issue;
import se.sml.sdj.model.WorkItem;

public final class Parser {

	private Parser() {
	}

	public static IssueWeb issueParser(Issue issue) {

		if (issue != null) {
			return new IssueWeb(issue);
		}
		else {
			Issue noIssue = new Issue("");

			return new IssueWeb(noIssue);
		}
	}

	public static Collection<WorkItemWeb> workItemParser(Collection<WorkItem> workItem) {

		Collection<WorkItemWeb> workItemWeb = new ArrayList<>();

		workItem.forEach(wi -> workItemWeb.add(new WorkItemWeb(wi)));

		return workItemWeb;
	}

}
