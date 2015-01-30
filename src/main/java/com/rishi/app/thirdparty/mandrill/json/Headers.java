package com.rishi.app.thirdparty.mandrill.json;

import org.codehaus.jackson.annotate.JsonProperty;

public class Headers {
	@JsonProperty("Reply-To")
	private String replyTo;

	public String getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}
}
