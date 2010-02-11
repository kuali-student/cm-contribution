package org.kuali.student.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 */
public class RecordedSession {
	private static final long serialVersionUID = 1L;

	List<RecordedRequest> recordedRequests = Collections.synchronizedList(new ArrayList<RecordedRequest>());
	String remoteAddress;
	String browser;

	public List<RecordedRequest> getRecordedRequests() {
		return recordedRequests;
	}

	public void setRecordedRequests(List<RecordedRequest> recordedRequests) {
		this.recordedRequests = recordedRequests;
	}

	public String getRemoteAddress() {
		return remoteAddress;
	}

	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}
}
